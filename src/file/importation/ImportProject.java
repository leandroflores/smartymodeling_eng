package file.importation;

import com.mxgraph.util.mxPoint;
import file.importation.diagram.types.ImportActivityDiagram;
import file.importation.diagram.types.ImportClassDiagram;
import file.importation.diagram.types.ImportComponentDiagram;
import file.importation.diagram.types.ImportFeatureDiagram;
import file.importation.diagram.types.ImportSequenceDiagram;
import file.importation.diagram.types.ImportUseCaseDiagram;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import model.structural.base.Diagram;
import model.structural.base.Profile;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import model.structural.base.association.Link;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.product.Relationship;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Traceability;
import model.structural.diagram.classes.base.TypeUML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <p>Class of File <b>ImportProject</b>.</p>
 * <p>Class responsible for <b>Importing Project</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    model.structural.base.Element
 * @see    model.structural.base.Project
 */
public class ImportProject {
    protected final String path;
    protected final XPath  xPath;
    protected File     file;
    protected Document document;
    protected NodeList nodeList;
    protected String   expression;
    protected Project  project;
    private   Element  root;
    
    /**
     * Default constructor method of Class.
     * @param path File Path.
     */
    public ImportProject(String path) {
        this.path  = path;
        this.xPath = XPathFactory.newInstance().newXPath();
    }
    
    /**
     * Method responsible for opening the File.
     * @throws ParserConfigurationException 
     * @throws SAXException
     * @throws IOException 
     */
    private void openFile() throws ParserConfigurationException, SAXException, IOException {
        file      = new File(path);
        document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
        document.getDocumentElement().normalize();
    }
    
    /**
     * Method responsible for initializing the File.
     * @throws ParserConfigurationException
     * @throws SAXException 
     * @throws IOException
     */
    protected void init() throws ParserConfigurationException, SAXException, IOException {
        openFile();
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws javax.xml.xpath.XPathExpressionException 
     */
    public Project getProject() throws IOException, ParserConfigurationException, SAXException, XPathExpressionException {
        init();
        
        expression = "/project";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        root       = (Element) nodeList.item(0);
        project    = new Project(path, root);
        
            importTypes();
            importStereotypes();
            importProfile();
            importDiagrams();
            importRequirements();
            importTraceabilities();
            importLinks();
            importProducts();
            importMetrics();
            importMeasures();
        
               project.updateStereotypes();
        return project;
    }
    
    /**
     * Method responsible for importing Project Types.
     * throws XPathExpressionException XPath Exception.
     */
    private void importTypes() throws XPathExpressionException {
        expression = "/project/types/type";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);
            TypeUML type    = new TypeUML(element);
            project.addDefaultType(type);
        }
    }
    
    /**
     * Method responsible for importing Project Stereotypes.
     * throws XPathExpressionException XPath Exception.
     */
    private void importStereotypes() throws XPathExpressionException {
        expression = "/project/stereotypes/stereotype";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element    element    = (Element) nodeList.item(i);
            Stereotype stereotype = new Stereotype(element);
            project.addDefaultStereotype(stereotype);
        }
    }
    
    /**
     * Method responsible for importing Project Profile.
     * throws XPathExpressionException XPath Exception.
     */
    private void importProfile() throws XPathExpressionException {
        expression = "/project/profile";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        Element element = (Element) nodeList.item(0);
        Profile profile = new Profile();
                profile.setMandatory((Stereotype) project.getStereotypes().get(element.getAttribute("mandatory")));
                profile.setOptional((Stereotype) project.getStereotypes().get(element.getAttribute("optional")));
                profile.setVariationPoint((Stereotype) project.getStereotypes().get(element.getAttribute("variationPoint")));
                profile.setInclusive((Stereotype) project.getStereotypes().get(element.getAttribute("inclusive")));
                profile.setExclusive((Stereotype) project.getStereotypes().get(element.getAttribute("exclusive")));
                profile.setRequires((Stereotype) project.getStereotypes().get(element.getAttribute("requires")));
                profile.setMutex((Stereotype) project.getStereotypes().get(element.getAttribute("mutex")));
        project.setProfile(profile);
    }
    
    /**
     * Method responsible for importing Project Diagrams.
     * throws XPathExpressionException XPath Exception. 
     */
    private void importDiagrams() throws XPathExpressionException {
        String[] types = {"Feature", "Activity", "Class", "Component", "UseCase", "Sequence"};
        for (int i = 0; i < types.length; i++) {
                   expression = "/project/diagram";
            String filter     = expression + "[@type='" + types[i] + "']";
                   nodeList   = (NodeList) xPath.compile(filter).evaluate(document, XPathConstants.NODESET);
            for (int x = 0; x < nodeList.getLength(); x++)
                importDiagram((Element) nodeList.item(x), i);
        }
    }
    
    /**
     * Method responsible for importing Project Diagram.
     * @param element W3C Element.
     * @param index Diagram Index.
     * throws XPathExpressionException XPath Exception.
     */
    private void importDiagram(Element element, int index) throws XPathExpressionException {
        switch (index) {
            case 0:
                project.addDiagram(new ImportFeatureDiagram(project, element).importDiagram());
                break;
            case 1:
                project.addDiagram(new ImportActivityDiagram(project, element).importDiagram());
                break;
            case 2:
                project.addDiagram(new ImportClassDiagram(project, element).importDiagram());
                break;
            case 3:
                project.addDiagram(new ImportComponentDiagram(project, element).importDiagram());
                break;
            case 4:
                project.addDiagram(new ImportUseCaseDiagram(project, element).importDiagram());
                break;
            case 5:
                project.addDiagram(new ImportSequenceDiagram(project, element).importDiagram());
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for importing Project Requirements.
     * throws XPathExpressionException XPath Exception.
     */
    private void importRequirements() throws XPathExpressionException {
        expression = "/project/requirement";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element     current     = (Element) nodeList.item(i);
            Requirement requirement = new Requirement(current);
                        requirement.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                addTraceabilities(requirement, current);
            project.addRequirement(requirement);
        }
    }
    
    /**
     * Method responsible for adding the Requirement Traceabilities.
     * @param current W3C Element.
     * @param requirement Requirement.
     * @throws XPathExpressionException XPath Exception.
     */
    private void addTraceabilities(Requirement requirement, Element current) throws XPathExpressionException {
        String[] tags = {"feature", "usecase", "class", "component", "sequence", "activity"};
        for (String tag : tags) {
            String   script = expression + "[@id='" + requirement.getId() + "']/" + tag;
            NodeList list   = (NodeList) xPath.compile(script).evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < list.getLength(); i++)
                requirement.addElement(tag, getElement(((Element) list.item(i)).getAttribute("element")));
        }
    }
    
    /**
     * Method responsible for importing the Project Traceabilities.
     * throws XPathExpressionException XPath Exception.
     */
    private void importTraceabilities() throws XPathExpressionException {
        expression = "/project/traceability";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element      current      = (Element) nodeList.item(i);
            Traceability traceability = new Traceability(current);
                         traceability.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                addElements(traceability, current);
            project.addTraceability(traceability);
        }
    }
    
    /**
     * Method responsible for adding the Requirement Traceabilities.
     * @param current W3C Element.
     * @param traceability Traceability.
     * @throws XPathExpressionException XPath Exception.
     */
    private void addElements(Traceability traceability, Element current) throws XPathExpressionException {
        NodeList list = current.getElementsByTagName("element");
        for (int i = 0; i < list.getLength(); i++)
            traceability.addElement(getElement(((Element) list.item(i)).getAttribute("id")));
    }
    
    /**
     * Method responsible for importing the Project Links.
     * throws XPathExpressionException XPath Exception.
     */
    private void importLinks() throws XPathExpressionException {
        expression = "/project/links/link";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element current = (Element) nodeList.item(i);
            Link    link    = new Link(getElement(current.getAttribute("element")), 
                                      (Stereotype) project.getStereotypes().get(current.getAttribute("stereotype")));
            project.addLink(link);
        }
    }
    
    /**
     * Method responsible for importing the Project Products.
     * throws XPathExpressionException XPath Exception. 
     */
    private void importProducts() throws XPathExpressionException {
        expression = "/project/products/product";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element current = (Element) nodeList.item(i);
            Product product = new Product(current);
                    product.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                addInstances(product, current);
            project.addProduct(product);
        }
    }
    
    /**
     * Method responsible for adding the Product Instances.
     * @param product Product.
     * @param current W3C Element.
     */
    private void addInstances(Product product, Element current) {
        NodeList list = current.getElementsByTagName("instance");
        for (int i = 0; i < list.getLength(); i++) {
            Element  node     = (Element) list.item(i);
            Instance instance = new Instance(node);
                     instance.setProduct(product);
                     instance.setDiagram((Diagram) getDiagram(node.getAttribute("diagram")));
                addArtifacts(instance, node);
                addRelationships(instance, node);
            product.addInstance(instance);
        }
    }
    
    /**
     * Method responsible for adding the Instance Artifacts.
     * @param instance Instance.
     * @param current W3C Element.
     */
    private void addArtifacts(Instance instance, Element current) {
        NodeList list = current.getElementsByTagName("artifact");
        for (int i = 0; i < list.getLength(); i++) {
            Element  node     = (Element) list.item(i);
            Artifact artifact = new Artifact(node, true);
                     artifact.setInstance(instance);
                     artifact.setElement(getElement(node.getAttribute("element")));
            instance.addArtifact(artifact);
        }
    }
    
    /**
     * Method responsible for adding the Instance Relationships.
     * @param instance Instance.
     * @param current W3C Element.
     */
    private void addRelationships(Instance instance, Element current) {
        NodeList list = current.getElementsByTagName("relationship");
        for (int i = 0; i < list.getLength(); i++) {
            Element      node         = (Element) list.item(i);
            Association  association  = (Association) instance.getDiagram().getAssociation(node.getAttribute("association"));
            Relationship relationship = new Relationship(node);
                         relationship.setInstance(instance);
                         relationship.setAssociation(association);
                addPoints(node, relationship);
            instance.addRelationship(relationship);
        }
    }
    
    /**
     * Method responsible for adding the Relationship Points.
     * @param node W3C Element.
     * @param relationship Relationship.
     */
    protected void addPoints(Element node, Relationship relationship) {
        NodeList list = node.getElementsByTagName("point");
        for (int i = 0; i < list.getLength(); i++)
            relationship.addPoint(getPoint((Element) list.item(i)));
    }
    
    /**
     * Method responsible for returning the Point by W3C Element.
     * @param  node W3C Element.
     * @return Point.
     */
    protected mxPoint getPoint(Element node) {
        return new mxPoint(Double.parseDouble(node.getAttribute("x").trim()), 
                           Double.parseDouble(node.getAttribute("y").trim()));
    }
    
    /**
     * Method responsible for importing the Project Metrics.
     * throws XPathExpressionException XPath Exception. 
     */
    private void importMetrics() throws XPathExpressionException {
        expression = "/project/metric";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element current = (Element) nodeList.item(i);
            Metric  metric  = new Metric(current);
                    metric.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                    metric.setOperation(current.getElementsByTagName("operation").item(0).getTextContent());
            project.addMetric(metric);
        }
    }
    
    /**
     * Method responsible for importing the Project Measures.
     * throws XPathExpressionException XPath Exception. 
     */
    private void importMeasures() throws XPathExpressionException {
        expression = "/project/measure";
        nodeList   = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element current = (Element) nodeList.item(i);
            Measure measure = new Measure(current);
                    measure.setMetric(project.getMetric(current.getAttribute("metric")));
            project.addMeasure(measure);
        }
    }
    
    /**
     * Method responsible for returning the Element by Id.
     * @param  id Element Id.
     * @return Element by Id.
     */
    protected model.structural.base.Element getElement(String id) {
        return (model.structural.base.Element) project.objects.get(id);
    }
    
    /**
     * Method responsible for returning the Stereotype by Id.
     * @param  id Stereotype Id.
     * @return Stereotype by Id.
     */
    protected Stereotype getStereotype(String id) {
        return project.getStereotype(id);
    }
    
    /**
     * Method responsible for returning the Diagram by Id.
     * @param  id Diagram Id.
     * @return Diagram by Id.
     */
    protected Diagram getDiagram(String id) {
        return (Diagram) project.getDiagrams().get(id);
    }
}
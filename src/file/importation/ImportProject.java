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
 * <p>Class of Import <b>ImportProject</b>.</p>
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
        this.file      = new File(this.path);
        this.document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(this.file);
        this.document.getDocumentElement().normalize();
    }
    
    /**
     * Method responsible for initializing the File.
     * @throws ParserConfigurationException
     * @throws SAXException 
     * @throws IOException
     */
    protected void init() throws ParserConfigurationException, SAXException, IOException {
        this.openFile();
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
        this.init();
        
        this.expression = "/project";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        this.root       = (Element) this.nodeList.item(0);
        this.project    = new Project(this.path, this.root);
        
            this.importTypes();
            this.importStereotypes();
            this.importProfile();
            this.importDiagrams();
            this.importRequirements();
            this.importTraceabilities();
            this.importLinks();
            this.importProducts();
            this.importMetrics();
            this.importMeasures();
        
               this.project.updateStereotypes();
        return this.project;
    }
    
    /**
     * Method responsible for importing Project Types.
     * throws XPathExpressionException XPath Exception.
     */
    private void importTypes() throws XPathExpressionException {
        this.expression = "/project/types/type";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element element = (Element) this.nodeList.item(i);
            TypeUML type    = new TypeUML(element);
            this.project.addDefaultType(type);
        }
    }
    
    /**
     * Method responsible for importing Project Stereotypes.
     * throws XPathExpressionException XPath Exception.
     */
    private void importStereotypes() throws XPathExpressionException {
        this.expression = "/project/stereotypes/stereotype";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element    element    = (Element) this.nodeList.item(i);
            Stereotype stereotype = new Stereotype(element);
            this.project.addDefaultStereotype(stereotype);
        }
    }
    
    /**
     * Method responsible for importing Project Profile.
     * throws XPathExpressionException XPath Exception.
     */
    private void importProfile() throws XPathExpressionException {
        this.expression = "/project/profile";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        Element element = (Element) this.nodeList.item(0);
        Profile profile = new Profile();
                profile.setMandatory((Stereotype) this.project.getStereotypes().get(element.getAttribute("mandatory")));
                profile.setOptional((Stereotype) this.project.getStereotypes().get(element.getAttribute("optional")));
                profile.setVariationPoint((Stereotype) this.project.getStereotypes().get(element.getAttribute("variationPoint")));
                profile.setInclusive((Stereotype) this.project.getStereotypes().get(element.getAttribute("inclusive")));
                profile.setExclusive((Stereotype) this.project.getStereotypes().get(element.getAttribute("exclusive")));
                profile.setRequires((Stereotype) this.project.getStereotypes().get(element.getAttribute("requires")));
                profile.setMutex((Stereotype) this.project.getStereotypes().get(element.getAttribute("mutex")));
        this.project.setProfile(profile);
    }
    
    /**
     * Method responsible for importing Project Diagrams.
     * throws XPathExpressionException XPath Exception. 
     */
    private void importDiagrams() throws XPathExpressionException {
        String[] types = {"Feature", "Activity", "Class", "Component", "UseCase", "Sequence"};
        for (int i = 0; i < types.length; i++) {
            this.expression = "/project/diagram";
            String filter   = this.expression + "[@type='" + types[i] + "']";
            this.nodeList   = (NodeList) this.xPath.compile(filter).evaluate(this.document, XPathConstants.NODESET);
            for (int x = 0; x < this.nodeList.getLength(); x++)
                this.importDiagram((Element) this.nodeList.item(x), i);
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
                this.project.addDiagram(new ImportFeatureDiagram(this.project, element).importDiagram());
                break;
            case 1:
                this.project.addDiagram(new ImportActivityDiagram(this.project, element).importDiagram());
                break;
            case 2:
                this.project.addDiagram(new ImportClassDiagram(this.project, element).importDiagram());
                break;
            case 3:
                this.project.addDiagram(new ImportComponentDiagram(this.project, element).importDiagram());
                break;
            case 4:
                this.project.addDiagram(new ImportUseCaseDiagram(this.project, element).importDiagram());
                break;
            case 5:
                this.project.addDiagram(new ImportSequenceDiagram(this.project, element).importDiagram());
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
        this.expression = "/project/requirement";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element     current     = (Element) this.nodeList.item(i);
            Requirement requirement = new Requirement(current);
                        requirement.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                this.addTraceabilities(requirement, current);
            this.project.addRequirement(requirement);
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
            String   script = this.expression + "[@id='" + requirement.getId() + "']/" + tag;
            NodeList list   = (NodeList) this.xPath.compile(script).evaluate(this.document, XPathConstants.NODESET);
            for (int i = 0; i < list.getLength(); i++)
                requirement.addElement(tag, this.getElement(((Element) list.item(i)).getAttribute("element")));
        }
    }
    
    /**
     * Method responsible for importing the Project Traceabilities.
     * throws XPathExpressionException XPath Exception.
     */
    private void importTraceabilities() throws XPathExpressionException {
        this.expression = "/project/traceability";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element      current      = (Element) this.nodeList.item(i);
            Traceability traceability = new Traceability(current);
                         traceability.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                this.addElements(traceability, current);
            this.project.addTraceability(traceability);
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
            traceability.addElement(this.getElement(((Element) list.item(i)).getAttribute("id")));
    }
    
    /**
     * Method responsible for importing the Project Links.
     * throws XPathExpressionException XPath Exception.
     */
    private void importLinks() throws XPathExpressionException {
        this.expression = "/project/links/link";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element current = (Element) this.nodeList.item(i);
            Link    link    = new Link(this.getElement(current.getAttribute("element")), 
                                      (Stereotype) this.project.getStereotypes().get(current.getAttribute("stereotype")));
            this.project.addLink(link);
        }
    }
    
    /**
     * Method responsible for importing the Project Products.
     * throws XPathExpressionException XPath Exception. 
     */
    private void importProducts() throws XPathExpressionException {
        this.expression = "/project/products/product";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element current = (Element) this.nodeList.item(i);
            Product product = new Product(current);
                    product.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                this.addInstances(product, current);
            this.project.addProduct(product);
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
                     instance.setDiagram((Diagram) this.getDiagram(node.getAttribute("diagram")));
                this.addArtifacts(instance, node);
                this.addRelationships(instance, node);
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
                     artifact.setElement(this.getElement(node.getAttribute("element")));
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
                this.addPoints(node, relationship);
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
            relationship.addPoint(this.getPoint((Element) list.item(i)));
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
        this.expression = "/project/metric";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element current = (Element) this.nodeList.item(i);
            Metric  metric  = new Metric(current);
                    metric.setDescription(current.getElementsByTagName("description").item(0).getTextContent());
                    metric.setOperation(current.getElementsByTagName("operation").item(0).getTextContent());
            this.project.addMetric(metric);
        }
    }
    
    /**
     * Method responsible for importing the Project Measures.
     * throws XPathExpressionException XPath Exception. 
     */
    private void importMeasures() throws XPathExpressionException {
        this.expression = "/project/measure";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element current = (Element) this.nodeList.item(i);
            Measure measure = new Measure(current);
                    measure.setMetric(this.project.getMetric(current.getAttribute("metric")));
            this.project.addMeasure(measure);
        }
    }
    
    /**
     * Method responsible for returning the Element by Id.
     * @param  id Element Id.
     * @return Element by Id.
     */
    protected model.structural.base.Element getElement(String id) {
        return (model.structural.base.Element) this.project.objects.get(id);
    }
    
    /**
     * Method responsible for returning the Stereotype by Id.
     * @param  id Stereotype Id.
     * @return Stereotype by Id.
     */
    protected Stereotype getStereotype(String id) {
        return this.project.getStereotype(id);
    }
    
    /**
     * Method responsible for returning the Diagram by Id.
     * @param  id Diagram Id.
     * @return Diagram by Id.
     */
    protected Diagram getDiagram(String id) {
        return (Diagram) this.project.getDiagrams().get(id);
    }
}
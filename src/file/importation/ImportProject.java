package file.importation;

import file.importation.diagram.types.ImportActivityDiagram;
import file.importation.diagram.types.ImportClassDiagram;
import file.importation.diagram.types.ImportComponentDiagram;
import file.importation.diagram.types.ImportUseCaseDiagram;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import model.structural.base.Profile;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import model.structural.base.association.Link;
import model.structural.diagram.classes.base.TypeUML;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * <p>Class of Import <b>ImportProject</b>.</p>
 * <p>Class responsible for <b>Importing Project</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
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
     * @throws XPathExpressionException 
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
        this.importLinks();
        
               this.project.updateStereotypes();
        return this.project;
    }
    
    /**
     * Method responsible for importing Project Types.
     * @throws XPathExpressionException
     */
    private void importTypes() throws XPathExpressionException {
        this.expression = "/project/types/type";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element   element = (Element) this.nodeList.item(i);
            TypeUML   typeUML = new TypeUML(element);
            this.project.addDefaultType(typeUML);
        }
    }
    
    /**
     * Method responsible for importing Project Stereotypes.
     * @throws XPathExpressionException
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
     * @throws XPathExpressionException
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
        this.project.setProfile(profile);
    }
    
    /**
     * Method responsible for importing Project Diagrams.
     * @throws XPathExpressionException 
     */
    private void importDiagrams() throws XPathExpressionException {
        String[] types = {"Activity", "Class", "Component", "Sequence", "UseCase"};
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
     * @throws XPathExpressionException
     */
    private void importDiagram(Element element, int index) throws XPathExpressionException {
        switch (index) {
            case 0:
                this.project.addDiagram(new ImportActivityDiagram(this.project, element).importDiagram());
                break;
            case 1:
                this.project.addDiagram(new ImportClassDiagram(this.project, element).importDiagram());
                break;
            case 2:
                this.project.addDiagram(new ImportComponentDiagram(this.project, element).importDiagram());
                break;
            case 4:
                this.project.addDiagram(new ImportUseCaseDiagram(this.project, element).importDiagram());
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for importing Project Links.
     * @throws XPathExpressionException
     */
    private void importLinks() throws XPathExpressionException {
        this.expression = "/project/links/link";
        this.nodeList   = (NodeList) this.xPath.compile(this.expression).evaluate(this.document, XPathConstants.NODESET);
        for (int i = 0; i < this.nodeList.getLength(); i++) {
            Element current = (Element) this.nodeList.item(i);
            Link    link    = new Link((model.structural.base.Element) this.project.objects.get(current.getAttribute("element")), 
                                                          (Stereotype) this.project.getStereotypes().get(current.getAttribute("stereotype")));
            this.project.addLink(link);
        }
    }
}
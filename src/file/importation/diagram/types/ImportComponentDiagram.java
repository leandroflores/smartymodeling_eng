package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Project;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
import model.structural.diagram.component.base.association.ComunicationUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportComponentDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Component Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/07/2019
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.ComponentDiagram
 */
public class ImportComponentDiagram extends ImportDiagram {
    private final ComponentDiagram componentDiagram;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportComponentDiagram(Project project, Element element) {
        this.componentDiagram = new ComponentDiagram(project, element);
        this.diagram          = this.componentDiagram;
        this.element          = element;
    }
    
    @Override
    protected void importElements() {
        this.importComponents();
        this.importInterfaces();
    }
    
    /**
     * Method responsible for importing the Components.
     */
    private void importComponents() {
        NodeList components = this.element.getElementsByTagName("component");
        for (int i = 0; i < components.getLength(); i++)
            this.componentDiagram.addComponent(new ComponentUML((Element) components.item(i)));
    }
    
    /**
     * Method responsible for importing the Interfaces.
     */
    private void importInterfaces() {
        NodeList interfaces = this.element.getElementsByTagName("interface");
        for (int i = 0; i < interfaces.getLength(); i++)
            this.componentDiagram.addInterface(new InterfaceUML((Element) interfaces.item(i)));
    }
    
    @Override
    protected void importAssociations() {
        this.importComunications();
    }
    
    /**
     * Method responsible for importing the Comunications.
     */
    private void importComunications() {
        NodeList comunications = this.element.getElementsByTagName("comunication");
        for (int i = 0; i < comunications.getLength(); i++) {
            Element         current      = (Element) comunications.item(i);
            ComunicationUML comunication = new ComunicationUML((ComponentUML) this.diagram.getElement(current.getAttribute("component")),
                                                               (InterfaceUML) this.diagram.getElement(current.getAttribute("interface")),
                                                               current.getAttribute("category"));
                            comunication.setId(current.getAttribute("id"));
                            super.addPoints(current, comunication);
            this.componentDiagram.addComunication(comunication);
        }
    }
}
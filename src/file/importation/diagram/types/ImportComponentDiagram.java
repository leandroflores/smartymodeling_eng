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
 * <p>Class of File <b>ImportComponentDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Component Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-20
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.ComponentDiagram
 */
public class ImportComponentDiagram extends ImportDiagram {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportComponentDiagram(Project project, Element element) {
        this.diagram = new ComponentDiagram(project, element);
        this.element = element;
    }
    
    @Override
    protected void importElements() {
        importComponents();
        importInterfaces();
    }
    
    /**
     * Method responsible for importing the UML Components.
     */
    private void importComponents() {
        NodeList list = element.getElementsByTagName("component");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addComponent(new ComponentUML((Element) list.item(i), getDiagram()));
    }
    
    /**
     * Method responsible for importing the UML Interfaces.
     */
    private void importInterfaces() {
        NodeList list = element.getElementsByTagName("interface");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addInterface(new InterfaceUML((Element) list.item(i), getDiagram()));
    }
    
    @Override
    protected void importAssociations() {
        importComunications();
    }
    
    /**
     * Method responsible for importing the Comunications.
     */
    private void importComunications() {
        NodeList list = element.getElementsByTagName("comunication");
        for (int i = 0; i < list.getLength(); i++) {
            Element         current      = (Element) list.item(i);
            ComunicationUML comunication = new ComunicationUML(current);
                            comunication.setSource(getElement(current.getAttribute("component")));
                            comunication.setTarget(getElement(current.getAttribute("interface")));
                    addPoints(current, comunication);
            getDiagram().addComunication(comunication);
        }
    }
    
    @Override
    protected ComponentDiagram getDiagram() {
        return (ComponentDiagram) diagram;
    }
}
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
        this.importComponents();
        this.importInterfaces();
    }
    
    /**
     * Method responsible for importing the UML Components.
     */
    private void importComponents() {
        NodeList list = this.element.getElementsByTagName("component");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addComponent(new ComponentUML((Element) list.item(i), this.getDiagram()));
    }
    
    /**
     * Method responsible for importing the UML Interfaces.
     */
    private void importInterfaces() {
        NodeList list = this.element.getElementsByTagName("interface");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addInterface(new InterfaceUML((Element) list.item(i), this.getDiagram()));
    }
    
    @Override
    protected void importAssociations() {
        this.importComunications();
    }
    
    /**
     * Method responsible for importing the Comunications.
     */
    private void importComunications() {
        NodeList list = this.element.getElementsByTagName("comunication");
        for (int i = 0; i < list.getLength(); i++) {
            Element         current      = (Element) list.item(i);
            ComunicationUML comunication = new ComunicationUML(current);
                            comunication.setSource(this.getElement(current.getAttribute("source")));
                            comunication.setTarget(this.getElement(current.getAttribute("target")));
                    super.addPoints(current, comunication);
            this.getDiagram().addComunication(comunication);
        }
    }
    
    @Override
    protected ComponentDiagram getDiagram() {
        return (ComponentDiagram) this.diagram;
    }
}
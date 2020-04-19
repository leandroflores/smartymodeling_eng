package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Project;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import model.structural.diagram.usecase.base.ActorUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportSequenceDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Sequence Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-01
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.SequenceDiagram
 */
public class ImportSequenceDiagram extends ImportDiagram {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportSequenceDiagram(Project project, Element element) {
        this.diagram = new SequenceDiagram(project, element);
        this.element = element;
    }
    
    @Override
    protected void importElements() {
        this.importLifelines();
        this.importInstances();
    }
    
    /**
     * Method responsible for importing the UML Lifelines.
     */
    private void importLifelines() {
        NodeList list = this.element.getElementsByTagName("lifeline");
        for (int i = 0; i < list.getLength(); i++) {
            Element     item     = (Element) list.item(i);
            LifelineUML lifeline = new LifelineUML(item);
            ActorUML    actor    = (ActorUML) this.getProject().objects.get(item.getAttribute("actor"));
                        lifeline.setActor(actor);
            this.getDiagram().addLifeline(lifeline);
        }
    }
    
    /**
     * Method responsible for importing the UML Instances.
     */
    private void importInstances() {
        NodeList list = this.element.getElementsByTagName("instance");
        for (int i = 0; i < list.getLength(); i++) {
            Element     item     = (Element) list.item(i);
            InstanceUML instance = new InstanceUML(item);
            ClassUML    class_   = (ClassUML) this.getProject().objects.get(item.getAttribute("class"));
                        instance.setClassUML(class_);
            this.getDiagram().addInstance(instance);
        }
    }
    
    @Override
    protected void importAssociations() {
        this.importMessages();
    }
    
    /**
     * Method responsible for importing the UML Messages.
     */
    private void importMessages() {
        NodeList list = this.element.getElementsByTagName("message");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current = (Element)   list.item(i);
            MethodUML  method  = (MethodUML) this.getProject().objects.get(current.getAttribute("method"));
            MessageUML message = new MessageUML(current);
                       message.setMethod(method);
                super.addPoints(current, message);
            this.getDiagram().addMessage(message);
        }
    }
    
    @Override
    protected SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.diagram;
    }
}
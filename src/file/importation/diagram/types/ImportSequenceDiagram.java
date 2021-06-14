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
 * <p>Class of File <b>ImportSequenceDiagram</b>.</p>
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
        importLifelines();
        importInstances();
    }
    
    /**
     * Method responsible for importing the UML Lifelines.
     */
    private void importLifelines() {
        NodeList list = element.getElementsByTagName("lifeline");
        for (int i = 0; i < list.getLength(); i++) {
            Element     item     = (Element) list.item(i);
            LifelineUML lifeline = new LifelineUML(item, getDiagram());
            ActorUML    actor    = (ActorUML) getProject().objects.get(item.getAttribute("actor"));
                        lifeline.setActor(actor);
            getDiagram().addLifeline(lifeline);
        }
    }
    
    /**
     * Method responsible for importing the UML Instances.
     */
    private void importInstances() {
        NodeList list = element.getElementsByTagName("instance");
        for (int i = 0; i < list.getLength(); i++) {
            Element     item     = (Element) list.item(i);
            InstanceUML instance = new InstanceUML(getDiagram());
            ClassUML    class_   = (ClassUML) getProject().objects.get(item.getAttribute("class"));
                        instance.setClassUML(class_);
            getDiagram().addInstance(instance);
        }
    }
    
    @Override
    protected void importAssociations() {
        importMessages();
    }
    
    /**
     * Method responsible for importing the UML Messages.
     */
    private void importMessages() {
        NodeList list = element.getElementsByTagName("message");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current = (Element)   list.item(i);
            MethodUML  method  = (MethodUML) getProject().objects.get(current.getAttribute("method"));
            MessageUML message = new MessageUML(current);
                       message.setMethod(method);
                addPoints(current, message);
            getDiagram().addMessage(message);
        }
    }
    
    @Override
    protected SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
}
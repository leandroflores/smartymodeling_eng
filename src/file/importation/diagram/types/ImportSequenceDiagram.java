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
 * @since  01/10/2019
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.SequenceDiagram
 */
public class ImportSequenceDiagram extends ImportDiagram {
    private final SequenceDiagram sequenceDiagram;
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportSequenceDiagram(Project project, Element element) {
        this.sequenceDiagram = new SequenceDiagram(project, element);
        this.project         = project;
        this.diagram         = this.sequenceDiagram;
        this.element         = element;
    }
    
    @Override
    protected void importElements() {
        this.importLifelines();
        this.importInstances();
    }
    
    /**
     * Method responsible for importing the Lifelines.
     */
    private void importLifelines() {
        NodeList lifelines = this.element.getElementsByTagName("lifeline");
        for (int i = 0; i < lifelines.getLength(); i++) {
            Element     item     = (Element) lifelines.item(i);
            LifelineUML lifeline = new LifelineUML(item);
            ActorUML    actorUML = (ActorUML) this.project.objects.get(item.getAttribute("actor"));
                        lifeline.setActor(actorUML);
            this.sequenceDiagram.addLifeline(lifeline);
        }
    }
    
    /**
     * Method responsible for importing the Instances.
     */
    private void importInstances() {
        NodeList instances = this.element.getElementsByTagName("instance");
        for (int i = 0; i < instances.getLength(); i++) {
            Element     item     = (Element) instances.item(i);
            InstanceUML instance = new InstanceUML(item);
            ClassUML    classUML = (ClassUML) this.project.objects.get(item.getAttribute("class"));
                        instance.setClassUML(classUML);
            this.sequenceDiagram.addInstance(instance);
        }
    }
    
    @Override
    protected void importAssociations() {
        this.importMessages();
    }
    
    /**
     * Method responsible for importing the Messages.
     */
    private void importMessages() {
        NodeList messages = this.element.getElementsByTagName("message");
        for (int i = 0; i < messages.getLength(); i++) {
            Element    current = (Element)   messages.item(i);
            MethodUML  method  = (MethodUML) this.project.objects.get(current.getAttribute("method"));
            MessageUML message = new MessageUML(this.diagram.getElement(current.getAttribute("source")), 
                                                this.diagram.getElement(current.getAttribute("target")));
                       message.setId(current.getAttribute("id"));
                       message.setCategory(current.getAttribute("category"));
                       message.setMethod(method);
                       message.setSequence(Integer.parseInt(current.getAttribute("sequence")));
                       super.addPoints(current, message);
            this.sequenceDiagram.addMessage(message);
        }
    }
}
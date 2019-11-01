package model.structural.diagram;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import model.structural.diagram.usecase.base.ActorUML;

/**
 * <p>Class of Model <b>SequenceDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Sequence Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.sequence.base.LifelineUML
 * @see    model.structural.diagram.sequence.base.InstanceUML
 */
public final class SequenceDiagram extends Diagram {
    private HashMap<String, LifelineUML> lifelines;
    private HashMap<String, InstanceUML> instances;
    private HashMap<String, Association> messages;

    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public SequenceDiagram(Project project) {
        super(project);
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public SequenceDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }
    
    @Override
    public void init() {
        this.type      = "Sequence";
        this.lifelines = new HashMap<>();
        this.instances = new HashMap<>();
        this.messages  = new HashMap<>();
    }
    
    /**
     * Method responsible for returning the Next Lifeline Id.
     * @return Next Lifeline Id.
     */
    private String nextLifelineId() {
        return this.nextId("LIFELINE#");
    }
    
    /**
     * Method responsible for adding a Lifeline UML.
     * @param lifeline Lifeline UML.
     */
    public void addLifeline(LifelineUML lifeline) {
        lifeline.setId(this.nextLifelineId());
        if (this.lifelines.get(lifeline.getId()) == null) {
            this.lifelines.put(lifeline.getId(), lifeline);
            this.addElement(lifeline);
        }
    }
    
    /**
     * Method responsible for reseting the Lifeline by Element.
     * @param element Element.
     */
    public void resetLifeline(Element element) {
        if (element instanceof ActorUML) {
            for (LifelineUML lifeline : this.getLifelinesList()) {
                if ((lifeline.getActor() != null) && (lifeline.getActor().equals(element)))
                    lifeline.setActor(null);
            }
        }
    }
    
    /**
     * Method responsible for removing a Lifeline UML.
     * @param lifeline Lifeline UML.
     */
    public void removeLifeline(LifelineUML lifeline) {
        this.removeAssociations(lifeline);
        this.removeElement(lifeline);
        this.lifelines.remove(lifeline.getId());
    }
    
    /**
     * Method responsible for returning the Lifelines List.
     * @return Lifelines List.
     */
    public List<LifelineUML> getLifelinesList() {
        return new ArrayList<>(this.lifelines.values());
    }
    
    /**
     * Method responsible for returning the Next Instance Id.
     * @return Next Instance Id.
     */
    private String nextInstanceId() {
        return this.nextId("INSTANCE#");
    }
    
    /**
     * Method responsible for adding a Instance UML.
     * @param instance Instance UML.
     */
    public void addInstance(InstanceUML instance) {
        instance.setId(this.nextInstanceId());
        if (this.instances.get(instance.getId()) == null) {
            this.instances.put(instance.getId(), instance);
            this.addElement(instance);
        }
    }
    
    /**
     * Method responsible for reseting the Instance by Element.
     * @param element Element.
     */
    public void resetInstance(Element element) {
        if (element instanceof ClassUML) {
            for (InstanceUML instance : this.getInstancesList()) {
                if ((instance.getClassUML() != null) && (instance.getClassUML().equals(element)))
                    instance.setClassUML(null);
            }
        }
    }
    
    /**
     * Method responsible for removing a Instance UML.
     * @param instance Instance UML.
     */
    public void removeInstance(InstanceUML instance) {
        this.removeAssociations(instance);
        this.removeElement(instance);
        this.instances.remove(instance.getId());
    }
    
    /**
     * Method responsible for returning the Instances List.
     * @return Instances List.
     */
    public List<InstanceUML> getInstancesList() {
        return new ArrayList<>(this.instances.values());
    }
    
    /**
     * Method responsible for adding a Message UML.
     * @param message Message UML.
     */
    public void addMessage(MessageUML message) {
        message.setSequence(this.nextSequence());
        if (this.messages.get(message.getId()) == null) {
            this.messages.put(message.getId(), message);
            this.addAssociation(message);
            this.updateSequence();
        }
    }
    
    /**
     * Method responsible for reseting the Message by Element.
     * @param element Element.
     */
    public void resetMessage(Element element) {
        if (element instanceof MethodUML) {
            for (MessageUML message : this.getMessageList()) {
                if (message.getMethod().equals(element))
                    message.setMethod(null);
            }
        }
    }
    
    /**
     * Method responsible for removing a Message UML.
     * @param message Message UML.
     */
    public void removeMessage(MessageUML message) {
        super.removeAssociation(message);
        this.messages.remove(message.getId());
        this.updateSequence();
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.messages);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof MessageUML)
            this.removeMessage((MessageUML) association);
        else
            super.removeAssociation(association);
    }
    
    /**
     * Method responsible for returning the Next Sequence.
     * @return Next Sequence.
     */
    public Integer nextSequence() {
        return this.getMessageList().size() + 1;
    }
    
    /**
     * Method responsible for updating the Sequence.
     */
    public void updateSequence() {
        List<MessageUML> list = this.getMessageList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).setSequence(i + 1);
    }
    
    /**
     * Method responsible for returning the Message List.
     * @return Message List.
     */
    public List<MessageUML> getMessageList() {
        ArrayList<MessageUML>  message = new ArrayList<>();
        ArrayList<Association> list    = new ArrayList<>(this.messages.values());
        for (Association association : list)
            message.add((MessageUML) association);
               message.sort(this.getMessageComparator());
        return message;
    }
    
    /**
     * Method responsible for returning the Message Comparator.
     * @return Message Comparator.
     */
    private Comparator<MessageUML> getMessageComparator() {
        return new Comparator<MessageUML>() {
            @Override
            public int compare(MessageUML messageA, MessageUML messageB) {
                if (messageA.getSequence() <= messageB.getSequence())
                    return -1;
                return 1;
            }
        };
    }
    
    @Override
    public String getIcon() {
        return "diagram/sequence";
    }

    @Override
    public Diagram getClone() {
        try {
            SequenceDiagram diagram = (SequenceDiagram) super.clone();
                            diagram.setElements(new HashMap<>(this.elements));
                            diagram.setAssociations(new HashMap<>(this.associations));
                            diagram.setVariabilities(new HashMap<>(this.variabilities));
            return          diagram;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error");
            return null;
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
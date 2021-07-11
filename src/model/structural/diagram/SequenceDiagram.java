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
 * @since  2019-07-25
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
        init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public SequenceDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        init();
    }
    
    @Override
    public void init() {
        type      = "Sequence";
        lifelines = new HashMap<>();
        instances = new HashMap<>();
        messages  = new HashMap<>();
    }
    
    /**
     * Method responsible for returning the Min Height.
     * @return Min Height.
     */
    public Integer getMinHeigth() {
        return Math.max(250, 140 + (messages.size() * 30));
    }
    
    /**
     * Method responsible for returning the Max Height.
     * @return Max Height.
     */
    public Integer getMaxHeight() {
        return Math.max(getMaxLifelinesHeight(), getMaxInstancesHeight());
    }
    
    /**
     * Method responsible for returning the Y Default.
     * @return Y Default.
     */
    public Integer getYDefault() {
        if (!lifelines.isEmpty())
            return getLifelinesList().get(0).getY();
        else if (!instances.isEmpty())
            return getInstancesList().get(0).getY();
        return 20;
    }
    
    /**
     * Method responsible for returning the Height Default.
     * @return Height Default.
     */
    public Integer getHeightDefault() {
        if (!lifelines.isEmpty())
            return getLifelinesList().get(0).getHeight();
        else if (!instances.isEmpty())
            return getInstancesList().get(0).getHeight();
        return 350;
    }
    
    /**
     * Method responsible for updating the Height.
     * @param heigth Height.
     */
    public void updateHeight(Integer heigth) {
        updateLifelinesHeight(heigth);
        updateInstancesHeight(heigth);
    }
    
    /**
     * Method responsible for updating the Y.
     * @param y Y Coordinate.
     */
    public void updateY(Integer y) {
        updateLifelinesY(y);
        updateInstancesY(y);
    }
    
    /**
     * Method responsible for adding a Lifeline UML.
     * @param lifeline Lifeline UML.
     */
    public void addLifeline(LifelineUML lifeline) {
        lifeline.setId(nextId(lifeline));
        if (lifelines.get(lifeline.getId()) == null) {
            lifelines.put(lifeline.getId(), lifeline);
            addElement(lifeline);
        }
    }
    
    /**
     * Method responsible for reseting the Lifeline by Element.
     * @param element Element.
     */
    public void resetLifeline(Element element) {
        if (element instanceof ActorUML) {
            for (LifelineUML lifeline : getLifelinesList()) {
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
        removeAssociations(lifeline);
        removeElement(lifeline);
        lifelines.remove(lifeline.getId());
    }
    
    /**
     * Method responsible for returning the Max Lifelines Height.
     * @return Max Lifelines Height.
     */
    public Integer getMaxLifelinesHeight() {
        Integer max = Math.max(350, getMinHeigth());
        for (LifelineUML lifeline : getLifelinesList())
                max = Math.max(lifeline.getHeight(), max);
        return  max;
    }
    
    /**
     * Method responsible for updating the Lifelines Height.
     * @param height Height.
     */
    public void updateLifelinesHeight(Integer height) {
        for (LifelineUML lifeline : getLifelinesList())
            lifeline.setHeight(height);
    }
    
    /**
     * Method responsible for updating the Lifelines Y.
     * @param y Y Coordinate.
     */
    public void updateLifelinesY(Integer y) {
        for (LifelineUML lifeline : getLifelinesList())
            lifeline.getPosition().y = y;
    }
    
    /**
     * Method responsible for returning the Lifelines List.
     * @return Lifelines List.
     */
    public List<LifelineUML> getLifelinesList() {
        return new ArrayList<>(lifelines.values());
    }
    
    /**
     * Method responsible for adding a Instance UML.
     * @param instance Instance UML.
     */
    public void addInstance(InstanceUML instance) {
        instance.setId(nextId(instance));
        if (instances.get(instance.getId()) == null) {
            instances.put(instance.getId(), instance);
            addElement(instance);
        }
    }
    
    /**
     * Method responsible for reseting the Instance by Element.
     * @param element Element.
     */
    public void resetInstance(Element element) {
        if (element instanceof ClassUML) {
            for (InstanceUML instance : getInstancesList()) {
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
        removeAssociations(instance);
        removeElement(instance);
        instances.remove(instance.getId());
    }
    
    /**
     * Method responsible for returning the Max Instances Height.
     * @return Max Instances Height.
     */
    public Integer getMaxInstancesHeight() {
        Integer max = Math.max(350, getMinHeigth());
        for (InstanceUML instance : getInstancesList())
                max = Math.max(instance.getHeight(), max);
        return  max;
    }
    
    /**
     * Method responsible for updating the Instances Height.
     * @param height Height.
     */
    public void updateInstancesHeight(Integer height) {
        for (InstanceUML instance : getInstancesList())
            instance.setHeight(height);
    }
    
    /**
     * Method responsible for updating the Instances Y.
     * @param y Y Coordinate.
     */
    public void updateInstancesY(Integer y) {
        for (InstanceUML instance : getInstancesList())
            instance.getPosition().y = y;
    }
    
    /**
     * Method responsible for returning the Instances List.
     * @return Instances List.
     */
    public List<InstanceUML> getInstancesList() {
        return new ArrayList<>(instances.values());
    }
    
    /**
     * Method responsible for adding a Message UML.
     * @param message Message UML.
     */
    public void addMessage(MessageUML message) {
        message.setId(nextId(message));
        message.setSequence(nextSequence());
        if (messages.get(message.getId()) == null) {
            messages.put(message.getId(), message);
            addAssociation(message);
            updateHeight(getMinHeigth());
            updateSequence();
        }
    }
    
    /**
     * Method responsible for changing the Message Names.
     * @param method Method changed.
     */
    public void changeNames(MethodUML method) {
        for (MessageUML message : getMessageList())
            message.changeName(method);
    }
    
    /**
     * Method responsible for updating the Next Messages.
     * @param message Message to be removed.
     */
    public void updateNextMessages(MessageUML message) {
        for (MessageUML current : getMessageList()) {
            if (current.getSequence() > message.getSequence())
                current.dy(-40);
        }
    }
    
    /**
     * Method responsible for reseting the Message by Element.
     * @param element Element.
     */
    public void resetMessage(Element element) {
        if (element instanceof MethodUML) {
            for (MessageUML message : getMessageList()) {
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
        updateNextMessages(message);
        messages.remove(message.getId());
        updateSequence();
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        removeAssociation(element, messages);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof MessageUML)
            removeMessage((MessageUML) association);
        else
            super.removeAssociation(association);
    }
    
    /**
     * Method responsible for returning the Next Sequence.
     * @return Next Sequence.
     */
    public Integer nextSequence() {
        return getMessageList().size() + 1;
    }
    
    /**
     * Method responsible for updating the Sequence.
     */
    public void updateSequence() {
        List<MessageUML> list = getMessageList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeSequence(i + 1);
    }
    
    /**
     * Method responsible for returning the Message List.
     * @return Message List.
     */
    public List<MessageUML> getMessageList() {
        ArrayList list = new ArrayList<>(messages.values());
                  list.sort(getMessageComparator());
        return    list;
    }
    
    /**
     * Method responsible for returning the Message Comparator.
     * @return Message Comparator.
     */
    private Comparator<MessageUML> getMessageComparator() {
        return new Comparator<MessageUML>() {
            @Override
            public int compare(MessageUML messageA, MessageUML messageB) {
                return messageA.getSequence().compareTo(messageB.getSequence());
            }
        };
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "sequence.png";
    }

    @Override
    public String getInstanceIcon() {
        return "icons/product/instance/instance-sequence.png";
    }
    
    @Override
    public Diagram getClone() {
        try {
            SequenceDiagram diagram = (SequenceDiagram) super.clone();
                            diagram.setElements(new HashMap<>(elements));
                            diagram.setAssociations(new HashMap<>(associations));
                            diagram.setVariabilities(new HashMap<>(variabilities));
            return          diagram;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }
}
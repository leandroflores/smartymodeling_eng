package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;

/**
 * <p>Class of Model <b>SequenceDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Sequence Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.sequence.base.LifelineUML
 */
public final class SequenceDiagram extends Diagram {
    private HashMap<String, LifelineUML> lifelines;
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
        this.messages  = new HashMap<>();
    }
    
    /**
     * Method responsible for returning the next Lifeline Id.
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
    public List<LifelineUML> getLifelines() {
        return new ArrayList<>(this.lifelines.values());
    }
    
    /**
     * Method responsible for adding a Message UML.
     * @param message Message UML.
     */
    public void addMessage(MessageUML message) {
        if (this.messages.get(message.getId()) == null) {
            this.messages.put(message.getId(), message);
            this.addAssociation(message);
        }
    }
    
    /**
     * Method responsible for removing a Message UML.
     * @param message Message UML.
     */
    public void removeMessage(MessageUML message) {
        this.removeAssociation(message);
        this.associations.remove(message.getId());
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.messages);
    }
    
    /**
     * Method responsible for removing the Association by Element.
     * @param element Element.
     * @param map Associations Map.
     */
    private void removeAssociation(Element element, Map<String, Association> map) {
        for (Association association : map.values()) {
            if (association.getSource().equals(element)
             || association.getTarget().equals(element))
                this.removeAssociation(association);
        }
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
package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import model.structural.diagram.activity.base.association.FlowUML;

/**
 * <p>Class of Model <b>ActivityDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Activity Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-17
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.activity.base.ActivityUML
 * @see    model.structural.diagram.activity.base.DecisionUML
 * @see    model.structural.diagram.activity.base.FinalUML
 * @see    model.structural.diagram.activity.base.InitialUML
 * @see    model.structural.diagram.activity.base.JoinUML
 */
public final class ActivityDiagram extends Diagram {
    private HashMap<String, ActivityUML> activities;
    private HashMap<String, DecisionUML> decisions;
    private HashMap<String, InitialUML>  initials;
    private HashMap<String, FinalUML>    finals;
    private HashMap<String, Association> flows;

    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ActivityDiagram(Project project) {
        super(project);
        init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ActivityDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        init();
    }
    
    @Override
    public void init() {
        type       = "Activity";
        activities = new HashMap<>();
        decisions  = new HashMap<>();
        initials   = new HashMap<>();
        finals     = new HashMap<>();
        flows      = new HashMap<>();
    }
    
    /**
     * Method responsible for adding a Activity UML.
     * @param activity Activity UML.
     */
    public void addActivity(ActivityUML activity) {
        activity.setId(nextId(activity));
        if (activities.get(activity.getId()) == null) {
            activities.put(activity.getId(), activity);
            addElement(activity);
        }
    }
    
    /**
     * Method responsible for removing a Activity UML.
     * @param activity Activity UML.
     */
    public void removeActivity(ActivityUML activity) {
        removeAssociations(activity);
        removeElement(activity);
        activities.remove(activity.getId());
    }
    
    /**
     * Method responsible for returning the Activities List.
     * @return Activities List.
     */
    public List<ActivityUML> getActivitiesList() {
        return new ArrayList<>(activities.values());
    }
    
    /**
     * Method responsible for adding a Decision UML.
     * @param decision Decision UML.
     */
    public void addDecision(DecisionUML decision) {
        decision.setId(nextId(decision));
        if (decisions.get(decision.getId()) == null) {
            decisions.put(decision.getId(), decision);
            addElement(decision);
        }
    }
    
    /**
     * Method responsible for removing a Decision UML.
     * @param decision Decision UML.
     */
    public void removeDecision(DecisionUML decision) {
        removeAssociations(decision);
        removeElement(decision);
        decisions.remove(decision.getId());
    }
    
    /**
     * Method responsible for returning the Decisions List.
     * @return Decisions List.
     */
    public List<DecisionUML> getDecisionsList() {
        return new ArrayList<>(decisions.values());
    }
    
    /**
     * Method responsible for adding a Initial UML.
     * @param initial Initial UML.
     */
    public void addInitial(InitialUML initial) {
        initial.setId(nextId(initial));
        if (initials.get(initial.getId()) == null) {
            initials.put(initial.getId(), initial);
            addElement(initial);
        }
    }
    
    /**
     * Method responsible for removing a Initial UML.
     * @param initial Initial UML.
     */
    public void removeInitial(InitialUML initial) {
        removeAssociations(initial);
        removeElement(initial);
        initials.remove(initial.getId());
    }
    
    /**
     * Method responsible for adding a Final UML.
     * @param final_ Final UML.
     */
    public void addFinal(FinalUML final_) {
        final_.setId(nextId(final_));
        if (finals.get(final_.getId()) == null) {
            finals.put(final_.getId(), final_);
            addElement(final_);
        }
    }
    
    /**
     * Method responsible for removing a Final UML.
     * @param final_ Final UML.
     */
    public void removeFinal(FinalUML final_) {
        removeAssociations(final_);
        removeElement(final_);
        finals.remove(final_.getId());
    }
    
    /**
     * Method responsible for adding a Flow UML.
     * @param flow Flow UML.
     */
    public void addFlow(FlowUML flow) {
        flow.setId(nextId(flow));
        if (flows.get(flow.getId()) == null) {
            flows.put(flow.getId(), flow);
            addAssociation(flow);
        }
    }
    
    /**
     * Method responsible for removing a Flow UML.
     * @param flow Flow UML.
     */
    public void removeFlow(FlowUML flow) {
        removeAssociation(flow);
        flows.remove(flow.getId());
    }
    
    /**
     * Method responsible for returning the Flows List.
     * @return Flows List.
     */
    public List<FlowUML> getFlowsList() {
        ArrayList<FlowUML> list = new ArrayList<>();
        for (Association association : flows.values())
               list.add((FlowUML) association);
        return list;
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        removeAssociation(element, flows);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof FlowUML)
            removeFlow((FlowUML) association);
        else
            super.removeAssociation(association);
    }
    
    @Override
    public List<Element> getTreeElementsList() {
        List<Element> filter  = new ArrayList<>();
        for (Element  element : getElementsList()) {
            if (element.getType().equals("activity"))
                filter.add(element);
        }
        return  filter;
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "activity.png";
    }

    @Override
    public String getInstanceIcon() {
        return "icons/product/instance/instance-activity.png";
    }
    
    @Override
    public Diagram getClone() {
        try {
            ActivityDiagram diagram = (ActivityDiagram) super.clone();
                            diagram.setElements(new HashMap<>(elements));
                            diagram.setAssociations(new HashMap<>(associations));
                            diagram.setVariabilities(new HashMap<>(variabilities));
            return          diagram;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error");
            return null;
        }
    }
}
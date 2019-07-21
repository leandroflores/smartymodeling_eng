package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import model.structural.diagram.activity.base.JoinUML;
import model.structural.diagram.activity.base.association.FlowUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportActivityDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Activity Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  18/07/2019
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.ActivityDiagram
 */
public class ImportActivityDiagram extends ImportDiagram {
    private final ActivityDiagram activityDiagram;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportActivityDiagram(Project project, Element element) {
        this.activityDiagram = new ActivityDiagram(project, element);
        this.diagram         = this.activityDiagram;
        this.element         = element;
    }
    
    @Override
    public Diagram importDiagram() {
                this.importActivities();
                this.importDecisions();
                this.importInitials();
                this.importFinals();
                this.importJoins();
                this.importFlows();
               super.importRelationships();
               super.importVariabilities();
        return  this.diagram;
    }
    
    /**
     * Method responsible for importing the Activities.
     */
    private void importActivities() {
        NodeList activities = this.element.getElementsByTagName("activity");
        for (int i = 0; i < activities.getLength(); i++)
            this.activityDiagram.addActivity(new ActivityUML((Element) activities.item(i)));
    }
    
    /**
     * Method responsible for importing the Decisions.
     */
    private void importDecisions() {
        NodeList decisions = this.element.getElementsByTagName("decision");
        for (int i = 0; i < decisions.getLength(); i++)
            this.activityDiagram.addDecision(new DecisionUML((Element) decisions.item(i)));
    }
    
    /**
     * Method responsible for importing the Initials.
     */
    private void importInitials() {
        NodeList initials  = this.element.getElementsByTagName("initial");
        for (int i = 0; i < initials.getLength(); i++)
            this.activityDiagram.addInitial(new InitialUML((Element) initials.item(i)));
    }
    
    /**
     * Method responsible for importing the Finals.
     */
    private void importFinals() {
        NodeList finals  = this.element.getElementsByTagName("final");
        for (int i = 0; i < finals.getLength(); i++)
            this.activityDiagram.addFinal(new FinalUML((Element) finals.item(i)));
    }
    
    /**
     * Method responsible for importing the Joins.
     */
    private void importJoins() {
        NodeList joins  = this.element.getElementsByTagName("join");
        for (int i = 0; i < joins.getLength(); i++)
            this.activityDiagram.addJoin(new JoinUML((Element) joins.item(i)));
    }
    
    /**
     * Method responsible for importing the Flows.
     */
    private void importFlows() {
        NodeList flows = this.element.getElementsByTagName("flow");
        for (int i = 0; i < flows.getLength(); i++) {
            Element current = (Element) flows.item(i);
            FlowUML flow    = new FlowUML(this.diagram.getElement(current.getAttribute("source")), this.diagram.getElement(current.getAttribute("target")));
            this.activityDiagram.addFlow(flow);
        }
    }
}
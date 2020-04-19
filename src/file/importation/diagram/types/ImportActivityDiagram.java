package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
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
 * @since  2019-07-18
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.ActivityDiagram
 */
public class ImportActivityDiagram extends ImportDiagram {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportActivityDiagram(Project project, Element element) {
        this.diagram = new ActivityDiagram(project, element);
        this.element = element;
    }
    
    @Override
    protected void importElements() {
        this.importActivities();
        this.importDecisions();
        this.importInitials();
        this.importFinals();
        this.importJoins();
    }
    
    /**
     * Method responsible for importing the UML Activities.
     */
    private void importActivities() {
        NodeList list = this.element.getElementsByTagName("activity");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addActivity(new ActivityUML((Element) list.item(i)));
    }
    
    /**
     * Method responsible for importing the UML Decisions.
     */
    private void importDecisions() {
        NodeList list = this.element.getElementsByTagName("decision");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addDecision(new DecisionUML((Element) list.item(i)));
    }
    
    /**
     * Method responsible for importing the Initial Nodes.
     */
    private void importInitials() {
        NodeList list = this.element.getElementsByTagName("initial");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addInitial(new InitialUML((Element) list.item(i)));
    }
    
    /**
     * Method responsible for importing the Final Nodes.
     */
    private void importFinals() {
        NodeList list = this.element.getElementsByTagName("final");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addFinal(new FinalUML((Element) list.item(i)));
    }
    
    /**
     * Method responsible for importing the Joins.
     */
    private void importJoins() {
        NodeList list = this.element.getElementsByTagName("join");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addJoin(new JoinUML((Element) list.item(i)));
    }
    
    @Override
    protected void importAssociations() {
        this.importFlows();
    }
    
    /**
     * Method responsible for importing the UML Flows.
     */
    private void importFlows() {
        NodeList list = this.element.getElementsByTagName("flow");
        for (int i = 0; i < list.getLength(); i++) {
            Element node = (Element) list.item(i);
            FlowUML flow = new FlowUML(node);
                    flow.setSource(this.getElement(node.getAttribute("source")));
                    flow.setTarget(this.getElement(node.getAttribute("target")));
                super.addPoints(node, flow);
            this.getDiagram().addFlow(flow);
        }
    }
    
    @Override
    protected ActivityDiagram getDiagram() {
        return (ActivityDiagram) this.diagram;
    }
}
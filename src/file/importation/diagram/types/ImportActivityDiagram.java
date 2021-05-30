package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Project;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.FinalUML;
import model.structural.diagram.activity.base.InitialUML;
import model.structural.diagram.activity.base.association.FlowUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of File <b>ImportActivityDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Activity Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-18
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.ActivityDiagram
 */
public class ImportActivityDiagram extends ImportDiagram {
    
    /**
     * Default constructor method of Class.
     * @param project_ Project.
     * @param element_ W3C Element.
     */
    public ImportActivityDiagram(Project project_, Element element_) {
        diagram = new ActivityDiagram(project_, element_);
        element = element_;
    }
    
    @Override
    protected void importElements() {
        importActivities();
        importDecisions();
        importInitials();
        importFinals();
    }
    
    /**
     * Method responsible for importing the UML Activities.
     */
    private void importActivities() {
        NodeList list = element.getElementsByTagName("activity");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addActivity(new ActivityUML((Element) list.item(i), getDiagram()));
    }
    
    /**
     * Method responsible for importing the UML Decisions.
     */
    private void importDecisions() {
        NodeList list = element.getElementsByTagName("decision");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addDecision(new DecisionUML((Element) list.item(i), getDiagram()));
    }
    
    /**
     * Method responsible for importing the Initial Nodes.
     */
    private void importInitials() {
        NodeList list = element.getElementsByTagName("initial");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addInitial(new InitialUML((Element) list.item(i), getDiagram()));
    }
    
    /**
     * Method responsible for importing the Final Nodes.
     */
    private void importFinals() {
        NodeList list = element.getElementsByTagName("final");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addFinal(new FinalUML((Element) list.item(i), getDiagram()));
    }
    
    @Override
    protected void importAssociations() {
        importFlows();
    }
    
    /**
     * Method responsible for importing the UML Flows.
     */
    private void importFlows() {
        NodeList list = element.getElementsByTagName("flow");
        for (int i = 0; i < list.getLength(); i++) {
            Element node = (Element) list.item(i);
            FlowUML flow = new FlowUML(node);
                    flow.setSource(getElement(node.getAttribute("source")));
                    flow.setTarget(getElement(node.getAttribute("target")));
                addPoints(node, flow);
            getDiagram().addFlow(flow);
        }
    }
    
    @Override
    protected ActivityDiagram getDiagram() {
        return (ActivityDiagram) diagram;
    }
}
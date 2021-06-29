package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelActivityOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelActivityDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelActivityOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Activity Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-04-09
 * @see    controller.view.panel.operation.types.ControllerPanelActivityOperation
 * @see    view.panel.operation.PanelOperation
 * @see    view.panel.diagram.types.PanelActivityDiagram
 */
public final class PanelActivityOperation extends PanelOperation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Activity Diagram.
     */
    public PanelActivityOperation(PanelActivityDiagram panel) {
        super(panel);
        controller = new ControllerPanelActivityOperation(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        add(createButton("initial",  "", "New Initial",  "diagram/activity/initial.png"));
        add(createButton("activity", "", "New Activity", "diagram/activity/activity.png"));
        add(createButton("decision", "", "New Decision", "diagram/activity/decision.png"));
        add(createButton("final",    "", "New Final",    "diagram/activity/final.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {getAssociationImage("activity/flow"),
                          getAssociationImage("dependency"),
                          getAssociationImage("requires"),
                          getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        getClickButton().setBackground(getDefaultColor());
        getInitialButton().setBackground(getDefaultColor());
        getActivityButton().setBackground(getDefaultColor());
        getDecisionButton().setBackground(getDefaultColor());
        getFinalButton().setBackground(getDefaultColor());
    }
    
    @Override
    public PanelActivityDiagram getPanelDiagram() {
        return (PanelActivityDiagram) panel;
    }
    
    /**
     * Method responsible for returning the Initial Button.
     * @return Initial Button.
     */
    public JButton getInitialButton() {
        return getButton("initial");
    }
    
    /**
     * Method responsible for returning the Activity Button.
     * @return Activity Button.
     */
    public JButton getActivityButton() {
        return getButton("activity");
    }
    
    /**
     * Method responsible for returning the Decision Button.
     * @return Decision Button.
     */
    public JButton getDecisionButton() {
        return getButton("decision");
    }
    
    /**
     * Method responsible for returning the Final Button.
     * @return Final Button.
     */
    public JButton getFinalButton() {
        return getButton("final");
    }
}
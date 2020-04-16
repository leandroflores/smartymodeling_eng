package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelActivityOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelActivityDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelActivityOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Activity Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2019
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
        this.controller = new ControllerPanelActivityOperation(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        this.add(this.createButton("initialButton",  "", "New Initial",  "diagram/activity/initial.png"));
        this.add(this.createButton("activityButton", "", "New Activity", "diagram/activity/activity.png"));
        this.add(this.createButton("decisionButton", "", "New Decision", "diagram/activity/decision.png"));
        this.add(this.createButton("finalButton",    "", "New Final",    "diagram/activity/final.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {this.getAssociationImage("activity/flow"),
                          this.getAssociationImage("dependency"),
                          this.getAssociationImage("requires"),
                          this.getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getInitialButton().setBackground(this.getDefaultColor());
        this.getActivityButton().setBackground(this.getDefaultColor());
        this.getDecisionButton().setBackground(this.getDefaultColor());
        this.getFinalButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public PanelActivityDiagram getPanelDiagram() {
        return (PanelActivityDiagram) this.panelDiagram;
    }
    
    /**
     * Method responsible for returning the Initial Button.
     * @return Initial Button.
     */
    public JButton getInitialButton() {
        return this.getButton("initialButton");
    }
    
    /**
     * Method responsible for returning the Activity Button.
     * @return Activity Button.
     */
    public JButton getActivityButton() {
        return this.getButton("activityButton");
    }
    
    /**
     * Method responsible for returning the Decision Button.
     * @return Decision Button.
     */
    public JButton getDecisionButton() {
        return this.getButton("decisionButton");
    }
    
    /**
     * Method responsible for returning the Final Button.
     * @return Final Button.
     */
    public JButton getFinalButton() {
        return this.getButton("finalButton");
    }
}
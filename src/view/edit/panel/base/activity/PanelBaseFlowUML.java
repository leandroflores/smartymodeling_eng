package view.edit.panel.base.activity;

import controller.view.edit.panel.base.activity.ControllerPanelBaseFlowUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.association.FlowUML;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseFlowUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Flow UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/11/2019
 * @see    controller.view.edit.panel.base.activity.ControllerPanelBaseFlowUML
 * @see    model.structural.diagram.activity.base.association.FlowUML
 * @see    view.panel.Panel
 */
public final class PanelBaseFlowUML extends Panel {
    private final ViewMenu viewMenu;
    private final ActivityDiagram diagram;
    private final FlowUML flowUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Activity Diagram.
     * @param flow Flow UML.
     */
    public PanelBaseFlowUML(ViewMenu viewMenu, ActivityDiagram diagram, FlowUML flow) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.flowUML    = flow;
        this.controller = new ControllerPanelBaseFlowUML(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: "));
        this.add(this.createTextFieldNoEditable("sourceTextField", "", 25));
        
        this.add(this.createLabel("Target: "));
        this.add(this.createTextFieldNoEditable("targetTextField", "", 25));
        
        this.add(this.createLabel("Guard: "));
        this.add(this.createTextField("guardTextField",  this.flowUML.getGuard(), 25));
        
        this.add(this.createLabel("Action: "));
        this.add(this.createTextField("actionTextField", this.flowUML.getAction(), 25));
        
        this.add(this.createLabel("Weight: "));
        this.add(this.createTextField("weightTextField", this.flowUML.getWeight(), 25));
    }
    
    /**
    /**
     * Method responsible for setting the Flow Values.
     */
    public void setValues() {
        this.getSourceTextField().setText(this.flowUML.getSource().getName());
        this.getTargetTextField().setText(this.flowUML.getTarget().getName());
        this.getGuardTextField().setText(this.flowUML.getGuard());
        this.getActionTextField().setText(this.flowUML.getAction());
        this.getWeightTextField().setText(this.flowUML.getWeight());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Activity Diagram.
     * @return Activity Diagram.
     */
    public ActivityDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Flow UML.
     * @return Flow UML.
     */
    public FlowUML getFlowUML() {
        return this.flowUML;
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return this.getTextField("sourceTextField");
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return this.getTextField("targetTextField");
    }
    
    /**
     * Method responsible for returning the Guard Text Field.
     * @return Guard Text Field.
     */
    public JTextField getGuardTextField() {
        return this.getTextField("guardTextField");
    }
    
    /**
     * Method responsible for returning the Action Text Field.
     * @return Action Text Field.
     */
    public JTextField getActionTextField() {
        return this.getTextField("actionTextField");
    }
    
    /**
     * Method responsible for returning the Weight Text Field.
     * @return Weight Text Field.
     */
    public JTextField getWeightTextField() {
        return this.getTextField("weightTextField");
    }
}
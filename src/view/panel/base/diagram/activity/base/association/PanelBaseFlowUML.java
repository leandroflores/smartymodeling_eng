package view.panel.base.diagram.activity.base.association;

import controller.view.panel.base.diagram.activity.base.association.ControllerPanelBaseFlowUML;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.activity.base.association.FlowUML;
import view.panel.base.PanelBaseAssociation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseFlowUML</b>.</p>
 * <p>Class responsible for defining a <b>Flow UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-04
 * @see    controller.view.panel.base.diagram.activity.base.association.ControllerPanelBaseFlowUML
 * @see    model.structural.diagram.activity.base.association.FlowUML
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseFlowUML extends PanelBaseAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Activity Diagram.
     * @param flow Flow UML.
     */
    public PanelBaseFlowUML(ViewMenu view, ActivityDiagram diagram, FlowUML flow) {
        super(view, diagram, flow);
        this.controller = new ControllerPanelBaseFlowUML(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 2, 2, 5));
        super.setDefaultProperties();
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: "));
        this.add(this.createTextFieldNoEditable("sourceTextField", this.getAssociation().getSource().getName(), 25));
        
        this.add(this.createLabel("Target: "));
        this.add(this.createTextFieldNoEditable("targetTextField", this.getAssociation().getTarget().getName(), 25));
        
        this.add(this.createLabel("Guard: "));
        this.add(this.createTextField("guardTextField",  this.getAssociation().getGuard(), 25));
        
        this.add(this.createLabel("Action: "));
        this.add(this.createTextField("actionTextField", this.getAssociation().getAction(), 25));
        
        this.add(this.createLabel("Weight: "));
        this.add(this.createTextField("weightTextField", this.getAssociation().getWeight(), 25));
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
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) this.diagram;
    }
    
    @Override
    public FlowUML getAssociation() {
        return (FlowUML) this.association;
    }
}
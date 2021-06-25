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
        controller = new ControllerPanelBaseFlowUML(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        super.setDefaultProperties();
        setLayout(new GridLayout(5, 2, 2, 5));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Source: "));
        add(createTextFieldNoEditable("source", getAssociation().getSource().getName(), 25));
        
        add(createLabel("Target: "));
        add(createTextFieldNoEditable("target", getAssociation().getTarget().getName(), 25));
        
        add(createLabel("Guard: "));
        add(createTextField("guard",  getAssociation().getGuard(), 25));
        
        add(createLabel("Action: "));
        add(createTextField("action", getAssociation().getAction(), 25));
        
        add(createLabel("Weight: "));
        add(createTextField("weight", getAssociation().getWeight(), 25));
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return getTextField("source");
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return getTextField("target");
    }
    
    /**
     * Method responsible for returning the Guard Text Field.
     * @return Guard Text Field.
     */
    public JTextField getGuardTextField() {
        return getTextField("guard");
    }
    
    /**
     * Method responsible for returning the Action Text Field.
     * @return Action Text Field.
     */
    public JTextField getActionTextField() {
        return getTextField("action");
    }
    
    /**
     * Method responsible for returning the Weight Text Field.
     * @return Weight Text Field.
     */
    public JTextField getWeightTextField() {
        return getTextField("weight");
    }
    
    @Override
    public ActivityDiagram getDiagram() {
        return (ActivityDiagram) diagram;
    }
    
    @Override
    public FlowUML getAssociation() {
        return (FlowUML) association;
    }
}
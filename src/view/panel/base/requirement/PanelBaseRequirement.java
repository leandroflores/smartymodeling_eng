package view.panel.base.requirement;

import controller.view.panel.base.requirement.ControllerPanelBaseRequirement;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.requirement.ControllerRequirement;
import model.structural.base.requirement.Requirement;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseRequirement</b>.</p>
 * <p>Class responsible for defining a <b>Requirement Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.panel.base.requirement.ControllerPanelBaseRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseRequirement extends PanelBase {
    private final Requirement requirement;
    private final boolean traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     * @param traceability Traceability Flag.
     */
    public PanelBaseRequirement(ViewMenu view, Requirement requirement, boolean traceability) {
        super(view);
        this.requirement  = requirement;
        this.traceability = traceability;
        this.controller   = new ControllerPanelBaseRequirement(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridBagLayout());
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Id*: "), createConstraints(1, 1, 0, 0));
        add(createTextField("code", requirement.getCode(), 10),  createConstraints(4, 1, 1, 0));
        
        add(createLabel("Type*: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("type", new ControllerRequirement(project).getTargets(), 10, requirement.getType()), createConstraints(4, 1, 1, 1));
        
        add(createLabel("Name*: "), createConstraints(1, 1, 0, 2));
        add(createTextField("name", requirement.getName(), 15), createConstraints(4, 1, 1, 2));
        
        add(createLabel("Description*: "), createConstraints(1, 1, 0, 3));
        add(createTextField("description", requirement.getDescription(), 15), createConstraints(4, 1, 1, 3));

        add(createLabel("Priority*: "), createConstraints(1, 1, 0, 4));
        add(createComboBox("priority", new ControllerRequirement(project).getPriorityTypes(), 10, requirement.getPriority()), createConstraints(4, 1, 1, 4));
        
        add(createLabel("History*: "), createConstraints(1, 1, 0, 6));
        add(createTextField("history", requirement.getHistory(), 15), createConstraints(4, 1, 1, 6));
        
        add(createButton("traceability" ,"Traceability"), createConstraints(4, 1, 1, 7));
        getTraceabilityButton().setVisible(traceability);
    }
    
    /**
     * Method responsible for returning the Code Text Field.
     * @return Code Text Field.
     */
    public JTextField getCodeTextField() {
        return getTextField("code");
    }
    
    /**
     * Method responsible for returning the Type Combo Box.
     * @return Type Combo Box.
     */
    public JComboBox getTypeComboBox() {
        return getComboBox("type");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    public JTextField getDescriptionTextField() {
        return getTextField("description");
    }
    
    public JComboBox getPriorityComboBox() {
        return getComboBox("priority");
    }
    
    public JTextField getHistoryTextField() {
        return getTextField("history");
    }
    
    public JButton getTraceabilityButton(){
        return getButton("traceability");
    }
    
    /**
     * Method responsible for return the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return requirement;
    }
}
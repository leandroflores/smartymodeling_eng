package view.panel.base.evaluation;

import controller.view.edit.panel.base.evaluation.ControllerPanelBaseOperation;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.evaluation.ControllerMetric;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Operation Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/08/2019
 * @see    controller.view.edit.panel.base.evaluation.ControllerPanelBaseOperation
 * @see    model.structural.base.evaluation.Metric
 * @see    view.panel.Panel
 */
public final class PanelBaseOperation extends Panel {
    private final ViewMenu viewMenu;
    private final Project project;
    private final Metric  metric;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param metric Metric.
     */
    public PanelBaseOperation(ViewMenu viewMenu, Metric metric) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.metric     = metric;
        this.controller = new ControllerPanelBaseOperation(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Target*: "));
        this.add(this.createComboBox("targetComboBox", ControllerMetric.TARGETS, 15, this.getSelectedItem()));
        
        this.add(this.createLabel("Operation*: "));
        this.add(this.createTextField("operationTextField", "", 15));
    }
    
    /**
     * Method responsible for returning the Selected Item.
     * @return Selected Item.
     */
    private Object getSelectedItem() {
        if (this.metric.getTarget() != null)
            return this.metric.getTarget();
        return "";
    }
    
    /**
     * Method responsible for setting the Operation Values.
     */
    public void setValues() {
        this.setTarget();
        this.getOperationTextField().setText(this.metric.getOperation());
    }
    
    /**
     * Method responsible for setting the Target.
     */
    public void setTarget() {
        this.metric.setTarget(this.getTargetComboBox().getSelectedItem().toString());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for return the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return this.getComboBox("targetComboBox");
    }
    
    /**
     * Method responsible for returning the Operation Text Field.
     * @return Operation Text Field.
     */
    public JTextField getOperationTextField() {
        return this.getTextField("operationTextField");
    }
}
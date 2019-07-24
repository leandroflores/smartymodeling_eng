package view.edit.panel.base.evaluation;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.controller.structural.base.evaluation.ControllerMetric;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMetric</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Metric Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/07/2019
 * @see    controller.view.edit.panel.base.evaluation.ControllerPanelBaseMetric
 * @see    model.structural.base.evaluation.Metric
 * @see    view.Panel
 */
public final class PanelBaseMetric extends Panel {
    private final ViewMenu viewMenu;
    private final Project project;
    private final Metric  metric;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param metric Metric.
     */
    public PanelBaseMetric(ViewMenu viewMenu, Metric metric) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.metric     = metric;
//        this.controller = new ControllerPanelBaseVariability(this);
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
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", "", 15));
        
        this.add(this.createLabel("Target*: "));
        this.add(this.createComboBox("targetComboBox", ControllerMetric.TARGETS, 15));
        
        this.add(this.createLabel("Operation*: "));
        this.add(this.createTextField("operationTextField", "", 15));
    }
    
    /**
     * Method responsible for setting the Metric Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.metric.getName());
        this.getTargetComboBox().setSelectedItem(this.metric.getTarget());
        this.getOperationTextField().setText(this.metric.getOperation());
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
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Target Combo Box.
     * @return Target Combo Box.
     */
    public JComboBox getTargetComboBox() {
        return this.comboBoxes.get("targetComboBox");
    }
    
    /**
     * Method responsible for returning the Operation Text Field.
     * @return Operation Text Field.
     */
    public JTextField getOperationTextField() {
        return this.textFields.get("operationTextField");
    }
}
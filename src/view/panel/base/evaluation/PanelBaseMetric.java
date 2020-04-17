package view.panel.base.evaluation;

import controller.view.panel.base.evaluation.ControllerPanelBaseMetric;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import model.structural.base.Project;
import model.structural.base.evaluation.Metric;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMetric</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Metric Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/07/2019
 * @see    controller.view.panel.base.evaluation.ControllerPanelBaseMetric
 * @see    model.structural.base.evaluation.Metric
 * @see    view.panel.Panel
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
        this.controller = new ControllerPanelBaseMetric(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "),  this.createConstraints(1, 1, 0, 0));
        this.add(this.createTextField("nameTextField",  "", 10),  this.createConstraints(2, 1, 1, 0));
        
        this.add(this.createLabel("Label*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createTextField("labelTextField", "", 10), this.createConstraints(2, 1, 1, 1));
        
        this.createTextArea("descriptionTextArea");
        this.add(this.createLabel("Description: "), this.createConstraints(1, 1, 0, 2));
        this.add(this.getDescriptionScrollPane(), this.createConstraints(2, 5, 1, 2));
    }
    
    /**
     * Method responsible for setting the Metric Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.metric.getName());
        this.getLabelTextField().setText(this.metric.getLabel());
        this.getDescriptionTextArea().setText(this.metric.getDescription());
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
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Label Text Field.
     * @return Label Text Field.
     */
    public JTextField getLabelTextField() {
        return this.getTextField("labelTextField");
    }
    
    /**
     * Method responsible for returning the Description Text Area.
     * @return Description Text Area.
     */
    public JTextArea getDescriptionTextArea() {
        return this.getTextArea("descriptionTextArea");
    }
    
    /**
     * Method responsible for return the Description Scroll Pane.
     * @return Description Scroll Pane.
     */
    public JScrollPane getDescriptionScrollPane() {
        return this.getScrollPane("descriptionTextArea");
    }
}
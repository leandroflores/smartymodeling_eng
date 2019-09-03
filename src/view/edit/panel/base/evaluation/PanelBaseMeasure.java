package view.edit.panel.base.evaluation;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMeasure</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Measure Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    controller.view.edit.panel.base.evaluation.
 * @see    model.structural.base.evaluation.Measure
 * @see    view.Panel
 */
public final class PanelBaseMeasure extends Panel {
    private final ViewMenu viewMenu;
    private final Project project;
    private final Measure measure;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param measure Measure.
     */
    public PanelBaseMeasure(ViewMenu viewMenu, Measure measure) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.measure    = measure;
//        this.controller = new ControllerPanelBaseMetric(this);
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
        this.add(this.createLabel("Name*: "),  this.getConstraints(1, 1, 0, 0));
        this.add(this.createTextField("nameTextField",  "", 10),  this.getConstraints(2, 1, 1, 0));
        
        this.add(this.createLabel("Date*: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createTextField("labelTextField", "", 10), this.getConstraints(2, 1, 1, 1));
    }
    
    /**
     * Method responsible for setting the Measure Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.measure.getName());
        this.getDateTextField().setText(this.measure.getDate());
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
     * Method responsible for return the Measure.
     * @return Measure.
     */
    public Measure getMeasure() {
        return this.measure;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Date Text Field.
     * @return Date Text Field.
     */
    public JTextField getDateTextField() {
        return this.textFields.get("dateTextField");
    }
}
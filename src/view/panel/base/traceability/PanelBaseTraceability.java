package view.panel.base.traceability;

import controller.view.panel.base.traceability.ControllerPanelBaseTraceability;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseTraceability</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Traceability Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/07/2019
 * @see    controller.view.panel.base.traceability.ControllerPanelBaseTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.panel.Panel
 */
public final class PanelBaseTraceability extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param traceability Traceability.
     */
    public PanelBaseTraceability(ViewMenu viewMenu, Traceability traceability) {
        this.viewMenu     = viewMenu;
        this.project      = this.viewMenu.getProject();
        this.traceability = traceability;
        this.controller   = new ControllerPanelBaseTraceability(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(2, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", "", 15));
        
        this.add(this.createLabel("Description*: "));
        this.add(this.createTextField("descriptionTextField", "", 15));
    }
    
    /**
     * Method responsible for setting the Traceability Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.traceability.getName());
        this.getDescriptionTextField().setText(this.traceability.getDescription());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for return the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Name Description Field.
     * @return Description Text Field.
     */
    public JTextField getDescriptionTextField() {
        return this.getTextField("descriptionTextField");
    }
}
package view.panel.export.code;

import controller.view.panel.export.ControllerPanelExportDiagram;
import controller.view.panel.export.code.ControllerPanelExportInstanceCode;
import javax.swing.JComboBox;
import model.controller.structural.base.product.ControllerProduct;
import model.structural.base.product.Instance;
import view.panel.export.PanelExport;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportInstanceCode</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Instance Code Export</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    controller.view.panel.export.code.ControllerPanelExportInstanceCode
 * @see    model.structural.base.product.Instance
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportInstanceCode extends PanelExport {
    private Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelExportInstanceCode(ViewMenu viewMenu) {
        super(viewMenu);
        this.instance   = null;
        this.controller = new ControllerPanelExportInstanceCode(this);
        this.setSettings();
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addDirectoryField();
        
        this.add(this.createLabel("Instance*: "), this.getConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("instanceComboBox", new ControllerProduct(this.project).getInstances("class"), 250), this.getConstraints(4, 1, 1, 1));
        this.setInstance((Instance) this.getInstanceComboBox().getSelectedItem());
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelExportDiagram getController() {
        return (ControllerPanelExportDiagram) this.controller;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for setting the Instance.
     * @param instance Instance.
     */
    public void setInstance(Instance instance) {
        this.instance = instance;
    }
    
    /**
     * Method responsible for returning the Instance Combo Box.
     * @return Instance Combo Box.
     */
    public JComboBox getInstanceComboBox() {
        return this.comboBoxes.get("instanceComboBox");
    }
}
package view.panel.export.base.product.code;

import controller.view.panel.export.base.product.code.ControllerPanelExportInstanceCode;
import model.controller.structural.base.product.ControllerProduct;
import model.structural.base.product.Instance;
import view.panel.export.PanelExport;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportInstanceCode</b>.</p> 
 * <p>Class responsible for defining a <b>Export Instance Code Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2010-01-29
 * @see    controller.view.panel.export.base.product.code.ControllerPanelExportInstanceCode
 * @see    model.structural.base.product.Instance
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportInstanceCode extends PanelExport {
    private Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelExportInstanceCode(ViewMenu view) {
        super(view);
        this.instance   = null;
        this.controller = new ControllerPanelExportInstanceCode(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().update();
    }
    
    @Override
    protected void addComponents() {
        super.addDirectoryField();
        
        this.add(this.createLabel("Instance*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("contextComboBox", new ControllerProduct(project).getInstances("class"), 250), this.createConstraints(5, 1, 1, 1));
        this.setInstance((Instance) this.getContextComboBox().getSelectedItem());
        
        super.addNameTextField();
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
    
    @Override
    public ControllerPanelExportInstanceCode getController() {
        return (ControllerPanelExportInstanceCode) this.controller;
    }
}
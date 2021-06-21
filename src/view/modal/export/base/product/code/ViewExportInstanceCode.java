package view.modal.export.base.product.code;

import controller.view.modal.export.base.product.code.ControllerViewExportInstanceCode;
import model.structural.base.product.Instance;
import view.modal.export.ViewExport;
import view.panel.export.base.product.code.PanelExportInstanceCode;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportInstanceCode</b>.</p>
 * <p>Class responsible for defining the <b>Export Instance Code View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.modal.export.base.product.code.ControllerViewExportInstanceCode
 * @see    model.structural.base.product.Instance
 * @see    view.modal.export.ViewExport
 */
public final class ViewExportInstanceCode extends ViewExport {
    private Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewExportInstanceCode(ViewMenu view) {
        super(view);
        controller = new ControllerViewExportInstanceCode(this);
        title      = "Export Instance Code";
        initComponents();
    }
    
    @Override
    protected PanelExportInstanceCode createPanelExport() {
        return new PanelExportInstanceCode(getView());
    }
    
    @Override
    public PanelExportInstanceCode getPanelExport() {
        return (PanelExportInstanceCode) getPanel("export");
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return instance;
    }
}
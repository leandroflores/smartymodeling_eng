package controller.view.panel.export.base.product.code;

import controller.view.panel.export.ControllerPanelExport;
import model.structural.base.product.Instance;
import view.panel.export.base.product.code.PanelExportInstanceCode;

/**
 * <p>Class of Controller <b>ControllerPanelExportInstanceCode</b>.</p>
 * <p>Class responsible for controlling the <b>PanelExportInstanceCode</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.product.Instance
 * @see    view.panel.export.base.product.code.PanelExportInstanceCode
 */
public class ControllerPanelExportInstanceCode extends ControllerPanelExport {

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Instance Code.
     */
    public ControllerPanelExportInstanceCode(PanelExportInstanceCode panel) {
        super(panel);
    }
    
    /**
     * Method responsible for returning the Selected Instance.
     * @return Selected Instance.
     */
    private Instance getSelectedInstance() {
        return (Instance) this.getPanel().getContextComboBox().getSelectedItem();
    }
    
    @Override
    public void update() {
        this.getPanel().getNameTextField().setText(this.getSelectedInstance().getName());
        this.getPanel().setInstance(this.getSelectedInstance());
    }
    
    @Override
    public PanelExportInstanceCode getPanel() {
        return (PanelExportInstanceCode) this.panel;
    }
}
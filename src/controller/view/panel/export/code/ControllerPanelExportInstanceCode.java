package controller.view.panel.export.code;

import controller.view.panel.export.ControllerPanelExport;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.product.Instance;
import view.panel.export.code.PanelExportInstanceCode;

/**
 * <p>Class of Controller <b>ControllerPanelExportInstanceCode</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelExportInstanceCode</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.product.Instance
 * @see    view.panel.export.code.PanelExportInstanceCode
 */
public class ControllerPanelExportInstanceCode extends ControllerPanelExport {
    private final PanelExportInstanceCode panelExportInstanceCode;

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Instance Code.
     */
    public ControllerPanelExportInstanceCode(PanelExportInstanceCode panel) {
        super(panel);
        this.panelExportInstanceCode = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelExportInstanceCode.getInstanceComboBox().equals(event.getSource()))
            this.update();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for returning the Selected Instance.
     * @return Selected Instance.
     */
    private Instance getSelectedInstance() {
        return (Instance) this.panelExportInstanceCode.getInstanceComboBox().getSelectedItem();
    }
    
    /**
     * Method responsible for updating the Instance.
     */
    public void update() {
        this.panelExportInstanceCode.getNameTextField().setText(this.getSelectedInstance().getName());
        this.panelExportInstanceCode.setInstance((Instance) this.panelExportInstanceCode.getInstanceComboBox().getSelectedItem());
    }
}
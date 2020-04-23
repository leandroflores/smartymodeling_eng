package controller.view.modal.export.base.product.code;

import controller.view.modal.export.ControllerViewExport;
import file.exportation.code.ExportInstance;
import java.io.IOException;
import model.structural.base.product.Instance;
import view.modal.export.base.product.code.ViewExportInstanceCode;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewExportInstanceCode</b>.</p>
 * <p>Class responsible for controlling the <b>ViewExportInstanceCode</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.modal.export.ControllerViewExport
 * @see    file.exportation.code.ExportInstance
 * @see    model.structural.base.product.Instance
 * @see    view.modal.export.base.product.code.ViewExportInstanceCode
 */
public class ControllerViewExportInstanceCode extends ControllerViewExport {

    /**
     * Default constructor method of Class.
     * @param viewExport View Export Instance Code.
     */
    public ControllerViewExportInstanceCode(ViewExportInstanceCode viewExport) {
        super(viewExport);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelExportInstanceCode().getDirectoryTextField(), "Select a Directory!")
            && this.check(this.getView().getPanelExportInstanceCode().getNameTextField(), "Name is required!")
            && this.checkInstance();
    }
    
    /**
     * Method responsible for checking the Instance.
     * @return Instance is selected.
     */
    private boolean checkInstance() {
        if (this.getView().getPanelExportInstanceCode().getInstance() == null) {
            new ViewError(this.getView(), "Select a Class Instance!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public void export() {
        String   path     = this.getView().getPanelExportInstanceCode().getDirectoryTextField().getText().trim();
        Instance instance = this.getView().getPanelExportInstanceCode().getInstance();
        String   name     = this.getView().getPanelExportInstanceCode().getNameTextField().getText().trim();
        try {
            new ExportInstance(path, name, instance).export();
        } catch (IOException exception) {
            new ViewError(this.getView(), "Error to Export the Code Instance!").setVisible(true);
        }
        this.getView().dispose();
    }
    
    @Override
    public ViewExportInstanceCode getView() {
        return (ViewExportInstanceCode) this.viewModal;
    }
}
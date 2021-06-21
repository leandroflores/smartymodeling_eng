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
     * @param view View Export Instance Code.
     */
    public ControllerViewExportInstanceCode(ViewExportInstanceCode view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelExport().getDirectoryTextField(), "Select a Directory!")
            && check(getView().getPanelExport().getNameTextField(), "Name is required!")
            && checkInstance();
    }
    
    /**
     * Method responsible for checking the Instance.
     * @return Instance is selected.
     */
    private boolean checkInstance() {
        if (getView().getPanelExport().getInstance() == null) {
            new ViewError(getView(), "Select a Class Instance!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public void export() {
        String   path     = getView().getPanelExport().getDirectoryTextField().getText().trim();
        Instance instance = getView().getPanelExport().getInstance();
        String   name     = getView().getPanelExport().getNameTextField().getText().trim();
        try {
            new ExportInstance(path, name, instance).export();
        } catch (IOException exception) {
            new ViewError(getView(), "Error to Export the Code Instance!").setVisible(true);
        }
        getView().dispose();
    }
    
    @Override
    public ViewExportInstanceCode getView() {
        return (ViewExportInstanceCode) super.getView();
    }
}
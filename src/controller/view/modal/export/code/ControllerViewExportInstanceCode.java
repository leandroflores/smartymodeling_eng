package controller.view.modal.export.code;

import controller.view.modal.export.ControllerViewExport;
import file.exportation.code.ExportInstance;
import java.io.IOException;
import model.structural.base.product.Instance;
import view.modal.export.code.ViewExportInstanceCode;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewExportInstanceCode</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewExportInstanceCode</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    controller.view.modal.export.ControllerViewExport
 * @see    file.exportation.code.ExportInstance
 * @see    model.structural.base.product.Instance
 * @see    view.modal.export.code.ViewExportInstanceCode
 */
public class ControllerViewExportInstanceCode extends ControllerViewExport {
    private final ViewExportInstanceCode viewExportInstanceCode;

    /**
     * Default constructor method of Class.
     * @param viewExport View Export Instance Code.
     */
    public ControllerViewExportInstanceCode(ViewExportInstanceCode viewExport) {
        super(viewExport);
        this.viewExportInstanceCode = viewExport;
    }
    
    /**
     * Method responsible for checking the Export Directory.
     * @return Directory is checked.
     */
    private boolean checkDirectory() {
        return this.check(this.viewExportInstanceCode.getPanelExportInstanceCode().getDirectoryTextField(), "Select a Directory!");
    }
    
    /**
     * Method responsible for checking the Instance.
     * @return Instance is selected.
     */
    private boolean checkInstance() {
        if (this.viewExportInstanceCode.getPanelExportInstanceCode().getInstance() == null) {
            new ViewError(this.viewExport, "Select a Class Instance!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Name.
     * @return Name is checked.
     */
    private boolean checkName() {
        return this.check(this.viewExportInstanceCode.getPanelExportInstanceCode().getNameTextField(), "Name is required!");
    }
    
    @Override
    public boolean check() {
        return this.checkDirectory()
            && this.checkInstance()
            && this.checkName();
    }

    @Override
    public void export() {
        String   path     = this.viewExportInstanceCode.getPanelExportInstanceCode().getDirectoryTextField().getText().trim();
        Instance instance = this.viewExportInstanceCode.getPanelExportInstanceCode().getInstance();
        String   name     = this.viewExportInstanceCode.getPanelExportInstanceCode().getNameTextField().getText().trim();
        try {
            new ExportInstance(path, name, instance).export();
        } catch (IOException exception) {
            new ViewError(this.viewExportInstanceCode, "Error to Export the Code Instance!").setVisible(true);
        }
        this.viewExportInstanceCode.dispose();
    }
}
package controller.view.modal.export.base.code;

import controller.view.modal.export.ControllerViewExport;
import file.exportation.code.ExportDiagram;
import java.io.IOException;
import model.structural.base.Diagram;
import model.structural.diagram.ClassDiagram;
import view.modal.export.base.code.ViewExportDiagramCode;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewExportDiagramCode</b>.</p>
 * <p>Class responsible for controlling the <b>ViewExportDiagramCode</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-19
 * @see    controller.view.modal.export.ControllerViewExport
 * @see    file.exportation.code.ExportDiagram
 * @see    model.structural.base.Diagram
 * @see    view.modal.export.base.code.ViewExportDiagramCode
 */
public class ControllerViewExportDiagramCode extends ControllerViewExport {

    /**
     * Default constructor method of Class.
     * @param view View Export Diagram Code.
     */
    public ControllerViewExportDiagramCode(ViewExportDiagramCode view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return this.check(this.getView().getPanelExportDiagramCode().getDirectoryTextField(), "Select a Directory!")
            && this.check(this.getView().getPanelExportDiagramCode().getNameTextField(), "Name is required!")
            && this.checkDiagram();
    }
    
    /**
     * Method responsible for checking the Diagram.
     * @return Diagram is selected.
     */
    private boolean checkDiagram() {
        if (this.getView().getPanelExportDiagramCode().getDiagram() == null) {
            new ViewError(this.getView(), "Select a Class Diagram!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public void export() {
        String  path    = this.getView().getPanelExportDiagramCode().getDirectoryTextField().getText().trim();
        Diagram diagram = this.getView().getPanelExportDiagramCode().getDiagram();
        String  name    = this.getView().getPanelExportDiagramCode().getNameTextField().getText().trim();
        try {
            new ExportDiagram(path, name, (ClassDiagram) diagram).export();
        } catch (IOException exception) {
            new ViewError(this.getView(), "Error to Export the Code Diagram!").setVisible(true);
        }
        this.getView().dispose();
    }
    
    @Override
    public ViewExportDiagramCode getView() {
        return (ViewExportDiagramCode) this.viewModal;
    }
}
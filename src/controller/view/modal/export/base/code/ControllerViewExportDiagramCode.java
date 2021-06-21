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
        return check(getView().getPanelExport().getDirectoryTextField(), "Select a Directory!")
            && check(getView().getPanelExport().getNameTextField(), "Name is required!")
            && checkDiagram();
    }
    
    /**
     * Method responsible for checking the Diagram.
     * @return Diagram is selected.
     */
    private boolean checkDiagram() {
        if (getView().getPanelExport().getDiagram() == null) {
            new ViewError(getView(), "Select a Class Diagram!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public void export() {
        String  path    = getView().getPanelExport().getDirectoryTextField().getText().trim();
        Diagram diagram = getView().getPanelExport().getDiagram();
        String  name    = getView().getPanelExport().getNameTextField().getText().trim();
        try {
            new ExportDiagram(path, name, (ClassDiagram) diagram).export();
        } catch (IOException exception) {
            new ViewError(getView(), "Error to Export the Code Diagram!").setVisible(true);
        }
        getView().dispose();
    }
    
    @Override
    public ViewExportDiagramCode getView() {
        return (ViewExportDiagramCode) super.getView();
    }
}
package controller.view.modal.export.code;

import controller.view.modal.export.ControllerViewExport;
import file.exportation.code.ExportDiagram;
import java.io.IOException;
import model.structural.base.Diagram;
import model.structural.diagram.ClassDiagram;
import view.modal.export.code.ViewExportDiagramCode;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewExportDiagramCode</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewExportDiagramCode</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  19/01/2020
 * @see    controller.view.modal.export.ControllerViewExport
 * @see    file.exportation.code.ExportDiagram
 * @see    model.structural.base.Diagram
 * @see    view.modal.export.code.ViewExportDiagramCode
 */
public class ControllerViewExportDiagramCode extends ControllerViewExport {
    private final ViewExportDiagramCode viewExportDiagramCode;

    /**
     * Default constructor method of Class.
     * @param viewExport View Export Diagram Code.
     */
    public ControllerViewExportDiagramCode(ViewExportDiagramCode viewExport) {
        super(viewExport);
        this.viewExportDiagramCode = viewExport;
    }
    
    /**
     * Method responsible for checking the Export Directory.
     * @return Directory is checked.
     */
    private boolean checkDirectory() {
        return this.check(this.viewExportDiagramCode.getPanelExportDiagramCode().getDirectoryTextField(), "Select a Directory!");
    }
    
    /**
     * Method responsible for checking the Diagram.
     * @return Diagram is selected.
     */
    private boolean checkDiagram() {
        if (this.viewExportDiagramCode.getPanelExportDiagramCode().getDiagram() == null) {
            new ViewError(this.viewExport, "Select a Class Diagram!").setVisible(true);
            return false;
        }
        return true;
    }
    
    /**
     * Method responsible for checking the Name.
     * @return Name is checked.
     */
    private boolean checkName() {
        return this.check(this.viewExportDiagramCode.getPanelExportDiagramCode().getNameTextField(), "Name is required!");
    }
    
    @Override
    public boolean check() {
        return this.checkDirectory()
            && this.checkDiagram()
            && this.checkName();
    }

    @Override
    public void export() {
        String  path    = this.viewExportDiagramCode.getPanelExportDiagramCode().getDirectoryTextField().getText().trim();
        Diagram diagram = this.viewExportDiagramCode.getPanelExportDiagramCode().getDiagram();
        String  name    = this.viewExportDiagramCode.getPanelExportDiagramCode().getNameTextField().getText().trim();
        try {
            new ExportDiagram(path, name, (ClassDiagram) diagram).export();
        } catch (IOException exception) {
            new ViewError(this.viewExportDiagramCode, "Error to Export the Code Diagram!").setVisible(true);
        }
        this.viewExportDiagramCode.dispose();
    }
}
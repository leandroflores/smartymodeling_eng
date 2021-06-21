package controller.view.modal.export.base;

import controller.view.modal.export.ControllerViewExport;
import file.exportation.diagram.ExportDiagram;
import java.io.IOException;
import model.structural.base.Diagram;
import view.modal.export.base.ViewExportDiagram;
import view.modal.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewExportDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>ViewExportDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    controller.view.modal.export.ControllerViewExport
 * @see    file.exportation.diagram.ExportDiagram
 * @see    model.structural.base.Diagram
 * @see    view.modal.export.base.ViewExportDiagram
 */
public class ControllerViewExportDiagram extends ControllerViewExport {

    /**
     * Default constructor method of Class.
     * @param view View Export Diagram.
     */
    public ControllerViewExportDiagram(ViewExportDiagram view) {
        super(view);
    }
    
    @Override
    public boolean check() {
        return check(getView().getPanelExport().getDirectoryTextField(), "Select a Directory!");
    }

    @Override
    public void export() {
        String  path    = getView().getPanelExport().getDirectoryTextField().getText().trim();
        Diagram diagram = getView().getPanelExport().getDiagram();
        try {
            new ExportDiagram(path, diagram).export();
        } catch (IOException exception) {
            new ViewError(getView(), "Error to Export the Diagram!").setVisible(true);
        }
        getView().dispose();
    }
    
    @Override
    public ViewExportDiagram getView() {
        return (ViewExportDiagram) super.getView();
    }
}
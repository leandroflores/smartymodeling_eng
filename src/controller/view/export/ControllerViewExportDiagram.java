package controller.view.export;

import file.exportation.diagram.ExportDiagram;
import java.io.IOException;
import model.structural.base.Diagram;
import view.export.ViewExportDiagram;
import view.message.ViewError;

/**
 * <p>Class of Controller <b>ControllerViewExportDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewExportDiagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    controller.view.export.ControllerViewExport
 * @see    model.structural.base.Diagram
 * @see    view.export.ViewExportDiagram
 */
public class ControllerViewExportDiagram extends ControllerViewExport {
    private final ViewExportDiagram viewExportDiagram;

    /**
     * Default constructor method of Class.
     * @param viewExport View Export Diagram.
     */
    public ControllerViewExportDiagram(ViewExportDiagram viewExport) {
        super(viewExport);
        this.viewExportDiagram = viewExport;
    }
    
    @Override
    public boolean check() {
        return this.check(this.viewExportDiagram.getPanelExportDiagram().getDirectoryTextField(), "Select a Directory!");
    }

    @Override
    public void export() {
        String  path    = this.viewExportDiagram.getPanelExportDiagram().getDirectoryTextField().getText().trim();
        Diagram diagram = this.viewExportDiagram.getPanelExportDiagram().getDiagram();
        try {
            new ExportDiagram(path, diagram).export();
        } catch (IOException exception) {
            new ViewError(this.viewExportDiagram, "Error to Export the Diagram!").setVisible(true);
        }
        this.viewExportDiagram.dispose();
    }
}
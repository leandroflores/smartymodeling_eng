package view.modal.export.base.code;

import controller.view.modal.export.base.code.ControllerViewExportDiagramCode;
import model.structural.base.Diagram;
import view.modal.export.ViewExport;
import view.panel.export.base.code.PanelExportDiagramCode;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportDiagramCode</b>.</p>
 * <p>Class responsible for defining the <b>Export Diagram Code View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-19
 * @see    controller.view.modal.export.base.code.ControllerViewExportDiagramCode
 * @see    model.structural.base.Diagram
 * @see    view.modal.export.ViewExport
 */
public final class ViewExportDiagramCode extends ViewExport {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ViewExportDiagramCode(ViewMenu viewMenu) {
        super(viewMenu);
        controller = new ControllerViewExportDiagramCode(this);
        title      = "Export Diagram Code";
        initComponents();
    }
    
    @Override
    protected PanelExportDiagramCode createPanelExport() {
        return new PanelExportDiagramCode(getView());
    }
    
    @Override
    public PanelExportDiagramCode getPanelExport() {
        return (PanelExportDiagramCode) getPanel("export");
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}
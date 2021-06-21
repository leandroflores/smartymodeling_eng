package view.modal.export.base;

import controller.view.modal.export.base.ControllerViewExportDiagram;
import model.structural.base.Diagram;
import view.modal.export.ViewExport;
import view.panel.export.base.PanelExportDiagram;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Export Diagram View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    controller.view.modal.export.base.ControllerViewExportDiagram
 * @see    model.structural.base.Diagram
 * @see    view.modal.export.ViewExport
 */
public final class ViewExportDiagram extends ViewExport {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewExportDiagram(ViewMenu view) {
        super(view);
        controller = new ControllerViewExportDiagram(this);
        title      = "Export Diagram";
        initComponents();
    }
    
    @Override
    protected PanelExportDiagram createPanelExport() {
        return new PanelExportDiagram(getView());
    }
    
    @Override
    public PanelExportDiagram getPanelExport() {
        return (PanelExportDiagram) getPanel("export");
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}
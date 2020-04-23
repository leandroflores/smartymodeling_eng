package view.modal.export.base.code;

import controller.view.modal.export.base.code.ControllerViewExportDiagramCode;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
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
 * @see    view.panel.export.base.code.PanelExportDiagramCode
 */
public final class ViewExportDiagramCode extends ViewExport {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ViewExportDiagramCode(ViewMenu viewMenu) {
        super(viewMenu);
        this.controller = new ControllerViewExportDiagramCode(this);
        this.title      = "Export Diagram Code";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 420);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 300));
            this.addPanelExportDiagramCode();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Export Diagram Code.
     */
    private void addPanelExportDiagramCode() {
        this.addPanel("panelExportDiagramCode", new PanelExportDiagramCode(this.getViewMenu()));
        this.createScrollPane("scrollPanelExportDiagramCode",  this.getPanelExportDiagramCode());
        this.getScrollPane("scrollPanelExportDiagramCode").setViewportView(this.getPanelExportDiagramCode());
        this.tabbedPane.add("Export Diagram Code", this.getScrollPane("scrollPanelExportDiagramCode"));
    }
    
    /**
     * Method responsible for returning the Panel Export Diagram Code.
     * @return Panel Export Diagram Code.
     */
    public PanelExportDiagramCode getPanelExportDiagramCode() {
        return (PanelExportDiagramCode) this.getPanel("panelExportDiagramCode");
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }

    /**
     * Method responsible for setting the Diagram.
     * @param diagram Diagram.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
}
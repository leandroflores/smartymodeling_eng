package view.modal.export.base;

import controller.view.modal.export.base.ControllerViewExportDiagram;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
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
        this.controller = new ControllerViewExportDiagram(this);
        this.title      = "Export Diagram";
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
            this.addPanelExportDiagram();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Export Diagram.
     */
    private void addPanelExportDiagram() {
        this.addPanel("panelExportDiagram", new PanelExportDiagram(this.getViewMenu()));
        this.createScrollPane("scrollPanelExportDiagram", this.getPanelExportDiagram());
        this.getScrollPane("scrollPanelExportDiagram").setViewportView(this.getPanelExportDiagram());
        this.tabbedPane.add("Export Diagram", this.getScrollPane("scrollPanelExportDiagram"));
    }
    
    /**
     * Method responsible for returning the Panel Export Diagram.
     * @return Panel Export Diagram.
     */
    public PanelExportDiagram getPanelExportDiagram() {
        return (PanelExportDiagram) this.getPanel("panelExportDiagram");
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
package view.export;

import controller.view.export.ControllerViewExportDiagram;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import view.panel.export.PanelExportDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Export Diagram View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    controller.view.export.ControllerViewExportDiagram
 * @see    model.structural.base.Diagram
 * @see    view.export.ViewExport
 */
public final class ViewExportDiagram extends ViewExport {
    private Diagram diagram;
    private PanelExportDiagram panelExportDiagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ViewExportDiagram(ViewMenu viewMenu) {
        super(viewMenu);
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
        this.panelExportDiagram = new PanelExportDiagram(this.getViewMenu());
        this.createScrollPane("scrollPanelExportDiagram",  this.panelExportDiagram);
        this.getScrollPanelExportDiagram().setViewportView(this.panelExportDiagram);
        this.tabbedPane.add("Export Diagram", this.getScrollPanelExportDiagram());
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
    
    /**
     * Method responsible for returning the Panel Export Diagram.
     * @return Panel Export Diagram.
     */
    public PanelExportDiagram getPanelExportDiagram() {
        return this.panelExportDiagram;
    }
    
    /**
     * Method responsible for returning the Panel Export Diagram.
     * @return Panel Export Diagram.
     */
    public JScrollPane getScrollPanelExportDiagram() {
        return this.getScrollPane("scrollPanelExportDiagram");
    }
}
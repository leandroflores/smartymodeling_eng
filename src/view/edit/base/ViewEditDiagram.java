package view.edit.base;

import controller.view.edit.base.ControllerViewEditDiagram;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import view.edit.ViewEdit;
import view.panel.base.PanelBaseDiagram;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-29
 * @see    controller.view.edit.base.ControllerViewEditDiagram
 * @see    model.structural.base.Diagram
 * @see    view.edit.ViewEdit
 */
public final class ViewEditDiagram extends ViewEdit {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     */
    public ViewEditDiagram(PanelModeling panel, Diagram diagram) {
        super(panel);
        this.diagram    = diagram;
        this.controller = new ControllerViewEditDiagram(this);
        this.title      = "Edit Diagram Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 320);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
        
        this.addPanelBaseDiagram();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Diagram.
     */
    private void addPanelBaseDiagram() {
        this.addPanel("panelBaseDiagram", new PanelBaseDiagram(this.getViewMenu(), this.diagram));
        this.createScrollPane("scrollPanelBaseDiagram",  this.getPanelBaseDiagram());
        this.getScrollPanelBaseDiagram().setViewportView(this.getPanelBaseDiagram());
        this.tabbedPane.add("Diagram", this.getScrollPanelBaseDiagram());
    }
    
    /**
     * Method responsible for returning the Panel Base Diagram.
     * @return Panel Base Diagram.
     */
    public PanelBaseDiagram getPanelBaseDiagram() {
        return (PanelBaseDiagram) this.getPanel("panelBaseDiagram");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Diagram.
     * @return Scroll Panel Base Diagram.
     */
    public JScrollPane getScrollPanelBaseDiagram() {
        return this.getScrollPane("scrollPanelBaseDiagram");
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
}
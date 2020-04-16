package view.edit;

import controller.view.edit.ControllerViewEditDiagram;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import view.edit.panel.base.PanelBaseDiagram;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    controller.view.edit.ControllerViewEditDiagram
 * @see    view.edit.ViewEdit
 */
public final class ViewEditDiagram extends ViewEdit {
    private final Diagram diagram;
    private PanelBaseDiagram panelBaseDiagram;
    
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
        this.setValues();
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
        this.panelBaseDiagram = new PanelBaseDiagram(this.getViewMenu(), this.diagram);
        this.createScrollPane("scrollPanelBaseDiagram",  this.panelBaseDiagram);
        this.getScrollPanelBaseDiagram().setViewportView(this.panelBaseDiagram);
        this.tabbedPane.add("Diagram", this.getScrollPanelBaseDiagram());
    }
    
    @Override
    public void setValues() {
        this.panelBaseDiagram.getNameTextField().setText(this.diagram.getName());
        this.panelBaseDiagram.getTypeTextField().setText(this.diagram.getType());
        
        this.panelBaseDiagram.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning Panel Base Diagram.
     * @return Panel Base Diagram.
     */
    public JScrollPane getScrollPanelBaseDiagram() {
        return this.getScrollPane("scrollPanelBaseDiagram");
    }
    
    /**
     * Method responsible for returning Panel Data Diagram.
     * @return Panel Data Diagram.
     */
    public PanelBaseDiagram getPanelBaseDiagram() {
        return this.panelBaseDiagram;
    }
}
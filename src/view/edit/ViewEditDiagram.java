package view.edit;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import view.edit.panel.PanelDataDiagram;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    controller.view.edit.
 * @see    view.edit.ViewEdit
 */
public final class ViewEditDiagram extends ViewEdit {
    private final Diagram diagram;
    private PanelDataDiagram panelDataDiagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     */
    public ViewEditDiagram(PanelModeling panel, Diagram diagram) {
        super(panel);
        this.diagram    = diagram;
//        this.controller = new ControllerViewEditarDiagrama(this);
        this.title      = "Edit Diagram";
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
        
    }
    
    /**
     * Method responsible for adding the Panel Diagram.
     */
    private void addPanelDiagram() {
        this.panelDataDiagram = new PanelDataDiagram();
        this.createScrollPane("scrollPanelDataDiagram",  this.panelDataDiagram);
        this.getScrollPanelDataDiagram().setViewportView(this.panelDataDiagram);
        this.tabbedPane.add("Diagram", this.getScrollPanelDataDiagram());
    }
    
    @Override
    public void setValues() {
        this.panelDataDiagram.getNameTextField().setText(this.diagram.getName());
        this.panelDataDiagram.getTypeTextField().setText(this.diagram.getType());
        
        this.panelDataDiagram.getNameTextField().requestFocus();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning Panel Data Diagram.
     * @return Panel Data Diagram.
     */
    public JScrollPane getScrollPanelDataDiagram() {
        return this.scrollPanes.get("scrollPanelDataDiagram");
    }
    
    /**
     * Method responsible for returning Panel Data Diagram.
     * @return Panel Data Diagram.
     */
    public PanelDataDiagram getPanelDataDiagram() {
        return this.panelDataDiagram;
    }
}
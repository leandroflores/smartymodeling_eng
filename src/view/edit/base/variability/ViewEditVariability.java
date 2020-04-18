package view.edit.base.variability;

import view.edit.base.ViewEdit;
import controller.view.edit.base.variability.ControllerViewEditVariability;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.panel.base.variability.PanelBaseVariability;
import view.panel.base.variability.PanelBaseVariants;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    controller.view.edit.base.variability.ControllerViewEditVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.edit.ViewEdit
 */
public final class ViewEditVariability extends ViewEdit {
    private final Variability variability;
    private final Diagram diagram;
    private PanelBaseVariability panelBaseVariability;
    private PanelBaseVariants panelBaseVariants;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public ViewEditVariability(PanelModeling panel, Diagram diagram, Variability variability) {
        super(panel);
        this.variability = variability;
        this.diagram     = diagram;
        this.controller  = new ControllerViewEditVariability(this);
        this.title       = "Edit Variability Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(520, 500);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.setValues();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.addChangeListener((ChangeListener) this.controller);
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));
        
        this.addPanelBaseVariability();
        this.addPanelBaseVariants();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Variability.
     */
    private void addPanelBaseVariability() {
        this.panelBaseVariability = new PanelBaseVariability(this.getViewMenu(), this.diagram, this.variability);
        this.createScrollPane("scrollPanelBaseVariability",  this.panelBaseVariability);
        this.getScrollPanelBaseVariability().setViewportView(this.panelBaseVariability);
        this.tabbedPane.add("Variability", this.getScrollPanelBaseVariability());
    }
    
    /**
     * Method responsible for adding the Panel Base Variants.
     */
    private void addPanelBaseVariants() {
        this.panelBaseVariants  = new PanelBaseVariants(this.getViewMenu(), this.diagram, this.variability);
        this.createScrollPane("scrollPanelVariants",  this.panelBaseVariants);
        this.getScrollPanelVariants().setViewportView(this.panelBaseVariants);
        this.tabbedPane.add("Variants", this.getScrollPanelVariants());
    }
    
    @Override
    public void setValues() {
        this.panelBaseVariability.setValues();
        this.panelBaseVariants.setValues();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return this.variability;
    }
    
    /**
     * Method responsible for returning the Panel Base Variability.
     * @return Panel Base Variability.
     */
    public PanelBaseVariability getPanelBaseVariability() {
        return this.panelBaseVariability;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Variability.
     * @return Scroll Panel Base Variability.
     */
    public JScrollPane getScrollPanelBaseVariability() {
        return this.getScrollPane("scrollPanelBaseVariability");
    }
    
    /**
     * Method responsible for returning the Panel Base Variants.
     * @return Panel Base Variants.
     */
    public PanelBaseVariants getPanelBaseVariants() {
        return this.panelBaseVariants;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Variants.
     * @return Scroll Panel Variants.
     */
    public JScrollPane getScrollPanelVariants() {
        return this.getScrollPane("scrollPanelVariants");
    }
}
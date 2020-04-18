package view.edit.base.variability;

import controller.view.edit.base.variability.ControllerViewEditVariability;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.edit.ViewEdit;
import view.panel.base.variability.PanelBaseVariability;
import view.panel.base.variability.PanelBaseVariants;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.edit.base.variability.ControllerViewEditVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.edit.ViewEdit
 */
public final class ViewEditVariability extends ViewEdit {
    private final Variability variability;
    private final Diagram diagram;
    
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
        this.addPanel("panelBaseVariability", new PanelBaseVariability(this.getViewMenu(), this.diagram, this.variability));
        this.createScrollPane("scrollPanelBaseVariability",  this.getPanelBaseVariability());
        this.getScrollPanelBaseVariability().setViewportView(this.getPanelBaseVariability());
        this.tabbedPane.add("Variability", this.getScrollPanelBaseVariability());
    }
    
    /**
     * Method responsible for adding the Panel Base Variants.
     */
    private void addPanelBaseVariants() {
        this.addPanel("panelBaseVariants", new PanelBaseVariants(this.getViewMenu(), this.diagram, this.variability));
        this.createScrollPane("scrollPanelBaseVariants",  this.getPanelBaseVariants());
        this.getScrollPanelBaseVariants().setViewportView(this.getPanelBaseVariants());
        this.tabbedPane.add("Variants", this.getScrollPanelBaseVariants());
    }
    
    /**
     * Method responsible for returning the Panel Base Variability.
     * @return Panel Base Variability.
     */
    public PanelBaseVariability getPanelBaseVariability() {
        return (PanelBaseVariability) this.getPanel("panelBaseVariability");
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
        return (PanelBaseVariants) this.getPanel("panelBaseVariants");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Variants.
     * @return Scroll Panel Base Variants.
     */
    public JScrollPane getScrollPanelBaseVariants() {
        return this.getScrollPane("scrollPanelBaseVariants");
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return this.variability;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
}
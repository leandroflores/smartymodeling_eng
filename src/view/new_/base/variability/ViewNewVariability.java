package view.new_.base.variability;

import controller.view.new_.base.variability.ControllerViewNewVariability;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.panel.base.variability.PanelBaseVariability;
import view.panel.base.variability.PanelBaseVariants;
import view.new_.base.ViewNew;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewNewVariability</b>.</p>
 * <p>Class responsible for defining the <b>New Variability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.new_.base.variability.ControllerViewNewVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.new_.base.ViewNew
 */
public final class ViewNewVariability extends ViewNew { 
    private final Diagram diagram;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     * @param variationPoint Variation Point.
     */
    public ViewNewVariability(PanelModeling panel, Diagram diagram, Element variationPoint) {
        super(panel);
        this.diagram     = diagram;
        this.variability = new Variability(variationPoint);
        this.controller  = new ControllerViewNewVariability(this);
        this.title       = "New Variability";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
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
        this.addPanel("panelBaseVariability",  new PanelBaseVariability(this.getViewMenu(), this.diagram, this.variability));
        this.createScrollPane("scrollPanelBaseVariability",  this.getPanelBaseVariability());
        this.getScrollPanelBaseVariability().setViewportView(this.getPanelBaseVariability());
        this.tabbedPane.add("Variability", this.getScrollPanelBaseVariability());
    }
    
    /**
     * Method responsible for adding the Panel Base Variants.
     */
    private void addPanelBaseVariants() {
        this.addPanel("panelBaseVariants", new PanelBaseVariants(this.getViewMenu(), this.diagram, this.variability));
        this.createScrollPane("scrollPanelVariants",  this.getPanelBaseVariants());
        this.getScrollPanelVariants().setViewportView(this.getPanelBaseVariants());
        this.tabbedPane.add("Variants", this.getScrollPanelVariants());
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
     * Method responsible for returning the Scroll Panel Variants.
     * @return Scroll Panel Variants.
     */
    public JScrollPane getScrollPanelVariants() {
        return this.getScrollPane("scrollPanelVariants");
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
}
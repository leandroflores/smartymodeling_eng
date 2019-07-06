package view.new_;

import controller.view.new_.ControllerViewNewVariability;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.edit.panel.base.variability.PanelBaseVariability;
import view.edit.panel.base.variability.PanelBaseVariants;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditVariability</b>.</p>
 * <p>Class responsible for defining the <b>New Variability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/07/2019
 * @see    controller.view.new_.
 * @see    model.structural.base.variability.Variability
 * @see    view.new_.ViewNew
 */
public final class ViewNewVariability extends ViewNew { 
    private final Diagram diagram;
    private final Variability variability;
    private PanelBaseVariability panelBaseVariability;
    private PanelBaseVariants    panelBaseVariants;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     */
    public ViewNewVariability(PanelModeling panel, Diagram diagram) {
        super(panel);
        this.diagram     = diagram;
        this.variability = new Variability();
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
        this.panelBaseVariability = new PanelBaseVariability(this.getViewMenu(), this.diagram, this.variability);
        this.createScrollPane("scrollPanelBaseVariability", this.panelBaseVariability);
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
        return this.scrollPanes.get("scrollPanelBaseVariability");
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
        return this.scrollPanes.get("scrollPanelVariants");
    }
}
package view.new_;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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
    private PanelBaseVariability panelVariability;
    private PanelBaseVariants    panelVariants;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     */
    public ViewNewVariability(PanelModeling panel, Diagram diagram) {
        super(panel);
        this.diagram     = diagram;
        this.variability = new Variability();
//        this.controller  = new ControllerViewEditMethod(this);
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
        this.panelVariability = new PanelBaseVariability(this.getViewMenu(), this.diagram, this.variability);
        this.createScrollPane("scrollPanelBaseVariability", this.panelVariability);
        this.getScrollPanelBaseVariability().setViewportView(this.panelVariability);
        this.tabbedPane.add("Variability", this.getScrollPanelBaseVariability());
    }
    
    /**
     * Method responsible for adding the Panel Base Variants.
     */
    private void addPanelBaseVariants() {
        this.panelVariants  = new PanelBaseVariants(this.getViewMenu(), this.diagram, this.variability);
        this.createScrollPane("scrollPanelVariants",  this.panelVariants);
        this.getScrollPanelVariants().setViewportView(this.panelVariants);
        this.tabbedPane.add("Variants", this.getScrollPanelVariants());
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Variability.
     * @return Scroll Panel Base Variability.
     */
    public JScrollPane getScrollPanelBaseVariability() {
        return this.scrollPanes.get("scrollPanelBaseVariability");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Variants.
     * @return Scroll Panel Variants.
     */
    public JScrollPane getScrollPanelVariants() {
        return this.scrollPanes.get("scrollPanelVariants");
    }
}
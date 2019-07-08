package view.panel.edit.base.variability;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.edit.panel.base.variability.PanelBaseVariability;
import view.edit.panel.base.variability.PanelBaseVariants;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditVariability</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Variability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/07/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.variability.Variability
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditVariability extends PanelEdit {
    private final Diagram diagram;
    private final Variability variability;
    private PanelBaseVariability panelBaseVariability;
    private PanelBaseVariants    panelBaseVariants;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public PanelEditVariability(ViewMenu viewMenu, Diagram diagram, Variability variability) {
        super(viewMenu);
        this.diagram     = diagram;
        this.variability = variability;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseVariability();
        this.addPanelBaseVariants();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Variability.
     */
    private void addPanelBaseVariability() {
        this.panelBaseVariability = new PanelBaseVariability(this.viewMenu, this.diagram, this.variability);
        this.createScrollPane("scrollPanelBaseVariability",  this.panelBaseVariability);
        this.getScrollPanelBaseVariability().setViewportView(this.panelBaseVariability);
        this.tabbedPane.add("Variability", this.getScrollPanelBaseVariability());
    }
    
    /**
     * Method responsible for adding the Panel Base Variants.
     */
    private void addPanelBaseVariants() {
        this.panelBaseVariants = new PanelBaseVariants(this.viewMenu, this.diagram, this.variability);
        this.createScrollPane("scrollPanelBaseVariants",  this.panelBaseVariants);
        this.getScrollPanelBaseVariants().setViewportView(this.panelBaseVariants);
        this.tabbedPane.add("Variants", this.getScrollPanelBaseVariants());
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
     * Method responsible for returning the Scroll Panel Base Variability.
     * @return Scroll Panel Base Variability.
     */
    public JScrollPane getScrollPanelBaseVariability() {
        return this.scrollPanes.get("scrollPanelBaseVariability");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Variants.
     * @return Scroll Panel Base Variants.
     */
    public JScrollPane getScrollPanelBaseVariants() {
        return this.scrollPanes.get("scrollPanelBaseVariants");
    }
}
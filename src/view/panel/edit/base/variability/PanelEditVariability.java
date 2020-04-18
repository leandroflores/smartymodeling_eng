package view.panel.edit.base.variability;

import controller.view.panel.edit.base.variability.ControllerTabbedPane;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.panel.base.variability.PanelBaseVariability;
import view.panel.base.variability.PanelBaseVariants;
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
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public PanelEditVariability(ViewMenu view, Diagram diagram, Variability variability) {
        super(view);
        this.diagram     = diagram;
        this.variability = variability;
//        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.addChangeListener(new ControllerTabbedPane(this.tabbedPane));
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
            this.addPanelBaseVariability();
            this.addPanelBaseVariants();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Variability.
     */
    private void addPanelBaseVariability() {
        this.addPanel("panelBaseVariability", new PanelBaseVariability(this.viewMenu, this.diagram, this.variability));
        this.createScrollPane("scrollPanelBaseVariability",  this.getPanelBaseVariability());
        this.getScrollPanelBaseVariability().setViewportView(this.getPanelBaseVariability());
        this.tabbedPane.add("Variability", this.getScrollPanelBaseVariability());
    }
    
    /**
     * Method responsible for adding the Panel Base Variants.
     */
    private void addPanelBaseVariants() {
        this.addPanel("panelBaseVariants", new PanelBaseVariants(this.viewMenu, this.diagram, this.variability));
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
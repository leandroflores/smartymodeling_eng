package view.panel.edit.base.variability;

import controller.view.panel.edit.base.variability.ControllerTabbedPane;
import java.awt.Dimension;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.panel.base.variability.PanelBaseVariability;
import view.panel.base.variability.PanelBaseVariants;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditVariability</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Variability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-05
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
     * @param index Tab Index.
     */
    public PanelEditVariability(ViewMenu view, Diagram diagram, Variability variability, Integer index) {
        super(view);
        this.diagram     = diagram;
        this.variability = variability;
        setPreferredSize(new Dimension(200, 100));
        addComponents();
        tabbedPane.setSelectedIndex(index);
    }
    
    @Override
    protected void addPanels() {
        tabbedPane.addChangeListener(new ControllerTabbedPane(tabbedPane));
        addPanelBaseVariability();
        addPanelBaseVariants();
    }
    
    /**
     * Method responsible for adding the Panel Base Variability.
     */
    private void addPanelBaseVariability() {
        addPanel("base_variability", new PanelBaseVariability(viewMenu, diagram, variability));
        createScrollPane("base_variability", getPanelBaseVariability());
        getScrollPane("base_variability").setViewportView(getPanelBaseVariability());
        tabbedPane.add("Variability", getScrollPane("base_variability"));
    }
    
    /**
     * Method responsible for adding the Panel Base Variants.
     */
    private void addPanelBaseVariants() {
        addPanel("variants", new PanelBaseVariants(viewMenu, diagram, variability));
        createScrollPane("variants",  getPanelBaseVariants());
        getScrollPane("variants").setViewportView(getPanelBaseVariants());
        tabbedPane.add("Variants", getScrollPane("variants"));
    }
    
    /**
     * Method responsible for returning the Panel Base Variability.
     * @return Panel Base Variability.
     */
    public PanelBaseVariability getPanelBaseVariability() {
        return (PanelBaseVariability) getPanel("base_variability");
    }
    
    /**
     * Method responsible for returning the Panel Base Variants.
     * @return Panel Base Variants.
     */
    public PanelBaseVariants getPanelBaseVariants() {
        return (PanelBaseVariants) getPanel("variants");
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return variability;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
}
package view.modal.new_.base.variability;

import controller.view.modal.new_.base.variability.ControllerViewNewVariability;
import java.awt.Dimension;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.variability.Variability;
import view.main.structural.ViewMenu;
import view.panel.base.variability.PanelBaseVariability;
import view.panel.base.variability.PanelBaseVariants;
import view.modal.new_.ViewNew;
import view.panel.edit.base.variability.PanelEditVariability;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewNewVariability</b>.</p>
 * <p>Class responsible for defining the <b>New Variability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.modal.new_.base.variability.ControllerViewNewVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewVariability extends ViewNew { 
    private final Diagram diagram;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     */
    public ViewNewVariability(ViewMenu view, Diagram diagram) {
        super(view);
        this.diagram     = diagram;
        this.variability = new Variability();
        this.controller  = new ControllerViewNewVariability(this);
        this.title       = "New Variability";
        this.initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     * @param variationPoint Variation Point.
     */
    public ViewNewVariability(PanelModeling panel, Diagram diagram, Element variationPoint) {
        super(panel.getViewMenu());
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
        this.addPanelEditVariability();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Variability.
     */
    private void addPanelEditVariability() {
        this.addPanel("panelEditVariability", new PanelEditVariability(this.getViewMenu(), this.diagram, this.variability, 0));
        this.getPanel("panelEditVariability").setPreferredSize(new Dimension(500, 325));
        this.add(this.getPanel("panelEditVariability"));
    }
    
    /**
     * Method responsible for returning the Panel Base Variability.
     * @return Panel Base Variability.
     */
    public PanelBaseVariability getPanelBaseVariability() {
        return ((PanelEditVariability) this.getPanel("panelEditVariability")).getPanelBaseVariability();
    }
    
    /**
     * Method responsible for returning the Panel Base Variants.
     * @return Panel Base Variants.
     */
    public PanelBaseVariants getPanelBaseVariants() {
        return ((PanelEditVariability) this.getPanel("panelEditVariability")).getPanelBaseVariants();
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
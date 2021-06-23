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
     * @param diagram_ Diagram.
     */
    public ViewNewVariability(ViewMenu view, Diagram diagram_) {
        super(view);
        diagram     = diagram_;
        variability = new Variability();
        controller  = new ControllerViewNewVariability(this);
        title       = "New Variability";
        initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram_ Diagram.
     * @param variationPoint Variation Point.
     */
    public ViewNewVariability(PanelModeling panel, Diagram diagram_, Element variationPoint) {
        super(panel.getViewMenu());
        diagram     = diagram_;
        variability = new Variability(variationPoint);
        controller  = new ControllerViewNewVariability(this);
        title       = "New Variability";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditVariability createPanelNew() {
        return new PanelEditVariability(getViewMenu(), diagram, variability, 0);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 325);
    }
    
    @Override
    protected PanelEditVariability getPanelNew() {
        return (PanelEditVariability) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Variability.
     * @return Panel Base Variability.
     */
    public PanelBaseVariability getPanelBaseVariability() {
        return getPanelNew().getPanelBaseVariability();
    }
    
    /**
     * Method responsible for returning the Panel Base Variants.
     * @return Panel Base Variants.
     */
    public PanelBaseVariants getPanelBaseVariants() {
        return getPanelNew().getPanelBaseVariants();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return variability;
    }
}
package view.modal.edit.base.variability;

import controller.view.modal.edit.base.variability.ControllerViewEditVariability;
import java.awt.Dimension;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.modal.edit.ViewEdit;
import view.panel.base.variability.PanelBaseVariability;
import view.panel.base.variability.PanelBaseVariants;
import view.panel.edit.base.variability.PanelEditVariability;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.modal.edit.base.variability.ControllerViewEditVariability
 * @see    model.structural.base.variability.Variability
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditVariability extends ViewEdit {
    private final Diagram diagram;
    private final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public ViewEditVariability(PanelModeling panel, Diagram diagram, Variability variability) {
        super(panel.getViewMenu());
        this.diagram     = diagram;
        this.variability = variability;
        this.controller  = new ControllerViewEditVariability(this);
        this.title       = "Edit Variability Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(650, 450));
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
        this.addPanel("panelEditVariability", new PanelEditVariability(this.view, this.diagram, this.variability, 0));
        this.getPanelEditVariability().setPreferredSize(new Dimension(500, 300));
        this.add(this.getPanelEditVariability());
    }
    
    /**
     * Method responsible for returning the Panel Edit Variability.
     * @return Panel Edit Variability.
     */
    private PanelEditVariability getPanelEditVariability() {
        return (PanelEditVariability) this.getPanel("panelEditVariability");
    }
    
    /**
     * Method responsible for returning the Panel Base Variability.
     * @return Panel Base Variability.
     */
    public PanelBaseVariability getPanelBaseVariability() {
        return this.getPanelEditVariability().getPanelBaseVariability();
    }
    
    /**
     * Method responsible for returning the Panel Base Variants.
     * @return Panel Base Variants.
     */
    public PanelBaseVariants getPanelBaseVariants() {
        return this.getPanelEditVariability().getPanelBaseVariants();
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
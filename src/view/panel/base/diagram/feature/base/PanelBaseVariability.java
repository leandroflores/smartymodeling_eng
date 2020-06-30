package view.panel.base.diagram.feature.base;

import controller.view.panel.base.diagram.feature.base.ControllerPanelBaseVariability;
import java.awt.Dimension;
import java.awt.GridLayout;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Variability;
import view.panel.base.PanelBaseElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseVariability</b>.</p> 
 * <p>Class responsible for defining the <b>Variability Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-06-29
 * @see    controller.view.panel.base.diagram.feature.base.ControllerPanelBaseVariability
 * @see    model.structural.diagram.feature.base.Variability
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseVariability extends PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Feature Diagram.
     * @param variability Variability.
     */
    public PanelBaseVariability(ViewMenu view, FeatureDiagram diagram, Variability variability) {
        super(view, diagram, variability);
        this.controller = new ControllerPanelBaseVariability(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(2, 2));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Category: "));
        this.add(this.createTextFieldNoEditable("typeTextField",    this.getElement().getCategory(), 25));
        
        this.add(this.createLabel("Var. Point: "));
        this.add(this.createTextFieldNoEditable("varPointTextField", this.getElement().getVariationPoint().getName(), 25));
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }
    
    @Override
    public Variability getElement() {
        return (Variability) this.element;
    }
}
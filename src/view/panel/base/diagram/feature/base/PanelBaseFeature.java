package view.panel.base.diagram.feature.base;

import controller.view.panel.base.diagram.feature.base.ControllerPanelBaseFeature;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.panel.base.PanelBaseElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseFeature</b>.</p> 
 * <p>Class responsible for defining the <b>Feature Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-16
 * @see    controller.view.panel.base.feature.base.ControllerPanelBaseFeature
 * @see    model.structural.diagram.feature.base.Feature
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseFeature extends PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Feature Diagram.
     * @param feature Feature.
     */
    public PanelBaseFeature(ViewMenu view, FeatureDiagram diagram, Feature feature) {
        super(view, diagram, feature);
        this.controller = new ControllerPanelBaseFeature(this);
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
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.getElement().getName(), 25));        
        
        this.add(this.createLabel("Abstract: "));
        this.add(this.createCheckBox("abstractCheckBox", "", this.getElement().isAbstract()));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Abstract Check Box.
     * @return Abstract Check Box.
     */
    public JCheckBox getAbstractCheckBox() {
        return this.getCheckBox("abstractCheckBox");
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }
    
    @Override
    public Feature getElement() {
        return (Feature) this.element;
    }
}
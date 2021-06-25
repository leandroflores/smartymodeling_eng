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
 * @see    controller.view.panel.base.diagram.feature.base.ControllerPanelBaseFeature
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
        controller = new ControllerPanelBaseFeature(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(2, 2));
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", getElement().getName(), 25));        
        
        add(createLabel("Abstract: "));
        add(createCheckBox("abstract", "", getElement().isAbstract()));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Abstract Check Box.
     * @return Abstract Check Box.
     */
    public JCheckBox getAbstractCheckBox() {
        return getCheckBox("abstract");
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) diagram;
    }
    
    @Override
    public Feature getElement() {
        return (Feature) element;
    }
}
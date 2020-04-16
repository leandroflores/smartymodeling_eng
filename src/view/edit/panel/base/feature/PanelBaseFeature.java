package view.edit.panel.base.feature;

import controller.view.edit.panel.base.feature.ControllerPanelBaseFeature;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseFeature</b>.</p> 
 * <p>Class responsible for defining the <b>Feature Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-16
 * @see    controller.view.edit.panel.base.feature.ControllerPanelBaseFeature
 * @see    model.structural.diagram.feature.base.Feature
 * @see    view.Panel
 */
public final class PanelBaseFeature extends Panel {
    private final ViewMenu viewMenu;
    private final Diagram  diagram;
    private final Feature  feature;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Feature Diagram.
     * @param feature Feature.
     */
    public PanelBaseFeature(ViewMenu viewMenu, FeatureDiagram diagram, Feature feature) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.feature    = feature;
        this.controller = new ControllerPanelBaseFeature(this);
        this.setSettings();
        this.addComponents();
//        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(2, 2));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.feature.getName(), 25));        
        
        this.add(this.createLabel("Abstract: "));
        this.add(this.createCheckBox("abstractCheckBox", "", this.feature.isAbstract()));
    }
    
    /**
     * Method responsible for setting the Feature Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.feature.getName());
        this.getAbstractCheckBox().setSelected(this.feature.isAbstract());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }
    
    /**
     * Method responsible for returning the Feature.
     * @return Feature.
     */
    public Feature getFeature() {
        return this.feature;
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
}
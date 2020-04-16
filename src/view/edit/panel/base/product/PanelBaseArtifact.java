package view.edit.panel.base.product;

import controller.view.edit.panel.base.product.ControllerPanelBaseArtifact;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.product.Artifact;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseArtifact</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Artifact Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/10/2019
 * @see    controller.view.edit.panel.base.product.ControllerPanelBaseArtifact
 * @see    model.structural.base.product.Artifact
 * @see    view.Panel
 */
public final class PanelBaseArtifact extends Panel {
    private final ViewMenu viewMenu;
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param artifact Artifact.
     */
    public PanelBaseArtifact(ViewMenu view, Artifact artifact) {
        this.viewMenu   = view;
        this.artifact   = artifact;
        this.controller = new ControllerPanelBaseArtifact(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(4, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product: "));
        this.add(this.createTextFieldNoEditable("productTextField",  "", 15));
        
        this.add(this.createLabel("Instance: "));
        this.add(this.createTextFieldNoEditable("instanceTextField", "", 15));
        
        this.add(this.createLabel("Diagram: "));
        this.add(this.createTextFieldNoEditable("diagramTextField",  "", 15));
        
        this.add(this.createLabel("Element: "));
        this.add(this.createTextFieldNoEditable("elementTextField",  "", 15));
    }
    
    /**
     * Method responsible for setting the Artifact Values.
     */
    public void setValues() {
        this.getProductTextField().setText(this.artifact.getInstance().getProduct().getName());
        this.getInstanceTextField().setText(this.artifact.getInstance().getName());
        this.getDiagramTextField().setText(this.artifact.getInstance().getDiagram().toString());
        this.getElementTextField().setText(this.artifact.getElement().getName());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return this.artifact;
    }
    
    /**
     * Method responsible for returning the Product Text Field.
     * @return Product Text Field.
     */
    public JTextField getProductTextField() {
        return this.getTextField("productTextField");
    }
    
    /**
     * Method responsible for returning the Instance Text Field.
     * @return Instance Text Field.
     */
    public JTextField getInstanceTextField() {
        return this.getTextField("instanceTextField");
    }
    
    /**
     * Method responsible for returning the Diagram Text Field.
     * @return Diagram Text Field.
     */
    public JTextField getDiagramTextField() {
        return this.getTextField("diagramTextField");
    }
    
    /**
     * Method responsible for returning the Element Text Field.
     * @return Element Text Field.
     */
    public JTextField getElementTextField() {
        return this.getTextField("elementTextField");
    }
}
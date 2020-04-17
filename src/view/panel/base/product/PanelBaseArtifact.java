package view.panel.base.product;

import controller.view.panel.base.product.ControllerPanelBaseArtifact;
import java.awt.GridLayout;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import view.panel.base.PanelBase;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseArtifact</b>.</p>
 * <p>Class responsible for defining a <b>Artifact Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-25
 * @see    controller.view.panel.base.product.ControllerPanelBaseArtifact
 * @see    model.structural.base.product.Artifact
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseArtifact extends PanelBase {
    private final Artifact artifact;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param artifact Artifact.
     */
    public PanelBaseArtifact(ViewMenu view, Artifact artifact) {
        super(view);
        this.artifact   = artifact;
        this.controller = new ControllerPanelBaseArtifact(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(4, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Product: "));
        this.add(this.createTextFieldNoEditable("productTextField",  this.getInstance().getProduct().getName(), 15));
        
        this.add(this.createLabel("Instance: "));
        this.add(this.createTextFieldNoEditable("instanceTextField", this.getInstance().getName(), 15));
        
        this.add(this.createLabel("Diagram: "));
        this.add(this.createTextFieldNoEditable("diagramTextField",  this.getInstance().getDiagram().toString(), 15));
        
        this.add(this.createLabel("Element: "));
        this.add(this.createTextFieldNoEditable("elementTextField",  this.getArtifact().getElement().getName(), 15));
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.artifact.getInstance();
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return this.artifact;
    }
}
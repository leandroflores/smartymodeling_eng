package view.panel.base.product;

import controller.view.panel.base.product.ControllerPanelBaseArtifact;
import java.awt.GridLayout;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import view.panel.base.PanelBase;
import view.main.structural.ViewMenu;

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
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(4, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Product: "));
        add(createTextFieldNoEditable("product",  getInstance().getProduct().getName(), 15));
        
        add(createLabel("Instance: "));
        add(createTextFieldNoEditable("instance", getInstance().getName(), 15));
        
        add(createLabel("Diagram: "));
        add(createTextFieldNoEditable("diagram",  getInstance().getDiagram().toString(), 15));
        
        add(createLabel("Element: "));
        add(createTextFieldNoEditable("element",  getArtifact().getElement().getName(), 15));
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return artifact.getInstance();
    }
    
    /**
     * Method responsible for returning the Artifact.
     * @return Artifact.
     */
    public Artifact getArtifact() {
        return artifact;
    }
}
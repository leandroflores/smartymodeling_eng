package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.instance.ControllerPanelInstance;
import java.util.Map;
import model.controller.style.StyleComponent;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import view.panel.instance.PanelInstance;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelComponentInstance</b>.</p>
 * <p>Class responsible for defining the <b>Component Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelComponentInstance extends PanelInstance {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Component Instance.
     */
    public PanelComponentInstance(ViewMenu view, Instance instance) {
        super(view, instance);
        this.controller = new ControllerPanelInstance(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    /**
     * Method responsible for adding the Component UML of a Artifact.
     * @param artifact Artifact.
     * @param component Component UML.
     */
    protected void addArtifact(Artifact artifact, ComponentUML component) {
        this.addStyle("imageComponentStyle", this.getImageComponentStyle());
        this.addStyle(artifact.getStyleLabel(), artifact.getStyle());
        String title  = component.getName();
        mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), title, artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               vertex.setConnectable(false);
               this.graph.insertVertex(vertex, null, "", 10, 10, 20, 20, "imageComponentStyle");
        this.addArtifact(artifact, vertex);
    }
    
    /**
     * Method responsible for returning the Image Component Style.
     * @return Image Component Style.
     */
    private Map getImageComponentStyle() {
        return new StyleComponent().getImageComponentStyle();
    }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) this.instance.getDiagram();
    }
}
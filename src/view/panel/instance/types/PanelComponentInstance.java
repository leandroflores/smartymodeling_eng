package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import controller.view.panel.instance.ControllerPanelInstance;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
import view.panel.instance.PanelInstance;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelComponentInstance</b>.</p>
 * <p>Class responsible for defining the <b>Component Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    controller.view.panel.instance.event.ControllerEventMove
 * @see    controller.view.panel.instance.event.ControllerEventResize
 * @see    model.structural.diagram.ComponentDiagram
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelComponentInstance extends PanelInstance {
    private final ComponentDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     * @param diagram Component Diagram.
     */
    public PanelComponentInstance(ViewMenu view, Instance instance, ComponentDiagram diagram) {
        super(view, instance);
        this.diagram    = diagram;
        this.controller = new ControllerPanelInstance(this);
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addInstancePanel();
        this.addControllers();
    }
    
    @Override
    public void addArtifacts() {
        this.addComponentsUML();
        this.addInterfacesUML();
    }
    
    /**
     * Method responsible for adding the Instance Components.
     */
    private void addComponentsUML() {
        this.graph.getStylesheet().putCellStyle("styleImageComponent", this.getImageComponentStyle());
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.getElement() instanceof ComponentUML) {
                ComponentUML componentUML = (ComponentUML) artifact.getElement();
                this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
                String title  = componentUML.getName();
                mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), title, artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
                       vertex.setConnectable(false);
                this.graph.insertVertex(vertex, null, "", 10, 10, 20, 20, "styleImageComponent");
                this.identifiers.put(vertex, artifact.getId());
                this.objects.put(artifact.getId(), vertex);
            }
        }
    }
    
    /**
     * Method responsible for adding the Instance Interfaces.
     */
    private void addInterfacesUML() {
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.getElement() instanceof InterfaceUML) {
                InterfaceUML interfaceUML = (InterfaceUML) artifact.getElement();
                this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
                String title  = interfaceUML.getName();
                mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), title, artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
                       vertex.setConnectable(false);
                this.identifiers.put(vertex, artifact.getId());
                this.objects.put(artifact.getId(), vertex);
            }
        }
    }
    
    /**
     * Method responsible for returning the Image Component Style.
     * @return Image Component Style.
     */
    private Map getImageComponentStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/component/component.png");
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
        return style;
    }
    
    /**
     * Method responsible for returning the Component Diagram.
     * @return Component Diagram.
     */
    public ComponentDiagram getDiagram() {
        return this.diagram;
    }
}
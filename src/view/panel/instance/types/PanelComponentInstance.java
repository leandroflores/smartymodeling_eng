package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.instance.ControllerPanelInstance;
import style.element.StyleComponent;
import model.structural.base.Element;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.component.base.ComponentUML;
import view.panel.instance.PanelInstance;
import view.main.structural.ViewMenu;

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
        controller = new ControllerPanelInstance(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addArtifact(Artifact artifact, Element element) {
        if (element instanceof ComponentUML)
            addArtifact(artifact, (ComponentUML) element);
        else
            super.addArtifact(artifact, element);
    }
    
    /**
     * Method responsible for adding the Component UML of a Artifact.
     * @param artifact Artifact.
     * @param component Component UML.
     */
    protected void addArtifact(Artifact artifact, ComponentUML component) {
        addStyle(artifact.getStyleLabel(), artifact.getStyle());
        String title  = component.getName();
        mxCell vertex = (mxCell) graph.insertVertex(parent, artifact.getId(), title, artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               vertex.setConnectable(false);
               graph.insertVertex(vertex, null, "", 10, 10, 20, 20, "imageComponentStyle");
        addArtifactCell(artifact, vertex);
    }
    
    @Override
    protected void loadDefaultStyles() {
        addStyle("styleImageComponent", new StyleComponent().getImageComponentStyle());
    }
    
    @Override
    public ComponentDiagram getDiagram() {
        return (ComponentDiagram) instance.getDiagram();
    }
}
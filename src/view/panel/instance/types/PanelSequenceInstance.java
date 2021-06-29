package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.instance.ControllerPanelInstance;
import style.element.StyleSequence;
import model.structural.base.Element;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import view.panel.instance.PanelInstance;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelSequenceInstance</b>.</p>
 * <p>Class responsible for defining the <b>Sequence Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-16
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelSequenceInstance extends PanelInstance {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     */
    public PanelSequenceInstance(ViewMenu view, Instance instance) {
        super(view, instance);
        controller = new ControllerPanelInstance(this);
        addComponents();
        loadDefaultStyles();
    }
    
    @Override
    protected void addArtifact(Artifact artifact, Element element) {
        addStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        addNameCell(cell, artifact);
        addIconCell(cell, artifact);
        addEndPointCell(cell, artifact);
        addLineCell(cell, artifact);
        super.addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for adding the Icon Cell of a Artifact.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     */
    private void addIconCell(mxCell parent, Artifact artifact) {
        String style_ = artifact.getElement().getType().equals("instance") ? "classIconStyle" : "actorIconStyle";
        mxCell cell   = (mxCell) getGraph().insertVertex(parent, null, "", 2, 12, 20, 20, style_);
               cell.setConnectable(false);
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     */
    private void addNameCell(mxCell parent, Artifact artifact) {
        mxCell cell = (mxCell) graph.insertVertex(parent, artifact.getId() + "(name)", getSignature(artifact.getElement()), 2, 0, artifact.getWidth() - 4, 50, "nameStyle");
               cell.setConnectable(false);
               cell.setId(artifact.getId() + "(name)");
        identifiers.put(cell.getId(), artifact.getId());
        objects.put(artifact.getId()  + "(name)", cell);
    }
    
    /**
     * Method responsible for returning the Element Signature.
     * @param  element Element.
     * @return Element Signature.
     */
    private String getSignature(Element element) {
        if (element instanceof LifelineUML)
            return ((LifelineUML) element).getSignature();
        return ((InstanceUML) element).getSignature();
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     */
    private void addLineCell(mxCell parent, Artifact artifact) {
        Object source  = objects.get(artifact.getId() + "(name)");
        Object target  = objects.get(artifact.getId() + "(point)");
        Object newEdge = graph.insertEdge(parent, artifact.getId(), "", source, target, "lineStyle");
        mxCell newCell = (mxCell) newEdge;
        identifiers.put(newEdge, artifact.getId());
    }
    
    /**
     * Method responsible for adding the End Point Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     */
    private void addEndPointCell(mxCell parent, Artifact artifact) {
        Integer x   = (artifact.getWidth()  / 2) - 5;
        Integer y   = artifact.getHeight() - 10;
        mxCell cell = (mxCell) graph.insertVertex(parent, artifact.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
               cell.setConnectable(false);
               cell.setId(artifact.getId() + "(point)");
        identifiers.put(cell.getId(), artifact.getId());
        objects.put(artifact.getId() + "(point)", cell);
    }
    
    @Override
    protected void loadDefaultStyles() {
        StyleSequence style = new StyleSequence();
        addStyle("actorIconStyle", style.getImageStyle("usecase/actor.png"));
        addStyle("classIconStyle", style.getImageStyle("classes/class.png"));
        addStyle("nameStyle",      style.getInstanceStyle());
        addStyle("lineStyle",      style.getLineStyle());
        addStyle("pointStyle",     style.getPointStyle());
        addStyle("endPointStyle",  style.getEndPointStyle());
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) instance.getDiagram();
    }
}
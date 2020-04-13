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
import view.structural.ViewMenu;

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
        this.controller = new ControllerPanelInstance(this);
        this.addComponents();
        this.loadDefaultStyles();
    }
    
    @Override
    protected void addArtifact(Artifact artifact, Element element) {
        if (element instanceof LifelineUML)
            this.addArtifact(artifact, (LifelineUML) element);
        else if (element instanceof InstanceUML)
            this.addArtifact(artifact, (InstanceUML) element);
    }
    
    /**
     * Method responsible for adding the Lifeline UML of a Artifact.
     * @param artifact Artifact.
     * @param lifeline Lifeline UML.
     */
    protected void addArtifact(Artifact artifact, LifelineUML lifeline) {
        this.addStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        this.addNameCell(cell, artifact);
        this.addEndPointCell(cell, artifact);
        this.addLineCell(cell, artifact);
        this.graph.insertVertex(cell, null, "", 5, 10, 20, 20, "styleImageActor");
        this.addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for adding the Instance UML of a Artifact.
     * @param artifact Artifact.
     * @param instance Instance UML.
     */
    protected void addArtifact(Artifact artifact, InstanceUML instance) {
        this.addStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        this.addNameCell(cell, artifact);
        this.addEndPointCell(cell, artifact);
        this.addLineCell(cell, artifact);
        this.graph.insertVertex(cell, null, "", 5, 10, 20, 20, "styleImageClass");
        this.addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     */
    private void addNameCell(mxCell parent, Artifact artifact) {
        mxCell cell = (mxCell) this.graph.insertVertex(parent, artifact.getId() + "(name)", this.getSignature(artifact.getElement()), 2, 0, artifact.getWidth() - 4, 50, "nameStyle");
               cell.setConnectable(false);
               cell.setId(artifact.getId() + "(name)");
        this.identifiers.put(cell.getId(), artifact.getId());
        this.objects.put(artifact.getId()  + "(name)", cell);
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
        Object source  = this.objects.get(artifact.getId() + "(name)");
        Object target  = this.objects.get(artifact.getId() + "(point)");
        Object newEdge = this.graph.insertEdge(this.parent, artifact.getId(), "", source, target, "lineStyle");
        mxCell newCell = (mxCell) newEdge;
        this.identifiers.put(newEdge, artifact.getId());
    }
    
    /**
     * Method responsible for adding the End Point Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     */
    private void addEndPointCell(mxCell parent, Artifact artifact) {
        Integer x   = (artifact.getWidth()  / 2) - 5;
        Integer y   = artifact.getHeight() - 10;
        mxCell cell = (mxCell) this.graph.insertVertex(parent, artifact.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
               cell.setConnectable(false);
               cell.setId(artifact.getId() + "(point)");
        this.identifiers.put(cell.getId(), artifact.getId());
        this.objects.put(artifact.getId() + "(point)", cell);
    }
    
    /**
     * Method responsible for loading the Default Styles.
     */
    private void loadDefaultStyles() {
        StyleSequence style = new StyleSequence();
        this.getGraph().getStylesheet().putCellStyle("styleImageActor", style.getImageStyle("usecase/actor.png"));
        this.getGraph().getStylesheet().putCellStyle("styleImageClass", style.getImageStyle("classes/class.png"));
        this.getGraph().getStylesheet().putCellStyle("nameStyle", style.getNameStyle());
        this.getGraph().getStylesheet().putCellStyle("lineStyle", style.getLineStyle());
        this.getGraph().getStylesheet().putCellStyle("endPointStyle", style.getEndPointStyle());
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.instance.getDiagram();
    }
}
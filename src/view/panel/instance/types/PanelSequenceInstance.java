package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxPoint;
import controller.view.panel.instance.ControllerPanelInstance;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import model.structural.base.Element;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Relationship;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.instance.PanelInstance;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelSequenceInstance</b>.</p>
 * <p>Class responsible for defining the <b>Sequence Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  16/01/2020
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    controller.view.panel.instance.event.ControllerEventMove
 * @see    controller.view.panel.instance.event.ControllerEventResize
 * @see    model.structural.diagram.SequenceDiagram
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelSequenceInstance extends PanelInstance {
    private final SequenceDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     * @param diagram Sequence Diagram.
     */
    public PanelSequenceInstance(ViewMenu view, Instance instance, SequenceDiagram diagram) {
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
        this.loadDefaultStyles();
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.getElement() instanceof LifelineUML)
                this.addLifeline(artifact);
            else if (artifact.getElement() instanceof InstanceUML)
                this.addInstance(artifact);
        }
    }
    
    @Override
    public void addRelationships() {
        for (Relationship relationship : this.instance.getRelationshipsList()) {
            MessageUML message = (MessageUML) relationship.getAssociation();
            if (message.isLoop())
                this.addLoopMessage(relationship);
            else
                this.addMessage(relationship);
        }
    }
    
    /**
     * Method responsible for adding a Lifeline.
     * @param artifact Artifact.
     */
    private void addLifeline(Artifact artifact) {
        LifelineUML lifelineUML = (LifelineUML) artifact.getElement();
        this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
                
        mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               vertex.setConnectable(false);
        this.addNameCell(vertex, artifact);
        this.addEndPointCell(vertex, artifact);
        this.addLineCell(vertex, artifact);
        this.graph.insertVertex(vertex, null, "", 5, 10, 20, 20, "styleImageActor");

        this.identifiers.put(vertex, artifact.getId());
        this.objects.put(artifact.getId(), vertex);
    }
    
    /**
     * Method responsible for adding a Instance.
     * @param artifact Artifact.
     */
    private void addInstance(Artifact artifact) {
        InstanceUML instanceUML = (InstanceUML) artifact.getElement();
        this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
            
        mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               vertex.setConnectable(false);
        this.addNameCell(vertex, artifact);
        this.addEndPointCell(vertex, artifact);
        this.addLineCell(vertex, artifact);
        this.graph.insertVertex(vertex, null, "", 5, 10, 20, 20, "styleImageClass");

        this.identifiers.put(vertex, artifact.getId());
        this.objects.put(artifact.getId(), vertex);
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
        this.graph.getStylesheet().putCellStyle("endPointStyle", this.getEndPointStyle());
        Integer x   = (artifact.getWidth()  / 2) - 5;
        Integer y   = artifact.getHeight() - 10;
        mxCell cell = (mxCell) this.graph.insertVertex(parent, artifact.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
               cell.setConnectable(false);
               cell.setId(artifact.getId() + "(point)");
        this.identifiers.put(cell.getId(), artifact.getId());
        this.objects.put(artifact.getId() + "(point)", cell);
    }
    
    /**
     * Method responsible for adding the Relationship Points.
     * @param relationship Relationship.
     */
    private void addMessage(Relationship relationship) {
        this.graph.getStylesheet().putCellStyle(relationship.getStyleLabel(), relationship.getStyle());
        MessageUML message  = (MessageUML)  relationship.getAssociation();
        Object     source   = this.addPoint(message, this.instance.getArtifact(message.getSource()));
        Object     target   = this.addPoint(message, this.instance.getArtifact(message.getTarget()));
        mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, relationship.getId(), relationship.getTitle(), source, target, relationship.getStyleLabel());
        mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                   geometry.setPoints(relationship.getPoints());
        ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);
        this.identifiers.put(edge, relationship.getId());
        this.objects.put(relationship.getId(), edge);
    }
     
    /**
     * Method responsible for adding the Loop Relationship Points.
     * @param relationship Relationship.
     */
    private void addLoopMessage(Relationship relationship) {
        this.graph.getStylesheet().putCellStyle(relationship.getStyleLabel(), relationship.getStyle());
        MessageUML message  = (MessageUML) relationship.getAssociation();
        Object     source   = this.addPoint(message, this.instance.getArtifact(message.getSource()));
        Object     target   = this.addSelfPoint(message, this.instance.getArtifact(message.getTarget()));
        Object     object   = this.objects.get(message.getSource().getId());
        mxCell     edge     = (mxCell) this.graph.insertEdge(object, relationship.getId(), relationship.getTitle(), source, target, relationship.getStyleLabel());
        mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                   geometry.setPoints(relationship.getPoints());
        ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);
        this.identifiers.put(edge, relationship.getId());
        this.objects.put(relationship.getId(), edge);
    }
    
    /**
     * Method responsible for returning the Point Cell.
     * @param  message Message UML.
     * @param  artifact Element.
     * @return Point Cell.
     */
    private mxCell addPoint(MessageUML message, Artifact artifact) {
        this.getDefaultEdgeStyle().put("pointStyle", this.getPointStyle());
        Integer x = artifact.getWidth() / 2;
        Integer y = 80 + (message.getSequence() * 35);
        mxCell cell = (mxCell) this.graph.insertVertex(this.objects.get(artifact.getId()), null, "", x, y, 5, 5, "pointStyle");
               cell.setConnectable(false);
        return cell;
    }
    
    /**
     * Method responsible for returning the Self Point Cell.
     * @param  message Message UML.
     * @param  artifact Artifact.
     * @return Self Point Cell.
     */
    private mxCell addSelfPoint(MessageUML message, Artifact artifact) {
        this.getDefaultEdgeStyle().put("pointStyle", this.getPointStyle());
        Integer x = artifact.getWidth() / 2;
        Integer y = 80 + (message.getSequence() * 35) + 25;
        message.addDefaultPoint(new mxPoint(x + 80, y - 25), new mxPoint(x + 80, y));
        mxCell cell = (mxCell) this.graph.insertVertex(this.objects.get(artifact.getId()), null, "", x, y, 5, 5, "pointStyle");
               cell.setConnectable(false);
        return cell;
    }
    
    /**
     * Method responsible for loading the Default Styles.
     */
    private void loadDefaultStyles() {
        this.graph.getStylesheet().putCellStyle("styleImageActor", this.getImageStyle("usecase/actor.png"));
        this.graph.getStylesheet().putCellStyle("styleImageClass", this.getImageStyle("classes/class.png"));
        this.graph.getStylesheet().putCellStyle("nameStyle", this.getNameStyle());
        this.graph.getStylesheet().putCellStyle("lineStyle", this.getLineStyle());
    }
    
    /**
     * Method responsible for returning the Image Style.
     * @param  path Image Path (images/diagram/).
     * @return Image Style.
     */
    private Map getImageStyle(String path) {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/" + path);
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
        return style;
    }
    
    /**
     * Method responsible for returning the Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
        return style;
    }
    
    /**
     * Method responsible for returning Line Style.
     * @return Line Style.
     */
    public Map getLineStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_DASHED,   "0");
               style.put(mxConstants.STYLE_EDITABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        return style;
    }
    
    /**
     * Method responsible for returning the End Point Style.
     * @return End Point Style.
     */
    public Map getEndPointStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
        return style;
    }
    
    /**
     * Method responsible for returning the Point Style.
     * @return Point Style.
     */
    private Map getPointStyle() {
        Map    style = this.getEndPointStyle();
               style.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
        return style;
    }
    
    /**
     * Method responsible for returning the Sequence Diagram.
     * @return Sequence Diagram.
     */
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
}
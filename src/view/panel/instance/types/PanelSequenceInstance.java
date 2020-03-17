package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import controller.view.panel.instance.ControllerPanelInstance;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
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
     * Method responsible for adding a Lifeline.
     * @param artifact Artifact.
     */
    private void addLifeline(Artifact artifact) {
        LifelineUML lifelineUML = (LifelineUML) artifact.getElement();
        this.graph.getStylesheet().putCellStyle(lifelineUML.getStyleLabel(), lifelineUML.getStyle());
                
        mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, lifelineUML.getId(), "", lifelineUML.getPosition().x, lifelineUML.getPosition().y, lifelineUML.getSize().x, lifelineUML.getSize().y, lifelineUML.getStyleLabel());
               vertex.setConnectable(true);
        this.addNameCell(vertex, artifact);
        this.addEndPointCell(vertex, lifelineUML);
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
        this.graph.getStylesheet().putCellStyle(instanceUML.getStyleLabel(), instanceUML.getStyle());
            
        mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, instanceUML.getId(), "", instanceUML.getPosition().x, instanceUML.getPosition().y, instanceUML.getSize().x, instanceUML.getSize().y, instanceUML.getStyleLabel());
               vertex.setConnectable(true);
        this.addNameCell(vertex, artifact);
        this.addEndPointCell(vertex, instanceUML);
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
        this.objects.put(artifact.getId() + "(name)", cell);
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
     * @param element Element.
     */
    private void addEndPointCell(mxCell parent, Element element) {
        this.graph.getStylesheet().putCellStyle("endPointStyle", this.getEndPointStyle());
        Integer x   = (element.getWidth()  / 2) - 5;
        Integer y   = element.getHeight() - 10;
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(point)");
        this.identifiers.put(cell.getId(), element.getId());
        this.objects.put(element.getId() + "(point)", cell);
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
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
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
               style.put(mxConstants.STYLE_ENDARROW,   mxConstants.ARROW_SPACING);
               style.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
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
     * Method responsible for returning the Sequence Diagram.
     * @return Sequence Diagram.
     */
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
}
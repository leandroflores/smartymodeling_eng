package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.util.mxPoint;
import controller.view.panel.diagram.association.types.ControllerEventAssociationSequence;
import controller.view.panel.diagram.event.ControllerEventFocus;
import controller.view.panel.diagram.event.ControllerEventPoints;
import controller.view.panel.diagram.event.sequence.ControllerEventChange;
import controller.view.panel.diagram.event.sequence.ControllerEventEdit;
import controller.view.panel.diagram.event.sequence.ControllerEventMove;
import controller.view.panel.diagram.event.sequence.ControllerEventResize;
import controller.view.panel.diagram.types.ControllerPanelSequenceDiagram;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelSequenceOperation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelSequenceDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    controller.view.panel.diagram.types.ControllerPanelSequenceDiagram
 * @see    model.structural.diagram.SequenceDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelSequenceDiagram extends PanelDiagram {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     */
    public PanelSequenceDiagram(ViewMenu view, SequenceDiagram diagram) {
        super(view, diagram);
        this.controller = new ControllerPanelSequenceDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setClick();
    }
    
    @Override
    public void initOperationsPanel() {
        this.panel = new PanelSequenceOperation(this);
    }
    
    @Override
    public void addElements() {
        this.loadStyles();
        for (Element element : this.getDiagram().getElementsList()) {
            this.addStyle(element.getStyleLabel(), element.getStyle());
            
            mxCell cell = (mxCell) this.graph.insertVertex(this.parent, element.getId(), "", element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
                   cell.setConnectable(true);
                this.addHeaderCell(cell, element);
                this.addEndPointCell(cell, element);
                this.addLineCell(cell, element);
            this.addElement(element, cell);
        }
    }
    
    /**
     * Method responsible for adding the Header Cell of a Element.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addHeaderCell(mxCell parent, Element element) {
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(header)", "", 2, 0, element.getWidth() - 4, 90, "headerStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(header)");
            this.addStereotypeCells(cell, element);
            this.addIconCell(cell, element);
            this.addNameCell(cell, element);
            this.addStartPointCell(cell, element);
        this.identifiers.put(cell.getId(), element.getId());
        this.objects.put(element.getId() + "(header)", cell);
    }
    
    /**
     * Method responsible for adding the Icon Cell of a Element.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addIconCell(mxCell parent, Element element) {
        String style = element.getType().equals("instance") ? "classIconStyle" : "actorIconStyle";
        mxCell cell  = (mxCell) this.graph.insertVertex(parent, null, "", 2, 22, 20, 20, style);
               cell.setConnectable(false);
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addStereotypeCells(mxCell parent, Element element) {
        Integer index = 0;
        for (Stereotype stereotype : this.getDiagram().getStereotypesList(element)) {
            this.addStyle("stereotypeStyle", stereotype.getStyle());
            mxCell cell   = (mxCell) this.graph.insertVertex(parent, "LINK#" + element.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (index * 21) + 5, element.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
                   cell.setId(stereotype.getId());
                   index += 1; 
            this.addIdentifier(cell, stereotype.getId());
            this.addIdentifier(cell.getId(), element.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addNameCell(mxCell parent, Element element) {
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(name)", this.getSignature(element), 4, 68, element.getWidth() - 10, 20, "nameStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(name)");
        this.addIdentifier(cell.getId(), element.getId());
        this.objects.put(element.getId() + "(name)", cell);
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
     * Method responsible for adding the Start Point Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addStartPointCell(mxCell parent, Element element) {
        Integer x   = (element.getWidth()  / 2);
        Integer y   =  89;
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(start)", "", x, y, 1, 1, "startPointStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(start)");
        this.addIdentifier(cell.getId(), element.getId());
        this.objects.put(element.getId()  + "(start)", cell);
    }
    
    /**
     * Method responsible for adding the End Point Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addEndPointCell(mxCell parent, Element element) {
        Integer x   = (element.getWidth()  / 2) - 5;
        Integer y   = element.getHeight() - 10;
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(point)");
        this.addIdentifier(cell.getId(), element.getId());
        this.objects.put(element.getId() + "(point)", cell);
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addLineCell(mxCell parent, Element element) {
        Object source  = this.objects.get(element.getId() + "(name)");
        Object target  = this.objects.get(element.getId() + "(point)");
        mxCell edge    = (mxCell) this.graph.insertEdge(this.parent, element.getId(), "", source, target, "lineStyle");
        this.addIdentifier(edge, element.getId());
    }
    
    @Override
    public void addAssociations() {
        for (MessageUML message : this.getDiagram().getMessageList())
            this.addAssociation(message);
    }
    
    /**
     * Method responsible for adding the Association.
     * @param message Message.
     */
    private void addAssociation(MessageUML message) {
        this.addStyle(message.getStyleLabel(), message.getStyle());
        if (!message.isLoop())
            this.addMessage(message);
        else
            this.addLoopMessage(message);
    }
     
    /**
     * Method responsible for adding the Message Points.
     * @param message Message UML.
     */
    private void addMessage(MessageUML message) {
        Object     source   = this.addPoint(message, message.getSource());
        Object     target   = this.addPoint(message, message.getTarget());
        mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        mxGeometry geometry = this.getModel().getGeometry(edge);
                   geometry.setPoints(message.getPoints());
        this.getModel().setGeometry(edge, geometry);
        this.addAssociation(message, edge);
    }
     
    /**
     * Method responsible for adding the Loop Message Points.
     * @param message Message UML.
     */
    private void addLoopMessage(MessageUML message) {
        Object     source   = this.addPoint(message, message.getSource());
        Object     target   = this.addSelfPoint(message, message.getTarget());
        Object     object   = this.objects.get(message.getSource().getId());
        mxCell     edge     = (mxCell) this.graph.insertEdge(object, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        mxGeometry geometry = this.getModel().getGeometry(edge);
                   geometry.setPoints(message.getPoints());
        this.getModel().setGeometry(edge, geometry);
        this.addAssociation(message, edge);
    }
    
    /**
     * Method responsible for returning the Point Cell.
     * @param  message Message UML.
     * @param  element Element.
     * @return Point Cell.
     */
    private mxCell addPoint(MessageUML message, Element element) {
        this.getDefaultEdgeStyle().put("pointStyle", this.getPointStyle());
        Integer x = element.getWidth() / 2;
        Integer y = 80 + (message.getSequence() * 35);
        mxCell cell = (mxCell) this.graph.insertVertex(this.objects.get(element.getId()), null, "", x, y, 5, 5, "pointStyle");
               cell.setConnectable(false);
        return cell;
    }
    
    /**
     * Method responsible for returning the Self Point Cell.
     * @param  message Message UML.
     * @param  element Element.
     * @return Self Point Cell.
     */
    private mxCell addSelfPoint(MessageUML message, Element element) {
        Integer x = element.getWidth() / 2;
        Integer y = 80 + (message.getSequence() * 35) + 25;
        message.addDefaultPoint(new mxPoint(x + 80, y - 25), new mxPoint(x + 80, y));
        mxCell cell = (mxCell) this.graph.insertVertex(this.objects.get(element.getId()), null, "", x, y, 5, 5, "pointStyle");
               cell.setConnectable(false);
        return cell;
    }
    
    /**
     * Method responsible for loading the Default Styles.
     */
    private void loadStyles() {
        this.addStyle("headerStyle",     this.getHeaderStyle());
        this.addStyle("actorIconStyle",  this.getImageStyle("usecase/actor.png"));
        this.addStyle("classIconStyle",  this.getImageStyle("classes/class.png"));
        this.addStyle("nameStyle",       this.getNameStyle());
        this.addStyle("startPointStyle", this.getStartPointStyle());
        this.addStyle("lineStyle",       this.getLineStyle());
        this.addStyle("endPointStyle",   this.getEndPointStyle());
        this.addStyle("pointStyle",      this.getPointStyle());
    }
    
    /**
     * Method responsible for returning the Header Style.
     * @return Header Style.
     */
    public Map getHeaderStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   "0");
               style.put(mxConstants.STYLE_FOLDABLE,  "0");
               style.put(mxConstants.STYLE_EDITABLE,  "1");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTSIZE,  "15");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR, mxConstants.NONE);
               style.put(mxConstants.STYLE_FONTSTYLE, mxConstants.FONT_BOLD);
               style.put(mxConstants.STYLE_SHAPE,     mxConstants.SHAPE_RECTANGLE);
        return style;
    }
    
    /**
     * Method responsible for returning the Image Style.
     * @param  path Image Path (images/diagram/).
     * @return Image Style.
     */
    private Map getImageStyle(String path) {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/" + path);
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
        return style;
    }
    
    /**
     * Method responsible for returning the Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>(this.getHeaderStyle());
               style.put(mxConstants.STYLE_STROKECOLOR, mxConstants.NONE);
        return style;
    }
    
    /**
     * Method responsible for returning the Start Point Style.
     * @return Start Point Style.
     */
    public Map getStartPointStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_FONTSIZE,  "1");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
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
        Map    style = new HashMap<>(this.getStartPointStyle());
               style.put(mxConstants.STYLE_FONTSIZE, "15");
        return style;
    }
    
    /**
     * Method responsible for returning the Point Style.
     * @return Point Style.
     */
    private Map getPointStyle() {
        Map    style = this.getEndPointStyle();
               style.put(mxConstants.STYLE_FILLCOLOR, "0");
        return style;
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setMessageStyle(true);
                break;
            case 1:
                this.setMessageStyle(false);
                break;
            case 2:
            case 3:
            case 4:
                this.setDependencyStyle();
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for setting the Message Style.
     * @param flag Flag.
     */
    private void setMessageStyle(boolean flag) {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    flag ?  mxConstants.ARROW_OPEN : mxConstants.ARROW_BLOCK);
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationSequence(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.diagram;
    }
    
    @Override
    public PanelSequenceOperation getPanelOperation() {
        return (PanelSequenceOperation) this.panel;
    }
}
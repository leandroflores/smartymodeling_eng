package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationSequence;
import controller.view.panel.diagram.event.sequence.ControllerEventChange;
import controller.view.panel.diagram.event.sequence.ControllerEventEdit;
import controller.view.panel.diagram.event.sequence.ControllerEventMove;
import controller.view.panel.diagram.event.sequence.ControllerEventResize;
import controller.view.panel.diagram.types.ControllerPanelSequenceDiagram;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.structural.base.Element;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.diagram.PanelDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelSequenceDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    controller.view.panel.diagram.types.ControllerPanelSequenceDiagram
 * @see    model.structural.diagram.SequenceDiagra
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelSequenceDiagram extends PanelDiagram {
    private final SequenceDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     */
    public PanelSequenceDiagram(ViewMenu view, SequenceDiagram diagram) {
        super(view, diagram);
        this.diagram    = diagram;
        this.controller = new ControllerPanelSequenceDiagram(this);
        this.initComponents();
        this.addComponents();
        this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public void addComponents() {
        this.addOperationsPanel();
        this.addModelingPanel();
        this.addControllers();
    }
    
    @Override
    public void addOperationsPanel() {
        JPanel  panel = new JPanel();
                panel.setLayout(new FlowLayout(FlowLayout.LEFT));
                panel.add(this.createButton("clickButton",       "", "Select",             "click.png"));
                panel.add(this.createButton("lifelineButton",    "", "New Actor Lifeline", "diagram/sequence/lifeline.png"));
                panel.add(this.createButton("instanceButton",    "", "New Class Instance", "diagram/sequence/instance.png"));
                panel.add(this.createButton("variabilityButton", "", "New Variability",    "variability.png"));
                panel.add(this.createButton("editButton",        "", "Edit",               "edit.png"));
                panel.add(this.createButton("deleteButton",      "", "Delete",             "delete.png"));
                panel.add(this.createComboBox("associationComboBox", this.getAssociationItems(), 50));
       this.add(panel, BorderLayout.PAGE_START);
       this.add(new JSeparator(), BorderLayout.PAGE_END);
       this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public Object[] getAssociationItems() {
        Object[] items  = {
            this.getAssociationImage("sequence/message-a"),
            this.getAssociationImage("sequence/message-s"),
            this.getAssociationImage("dependency"),
            this.getAssociationImage("requires"),
            this.getAssociationImage("mutex")};
        return   items;
    }
    
    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getLifelineButton().setBackground(this.getDefaultColor());
        this.getInstanceButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public void addElements() {
        this.addLifelines();
        this.addInstances();
    }
    
    /**
     * Method responsible for adding the Diagram Lifelines.
     */
    private void addLifelines() {
        this.graph.getStylesheet().putCellStyle("styleImageActor", this.getImageStyle("usecase/actor.png"));
        for (LifelineUML lifeline : this.diagram.getLifelinesList()) {
            this.graph.getStylesheet().putCellStyle(lifeline.getStyleLabel(), lifeline.getStyle());
            
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, lifeline.getId(), "", lifeline.getPosition().x, lifeline.getPosition().y, lifeline.getSize().x, lifeline.getSize().y, lifeline.getStyleLabel());
                   vertex.setConnectable(true);
                this.addNameCell(vertex, lifeline);
                this.addEndPointCell(vertex, lifeline);
                this.addLineCell(vertex, lifeline);
                this.graph.insertVertex(vertex, null, "", 5, 10, 20, 20, "styleImageActor");

            this.identifiers.put(vertex, lifeline.getId());
            this.objects.put(lifeline.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Instances.
     */
    private void addInstances() {
        this.graph.getStylesheet().putCellStyle("styleImageClass", this.getImageStyle("classes/class.png"));
        for (InstanceUML instance : this.diagram.getInstancesList()) {
            this.graph.getStylesheet().putCellStyle(instance.getStyleLabel(), instance.getStyle());
            
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, instance.getId(), "", instance.getPosition().x, instance.getPosition().y, instance.getSize().x, instance.getSize().y, instance.getStyleLabel());
                   vertex.setConnectable(true);
            this.addNameCell(vertex, instance);
            this.addEndPointCell(vertex, instance);
            this.addLineCell(vertex, instance);
            this.graph.insertVertex(vertex, null, "", 5, 10, 20, 20, "styleImageClass");

            this.identifiers.put(vertex, instance.getId());
            this.objects.put(instance.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addNameCell(mxCell parent, Element element) {
        this.graph.getStylesheet().putCellStyle("nameStyle", this.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(name)", this.getSignature(element), 2, 0, element.getWidth() - 4, 50, "nameStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(name)");
        this.identifiers.put(cell.getId(), element.getId());
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
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addLineCell(mxCell parent, Element element) {
        this.graph.getStylesheet().putCellStyle("lineStyle", this.getLineStyle());
        Object source  = this.objects.get(element.getId() + "(name)");
        Object target  = this.objects.get(element.getId() + "(point)");
        Object newEdge = this.graph.insertEdge(this.parent, element.getId(), "", source, target, "lineStyle");
        mxCell newCell = (mxCell) newEdge;
        this.identifiers.put(newEdge, element.getId());
    }
    
    /**
     * Method responsible for adding the End Point Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addEndPointCell(mxCell parent, Element element) {
        this.graph.getStylesheet().putCellStyle("endPointStyle", this.getEndPointStyle());
        Integer x   = element.getWidth()  / 2;
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
    
    @Override
    public void addAssociations() {
        for (MessageUML message : this.diagram.getMessageList())
            this.addMessage(message);
    }
    
    /**
     * Method responsible for adding the Message Points.
     * @param message Message UML.
     */
    private void addMessage(MessageUML message) {
        this.graph.getStylesheet().putCellStyle(message.getStyleLabel(), message.getStyle());
        Object source = this.addPoint(message, message.getSource());
        Object target = this.addPoint(message, message.getTarget());
        Object edge   = this.graph.insertEdge(this.parent, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        mxCell cell   = (mxCell) edge;
        this.identifiers.put(cell, message.getId());
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
        Integer y = 50 + (message.getSequence() * 25);
        mxCell cell = (mxCell) this.graph.insertVertex(this.objects.get(element.getId()), null, "", x, y, 5, 5, "pointStyle");
               cell.setConnectable(false);
        return cell;
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
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,   flag ?  mxConstants.ARROW_OPEN : mxConstants.ARROW_BLOCK);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationSequence(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
     }
    
    @Override
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Lifeline Button.
     * @return Lifeline Button.
     */
    public JButton getLifelineButton() {
        return this.buttons.get("lifelineButton");
    }
    
    /**
     * Method responsible for returning the Instance Button.
     * @return Instance Button.
     */
    public JButton getInstanceButton() {
        return this.buttons.get("instanceButton");
    }
}
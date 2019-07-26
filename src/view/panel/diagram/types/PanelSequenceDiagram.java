package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationSequence;
import controller.view.panel.diagram.event.sequence.ControllerEventMove;
import controller.view.panel.diagram.event.sequence.ControllerEventResize;
import controller.view.panel.diagram.types.ControllerPanelSequenceDiagram;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.structural.diagram.SequenceDiagram;
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
                panel.add(this.createButton("clickButton",         "", "Select",             "click.png"));
                panel.add(this.createButton("lifelineActorButton", "", "New Actor Lifeline", "diagram/sequence/lifeline-actor.png"));
                panel.add(this.createButton("lifelineClassButton", "", "New Class Lifeline", "diagram/sequence/lifeline-class.png"));
                panel.add(this.createButton("variabilityButton",   "", "New Variability",    "variability.png"));
                panel.add(this.createButton("editButton",          "", "Edit",               "edit.png"));
                panel.add(this.createButton("deleteButton",        "", "Delete",             "delete.png"));
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
        this.getLifelineActorButton().setBackground(this.getDefaultColor());
        this.getLifelineClassButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public void addElements() {
        this.addLifelines();
    }
    
    /**
     * Method responsible for adding the Lifelines.
     */
    private void addLifelines() {
        for (LifelineUML lifeline : this.diagram.getLifelinesList()) {
            this.graph.getStylesheet().putCellStyle(lifeline.getStyleLabel(), lifeline.getStyle());
//            this.graph.getStylesheet().putCellStyle("styleImageActor", this.getImageActorStyle());
//            this.graph.getStylesheet().putCellStyle("styleEndPoint",   this.getEndPointStyle());
            mxCell vertex   = (mxCell) this.graph.insertVertex(this.parent, lifeline.getId(), "", lifeline.getPosition().x, lifeline.getPosition().y, lifeline.getSize().x, lifeline.getSize().y, lifeline.getStyleLabel());
                   vertex.setConnectable(true);
            this.addNameCell(vertex, lifeline);
            this.addEndPointCell(vertex, lifeline);
            this.addLineCell(vertex, lifeline);
//            mxCell line     = (mxCell) this.graph.insertVertex(this.parent, null, "", lifeline.getXCenter(), lifeline.getY() + lifeline.getHeight(), 2, 130);
//            mxCell endPoint = (mxCell) this.graph.insertVertex(this.parent, null, "", lifeline.getXCenter(), 150, 10, 10, "styleEndPoint");
//            this.graph.insertVertex(vertex,      null, "", 10, 5, 20, 20, "styleImageActor");
            
//            this.insert(vertex, lifeline);
            this.identifiers.put(vertex, lifeline.getId());
            this.objects.put(lifeline.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param lifeline Lifeline.
     */
    private void addNameCell(mxCell parent, LifelineUML lifeline) {
        this.graph.getStylesheet().putCellStyle("nameStyle", lifeline.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, lifeline.getId() + "(name)", lifeline.getName(), 0, 0, lifeline.getWidth(), 50, "nameStyle");
               cell.setConnectable(false);
               cell.setId(lifeline.getId() + "(name)");
        this.identifiers.put(cell.getId(), lifeline.getId());
        this.objects.put(lifeline.getId() + "(name)", cell);
    }
    
    /**
     * Method responsible for adding the End Point Cell.
     * @param parent Parent Cell.
     * @param lifeline Lifeline.
     */
    private void addEndPointCell(mxCell parent, LifelineUML lifeline) {
        this.graph.getStylesheet().putCellStyle("endPointStyle", lifeline.getEndPointStyle());
        Integer x   = lifeline.getWidth()  / 2;
        Integer y   = lifeline.getHeight();
        mxCell cell = (mxCell) this.graph.insertVertex(parent, lifeline.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
               cell.setConnectable(false);
               cell.setId(lifeline.getId() + "(point)");
        this.identifiers.put(cell.getId(), lifeline.getId());
        this.objects.put(lifeline.getId() + "(point)", cell);
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param lifeline Lifeline.
     */
    private void addLineCell(mxCell parent, LifelineUML lifeline) {
        this.graph.getStylesheet().putCellStyle("lineStyle", lifeline.getLineStyle());
        Object edge = this.graph.insertEdge(this.parent, lifeline.getId(), "", this.objects.get(lifeline.getId() + "(name)"), this.objects.get(lifeline.getId() + "(point)"), "lineStyle");
        mxCell cell = (mxCell) edge;
        this.identifiers.put(edge, lifeline.getId());
//        /this.identifiers.put(cell, lifeline.getId());
    }
    
    /**
     * Method responsible for returning the Image Actor Style.
     * @return Image Actor Style.
     */
    private Map getImageActorStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_IMAGE);
               style.put(mxConstants.STYLE_IMAGE, "/images/diagram/usecase/actor.png");
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
        return style;
    }
    
    /**
     * Method responsible for returning the Line Style.
     * @return Line Style.
     */
    private Map getLineStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_LINE);
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
        return style;
    }
    
    /**
     * Method responsible for returning the End Point Style.
     * @return End Point Style.
     */
    private Map getEndPointStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_CYLINDER);
               style.put(mxConstants.STYLE_MOVABLE,   0);
               style.put(mxConstants.STYLE_EDITABLE,  0);
               style.put(mxConstants.STYLE_RESIZABLE, 0);
        return style;
    }
    
    @Override
    public void addAssociations() {
        List<MessageUML> messages = this.diagram.getMessageList();
        for (int i = 0; i < messages.size(); i++) {
            MessageUML message = messages.get(i);
            this.addPoints(message);
            System.out.println(message.getId() + " " + message.getSequence());
        }
    }
    
    private void addPoints(MessageUML message) {
        this.getDefaultEdgeStyle().put(message.getStyleLabel(), message.getStyle());
        Object source = this.addPoint(message, message.getSource());
        Object target = this.addPoint(message, message.getTarget());
        Object edge   = this.graph.insertEdge(this.parent, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        mxCell cell   = (mxCell) edge;
        this.identifiers.put(edge, message.getId());
//        mxCell cell = (mxCell) this.graph.insertVertex(parent, lifeline.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
//               cell.setConnectable(false);
    }
     
    
    private mxCell addPoint(MessageUML message, LifelineUML lifeline) {
        this.getDefaultEdgeStyle().put("pointStyle", this.getPointStyle());
        Integer x = lifeline.getWidth() / 2;
        Integer y = 50 + (message.getSequence() * 25);
        mxCell cell = (mxCell) this.graph.insertVertex(this.objects.get(lifeline.getId()), null, "", x, y, 5, 5, "pointStyle");
               cell.setConnectable(false);
        return cell;
    }
    
    /**
     * Method responsible for returning the Point Style.
     * @return Point Style.
     */
    private Map getPointStyle() {
        Map    style = this.getEndPointStyle();
//         style.put(mxConstants.STYLE_FILLCOLOR,   "#E6E6FA");
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
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,  flag ?  mxConstants.ARROW_OPEN : mxConstants.ARROW_BLOCK);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FILLCOLOR,   "#FFFFFF");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    @Override
     public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationSequence(this));
//        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationActivity(this));
//        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
//        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
     }
    
    @Override
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Lifeline Actor Button.
     * @return Lifeline Actor Button.
     */
    public JButton getLifelineActorButton() {
        return this.buttons.get("lifelineActorButton");
    }
    
    /**
     * Method responsible for returning the Lifeline Class Button.
     * @return Lifeline Class Button.
     */
    public JButton getLifelineClassButton() {
        return this.buttons.get("lifelineClassButton");
    }
}
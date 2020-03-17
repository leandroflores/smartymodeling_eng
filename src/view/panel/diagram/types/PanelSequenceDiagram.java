package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
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
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import model.structural.base.Element;
import model.structural.base.Stereotype;
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
        this.loadStyles();
         for (Element element : this.diagram.getElementsList()) {
            this.graph.getStylesheet().putCellStyle(element.getStyleLabel(), element.getStyle());
            
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, element.getId(), "", element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
                   vertex.setConnectable(true);
                this.addHeaderCell(vertex, element);
                this.addEndPointCell(vertex, element);
                this.addLineCell(vertex, element);

            this.identifiers.put(vertex, element.getId());
            this.objects.put(element.getId(), vertex);
        }
//        this.addLifelines();
//        this.addInstances();
    }
    
    /**
     * Method responsible for loading the Default Styles.
     */
    private void loadStyles() {
        this.graph.getStylesheet().putCellStyle("headerStyle",     this.getHeaderStyle());
        this.graph.getStylesheet().putCellStyle("styleActorIcon",  this.getImageStyle("usecase/actor.png"));
        this.graph.getStylesheet().putCellStyle("styleClassIcon",  this.getImageStyle("classes/class.png"));
        this.graph.getStylesheet().putCellStyle("nameStyle",       this.getNameStyle());
        this.graph.getStylesheet().putCellStyle("startPointStyle", this.getStartPointStyle());
        this.graph.getStylesheet().putCellStyle("endPointStyle",   this.getEndPointStyle());
    }
    
    /**
     * Method responsible for adding the Header Cell.
     * @param parent
     * @param element 
     */
    private void addHeaderCell(mxCell parent, Element element) {
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(header)", "", 2, 0, element.getWidth() - 4, 80, "headerStyle");
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
     * Method responsible for adding the Icon Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addIconCell(mxCell parent, Element element) {
        String style = element.getType().equals("instance") ? "styleClassIcon" : "styleActorIcon";
        mxCell cell = (mxCell) this.graph.insertVertex(parent, null, "", 2, 2, 20, 20, style);
               cell.setConnectable(false);
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addStereotypeCells(mxCell parent, Element element) {
        List<Stereotype>  stereotypes = this.diagram.getStereotypesList(element);
        for (int i = 0; i < stereotypes.size(); i++) {
            Stereotype stereotype = stereotypes.get(i);
            this.graph.getStylesheet().putCellStyle("stereotypeStyle", stereotype.getStyle()); 
            mxCell     cell       = (mxCell) this.graph.insertVertex(parent, "LINK#" + element.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (i * 21) + 5, element.getWidth() - 10, 20, "stereotypeStyle");
                       cell.setConnectable(false);
                       cell.setId(stereotype.getId());
            this.identifiers.put(cell,         stereotype.getId());
            this.identifiers.put(cell.getId(), stereotype.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addNameCell(mxCell parent, Element element) {
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(name)", this.getSignature(element), 5, 58, element.getWidth() - 10, 20, "nameStyle");
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
     * Method responsible for adding the Start Point Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addStartPointCell(mxCell parent, Element element) {
        Integer x   = (element.getWidth()  / 2);
        Integer y   =  79;
        mxCell cell = (mxCell) this.graph.insertVertex(parent, element.getId() + "(start)", "", x, y, 1, 1, "startPointStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(start)");
        this.identifiers.put(cell.getId(), element.getId());
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
        this.identifiers.put(cell.getId(), element.getId());
        this.objects.put(element.getId() + "(point)", cell);
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
     * Method responsible for returning the Header Style.
     * @return Header Style.
     */
    public Map getHeaderStyle() {
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
     * Method responsible for returning the Name Style.
     * @return Name Style.
     */
    public Map getNameStyle() {
        Map    style = new HashMap<>(this.getHeaderStyle());
               style.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
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
     * Method responsible for returning the Start Point Style.
     * @return Start Point Style.
     */
    public Map getStartPointStyle() {
        Map    style = new HashMap<>();
               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_FONTSIZE,  "1");
               style.put(mxConstants.STYLE_EDITABLE,  "0");
               style.put(mxConstants.STYLE_RESIZABLE, "0");
               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               style.put(mxConstants.STYLE_FILLCOLOR,   "#000000");
               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
        return style;
    }
    
    /**
     * Method responsible for returning the End Point Style.
     * @return End Point Style.
     */
    public Map getEndPointStyle() {
        Map    style = new HashMap<>(this.getStartPointStyle());
//               style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
//               style.put(mxConstants.STYLE_VERTICAL_LABEL_POSITION, mxConstants.ALIGN_BOTTOM);
               style.put(mxConstants.STYLE_FONTSIZE, "15");
//               style.put(mxConstants.STYLE_EDITABLE,  "0");
//               style.put(mxConstants.STYLE_RESIZABLE, "0");
//               style.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
//               style.put(mxConstants.STYLE_FILLCOLOR,   "#000000");
//               style.put(mxConstants.STYLE_STROKECOLOR, "#FFFFFF");
        return style;
    }
    
    @Override
    public void addAssociations() {
        for (MessageUML message : this.diagram.getMessageList()) {
            if (message.isLoop())
                this.addLoopMessage(message);
            else
                this.addMessage(message);
        }
    }
    
    /**
     * Method responsible for adding the Message Points.
     * @param message Message UML.
     */
    private void addMessage(MessageUML message) {
        this.graph.getStylesheet().putCellStyle(message.getStyleLabel(), message.getStyle());
        Object     source   = this.addPoint(message, message.getSource());
        Object     target   = this.addPoint(message, message.getTarget());
        mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                   geometry.setPoints(message.getPoints());
        ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);
        this.identifiers.put(edge, message.getId());
        this.objects.put(message.getId(), edge);
    }
     
    /**
     * Method responsible for adding the Loop Message Points.
     * @param message Message UML.
     */
    private void addLoopMessage(MessageUML message) {
        this.graph.getStylesheet().putCellStyle(message.getStyleLabel(), message.getStyle());
        Object     source   = this.addPoint(message, message.getSource());
        Object     target   = this.addSelfPoint(message, message.getTarget());
        Object     object   = this.objects.get(message.getSource().getId());
        mxCell     edge     = (mxCell) this.graph.insertEdge(object, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                   geometry.setPoints(message.getPoints());
        ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);
        this.identifiers.put(edge, message.getId());
        this.objects.put(message.getId(), edge);
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
        this.getDefaultEdgeStyle().put("pointStyle", this.getPointStyle());
        Integer x = element.getWidth() / 2;
        Integer y = 80 + (message.getSequence() * 35) + 25;
        message.addDefaultPoint(new mxPoint(x + 80, y - 25), new mxPoint(x + 80, y));
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
        
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
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
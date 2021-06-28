package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
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
import style.element.StyleSequence;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import style.association.types.StyleSequenceAssociation;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelSequenceOperation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelSequenceDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-25
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
        controller = new ControllerPanelSequenceDiagram(this);
        setDefaultProperties();
        addComponents();
        setClick();
    }
    
    @Override
    public void initPanelOperation() {
        panel = new PanelSequenceOperation(this);
    }
    
    @Override
    public void initStyleAssociation() {
        style = new StyleSequenceAssociation();
    }
    
    @Override
    protected void addElement(Element element) {
        addStyle(element.getStyleLabel(), element.getStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, element.getId(), "", element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
               cell.setConnectable(true);
            addHeaderCell(cell, element);
            addEndPointCell(cell, element);
            addLineCell(cell, element);
        addElementCell(element, cell);
    }
    
    /**
     * Method responsible for adding the Header Cell of a Element.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addHeaderCell(mxCell parent, Element element) {
        mxCell cell = (mxCell) getGraph().insertVertex(parent, element.getId() + "(header)", "", 2, 0, element.getWidth() - 4, 90, "headerStyle");
               cell.setConnectable(false);
            addStereotypeCells(cell, element);
            addIconCell(cell, element);
            addNameCell(cell, element);
            addStartPointCell(cell, element);
        addIdentifier(cell.getId(), element.getId());
        objects.put(element.getId() + "(header)", cell);
    }
    
    /**
     * Method responsible for adding the Icon Cell of a Element.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addIconCell(mxCell parent, Element element) {
        String style_ = element.getType().equals("instance") ? "classIconStyle" : "actorIconStyle";
        mxCell cell   = (mxCell) getGraph().insertVertex(parent, null, "", 2, 22, 20, 20, style_);
               cell.setConnectable(false);
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addStereotypeCells(mxCell parent, Element element) {
        Integer index = 0;
        for (Stereotype stereotype : getDiagram().getStereotypesList(element)) {
            addStyle("stereotypeStyle", stereotype.getStyle());
            mxCell cell   = (mxCell) getGraph().insertVertex(parent, "LINK#" + element.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (index * 21) + 5, element.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
                   cell.setId(stereotype.getId());
                   index++; 
            addIdentifier(cell, stereotype.getId());
            addIdentifier(cell.getId(), element.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addNameCell(mxCell parent, Element element) {
        mxCell cell = (mxCell) getGraph().insertVertex(parent, element.getId() + "(name)", getSignature(element), 4, 68, element.getWidth() - 10, 20, "nameStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(name)");
        addIdentifier(cell.getId(), element.getId());
        objects.put(element.getId() + "(name)", cell);
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
        mxCell cell = (mxCell) getGraph().insertVertex(parent, element.getId() + "(start)", "", x, y, 1, 1, "startPointStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(start)");
        addIdentifier(cell.getId(), element.getId());
        objects.put(element.getId()  + "(start)", cell);
    }
    
    /**
     * Method responsible for adding the End Point Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addEndPointCell(mxCell parent, Element element) {
        Integer x   = (element.getWidth()  / 2) - 5;
        Integer y   = element.getHeight() - 10;
        mxCell cell = (mxCell) getGraph().insertVertex(parent, element.getId() + "(point)", "", x, y, 10, 10, "endPointStyle");
               cell.setConnectable(false);
               cell.setId(element.getId() + "(point)");
        addIdentifier(cell.getId(), element.getId());
        objects.put(element.getId() + "(point)", cell);
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param element Element.
     */
    private void addLineCell(mxCell parent, Element element) {
        Object source  = objects.get(element.getId() + "(name)");
        Object target  = objects.get(element.getId() + "(point)");
        mxCell edge    = (mxCell) getGraph().insertEdge(parent, element.getId(), "", source, target, "lineStyle");
        addIdentifier(edge, element.getId());
    }
    
    @Override
    protected void addAssociation(Association association) {
        if (association instanceof MessageUML)
            addAssociation((MessageUML) association);
        else 
            super.addAssociation(association);
    }
    
    /**
     * Method responsible for adding the Association.
     * @param message Message.
     */
    protected void addAssociation(MessageUML message) {
        addStyle(message.getStyleLabel(), message.getStyle());
        if (!message.isLoop())
            addMessage(message);
        else
            addLoopMessage(message);
    }
     
    /**
     * Method responsible for adding the Message Points.
     * @param message Message UML.
     */
    private void addMessage(MessageUML message) {
        Object source = addPoint(message, message.getSource());
        Object target = addPoint(message, message.getTarget());
        mxCell edge   = (mxCell) getGraph().insertEdge(parent, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        updatePoints(message, edge);
        addAssociationCell(message, edge);
    }
     
    /**
     * Method responsible for adding the Loop Message Points.
     * @param message Message UML.
     */
    private void addLoopMessage(MessageUML message) {
        Object source = addPoint(message, message.getSource());
        Object target = addSelfPoint(message, message.getTarget());
        Object object = objects.get(message.getSource().getId());
        mxCell edge   = (mxCell) getGraph().insertEdge(object, message.getId(), message.getTitle(), source, target, message.getStyleLabel());
        updatePoints(message, edge);
        addAssociationCell(message, edge);
    }
    
    /**
     * Method responsible for returning the Point Cell.
     * @param  message Message UML.
     * @param  element Element.
     * @return Point Cell.
     */
    private mxCell addPoint(MessageUML message, Element element) {
        Integer x = element.getWidth() / 2;
        Integer y = 80 + (message.getSequence() * 35);
        mxCell cell = (mxCell) graph.insertVertex(objects.get(element.getId()), null, "", x, y, 5, 5, "pointStyle");
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
        mxCell cell = (mxCell) graph.insertVertex(objects.get(element.getId()), null, "", x, y, 5, 5, "pointStyle");
               cell.setConnectable(false);
        return cell;
    }
    
    @Override
    protected void loadDefaultStyles() {
        StyleSequence newStyle = new StyleSequence();
        addStyle("actorIconStyle", newStyle.getImageStyle("usecase/actor.png"));
        addStyle("classIconStyle", newStyle.getImageStyle("classes/class.png"));
        addStyle("headerStyle",    newStyle.getHeaderStyle());
        addStyle("nameStyle",      newStyle.getNameStyle());
        addStyle("lineStyle",      newStyle.getLineStyle());
        addStyle("pointStyle",     newStyle.getPointStyle());
        addStyle("endPointStyle",  newStyle.getEndPointStyle());
    }
    
    @Override
    public void setStyle() {
        switch (getType()) {
            case 0:
                getStyle().setMessageStyle(getEdgeStyle(), true);
                break;
            case 1:
                getStyle().setMessageStyle(getEdgeStyle(), false);
                break;
            case 2:
            case 3:
            case 4:
                setDependencyStyle();
                break;
            default:
                break;
        }
    }
    
    @Override
     public void addControllers() {
        component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationSequence(this));
        component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        component.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventMove(this));
        component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        
        component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
     }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
    
    @Override
    public PanelSequenceOperation getPanelOperation() {
        return (PanelSequenceOperation) panel;
    }
    
    @Override
    public StyleSequenceAssociation getStyle() {
        return (StyleSequenceAssociation) style;
    }
}
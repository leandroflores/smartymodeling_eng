package controller.view.panel.diagram.event.sequence;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventEdit</b>.</p>
 * <p>Class responsible for defining the <b>Edit Events</b> in <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-04
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public class ControllerEventEdit extends mxEventSource implements mxIEventListener {
    private final PanelSequenceDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerEventEdit(PanelSequenceDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        if (getPanel().getGraph().getSelectionCell() != null) {
            Object cell = getPanel().getGraph().getSelectionCell();
            String id   = getId(cell);
            edit(cell, id);
        }
    }
    
    /**
     * Method responsible for editing the Selected Element.
     * @param element Selected Element.
     * @param id Element Id.
     */
    private void edit(Object element, String id) {
        if (getDiagram().getElement(id) instanceof LifelineUML)
            edit(element, (LifelineUML) getDiagram().getElement(id));
        else if (getDiagram().getElement(id) instanceof InstanceUML)
            edit(element, (InstanceUML) getDiagram().getElement(id));
        else if (getDiagram().getAssociation(id) instanceof MessageUML)
            edit(element, (MessageUML) getDiagram().getAssociation(id));
    }
    
    /**
     * Method responsible for editing the Lifeline UML.
     * @param object Graph Object.
     * @param lifeline Lifeline UML.
     */
    private void edit(Object object, LifelineUML lifeline) {
        mxCell cell = (mxCell) object;
               cell.setValue(lifeline.getSignature());
    }
    
    /**
     * Method responsible for editing the Instance UML.
     * @param object Graph Object.
     * @param instance Instance UML.
     */
    private void edit(Object object, InstanceUML instance) {
        mxCell cell = (mxCell) object;
               cell.setValue(instance.getSignature());
    }
    
    /**
     * Method responsible for editing the Message UML.
     * @param object Graph Object.
     * @param message Message UML.
     */
    private void edit(Object object, MessageUML message) {
        mxCell cell = (mxCell) object;
               cell.setValue(message.getName());
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (getPanel().getIdentifiers().get(cell) == null) {
            if (cell instanceof mxCell)
                return getId(((mxCell) cell).getParent());
        }
        return getPanel().getIdentifiers().get(cell);
    }
    
    /**
     * Method responsible for returning the Sequence Diagram.
     * @return Sequence Diagram.
     */
    public SequenceDiagram getDiagram() {
        return getPanel().getDiagram();
    }
    
    /**
     * Method responsible for returning the Panel Sequence Diagram.
     * @return Panel Sequence Diagram.
     */
    public PanelSequenceDiagram getPanel() {
        return panel;
    }
}
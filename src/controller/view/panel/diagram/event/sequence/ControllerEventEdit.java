package controller.view.panel.diagram.event.sequence;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventEdit</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Editing Events</b> on Sequence Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  04/10/2019
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
        if (this.panel.getGraph().getSelectionCell() != null) {
            Object cell = this.panel.getGraph().getSelectionCell();
            String id   = this.getId(cell);
            this.edit(cell, id);
        }
    }
    
    /**
     * Method responsible for editing the Selected Element.
     * @param element Selected Element.
     * @param id Element Id.
     */
    private void edit(Object element, String id) {
        if (this.panel.getDiagram().getElement(id) instanceof LifelineUML)
            this.edit(element, (LifelineUML) this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) instanceof InstanceUML)
            this.edit(element, (InstanceUML) this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getAssociation(id) instanceof MessageUML)
            this.edit(element, (MessageUML) this.panel.getDiagram().getAssociation(id));
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
        if (this.panel.getIdentifiers().get(cell) == null) {
            if (cell instanceof mxCell)
                return this.getId(((mxCell) cell).getParent());
        }
        return this.panel.getIdentifiers().get(cell);
    }
}
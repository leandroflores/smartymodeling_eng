package controller.view.panel.diagram.event.sequence;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import model.structural.diagram.usecase.base.ActorUML;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventChange</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Changing Events</b> on Sequence Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  04/10/2019
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public class ControllerEventChange extends mxEventSource implements mxIEventListener {
    private final PanelSequenceDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerEventChange(PanelSequenceDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.getId(cell);
            this.change(cell, id);
        this.panel.updateDiagram();
        this.panel.getViewMenu().getPanelProject().updateUI();
    }
    
    /**
     * Method responsible for changing the Selected Element.
     * @param object Selected Object.
     * @param id Element Id.
     */
    private void change(Object object, String id) {
        if (this.panel.getDiagram().getAssociation(id)  != null)
            this.changeAssociation(object, this.panel.getDiagram().getAssociation(id));
        else if (this.panel.getDiagram().getElement(id) instanceof LifelineUML)
            this.changeLifeline(object, (LifelineUML) this.panel.getDiagram().getElement(id));
//        else if (this.panel.getDiagram().getElement(id) instanceof MethodUML)
//            this.changeMethod(object,    (MethodUML)    this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) != null)
            this.changeElement(object,   (Element)      this.panel.getDiagram().getElement(id));
    }
    
    /**
     * Method responsible for changing the Lifeline UML.
     * @param object Graph Object.
     * @param lifeline Lifeline UML.
     */
    private void changeLifeline(Object object, LifelineUML lifeline) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (this.checkLifeline(signature)) {
            lifeline.setName(this.getName(signature, ":"));
            lifeline.setActor(this.getActor(signature));
            this.panel.getViewMenu().getPanelModeling().updateDiagram(this.panel.getDiagram());
            this.panel.getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for checking the Lifeline Signature.
     * @param  signature Lifeline Signature.
     * @return Lifeline Signature checked.
     */
    private boolean checkLifeline(String signature) {
        return     signature.contains(":")
               && !this.getName(signature, ":").equals("");
    }
    
    
    /**
     * Method responsible for changing the Element.
     * @param object Graph Object.
     * @param element Element.
     */
    private void changeElement(Object object, Element element) {
        mxCell cell = (mxCell) object;
        if (cell.getValue().toString().equals("") == false) {
            element.setName(cell.getValue().toString());
            this.panel.getViewMenu().getPanelModeling().getViewMenu().update();
            this.panel.getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for changing the Association.
     * @param object Graph Object.
     * @param association Association.
     */
    private void changeAssociation(Object object, Association association) {
        mxCell cell = (mxCell) object;
        if (association instanceof MessageUML)
            ((MessageUML) association).setMethod(this.getMethod(cell, (MessageUML) association));
    }
    
    /**
     * Method responsible for returning the Method UML.
     * @param  cell Graph Cell.
     * @param  message Message UML.
     * @return Method UML.
     */
    private MethodUML getMethod(mxCell cell, MessageUML message) {
        if (message.getTarget() instanceof InstanceUML)
            return ((InstanceUML) message.getTarget()).getClassUML().getMethodByName(cell.getValue().toString().trim());
        return null;
    }
    
    /**
     * Method responsible for returning the Name by Signature.
     * @param  signature Signature.
     * @param  symbol Symbol.
     * @return Name.
     */
    private String getName(String signature, String symbol) {
        if (signature.contains(symbol))
            return signature.substring(1, signature.trim().indexOf(symbol)).trim();
        return "";
    }
    
    /**
     * Method responsible for returning the Actor UML by Signature.
     * @param  signature Signature.
     * @return Actor UML.
     */
    private ActorUML getActor(String signature) {
        if (signature.contains(":"))
            return (ActorUML) this.panel.getDiagram().getProject().getByName("actor", signature.substring(signature.trim().indexOf(":") + 1).trim());
        return null;
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (this.panel.getIdentifiers().get(cell) != null)
            return this.panel.getIdentifiers().get(cell);
        return this.getCellId((mxCell) cell);
    }
    
    /**
     * Method responsible for returning the Cell Id by Cell.
     * @param  cell Cell.
     * @return Cell Id.
     */
    private String getCellId(mxCell cell) {
        if (cell != null) {
            if (cell.getId().endsWith("(name)"))
                return cell.getId().substring(0, cell.getId().indexOf("("));
            return cell.getId();
        }
        return "";
    }
}
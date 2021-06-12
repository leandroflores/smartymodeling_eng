package controller.view.panel.diagram.event.sequence;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import model.structural.diagram.usecase.base.ActorUML;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventChange</b>.</p>
 * <p>Class responsible for defining the <b>Change Events</b> in <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-04
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
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
        Object cell = getPanel().getGraph().getSelectionCell();
        String id   = getId(cell);
            change(cell, id);
        getPanel().getViewMenu().getPanelModeling().updateInstancePanels();
        getPanel().updateGraph();
        getPanel().getViewMenu().getPanelProject().updateUI();
    }
    
    /**
     * Method responsible for changing the Selected Element.
     * @param object Selected Object.
     * @param id Element Id.
     */
    private void change(Object object, String id) {
        if (getDiagram().getAssociation(id) != null)
            changeAssociation(object, getDiagram().getAssociation(id));
        else if (getDiagram().getElement(id) instanceof LifelineUML)
            changeLifeline(object, (LifelineUML) getDiagram().getElement(id));
        else if (getDiagram().getElement(id) instanceof InstanceUML)
            changeInstance(object, (InstanceUML) getDiagram().getElement(id));
        else if (getDiagram().getElement(id) != null)
            changeElement(object, (Element) getDiagram().getElement(id));
    }
    
    /**
     * Method responsible for changing the Lifeline UML.
     * @param object Graph Object.
     * @param lifeline Lifeline UML.
     */
    private void changeLifeline(Object object, LifelineUML lifeline) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (check(signature)) {
            lifeline.setName(getName(signature, ":"));
            lifeline.setActor(getActor(signature));
            getPanel().getViewMenu().getPanelModeling().updateDiagram(getDiagram());
            getPanel().getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for changing the Instance UML.
     * @param object Graph Object.
     * @param instance Instance UML.
     */
    private void changeInstance(Object object, InstanceUML instance) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (check(signature)) {
            instance.setName(getName(signature, ":"));
            instance.setClassUML(getClass(signature));
            getPanel().getViewMenu().getPanelModeling().updateDiagram(getDiagram());
            getPanel().getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for checking the Lifeline Signature.
     * @param  signature Lifeline Signature.
     * @return Lifeline Signature checked.
     */
    private boolean check(String signature) {
        return     signature.contains(":")
               && !getName(signature, ":").equals("");
    }
    
    
    /**
     * Method responsible for changing the Element.
     * @param object Graph Object.
     * @param element Element.
     */
    private void changeElement(Object object, Element element) {
        mxCell cell = (mxCell) object;
        if (!cell.getValue().toString().equals("")) {
            element.setName(cell.getValue().toString());
            getPanel().getViewMenu().getPanelModeling().getViewMenu().update();
            getPanel().getViewMenu().setSave(false);
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
            ((MessageUML) association).setMethod(getMethod(cell, (MessageUML) association));
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
            return signature.substring(0, signature.trim().indexOf(symbol)).trim();
        return "";
    }
    
    /**
     * Method responsible for returning the Actor UML by Signature.
     * @param  signature Signature.
     * @return Actor UML.
     */
    private ActorUML getActor(String signature) {
        if (signature.contains(":"))
            return (ActorUML) getDiagram().getProject().getByName("actor", signature.substring(signature.trim().indexOf(":") + 1).trim());
        return null;
    }
    
    /**
     * Method responsible for returning the Class UML by Signature.
     * @param  signature Signature.
     * @return Class UML.
     */
    private ClassUML getClass(String signature) {
        if (signature.contains(":"))
            return (ClassUML) getDiagram().getProject().getByName("class", signature.substring(signature.trim().indexOf(":") + 1).trim());
        return null;
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (getPanel().getIdentifiers().get(cell) != null)
            return getPanel().getIdentifiers().get(cell);
        return getCellId((mxCell) cell);
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
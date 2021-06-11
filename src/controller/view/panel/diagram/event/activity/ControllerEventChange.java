package controller.view.panel.diagram.event.activity;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.activity.base.association.FlowUML;
import view.panel.diagram.types.PanelActivityDiagram;

/**
 * <p>Class of Controller <b>ControllerEventChange</b>.</p>
 * <p>Class responsible for defining the <b>Change Events</b> in <b>Activity Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-03
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelActivityDiagram
 */
public class ControllerEventChange extends mxEventSource implements mxIEventListener {
    private final PanelActivityDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Activity Diagram.
     */
    public ControllerEventChange(PanelActivityDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = getPanel().getGraph().getSelectionCell();
        String id   = getId(cell);
            change(cell, id);
        getPanel().updateGraph();
        getPanel().getViewMenu().getPanelProject().updateUI();
    }
    
    /**
     * Method responsible for changing the Selected Element.
     * @param object Selected Object.
     * @param id Element Id.
     */
    private void change(Object object, String id) {
        if (getPanel().getDiagram().getElement(id) != null)
            changeElement(object, (Element) getPanel().getDiagram().getElement(id));
        else if (getPanel().getDiagram().getAssociation(id)  != null)
            changeAssociation(object, getPanel().getDiagram().getAssociation(id));
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
        mxCell cell  = (mxCell) object;
        String value = cell.getValue().toString();
        if (association instanceof FlowUML) {
            FlowUML flow = (FlowUML) association;
                    flow.setGuard(getGuard(value));
                    flow.setAction(getAction(value));
                    flow.setWeight(getWeight(value));
        }
    }
    
    /**
     * Method responsible for checking the Guard.
     * @param  signature Signature.
     * @return Guard checked.
     */
    private boolean checkGuard(String signature) {
        return    signature.contains("[")
               && signature.contains("]")
               && signature. indexOf("[") < signature.indexOf("]");
    }
    
    /**
     * Method responsible for returning the Guard.
     * @param  signature Signature.
     * @return Guard.
     */
    private String getGuard(String signature) {
        if (checkGuard(signature))
            return signature.substring(signature.indexOf("[") + 1, 
                                       signature.indexOf("]")).trim();
        return "";
    }
    
    /**
     * Method responsible for checking the Action.
     * @param  signature Signature.
     * @return Action checked.
     */
    private boolean checkAction(String signature) {
        return      signature.contains("/")
               && (!signature.contains("{") || (signature.contains("{") 
                 && signature.indexOf ("{")  >  signature.indexOf("/")));
    }
    
    /**
     * Method responsible for returning the Action.
     * @param  signature Signature.
     * @return Action.
     */
    private String getAction(String signature) {
        if (checkAction(signature)) {
            if (!signature.contains("{"))
                return signature.substring(signature.indexOf("/"));
            return signature.substring(signature.indexOf("/") + 1, 
                                       signature.indexOf("{")).trim();
        }
        return "";
    }
    
    /**
     * Method responsible for checking the Weight.
     * @param  signature Signature.
     * @return Weight checked.
     */
    private boolean checkWeight(String signature) {
        return    signature.contains("{")
               && signature.contains("}")
               && signature. indexOf("{") < signature.indexOf("}");
    }
    
    /**
     * Method responsible for returning the Weight.
     * @param  signature Signature.
     * @return Weight.
     */
    private String getWeight(String signature) {
        if (checkWeight(signature))
            return signature.substring(signature.indexOf("{") + 1, 
                                       signature.indexOf("}")).trim();
        return "";
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
     * Method responsible for returning the Panel Activity Diagram.
     * @return Panel Activity Diagram.
     */
    public PanelActivityDiagram getPanel() {
        return panel;
    }
}
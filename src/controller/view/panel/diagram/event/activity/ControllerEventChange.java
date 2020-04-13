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
 * <p>Class responsible for defining the <b>Controller</b> for <b>Changing Events</b> on Activity Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/11/2019
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
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.getId(cell);
            this.change(cell, id);
        this.panel.updateGraph();
        this.panel.getViewMenu().getPanelProject().updateUI();
    }
    
    /**
     * Method responsible for changing the Selected Element.
     * @param object Selected Object.
     * @param id Element Id.
     */
    private void change(Object object, String id) {
        if (this.panel.getDiagram().getElement(id) != null)
            this.changeElement(object, (Element) this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getAssociation(id)  != null)
            this.changeAssociation(object, this.panel.getDiagram().getAssociation(id));
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
        mxCell cell  = (mxCell) object;
        String value = cell.getValue().toString();
        if (association instanceof FlowUML) {
            FlowUML flow = (FlowUML) association;
                    flow.setGuard(this.getGuard(value));
                    flow.setAction(this.getAction(value));
                    flow.setWeight(this.getWeight(value));
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
        if (this.checkGuard(signature))
            return signature.substring(signature.indexOf("[") + 1, signature.indexOf("]")).trim();
        return "";
    }
    
    /**
     * Method responsible for checking the Action.
     * @param  signature Signature.
     * @return Action checked.
     */
    private boolean checkAction(String signature) {
        return         signature.contains("/")
               &&  (  !signature.contains("{")
                   || (signature.contains("{") 
                   &&  signature.indexOf ("{") > signature.indexOf("/")));
    }
    
    /**
     * Method responsible for returning the Action.
     * @param  signature Signature.
     * @return Action.
     */
    private String getAction(String signature) {
        if (this.checkAction(signature)) {
            if (!signature.contains("{"))
                return signature.substring(signature.indexOf("/"));
            return signature.substring(signature.indexOf("/") + 1, signature.indexOf("{")).trim();
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
        if (this.checkWeight(signature))
            return signature.substring(signature.indexOf("{") + 1, signature.indexOf("}")).trim();
        return "";
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
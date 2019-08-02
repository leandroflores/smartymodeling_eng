package controller.view.panel.diagram.event.classes;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Events</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventMove(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.panel.getIdentifiers().get(cell);
        this.move(id, event);
    }
    
    /**
     * Method responsible for moving the Selected Element.
     * @param object Graph Object.
     * @param id Element Id.
     * @param event Event.
     */
    private void move(String id, mxEventObject event) {
        if (id != null)
            this.moveCardinality(id, event);
//        if (this.panel.getDiagram().getElement(id) != null)
//            this.moveElement(this.panel.getDiagram().getElement(id), event);
//        else if (id != null)
//            this.moveCardinality(id, event);
    }
    
    /**
     * Method responsible for moving the Element.
     * @param element Element.
     * @param event Event.
     */
    private void moveElement(Element element, mxEventObject event) {
        element.dx(((Double) event.getProperty("dx")).intValue());
        element.dy(((Double) event.getProperty("dy")).intValue());
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for changing the Cardinality.
     * @param id Association Id.
     * @param event Event.
     */
    private void moveCardinality(String id, mxEventObject event) {
        if (id.endsWith("(source)"))
            this.moveSourceCardinality(id, event);
        else if (id.endsWith("(target)"))
            this.moveTargetCardinality(id, event);
    }
    
    /**
     * Method responsible for changing the Source Cardinality.
     * @param id Association Id.
     * @param event Event.
     */
    private void moveSourceCardinality(String id, mxEventObject event) {
        AssociationUML associationUML = (AssociationUML) this.panel.getDiagram().getAssociation(id.substring(0, id.indexOf("(")));
                       associationUML.dxSource(((Double) event.getProperty("dx")).intValue());
                       associationUML.dySource(((Double) event.getProperty("dy")).intValue());
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for changing the Target Cardinality.
     * @param id Association Id.
     * @param event Event.
     */
    private void moveTargetCardinality(String id, mxEventObject event) {
        AssociationUML associationUML = (AssociationUML) this.panel.getDiagram().getAssociation(id.substring(0, id.indexOf("(")));
                       associationUML.dxTarget(((Double) event.getProperty("dx")).intValue());
                       associationUML.dyTarget(((Double) event.getProperty("dy")).intValue());
        this.panel.getViewMenu().setSave(false);               
    }
}
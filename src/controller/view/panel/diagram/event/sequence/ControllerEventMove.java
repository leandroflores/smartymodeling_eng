package controller.view.panel.diagram.event.sequence;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Events</b> on Sequence Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelSequenceDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerEventMove(PanelSequenceDiagram panel) {
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
        if (this.panel.getDiagram().getElement(id) != null)
            this.moveElement(this.panel.getDiagram().getElement(id), event);
    }
    
    /**
     * Method responsible for moving the Element.
     * @param element Element.
     * @param event Event.
     */
    private void moveElement(Element element, mxEventObject event) {
        element.dx(((Double) event.getProperty("dx")).intValue());
        element.dy(((Double) event.getProperty("dy")).intValue());
        this.panel.getDiagram().updateY(element.getY());
        this.panel.getViewMenu().setSave(false); 
        this.panel.updateDiagram();
   }
}
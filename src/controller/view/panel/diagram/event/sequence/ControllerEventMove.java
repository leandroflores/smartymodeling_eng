package controller.view.panel.diagram.event.sequence;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.SequenceDiagram;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Move Events</b> in <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-25
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
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
        Object cell = getPanel().getGraph().getSelectionCell();
        String id   = getPanel().getIdentifiers().get(cell);
        move(id, event);
    }
    
    /**
     * Method responsible for moving the Selected Element.
     * @param object Graph Object.
     * @param id Element Id.
     * @param event Event.
     */
    private void move(String id, mxEventObject event) {
        if (getDiagram().getElement(id) != null)
            moveElement(getDiagram().getElement(id), event);
    }
    
    /**
     * Method responsible for moving the Element.
     * @param element Element.
     * @param event Event.
     */
    private void moveElement(Element element, mxEventObject event) {
        element.dx(((Double) event.getProperty("dx")).intValue());
        element.dy(((Double) event.getProperty("dy")).intValue());
        getDiagram().updateY(element.getY());
        getPanel().getViewMenu().setSave(false); 
        getPanel().updateGraph();
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
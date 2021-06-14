package controller.view.panel.diagram.event;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Move Event</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventMove(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = getPanel().getGraph().getSelectionCell();
        String id   = getPanel().getIdentifiers().get(cell);
        if (getPanel().getDiagram().getElement(id) != null)
            move(getPanel().getDiagram().getElement(id), event);
        else if (getPanel().getDiagram().getAssociation(id) != null)
            move(getPanel().getDiagram().getAssociation(id));
    }
    
    /**
     * Method responsible for moving a Element.
     * @param element Element.
     * @param event Graph Event.
     */
    private void move(Element element, mxEventObject event) {
        element.dx(((Double) event.getProperty("dx")).intValue());
        element.dy(((Double) event.getProperty("dy")).intValue());
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for moving a Association.
     * @param association Association.
     */
    private void move(Association association) {
        mxGeometry geometry = ((mxGraphModel) (getPanel().getGraph().getModel())).getGeometry(getPanel().getObjects().get(association.getId()));
                   association.setPoints(geometry.getPoints());
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanel() {
        return panel;
    }
}
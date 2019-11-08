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
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Modeling Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
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
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.panel.getIdentifiers().get(cell);
        if (this.panel.getDiagram().getElement(id) != null)
            this.move(this.panel.getDiagram().getElement(id), event);
        else if (this.panel.getDiagram().getAssociation(id) != null)
            this.move(this.panel.getDiagram().getAssociation(id));
    }
    
    /**
     * Method responsible for moving a Element.
     * @param element Element.
     * @param event Graph Event.
     */
    private void move(Element element, mxEventObject event) {
        element.dx(((Double) event.getProperty("dx")).intValue());
        element.dy(((Double) event.getProperty("dy")).intValue());
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for moving a Association.
     * @param association Association.
     */
    private void move(Association association) {
        mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(this.panel.getObjects().get(association.getId()));
                   association.setPoints(geometry.getPoints());
        this.panel.getViewMenu().setSave(false);
    }
}
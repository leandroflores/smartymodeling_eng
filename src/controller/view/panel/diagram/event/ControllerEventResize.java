package controller.view.panel.diagram.event;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxRectangle;
import model.structural.base.Element;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventResize</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Resizing Panel Modeling</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventResize extends mxEventSource implements mxIEventListener {
    private final PanelDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventResize(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object  cell    = this.panel.getGraph().getSelectionCell();
        String  id      = this.panel.getIdentifiers().get(cell);
        Element element = this.panel.getDiagram().getElement(id);
        if (element != null) {
            mxRectangle coordinates = this.panel.getGraph().getCellGeometry(cell);
                            element.setSize(new Double(coordinates.getHeight()).intValue(), new Double(coordinates.getWidth()).intValue());
            this.panel.getViewMenu().setSave(false);
            this.panel.updateDiagram();
        }
    }
}
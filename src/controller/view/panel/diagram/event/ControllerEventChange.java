package controller.view.panel.diagram.event;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventChange</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Change Event</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventChange extends mxEventSource implements mxIEventListener {
    private final PanelDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel_ Panel Diagram.
     */
    public ControllerEventChange(PanelDiagram panel_) {
        panel = panel_;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object  cell    = getPanel().getGraph().getSelectionCell();
        String  id      = getPanel().getIdentifiers().get(cell);
        Element element = getPanel().getDiagram().getElement(id);
        if (element != null) {
            mxCell vertex = (mxCell) cell;
            element.setName(vertex.getValue().toString());
            getPanel().getViewMenu().getPanelModeling().updateInstancePanels();
        }
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
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
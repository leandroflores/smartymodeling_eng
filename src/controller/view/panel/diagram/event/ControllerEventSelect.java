package controller.view.panel.diagram.event;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventSelect</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Select Event</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-06
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventSelect extends mxEventSource implements mxIEventListener {
    private final PanelDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventSelect(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        if (getPanel().getGraph().getSelectionCell() != null) {
            if (getPanel().getGraph().getSelectionCell() instanceof mxCell) {    
                mxCell cell = (mxCell) getPanel().getGraph().getSelectionCell();
                String id   = getPanel().getIdentifiers().get(cell);
                select(cell, id);
            }
        }
    }
    
    /**
     * Method responsible for selecting the Element.
     * @param cell Graph Cell.
     * @param id Element Id.
     */
    private void select(mxCell cell, String id) {
//        System.out.println("Id..: " + id);
//        System.out.println("Cell: " + cell);
//        System.out.println("Cell: " + cell.getIdentifier());
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanel() {
        return panel;
    }
}
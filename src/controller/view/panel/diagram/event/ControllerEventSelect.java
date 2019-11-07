package controller.view.panel.diagram.event;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventSelect</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Selecting Panel Modeling</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/11/2019
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
//        System.out.println("AA");
        if (this.panel.getGraph().getSelectionCell() != null) {
            if (this.panel.getGraph().getSelectionCell() instanceof mxCell) {
//                System.out.println("Properties: " + event.getProperty("dx"));
                
                mxCell cell = (mxCell) this.panel.getGraph().getSelectionCell();
                String id   = this.panel.getIdentifiers().get(cell);
                this.select(cell, id);
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
//        System.out.println("Cell: " + cell.getId());
    }
}
package controller.view.panel.diagram.event;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventEdit</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Editing Modeling Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventEdit extends mxEventSource implements mxIEventListener {
    private final PanelDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventEdit(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject evento) {
        Object   cell   = this.panel.getGraph().getSelectionCell();
        String   id     = this.panel.getIdentifiers().get(cell);
        Element element = this.panel.getDiagram().getElement(id);
        if (element != null) {
            mxCell celula = (mxCell) cell;
                   celula.setValue(element.getName());
        }
    }
}
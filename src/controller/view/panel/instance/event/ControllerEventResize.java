package controller.view.panel.instance.event;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxRectangle;
import model.structural.base.product.Artifact;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerEventResize</b>.</p>
 * <p>Class responsible for defining the <b>Resize Events</b> in <b>Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.instance.PanelInstance
 */
public class ControllerEventResize extends mxEventSource implements mxIEventListener {
    private final PanelInstance panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Instance.
     */
    public ControllerEventResize(PanelInstance panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object   cell     = getPanel().getGraph().getSelectionCell();
        String   id       = getPanel().getIdentifiers().get(cell);
        Artifact artifact = getPanel().getInstance().getArtifact(id);
        if (artifact != null) {
            mxRectangle coordinates = getPanel().getGraph().getCellGeometry(cell);
                            artifact.setSize(new Double(coordinates.getHeight()).intValue(), new Double(coordinates.getWidth()).intValue());
            getPanel().getViewMenu().setSave(false);
            getPanel().updateGraph();
        }
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    public PanelInstance getPanel() {
        return panel;
    }
}
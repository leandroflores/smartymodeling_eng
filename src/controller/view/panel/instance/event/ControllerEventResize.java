package controller.view.panel.instance.event;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import com.mxgraph.util.mxRectangle;
import model.structural.base.product.Artifact;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerEventResize</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Resizing Panel Instance</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
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
        Object   cell     = this.panel.getGraph().getSelectionCell();
        String   id       = this.panel.getIdentifiers().get(cell);
        Artifact artifact = this.panel.getInstance().getArtifact(id);
        if (artifact != null) {
            mxRectangle coordinates = this.panel.getGraph().getCellGeometry(cell);
                            artifact.setSize(new Double(coordinates.getHeight()).intValue(), new Double(coordinates.getWidth()).intValue());
            this.panel.getViewMenu().setSave(false);
            this.panel.updateInstance();
        }
    }
}
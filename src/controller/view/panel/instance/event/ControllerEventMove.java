package controller.view.panel.instance.event;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.product.Artefact;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    view.panel.instance.PanelInstance
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelInstance panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Instance.
     */
    public ControllerEventMove(PanelInstance panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object   cell     = this.panel.getGraph().getSelectionCell();
        String   id       = this.panel.getIdentifiers().get(cell);
        Artefact artefact = this.panel.getInstance().getArtefact(id);
        if (artefact != null) {
            artefact.dx(((Double) event.getProperty("dx")).intValue());
            artefact.dy(((Double) event.getProperty("dy")).intValue());
            this.panel.getViewMenu().setSave(false);
        }
    }
}
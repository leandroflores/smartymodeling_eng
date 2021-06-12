package controller.view.panel.diagram.event.feature;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.feature.base.Feature;
import view.panel.diagram.types.PanelFeatureDiagram;

/**
 * <p>Class of Controller <b>ControllerEventResize</b>.</p>
 * <p>Class responsible for defining the <b>Resize Events</b> in <b>Feature Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-08
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelFeatureDiagram
 */
public class ControllerEventResize extends mxEventSource implements mxIEventListener {
    private final PanelFeatureDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Diagram.
     */
    public ControllerEventResize(PanelFeatureDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject evento) {
        Object  cell    = getPanel().getGraph().getSelectionCell();
        String  id      = getId(cell);
        Element element = getPanel().getDiagram().getElement(id);
        if (element != null && element instanceof Feature)
            resize((Feature) element, cell);
    }
    
    /**
     * Method responsible for returning the Cell Id.
     * @param  cell Graph Cell.
     * @return Cell Id.
     */
    private String getId(Object cell) {
        if ((cell != null) && (cell instanceof mxCell))
            return ((mxCell) cell).getId();
        return "";
    }
    
    /**
     * Method responsible for resizing the Selected Feature.
     * @param feature Selected Feature.
     * @param cell Graph Cell.
     */
    private void resize(Feature feature, Object cell) {
        Integer height = new Double(getPanel().getGraph().getCellGeometry(cell).getHeight()).intValue();
        Integer width  = new Double(getPanel().getGraph().getCellGeometry(cell).getWidth()).intValue();
                feature.setHeight(height > feature.getMinHeight() ? height : feature.getMinHeight());
                feature.setWidth( width  > feature.getMinWidth()  ?  width : feature.getMinWidth());
        getPanel().getViewMenu().setSave(false);
        getPanel().updateGraph();
    }
    
    /**
     * Method responsible for returning the Panel Feature Diagram.
     * @return Panel Feature Diagram.
     */
    public PanelFeatureDiagram getPanel() {
        return panel;
    }
}
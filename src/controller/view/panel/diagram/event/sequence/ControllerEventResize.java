package controller.view.panel.diagram.event.sequence;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventResize</b>.</p>
 * <p>Class responsible for defining the <b>Resize Events</b> in <b>Sequence Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-25
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelSequenceDiagram
 */
public class ControllerEventResize extends mxEventSource implements mxIEventListener {
    private final PanelSequenceDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Sequence Diagram.
     */
    public ControllerEventResize(PanelSequenceDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject evento) {
        Object  cell    = getPanel().getGraph().getSelectionCell();
        String  id      = getId(cell);
        Element element = getDiagram().getElement(id);
        if (element != null)
            resize(element, cell);
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
     * Method responsible for resizing the Selected Element.
     * @param element Selected Object.
     * @param cell Graph Cell.
     */
    private void resize(Element element, Object cell) {
        if (element instanceof LifelineUML)
            resize((LifelineUML) element, cell);
        else if (element instanceof InstanceUML)
            resize((InstanceUML) element, cell);
    }
    
    /**
     * Method responsible for resizing the Instance UML.
     * @param lifelineUML LifelineUML.
     * @param cell Graph Cell.
     */
    private void resize(LifelineUML lifelineUML, Object cell) {
        Integer height = new Double(getPanel().getGraph().getCellGeometry(cell).getHeight()).intValue();
        Integer width  = new Double(getPanel().getGraph().getCellGeometry(cell).getWidth()).intValue();
        if (height > getDiagram().getMinHeigth()) {
            lifelineUML.setHeight(height);
            lifelineUML.setWidth( width  >  lifelineUML.getNameSize() ?  width :  lifelineUML.getNameSize());
            getDiagram().updateHeight(height);
            getPanel().updateGraph();
        }
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for resizing the Instance UML.
     * @param instanceUML Instance UML.
     * @param cell Graph Cell.
     */
    private void resize(InstanceUML instanceUML, Object cell) {
        Integer height = new Double(getPanel().getGraph().getCellGeometry(cell).getHeight()).intValue();
        Integer width  = new Double(getPanel().getGraph().getCellGeometry(cell).getWidth()).intValue();
        if (height > getDiagram().getMinHeigth()) {
            instanceUML.setHeight(height);
            instanceUML.setWidth( width  >  instanceUML.getSignatureSize() ?  width :  instanceUML.getSignatureSize());
            getDiagram().updateHeight(height);
            getPanel().updateGraph();
        }
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for returning the Sequence Diagram.
     * @return Sequence Diagram.
     */
    public SequenceDiagram getDiagram() {
        return getPanel().getDiagram();
    } 
    
    /**
     * Method responsible for returning the Panel Sequence Diagram.
     * @return Panel Sequence Diagram.
     */
    public PanelSequenceDiagram getPanel() {
        return panel;
    }
}
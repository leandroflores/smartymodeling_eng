package controller.view.panel.diagram.event.sequence;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import view.panel.diagram.types.PanelSequenceDiagram;

/**
 * <p>Class of Controller <b>ControllerEventResize</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Resizing Panel Modeling</b> on Sequence Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  25/07/2019
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
        Object  cell    = this.panel.getGraph().getSelectionCell();
        String  id      = this.getId(cell);
        Element element = this.panel.getDiagram().getElement(id);
        if (element != null)
            this.resize(element, cell);
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
            this.resize((LifelineUML) element, cell);
        else if (element instanceof InstanceUML)
            this.resize((InstanceUML) element, cell);
    }
    
    /**
     * Method responsible for resizing the Instance UML.
     * @param lifelineUML LifelineUML.
     * @param cell Graph Cell.
     */
    private void resize(LifelineUML lifelineUML, Object cell) {
        Integer height = new Double(this.panel.getGraph().getCellGeometry(cell).getHeight()).intValue();
        Integer width  = new Double(this.panel.getGraph().getCellGeometry(cell).getWidth()).intValue();
        if (height > this.panel.getDiagram().getMinHeigth()) {
            lifelineUML.setHeight(height);
            lifelineUML.setWidth( width  >  lifelineUML.getNameSize() ?  width :  lifelineUML.getNameSize());
            this.panel.getDiagram().updateHeight(height);
            this.panel.updateGraph();
        }
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for resizing the Instance UML.
     * @param instanceUML Instance UML.
     * @param cell Graph Cell.
     */
    private void resize(InstanceUML instanceUML, Object cell) {
        Integer height = new Double(this.panel.getGraph().getCellGeometry(cell).getHeight()).intValue();
        Integer width  = new Double(this.panel.getGraph().getCellGeometry(cell).getWidth()).intValue();
        if (height > this.panel.getDiagram().getMinHeigth()) {
            instanceUML.setHeight(height);
            instanceUML.setWidth( width  >  instanceUML.getSignatureSize() ?  width :  instanceUML.getSignatureSize());
            this.panel.getDiagram().updateHeight(height);
            this.panel.updateGraph();
        }
        this.panel.getViewMenu().setSave(false);
    }
}
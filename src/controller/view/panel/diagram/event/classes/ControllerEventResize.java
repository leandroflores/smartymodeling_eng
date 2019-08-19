package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventResize</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Resizing Panel Modeling</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  10/06/2019
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventResize extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventResize(PanelClassDiagram panel) {
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
        if (element instanceof Entity)
            this.resize((Entity)     element, cell);
        else if (element instanceof PackageUML)
            this.resize((PackageUML) element, cell);
    }
    
    /**
     * Method responsible for resizing the Entity.
     * @param entity Entity.
     * @param cell Graph Cell.
     */
    private void resize(Entity entity, Object cell) {
        Integer height = new Double(this.panel.getGraph().getCellGeometry(cell).getHeight()).intValue();
        Integer width  = new Double(this.panel.getGraph().getCellGeometry(cell).getWidth()).intValue();
                entity.setHeight(height > entity.getMinHeight() ? height : entity.getMinHeight());
                entity.setWidth( width  >  entity.getMinWidth() ?  width :  entity.getMinWidth());
                entity.updatePackageSize();
        this.panel.getViewMenu().setSave(false);
        this.panel.updateDiagram();
    }
    
    /**
     * Method responsible for resizing the Package UML.
     * @param packageUML Package UML.
     * @param cell Graph Cell.
     */
    private void resize(PackageUML packageUML, Object cell) {
        Integer height = new Double(this.panel.getGraph().getCellGeometry(cell).getHeight()).intValue();
        Integer width  = new Double(this.panel.getGraph().getCellGeometry(cell).getWidth()).intValue();
                packageUML.setHeight(height > packageUML.getMinHeight() ? height : packageUML.getMinHeight());
                packageUML.setWidth( width  > packageUML.getMinWidth()  ?  width : packageUML.getMinWidth());
                packageUML.updateParentSize();
        this.panel.getViewMenu().setSave(false);
        this.panel.updateDiagram();
    }
}
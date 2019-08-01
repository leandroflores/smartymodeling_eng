package controller.view.panel.diagram.event.classes;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventGroup</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Grouping Panel Modeling</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  10/06/2019
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventGroup extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventGroup(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        mxCell  cell    = (mxCell) this.panel.getGraph().getSelectionCell();
        String  parent  = this.getId(this.getCellId(cell.getParent()));
        System.out.println("P: " + parent);
        System.out.println("X: " + cell.getGeometry().getX());
        System.out.println("Y: " + cell.getGeometry().getY());
        System.out.println("");
//        System.out.println(cell.getGeometry().getX());
//        String  id      = this.getCellId(this.panel.getGraph().getSelectionCell());
//        Element element = this.panel.getDiagram().getElement(id);
//        PackageUML packageUML = (PackageUML) this.panel.getDiagram().getElement(parent);
//        
//        this.panel.getComponent().getCel
//        System.out.println("Cell..: " + cell);
//        System.out.println("Id....: " + id);
//        System.out.println("Eleme.: " + element);
//        System.out.println("Parent: " + parent);
    }
    
    /**
     * Method responsible for returning the Id.
     * @param  id Original String.
     * @return Id.
     */
    private String getId(String id) {
        if (id.contains("("))
            return id.substring(0, id.indexOf("("));
        return id;
    }
    
    /**
     * Method responsible for returning the Cell Id.
     * @param  cell Graph Cell.
     * @return Cell Id.
     */
    private String getCellId(Object cell) {
        if ((cell != null) && (cell instanceof mxCell))
            return ((mxCell) cell).getId();
        return "";
    }
}
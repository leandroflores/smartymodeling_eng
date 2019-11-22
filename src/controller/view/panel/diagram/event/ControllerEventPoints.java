package controller.view.panel.diagram.event;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxPoint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.structural.base.association.Association;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventPoints</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Points Association</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/11/2019
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventPoints extends MouseAdapter {
    private final PanelDiagram panel;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventPoints(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        Association association = this.getAssociation(event);
        mxPoint     point       = new mxPoint(event.getX(), event.getY());
        if (association != null) {
//            if (event.getClickCount() == 1)
//                this.updatePoint(association);
//            else 
            if (event.getClickCount() == 2)
                this.addPoint(association, point);
            else if (event.getButton() == 3)
                this.removePoint(association, point);
        }
    }
    
    /**
     * Method responsible for returning the Association Selected.
     * @param  event Mouse Event.
     * @return Association Selected.
     */
    private Association getAssociation(MouseEvent event) {
        Object object = this.panel.getComponent().getCellAt(event.getX(), event.getY());
        String id     = this.panel.getIdentifiers().get(object);
        return this.panel.getDiagram().getAssociation(id);
    }
    
    /**
     * Method responsible for updating a Point Position.
     * @param association Association.
     * @param point Point.
     */
    private void updatePoint(Association association) {
        System.out.println(association.getId());
        mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(this.panel.getObjects().get(association.getId()));
        System.out.println(association.getId() + " " +  geometry.getPoints());
                   association.setPoints(geometry.getPoints());
//        System.out.println("");
    }
    
    /**
     * Method responsible for adding a Point to a Association.
     * @param association Association.
     * @param point Point.
     */
    private void addPoint(Association association, mxPoint point) {
        association.addPoint(point);
        this.panel.updateDiagram();
//        this.panel.getViewMenu().getPanelModeling().updateUI();
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for removing a Point from a Association.
     * @param association Association.
     * @param point Point.
     */
    private void removePoint(Association association, mxPoint point) {
        mxPoint nearest = association.getNearestPoint(point);
        if (nearest != null) {
            System.out.println(association);
            association.removePoint(nearest);
            this.panel.updateDiagram();
            this.panel.getViewMenu().getPanelModeling().updateUI();
            this.panel.getViewMenu().setSave(false);
        }
    }
}
package controller.view.panel.diagram.event;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxPoint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
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
        System.out.println("Event Points: " + event.getClickCount());
        Association association = this.getAssociation(event);
        mxPoint     point       = new mxPoint(event.getX(), event.getY());
        if (association != null) {
//            if (event.getClickCount() == 1)
//                this.updatePoint(association);
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
        System.out.println("Single Click - ");
        mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(this.panel.getObjects().get(association.getId()));
        System.out.println("Points: " +  geometry.getPoints());
                   association.setPoints(geometry.getPoints());
        System.out.println("");
    }
    
    /**
     * Method responsible for adding a Point to a Association.
     * @param association Association.
     * @param point Point.
     */
    private void addPoint(Association association, mxPoint point) {
        System.out.println("Double Click ");
        association.addPoint(point);
        this.panel.updateDiagram();
        this.panel.getViewMenu().update();
        this.panel.getViewMenu().setSave(false);
//        this.panel.updateDiagram();
//        this.panel.getViewMenu().getPanelModeling().updateUI();
//        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for removing a Point from a Association.
     * @param association Association.
     * @param point Point.
     */
    private void removePoint(Association association, mxPoint point) {
        System.out.println("Left Click ");
        mxPoint nearest = association.getNearestPoint(point);
        if (nearest != null) {
            System.out.println("Nearest: " + nearest);
            association.removePoint(nearest);
            this.panel.updateDiagram();
            this.panel.getViewMenu().getPanelModeling().updateUI();
            this.panel.getViewMenu().setSave(false);
        }
    } 
    
    private void updatePoints(mxCell edge, Association association, MouseEvent event) {
        if (event.getClickCount() == 2) {
            System.out.println("doubleClicked");
            mxPoint    point    = new mxPoint(event.getX(), event.getY());
            mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(edge);
                       this.updatePoints(geometry, point);
                       ((mxGraphModel) (this.panel.getGraph().getModel())).setGeometry(edge, geometry);
                       System.out.println("Points: " + geometry.getPoints());
        }else if (event.getButton() == MouseEvent.BUTTON3) {
            mxPoint    point    = new mxPoint(event.getX(), event.getY());
            System.out.println("leftClicked: " + point);
            mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(edge);
                       geometry.getPoints().remove(point);
                ((mxGraphModel) (this.panel.getGraph().getModel())).setGeometry(edge, geometry);
                       System.out.println("Points: " + geometry.getPoints());
        }
    }
    
    private void updatePoints(mxGeometry geometry, mxPoint point) {
        List<mxPoint> points = this.getPoints(point);
        if (geometry.getPoints() == null)
            geometry.setPoints(points);
        else
            geometry.getPoints().addAll(points);
    }
    
    private List<mxPoint> getPoints(mxPoint point) {
        List<mxPoint> points = new ArrayList<>();
                      points.add(point);
        return        points;
    }
}

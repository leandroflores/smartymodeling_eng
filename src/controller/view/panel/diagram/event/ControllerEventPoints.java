package controller.view.panel.diagram.event;

import com.mxgraph.util.mxPoint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.structural.base.association.Association;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventPoints</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Points Event</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    java.awt.event.MouseAdapter
 * @see    view.panel.diagram.PanelDiagram
 */
public class ControllerEventPoints extends MouseAdapter {
    private final PanelDiagram panel;
    
    /**
     * Default constructor method of Class.
     * @param panel_ Panel Diagram.
     */
    public ControllerEventPoints(PanelDiagram panel_) {
        panel = panel_;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        Association association = getAssociation(event);
        mxPoint     point       = new mxPoint(event.getX(), event.getY());
        if (association != null) {
            if (event.getClickCount() == 2)
                addPoint(association, point);
            else if (event.getButton() == 3)
                removePoint(association, point);
        }
    }
    
    /**
     * Method responsible for returning the Association Selected.
     * @param  event Mouse Event.
     * @return Association Selected.
     */
    private Association getAssociation(MouseEvent event) {
        Object object = getPanel().getComponent().getCellAt(event.getX(), event.getY());
        String id     = getPanel().getIdentifiers().get(object);
        return getPanel().getDiagram().getAssociation(id);
    }
    
    /**
     * Method responsible for adding a Point to a Association.
     * @param association Association.
     * @param point Point.
     */
    private void addPoint(Association association, mxPoint point) {
        association.addPoint(point);
        getPanel().updateGraph();
//        getPanel().getViewMenu().getPanelModeling().updateUI();
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for removing a Point of a Association.
     * @param association Association.
     * @param point Point.
     */
    private void removePoint(Association association, mxPoint point) {
        mxPoint nearest = association.getNearestPoint(point);
        if (nearest != null) {
            association.removePoint(nearest);
            getPanel().updateGraph();
            getPanel().getViewMenu().getPanelModeling().updateUI();
            getPanel().getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanel() {
        return panel;
    }
}
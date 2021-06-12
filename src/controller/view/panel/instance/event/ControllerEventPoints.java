package controller.view.panel.instance.event;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxPoint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.structural.base.product.Relationship;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerEventPoints</b>.</p>
 * <p>Class responsible for defining the <b>Points Events</b> in <b>Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-14
 * @see    java.awt.event.MouseAdapter
 * @see    view.panel.instance.PanelInstance
 */
public class ControllerEventPoints extends MouseAdapter {
    private final PanelInstance panel;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Instance.
     */
    public ControllerEventPoints(PanelInstance panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        Relationship relationship = getRelationship(event);
        if (relationship != null) {
            if (event.getClickCount() == 1)
                updatePoint(relationship);
            else if (event.getClickCount() == 2)
                addPoint(relationship, new mxPoint(event.getX(), event.getY()));
            else if (event.getButton() == 3)
                removePoint(relationship, new mxPoint(event.getX(), event.getY()));
        }
    }
    
    /**
     * Method responsible for returning the Relationship Selected.
     * @param  event Mouse Event.
     * @return Relationship Selected.
     */
    private Relationship getRelationship(MouseEvent event) {
        Object object = getPanel().getComponent().getCellAt(event.getX(), event.getY());
        String id     = getPanel().getIdentifiers().get(object);
        return getPanel().getInstance().getRelationship(id);
    }
    
    /**
     * Method responsible for updating a Point Position.
     * @param relationship Relationship.
     * @param point Point.
     */
    private void updatePoint(Relationship relationship) {
        mxGeometry geometry = ((mxGraphModel) (getPanel().getGraph().getModel())).getGeometry(getPanel().getObjects().get(relationship.getId()));
                   relationship.setPoints(geometry.getPoints());
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for adding a Point to a Relationship.
     * @param relationship Relationship.
     * @param point Point.
     */
    private void addPoint(Relationship relationship, mxPoint point) {
        relationship.addPoint(point);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for removing a Point from a Relationship.
     * @param relationship Relationship.
     * @param point Point.
     */
    private void removePoint(Relationship relationship, mxPoint point) {
        mxPoint nearest = relationship.getNearestPoint(point);
        if (nearest != null) {
            relationship.removePoint(nearest);
            getPanel().updateGraph();
            getPanel().getViewMenu().update();
            getPanel().getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    public PanelInstance getPanel() {
        return panel;
    }
}
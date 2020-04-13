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
 * <p>Class responsible for defining the <b>Controller</b> for <b>Points Relationship</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/11/2019
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
        Relationship relationship = this.getRelationship(event);
        mxPoint      point        = new mxPoint(event.getX(), event.getY());
        if (relationship != null) {
            if (event.getClickCount() == 1)
                this.updatePoint(relationship);
            else if (event.getClickCount() == 2)
                this.addPoint(relationship, point);
            else if (event.getButton() == 3)
                this.removePoint(relationship, point);
        }
    }
    
    /**
     * Method responsible for returning the Relationship Selected.
     * @param  event Mouse Event.
     * @return Relationship Selected.
     */
    private Relationship getRelationship(MouseEvent event) {
        Object object = this.panel.getComponent().getCellAt(event.getX(), event.getY());
        String id     = this.panel.getIdentifiers().get(object);
        return this.panel.getInstance().getRelationship(id);
    }
    
    /**
     * Method responsible for updating a Point Position.
     * @param relationship Relationship.
     * @param point Point.
     */
    private void updatePoint(Relationship relationship) {
        mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(this.panel.getObjects().get(relationship.getId()));
                   relationship.setPoints(geometry.getPoints());
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for adding a Point to a Relationship.
     * @param relationship Relationship.
     * @param point Point.
     */
    private void addPoint(Relationship relationship, mxPoint point) {
        relationship.addPoint(point);
        this.panel.updateGraph();
        this.panel.getViewMenu().update();
        this.panel.getViewMenu().setSave(false);
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
            this.panel.updateGraph();
            this.panel.getViewMenu().update();
            this.panel.getViewMenu().setSave(false);
        }
    }
}
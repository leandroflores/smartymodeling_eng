package controller.view.panel.instance.event;

import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.product.Artifact;
import model.structural.base.product.Relationship;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Move Events</b> in <b>Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    com.mxgraph.util.mxEventSource
 * @see    com.mxgraph.util.mxEventSource.mxIEventListener
 * @see    view.panel.instance.PanelInstance
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelInstance panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Instance.
     */
    public ControllerEventMove(PanelInstance panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = getPanel().getGraph().getSelectionCell();
        String id   = getPanel().getIdentifiers().get(cell);
        if (getPanel().getInstance().getArtifact(id) != null)
            move(getPanel().getInstance().getArtifact(id), event);
        else if (getPanel().getInstance().getRelationship(id) != null)
            move(getPanel().getInstance().getRelationship(id));
        else if (id != null)
            move(id, event);
    }
    
    /**
     * Method responsible for moving a Artifact.
     * @param artifact Artifact.
     * @param event Graph Event.
     */
    private void move(Artifact artifact, mxEventObject event) {
        artifact.dx(((Double) event.getProperty("dx")).intValue());
        artifact.dy(((Double) event.getProperty("dy")).intValue());
        getPanel().getInstance().dx(artifact.getElement(), ((Double) event.getProperty("dx")).intValue());
        getPanel().getInstance().dy(artifact.getElement(), ((Double) event.getProperty("dy")).intValue());
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for moving a Relationship.
     * @param relationship Relationship.
     */
    private void move(Relationship relationship) {
        mxGeometry geometry = ((mxGraphModel) (getPanel().getGraph().getModel())).getGeometry(getPanel().getObjects().get(relationship.getId()));
                   relationship.setPoints(geometry.getPoints());
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for moving a Cardinality Relationship.
     * @param id Relationship Id.
     * @param event Graph Event.
     */
    private void move(String id, mxEventObject event) {
        Relationship relationship = getPanel().getInstance().getRelationship(id.substring(0, id.indexOf("(")));
        if ((relationship != null) && (relationship.getAssociation() instanceof AssociationUML)) {
            if (id.endsWith("(source)"))
                moveSourceCardinality(relationship, event);
            else if (id.endsWith("(target)"))
                moveTargetCardinality(relationship, event);
        }
    }
    
    /**
     * Method responsible for changing the Source Cardinality.
     * @param relationship Relationship.
     * @param event Graph Event.
     */
    private void moveSourceCardinality(Relationship relationship, mxEventObject event) {
        relationship.dxSource(((Double) event.getProperty("dx")).intValue());
        relationship.dySource(((Double) event.getProperty("dy")).intValue());
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for changing the Target Cardinality.
     * @param relationship Relationship.
     * @param event Graph Event.
     */
    private void moveTargetCardinality(Relationship relationship, mxEventObject event) {
        relationship.dxTarget(((Double) event.getProperty("dx")).intValue());
        relationship.dyTarget(((Double) event.getProperty("dy")).intValue());
        getPanel().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    public PanelInstance getPanel() {
        return panel;
    }
}
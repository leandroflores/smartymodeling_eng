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
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
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
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.panel.getIdentifiers().get(cell);
        if (this.panel.getInstance().getArtifact(id) != null)
            this.move(this.panel.getInstance().getArtifact(id), event);
        else if (this.panel.getInstance().getRelationship(id) != null)
            this.move(this.panel.getInstance().getRelationship(id));
        else
            this.move(id, event);
    }
    
    /**
     * Method responsible for moving a Artifact.
     * @param artifact Artifact.
     * @param event Graph Event.
     */
    private void move(Artifact artifact, mxEventObject event) {
        artifact.dx(((Double) event.getProperty("dx")).intValue());
        artifact.dy(((Double) event.getProperty("dy")).intValue());
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for moving a Relationship.
     * @param relationship Relationship.
     */
    private void move(Relationship relationship) {
        mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(this.panel.getObjects().get(relationship.getId()));
                   relationship.setPoints(geometry.getPoints());
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for moving a Cardinality Relationship.
     * @param id Relationship Id.
     * @param event Graph Event.
     */
    private void move(String id, mxEventObject event) {
        System.out.println("Id..: " + id);
        Relationship relationship = this.panel.getInstance().getRelationship(id.substring(0, id.indexOf("(")));
        System.out.println("Rel.: " + relationship);
        if ((relationship != null) && (relationship.getAssociation() instanceof AssociationUML)) {
            if (id.endsWith("(source)"))
                this.moveSourceCardinality(relationship, event);
            else if (id.endsWith("(target)"))
                this.moveTargetCardinality(relationship, event);
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
        this.panel.getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for changing the Target Cardinality.
     * @param relationship Relationship.
     * @param event Graph Event.
     */
    private void moveTargetCardinality(Relationship relationship, mxEventObject event) {
        relationship.dxTarget(((Double) event.getProperty("dx")).intValue());
        relationship.dyTarget(((Double) event.getProperty("dy")).intValue());
        this.panel.getViewMenu().setSave(false);
    }
}
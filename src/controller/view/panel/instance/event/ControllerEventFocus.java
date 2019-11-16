package controller.view.panel.instance.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.structural.base.product.Artifact;
import model.structural.base.product.Relationship;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerEventFocus</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Focus Modeling Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  14/11/2019
 * @see    view.panel.instance.PanelInstance
 */
public class ControllerEventFocus extends MouseAdapter {
    private final PanelInstance panel;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Instance.
     */
    public ControllerEventFocus(PanelInstance panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        Object  object = this.panel.getComponent().getCellAt(event.getX(), event.getY());
        if (object != null) {
            String id = this.panel.getIdentifiers().get(object);
            if (this.panel.getInstance().getArtifact(id) != null)
                this.updatePanelEdit(this.panel.getInstance().getArtifact(id)); 
            else if (this.panel.getInstance().getRelationship(id) != null)
                this.updatePanelEdit(this.panel.getInstance().getRelationship(id));
        }
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param artifact Artifact selected.
     */
    private void updatePanelEdit(Artifact artifact) {
        this.panel.getViewMenu().getPanelProject().initPanelEditArtifact(artifact);
        this.panel.getViewMenu().getPanelProject().updatePanelEdit();
        this.panel.getGraph().setSelectionCell(this.panel.getObjects().get(artifact.getId()));
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param relationship Relationship selected.
     */
    private void updatePanelEdit(Relationship relationship) {
        this.panel.getViewMenu().getPanelProject().initPanelRelationship(relationship);
        this.panel.getViewMenu().getPanelProject().updatePanelEdit();
        this.panel.getGraph().setSelectionCell(this.panel.getObjects().get(relationship.getId()));
    }
}
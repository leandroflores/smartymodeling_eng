package controller.view.panel.instance.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import model.structural.base.product.Artifact;
import model.structural.base.product.Relationship;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerEventFocus</b>.</p>
 * <p>Class responsible for defining the <b>Focus Events</b> in <b>Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-14
 * @see    java.awt.event.MouseAdapter
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
        Object  object = getPanel().getComponent().getCellAt(event.getX(), event.getY());
        if (object != null) {
            String id = getPanel().getIdentifiers().get(object);
            if (getPanel().getInstance().getArtifact(id) != null)
                updatePanelEdit(getPanel().getInstance().getArtifact(id)); 
            else if (getPanel().getInstance().getRelationship(id) != null)
                updatePanelEdit(getPanel().getInstance().getRelationship(id));
        }
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param artifact Artifact selected.
     */
    private void updatePanelEdit(Artifact artifact) {
        getPanel().getViewMenu().getPanelProject().initPanelEditArtifact(artifact);
        getPanel().getViewMenu().getPanelProject().updatePanelEdit();
        getPanel().getGraph().setSelectionCell(getPanel().getObjects().get(artifact.getId()));
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param relationship Relationship selected.
     */
    private void updatePanelEdit(Relationship relationship) {
        getPanel().getViewMenu().getPanelProject().initPanelRelationship(relationship);
        getPanel().getViewMenu().getPanelProject().updatePanelEdit();
        getPanel().getGraph().setSelectionCell(getPanel().getObjects().get(relationship.getId()));
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    public PanelInstance getPanel() {
        return panel;
    }
}
package controller.view.panel.instance;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.structural.base.product.Artifact;
import model.structural.base.product.Relationship;
import view.modal.delete.base.product.ViewDeleteArtifact;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerPanelInstance</b>.</p>
 * <p>Class responsible for controlling the <b>Instance Panel</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.ControllerPanel
 * @see    java.awt.event.MouseListener
 * @see    view.panel.instance.PanelInstance
 */
public class ControllerPanelInstance extends ControllerPanel implements MouseListener {

    /**
     * Default constructor method of Class.
     * @param panel Panel Instance.
     */
    public ControllerPanelInstance(PanelInstance panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        getPanel().getViewMenu().setSave(false);
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case DELETE:
                delete();
                getPanel().getViewMenu().setSave(false);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                move(event);
                getPanel().getViewMenu().setSave(false);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {}
    
    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {
        update(event);
    }
    
    /**
     * Method responsible for updating the Relationship Points.
     * @param event Mouse Event.
     */
    public void update(MouseEvent event) {
        if (getPanel().getGraph().getSelectionCell() != null) {
            mxCell cell = (mxCell) getPanel().getGraph().getSelectionCell();
            String id   = getPanel().getIdentifiers().get(cell);
            if (getPanel().getInstance().getRelationship(id) != null)
                updatePoints(getPanel().getInstance().getRelationship(id), cell);
        }
    }
    
    /**
     * Method responsible for updating the Relationship Points of a Selected Cell.
     * @param relationship Relationship.
     * @param cell Selected Cell.
     */
    private void updatePoints(Relationship relationship, mxCell cell) {
        mxGeometry geometry = ((mxGraphModel) (getPanel().getGraph().getModel())).getGeometry(cell);
                   relationship.setPoints(geometry.getPoints());
        getPanel().getViewMenu().setSave(false);
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}
    
    
    /**
     * Method responsible for moving the Element on Instance.
     * @param event Key Event.
     */
    public void move(KeyEvent event) {
        mxCell   cell     = (mxCell) getPanel().getGraph().getSelectionCell();
        String   id       = getPanel().getIdentifiers().get(cell);
        Artifact artifact = getPanel().getInstance().getArtifact(id);
        if (artifact != null) {
            if (event.getKeyCode() == KeyEvent.VK_UP)
                artifact.dy(-10);
            if (event.getKeyCode() == KeyEvent.VK_DOWN)
                artifact.dy(10);
            if (event.getKeyCode() == KeyEvent.VK_LEFT)
                artifact.dx(-10);
            if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                artifact.dx(10);
            getPanel().updateGraph();
            getPanel().getGraph().setSelectionCell(getPanel().getObjects().get(artifact.getId()));
        }
    }
    
    /**
     * Method responsible for deleting the Element.
     */
    public void delete() {
        if (getPanel().getGraph() != null) {
            mxCell cell = (mxCell) getPanel().getGraph().getSelectionCell();
            String id   = getPanel().getIdentifiers().get(cell);
            if (getPanel().getInstance().getArtifact("id") != null)
                delete(getPanel().getInstance().getArtifact("id"));
            else if (getPanel().getInstance().getRelationship("id") != null)
                delete(getPanel().getInstance().getRelationship("id"));
        }
    }
    
    /**
     * Method responsible for removing a Artifact.
     * @param artifact Artifact.
     */
    private void delete(Artifact artifact) {
        new ViewDeleteArtifact(getPanel().getViewMenu().getPanelModeling(), artifact).setVisible(true);
        getPanel().updateGraph();
    }
    
    /**
     * Method responsible for removing a Relationship.
     * @param relationship Relationship.
     */
    private void delete(Relationship relationship) {
        getPanel().getInstance().removeRelationship(relationship);
        getPanel().updateGraph();
    }
    
    @Override
    public PanelInstance getPanel() {
        return (PanelInstance) panel;
    }
}
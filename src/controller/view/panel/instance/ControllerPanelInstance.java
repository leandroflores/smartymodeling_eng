package controller.view.panel.instance;

import com.mxgraph.model.mxCell;
import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.structural.base.association.Association;
import model.structural.base.product.Artifact;
import view.delete.product.ViewDeleteArtifact;
import view.panel.instance.PanelInstance;

/**
 * <p>Class of Controller <b>ControllerPanelInstance</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.ControllerPanel
 * @see    java.awt.event.MouseListener
 * @see    view.panel.instance.PanelInstance
 */
public class ControllerPanelInstance extends ControllerPanel implements MouseListener {
    private final PanelInstance panelInstance;

    /**
     * Default constructor method of Class.
     * @param panel Panel Instance.
     */
    public ControllerPanelInstance(PanelInstance panel) {
        super(panel);
        this.panelInstance = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.panelInstance.getViewMenu().setSave(false);
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case DELETE:
                this.delete();
                this.panelInstance.getViewMenu().setSave(false);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.move(event);
                this.panelInstance.getViewMenu().setSave(false);
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
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}
    
    
    /**
     * Method responsible for moving the Element on Instance.
     * @param event Key Event.
     */
    public void move(KeyEvent event) {
        mxCell   cell     = (mxCell) this.panelInstance.getGraph().getSelectionCell();
        String   id       = this.panelInstance.getIdentifiers().get(cell);
        Artifact artifact = this.panelInstance.getInstance().getArtifact(id);
        if (artifact != null) {
            if (event.getKeyCode() == KeyEvent.VK_UP)
                artifact.dy(-10);
            if (event.getKeyCode() == KeyEvent.VK_DOWN)
                artifact.dy(10);
            if (event.getKeyCode() == KeyEvent.VK_LEFT)
                artifact.dx(-10);
            if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                artifact.dx(10);
            this.panelInstance.updateInstance();
            this.panelInstance.getGraph().setSelectionCell(this.panelInstance.getObjects().get(artifact.getId()));
        }
    }
    
    /**
     * Method responsible for deleting the Element.
     */
    public void delete() {
        if (this.panelInstance.getGraph() != null) {
            mxCell      cell        = (mxCell) this.panelInstance.getGraph().getSelectionCell();
            String      id          = this.panelInstance.getIdentifiers().get(cell);
            Artifact    artifact    = this.panelInstance.getInstance().getArtifact(id);
            Association association = this.panelInstance.getInstance().getAssociations().get(id);
            if (artifact != null)
                new ViewDeleteArtifact(this.panelInstance.getViewMenu().getPanelModeling(), artifact).setVisible(true);
            else if (association != null)
                this.panelInstance.getInstance().remove(association);
            this.panelInstance.updateInstance();
            this.panelInstance.getViewMenu().update();
        }
    }
}
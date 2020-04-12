package controller.view.panel.project.tree;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import view.panel.project.tree.PanelProjectTree;

/**
 * <p>Class of Controller <b>ControllerPanelProjectTree</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Project Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-11
 * @see    controller.view.ControllerPanel
 * @see    view.panel.project.tree.PanelProjectTree
 */
public class ControllerPanelProjectTree extends ControllerPanel implements MouseListener {

    /**
     * Default constructor method of Class.
     * @param panel Panel Project Tree.
     */
    public ControllerPanelProjectTree(PanelProjectTree panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void mouseClicked(MouseEvent event) {
        this.getPanel().setIndex(this.getPanel().getTabbedPane().getSelectedIndex());
    }

    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}
    
    @Override
    public PanelProjectTree getPanel() {
        return (PanelProjectTree) this.panel;
    }
}
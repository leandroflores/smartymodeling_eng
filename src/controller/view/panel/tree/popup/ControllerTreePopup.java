package controller.view.panel.tree.popup;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import view.panel.modeling.PanelModeling;
import view.panel.project.PanelProject;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerTreePopup</b>.</p>
 * <p>Class responsible for controlling the <b>TreePopup</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    java.awt.event.KeyListener
 * @see    java.awt.event.MouseListener
 * @see    view.panel.tree.popup.TreePopup
 */
public abstract class ControllerTreePopup implements MouseListener, KeyListener {
    protected final TreePopup popup;
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerTreePopup(TreePopup popup) {
        this.popup = popup;
    }
    
    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseClicked(MouseEvent event) {
        DefaultMutableTreeNode node = getSelectedNode();
        if (node != null) {
            if (!SwingUtilities.isLeftMouseButton(event))
                showPopup(node, event);
            else if (event.getClickCount() == 1)
                showPanelEdit(node);
            else if (event.getClickCount() == 2)
                showPanelModeling(node);
        }
    }
    
    /**
     * Method responsible for setting the Popup Flag.
     * @param new_ New Flag.
     * @param edit Edit Flag.
     * @param delete Delete Flag.
     */
    protected void setPopupFlag(boolean new_, boolean edit, boolean delete) {
        getPopup().getNewMenu().setEnabled(new_);
        getPopup().getEditMenuItem().setEnabled(edit);
        getPopup().getDeleteMenuItem().setEnabled(delete);
    }
    
    /**
     * Method responsible for showing the Popup.
     * @param node Tree Node.
     * @param event Mouse Event.
     */
    protected abstract void showPopup(DefaultMutableTreeNode node, MouseEvent event);

    /**
     * Method responsible for showing the Panel Edit.
     * @param node Tree Node.
     */
    protected void showPanelEdit(DefaultMutableTreeNode node) {
        if (node != null && node.getUserObject() != null)
            showPanelEdit(node, node.getUserObject());
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param node Tree Node.
     * @param object Object Node.
     */
    protected abstract void showPanelEdit(DefaultMutableTreeNode node, Object object);
    
    /**
     * Method responsible for showing the Panel Modeling.
     * @param node Tree Node.
     */
    protected void showPanelModeling(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Diagram) 
            popup.getPanel().getViewMenu().showDiagram( (Diagram)  node.getUserObject());
        else if (node.getUserObject() instanceof Instance)
            popup.getPanel().getViewMenu().showInstance((Instance) node.getUserObject());
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        showPanelEdit(getSelectedNode());
    }
    
    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyPressed(KeyEvent event) {
        DefaultMutableTreeNode node = getSelectedNode();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                showPanelModeling(node);
                break;
            case KeyEvent.VK_DELETE:
                delete(node);
                break;
            case KeyEvent.VK_F2:
                edit(node);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for deleting the Object Node.
     * @param node Tree Node.
     */
    protected void delete(DefaultMutableTreeNode node) {
        if (node != null && node.getUserObject() != null)
            delete(node, node.getUserObject());
    }
    
    /**
     * Method responsible for deleting the Object Node.
     * @param node Tree Node.
     * @param object Object Node.
     */
    protected abstract void delete(DefaultMutableTreeNode node, Object object);
    
    /**
     * Method responsible for editing the Object Node.
     * @param node Tree Node.
     */
    protected void edit(DefaultMutableTreeNode node) {
        if (node != null && node.getUserObject() != null)
            edit(node, node.getUserObject());
    }
    
    /**
     * Method responsible for deleting the Object Node.
     * @param node Tree Node.
     * @param object Object Node.
     */
    protected abstract void edit(DefaultMutableTreeNode node, Object object);
    
    /**
     * Method responsible for returning the Diagram Node.
     * @param  node JTree Node.
     * @return Diagram Node.
     */
    protected Diagram getDiagram(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        while ((parent != null) && !(parent.getUserObject() instanceof Diagram))
                parent = (DefaultMutableTreeNode) parent.getParent();
        return  parent == null ? null : (Diagram) parent.getUserObject();
    }
    
    /**
     * Method responsible for returning the Selected Node.
     * @return Selected Node.
     */
    protected DefaultMutableTreeNode getSelectedNode() {
        return (DefaultMutableTreeNode) popup.getPanel().getTree().getLastSelectedPathComponent();
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    protected PanelModeling getPanelModeling() {
        return getPopup().getPanel().getViewMenu().getPanelModeling();
    }
    
    /**
     * Method responsible for returning the Panel Project.
     * @return Panel Project.
     */
    protected PanelProject getPanelProject() {
        return getPopup().getPanel().getViewMenu().getPanelProject();
    }
    
    /**
     * Method responsible for returning the Tree Popup.
     * @return Tree Popup.
     */
    protected TreePopup getPopup() {
        return popup;
    }
}
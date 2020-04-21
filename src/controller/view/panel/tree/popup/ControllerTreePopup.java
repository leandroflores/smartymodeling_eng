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
    protected final TreePopup treePopup;
    
    /**
     * Default constructor method of Class.
     * @param treePopup Tree Popup.
     */
    public ControllerTreePopup(TreePopup treePopup) {
        this.treePopup = treePopup;
    }
    
    @Override
    public void mouseReleased(MouseEvent event) {}

    @Override
    public void mouseClicked(MouseEvent event) {
        DefaultMutableTreeNode node = this.getSelectedNode();
        if (node != null) {
            if (!SwingUtilities.isLeftMouseButton(event))
                this.showPopup(node, event);
            else if (event.getClickCount() == 1)
                this.showPanelEdit(node);
            else if (event.getClickCount() == 2)
                this.showPanelModeling(node);
        }
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
            this.showPanelEdit(node.getUserObject());
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param object Object Node.
     */
    protected abstract void showPanelEdit(Object object);
    
    /**
     * Method responsible for showing the Panel Modeling.
     * @param node Tree Node.
     */
    protected void showPanelModeling(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Diagram) 
            this.treePopup.getPanel().getViewMenu().showDiagram( (Diagram)  node.getUserObject());
        else if (node.getUserObject() instanceof Instance)
            this.treePopup.getPanel().getViewMenu().showInstance((Instance) node.getUserObject());
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.showPanelEdit(this.getSelectedNode());
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
        DefaultMutableTreeNode node = this.getSelectedNode();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.showPanelModeling(node);
                break;
            case KeyEvent.VK_DELETE:
                this.delete(node);
                break;
            case KeyEvent.VK_F2:
                this.edit(node);
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
            this.delete(node.getUserObject());
    }
    
    /**
     * Method responsible for deleting the Object Node.
     * @param object Object Node.
     */
    protected abstract void delete(Object object);
    
    /**
     * Method responsible for editing the Object Node.
     * @param node Tree Node.
     */
    protected void edit(DefaultMutableTreeNode node) {
        if (node != null && node.getUserObject() != null)
            this.edit(node.getUserObject());
    }
    
    /**
     * Method responsible for deleting the Object Node.
     * @param object Object Node.
     */
    protected abstract void edit(Object object);
    
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
        return (DefaultMutableTreeNode) this.treePopup.getPanel().getTree().getLastSelectedPathComponent();
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    protected PanelModeling getPanelModeling() {
        return this.treePopup.getPanel().getViewMenu().getPanelModeling();
    }
}
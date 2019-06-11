package controller.view.panel.tree.popup;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.variability.Variability;
import model.structural.diagram.classs.base.AttributeUML;
import model.structural.diagram.classs.base.MethodUML;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerTreePopup</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    java.awt.event.KeyListener
 * @see    java.awt.event.MouseListener
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerTreePopup implements MouseListener, KeyListener {
    private final TreePopup treePopup;
    
    /**
     * Default constructor method of Class.
     * @param treePopup Tree Popup.
     */
    public ControllerTreePopup(TreePopup treePopup) {
        this.treePopup = treePopup;
    }
    
    @Override
    public void mouseReleased(MouseEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.treePopup.getPanelTree().getTree().getLastSelectedPathComponent();
//        System.out.println("Released: " + node.getUserObject());
//        System.out.println("");
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.treePopup.getPanelTree().getTree().getLastSelectedPathComponent();
        if (!SwingUtilities.isLeftMouseButton(event))
            this.showPopup(node, event);
        else if (event.getClickCount() == 1)
            this.showPanelEdit(node);
        else if (event.getClickCount() == 2)
            this.showPanelDiagram(node);
    }
    
    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void keyTyped(KeyEvent evento) {}

    @Override
    public void keyPressed(KeyEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.treePopup.getPanelTree().getTree().getLastSelectedPathComponent();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.showPanelDiagram(node);
                break;
            case KeyEvent.VK_DELETE:
                this.delete(node.getUserObject(), node);
                break;
            case KeyEvent.VK_F2:
                this.edit(node.getUserObject(), node);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent evento) {}
    
    /**
     * Method responsible for showing Popup.
     * @param node JTree Node.
     * @param event Mouse Event.
     */
    private void showPopup(DefaultMutableTreeNode node, MouseEvent event) {
        if (node.getUserObject() instanceof Project)
            this.treePopup.getMenuItemDelete().setVisible(false);
        else
            this.treePopup.getMenuItemDelete().setVisible(true);
        this.treePopup.show(event.getComponent(), event.getX(), event.getY());
    }

    /**
     * Method responsible for showing the Panel Edit.
     * @param node JTree Node.
     */
    private void showPanelEdit(DefaultMutableTreeNode node) {
        if (node.getUserObject() != null)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEdit(node.getUserObject());
    }
    
    /**
     * Method responsible for showing the Panel Diagram.
     * @param node JTree Node.
     */
    private void showPanelDiagram(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Diagram)
            this.treePopup.getPanelTree().getViewMenu().showDiagram((Diagram) node.getUserObject());
    }
    
    /**
     * Method responsible for deleting JTree Node.
     * @param object Object.
     * @param node JTree Node.
     */
    private void delete(Object object, DefaultMutableTreeNode node) {
        if (object instanceof Diagram)
            System.out.println("Diagram");
//            new ViewExcluirDiagrama(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), ((Diagrama) object)).setVisible(true);
//        else if (object instanceof Element)
//            new ViewExcluirElemento(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), ((Elemento) object)).setVisible(true);
        else if (object instanceof Variability)
            this.deleteVariability(object, node);
        else if (object instanceof AttributeUML)
            this.deleteAttributeUML(object, node);
        else if (object instanceof MethodUML)
            this.deleteMethodUML(object, node);
    }
    
    /**
     * Method responsible for deleting Variability.
     * @param object Object.
     * @param node JTree Node.
     */
    private void deleteVariability(Object object, DefaultMutableTreeNode node) {
        Diagram     diagram     = this.getDiagram((DefaultMutableTreeNode) node.getParent());
        Variability variability = (Variability) object;
//        new ViewExcluirVariabilidade(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), diagrama, variability).setVisible(true);
    }
    
    /**
     * Method responsible for deleting Attribute UML.
     * @param object Object.
     * @param node JTree Node.
     */
    private void deleteAttributeUML(Object object, DefaultMutableTreeNode node) {
//        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());
//        AtributoUML     atributo = (AtributoUML) object;
//                        diagrama.removeAtributo(atributo);
//        this.treePopup.getPanelProject().getViewMenu().getPanelModeling().getPainelDiagrama().atualizarDiagrama();
//        this.treePopup.getPanelProject().getViewMenu().update();
//        this.treePopup.getPanelProject().getViewMenu().setSalvo(false);
    }
    
    /**
     * Method responsible for deleting Method UML.
     * @param object Object.
     * @param node JTree Node.
     */
    private void deleteMethodUML(Object object, DefaultMutableTreeNode node) {
//        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());
//        MetodoUML       metodo = (MetodoUML) object;
//                        diagrama.removeMetodo(metodo);
//        this.treePopup.getPanelProject().getViewMenu().getPanelModeling().getPainelDiagrama().atualizarDiagrama();
//        this.treePopup.getPanelProject().getViewMenu().update();
//        this.treePopup.getPanelProject().getViewMenu().setSalvo(false);
    }
    
    /**
     * Method responsible for editing JTree Node.
     * @param object Object.
     * @param node JTree Node.
     */
    private void edit(Object object, DefaultMutableTreeNode node) {
//        if (object instanceof Projeto)
//            new ViewEditarProjeto(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), ((Projeto) object)).setVisible(true);
//        else if (object instanceof Diagrama)
//            new ViewEditarDiagrama(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), ((Diagrama) object)).setVisible(true);
//        else if (object instanceof Elemento)
//            new ViewEditarElemento(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), ((Elemento) object)).setVisible(true);
//        else if (object instanceof Variabilidade)
//            this.editarVariabilidade(object, node);
//        else if (object instanceof AtributoUML)
//            this.editarAtributo(object, node);
//        else if (object instanceof MetodoUML)
//            this.editarMetodo(object, node);
    }
    
    /**
     * Method responsible for editing Variability.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void editVariability(Object objeto, DefaultMutableTreeNode node) {
        Diagram     diagram     = this.getDiagram((DefaultMutableTreeNode) node.getParent());
        Variability variability = (Variability) objeto;
//        new ViewEditarVariabilidade(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), diagram, variability).setVisible(true);
    }
    
    /**
     * Method responsible for editing Attribute UML.
     * @param object Object.
     * @param node JTree Node.
     */
    private void editAttributeUML(Object object, DefaultMutableTreeNode node) {
//        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());
//        AtributoUML     atributo = (AtributoUML) object;
//        new ViewEditarAtributo(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), diagrama, atributo).setVisible(true);
    }
    
    /**
     * Method responsible for editing Method UML.
     * @param object Object.
     * @param node JTree Node.
     */
    private void editMethodUML(Object object, DefaultMutableTreeNode node) {
//        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());;
//        MetodoUML       metodo   = (MetodoUML) object;
//        new ViewEditarMetodo(this.treePopup.getPanelProject().getViewMenu().getPanelModeling(), diagrama, metodo).setVisible(true);
    }
    
    /**
     * Method responsible for returning Diagram.
     * @param  node JTree Node.
     * @return Diagram.
     */
    private Diagram getDiagram(DefaultMutableTreeNode node) {
        return (Diagram) node.getUserObject();
    }
}
package controller.view.panel.tree.popup;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Project;
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
        if (SwingUtilities.isLeftMouseButton(event) == false)
            this.showPopup(node, event);
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
                this.excluir(node.getUserObject(), node);
                break;
            case KeyEvent.VK_F2:
                this.editar(node.getUserObject(), node);
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
     * Metodo responsavel por apresentar a
     * @param node Node da JTree.
     */
    private void showPanelDiagram(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Diagrama)
            this.treePopup.getPainelProjeto().getViewMenu().showDiagrama((Diagrama) node.getUserObject());
    }
    
    /**
     * Metodo responsavel por excluir o Node da JTree.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void excluir(Object objeto, DefaultMutableTreeNode node) {
        if (objeto instanceof Diagrama)
            new ViewExcluirDiagrama(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), ((Diagrama) objeto)).setVisible(true);
        else if (objeto instanceof Elemento)
            new ViewExcluirElemento(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), ((Elemento) objeto)).setVisible(true);
        else if (objeto instanceof Variabilidade)
            this.excluirVariabilidade(objeto, node);
        else if (objeto instanceof AtributoUML)
            this.excluirAtributo(objeto, node);
        else if (objeto instanceof MetodoUML)
            this.excluirMetodo(objeto, node);
    }
    
    /**
     * Metodo responsavel por excluir a Variabilidade.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void excluirVariabilidade(Object objeto, DefaultMutableTreeNode node) {
        Diagrama      diagrama      = this.getDiagrama((DefaultMutableTreeNode) node.getParent());
        Variabilidade variabilidade = (Variabilidade) objeto;
        new ViewExcluirVariabilidade(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), diagrama, variabilidade).setVisible(true);
    }
    
    /**
     * Metodo responsavel por excluir o Atributo UML.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void excluirAtributo(Object objeto, DefaultMutableTreeNode node) {
        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());
        AtributoUML     atributo = (AtributoUML) objeto;
                        diagrama.removeAtributo(atributo);
        this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem().getPainelDiagrama().atualizarDiagrama();
        this.treePopup.getPainelProjeto().getViewMenu().update();
        this.treePopup.getPainelProjeto().getViewMenu().setSalvo(false);
    }
    
    /**
     * Metodo responsavel por excluir o Metodo UML.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void excluirMetodo(Object objeto, DefaultMutableTreeNode node) {
        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());
        MetodoUML       metodo = (MetodoUML) objeto;
                        diagrama.removeMetodo(metodo);
        this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem().getPainelDiagrama().atualizarDiagrama();
        this.treePopup.getPainelProjeto().getViewMenu().update();
        this.treePopup.getPainelProjeto().getViewMenu().setSalvo(false);
    }
    
    /**
     * Metodo responsavel por editar o Node da JTree.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void editar(Object objeto, DefaultMutableTreeNode node) {
        if (objeto instanceof Projeto)
            new ViewEditarProjeto(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), ((Projeto) objeto)).setVisible(true);
        else if (objeto instanceof Diagrama)
            new ViewEditarDiagrama(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), ((Diagrama) objeto)).setVisible(true);
        else if (objeto instanceof Elemento)
            new ViewEditarElemento(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), ((Elemento) objeto)).setVisible(true);
        else if (objeto instanceof Variabilidade)
            this.editarVariabilidade(objeto, node);
        else if (objeto instanceof AtributoUML)
            this.editarAtributo(objeto, node);
        else if (objeto instanceof MetodoUML)
            this.editarMetodo(objeto, node);
    }
    
    /**
     * Metodo responsavel por editar a Variabilidade.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void editarVariabilidade(Object objeto, DefaultMutableTreeNode node) {
        Diagrama      diagrama      = this.getDiagrama((DefaultMutableTreeNode) node.getParent());
        Variabilidade variabilidade = (Variabilidade) objeto;
        new ViewEditarVariabilidade(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), diagrama, variabilidade).setVisible(true);
    }
    
    /**
     * Metodo responsavel por editar o Atributo UML.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void editarAtributo(Object objeto, DefaultMutableTreeNode node) {
        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());
        AtributoUML     atributo = (AtributoUML) objeto;
        new ViewEditarAtributo(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), diagrama, atributo).setVisible(true);
    }
    
    /**
     * Metodo responsavel por editar o Metodo UML.
     * @param objeto Objeto selecionado.
     * @param node Node da JTree.
     */
    private void editarMetodo(Object objeto, DefaultMutableTreeNode node) {
        DiagramaClasses diagrama = (DiagramaClasses) this.getDiagrama((DefaultMutableTreeNode) node.getParent().getParent());
        MetodoUML       metodo   = (MetodoUML) objeto;
        new ViewEditarMetodo(this.treePopup.getPainelProjeto().getViewMenu().getPainelModelagem(), diagrama, metodo).setVisible(true);
    }
    
    /**
     * Metodo responsavel por retornar o Diagrama do Node.
     * @param  node Node da JTree.
     * @return Diagrama.
     */
    private Diagrama getDiagrama(DefaultMutableTreeNode node) {
        return (Diagrama) node.getUserObject();
    }
}
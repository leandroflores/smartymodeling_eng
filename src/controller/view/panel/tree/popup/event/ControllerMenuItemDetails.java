package controller.view.panel.tree.popup.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerMenuItemDetails</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Menu Item Details</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    java.awt.event.ActionListener
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerMenuItemDetails implements ActionListener {
    private final TreePopup popup;
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup.
     */
    public ControllerMenuItemDetails(TreePopup popup) {
        this.popup = popup;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.popup.getPanelTree().getTree().getLastSelectedPathComponent();
        if (node != null) {
            Object object = node.getUserObject();
            this.action(object, node);
        }
    }
    
    /**
     * Method responsible for forwarding Action.
     * @param object Object.
     * @param node JTree Node.
     */
    private void action(Object object, DefaultMutableTreeNode node) {
//        if (object instanceof Projeto)
//            new ViewDetalhesProjeto(this.popup.getPainelProjeto().getViewMenu().getPainelModelagem(),  (Projeto)  object).setVisible(true);
//        else if (object instanceof Diagrama)
//            new ViewDetalhesDiagrama(this.popup.getPainelProjeto().getViewMenu().getPainelModelagem(), (Diagrama) object).setVisible(true);
//        else if (object instanceof Elemento)
//            new ViewDetalhesElemento(this.popup.getPainelProjeto().getViewMenu().getPainelModelagem(), (Elemento) object).setVisible(true);
//        else if (object instanceof Variabilidade)
//            new ViewDetalhesVariabilidade(this.popup.getPainelProjeto().getViewMenu().getPainelModelagem(), (Variabilidade) object).setVisible(true);
    }
    
    /**
     * Method responsible for returning Diagram from Node.
     * @param  node JTree Node.
     * @return Diagram.
     */
    private Diagram getDiagram(DefaultMutableTreeNode node) {
        return (Diagram) node.getUserObject();
    }
}
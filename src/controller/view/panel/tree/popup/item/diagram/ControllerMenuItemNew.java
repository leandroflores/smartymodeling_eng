package controller.view.panel.tree.popup.item.diagram;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Project;
import view.modal.new_.base.variability.ViewNewVariability;
import view.panel.tree.popup.diagram.TreePopupDiagram;

/**
 * <p>Class of Controller <b>ControllerMenuItemNew</b>.</p>
 * <p>Class responsible for controlling the <b>MenuItemNew</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.panel.tree.popup.item.ControllerMenuItem
 * @see    view.panel.tree.popup.diagram.TreePopupDiagram
 */
public class ControllerMenuItemNew extends ControllerMenuItem {
    
    /**
     * Default constructor method of Class.
     * @param popup Tree Popup Diagram.
     */
    public ControllerMenuItemNew(TreePopupDiagram popup) {
        super(popup);
    }
    
    @Override
    protected void action(DefaultMutableTreeNode node, JMenuItem item) {
        Object object = node.getUserObject();
        if (object instanceof Project)
            this.newDiagram(item.getText());
        else if (object instanceof Diagram)
            this.newVariability((Diagram) object);
    }
    
    /**
     * Method responsible for adding a New Diagram.
     * @param type Diagram Type.
     */
    private void newDiagram(String type) {
        if (type.equalsIgnoreCase("Use Case Diagram"))
            this.getViewMenu().getController().newUseCaseDiagram();
        else if (type.equalsIgnoreCase("Class Diagram"))
            this.getViewMenu().getController().newClassDiagram();
        else if (type.equalsIgnoreCase("Component Diagram"))
            this.getViewMenu().getController().newComponentDiagram();
        else if (type.equalsIgnoreCase("SequenceDiagram"))
            this.getViewMenu().getController().newSequenceDiagram();
        else if (type.equalsIgnoreCase("ActivityDiagram"))
            this.getViewMenu().getController().newActivityDiagram();
    }
    
    /**
     * Method responsible for adding a New Variability.
     * @param diagram Diagram.
     */
    private void newVariability(Diagram diagram) {
        new ViewNewVariability(this.getViewMenu(), diagram).setVisible(true);
    }
}
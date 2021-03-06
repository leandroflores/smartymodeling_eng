package controller.view.panel.tree.popup.item.diagram;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import javax.swing.JMenuItem;
import javax.swing.tree.DefaultMutableTreeNode;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.modal.message.ViewError;
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
            newDiagram(item.getText());
        else if (object instanceof Diagram)
            newVariability((Diagram) object);
        else if (object instanceof Entity)
            newElement((Entity) object, item.getText());
    }
    
    /**
     * Method responsible for adding a New Diagram.
     * @param type Diagram Type.
     */
    private void newDiagram(String type) {
        if (type.equalsIgnoreCase("Use Case Diagram"))
            getViewMenu().getController().createNewUseCaseDiagram();
        else if (type.equalsIgnoreCase("Class Diagram"))
            getViewMenu().getController().createNewClassDiagram();
        else if (type.equalsIgnoreCase("Component Diagram"))
            getViewMenu().getController().createNewComponentDiagram();
        else if (type.equalsIgnoreCase("SequenceDiagram"))
            getViewMenu().getController().createNewSequenceDiagram();
        else if (type.equalsIgnoreCase("ActivityDiagram"))
            getViewMenu().getController().createNewActivityDiagram();
    }
    
    /**
     * Method responsible for adding a New Variability.
     * @param diagram Diagram.
     */
    private void newVariability(Diagram diagram) {
        if (diagram.getElementsList().isEmpty())
            new ViewError(getViewMenu(), "Diagram with no Elements!").setVisible(true);
        else
            new ViewNewVariability(getViewMenu(), diagram).setVisible(true);
    }
    
    /**
     * Method responsible for adding a New Element.
     * @param entity Entity.
     * @param type Element TYpe
     */
    private void newElement(Entity entity, String type) {
        if (type.equalsIgnoreCase("UML Attribute"))
            newAttribute(entity);
        else if (type.equalsIgnoreCase("UML Method"))
            newMethod(entity);
    }
    
    /**
     * Method responsible for adding a New Attribute to a Entity.
     * @param entity Entity.
     */
    private void newAttribute(Entity entity) {
        AttributeUML attribute = new AttributeUML(entity.getDiagram());
                     entity.getDiagram().addAttribute(attribute);
                     attribute.setEntity(entity);
                     attribute.setTypeUML(entity.getDiagram().getObjectType());
                     entity.addAttribute(attribute);
                     attribute.setDefaultName();
                     entity.updateSize();
        getViewMenu().update();
        getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for adding a New Method to a Entity.
     * @param entity Entity.
     */
    private void newMethod(Entity entity) {
        MethodUML method = new MethodUML(entity.getDiagram());
                  method.setId(entity.getDiagram().nextMethodId());
                  method.setEntity(entity);
                  method.setReturn(entity.getDiagram().getVoidType());
                  entity.addMethod(method);
                  entity.getDiagram().addMethod(method);
                  method.setDefaultName();
                  entity.updateSize();
        getViewMenu().update();
        getViewMenu().setSave(false);
    }
}
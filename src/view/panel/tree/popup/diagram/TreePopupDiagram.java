package view.panel.tree.popup.diagram;

import controller.view.panel.tree.popup.item.ControllerMenuItem;
import controller.view.panel.tree.popup.item.diagram.ControllerMenuItemDelete;
import controller.view.panel.tree.popup.item.diagram.ControllerMenuItemEdit;
import controller.view.panel.tree.popup.item.diagram.ControllerMenuItemNew;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JMenuItem;
import view.panel.tree.diagram.PanelTreeDiagram;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of View <b>TreePopupDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    view.panel.tree.diagram.PanelTreeDiagram
 * @see    view.panel.tree.popup.TreePopup
 */
public final class TreePopupDiagram extends TreePopup {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Tree Diagram.
     */
    public TreePopupDiagram(PanelTreeDiagram panel) {
        super(panel);
        this.addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        this.createMenuItem("use-case_diagram",  "Use Case Diagram",  "menu/diagram/use-case",  KeyEvent.VK_U, InputEvent.CTRL_MASK);
        this.createMenuItem("class_diagram",     "Class Diagram",     "menu/diagram/class",     KeyEvent.VK_C, InputEvent.CTRL_MASK);
        this.createMenuItem("component_diagram", "Component Diagram", "menu/diagram/component", KeyEvent.VK_M, InputEvent.CTRL_MASK);
        this.createMenuItem("sequence_diagram",  "Sequence Diagram",  "menu/diagram/sequence",  KeyEvent.VK_E, InputEvent.CTRL_MASK);
        this.createMenuItem("activity_diagram",  "Activity Diagram",  "menu/diagram/activity",  KeyEvent.VK_A, InputEvent.CTRL_MASK);
        this.createMenuItem("variability",       "Variability",       "variability");
        
        this.getNewMenu().add(this.getUseCaseDiagramMenuItem());
        this.getNewMenu().add(this.getClassDiagramMenuItem());
        this.getNewMenu().add(this.getComponentDiagramMenuItem());
        this.getNewMenu().add(this.getSequenceDiagramMenuItem());
        this.getNewMenu().add(this.getActivityDiagramMenuItem());
        this.getNewMenu().add(this.getVariabilityMenuItem());
    }
    
    @Override
    protected void setControllers() {
        this.setNewControllers();
        this.getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        this.getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    private void setNewControllers() {
        ControllerMenuItem controller = new ControllerMenuItemNew(this);
        this.getUseCaseDiagramMenuItem().addActionListener(controller);
        this.getClassDiagramMenuItem().addActionListener(controller);
        this.getComponentDiagramMenuItem().addActionListener(controller);
        this.getSequenceDiagramMenuItem().addActionListener(controller);
        this.getActivityDiagramMenuItem().addActionListener(controller);
        this.getVariabilityMenuItem().addActionListener(controller);
    }
    
    @Override
    protected void addMenuItems() {
        this.add(this.getNewMenu());
        this.addSeparator();
        this.add(getEditMenuItem());
        this.addSeparator();
        this.add(getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the Use Case Diagram Menu Item.
     * @return Use Case Diagram Menu Item.
     */
    public JMenuItem getUseCaseDiagramMenuItem() {
        return this.getItems().get("use-case_diagram");
    }
    
    /**
     * Method responsible for returning the Class Diagram Menu Item.
     * @return Class Diagram Menu Item.
     */
    public JMenuItem getClassDiagramMenuItem() {
        return this.getItems().get("class_diagram");
    }
    
    /**
     * Method responsible for returning the Component Diagram Menu Item.
     * @return Component Diagram Menu Item.
     */
    public JMenuItem getComponentDiagramMenuItem() {
        return this.getItems().get("component_diagram");
    }
    
    /**
     * Method responsible for returning the Sequence Diagram Menu Item.
     * @return Sequence Diagram Menu Item.
     */
    public JMenuItem getSequenceDiagramMenuItem() {
        return this.getItems().get("sequence_diagram");
    }
    
    /**
     * Method responsible for returning the Activity Diagram Menu Item.
     * @return Activity Diagram Menu Item.
     */
    public JMenuItem getActivityDiagramMenuItem() {
        return this.getItems().get("activity_diagram");
    }
    
    /**
     * Method responsible for returning the Variability Menu Item.
     * @return Variability Menu Item.
     */
    public JMenuItem getVariabilityMenuItem() {
        return this.getItems().get("variability");
    }
}
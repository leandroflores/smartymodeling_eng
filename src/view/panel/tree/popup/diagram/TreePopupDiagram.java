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
        addComponents();
    }
    
    @Override
    protected void createMenuItems() {
        super.createMenuItems();
        createMenuItem("use_case_diagram",  "Use Case Diagram",  "menu/diagram/use-case",  KeyEvent.VK_U, InputEvent.CTRL_MASK);
        createMenuItem("class_diagram",     "Class Diagram",     "menu/diagram/class",     KeyEvent.VK_C, InputEvent.CTRL_MASK);
        createMenuItem("component_diagram", "Component Diagram", "menu/diagram/component", KeyEvent.VK_M, InputEvent.CTRL_MASK);
        createMenuItem("sequence_diagram",  "Sequence Diagram",  "menu/diagram/sequence",  KeyEvent.VK_E, InputEvent.CTRL_MASK);
        createMenuItem("activity_diagram",  "Activity Diagram",  "menu/diagram/activity",  KeyEvent.VK_A, InputEvent.CTRL_MASK);
        createMenuItem("variability",       "Variability",       "variability");
        createMenuItem("attribute",         "UML Attribute",     "diagram/classes/attribute");
        createMenuItem("method",            "UML Method",        "diagram/classes/method");
        
        getNewMenu().add(getUseCaseDiagramMenuItem());
        getNewMenu().add(getClassDiagramMenuItem());
        getNewMenu().add(getComponentDiagramMenuItem());
        getNewMenu().add(getSequenceDiagramMenuItem());
        getNewMenu().add(getActivityDiagramMenuItem());
        getNewMenu().add(getVariabilityMenuItem());
        getNewMenu().add(getAttributeMenuItem());
        getNewMenu().add(getMethodMenuItem());
    }
    
    @Override
    protected void setControllers() {
        setNewControllers();
        getEditMenuItem().addActionListener(new ControllerMenuItemEdit(this));
        getDeleteMenuItem().addActionListener(new ControllerMenuItemDelete(this));
    }
    
    /**
     * Method responsible for setting the New Controllers.
     */
    private void setNewControllers() {
        ControllerMenuItem controller = new ControllerMenuItemNew(this);
        getUseCaseDiagramMenuItem().addActionListener(controller);
        getClassDiagramMenuItem().addActionListener(controller);
        getComponentDiagramMenuItem().addActionListener(controller);
        getSequenceDiagramMenuItem().addActionListener(controller);
        getActivityDiagramMenuItem().addActionListener(controller);
        getVariabilityMenuItem().addActionListener(controller);
        getAttributeMenuItem().addActionListener(controller);
        getMethodMenuItem().addActionListener(controller);
    }
    
    @Override
    protected void addMenuItems() {
        add(getNewMenu());
        addSeparator();
        add(getEditMenuItem());
        addSeparator();
        add(getDeleteMenuItem());
    }
    
    /**
     * Method responsible for returning the Use Case Diagram Menu Item.
     * @return Use Case Diagram Menu Item.
     */
    public JMenuItem getUseCaseDiagramMenuItem() {
        return getItems().get("use_case_diagram");
    }
    
    /**
     * Method responsible for returning the Class Diagram Menu Item.
     * @return Class Diagram Menu Item.
     */
    public JMenuItem getClassDiagramMenuItem() {
        return getItems().get("class_diagram");
    }
    
    /**
     * Method responsible for returning the Component Diagram Menu Item.
     * @return Component Diagram Menu Item.
     */
    public JMenuItem getComponentDiagramMenuItem() {
        return getItems().get("component_diagram");
    }
    
    /**
     * Method responsible for returning the Sequence Diagram Menu Item.
     * @return Sequence Diagram Menu Item.
     */
    public JMenuItem getSequenceDiagramMenuItem() {
        return getItems().get("sequence_diagram");
    }
    
    /**
     * Method responsible for returning the Activity Diagram Menu Item.
     * @return Activity Diagram Menu Item.
     */
    public JMenuItem getActivityDiagramMenuItem() {
        return getItems().get("activity_diagram");
    }
    
    /**
     * Method responsible for returning the Variability Menu Item.
     * @return Variability Menu Item.
     */
    public JMenuItem getVariabilityMenuItem() {
        return getItems().get("variability");
    }
    
    /**
     * Method responsible for returning the Attribute Menu Item.
     * @return Attribute Menu Item.
     */
    public JMenuItem getAttributeMenuItem() {
        return getItems().get("attribute");
    }
    
    /**
     * Method responsible for returning the Method Menu Item.
     * @return Method Menu Item.
     */
    public JMenuItem getMethodMenuItem() {
        return getItems().get("method");
    }
}
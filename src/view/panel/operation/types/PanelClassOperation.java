package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelClassOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelClassDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelClassOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Class Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-04-09
 * @see    controller.view.panel.operation.types.ControllerPanelClassOperation
 * @see    view.panel.operation.PanelOperation
 * @see    view.panel.operation.types.PanelClassOperation
 */
public final class PanelClassOperation extends PanelOperation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public PanelClassOperation(PanelClassDiagram panel) {
        super(panel);
        controller = new ControllerPanelClassOperation(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        add(createButton("package",   "", "New Package",   "diagram/classes/package.png"));
        add(createButton("class",     "", "New Class",     "diagram/classes/class.png"));
        add(createButton("interface", "", "New Interface", "diagram/classes/interface.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {getAssociationImage("classes/association"),
                          getAssociationImage("classes/directed-association"),
                          getAssociationImage("classes/aggregation"),
                          getAssociationImage("classes/directed-aggregation"),
                          getAssociationImage("classes/composition"),
                          getAssociationImage("classes/directed-composition"),
                          getAssociationImage("generalization"),
                          getAssociationImage("classes/realization"),
                          getAssociationImage("classes/abstraction"),
                          getAssociationImage("classes/usage"),
                          getAssociationImage("dependency"),
                          getAssociationImage("requires"),
                          getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        getClickButton().setBackground(getDefaultColor());
        getPackageButton().setBackground(getDefaultColor());
        getClassButton().setBackground(getDefaultColor());
        getInterfaceButton().setBackground(getDefaultColor());
    }
    
    @Override
    public PanelClassDiagram getPanelDiagram() {
        return (PanelClassDiagram) panel;
    }
    
    /**
     * Method responsible for returning the Package Button.
     * @return Package Button.
     */
    public JButton getPackageButton() {
        return getButton("package");
    }
    
    /**
     * Method responsible for returning the Class Button.
     * @return Class Button.
     */
    public JButton getClassButton() {
        return getButton("class");
    }
    
    /**
     * Method responsible for returning the Interface Button.
     * @return Interface Button.
     */
    public JButton getInterfaceButton() {
        return getButton("interface");
    }
}
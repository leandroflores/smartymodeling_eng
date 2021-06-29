package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelComponentOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelComponentDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelComponentOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Component Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-04-09
 * @see    controller.view.panel.operation.types.ControllerPanelComponentOperation
 * @see    view.panel.diagram.types.PanelComponentDiagram
 * @see    view.panel.operation.PanelOperation
 */
public final class PanelComponentOperation extends PanelOperation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Component Diagram.
     */
    public PanelComponentOperation(PanelComponentDiagram panel) {
        super(panel);
        controller = new ControllerPanelComponentOperation(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        add(createButton("component", "", "New Component", "diagram/component/component.png"));
        add(createButton("interface", "", "New Interface", "diagram/component/interface.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {getAssociationImage("component/provide"),
                          getAssociationImage("component/require"),
                          getAssociationImage("dependency"),
                          getAssociationImage("requires"),
                          getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        getClickButton().setBackground(getDefaultColor());
        getComponentButton().setBackground(getDefaultColor());
        getInterfaceButton().setBackground(getDefaultColor());
    }
    
    @Override
    public PanelComponentDiagram getPanelDiagram() {
        return (PanelComponentDiagram) panel;
    }
    
    /**
     * Method responsible for returning the Component Button.
     * @return Component Button.
     */
    public JButton getComponentButton() {
        return getButton("component");
    }
    
    /**
     * Method responsible for returning the Interface Button.
     * @return Interface Button.
     */
    public JButton getInterfaceButton() {
        return getButton("interface");
    }
}
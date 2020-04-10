package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelComponentOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelComponentDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelComponentOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Component Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2019
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
        this.controller = new ControllerPanelComponentOperation(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        this.add(this.createButton("componentButton", "", "New Component", "diagram/component/component.png"));
        this.add(this.createButton("interfaceButton", "", "New Interface", "diagram/component/interface.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {this.getAssociationImage("component/provide"),
                          this.getAssociationImage("component/require"),
                          this.getAssociationImage("dependency"),
                          this.getAssociationImage("requires"),
                          this.getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getComponentButton().setBackground(this.getDefaultColor());
        this.getInterfaceButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public PanelComponentDiagram getPanelDiagram() {
        return (PanelComponentDiagram) this.panelDiagram;
    }
    
    /**
     * Method responsible for returning the Component Button.
     * @return Component Button.
     */
    public JButton getComponentButton() {
        return this.buttons.get("componentButton");
    }
    
    /**
     * Method responsible for returning the Interface Button.
     * @return Interface Button.
     */
    public JButton getInterfaceButton() {
        return this.buttons.get("interfaceButton");
    }
}
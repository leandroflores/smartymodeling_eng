package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelClassOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelClassDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelClassOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Class Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2019
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
        this.controller = new ControllerPanelClassOperation(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        this.add(this.createButton("packageButton",   "", "New Package",   "diagram/classes/package.png"));
        this.add(this.createButton("classButton",     "", "New Class",     "diagram/classes/class.png"));
        this.add(this.createButton("interfaceButton", "", "New Interface", "diagram/classes/interface.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {this.getAssociationImage("classes/association"),
                          this.getAssociationImage("classes/directed-association"),
                          this.getAssociationImage("classes/aggregation"),
                          this.getAssociationImage("classes/directed-aggregation"),
                          this.getAssociationImage("classes/composition"),
                          this.getAssociationImage("classes/directed-composition"),
                          this.getAssociationImage("generalization"),
                          this.getAssociationImage("classes/realization"),
                          this.getAssociationImage("dependency"),
                          this.getAssociationImage("requires"),
                          this.getAssociationImage("mutex")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getPackageButton().setBackground(this.getDefaultColor());
        this.getClassButton().setBackground(this.getDefaultColor());
        this.getInterfaceButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public PanelClassDiagram getPanelDiagram() {
        return (PanelClassDiagram) this.panelDiagram;
    }
    
    /**
     * Method responsible for returning the Package Button.
     * @return Package Button.
     */
    public JButton getPackageButton() {
        return this.buttons.get("packageButton");
    }
    
    /**
     * Method responsible for returning the Class Button.
     * @return Class Button.
     */
    public JButton getClassButton() {
        return this.buttons.get("classButton");
    }
    
    /**
     * Method responsible for returning the Interface Button.
     * @return Interface Button.
     */
    public JButton getInterfaceButton() {
        return this.buttons.get("interfaceButton");
    }
}
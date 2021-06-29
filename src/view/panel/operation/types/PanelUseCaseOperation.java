package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelUseCaseOperation;
import javax.swing.JButton;
import view.panel.diagram.types.PanelUseCaseDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelUseCaseOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Use Case Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-04-09
 * @see    controller.view.panel.operation.types.ControllerPanelUseCaseOperation
 * @see    view.panel.operation.PanelOperation
 * @see    view.panel.diagram.types.PanelUseCaseDiagram
 */
public final class PanelUseCaseOperation extends PanelOperation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Use Case Diagram.
     */
    public PanelUseCaseOperation(PanelUseCaseDiagram panel) {
        super(panel);
        controller = new ControllerPanelUseCaseOperation(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        add(createButton("actor",    "", "New Actor",    "diagram/usecase/actor.png"));
        add(createButton("use_case", "", "New Use Case", "diagram/usecase/use-case.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {getAssociationImage("usecase/communication"),
                          getAssociationImage("usecase/extend"),
                          getAssociationImage("usecase/include"),
                          getAssociationImage("generalization"),
                          getAssociationImage("requires"),
                          getAssociationImage("mutex"),
                          getAssociationImage("dependency")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        getClickButton().setBackground(getDefaultColor());
        getActorButton().setBackground(getDefaultColor());
        getUseCaseButton().setBackground(getDefaultColor());
    }
    
    @Override
    public PanelUseCaseDiagram getPanelDiagram() {
        return (PanelUseCaseDiagram) panel;
    }
    
    /**
     * Method responsible for returning the Actor Button.
     * @return Actor Button.
     */
    public JButton getActorButton() {
        return getButton("actor");
    }
    
    /**
     * Method responsible for returning the Use Case Button.
     * @return Use Case Button.
     */
    public JButton getUseCaseButton() {
        return getButton("use_case");
    }
}
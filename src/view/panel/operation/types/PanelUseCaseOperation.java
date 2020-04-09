package view.panel.operation.types;

import javax.swing.JButton;
import view.panel.diagram.types.PanelUseCaseDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of View <b>PanelUseCaseOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Use Case Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2019
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
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void addDiagramButtons() {
        this.add(this.createButton("actorButton",   "", "New Actor",    "diagram/usecase/actor.png"));
        this.add(this.createButton("useCaseButton", "", "New Use Case", "diagram/usecase/use-case.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons = {this.getAssociationImage("association"),
                          this.getAssociationImage("usecase/extend"),
                          this.getAssociationImage("usecase/include"),
                          this.getAssociationImage("generalization"),
                          this.getAssociationImage("requires"),
                          this.getAssociationImage("mutex"),
                          this.getAssociationImage("dependency")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getActorButton().setBackground(this.getDefaultColor());
        this.getUseCaseButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public PanelUseCaseDiagram getPanelDiagram() {
        return (PanelUseCaseDiagram) this.panelDiagram;
    }
    
    /**
     * Method responsible for returning the Actor Button.
     * @return Actor Button.
     */
    public JButton getActorButton() {
        return this.buttons.get("actorButton");
    }
    
    /**
     * Method responsible for returning the Use Case Button.
     * @return Use Case Button.
     */
    public JButton getUseCaseButton() {
        return this.buttons.get("useCaseButton");
    }
}
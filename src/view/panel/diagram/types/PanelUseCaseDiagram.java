package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationUseCase;
import controller.view.panel.diagram.types.ControllerPanelUseCaseDiagram;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.structural.base.Element;
import model.structural.diagram.UseCaseDiagram;
import view.panel.diagram.PanelDiagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelUseCaseDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Use Case Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.panel.diagram.association.types.ControllerEventAssociationUseCase
 * @see    controller.view.panel.diagram.types.ControllerPanelUseCaseDiagram
 * @see    model.structural.diagram.UseCaseDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelUseCaseDiagram extends PanelDiagram {
    private final UseCaseDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Use Case Diagram.
     */
    public PanelUseCaseDiagram(ViewMenu view, UseCaseDiagram diagram) {
        super(view, diagram);
        this.diagram    = diagram;
        this.controller = new ControllerPanelUseCaseDiagram(this);
        this.initComponents();
        this.addComponents();
        this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addOperationsPanel();
        this.addPanelModeling();
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationUseCase(this));
    }
    
    @Override
    public void addOperationsPanel() {
        JPanel panel = new JPanel();
               panel.add(this.createButton("clickButton",       "", "Select",          "click.png"));
               panel.add(this.createButton("actorButton",       "", "New Actor",       "diagram/usecase/actor.png"));
               panel.add(this.createButton("useCaseButton",     "", "New Use Case",    "diagram/usecase/use-case.png"));
               panel.add(this.createButton("variabilityButton", "", "New Variability", "variability.png"));
               panel.add(this.createButton("editButton",        "", "Edit",            "edit.png"));
               panel.add(this.createButton("deleteButton",      "", "Delete",          "delete.png"));
               panel.add(this.createComboBox("associationComboBox", this.getAssociationItems(), 50));
       this.add(panel);
//       this.add(new JSeparator(), BorderLayout.PAGE_END);
       this.getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    public Object[] getAssociationItems() {
        Object[] items  = {
                 this.getAssociationImage("association"),
                 this.getAssociationImage("usecase/extend"),
                 this.getAssociationImage("usecase/include"),
                 this.getAssociationImage("generalization"),
                 this.getAssociationImage("requires"),
                 this.getAssociationImage("mutex"),
                 this.getAssociationImage("dependency")};
        return   items;
    }
    
    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getActorButton().setBackground(this.getDefaultColor());
        this.getUseCaseButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setRealizationStyle();
                break;
            case 1:
            case 2:
            case 4:
            case 5:
                this.setExtendStyle();
                break;
            case 3:
                this.setGeneralizationStyle();
                break;
            default:
                this.setDependenyStyle();
                break;
        }
    }
    
    /**
     * Method responsible for setting Realization Style.
     */
    private void setRealizationStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    /**
     * Method responsible for setting Extend Style.
     */
    private void setExtendStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_OPEN);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    @Override
    public UseCaseDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning Actor Button.
     * @return Actor Button.
     */
    public JButton getActorButton() {
        return this.buttons.get("actorButton");
    }
    
    /**
     * Method responsible for returning Use Case Button.
     * @return Use Case Button.
     */
    public JButton getUseCaseButton() {
        return this.buttons.get("useCaseButton");
    }
}
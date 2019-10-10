package view.panel.instance.types;

import controller.view.panel.instance.ControllerPanelInstance;
import javax.swing.BoxLayout;
import model.structural.base.product.Instance;
import model.structural.diagram.UseCaseDiagram;
import view.panel.instance.PanelInstance;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelUseCaseInstance</b>.</p>
 * <p>Class responsible for defining the <b>Use Case Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    controller.view.panel.instance.event.ControllerEventMove
 * @see    controller.view.panel.instance.event.ControllerEventResize
 * @see    model.structural.diagram.UseCaseDiagram
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelUseCaseInstance extends PanelInstance {
    private final UseCaseDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     * @param diagram Use Case Diagram.
     */
    public PanelUseCaseInstance(ViewMenu view, Instance instance, UseCaseDiagram diagram) {
        super(view, instance);
        this.diagram    = diagram;
        this.controller = new ControllerPanelInstance(this);
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addInstancePanel();
        this.addControllers();
    }
    
    /**
     * Method responsible for returning the Use Case Diagram.
     * @return Use Case Diagram.
     */
    public UseCaseDiagram getDiagram() {
        return this.diagram;
    }
}
package view.panel.instance.types;

import controller.view.panel.instance.ControllerPanelInstance;
import javax.swing.BoxLayout;
import model.structural.base.product.Instance;
import model.structural.diagram.ActivityDiagram;
import view.panel.instance.PanelInstance;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelActivityInstance</b>.</p>
 * <p>Class responsible for defining the <b>Activity Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    controller.view.panel.instance.event.ControllerEventMove
 * @see    controller.view.panel.instance.event.ControllerEventResize
 * @see    model.structural.diagram.ActivityDiagram
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelActivityInstance extends PanelInstance {
    private final ActivityDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     * @param diagram Activity Diagram.
     */
    public PanelActivityInstance(ViewMenu view, Instance instance, ActivityDiagram diagram) {
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
     * Method responsible for returning the Activity Diagram.
     * @return Activity Diagram.
     */
    public ActivityDiagram getDiagram() {
        return this.diagram;
    }
}
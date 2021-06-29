package view.panel.instance.types;

import controller.view.panel.instance.ControllerPanelInstance;
import model.structural.base.product.Instance;
import model.structural.diagram.UseCaseDiagram;
import view.panel.instance.PanelInstance;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelUseCaseInstance</b>.</p>
 * <p>Class responsible for defining the <b>Use Case Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-06
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelUseCaseInstance extends PanelInstance {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Use Case Instance.
     */
    public PanelUseCaseInstance(ViewMenu view, Instance instance) {
        super(view, instance);
        controller = new ControllerPanelInstance(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    public UseCaseDiagram getDiagram() {
        return (UseCaseDiagram) instance.getDiagram();
    }
}
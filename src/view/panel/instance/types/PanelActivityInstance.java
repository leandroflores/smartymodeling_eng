package view.panel.instance.types;

import controller.view.panel.instance.ControllerPanelInstance;
import model.structural.base.product.Instance;
import view.panel.instance.PanelInstance;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelActivityInstance</b>.</p>
 * <p>Class responsible for defining the <b>Activity Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelActivityInstance extends PanelInstance {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Activity Instance.
     */
    public PanelActivityInstance(ViewMenu view, Instance instance) {
        super(view, instance);
        controller = new ControllerPanelInstance(this);
        setDefaultProperties();
        addComponents();
    }
}
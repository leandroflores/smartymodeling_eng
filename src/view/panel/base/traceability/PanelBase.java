package view.panel.base.traceability;

import model.structural.base.traceability.Reference;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Reference Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBase
 * @see    model.structural.base.traceability.Reference
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final Reference reference;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param reference Reference.
     */
    public PanelBase(ViewMenu view, Reference reference) {
        super(view);
        this.reference = reference;
    }
    
    /**
     * Method responsible for returning the Reference.
     * @return Reference.
     */
    public Reference getReference() {
        return reference;
    }
}
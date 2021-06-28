package view.panel.base.traceability;

import model.structural.base.traceability.Traceability;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Traceability Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBase
 * @see    model.structural.base.traceability.Traceability
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param traceability Traceability.
     */
    public PanelBase(ViewMenu view, Traceability traceability) {
        super(view);
        this.traceability = traceability;
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return traceability;
    }
}
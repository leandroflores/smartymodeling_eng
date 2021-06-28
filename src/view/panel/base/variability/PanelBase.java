package view.panel.base.variability;

import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Variability Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.panel.base.variability.ControllerPanelBase
 * @see    model.structural.base.variability.Variability
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final Diagram diagram;
    protected final Variability variability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public PanelBase(ViewMenu view, Diagram diagram, Variability variability) {
        super(view);
        this.diagram     = diagram;
        this.variability = variability;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    public Variability getVariability() {
        return variability;
    }
}
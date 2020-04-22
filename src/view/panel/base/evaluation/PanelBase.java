package view.panel.base.evaluation;

import model.structural.base.evaluation.Metric;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Metric Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param metric Metric.
     */
    public PanelBase(ViewMenu view, Metric metric) {
        super(view);
        this.metric = metric;
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
}
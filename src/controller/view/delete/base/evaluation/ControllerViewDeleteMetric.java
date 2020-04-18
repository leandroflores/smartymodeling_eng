package controller.view.delete.base.evaluation;

import controller.view.delete.ControllerViewDelete;
import model.structural.base.evaluation.Metric;
import view.delete.base.evaluation.ViewDeleteMetric;

/**
 * <p>Class of Controller <b>ControllerViewDeleteMetric</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteMetric</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.delete.ControllerViewDelete
 * @see    model.structural.base.evaluation.Metric
 * @see    view.delete.base.evaluation.ViewDeleteMetric
 */
public final class ControllerViewDeleteMetric extends ControllerViewDelete {
    private final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param viewDelete View Delete Metric.
     */
    public ControllerViewDeleteMetric(ViewDeleteMetric viewDelete) {
        super(viewDelete);
        this.metric = this.getView().getMetric();
    }
    
    @Override
    public void delete() {
        this.getView().getProject().removeMetric(this.metric);
        this.close();
    }
    
    @Override
    public ViewDeleteMetric getView() {
        return (ViewDeleteMetric) this.viewModal;
    }
}
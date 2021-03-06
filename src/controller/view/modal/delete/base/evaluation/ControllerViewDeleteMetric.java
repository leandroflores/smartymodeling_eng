package controller.view.modal.delete.base.evaluation;

import controller.view.modal.delete.ControllerViewDelete;
import model.structural.base.evaluation.Metric;
import view.modal.delete.base.evaluation.ViewDeleteMetric;

/**
 * <p>Class of Controller <b>ControllerViewDeleteMetric</b>.</p>
 * <p>Class responsible for controlling the <b>ViewDeleteMetric</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    model.structural.base.evaluation.Metric
 * @see    view.modal.delete.base.evaluation.ViewDeleteMetric
 */
public final class ControllerViewDeleteMetric extends ControllerViewDelete {
    private final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param view View Delete Metric.
     */
    public ControllerViewDeleteMetric(ViewDeleteMetric view) {
        super(view);
        metric = getView().getMetric();
    }
    
    @Override
    public void delete() {
        getView().getProject().removeMetric(metric);
        close();
    }
    
    @Override
    public ViewDeleteMetric getView() {
        return (ViewDeleteMetric) super.getView();
    }
}
package view.delete.base.evaluation;

import view.delete.ViewDelete;
import model.structural.base.evaluation.Metric;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteMetric</b>.</p>
 * <p>Class responsible for defining the <b>Metric Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.delete.ControllerViewDeleteDiagram
 * @see    model.structural.base.evaluation.Metric
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteMetric extends ViewDelete {
    private final Metric metric;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param metric Metric.
     */
    public ViewDeleteMetric(PanelModeling panel, Metric metric) {
        super(panel);
        this.metric     = metric;
//        this.controller = new ControllerViewDeleteDiagram(this);
        this.title      = "Delete Metric";
        this.initComponents();
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.metric.getName());
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    public Metric getMetric() {
        return this.metric;
    }
}
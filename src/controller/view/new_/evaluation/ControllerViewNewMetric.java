package controller.view.new_.evaluation;

import controller.view.new_.ControllerViewNew;
import java.awt.event.ActionEvent;
import model.structural.base.evaluation.Metric;
import view.new_.evaluation.ViewNewMetric;

/**
 * <p>Class of Controller <b>ControllerViewNewMetric</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewMetric</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/08/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    view.new_.evaluation.ViewNewMetric
 */
public class ControllerViewNewMetric extends ControllerViewNew {
    private final ViewNewMetric viewNewMetric;

    /**
     * Default constructor method of Class.
     * @param viewNew View New Metric.
     */
    public ControllerViewNewMetric(ViewNewMetric viewNew) {
        super(viewNew);
        this.viewNewMetric = viewNew;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }

    /**
     * Method responsible for checking the Metric Name.
     * @return Name is checked.
     */
    public boolean checkName() {
        return this.check(this.viewNewMetric.getPanelBaseMetric().getNameTextField(), "Name is required!");
    }
    
    /**
     * Method responsible for checking the Metric Label.
     * @return Label is checked.
     */
    public boolean checkLabel() {
        return this.check(this.viewNewMetric.getPanelBaseMetric().getLabelTextField(), "Label is required!");
    }
    
    /**
     * Method responsible for checking the Metric Operation.
     * @return Operation is checked.
     */
    public boolean checkOperation() {
        return this.check(this.viewNewMetric.getPanelBaseOperation().getOperationTextField(), "Operation is required!");
    }
    
    @Override
    public boolean check() {
        return this.checkName()
            && this.checkLabel()
            && this.checkOperation();
    }

    @Override
    public void insert() {
        Metric metric = this.viewNewMetric.getMetric();
        this.viewNewMetric.getProject().addMetric(metric);
        this.viewNewMetric.getViewMenu().update();
        this.viewNewMetric.dispose();
    }
}
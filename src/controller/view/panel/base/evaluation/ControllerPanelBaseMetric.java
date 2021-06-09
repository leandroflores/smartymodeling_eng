package controller.view.panel.base.evaluation;

import view.panel.base.evaluation.PanelBaseMetric;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMetric</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseMetric</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    controller.view.panel.base.evaluation.ControllerPanelBase
 * @see    model.structural.base.evaluation.Metric
 * @see    view.panel.base.evaluation.PanelBaseMetric
 */
public class ControllerPanelBaseMetric extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Metric.
     */
    public ControllerPanelBaseMetric(PanelBaseMetric panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getMetric().setName(getString(getPanel().getNameTextField()));
        getMetric().setLabel(getString(getPanel().getLabelTextField()));
        getMetric().setDescription(getString(getPanel().getDescriptionTextArea()));
        refresh();
    }
    
    @Override
    public PanelBaseMetric getPanel() {
        return (PanelBaseMetric) panel;
    }
}
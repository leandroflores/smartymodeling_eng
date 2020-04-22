package controller.view.panel.base.evaluation;

import view.panel.base.evaluation.PanelBaseMetric;

/**
 * <p>Class of Controller <b>ControllerPanelBaseMetric</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseMetric</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-23
 * @see    controller.view.panel.base.evaluation.ControllerPanelBase
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
        this.getMetric().setName(this.getString(this.getPanel().getNameTextField()));
        this.getMetric().setLabel(this.getString(this.getPanel().getLabelTextField()));
        this.getMetric().setDescription(this.getString(this.getPanel().getDescriptionTextArea()));
        this.refresh();
    }
    
    @Override
    public PanelBaseMetric getPanel() {
        return (PanelBaseMetric) this.panel;
    }
}
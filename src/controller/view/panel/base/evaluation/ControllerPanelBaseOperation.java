package controller.view.panel.base.evaluation;

import view.panel.base.evaluation.PanelBaseOperation;

/**
 * <p>Class of Controller <b>ControllerPanelBaseOperation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseOperation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-20
 * @see    controller.view.panel.base.evaluation.ControllerPanelBase
 * @see    view.panel.base.evaluation.PanelBaseOperation
 */
public class ControllerPanelBaseOperation extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Operation.
     */
    public ControllerPanelBaseOperation(PanelBaseOperation panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getPanel().setTarget();
        getMetric().setOperation(getString(getPanel().getOperationTextField()));
        refresh();
    }
    
    @Override
    public PanelBaseOperation getPanel() {
        return (PanelBaseOperation) panel;
    }
}
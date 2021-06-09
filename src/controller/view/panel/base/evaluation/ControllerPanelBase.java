package controller.view.panel.base.evaluation;

import java.awt.event.KeyEvent;
import model.structural.base.evaluation.Metric;
import view.panel.base.evaluation.PanelBase;

/**
 * <p>Class of Controller <b>ControllerPanelBase</b>.</p>
 * <p>Class responsible for controlling the <b>Metric Panel Base</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-22
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.evaluation.PanelBase
 */
public abstract class ControllerPanelBase extends controller.view.panel.base.ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base.
     */
    public ControllerPanelBase(PanelBase panel) {
        super(panel);
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (ready)
            update();
    }
    
    @Override
    protected void refresh() {
        getPanelTree().updateNode(getMetric());
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Metric.
     * @return Metric.
     */
    protected Metric getMetric() {
        return getPanel().getMetric();
    }
    
    @Override
    public PanelBase getPanel() {
        return (PanelBase) panel;
    }
}
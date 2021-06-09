package controller.view.panel.base.variability;

import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.variability.Variability;
import view.panel.base.variability.PanelBase;

/**
 * <p>Class of Controller <b>ControllerPanelBase</b>.</p>
 * <p>Class responsible for controlling the <b>Variability Panel Base</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-16
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.variability.Variability
 * @see    view.panel.base.variability.PanelBase
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
        getPanelTree().updateNode(getVariability());
        getPanelModeling().updateDiagram(getPanel().getDiagram());
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    protected Diagram getDiagram() {
        return getPanel().getDiagram();
    }
    
    /**
     * Method responsible for returning the Variability.
     * @return Variability.
     */
    protected Variability getVariability() {
        return getPanel().getVariability();
    }
    
    @Override
    public PanelBase getPanel() {
        return (PanelBase) panel;
    }
}
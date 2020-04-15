package controller.view.edit.panel.base.feature;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.feature.PanelBaseFeature;

/**
 * <p>Class of Controller <b>ControllerPanelBaseFeature</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseFeature</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.feature.PanelBaseFeature
 */
public class ControllerPanelBaseFeature extends ControllerPanel {
    private final PanelBaseFeature panelBaseFeature;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Base Feature.
     */
    public ControllerPanelBaseFeature(PanelBaseFeature panel) {
        super(panel);
        this.panelBaseFeature = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseFeature.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    /**
     * Method responsible for setting the Feature Values.
     */
    private void update() {
        this.panelBaseFeature.getFeature().setName(this.panelBaseFeature.getNameTextField().getText().trim());
        this.panelBaseFeature.getFeature().setAbstract(this.panelBaseFeature.getAbstractCheckBox().isSelected());
        this.panelBaseFeature.getViewMenu().getPanelProject().getPanelTree().updateNode(this.panelBaseFeature.getFeature());
        this.panelBaseFeature.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseFeature.getDiagram());
        this.panelBaseFeature.getViewMenu().setSave(false);
    }
}
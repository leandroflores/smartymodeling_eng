package controller.view.edit.panel.base;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.edit.panel.base.PanelBaseDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelBaseDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseDiagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  12/06/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.PanelBaseDiagram
 */
public class ControllerPanelBaseDiagram extends ControllerPanel {
    private final PanelBaseDiagram panelBaseDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Diagram.
     */
    public ControllerPanelBaseDiagram(PanelBaseDiagram panel) {
        super(panel);
        this.panelBaseDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseDiagram.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseDiagram.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for checking the Diagram.
     * @return Diagram checked.
     */
    private boolean check() {
        return this.check(this.panelBaseDiagram.getNameTextField().getText());
    }
    
    /**
     * Method responsible for setting the Diagram Values.
     */
    private void update() {
        this.panelBaseDiagram.getDiagram().setName(this.panelBaseDiagram.getNameTextField().getText().trim());
        this.panelBaseDiagram.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseDiagram.getViewMenu().getPanelModeling().updateDiagram(this.panelBaseDiagram.getDiagram());
        this.panelBaseDiagram.getViewMenu().setSave(false);
    }
}
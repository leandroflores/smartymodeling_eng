package controller.view.edit.panel.base;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import view.edit.panel.base.PanelBaseProject;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProject</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseProject</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  11/06/2019
 * @see    controller.view.ControllerPanel
 * @see    view.edit.panel.base.PanelBaseProject
 */
public class ControllerPanelBaseProject extends ControllerPanel {
    private final PanelBaseProject panelBaseProject;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Project.
     */
    public ControllerPanelBaseProject(PanelBaseProject panel) {
        super(panel);
        this.panelBaseProject = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (this.panelBaseProject.getNameTextField().equals(event.getSource()))
            this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        if (this.panelBaseProject.getNameTextField().equals(event.getSource()))
            this.update();
    }

    /**
     * Method responsible for checking the Project.
     * @return Project checked.
     */
    private boolean check() {
        return this.check(this.panelBaseProject.getNameTextField().getText());
    }
    
    /**
     * Method responsible for setting the Project Values.
     */
    private void update() {
        this.panelBaseProject.getProject().setName(this.panelBaseProject.getNameTextField().getText().trim());
        this.panelBaseProject.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseProject.getViewMenu().getPanelModeling().updateUI();
        this.panelBaseProject.getViewMenu().setSave(false);
    }
}
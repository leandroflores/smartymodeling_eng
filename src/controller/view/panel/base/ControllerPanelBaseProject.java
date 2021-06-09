package controller.view.panel.base;

import view.panel.base.PanelBaseProject;

/**
 * <p>Class of Controller <b>ControllerPanelBaseProject</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseProject</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-11
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.PanelBaseProject
 */
public class ControllerPanelBaseProject extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Project.
     */
    public ControllerPanelBaseProject(PanelBaseProject panel) {
        super(panel);
    }
    
    @Override
    protected void refresh() {
        getPanelTree().updateNode(getPanel().getProject());
        super.refresh();
    }
    
    @Override
    protected void update() {
        getProject().setName(getPanel().getNameTextField().getText().trim());
        getProject().setVersion(getPanel().getVersionTextField().getText().trim());
        refresh();
    }
    
    @Override
    public PanelBaseProject getPanel() {
        return (PanelBaseProject) panel;
    }
}
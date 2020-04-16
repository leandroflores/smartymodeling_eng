package controller.view.panel.base;

import view.panel.base.PanelBaseDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelBaseDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-12
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    view.panel.base.PanelBaseDiagram
 */
public class ControllerPanelBaseDiagram extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Diagram.
     */
    public ControllerPanelBaseDiagram(PanelBaseDiagram panel) {
        super(panel);
    }
    
    @Override
    protected void refresh() {
        this.getPanelTree().updateNode(this.getPanel().getDiagram());
        this.getPanelModeling().updateTab(this.getPanel().getDiagram());
        super.refresh();
    }
    
    @Override
    protected void update() {
        this.getPanel().getDiagram().setName(this.getPanel().getNameTextField().getText().trim());
        this.refresh();
    }
    
    @Override
    public PanelBaseDiagram getPanel() {
        return (PanelBaseDiagram) this.panel;
    }
}
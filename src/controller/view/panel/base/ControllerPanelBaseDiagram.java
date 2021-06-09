package controller.view.panel.base;

import model.structural.base.Diagram;
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
        getPanelTree().updateNode(getDiagram());
        getPanelModeling().updateTab(getDiagram());
        super.refresh();
    }
    
    @Override
    protected void update() {
        getDiagram().setName(getPanel().getNameTextField().getText().trim());
        refresh();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    private Diagram getDiagram() {
        return getPanel().getDiagram();
    }
    
    @Override
    public PanelBaseDiagram getPanel() {
        return (PanelBaseDiagram) panel;
    }
}
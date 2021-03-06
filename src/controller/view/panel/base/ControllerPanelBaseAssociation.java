package controller.view.panel.base;

import model.structural.base.Diagram;
import model.structural.base.association.Association;
import view.panel.base.PanelBaseAssociation;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAssociation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseAssociation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    controller.view.panel.base.ControllerPanelBase
 * @see    model.structural.base.association.Association
 * @see    view.panel.base.PanelBaseAssociation
 */
public abstract class ControllerPanelBaseAssociation extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Association.
     */
    public ControllerPanelBaseAssociation(PanelBaseAssociation panel) {
        super(panel);
    }

    @Override
    protected void refresh() {
        getPanelModeling().updateDiagram(getDiagram());
        getPanelModeling().updateInstancePanels();
        getPanelModeling().setSelected(getDiagram(), getAssociation().getId());
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
     * Method responsible for returning the Element.
     * @return Element.
     */
    protected Association getAssociation() {
        return getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseAssociation getPanel() {
        return (PanelBaseAssociation) panel;
    }
}
package controller.view.panel.base.diagram.classes.base.association;

import controller.view.panel.base.ControllerPanelBaseAssociation;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.diagram.classes.base.association.PanelBaseAssociationUML;

/**
 * <p>Class of Controller <b>ControllerPanelBaseAssociationUML</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseAssociationUML</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-16
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.panel.base.diagram.classes.base.association.PanelBaseAssociationUML
 */
public class ControllerPanelBaseAssociationUML extends ControllerPanelBaseAssociation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Association UML.
     */
    public ControllerPanelBaseAssociationUML(PanelBaseAssociationUML panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        getAssociation().setName(getString(getPanel().getNameTextField()));
        getAssociation().setDirection(getPanel().getDirectedCheckBox().isSelected());
        getPanel().updatePanelBaseSource();
        refresh();
    }
    
    @Override
    protected AssociationUML getAssociation() {
        return getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseAssociationUML getPanel() {
        return (PanelBaseAssociationUML) panel;
    }
}
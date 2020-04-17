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
        this.getAssociation().setName(this.getString(this.getPanel().getNameTextField()));
        this.getAssociation().setDirection(this.getPanel().getDirectedCheckBox().isSelected());
        this.getPanel().updatePanelBaseSource();
        super.refresh();
    }
    
    @Override
    protected AssociationUML getAssociation() {
        return this.getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseAssociationUML getPanel() {
        return (PanelBaseAssociationUML) this.panel;
    }
}
package controller.view.panel.base.diagram.classes.base.association;

import controller.view.panel.base.ControllerPanelBaseAssociation;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.diagram.classes.base.association.PanelBaseTarget;

/**
 * <p>Class of Controller <b>ControllerPanelBaseTarget</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseTarget</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-16
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    view.panel.base.diagram.classes.base.association.PanelBaseTarget
 */
public class ControllerPanelBaseTarget extends ControllerPanelBaseAssociation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Target.
     */
    public ControllerPanelBaseTarget(PanelBaseTarget panel) {
        super(panel);
    }

    /**
     * Method responsible for updating the Target Cardinality.
     * @return Source Cardinality.
     */
    private void updateCardinality() {
        String value = this.getString(this.getPanel().getCardinalityTextField());
        if (this.checkCardinality(value)) {
            this.getAssociation().setTargetMin(this.getMin(value));
            this.getAssociation().setTargetMax(this.getMax(value));
        }
    }
    
    @Override
    protected void update() {
        this.getAssociation().setTargetVisibility(this.getValue(this.getPanel().getVisibilityComboBox()));
        this.getAssociation().setTargetName(this.getString(this.getPanel().getNameTextField()));
        this.updateCardinality();
        super.refresh();
    }
    
    @Override
    protected AssociationUML getAssociation() {
        return this.getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseTarget getPanel() {
        return (PanelBaseTarget) this.panel;
    }
}
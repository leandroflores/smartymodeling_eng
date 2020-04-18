package controller.view.panel.base.diagram.classes.base.association;

import controller.view.panel.base.ControllerPanelBaseAssociation;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.diagram.classes.base.association.PanelBaseSource;

/**
 * <p>Class of Controller <b>ControllerPanelBaseSource</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseSource</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-16
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    view.panel.base.diagram.classes.base.association.PanelBaseSource
 */
public class ControllerPanelBaseSource extends ControllerPanelBaseAssociation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Source.
     */
    public ControllerPanelBaseSource(PanelBaseSource panel) {
        super(panel);
    }
    
    /**
     * Method responsible for updating the Source Name.
     */
    private void updateName() {
        if (!this.getAssociation().isDirection())
            this.getAssociation().setSourceName(this.getString(this.getPanel().getNameTextField()));
    }
    
    /**
     * Method responsible for updating the Source Cardinality.
     * @return Source Cardinality.
     */
    private void updateCardinality() {
        String value = this.getString(this.getPanel().getCardinalityTextField());
        if (this.checkCardinality(value)) {
            this.getAssociation().setSourceMin(this.getMin(value));
            this.getAssociation().setSourceMax(this.getMax(value));
        }
    }
    
    @Override
    protected void update() {
        this.getAssociation().setSourceVisibility(this.getValue(this.getPanel().getVisibilityComboBox()));
        this.updateName();
        this.updateCardinality();
        super.refresh();
    }
    
    @Override
    protected AssociationUML getAssociation() {
        return this.getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseSource getPanel() {
        return (PanelBaseSource) this.panel;
    }
}
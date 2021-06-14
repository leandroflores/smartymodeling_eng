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
 * @see    model.structural.diagram.classes.base.association.AssociationUML
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
        if (!getAssociation().isDirection())
            getAssociation().setSourceName(getString(getPanel().getNameTextField()));
    }
    
    /**
     * Method responsible for updating the Source Cardinality.
     * @return Source Cardinality.
     */
    private void updateCardinality() {
        String value = getString(getPanel().getCardinalityTextField());
        if (checkCardinality(value)) {
            getAssociation().setSourceMin(getMin(value));
            getAssociation().setSourceMax(getMax(value));
        }
    }
    
    @Override
    protected void update() {
        getAssociation().setSourceVisibility(getValue(getPanel().getVisibilityComboBox()));
        updateName();
        updateCardinality();
        refresh();
    }
    
    @Override
    protected AssociationUML getAssociation() {
        return getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseSource getPanel() {
        return (PanelBaseSource) panel;
    }
}
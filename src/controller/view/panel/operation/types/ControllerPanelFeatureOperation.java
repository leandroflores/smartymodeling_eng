package controller.view.panel.operation.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.feature.base.association.Combination;
import view.panel.operation.types.PanelFeatureOperation;

/**
 * <p>Class of Controller <b>ControllerPanelFeatureOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Feature Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
 * @see    controller.view.panel.operation.ControllerPanelOperation
 * @see    view.panel.operation.types.PanelFeatureOperation
 */
public class ControllerPanelFeatureOperation extends ControllerPanelOperation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Operation.
     */
    public ControllerPanelFeatureOperation(PanelFeatureOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.getPanelOperation().getFeatureButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getFeatureButton(), "Feature");
        else if (this.getPanelOperation().getInclusiveButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getInclusiveButton(), "Inclusive");
        else if (this.getPanelOperation().getExclusiveButton().equals(event.getSource()))
            this.setOperation(this.getPanelOperation().getExclusiveButton(), "Exclusive");
    }
    
    @Override
    public void delete() {
        mxCell cell   = (mxCell) this.getPanelDiagram().getGraph().getSelectionCell();
        String id     = this.getPanelDiagram().getIdentifiers().get(cell);
        Object object = this.getPanelDiagram().getDiagram().getAssociation(id);
        if (this.getPanelDiagram().getDiagram().getElement(id) != null)
            super.delete(this.getPanelDiagram().getDiagram().getElement(id));
        else if (object != null && !(object instanceof Combination))
            this.delete(this.getPanelDiagram().getDiagram().getAssociation(id));
        else if (object != null && object instanceof Combination) {
            this.delete((Combination) object);
        }
    }
    
    /**
     * Method responsible for deleting the Combination.
     * @param combination Combination.
     */
    private void delete(Combination combination) {
        Variability variability = combination.getSource();
        if (!variability.getVariationPoint().equals(combination.getTarget())) {
            variability.removeVariant(combination.getTarget());
            super.delete(combination);
        }
    }
    
    @Override
    protected PanelFeatureOperation getPanelOperation() {
        return (PanelFeatureOperation) this.panel;
    }
}
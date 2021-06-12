package controller.view.panel.operation.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.operation.ControllerPanelOperation;
import java.awt.event.ActionEvent;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.feature.base.association.Combination;
import view.panel.operation.types.PanelFeatureOperation;

/**
 * <p>Class of Controller <b>ControllerPanelFeatureOperation</b>.</p>
 * <p>Class responsible for controlling the <b>PanelFeatureOperation</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-10
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
        if (getPanel().getFeatureButton().equals(event.getSource()))
            setOperation(getPanel().getFeatureButton(), "Feature");
        else if (getPanel().getInclusiveButton().equals(event.getSource()))
            setOperation(getPanel().getInclusiveButton(), "Inclusive");
        else if (getPanel().getExclusiveButton().equals(event.getSource()))
            setOperation(getPanel().getExclusiveButton(), "Exclusive");
    }
    
    @Override
    public void delete() {
        mxCell cell   = (mxCell) getPanelDiagram().getGraph().getSelectionCell();
        String id     = getPanelDiagram().getIdentifiers().get(cell);
        Object object = getPanelDiagram().getDiagram().getAssociation(id);
        if (getPanelDiagram().getDiagram().getElement(id) != null)
            super.delete(getPanelDiagram().getDiagram().getElement(id));
        else if (object != null && !(object instanceof Combination))
            delete(getPanelDiagram().getDiagram().getAssociation(id));
        else if (object != null && object instanceof Combination) {
            delete((Combination) object);
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
    public PanelFeatureOperation getPanel() {
        return (PanelFeatureOperation) panel;
    }
}
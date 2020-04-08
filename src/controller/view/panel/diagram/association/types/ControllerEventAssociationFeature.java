package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.association.Connection;
import view.panel.diagram.types.PanelFeatureDiagram;

/**
 * <p>Class of Controller <b>ControllerEventAssociationFeature</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Feature Diagram Association</b> of SMartyModeling.</p>
 * @author Henrique
 * @since  11/02/2020
 * @see    controller.view.panel.diagram.association.ControllerEventAssociation
 * @see    model.structural.diagram.FeatureDiagram
 * @see    view.panel.diagram.types.PanelFeatureDiagram
 */
public class ControllerEventAssociationFeature extends ControllerEventAssociation {
    private final PanelFeatureDiagram panelDiagram;
    private final FeatureDiagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Diagram.
     */
    public ControllerEventAssociationFeature(PanelFeatureDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
        this.diagram      = this.panelDiagram.getDiagram();
    }
    
    @Override
    public void addAssociation(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        if (this.check(source, target) && this.distinct(source, target))
            this.createAssociation(association);
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (this.panelDiagram.getType()) {
            case 0:
                this.addConnection(association);
                break;
            default:
                break;
        }
    }
    /**
     * Method responsible for adding a Connection.
     * @param association Association.
     */
    private void addConnection(mxCell association) {
        Connection connection = this.createRelationshipUML(association);
        if (connection != null)
            this.diagram.addConnection(connection);
    }

    /**
     * Method responsible for returning a new Connection.
     * @param  association Association.
     * @return Connection.
     */
    private Connection createRelationshipUML(mxCell association) {
        Element source = this.getSource(association);
        Element target = this.getTarget(association);
        return new Connection((Feature) source, (Feature) target);
    }
}
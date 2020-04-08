package controller.view.panel.diagram.association.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.association.ControllerEventAssociation;
import model.structural.base.Element;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.association.Connection;
import view.message.ViewError;
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
        if (this.check(source, target) 
         && this.distinct(source, target) 
         && this.isVoid(source, target))
            this.createAssociation(association);
    }
    
    /**
     * 
     * @param source
     * @param target
     * @return 
     */
    private boolean isVoid(Element source, Element target) {
        if (this.panelDiagram.getDiagram().existsConnection(source, target)) {
            new ViewError(this.panelDiagram.getViewMenu(), "Connection already exists!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public void createAssociation(mxCell association) {
        switch (this.panelDiagram.getType()) {
            case 0:
            case 1:
            case 2:
            case 3:
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
        Connection connection = this.createConnection(association);
        if (connection != null)
            this.diagram.addConnection(connection);
    }

    /**
     * Method responsible for returning a new Connection.
     * @param  association Association.
     * @return Connection.
     */
    private Connection createConnection(mxCell association) {
        Element source   = this.getSource(association);
        Element target   = this.getTarget(association);
        String  category = this.getCategory();
        return new Connection((Feature) source, (Feature) target, category);
    }
    
    /**
     * Method responsible for returning the Connection Category.
     * @return Connection Category.
     */
    private String getCategory() {
        switch (this.panelDiagram.getType()) {
            case 0:
                return "mandatory";
            case 1:
                return "optional";
            case 2:
                return "inclusive";
            case 3:
                return "exclusive";
            default:
                return "mandatory";
        }
    }
}
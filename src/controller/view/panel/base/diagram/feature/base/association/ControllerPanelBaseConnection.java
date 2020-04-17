package controller.view.panel.base.diagram.feature.base.association;

import controller.view.panel.base.ControllerPanelBaseAssociation;
import model.structural.diagram.feature.base.association.Connection;
import view.panel.base.diagram.feature.base.association.PanelBaseConnection;

/**
 * <p>Class of Controller <b>ControllerPanelBaseConnection</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseConnection</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-09
 * @see    controller.view.panel.base.ControllerPanelBaseAssociation
 * @see    view.panel.base.diagram.feature.base.association.PanelBaseConnection
 */
public class ControllerPanelBaseConnection extends ControllerPanelBaseAssociation {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Connection.
     */
    public ControllerPanelBaseConnection(PanelBaseConnection panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        this.getConnection().setCategory(this.getValue(this.getPanel().getCategoryComboBox()));
        super.refresh();
    }
    
    /**
     * Method responsible for returning the Connection.
     * @return Connection.
     */
    private Connection getConnection() {
        return this.getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseConnection getPanel() {
        return (PanelBaseConnection) this.panel;
    }
}
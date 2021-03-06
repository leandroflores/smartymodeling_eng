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
 * @see    model.structural.diagram.feature.base.association.Connection
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
        getConnection().setCategory(getValue(getPanel().getCategoryComboBox()));
        refresh();
    }
    
    /**
     * Method responsible for returning the Connection.
     * @return Connection.
     */
    private Connection getConnection() {
        return getPanel().getAssociation();
    }
    
    @Override
    public PanelBaseConnection getPanel() {
        return (PanelBaseConnection) panel;
    }
}
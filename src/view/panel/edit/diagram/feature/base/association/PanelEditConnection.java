package view.panel.edit.diagram.feature.base.association;

import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.association.Connection;
import view.panel.base.diagram.feature.base.association.PanelBaseConnection;
import view.panel.edit.base.PanelEditAssociation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditConnection</b>.</p> 
 * <p>Class responsible for defining a <b>Connection Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-09
 * @see    model.structural.diagram.feature.base.association.Connection
 * @see    view.panel.edit.base.PanelEditAssociation
 */
public final class PanelEditConnection extends PanelEditAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Feature Diagram.
     * @param connection Connection.
     */
    public PanelEditConnection(ViewMenu view, FeatureDiagram diagram, Connection connection) {
        super(view, diagram, connection);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseConnection();
    }
    
    /**
     * Method responsible for adding the Panel Base Connection.
     */
    protected void addPanelBaseConnection() {
        addPanel("base_connection", new PanelBaseConnection(this, getDiagram(), getAssociation()));
        createScrollPane("base_connection", getPanelBaseConnection());
        getScrollPane("base_connection").setViewportView(getPanelBaseConnection());
        tabbedPane.add("Connection", getScrollPane("base_connection"));
    }
    
    /**
     * Method responsible for returning the Panel Base Connection.
     * @return Panel Base Connection.
     */
    public PanelBaseConnection getPanelBaseConnection() {
        return (PanelBaseConnection) getPanel("base_connection");
    }
    
    @Override
    public Connection getAssociation() {
        return (Connection) association;
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) diagram;
    }
}
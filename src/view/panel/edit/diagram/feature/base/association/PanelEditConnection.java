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
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseConnection();
    }
    
    /**
     * Method responsible for adding the Panel Base Connection.
     */
    protected void addPanelBaseConnection() {
        this.addPanel("panelBaseConnection", new PanelBaseConnection(this, this.getDiagram(), this.getAssociation()));
        this.createScrollPane("scrollPanelBaseConnection",  this.getPanelBaseConnection());
        this.getScrollPane("scrollPanelBaseConnection").setViewportView(this.getPanelBaseConnection());
        this.tabbedPane.add("Connection", this.getScrollPane("scrollPanelBaseConnection"));
    }
    
    /**
     * Method responsible for returning the Panel Base Connection.
     * @return Panel Base Connection.
     */
    public PanelBaseConnection getPanelBaseConnection() {
        return (PanelBaseConnection) this.getPanel("panelBaseConnection");
    }
    
    @Override
    public Connection getAssociation() {
        return (Connection) this.association;
    }
    
    @Override
    public FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }
}
package view.panel.edit.base.feature.association;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.association.Connection;
import view.edit.panel.base.feature.association.PanelBaseConnection;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditConnection</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Connection</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2020
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditConnection extends PanelEdit {
    private final Project project;
    private final FeatureDiagram diagram;
    private final Connection connection;
    private PanelBaseConnection panelBaseConnection;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Feature Diagram.
     * @param connection Connection.
     */
    public PanelEditConnection(ViewMenu viewMenu, FeatureDiagram diagram, Connection connection) {
        super(viewMenu);
        this.project    = this.viewMenu.getProject();
        this.diagram    = diagram;
        this.connection = connection;
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseConnection();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Connection.
     */
    protected void addPanelBaseConnection() {
        this.panelBaseConnection  = new PanelBaseConnection(this, this.diagram, this.connection);
        this.createScrollPane("scrollPanelBaseConnection",  this.panelBaseConnection);
        this.getScrollPanelBaseConnection().setViewportView(this.panelBaseConnection);
        this.tabbedPane.add("Connection", this.getScrollPanelBaseConnection());
    }
    
    /**
     * Method responsible for returning the Connection.
     * @return Connection.
     */
    public Connection getAssociationUML() {
        return this.connection;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Connection.
     * @return Scroll Panel Base Connection.
     */
    public JScrollPane getScrollPanelBaseConnection() {
        return this.getScrollPane("scrollPanelBaseConnection");
    }
    
    /**
     * Method responsible for returning the Panel Base Connection.
     * @return Panel Base Connection.
     */
    public PanelBaseConnection getPanelBaseConnection() {
        return this.panelBaseConnection;
    }
}
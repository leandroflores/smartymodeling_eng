package view.panel.edit.base.product;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import view.edit.panel.base.product.PanelBaseInstance;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditInstance</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Instance</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  25/10/2019
 * @see    model.structural.base.product.Instance
 * @see    view.edit.panel.base.product.PanelBaseInstance
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditInstance extends PanelEdit {
    private final Project  project;
    private final Instance instance;
    private PanelBaseInstance panelBaseInstance;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param instance Instance.
     */
    public PanelEditInstance(ViewMenu viewMenu, Instance instance) {
        super(viewMenu);
        this.project  = this.viewMenu.getProject();
        this.instance = instance;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseInstance();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Instance.
     */
    private void addPanelBaseInstance() {
        this.panelBaseInstance = new PanelBaseInstance(this.viewMenu, this.instance);
        this.createScrollPane("scrollPanelBaseInstance", this.panelBaseInstance);
        this.getScrollPanelBaseInstance().setViewportView(this.panelBaseInstance);
        this.tabbedPane.add("Instance", this.getScrollPanelBaseInstance());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Instance.
     * @return Scroll Panel Base Instance.
     */
    public JScrollPane getScrollPanelBaseInstance() {
        return this.scrollPanes.get("scrollPanelBaseInstance");
    }
}
package view.panel.edit.base;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import view.edit.panel.base.PanelBaseProject;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditProject</b>.</p> 
 * <p>Class responsible for defining a <b>Project Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-11
 * @see    model.structural.base.Project
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditProject extends PanelEdit {
    private PanelBaseProject panelBaseProject;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelEditProject(ViewMenu view) {
        super(view);
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseProject();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Project.
     */
    private void addPanelBaseProject() {
        this.addPanel("panelBaseProject", new PanelBaseProject(this.viewMenu));
        this.createScrollPane("scrollPanelBaseProject",  this.getPanelBaseProject());
        this.getScrollPanelBaseProject().setViewportView(this.getPanelBaseProject());
        this.tabbedPane.add("Project", this.getScrollPanelBaseProject());
    }
    
    /**
     * Method responsible for returning the Panel Base Project.
     * @return Panel Base Project.
     */
    public PanelBaseProject getPanelBaseProject() {
        return (PanelBaseProject) this.getPanel("panelBaseProject");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Project.
     * @return Scroll Panel Base Project.
     */
    public JScrollPane getScrollPanelBaseProject() {
        return this.getScrollPane("scrollPanelBaseProject");
    }
}
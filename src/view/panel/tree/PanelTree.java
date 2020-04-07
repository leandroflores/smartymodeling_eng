package view.panel.tree;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTree</b>.</p>
 * <p>Class responsible for defining the <b>Project Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    model.structural.base.Project
 * @see    view.Panel
 */
public final class PanelTree extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private JTabbedPane tabbedPane;
    
    private PanelTreeUML panelTree1;
    private PanelTreeUML panelTree2;
    private PanelTreeUML panelTree3;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTree(ViewMenu viewMenu) {
        this.viewMenu   = viewMenu;
        this.project    = this.viewMenu.getProject();
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.initTabbedPane();
//            this.addPanelTreeRequirements();
//            this.addPanelTreeFeatures();
            this.addPanelTreeUML();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for initializing the Tabbed Pane.
     */
    protected void initTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(275, 260));
    }
    
    /**
     * Method responsible for adding the Panel Requirements.
     */
    protected void addPanelTreeRequirements() {
        this.panelTree1  = new PanelTreeUML(this.viewMenu);
        this.createScrollPane("scrollPanelTreeReq",  this.panelTree1);
        this.getScrollPanelTreeReq().setViewportView(this.panelTree1);
        this.tabbedPane.add("Req", this.getScrollPanelTreeReq());
    }
    
    /**
     * Method responsible for adding the Panel Features.
     */
    protected void addPanelTreeFeatures() {
        this.panelTree2  = new PanelTreeUML(this.viewMenu);
        this.createScrollPane("scrollPanelTreeFeat",  this.panelTree2);
        this.getScrollPanelTreeFeat().setViewportView(this.panelTree2);
        this.tabbedPane.add("Feat", this.getScrollPanelTreeFeat());
    }
    
    /**
     * Method responsible for adding the Panel Tree UML.
     */
    protected void addPanelTreeUML() {
        this.panelTree3  = new PanelTreeUML(this.viewMenu);
        this.createScrollPane("scrollPanelTreeUML",  this.panelTree3);
        this.getScrollPanelTreeUML().setViewportView(this.panelTree3);
        this.tabbedPane.add("UML", this.getScrollPanelTreeUML());
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Requirements.
     * @return Scroll Panel Tree Requirements.
     */
    public JScrollPane getScrollPanelTreeReq() {
        return this.scrollPanes.get("scrollPanelTreeReq");
    }
    
    /**
     * Method responsible for returning the Panel Tree Requirements.
     * @return Panel Tree Requirements.
     */
    public PanelTreeUML getPanelTreeRequirements() {
        return this.panelTree1;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Features.
     * @return Scroll Panel Tree Features.
     */
    public JScrollPane getScrollPanelTreeFeat() {
        return this.scrollPanes.get("scrollPanelTreeFeat");
    }
    
    /**
     * Method responsible for returning the Panel Tree Features.
     * @return Panel Tree Features.
     */
    public PanelTreeUML getPanelTreeFeatures() {
        return this.panelTree2;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree UML.
     * @return Scroll Panel Tree UML.
     */
    public JScrollPane getScrollPanelTreeUML() {
        return this.scrollPanes.get("scrollPanelTreeUML");
    }
    
    /**
     * Method responsible for returning the Panel Tree UML.
     * @return Panel Tree UML.
     */
    public PanelTreeUML getPanelTreeUML() {
        return this.panelTree3;
    }
}
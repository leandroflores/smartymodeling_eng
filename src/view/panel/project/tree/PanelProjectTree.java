package view.panel.project.tree;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.Panel;
import view.panel.tree.PanelTreeFeature;
import view.panel.tree.PanelTreeUML;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelProjectTree</b>.</p>
 * <p>Class responsible for defining the <b>Project Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    model.structural.base.Project
 * @see    view.Panel
 */
public final class PanelProjectTree extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private JTabbedPane tabbedPane;
    private Integer index;
    private PanelTreeFeature panelTreeFeat;
    private PanelTreeUML     panelTreeUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelProjectTree(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.index    = 0;
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.initTabbedPane();
            this.addPanelTreeFeatures();
            this.addPanelTreeUML();
//        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
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
     * Method responsible for adding the Panel Features.
     */
    protected void addPanelTreeFeatures() {
        this.panelTreeFeat = new PanelTreeFeature(this.viewMenu);
        this.createScrollPane("scrollPanelTreeFeat",  this.panelTreeFeat);
        this.getScrollPanelTreeFeat().setViewportView(this.panelTreeFeat);
        this.tabbedPane.add("Feat", this.getScrollPanelTreeFeat());
    }
    
    /**
     * Method responsible for adding the Panel Tree UML.
     */
    protected void addPanelTreeUML() {
        this.panelTreeUML  = new PanelTreeUML(this.viewMenu);
        this.createScrollPane("scrollPanelTreeUML",  this.panelTreeUML);
        this.getScrollPanelTreeUML().setViewportView(this.panelTreeUML);
        this.tabbedPane.add("UML", this.getScrollPanelTreeUML());
    }

    /**
     * Method responsible for returning the Index Panel.
     * @return Index Panel.
     */
    public Integer getIndex() {
        return this.index;
    }

    /**
     * Method responsible for setting the Index Panel.
     * @param index Index Panel.
     */
    public void setIndex(Integer index) {
        this.index = index;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Features.
     * @return Scroll Panel Tree Features.
     */
    public JScrollPane getScrollPanelTreeFeat() {
        return this.scrollPanes.get("scrollPanelTreeFeat");
    }
    
    /**
     * Method responsible for returning the Panel Tree Feature.
     * @return Panel Tree Feature.
     */
    public PanelTreeFeature getPanelTreeFeature() {
        return this.panelTreeFeat;
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
        return this.panelTreeUML;
    }
}
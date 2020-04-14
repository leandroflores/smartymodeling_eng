package view.panel.project.tree;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import view.Panel;
import view.panel.tree.old.PanelTreeFeature;
import view.panel.tree.old.PanelTreeUML;
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
    private PanelTreeFeature panelTreeFeat;
    private PanelTreeUML     panelTreeUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelProjectTree(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new GridLayout(1, 1));
        this.initTabbedPane();
            this.addPanelTreeFeature();
            this.addPanelTreeUML();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for initializing the Tabbed Pane.
     */
    protected void initTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.addMouseListener((MouseListener) this.controller);
        this.tabbedPane.setPreferredSize(new Dimension(275, 260));
    }
    
    /**
     * Method responsible for adding the Panel Feature.
     */
    protected void addPanelTreeFeature() {
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
     * Method responsible for updating the Project Node.
     */
    public void updateProjectNode() {
        if (this.panelTreeFeat != null)
            this.panelTreeFeat.updateProjectNode();
        if (this.panelTreeUML  != null)
            this.panelTreeUML.updateProjectNode();
    }
    
    /**
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram.
     */
    public void updateNode(Diagram diagram) {
        if (this.panelTreeFeat != null)
            this.panelTreeFeat.updateNode(diagram);
        if (this.panelTreeUML  != null)
            this.panelTreeUML.updateNode(diagram);
    }
    
    /**
     * Method responsible for updating the Element Node.
     * @param element Element.
     */
    public void updateNode(Element element) {
        if (this.panelTreeFeat != null)
            this.panelTreeFeat.updateNode(element);
        if (this.panelTreeUML  != null)
            this.panelTreeUML.updateNode(element);
    }

    /**
     * Method responsible for returning the Tabbed Pane.
     * @return Tabbed Pane.
     */
    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
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
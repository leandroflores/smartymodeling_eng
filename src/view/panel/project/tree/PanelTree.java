package view.panel.project.tree;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import view.Panel;
import view.panel.tree.PanelTreeDiagram;
import view.panel.z.old.PanelTreeFeature;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTree</b>.</p>
 * <p>Class responsible for defining the <b>Project Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    model.structural.base.Project
 * @see    view.Panel
 */
public final class PanelTree extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private JTabbedPane tabbedPane;
    private HashMap panels;
    
    private PanelTreeFeature panelTreeFeature;
    private PanelTreeDiagram panelTreeDiagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTree(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new GridLayout(1, 1));
        this.initTabbedPane();
            this.addPanelTreeFeature();
            this.addPanelTreeDiagram();
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
     * Method responsible for adding the Panel Feature.
     */
    protected void addPanelTreeFeature() {
        this.panelTreeFeature = new PanelTreeFeature(this.viewMenu);
        this.createScrollPane("scrollPanelTreeFeat",  this.panelTreeFeature);
        this.getScrollPanelTreeFeat().setViewportView(this.panelTreeFeature);
        this.tabbedPane.add("Feat", this.getScrollPanelTreeFeat());
    }
    
    /**
     * Method responsible for adding the Panel Tree Diagram.
     */
    protected void addPanelTreeDiagram() {
        this.panelTreeDiagram  = new PanelTreeDiagram(this.viewMenu);
        this.createScrollPane("scrollPanelTreeUML",  this.panelTreeDiagram);
        this.getScrollPanelTreeUML().setViewportView(this.panelTreeDiagram);
        this.tabbedPane.add("UML", this.getScrollPanelTreeUML());
    }
    
    
    /**
     * Method responsible for updating the Project Node.
     */
    public void updateProjectNode() {
//        if (this.panelTreeFeature != null)
//            this.panelTreeFeature.updateProjectNode();
//        if (this.panelTreeDiagram  != null)
//            this.panelTreeDiagram.updateProjectNode();
    }
    
    /**
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram.
     */
    public void updateNode(Diagram diagram) {
//        if (this.panelTreeFeature != null)
//            this.panelTreeFeature.updateNode(diagram);
//        if (this.panelTreeDiagram  != null)
//            this.panelTreeDiagram.updateNode(diagram);
    }
    
    /**
     * Method responsible for updating the Element Node.
     * @param element Element.
     */
    public void updateNode(Element element) {
//        if (this.panelTreeFeature != null)
//            this.panelTreeFeature.updateNode(element);
//        if (this.panelTreeDiagram  != null)
//            this.panelTreeDiagram.updateNode(element);
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
        return this.panelTreeFeature;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree UML.
     * @return Scroll Panel Tree UML.
     */
    public JScrollPane getScrollPanelTreeUML() {
        return this.scrollPanes.get("scrollPanelTreeUML");
    }
    
    /**
     * Method responsible for returning the Panel Tree Diagram.
     * @return Panel Tree Diagram.
     */
    public PanelTreeDiagram getPanelTreeDiagram() {
        return this.panelTreeDiagram;
    }
}
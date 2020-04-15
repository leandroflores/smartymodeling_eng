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
import view.panel.tree.PanelTreeEvaluation;
import view.panel.tree.PanelTreeFeature;
import view.panel.tree.PanelTreeProduct;
import view.panel.tree.PanelTreeVariability;
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
    private PanelTreeProduct panelTreeProduct;
    private PanelTreeEvaluation  panelTreeEvaluation;
    private PanelTreeVariability panelTreeVariability;
    
    
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
        this.addProjectPanels();
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
     * Method responsible for adding the Project Panels.
     */
    protected void addProjectPanels() {
        if (this.project != null) {
            this.addPanelTreeFeature();
            this.addPanelTreeDiagram();
            this.addPanelTreeVariability();
            this.addPanelTreeProduct();
            this.addPanelTreeEvaluation();
        }
    }
    
    /**
     * Method responsible for adding the Panel Feature.
     */
    protected void addPanelTreeFeature() {
        this.panelTreeFeature = new PanelTreeFeature(this.viewMenu);
        this.createScrollPane("scrollPanelTreeFeature",  this.panelTreeFeature);
        this.getScrollPanelTreeFeature().setViewportView(this.panelTreeFeature);
        this.tabbedPane.add("Feat", this.getScrollPanelTreeFeature());
    }
    
    /**
     * Method responsible for adding the Panel Tree Diagram.
     */
    protected void addPanelTreeDiagram() {
        this.panelTreeDiagram = new PanelTreeDiagram(this.viewMenu);
        this.createScrollPane("scrollPanelTreeDiagram",  this.panelTreeDiagram);
        this.getScrollPanelTreeDiagram().setViewportView(this.panelTreeDiagram);
        this.tabbedPane.add("UML", this.getScrollPanelTreeDiagram());
    }
    
    /**
     * Method responsible for adding the Panel Tree Variability.
     */
    protected void addPanelTreeVariability() {
        this.panelTreeVariability = new PanelTreeVariability(this.viewMenu);
        this.createScrollPane("scrollPanelTreeVariability",  this.panelTreeVariability);
        this.getScrollPanelTreeVariability().setViewportView(this.panelTreeVariability);
        this.tabbedPane.add("Variability", this.getScrollPanelTreeVariability());
    }
    
    /**
     * Method responsible for adding the Panel Tree Product.
     */
    protected void addPanelTreeProduct() {
        this.panelTreeProduct = new PanelTreeProduct(this.viewMenu);
        this.createScrollPane("scrollPanelTreeProduct",  this.panelTreeProduct);
        this.getScrollPanelTreeProduct().setViewportView(this.panelTreeProduct);
        this.tabbedPane.add("Product", this.getScrollPanelTreeProduct());
    }
    
    /**
     * Method responsible for adding the Panel Tree Evaluation.
     */
    protected void addPanelTreeEvaluation() {
        this.panelTreeEvaluation = new PanelTreeEvaluation(this.viewMenu);
        this.createScrollPane("scrollPanelTreeEvaluation",  this.panelTreeEvaluation);
        this.getScrollPanelTreeEvaluation().setViewportView(this.panelTreeEvaluation);
        this.tabbedPane.add("Evaluation", this.getScrollPanelTreeEvaluation());
    }
    
    /**
     * Method responsible for updating the Project Node.
     * @param project Project.
     */
    public void updateNode(Project project) {
        this.panelTreeFeature.updateNode(project);
        this.panelTreeDiagram.updateNode(project);
        this.panelTreeVariability.updateNode(project);
        this.panelTreeProduct.updateNode(project);
        this.panelTreeEvaluation.updateNode(project);
    }
    
    /**
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram.
     */
    public void updateNode(Diagram diagram) {
        if (diagram.getType().equalsIgnoreCase("Feature")) {
            this.panelTreeFeature.updateNode(diagram);
        }else {
            this.panelTreeDiagram.updateNode(diagram);
            this.panelTreeVariability.updateNode(diagram);
        }
    }
    
    /**
     * Method responsible for updating the Element Node.
     * @param element Element.
     */
    public void updateNode(Element element) {
        this.panelTreeFeature.updateNode(element);
        this.panelTreeDiagram.updateNode(element);
        this.panelTreeVariability.updateNode(element);
        this.panelTreeProduct.updateNode(element);
        
        this.panelTreeDiagram.updateVariability(element);
        this.panelTreeVariability.updateVariability(element);
    }

    /**
     * Method responsible for returning the Tabbed Pane.
     * @return Tabbed Pane.
     */
    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Feature.
     * @return Scroll Panel Tree Feature.
     */
    public JScrollPane getScrollPanelTreeFeature() {
        return this.scrollPanes.get("scrollPanelTreeFeature");
    }
    
    /**
     * Method responsible for returning the Panel Tree Feature.
     * @return Panel Tree Feature.
     */
    public PanelTreeFeature getPanelTreeFeature() {
        return this.panelTreeFeature;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Diagram.
     * @return Scroll Panel Tree Diagram.
     */
    public JScrollPane getScrollPanelTreeDiagram() {
        return this.scrollPanes.get("scrollPanelTreeDiagram");
    }
    
    /**
     * Method responsible for returning the Panel Tree Diagram.
     * @return Panel Tree Diagram.
     */
    public PanelTreeDiagram getPanelTreeDiagram() {
        return this.panelTreeDiagram;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Variability.
     * @return Scroll Panel Tree Variability.
     */
    public JScrollPane getScrollPanelTreeVariability() {
        return this.scrollPanes.get("scrollPanelTreeVariability");
    }
    
    /**
     * Method responsible for returning the Panel Tree Variability.
     * @return Panel Tree Variability.
     */
    public PanelTreeVariability getPanelTreeVariability() {
        return this.panelTreeVariability;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Product.
     * @return Scroll Panel Tree Product.
     */
    public JScrollPane getScrollPanelTreeProduct() {
        return this.scrollPanes.get("scrollPanelTreeProduct");
    }
    
    /**
     * Method responsible for returning the Panel Tree Product.
     * @return Panel Tree Product.
     */
    public PanelTreeProduct getPanelTreeProduct() {
        return this.panelTreeProduct;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Evaluation.
     * @return Scroll Panel Tree Evaluation.
     */
    public JScrollPane getScrollPanelTreeEvaluation() {
        return this.scrollPanes.get("scrollPanelTreeEvaluation");
    }
    
    /**
     * Method responsible for returning the Panel Tree Evaluation.
     * @return Panel Tree Evaluation.
     */
    public PanelTreeEvaluation getPanelTreeEvaluation() {
        return this.panelTreeEvaluation;
    }
}
package view.panel.project.tree;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.requirement.Requirement;
import model.structural.base.variability.Variability;
import view.panel.Panel;
import view.panel.tree.diagram.PanelTreeDiagram;
import view.panel.tree.base.evaluation.PanelTreeEvaluation;
import view.panel.tree.feature.PanelTreeFeature;
import view.panel.tree.base.product.PanelTreeProduct;
import view.panel.tree.base.requirement.PanelTreeRequirement;
import view.panel.tree.base.variability.PanelTreeVariability;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelTree</b>.</p>
 * <p>Class responsible for defining the <b>Project Tree Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    model.structural.base.Project
 * @see    view.panel.Panel
 */
public final class PanelTree extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private final boolean  complete;
    private JTabbedPane tabbedPane;
    
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelTree(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.complete = true;
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
            this.addPanelTreeRequirement();
            this.addPanelTreeFeature();
            this.addPanelTreeDiagram();
            this.addPanelTreeVariability();
            this.addPanelTreeProduct();
            this.addPanelTreeEvaluation();
            this.setUMLVersion();
        }
    }
    
    /**
     * Method responsible for setting the UML Version Tab.
     */
    private void setUMLVersion() {
        if (!this.complete) {
            this.getTabbedPane().remove(5);
            this.getTabbedPane().remove(4);
            this.getTabbedPane().remove(3);
            this.getTabbedPane().remove(1);
            this.getTabbedPane().remove(0);
        }
    }
    
    
    public void setTabIndex(int index) {
        if (this.complete)
            this.tabbedPane.setSelectedIndex(index);
        else
            this.tabbedPane.setSelectedIndex(0); 
    }
    
    /**
     * Method responsible for adding the Panel Tree Requirement.
     */
    protected void addPanelTreeRequirement() {
        this.addPanel("panelTreeRequirement", new PanelTreeRequirement(this.viewMenu));
        this.createScrollPane("scrollPanelTreeRequirement",  this.getPanelTreeRequirement());
        this.getScrollPanelTreeRequirement().setViewportView(this.getPanelTreeRequirement());
        this.tabbedPane.add("Requirement", this.getScrollPanelTreeRequirement());
    }
    
    /**
     * Method responsible for adding the Panel Tree Feature.
     */
    protected void addPanelTreeFeature() {
        this.addPanel("panelTreeFeature", new PanelTreeFeature(this.viewMenu));
        this.createScrollPane("scrollPanelTreeFeature",  this.getPanelTreeFeature());
        this.getScrollPanelTreeFeature().setViewportView(this.getPanelTreeFeature());
        this.tabbedPane.add("Feature", this.getScrollPanelTreeFeature());
    }
    
    /**
     * Method responsible for adding the Panel Tree Diagram.
     */
    protected void addPanelTreeDiagram() {
        this.addPanel("panelTreeDiagram", new PanelTreeDiagram(this.viewMenu));
        this.createScrollPane("scrollPanelTreeDiagram",  this.getPanelTreeDiagram());
        this.getScrollPanelTreeDiagram().setViewportView(this.getPanelTreeDiagram());
        this.tabbedPane.add("UML", this.getScrollPanelTreeDiagram());
    }
    
    /**
     * Method responsible for adding the Panel Tree Variability.
     */
    protected void addPanelTreeVariability() {
        this.addPanel("panelTreeVariability", new PanelTreeVariability(this.viewMenu));
        this.createScrollPane("scrollPanelTreeVariability",  this.getPanelTreeVariability());
        this.getScrollPanelTreeVariability().setViewportView(this.getPanelTreeVariability());
        this.tabbedPane.add("Variability", this.getScrollPanelTreeVariability());
    }
    
    /**
     * Method responsible for adding the Panel Tree Product.
     */
    protected void addPanelTreeProduct() {
        this.addPanel("panelTreeProduct", new PanelTreeProduct(this.viewMenu));
        this.createScrollPane("scrollPanelTreeProduct",  this.getPanelTreeProduct());
        this.getScrollPanelTreeProduct().setViewportView(this.getPanelTreeProduct());
        this.tabbedPane.add("Product", this.getScrollPanelTreeProduct());
    }
    
    /**
     * Method responsible for adding the Panel Tree Evaluation.
     */
    protected void addPanelTreeEvaluation() {
        this.addPanel("panelTreeEvaluation", new PanelTreeEvaluation(this.viewMenu));
        this.createScrollPane("scrollPanelTreeEvaluation",  this.getPanelTreeEvaluation());
        this.getScrollPanelTreeEvaluation().setViewportView(this.getPanelTreeEvaluation());
        this.tabbedPane.add("Evaluation", this.getScrollPanelTreeEvaluation());
    }
    
    /**
     * Method responsible for updating the Project Node.
     * @param project Project.
     */
    public void updateNode(Project project) {
        this.getPanelTreeRequirement().updateNode(project);
        this.getPanelTreeFeature().updateNode(project);
        this.getPanelTreeDiagram().updateNode(project);
        this.getPanelTreeVariability().updateNode(project);
        this.getPanelTreeProduct().updateNode(project);
        this.getPanelTreeEvaluation().updateNode(project);
    }
    
    /**
     * Method responsible for updating the Requirement Node.
     * @param requirement Requirement.
     */
    public void updateNode(Requirement requirement) {
        this.getPanelTreeRequirement().updateNode(requirement);
    }
    
    /**
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram.
     */
    public void updateNode(Diagram diagram) {
        if (diagram.getType().equalsIgnoreCase("Feature")) {
            this.getPanelTreeFeature().updateNode(diagram);
        }else {
            this.getPanelTreeDiagram().updateNode(diagram);
            this.getPanelTreeVariability().updateNode(diagram);
        }
    }
    
    /**
     * Method responsible for updating the Element Node.
     * @param element Element.
     */
    public void updateNode(Element element) {
        this.getPanelTreeRequirement().updateNode(element);
        this.getPanelTreeFeature().updateNode(element);
        this.getPanelTreeDiagram().updateNode(element);
        this.getPanelTreeVariability().updateNode(element);
        this.getPanelTreeProduct().updateNode(element);
        
        this.getPanelTreeDiagram().updateVariability(element);
        this.getPanelTreeVariability().updateVariability(element);
    }
    
    /**
     * Method responsible for updating the Variability Node.
     * @param variability Variability.
     */
    public void updateNode(Variability variability) {
        this.getPanelTreeDiagram().updateNode(variability);
        this.getPanelTreeVariability().updateNode(variability);
    }
    
    
    /**
     * Method responsible for updating the Product Node.
     * @param product Product.
     */
    public void updateNode(Product product) {
        this.getPanelTreeProduct().updateNode(product);
    }
    
    /**
     * Method responsible for updating the Instance Node.
     * @param instance Instance.
     */
    public void updateNode(Instance instance) {
        this.getPanelTreeProduct().updateNode(instance);
    }

    /**
     * Method responsible for returning the Tabbed Pane.
     * @return Tabbed Pane.
     */
    public JTabbedPane getTabbedPane() {
        return this.tabbedPane;
    }
    
    /**
     * Method responsible for returning the Panel Tree Requirement.
     * @return Panel Tree Requirement.
     */
    public PanelTreeRequirement getPanelTreeRequirement() {
        return (PanelTreeRequirement) this.getPanel("panelTreeRequirement");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Requirement.
     * @return Scroll Panel Tree Requirement.
     */
    public JScrollPane getScrollPanelTreeRequirement() {
        return this.getScrollPane("scrollPanelTreeRequirement");
    }
    
    /**
     * Method responsible for returning the Panel Tree Feature.
     * @return Panel Tree Feature.
     */
    public PanelTreeFeature getPanelTreeFeature() {
        return (PanelTreeFeature) this.getPanel("panelTreeFeature");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Feature.
     * @return Scroll Panel Tree Feature.
     */
    public JScrollPane getScrollPanelTreeFeature() {
        return this.getScrollPane("scrollPanelTreeFeature");
    }
    
    /**
     * Method responsible for returning the Panel Tree Diagram.
     * @return Panel Tree Diagram.
     */
    public PanelTreeDiagram getPanelTreeDiagram() {
        return (PanelTreeDiagram) this.getPanel("panelTreeDiagram");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Diagram.
     * @return Scroll Panel Tree Diagram.
     */
    public JScrollPane getScrollPanelTreeDiagram() {
        return this.getScrollPane("scrollPanelTreeDiagram");
    }
    
    /**
     * Method responsible for returning the Panel Tree Variability.
     * @return Panel Tree Variability.
     */
    public PanelTreeVariability getPanelTreeVariability() {
        return (PanelTreeVariability) this.getPanel("panelTreeVariability");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Variability.
     * @return Scroll Panel Tree Variability.
     */
    public JScrollPane getScrollPanelTreeVariability() {
        return this.getScrollPane("scrollPanelTreeVariability");
    }
    
    /**
     * Method responsible for returning the Panel Tree Product.
     * @return Panel Tree Product.
     */
    public PanelTreeProduct getPanelTreeProduct() {
        return (PanelTreeProduct) this.getPanel("panelTreeProduct");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Product.
     * @return Scroll Panel Tree Product.
     */
    public JScrollPane getScrollPanelTreeProduct() {
        return this.getScrollPane("scrollPanelTreeProduct");
    }
    
    /**
     * Method responsible for returning the Panel Tree Evaluation.
     * @return Panel Tree Evaluation.
     */
    public PanelTreeEvaluation getPanelTreeEvaluation() {
        return (PanelTreeEvaluation) this.getPanel("panelTreeEvaluation");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Evaluation.
     * @return Scroll Panel Tree Evaluation.
     */
    public JScrollPane getScrollPanelTreeEvaluation() {
        return this.getScrollPane("scrollPanelTreeEvaluation");
    }
}
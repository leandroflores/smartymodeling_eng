package view.panel.project.tree;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
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
import view.main.structural.ViewMenu;

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
     * @param view View Menu.
     */
    public PanelTree(ViewMenu view) {
        viewMenu = view;
        project  = view.getProject();
        complete = true;
        addComponents();
    }
    
    @Override
    public void addComponents() {
        setLayout(new GridLayout(1, 1));
        initTabbedPane();
        addProjectPanels();
        add(tabbedPane);
    }
    
    /**
     * Method responsible for initializing the Tabbed Pane.
     */
    protected void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(275, 260));
    }
    
    /**
     * Method responsible for adding the Project Panels.
     */
    protected void addProjectPanels() {
        if (project != null) {
            addPanelTreeRequirement();
            addPanelTreeFeature();
            addPanelTreeDiagram();
            addPanelTreeVariability();
            addPanelTreeProduct();
            addPanelTreeEvaluation();
            setUMLVersion();
        }
    }
    
    /**
     * Method responsible for setting the UML Version Tab.
     */
    private void setUMLVersion() {
        if (!complete) {
            getTabbedPane().remove(5);
            getTabbedPane().remove(4);
            getTabbedPane().remove(3);
            getTabbedPane().remove(1);
            getTabbedPane().remove(0);
        }
    }
    
    /**
     * Method responsible for setting the Tab Index.
     * @param index Tab Index.
     */
    public void setTabIndex(int index) {
        tabbedPane.setSelectedIndex(complete ? index : 0);
    }
    
    /**
     * Method responsible for adding the Panel Tree Requirement.
     */
    protected void addPanelTreeRequirement() {
        addPanel("tree_requirement", new PanelTreeRequirement(viewMenu));
        createScrollPane("tree_requirement", getPanelTreeRequirement());
        getScrollPanelTreeRequirement().setViewportView(getPanelTreeRequirement());
        tabbedPane.add("Requirements", getScrollPanelTreeRequirement());
    }
    
    /**
     * Method responsible for adding the Panel Tree Feature.
     */
    protected void addPanelTreeFeature() {
        addPanel("tree_feature", new PanelTreeFeature(viewMenu));
        createScrollPane("tree_feature", getPanelTreeFeature());
        getScrollPanelTreeFeature().setViewportView(getPanelTreeFeature());
        tabbedPane.add("Features", getScrollPanelTreeFeature());
    }
    
    /**
     * Method responsible for adding the Panel Tree Diagram.
     */
    protected void addPanelTreeDiagram() {
        addPanel("tree_diagram", new PanelTreeDiagram(viewMenu));
        createScrollPane("tree_diagram", getPanelTreeDiagram());
        getScrollPanelTreeDiagram().setViewportView(getPanelTreeDiagram());
        tabbedPane.add("UML", getScrollPanelTreeDiagram());
    }
    
    /**
     * Method responsible for adding the Panel Tree Variability.
     */
    protected void addPanelTreeVariability() {
        addPanel("tree_variability", new PanelTreeVariability(viewMenu));
        createScrollPane("tree_variability", getPanelTreeVariability());
        getScrollPanelTreeVariability().setViewportView(getPanelTreeVariability());
        tabbedPane.add("Variability View", getScrollPanelTreeVariability());
    }
    
    /**
     * Method responsible for adding the Panel Tree Product.
     */
    protected void addPanelTreeProduct() {
        addPanel("tree_product", new PanelTreeProduct(viewMenu));
        createScrollPane("tree_product", getPanelTreeProduct());
        getScrollPanelTreeProduct().setViewportView(getPanelTreeProduct());
        tabbedPane.add("Product View", getScrollPanelTreeProduct());
    }
    
    /**
     * Method responsible for adding the Panel Tree Evaluation.
     */
    protected void addPanelTreeEvaluation() {
        addPanel("tree_evaluation", new PanelTreeEvaluation(viewMenu));
        createScrollPane("tree_evaluation", getPanelTreeEvaluation());
        getScrollPanelTreeEvaluation().setViewportView(getPanelTreeEvaluation());
        tabbedPane.add("Evaluation", getScrollPanelTreeEvaluation());
    }
    
    /**
     * Method responsible for updating the Project Node.
     * @param project Project.
     */
    public void updateNode(Project project) {
        getPanelTreeRequirement().updateNode(project);
        getPanelTreeFeature().updateNode(project);
        getPanelTreeDiagram().updateNode(project);
        getPanelTreeVariability().updateNode(project);
        getPanelTreeProduct().updateNode(project);
        getPanelTreeEvaluation().updateNode(project);
    }
    
    /**
     * Method responsible for updating the Requirement Node.
     * @param requirement Requirement.
     */
    public void updateNode(Requirement requirement) {
        getPanelTreeRequirement().updateNode(requirement);
    }
    
    /**
     * Method responsible for updating the Diagram Node.
     * @param diagram Diagram.
     */
    public void updateNode(Diagram diagram) {
        if (diagram.getType().equalsIgnoreCase("Feature")) {
            getPanelTreeFeature().updateNode(diagram);
        }else {
            getPanelTreeDiagram().updateNode(diagram);
            getPanelTreeVariability().updateNode(diagram);
        }
    }
    
    /**
     * Method responsible for updating the Element Node.
     * @param element Element.
     */
    public void updateNode(Element element) {
        getPanelTreeRequirement().updateNode(element);
        getPanelTreeFeature().updateNode(element);
        getPanelTreeDiagram().updateNode(element);
        getPanelTreeVariability().updateNode(element);
        getPanelTreeProduct().updateNode(element);
        
        getPanelTreeDiagram().updateVariability(element);
        getPanelTreeVariability().updateVariability(element);
    }
    
    /**
     * Method responsible for updating the Variability Node.
     * @param variability Variability.
     */
    public void updateNode(Variability variability) {
        getPanelTreeDiagram().updateNode(variability);
        getPanelTreeVariability().updateNode(variability);
    }
    
    
    /**
     * Method responsible for updating the Product Node.
     * @param product Product.
     */
    public void updateNode(Product product) {
        getPanelTreeProduct().updateNode(product);
    }
    
    /**
     * Method responsible for updating the Instance Node.
     * @param instance Instance.
     */
    public void updateNode(Instance instance) {
        getPanelTreeProduct().updateNode(instance);
    }

    /**
     * Method responsible for updating the Metric Node.
     * @param metric Metric.
     */
    public void updateNode(Metric metric) {
        getPanelTreeEvaluation().updateNode(metric);
    }
    
    /**
     * Method responsible for updating the Measure Node.
     * @param measure Measure.
     */
    public void updateNode(Measure measure) {
        getPanelTreeEvaluation().updateNode(measure);
    }
    
    /**
     * Method responsible for returning the Tabbed Pane.
     * @return Tabbed Pane.
     */
    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }
    
    /**
     * Method responsible for returning the Panel Tree Requirement.
     * @return Panel Tree Requirement.
     */
    public PanelTreeRequirement getPanelTreeRequirement() {
        return (PanelTreeRequirement) getPanel("tree_requirement");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Requirement.
     * @return Scroll Panel Tree Requirement.
     */
    public JScrollPane getScrollPanelTreeRequirement() {
        return getScrollPane("tree_requirement");
    }
    
    /**
     * Method responsible for returning the Panel Tree Feature.
     * @return Panel Tree Feature.
     */
    public PanelTreeFeature getPanelTreeFeature() {
        return (PanelTreeFeature) getPanel("tree_feature");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Feature.
     * @return Scroll Panel Tree Feature.
     */
    public JScrollPane getScrollPanelTreeFeature() {
        return getScrollPane("tree_feature");
    }
    
    /**
     * Method responsible for returning the Panel Tree Diagram.
     * @return Panel Tree Diagram.
     */
    public PanelTreeDiagram getPanelTreeDiagram() {
        return (PanelTreeDiagram) getPanel("tree_diagram");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Diagram.
     * @return Scroll Panel Tree Diagram.
     */
    public JScrollPane getScrollPanelTreeDiagram() {
        return getScrollPane("tree_diagram");
    }
    
    /**
     * Method responsible for returning the Panel Tree Variability.
     * @return Panel Tree Variability.
     */
    public PanelTreeVariability getPanelTreeVariability() {
        return (PanelTreeVariability) getPanel("tree_variability");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Variability.
     * @return Scroll Panel Tree Variability.
     */
    public JScrollPane getScrollPanelTreeVariability() {
        return getScrollPane("tree_variability");
    }
    
    /**
     * Method responsible for returning the Panel Tree Product.
     * @return Panel Tree Product.
     */
    public PanelTreeProduct getPanelTreeProduct() {
        return (PanelTreeProduct) getPanel("tree_product");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Product.
     * @return Scroll Panel Tree Product.
     */
    public JScrollPane getScrollPanelTreeProduct() {
        return getScrollPane("tree_product");
    }
    
    /**
     * Method responsible for returning the Panel Tree Evaluation.
     * @return Panel Tree Evaluation.
     */
    public PanelTreeEvaluation getPanelTreeEvaluation() {
        return (PanelTreeEvaluation) getPanel("tree_evaluation");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree Evaluation.
     * @return Scroll Panel Tree Evaluation.
     */
    public JScrollPane getScrollPanelTreeEvaluation() {
        return getScrollPane("tree_evaluation");
    }
}
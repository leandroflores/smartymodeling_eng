package view.panel.project;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.base.evaluation.Metric;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.traceability.Traceability;
import model.structural.base.variability.Variability;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.Panel;
import view.panel.edit.PanelEdit;
import view.panel.edit.base.PanelEditDiagram;
import view.panel.edit.base.PanelEditProject;
import view.panel.edit.base.activity.PanelEditActivityUML;
import view.panel.edit.base.activity.PanelEditDecisionUML;
import view.panel.edit.base.classes.PanelEditAttributeUML;
import view.panel.edit.base.classes.PanelEditClassUML;
import view.panel.edit.base.classes.PanelEditInterfaceUML;
import view.panel.edit.base.classes.PanelEditMethodUML;
import view.panel.edit.base.classes.PanelEditPackageUML;
import view.panel.edit.base.component.PanelEditComponentUML;
import view.panel.edit.base.evaluation.PanelEditMetric;
import view.panel.edit.base.product.PanelEditArtifact;
import view.panel.edit.base.product.PanelEditInstance;
import view.panel.edit.base.product.PanelEditProduct;
import view.panel.edit.base.sequence.PanelEditInstanceUML;
import view.panel.edit.base.sequence.PanelEditLifelineUML;
import view.panel.edit.base.sequence.PanelEditMessageUML;
import view.panel.edit.base.traceability.PanelEditTraceability;
import view.panel.edit.base.usecase.PanelEditActorUML;
import view.panel.edit.base.usecase.PanelEditUseCaseUML;
import view.panel.edit.base.variability.PanelEditVariability;
import view.panel.tree.PanelTree;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelProject</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Project</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  11/06/2019
 * @see    view.Panel
 */
public final class PanelProject extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private JSplitPane splitPane;
    private PanelTree  panelTree;
    private PanelEdit  panelEdit;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelProject(ViewMenu viewMenu) {
        this.viewMenu = viewMenu;
        this.project  = this.viewMenu.getProject();
        this.initComponents();
        this.addComponents();
    }

    /**
     * Method responsible for initializing the Components.
     */
    private void initComponents() {
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(240, 250));
        this.setMinimumSize(new Dimension(240, 220));
    }
    
    @Override
    protected void addComponents() {
        this.initVerticalSplitPane();
        this.initPanelTree();
        this.initPanelEdit();
        
        this.splitPane.setTopComponent(this.getScrollPanelTree());
        this.splitPane.setBottomComponent(this.getScrollPanelEdit());
        
        this.add(this.splitPane, this.getConstraints());
    }
    
    /**
     * Method responsible for initializing the Vertical Split Pane.
     */
    private void initVerticalSplitPane() {
        this.splitPane = new JSplitPane();
        this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    }
    
    /**
     * Method responsible for initializing the Panel Tree.
     */
    public void initPanelTree() {
        this.panelTree = new PanelTree(this.viewMenu);
        this.createScrollPane("scrollPanelTree");
        this.getScrollPanelTree().setViewportView(this.panelTree);
        this.getScrollPanelTree().setMinimumSize(new Dimension(200, 250));
        this.getScrollPanelTree().setPreferredSize(new Dimension(200, 300));
    }
    
    /**
     * Method responsible for initializing the Panel Edit.
     */
    public void initPanelEdit() {
        this.panelEdit = new PanelEdit(this.viewMenu);
        this.createScrollPane("scrollPanelEdit");
        this.getScrollPanelEdit().setViewportView(this.panelEdit);
        this.getScrollPanelEdit().setMinimumSize(new Dimension(200, 200));
        this.getScrollPanelEdit().setPreferredSize(new Dimension(200, 200));
    }
    
    /**
     * Method responsible for initializing the Panel Edit Project.
     */
    public void initPanelEditProject() {
        this.panelEdit = new PanelEditProject(this.viewMenu);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Diagram.
     * @param diagram Diagram.
     */
    public void initPanelEditDiagram(Diagram diagram) {
        this.panelEdit = new PanelEditDiagram(this.viewMenu, diagram);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Activity Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(ActivityDiagram diagram, Element element) {
        if (element instanceof ActivityUML)
            this.panelEdit = new PanelEditActivityUML(this.viewMenu, diagram, (ActivityUML) element);
        else if (element instanceof DecisionUML)
            this.panelEdit = new PanelEditDecisionUML(this.viewMenu, diagram, (DecisionUML) element);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Class Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(ClassDiagram diagram, Element element) {
        if (element instanceof AttributeUML)
            this.panelEdit = new PanelEditAttributeUML(this.viewMenu, diagram, (AttributeUML) element);
        else if (element instanceof MethodUML)
            this.panelEdit = new PanelEditMethodUML(this.viewMenu, diagram, (MethodUML) element);
        else if (element instanceof ClassUML)
            this.panelEdit = new PanelEditClassUML(this.viewMenu, diagram, (ClassUML) element);
        else if (element instanceof InterfaceUML)
            this.panelEdit = new PanelEditInterfaceUML(this.viewMenu, diagram, (InterfaceUML) element);
        else if (element instanceof PackageUML)
            this.panelEdit = new PanelEditPackageUML(this.viewMenu, diagram, (PackageUML) element);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Component Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(ComponentDiagram diagram, Element element) {
        if (element instanceof ComponentUML)
            this.panelEdit = new PanelEditComponentUML(this.viewMenu, diagram, (ComponentUML) element);
        else if (element instanceof model.structural.diagram.component.base.InterfaceUML)
            this.panelEdit = new view.panel.edit.base.component.PanelEditInterfaceUML(this.viewMenu, diagram, (model.structural.diagram.component.base.InterfaceUML) element);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Use Case Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(UseCaseDiagram diagram, Element element) {
        if (element instanceof ActorUML)
            this.panelEdit = new PanelEditActorUML(this.viewMenu, diagram, (ActorUML) element);
        else if (element instanceof UseCaseUML)
            this.panelEdit = new PanelEditUseCaseUML(this.viewMenu, diagram, (UseCaseUML) element);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Sequence Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(SequenceDiagram diagram, Element element) {
        if (element instanceof LifelineUML)
            this.panelEdit = new PanelEditLifelineUML(this.viewMenu, diagram, (LifelineUML) element);
        else if (element instanceof InstanceUML)
            this.panelEdit = new PanelEditInstanceUML(this.viewMenu, diagram, (InstanceUML) element);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Association.
     * @param diagram Sequence Diagram.
     * @param association Association.
     */
    public void initPanelEditAssociation(SequenceDiagram diagram, Association association) {
        if (association instanceof MessageUML)
            this.panelEdit = new PanelEditMessageUML(this.viewMenu, diagram, (MessageUML) association);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Variability.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    public void initPanelEditVariability(Diagram diagram, Variability variability) {
        this.panelEdit = new PanelEditVariability(this.viewMenu, diagram, variability);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Traceability.
     * @param traceability Traceability.
     */
    public void initPanelEditTraceability(Traceability traceability) {
        this.panelEdit = new PanelEditTraceability(this.viewMenu, traceability);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Metric.
     * @param metric Metric.
     */
    public void initPanelEditMetric(Metric metric) {
        this.panelEdit = new PanelEditMetric(this.viewMenu, metric);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Product.
     * @param product Product.
     */
    public void initPanelEditProduct(Product product) {
        this.panelEdit = new PanelEditProduct(this.viewMenu, product);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Instance.
     * @param instance Instance.
     */
    public void initPanelEditInstance(Instance instance) {
        this.panelEdit = new PanelEditInstance(this.viewMenu, instance);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Artifact.
     * @param artifact Artifact.
     */
    public void initPanelEditArtifact(Artifact artifact) {
        this.panelEdit = new PanelEditArtifact(this.viewMenu, artifact);
        this.updatePanelEdit();
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     */
    public void updatePanelEdit() {
        this.panelEdit.updateUI();
        this.getScrollPanelEdit().setViewportView(this.panelEdit);
        this.getScrollPanelEdit().updateUI();
    }
    
    /**
     * Method responsible for returning the Constraints.
     * @return Constraints.
     */
    private GridBagConstraints getConstraints() {
        GridBagConstraints constraints         = new GridBagConstraints();
                           constraints.fill    = GridBagConstraints.BOTH;
                           constraints.gridx   = 0;
                           constraints.weightx = 1;
                           constraints.weighty = 1;
                    return constraints;
    }
    
    /**
     * Method responsible for returning the Panel Tree.
     * @return Panel Tree.
     */
    public PanelTree getPanelTree() {
        return this.panelTree;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree.
     * @return Scroll Panel Tree.
     */
    public JScrollPane getScrollPanelTree() {
        return this.scrollPanes.get("scrollPanelTree");
    }
    
    /**
     * Method responsible for returning the Panel Edit.
     * @return Panel Edit.
     */
    public PanelEdit getPanelEdit() {
        return this.panelEdit;
    }
    
    /**
     * Method responsible for returning Scroll Panel Edit.
     * @return Scroll Panel Edit
     */
    public JScrollPane getScrollPanelEdit() {
        return this.scrollPanes.get("scrollPanelEdit");
    }
}
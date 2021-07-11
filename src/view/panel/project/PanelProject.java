package view.panel.project;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import model.structural.base.evaluation.Measure;
import model.structural.base.evaluation.Metric;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import model.structural.base.product.Relationship;
import model.structural.base.requirement.Requirement;
import model.structural.base.traceability.Reference;
import model.structural.base.variability.Variability;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.activity.base.ActivityUML;
import model.structural.diagram.activity.base.DecisionUML;
import model.structural.diagram.activity.base.association.FlowUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.association.Connection;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import view.panel.Panel;
import view.panel.edit.PanelEdit;
import view.panel.edit.base.PanelEditDiagram;
import view.panel.edit.base.PanelEditProject;
import view.panel.edit.base.PanelEditStereotype;
import view.panel.edit.diagram.activity.base.PanelEditActivityUML;
import view.panel.edit.diagram.activity.base.PanelEditDecisionUML;
import view.panel.edit.diagram.activity.base.association.PanelEditFlowUML;
import view.panel.edit.diagram.PanelEditAssociation;
import view.panel.edit.diagram.classes.base.PanelEditAttributeUML;
import view.panel.edit.diagram.classes.base.PanelEditClassUML;
import view.panel.edit.diagram.classes.base.PanelEditInterfaceUML;
import view.panel.edit.diagram.classes.base.PanelEditMethodUML;
import view.panel.edit.diagram.classes.base.PanelEditPackageUML;
import view.panel.edit.diagram.classes.base.association.PanelEditAssociationUML;
import view.panel.edit.diagram.component.base.PanelEditComponentUML;
import view.panel.edit.base.evaluation.PanelEditMetric;
import view.panel.edit.diagram.feature.base.PanelEditFeature;
import view.panel.edit.diagram.feature.base.association.PanelEditConnection;
import view.panel.edit.base.product.PanelEditArtifact;
import view.panel.edit.base.product.PanelEditInstance;
import view.panel.edit.base.product.PanelEditProduct;
import view.panel.edit.base.product.PanelEditRelationship;
import view.panel.edit.base.requirement.PanelEditRequirement;
import view.panel.edit.diagram.sequence.base.PanelEditInstanceUML;
import view.panel.edit.diagram.sequence.base.PanelEditLifelineUML;
import view.panel.edit.diagram.sequence.base.association.PanelEditMessageUML;
import view.panel.edit.base.traceability.PanelEditReference;
import view.panel.edit.diagram.usecase.base.PanelEditActorUML;
import view.panel.edit.diagram.usecase.base.PanelEditUseCaseUML;
import view.panel.edit.base.variability.PanelEditVariability;
import view.panel.project.tree.PanelTree;
import view.main.structural.ViewMenu;
import view.panel.edit.PanelVoid;
import view.panel.edit.base.evaluation.PanelEditMeasure;

/**
 * <p>Class of View <b>PanelProject</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Project</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-11
 * @see    view.panel.Panel
 */
public final class PanelProject extends Panel {
    private final ViewMenu viewMenu;
    private final Project  project;
    private JSplitPane splitPane;
    private PanelTree panelTree;
    private PanelEdit panelEdit;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelProject(ViewMenu view) {
        viewMenu = view;
        project  = view.getProject();
        initComponents();
        addComponents();
    }

    /**
     * Method responsible for initializing the Components.
     */
    private void initComponents() {
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(300, 250));
        setMinimumSize(new Dimension(300, 250));
    }
    
    @Override
    protected void addComponents() {
        initVerticalSplitPane();
        initPanelTree();
        initPanelEdit();
        
        splitPane.setTopComponent(getScrollPanelTree());
        splitPane.setBottomComponent(getScrollPanelEdit());
        
        add(splitPane, getConstraints());
    }
    
    /**
     * Method responsible for initializing the Vertical Split Pane.
     */
    private void initVerticalSplitPane() {
        splitPane = new JSplitPane();
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    }
    
    /**
     * Method responsible for initializing the Panel Tree.
     */
    public void initPanelTree() {
        panelTree = new PanelTree(viewMenu);
        createScrollPane("tree");
        getScrollPanelTree().setViewportView(panelTree);
        getScrollPanelTree().setMinimumSize(new Dimension(250, 275));
        getScrollPanelTree().setPreferredSize(new Dimension(250, 275));
    }
    
    /**
     * Method responsible for initializing the Panel Edit.
     */
    public void initPanelEdit() {
        panelEdit = new PanelVoid(viewMenu);
        createScrollPane("edit");
        getScrollPanelEdit().setViewportView(panelEdit);
        getScrollPanelEdit().setMinimumSize(new Dimension(200, 200));
        getScrollPanelEdit().setPreferredSize(new Dimension(200, 200));
    }
    
    /**
     * Method responsible for initializing the Panel Edit Project.
     */
    public void initPanelEditProject() {
        panelEdit = new PanelEditProject(viewMenu);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Diagram.
     * @param diagram Diagram.
     */
    public void initPanelEditDiagram(Diagram diagram) {
        panelEdit = new PanelEditDiagram(viewMenu, diagram);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(Diagram diagram, Element element) {
        if (diagram instanceof FeatureDiagram)
            initPanelEditElement((FeatureDiagram)   diagram, element);
        else if (diagram instanceof ActivityDiagram)
            initPanelEditElement((ActivityDiagram)  diagram, element);
        else if (diagram instanceof ClassDiagram)
            initPanelEditElement((ClassDiagram)     diagram, element);
        else if (diagram instanceof ComponentDiagram)
            initPanelEditElement((ComponentDiagram) diagram, element);
        else if (diagram instanceof SequenceDiagram)
            initPanelEditElement((SequenceDiagram)  diagram, element);
        else if (diagram instanceof UseCaseDiagram)
            initPanelEditElement((UseCaseDiagram)   diagram, element);
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Feature Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(FeatureDiagram diagram, Element element) {
        if (element instanceof Feature)
            panelEdit = new PanelEditFeature(viewMenu, diagram, (Feature) element);
        else if (element instanceof model.structural.diagram.feature.base.Variability)
            panelEdit = new view.panel.edit.diagram.feature.base.PanelEditVariability(viewMenu, diagram, (model.structural.diagram.feature.base.Variability) element);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Activity Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(ActivityDiagram diagram, Element element) {
        if (element instanceof ActivityUML)
            panelEdit = new PanelEditActivityUML(viewMenu, diagram, (ActivityUML) element);
        else if (element instanceof DecisionUML)
            panelEdit = new PanelEditDecisionUML(viewMenu, diagram, (DecisionUML) element);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Class Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(ClassDiagram diagram, Element element) {
        if (element instanceof AttributeUML)
            panelEdit = new PanelEditAttributeUML(viewMenu, diagram, (AttributeUML) element);
        else if (element instanceof MethodUML)
            panelEdit = new PanelEditMethodUML(viewMenu, diagram, (MethodUML) element);
        else if (element instanceof ClassUML)
            panelEdit = new PanelEditClassUML(viewMenu, diagram, (ClassUML) element);
        else if (element instanceof InterfaceUML)
            panelEdit = new PanelEditInterfaceUML(viewMenu, diagram, (InterfaceUML) element);
        else if (element instanceof PackageUML)
            panelEdit = new PanelEditPackageUML(viewMenu, diagram, (PackageUML) element);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Component Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(ComponentDiagram diagram, Element element) {
        if (element instanceof ComponentUML)
            panelEdit = new PanelEditComponentUML(viewMenu, diagram, (ComponentUML) element);
        else if (element instanceof model.structural.diagram.component.base.InterfaceUML)
            panelEdit = new view.panel.edit.diagram.component.base.PanelEditInterfaceUML(viewMenu, diagram, (model.structural.diagram.component.base.InterfaceUML) element);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Use Case Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(UseCaseDiagram diagram, Element element) {
        if (element instanceof ActorUML)
            panelEdit = new PanelEditActorUML(viewMenu, diagram, (ActorUML) element);
        else if (element instanceof UseCaseUML)
            panelEdit = new PanelEditUseCaseUML(viewMenu, diagram, (UseCaseUML) element);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Element.
     * @param diagram Sequence Diagram.
     * @param element Element.
     */
    public void initPanelEditElement(SequenceDiagram diagram, Element element) {
        if (element instanceof LifelineUML)
            panelEdit = new PanelEditLifelineUML(viewMenu, diagram, (LifelineUML) element);
        else if (element instanceof InstanceUML)
            panelEdit = new PanelEditInstanceUML(viewMenu, diagram, (InstanceUML) element);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Association.
     * @param diagram
     * @param association 
     */
    public void initPanelEditAssociation(Diagram diagram, Association association) {
        if (diagram instanceof FeatureDiagram)
            initPanelEditAssociation((FeatureDiagram) diagram, association);
        else if (diagram instanceof ActivityDiagram)
            initPanelEditAssociation((ActivityDiagram) diagram, association);
        else if (diagram instanceof ClassDiagram)
            initPanelEditAssociation((ClassDiagram)    diagram, association);
        else if (diagram instanceof SequenceDiagram)
            initPanelEditAssociation((SequenceDiagram) diagram, association);
        else {
            panelEdit = new PanelEditAssociation(viewMenu, diagram, association);
            updatePanelEdit();
        }
    }
    
    /**
     * Method responsible for initializing the Panel Edit Association.
     * @param diagram Feature Diagram.
     * @param association Association.
     */
    public void initPanelEditAssociation(FeatureDiagram diagram, Association association) {
        if (association instanceof Connection)
            panelEdit = new PanelEditConnection(viewMenu, diagram, (Connection) association);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Association.
     * @param diagram Activity Diagram.
     * @param association Association.
     */
    public void initPanelEditAssociation(ActivityDiagram diagram, Association association) {
        if (association instanceof FlowUML)
            panelEdit = new PanelEditFlowUML(viewMenu, diagram, (FlowUML) association);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Association.
     * @param diagram Class Diagram.
     * @param association Association.
     */
    public void initPanelEditAssociation(ClassDiagram diagram, Association association) {
        if (association instanceof AssociationUML)
            panelEdit = new PanelEditAssociationUML(viewMenu, diagram, (AssociationUML) association);
        else 
            panelEdit = new PanelEditAssociation(viewMenu, diagram, association);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Association.
     * @param diagram Sequence Diagram.
     * @param association Association.
     */
    public void initPanelEditAssociation(SequenceDiagram diagram, Association association) {
        if (association instanceof MessageUML)
            panelEdit = new PanelEditMessageUML(viewMenu, diagram, (MessageUML) association);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Variability.
     * @param diagram Diagram.
     * @param variability Variability.
     * @param index Tab Index.
     */
    public void initPanelEditVariability(Diagram diagram, Variability variability, Integer index) {
        panelEdit = new PanelEditVariability(viewMenu, diagram, variability, index);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Stereotype.
     * @param stereotype Stereotype.
     */
    public void initPanelEditStereotype(Stereotype stereotype) {
        panelEdit = new PanelEditStereotype(viewMenu, stereotype);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Requirement.
     * @param requirement Requirement.
     * @param index Tab Index.
     */
    public void initPanelEditRequirement(Requirement requirement, Integer index) {
        panelEdit = new PanelEditRequirement(viewMenu, requirement, index);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Reference.
     * @param reference Reference.
     */
    public void initPanelEditReference(Reference reference) {
        panelEdit = new PanelEditReference(viewMenu, reference);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Product.
     * @param product Product.
     */
    public void initPanelEditProduct(Product product) {
        panelEdit = new PanelEditProduct(viewMenu, product);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Instance.
     * @param instance Instance.
     */
    public void initPanelEditInstance(Instance instance) {
        panelEdit = new PanelEditInstance(viewMenu, instance);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Artifact.
     * @param artifact Artifact.
     */
    public void initPanelEditArtifact(Artifact artifact) {
        panelEdit = new PanelEditArtifact(viewMenu, artifact);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Relationship.
     * @param relationship Relationship.
     */
    public void initPanelRelationship(Relationship relationship) {
        panelEdit = new PanelEditRelationship(viewMenu, relationship);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Metric.
     * @param metric Metric.
     */
    public void initPanelEditMetric(Metric metric) {
        panelEdit = new PanelEditMetric(viewMenu, metric);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for initializing the Panel Edit Measure.
     * @param measure Measure.
     */
    public void initPanelEditMeasure(Measure measure) {
        panelEdit = new PanelEditMeasure(viewMenu, measure);
        updatePanelEdit();
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     */
    public void updatePanelEdit() {
        panelEdit.updateUI();
        getScrollPanelEdit().setViewportView(panelEdit);
        getScrollPanelEdit().updateUI();
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
        return panelTree;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Tree.
     * @return Scroll Panel Tree.
     */
    public JScrollPane getScrollPanelTree() {
        return getScrollPane("tree");
    }
    
    /**
     * Method responsible for returning the Panel Edit.
     * @return Panel Edit.
     */
    public PanelEdit getPanelEdit() {
        return panelEdit;
    }
    
    /**
     * Method responsible for returning Scroll Panel Edit.
     * @return Scroll Panel Edit
     */
    public JScrollPane getScrollPanelEdit() {
        return getScrollPane("edit");
    }
}
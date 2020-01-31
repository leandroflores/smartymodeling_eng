package controller.view.panel.tree.popup;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
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
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.MethodUML;
import view.delete.ViewDeleteDiagram;
import view.delete.ViewDeleteElement;
import view.delete.variability.ViewDeleteVariability;
import view.edit.ViewEditDiagram;
import view.edit.ViewEditElement;
import view.edit.ViewEditProject;
import view.edit.classs.ViewEditAttribute;
import view.edit.classs.ViewEditMethod;
import view.panel.tree.popup.TreePopup;

/**
 * <p>Class of Controller <b>ControllerTreePopup</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Tree Popup</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    java.awt.event.KeyListener
 * @see    java.awt.event.MouseListener
 * @see    view.panel.tree.popup.TreePopup
 */
public class ControllerTreePopup implements MouseListener, KeyListener {
    private final TreePopup treePopup;
    
    /**
     * Default constructor method of Class.
     * @param treePopup Tree Popup.
     */
    public ControllerTreePopup(TreePopup treePopup) {
        this.treePopup = treePopup;
    }
    
    @Override
    public void mouseReleased(MouseEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.treePopup.getPanelTree().getTree().getLastSelectedPathComponent();
//        System.out.println("Released: " + node.getUserObject());
//        System.out.println("");
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.treePopup.getPanelTree().getTree().getLastSelectedPathComponent();
        if (!SwingUtilities.isLeftMouseButton(event))
            this.showPopup(node, event);
        else if (event.getClickCount() == 1)
            this.showPanelEdit(node);
        else if (event.getClickCount() == 2)
            this.showPanel(node);
    }
    
    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void keyTyped(KeyEvent evento) {}

    @Override
    public void keyPressed(KeyEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.treePopup.getPanelTree().getTree().getLastSelectedPathComponent();
        switch (event.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                this.showPanel(node);
                break;
            case KeyEvent.VK_DELETE:
                this.delete(node.getUserObject(), node);
                break;
            case KeyEvent.VK_F2:
                this.edit(node.getUserObject(), node);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent evento) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.treePopup.getPanelTree().getTree().getLastSelectedPathComponent();
        this.showPanelEdit(node);
    }
    
    /**
     * Method responsible for showing Popup.
     * @param node JTree Node.
     * @param event Mouse Event.
     */
    private void showPopup(DefaultMutableTreeNode node, MouseEvent event) {
        if (node.getUserObject() instanceof Project)
            this.treePopup.getMenuItemDelete().setVisible(false);
        else
            this.treePopup.getMenuItemDelete().setVisible(true);
        this.treePopup.show(event.getComponent(), event.getX(), event.getY());
    }

    /**
     * Method responsible for showing the Panel Edit.
     * @param node JTree Node.
     */
    private void showPanelEdit(DefaultMutableTreeNode node) {
        if (node != null && node.getUserObject() != null) {
            Diagram diagram = this.getDiagram(node);
            if (node.getUserObject() instanceof Project)
                this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditProject();
            else if (node.getUserObject() instanceof Diagram)
                this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditDiagram((Diagram) node.getUserObject());
            else if (node.getUserObject() instanceof Variability)
                this.showPanelEditVariability(diagram, (Variability) node.getUserObject());
            else if (node.getUserObject() instanceof Traceability)
                this.showPanelEditTraceability((Traceability) node.getUserObject());
            else if (node.getUserObject() instanceof Metric)
                this.showPanelEditMetric((Metric) node.getUserObject());
            else if (node.getUserObject() instanceof Product)
                this.showPanelEditProduct((Product) node.getUserObject());
            else if (node.getUserObject() instanceof Instance)
                this.showPanelEditInstance((Instance) node.getUserObject());
            else if (node.getUserObject() instanceof Artifact)
                this.showPanelEditArtifact((Artifact) node.getUserObject());
            else if (node.getUserObject() instanceof Element)
                this.showPanelEdit(diagram, (Element) node.getUserObject());
            else if (node.getUserObject() instanceof Association)
                this.showPanelEdit(diagram, (Association) node.getUserObject());
        }
    }
    
    /**
     * Method responsible for showing the Panel Edit Variability.
     * @param diagram Diagram.
     * @param variability Variability.
     */
    private void showPanelEditVariability(Diagram diagram, Variability variability) {
        this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditVariability(diagram, variability);
    }
    
    /**
     * Method responsible for showing the Panel Edit Traceability.
     * @param traceability Traceability.
     */
    private void showPanelEditTraceability(Traceability traceability) {
        this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditTraceability(traceability);
    }
    
    /**
     * Method responsible for showing the Panel Edit Metric.
     * @param metric Metric.
     */
    private void showPanelEditMetric(Metric metric) {
        this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditMetric(metric);
    }
    
    /**
     * Method responsible for showing the Panel Edit Product.
     * @param product Product.
     */
    private void showPanelEditProduct(Product product) {
        this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditProduct(product);
    }
    
    /**
     * Method responsible for showing the Panel Edit Instance.
     * @param instance Instance.
     */
    private void showPanelEditInstance(Instance instance) {
        this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditInstance(instance);
    }
    
    /**
     * Method responsible for showing the Panel Edit Artifact.
     * @param artifact Artifact.
     */
    private void showPanelEditArtifact(Artifact artifact) {
        this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditArtifact(artifact);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param element Element.
     */
    private void showPanelEdit(Diagram diagram, Element element) {
        if (diagram instanceof ActivityDiagram)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditElement((ActivityDiagram) diagram, element);
        else if (diagram instanceof ClassDiagram)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditElement((ClassDiagram) diagram, element);
        else if (diagram instanceof ComponentDiagram)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditElement((ComponentDiagram) diagram, element);
        else if (diagram instanceof UseCaseDiagram)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditElement((UseCaseDiagram)  diagram, element);
        else if (diagram instanceof SequenceDiagram)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditElement((SequenceDiagram) diagram, element);
    }
    
    /**
     * Method responsible for showing the Panel Edit.
     * @param diagram Diagram.
     * @param association Association.
     */
    private void showPanelEdit(Diagram diagram, Association association) {
        if (diagram instanceof ActivityDiagram)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditAssociation((ActivityDiagram) diagram, association);
        else if (diagram instanceof SequenceDiagram)
            this.treePopup.getPanelTree().getViewMenu().getPanelProject().initPanelEditAssociation((SequenceDiagram) diagram, association);
    }
    
    private void showPanel(DefaultMutableTreeNode node) {
        if (node.getUserObject() instanceof Diagram) 
            this.treePopup.getPanelTree().getViewMenu().showDiagram( (Diagram)  node.getUserObject());
        else if (node.getUserObject() instanceof Instance) 
            this.treePopup.getPanelTree().getViewMenu().showInstance((Instance) node.getUserObject());
    }
    
    /**
     * Method responsible for deleting the JTree Node.
     * @param object Object.
     * @param node JTree Node.
     */
    private void delete(Object object, DefaultMutableTreeNode node) {
        if (object instanceof Diagram)
            new ViewDeleteDiagram(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), (Diagram) object).setVisible(true);
        else if (object instanceof Element)
            new ViewDeleteElement(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), (Element) object).setVisible(true);
        else if (object instanceof Variability)
            new ViewDeleteVariability(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), this.getDiagram(node), (Variability) object).setVisible(true);
    }
    
    /**
     * Method responsible for editing JTree Node.
     * @param object Object.
     * @param node JTree Node.
     */
    private void edit(Object object, DefaultMutableTreeNode node) {
        if (object instanceof Project)
            new ViewEditProject(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), ((Project) object)).setVisible(true);
        else if (object instanceof Diagram)
            new ViewEditDiagram(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), ((Diagram) object)).setVisible(true);
        else if (object instanceof Variability)
            this.editVariability(object, node);
        else if (object instanceof AttributeUML)
            new ViewEditAttribute(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), ((ClassDiagram) this.getDiagram(node)), ((AttributeUML) object)).setVisible(true);
        else if (object instanceof MethodUML)
            new ViewEditMethod(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), ((ClassDiagram) this.getDiagram(node)), ((MethodUML) object)).setVisible(true);
        else if (object instanceof Element)
            new ViewEditElement(this.treePopup.getPanelTree().getViewMenu().getPanelModeling(), this.getDiagram(node), ((Element) object)).setVisible(true);
    }
    
    /**
     * Method responsible for editing Variability.
     * @param object Selected Object.
     * @param node JTree Node.
     */
    private void editVariability(Object object, DefaultMutableTreeNode node) {
        Diagram     diagram     = this.getDiagram((DefaultMutableTreeNode) node.getParent());
        Variability variability = (Variability) object;
        
    }
    
    /**
     * Method responsible for returning the Diagram from Node.
     * @param  node JTree Node.
     * @return Diagram.
     */
    private Diagram getDiagram(DefaultMutableTreeNode node) {
        DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
        while ((parent != null) && !(parent.getUserObject() instanceof Diagram))
            parent = (DefaultMutableTreeNode) parent.getParent();
        return parent == null ? null : (Diagram) parent.getUserObject();
    }
}
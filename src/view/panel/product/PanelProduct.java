package view.panel.product;

import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.product.test.Product_Final;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelProduct</b>.</p>
 * <p>Class responsible for defining the <b>Product_Final Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/08/2019
 * @see    controller.view.panel.
 * @see    model.structural.base.product.Product
 * @see    view.Panel
 */
public final class PanelProduct extends Panel {
    private final ViewMenu viewMenu;
    private final Product_Final  product;
    protected Double   zoom;
    protected Object   parent;
    protected mxGraph  graph;
    protected mxGraphComponent component;
    protected HashMap<Object, String> identifiers;
    protected HashMap<String, Object> objects;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param product Product_Final.
     */
    public PanelProduct(ViewMenu view, Product_Final product) {
        super();
        this.viewMenu = view;
        this.product  = product;
        this.initComponents();
    }
    
    /**
     * Method responsible for initializing the Components.
     */
    protected void initComponents() {
        this.setLayout(new BorderLayout());
        this.addComponents();
        this.getGraph().setCellsLocked(true);
    }
    
    @Override
    protected void addComponents() {
        this.createProductPanel();
    }
    
    /**
     * Method responsible for creating the Product_Final Panel.
     */
    public void createProductPanel() {
        JPanel panel = new JPanel();
        this.graph   = new mxGraph();
        this.parent  = this.graph.getDefaultParent();
        this.zoom    = 1.0d;
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
        this.graph.getModel().beginUpdate();
            this.atualizarDiagrama();
        this.graph.getModel().endUpdate();
        this.graph.refresh();
        
        this.graph.setAllowDanglingEdges(false);
        this.graph.setAllowNegativeCoordinates(false);
        this.graph.setAllowLoops(false);
        this.graph.setSplitEnabled(false);
        
        this.component = new mxGraphComponent(this.graph);        
        
        this.graph.setDisconnectOnMove(false);
        this.graph.setCellsDisconnectable(false);
        
        this.component.setPageBackgroundColor(Color.WHITE);
        this.component.setPreferredSize(new Dimension(1050, 530));
        this.component.setEnterStopsCellEditing(true);
        this.component.refresh();
        
                 panel.add(this.component);
        this.add(panel);
    }
    
    /**
     * Method responsible for clearing the Diagram.
     */
    public void clearDiagram() {
        this.graph.removeCells(this.graph.getChildVertices(this.graph.getDefaultParent()));
    }
    
    /**
     * Method responsible for updating the Diagram.
     */
    public void atualizarDiagrama() {
        this.clearDiagram();
        this.identifiers = new HashMap<>();
        
        this.addElements();
        this.addAssociations();
    }
    
    /**
     * Method responsible for adding the Diagram Elements.
     */
    private void addElements() {
        for (Element element : this.product.getElementsList()) {
            this.graph.getStylesheet().putCellStyle(element.getStyleLabel(), element.getStyle());
            String title  = element.getName();
            mxCell vertex = (mxCell) this.graph.insertVertex(this.parent, null, title, element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
            this.identifiers.put(vertex, element.getId());
            this.objects.put(element.getId(), vertex);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Associations.
     */
    private void addAssociations() {
        for (Association association : this.product.getAssociationsList()) {
            this.graph.getStylesheet().putCellStyle(association.getStyleLabel(), association.getStyle());
            Object edge = this.graph.insertEdge(this.parent, null, association.getTitle(), this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
            this.identifiers.put(edge, association.getId());
        }
    }
    
    /**
     * Method responsible for setting the Zoom Factor.
     * @param zoom Zoom Factor.
     */
    public void setZoom(Double zoom) {
        this.zoom = zoom;
        this.graph.getView().setScale(this.zoom);
    }
    
    /**
     * Method responsible for returning the Product_Final.
     * @return Product_Final.
     */
    public Product_Final getProduct() {
        return this.product;
    }

    /**
     * Method responsible for returning the Graph.
     * @return Graph.
     */
    public mxGraph getGraph() {
        return this.graph;
    }

    /**
     * Method responsible for returning the Component.
     * @return Component.
     */
    public mxGraphComponent getComponent() {
        return this.component;
    }

    /**
     * Method responsible for returning the Identifiers.
     * @return Identifiers Hash.
     */
    public HashMap<Object, String> getIdentifiers() {
        return this.identifiers;
    }
    
    /**
     * Method responsible for returning the Objects.
     * @return Objects Hash.
     */
    public HashMap<String, Object> getObjects() {
        return this.objects;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
}
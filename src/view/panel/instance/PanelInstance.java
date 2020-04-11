package view.panel.instance;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraph;
import controller.view.panel.instance.ControllerPanelInstance;
import controller.view.panel.instance.event.ControllerEventFocus;
import controller.view.panel.instance.event.ControllerEventMove;
import controller.view.panel.instance.event.ControllerEventPoints;
import controller.view.panel.instance.event.ControllerEventResize;
import java.awt.Color;
import java.util.HashMap;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JScrollPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Relationship;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelInstance</b>.</p>
 * <p>Class responsible for defining the <b>Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  06/10/2019
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    model.structural.base.product.Instance
 * @see    view.Panel
 */
public abstract class PanelInstance extends Panel {
    protected final ViewMenu viewMenu;
    protected final Instance instance;
    
    protected Double  zoom;
    protected HashMap identifiers;
    protected HashMap objects;
    
    protected Object  parent;
    protected mxGraph graph;
    protected mxGraphLayout layout;
    protected mxGraphComponent component;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     */
    public PanelInstance(ViewMenu view, Instance instance) {
        super();
        this.viewMenu = view;
        this.instance = instance;
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(1, 1));
    }
    
    @Override
    protected void addComponents() {
        this.addModelingPanel();
        this.addControllers();
    }
    
    /**
     * Method responsible for adding the Instance Panel Controllers.
     */
    public void addControllers() {
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED,   new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
    }
    
    /**
     * Method responsible for creating a New mxGraph.
     * @return New mxGraph.
     */
    private mxGraph createMxGraph() {
        return new mxGraph() {
            @Override
            public boolean isValidDropTarget(Object cell, Object[] cells) {
                return      isCellsMovable() 
                        && !isCellLocked(cell) 
                        && this.isValidParent(cell, cells);
            }
            
            public boolean isValidParent(Object cell, Object[] cells) {
                for (Object object : cells) {
                    if (((mxCell) object).getId().equals(((mxCell) cell).getId()))
                        return false;
                }
                
                if  (((mxCell) cell).getParent() == null)
                    return true;
                return this.isValidParent(((mxCell) cell).getParent(), cells);
            }
        };
    }
    
    /**
     * Method responsible for adding the Instance Panel.
     */
    public void addModelingPanel() {
        this.zoom        = 1.0d;
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
            this.initGraph();
            this.initGraphComponent();
            this.initGraphLayout();
            this.addGraphPanel();
        
        this.component.refresh();
    }
    
    /**
     * Method responsible for initializing the Graph.
     */
    protected void initGraph() {
        this.graph  = this.createMxGraph();
        this.parent = this.graph.getDefaultParent();
        
        this.graph.getModel().beginUpdate();
            this.updateInstance();
        this.graph.getModel().endUpdate();
        this.graph.refresh();
        
        this.graph.setAllowDanglingEdges(false);
        this.graph.setAllowNegativeCoordinates(false);
        this.graph.setAllowLoops(false);
        this.graph.setSplitEnabled(false);
        this.graph.setDisconnectOnMove(false);
        this.graph.setCellsDisconnectable(false);
    }
    
    /**
     * Method responsible for initializing the Graph Component.
     */
    protected void initGraphComponent() {
        this.component = new mxGraphComponent(this.graph);
        this.component.setEnterStopsCellEditing(true); 
        this.component.getGraphControl().addMouseListener((ControllerPanelInstance) this.controller);
        this.component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelInstance) this.controller);
        this.component.getViewport().setOpaque(true);
        this.component.getViewport().setBackground(Color.WHITE);
    }
    
    /**
     * Method responsible for initializing the Graph Layout.
     */
    protected void initGraphLayout() {
        this.layout = new mxParallelEdgeLayout(this.graph);
        this.layout.execute(this.graph.getDefaultParent());
    }
    
    /**
     * Method responsible for adding the Graph Panel.
     */
    protected void addGraphPanel() {
        this.createScrollPane("scrollPaneInstance");
        this.getScrollPaneInstance().setViewportView(this.component);
        this.add(this.getScrollPaneInstance());
    }
    
    /**
     * Method responsible for cleaning the Instance.
     */
    public void clearInstance() {
        this.graph.removeCells(this.graph.getChildCells(this.graph.getDefaultParent(), true, true));
    }
    
    /**
     * Method responsible for updating the Instance.
     */
    public void updateInstance() {
        this.clearInstance();
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
        this.addArtifacts();
        this.addRelationships();
    }
    
    /**
     * Method responsible for adding the Instance Artifacts.
     */
    public void addArtifacts() {
        for (Artifact artifact : this.instance.getArtifactsList())
            this.addArtifact(artifact, artifact.getElement());
    }
    
    /**
     * Method responsible for adding the Artifact Cell.
     * @param artifact Artifact.
     * @param element Element.
     */
    protected void addArtifact(Artifact artifact, Element element) {
        this.addStyle(artifact.getStyleLabel(), artifact.getStyle());
        String title = element.getName();
        mxCell cell  = (mxCell) this.graph.insertVertex(this.parent, artifact.getId(), title, artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        this.addArtifact(artifact, cell);
    }
    
    /**
     * Method responsible for adding a Artifact.
     * @param artifact Artifact.
     * @param cell mxCell.
     */
    protected void addArtifact(Artifact artifact, mxCell cell) {
        this.identifiers.put(cell, artifact.getId());
        this.objects.put(artifact.getId(), cell);
    }
    
    /**
     * Method responsible for adding a Identifier.
     * @param object Object Key.
     * @param id Identifier.
     */
    protected void addIdentifier(Object object, String id) {
        this.identifiers.put(object, id);
    }
    
    /**
     * Method responsible for returning the Artifact Id by Element.
     * @param  element Element.
     * @return Artifact Id.
     */
    public String getId(Element element) {
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.getElement().equals(element))
                return artifact.getId();
        }
        return null;
    }
    
    /**
     * Method responsible for adding the Instance Relationships.
     */
    public void addRelationships() {
        for (Relationship relationship : this.instance.getRelationshipsList()) {
            this.addStyle(relationship.getStyleLabel(), relationship.getStyle());
            String     title    = this.getTitle(relationship);
            mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, relationship.getId(), title, this.objects.get(this.getId(relationship.getAssociation().getSource())), this.objects.get(this.getId(relationship.getAssociation().getTarget())), relationship.getStyleLabel());
            mxGeometry geometry = this.getModel().getGeometry(edge);
                       geometry.setPoints(relationship.getPoints());
                       this.getModel().setGeometry(edge, geometry);
            this.addRelationship(relationship, edge);
        }
    }
    
    /**
     * Method responsible for returning the Relationship Title.
     * @param  relationship Relationship.
     * @return Relationship Title.
     */
    private String getTitle(Relationship relationship) {
        return relationship.getTitle();
    }
    
    /**
     * Method responsible for adding a Relationship.
     * @param relationship Relationship.
     * @param cell mxCell.
     */
    protected void addRelationship(Relationship relationship, mxCell cell) {
        this.identifiers.put(cell, relationship.getId());
        this.objects.put(relationship.getId(), cell);
    }
    
    /**
     * Method responsible for returning the Final Style.
     * @param  style Original Style.
     * @return Final Style (No Editable)
     */
    protected Map getStyle(Map style) {
        HashMap newStyle = new HashMap(style);
                newStyle.put(mxConstants.STYLE_EDITABLE, "0");
        return  newStyle;
    }
    
    /**
     * Method responsible for adding a Graph Style.
     * @param id Style Id.
     * @param properties Style Properties.
     */
    protected void addStyle(String id, Map properties) {
        this.graph.getStylesheet().putCellStyle(id, properties);
    }
    
    /**
     * Method responsible for returning the Default Edge Style.
     * @return Default Edge Style.
     */
    protected Map<String, Object> getDefaultEdgeStyle() {
        return this.getGraph().getStylesheet().getStyles().get("defaultEdge");
    }
    
    /**
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.instance.getDiagram();
    }
    
    /**
     * Method responsible for returning the Panel Zoom.
     * @return Panel Zoom.
     */
    public Double getZoom() {
        return this.zoom;
    }
    
    /**
     * Method responsible for setting the Panel Zoom.
     * @param zoom Zoom Value.
     */
    public void setZoom(Double zoom) {
        this.zoom = zoom;
        this.graph.getView().setScale(this.zoom);
    }
    
    /**
     * Method responsible for returning the Selected Relationship.
     * @return Selected Relationship.
     */
    public Relationship getSelectedRelationship() {
        mxCell cell = (mxCell) this.graph.getSelectionCell();
        String id   = this.getIdentifiers().get(cell);
        return this.getInstance().getRelationship(id);
    }
    
    /**
     * Method responsible for returning the Selected Artifact.
     * @return Selected Artifact.
     */
    public Artifact getSelectedArtifact() {
        mxCell cell = (mxCell) this.graph.getSelectionCell();
        String id   = this.getIdentifiers().get(cell);
        return this.getInstance().getArtifact(id);
    }
    
    /**
     * Method responsible for returning the Identifiers HashMap.
     * @return Identifiers HashMap.
     */
    public HashMap<Object, String> getIdentifiers() {
        return this.identifiers;
    }
    
    /**
     * Method responsible for returning the Objects HashMap.
     * @return Objects HashMap.
     */
    public HashMap<String, Object> getObjects() {
        return this.objects;
    }
    
    /**
     * Method responsible for returning the Graph.
     * @return Graph.
     */
    public mxGraph getGraph() {
        return this.graph;
    }

    /**
     * Method responsible for returning the Graph Layout.
     * @return Graph Layout.
     */
    public mxGraphLayout getGraphLayout() {
        return this.layout;
    }
    
    /**
     * Method responsible for returning the Graph Model.
     * @return Graph Model.
     */
    public mxGraphModel getModel() {
        return (mxGraphModel) this.graph.getModel();
    }
    
    /**
     * Method responsible for returning the Graph Component.
     * @return Graph Component.
     */
    public mxGraphComponent getComponent() {
        return this.component;
    }
    
    /**
     * Method responsible for returning the Scroll Pane Instance.
     * @return Scroll Pane Instance.
     */
    public JScrollPane getScrollPaneInstance() {
        return this.scrollPanes.get("scrollPaneInstance");
    }
}
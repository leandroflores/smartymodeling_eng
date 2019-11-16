package view.panel.instance;

import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxEvent;
import com.mxgraph.view.mxGraph;
import controller.view.panel.instance.ControllerPanelInstance;
import controller.view.panel.instance.event.ControllerEventFocus;
import controller.view.panel.instance.event.ControllerEventMove;
import controller.view.panel.instance.event.ControllerEventPoints;
import controller.view.panel.instance.event.ControllerEventResize;
import java.awt.Color;
import java.util.HashMap;
import java.awt.Dimension;
import java.util.Map;
import javax.swing.JScrollPane;
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
 * @see    controller.view.panel.
 * @see    model.structural.base.product.Instance
 * @see    view.Panel
 */
public abstract class PanelInstance extends Panel {
    protected final ViewMenu viewMenu;
    protected final Instance instance;
    protected Double  zoom;
    protected Object  parent;
    protected mxGraph graph;
    protected mxGraphComponent component;
    protected HashMap<Object, String> identifiers;
    protected HashMap<String, Object> objects;
    
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
     * Method responsible for adding the Instance Panel Controllers.
     */
    public void addControllers() {
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
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
    public void addInstancePanel() {
        this.graph       = this.createMxGraph();
        this.parent      = this.graph.getDefaultParent();
        this.zoom        = 1.0d;
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
        this.graph.getModel().beginUpdate();
            this.updateInstance();
        this.graph.getModel().endUpdate();
        this.graph.refresh();
        
        this.graph.setAllowDanglingEdges(false);
        this.graph.setAllowNegativeCoordinates(false);
        this.graph.setAllowLoops(false);
        this.graph.setSplitEnabled(false);
        this.graph.setConnectableEdges(false);
        
        this.component = new mxGraphComponent(this.graph);
        
        this.component.getGraphControl().addMouseListener((ControllerPanelInstance) this.controller);
        this.component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelInstance) this.controller);
        this.graph.setDisconnectOnMove(false);
        this.graph.setCellsDisconnectable(false);
        
        this.component.setPageBackgroundColor(Color.WHITE);
        this.component.setPreferredSize(new Dimension(1075, 500));
        this.component.setEnterStopsCellEditing(true);
        this.component.refresh();
     
        mxParallelEdgeLayout layout = new mxParallelEdgeLayout(this.graph);
                             layout.execute(this.graph.getDefaultParent());
        
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
        for (Artifact artifact : this.instance.getArtifactsList()) {
            Element element = artifact.getElement();
            this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
            String  title   = element.getName();
            mxCell  cell    = (mxCell) this.graph.insertVertex(this.parent, null, title, artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
            this.identifiers.put(cell, artifact.getId());
            this.objects.put(artifact.getId(), cell);
        }
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
            this.graph.getStylesheet().putCellStyle(relationship.getStyleLabel(), relationship.getStyle());
            mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, null, relationship.getTitle(), this.objects.get(this.getId(relationship.getAssociation().getSource())), this.objects.get(this.getId(relationship.getAssociation().getTarget())), relationship.getStyleLabel());
            mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                       geometry.setPoints(relationship.getPoints());
                       ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);           
            this.identifiers.put(edge, relationship.getId());
            this.objects.put(relationship.getId(), edge);
        }
    }
    
    /**
     * Method responsible for returning Default Edge Style.
     * @return Default Edge Style.
     */
    protected Map<String, Object> getDefaultEdgeStyle() {
        return this.getGraph().getStylesheet().getStyles().get("defaultEdge");
    }
    
    /**
     * Method responsible for defining Panel Zoom.
     * @param zoom Zoom Value.
     */
    public void setZoom(Double zoom) {
        this.zoom = zoom;
        this.graph.getView().setScale(this.zoom);
    }
    
    /**
     * Method responsible for returning the Scroll Pane Instance.
     * @return Scroll Pane Instance.
     */
    public JScrollPane getScrollPaneInstance() {
        return this.scrollPanes.get("scrollPaneInstance");
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Graph.
     * @return Graph.
     */
    public mxGraph getGraph() {
        return this.graph;
    }

    /**
     * Method responsible for returning the Graph Component.
     * @return Graph Component.
     */
    public mxGraphComponent getComponent() {
        return this.component;
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
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
}
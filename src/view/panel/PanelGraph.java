package view.panel;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Class of View <b>PanelGraph</b>.</p>
 * <p>Class responsible for defining the <b>Graph Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-13
 * @see    view.Panel
 */
public abstract class PanelGraph extends Panel {
    protected Double  zoom;
    protected HashMap objects;
    protected HashMap identifiers;
    protected Object  parent;
    protected mxGraph graph;
    protected mxGraphLayout layout;
    protected mxGraphComponent component;
    
    /**
     * Default constructor of Class.
     */
    public PanelGraph() {
        zoom = 1.0d;
    }
    
    /**
     * Method responsible for cleaning the Graph.
     */
    public void clearGraph() {
        graph.removeCells(graph.getChildCells(parent, true, true));
    }
    
    /**
     * Method responsible for loading the Default Styles.
     */
    protected void loadDefaultStyles() {}
    
    /**
     * Method responsible for adding the Panel Controllers.
     */
    public abstract void addControllers();
    
    /**
     * Method responsible for updating the Graph.
     */
    public abstract void updateGraph();
    
    /**
     * Method responsible for initializing the Graph.
     */
    protected void initGraph() {
        graph  = createMxGraph();
        parent = graph.getDefaultParent();
        
        graph.getModel().beginUpdate();
            updateGraph();
        graph.getModel().endUpdate();
        graph.refresh();
        
        graph.setAllowDanglingEdges(false);
        graph.setAllowNegativeCoordinates(false);
        graph.setAllowLoops(true);
        graph.setSplitEnabled(false);
        graph.setDisconnectOnMove(false);
        graph.setCellsDisconnectable(false);
    }
    
    /**
     * Method responsible for creating a New mxGraph.
     * @return New mxGraph.
     */
    protected mxGraph createMxGraph() {
        return new mxGraph() {
            @Override
            public boolean isValidDropTarget(Object cell, Object[] cells) {
                return      isCellsMovable() 
                        && !isCellLocked(cell) 
                        && isValidParent(cell, cells);
            }
            
            public boolean isValidParent(Object cell, Object[] cells) {
                for (Object object : cells) {
                    if (((mxCell) object).getId().equals(((mxCell) cell).getId()))
                        return false;
                }
                
                if  (((mxCell) cell).getParent() == null)
                    return true;
                return isValidParent(((mxCell) cell).getParent(), cells);
            }
        };
    }
    
    /**
     * Method responsible for adding the Modeling Panel.
     */
    public abstract void addModelingPanel();
    
    /**
     * Method responsible for adding the Graph Panel.
     */
    protected abstract void addGraphPanel();
    
    /**
     * Method responsible for initializing the Graph Component.
     */
    protected void initGraphComponent() {
        component = new mxGraphComponent(graph);
        component.setEnterStopsCellEditing(true); 
        component.getViewport().setOpaque(true);
        component.getViewport().setBackground(Color.WHITE);
    }
    
    /**
     * Method responsible for initializing the Graph Layout.
     */
    protected void initGraphLayout() {
        layout = new mxParallelEdgeLayout(graph);
        layout.execute(graph.getDefaultParent());
    }
    
    /**
     * Method responsible for adding a Graph Style.
     * @param id Style Id.
     * @param properties Style Properties.
     */
    protected void addStyle(String id, Map properties) {
        graph.getStylesheet().putCellStyle(id, properties);
    }
    
    /**
     * Method responsible for returning the Default Edge Style.
     * @return Default Edge Style.
     */
    protected Map<String, Object> getEdgeStyle() {
        return getGraph().getStylesheet().getStyles().get("defaultEdge");
    }
    
    /**
     * Method responsible for setting the Panel Zoom.
     * @return Panel Zoom.
     */
    public Double getZoom() {
        return zoom;
    }
    
    /**
     * Method responsible for setting the Panel Zoom.
     * @param zoom Zoom Value.
     */
    public void setZoom(Double zoom) {
        this.zoom = zoom;
        graph.getView().setScale(zoom);
    }
    
    /**
     * Method responsible for returning the Parent Cell.
     * @return Parent Cell.
     */
    public Object getParentCell() {
        return parent;
    }
    
    /**
     * Method responsible for returning the Graph.
     * @return Graph.
     */
    public mxGraph getGraph() {
        return graph;
    }

    /**
     * Method responsible for returning the Graph Layout.
     * @return Graph Layout.
     */
    public mxGraphLayout getGraphLayout() {
        return layout;
    }
    
    /**
     * Method responsible for returning the Graph Model.
     * @return Graph Model.
     */
    public mxGraphModel getModel() {
        return (mxGraphModel) graph.getModel();
    }
    
    /**
     * Method responsible for returning the Graph Component.
     * @return Graph Component.
     */
    public mxGraphComponent getComponent() {
        return component;
    }
}
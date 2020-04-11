package view.panel.diagram;

import com.mxgraph.layout.mxGraphLayout;
import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.Color;
import java.util.HashMap;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;
import javax.swing.JScrollPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.Panel;
import view.panel.operation.PanelOperation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.base.Diagram
 * @see    view.Panel
 */
public abstract class PanelDiagram extends Panel {
    protected final ViewMenu viewMenu;
    protected final Diagram diagram;
    
    protected String  operation;
    protected Integer type;
    protected Double  zoom;
    protected HashMap identifiers;
    protected HashMap objects;
    
    protected PanelOperation panel;
    
    protected Object  parent;
    protected mxGraph graph;
    protected mxGraphLayout layout;
    protected mxGraphComponent component;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     */
    public PanelDiagram(ViewMenu view, Diagram diagram) {
        super();
        this.viewMenu = view;
        this.diagram  = diagram;
    }
    
    /**
     * Method responsible for setting the Default Properties.
     */
    protected void setDefaultProperties() {
        this.setOperation("Click");
        this.setLayout(new GridBagLayout());
        this.type = 0;
    }
    
    @Override
    protected void addComponents() {
        this.addOperationsPanel();
        this.addModelingPanel();
        this.addControllers();
    }
    
    /**
     * Method responsible for adding Operations Panel on View.
     */
    public abstract void addOperationsPanel();
    
    /**
     * Method responsible for setting the Style.
     */
    public abstract void setStyle();
    
    /**
     * Method responsible for adding the Diagram Panel Controllers.
     */
    public abstract void addControllers();
    
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
     * Method responsible for adding the Modeling Panel.
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
        this.setStyle();
    }
    
    /**
     * Method responsible for initializing the Graph.
     */
    protected void initGraph() {
        this.graph  = this.createMxGraph();
        this.parent = this.graph.getDefaultParent();
        
        this.graph.getModel().beginUpdate();
            this.updateDiagram();
        this.graph.getModel().endUpdate();
        this.graph.refresh();
        
        this.graph.setAllowDanglingEdges(false);
        this.graph.setAllowNegativeCoordinates(false);
        this.graph.setAllowLoops(true);
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
        this.component.getGraphControl().addMouseListener((ControllerPanelDiagram) this.controller);
        this.component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelDiagram) this.controller);
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
        this.createScrollPane("scrollPaneDiagram");
        this.getScrollPaneDiagram().setViewportView(this.component);
        this.add(this.getScrollPaneDiagram(), this.setBodyConstraint(new GridBagConstraints()));
    }
    
    /**
     * Method responsible for cleaning the Diagram.
     */
    public void clearDiagram() {
        this.graph.removeCells(this.graph.getChildCells(this.parent, true, true));
    }
    
    /**
     * Method responsible for updating the Diagram.
     */
    public void updateDiagram() {
        this.clearDiagram();
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
        this.setClick();
        
        this.addElements();
        this.addAssociations();
    }
    
    /**
     * Method responsible for setting Click Operation.
     */
    public void setClick() {
        this.getPanelOperation().resetBackground();
        this.setOperation("Click");
        this.getPanelOperation().getClickButton().setBackground(this.getFocusColor());
    }
    
    /**
     * Method responsible for adding the Diagram Elements.
     */
    public void addElements() {
        for (Element element : this.diagram.getElementsList()) {
            this.addStyle(element.getStyleLabel(), element.getStyle());
            String  title   = this.getTitle(element);
            mxCell  cell    = (mxCell) this.graph.insertVertex(this.parent, element.getId(), title, element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
                    cell.setId(element.getId());
            this.addElement(element, cell);
        }
    }
    
    /**
     * Method responsible for returning the Element Title.
     * @param  element Element.
     * @return Element Title.
     */
    protected String getTitle(Element element) {
        return this.diagram.getStereotypes(element, "\n") + element.getName();
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     * @param cell mxCell.
     */
    protected void addElement(Element element, mxCell cell) {
        this.identifiers.put(cell, element.getId());
        this.objects.put(element.getId(), cell);
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
     * Method responsible for returning the Identifier by Object.
     * @param  object Object.
     * @return Identifier by Object.
     */
    public String getIdentifier(Object object) {
        return this.getIdentifiers().get(object);
    }
    
    /**
     * Method responsible for adding the Diagram Associations.
     */
    public void addAssociations() {
        for (Association association : this.diagram.getAssociationsList()) {
            this.addStyle(association.getStyleLabel(), association.getStyle());
            String     title    = this.getTitle(association);
            mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, association.getId(), title, this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
            mxGeometry geometry = this.getModel().getGeometry(edge);
                       geometry.setPoints(association.getPoints());
                       this.getModel().setGeometry(edge, geometry);
            this.addAssociation(association, edge);
        }
    }
    
    /**
     * Method responsible for returning the Association Title.
     * @param  association Association.
     * @return Association Title.
     */
    private String getTitle(Association association) {
        return association.getTitle();
    }
    
    /**
     * Method responsible for adding a Association.
     * @param association Association.
     * @param cell mxCell.
     */
    protected void addAssociation(Association association, mxCell cell) {
        this.identifiers.put(cell, association.getId());
        this.objects.put(association.getId(), cell);
    }
    
    /**
     * Method responsible for setting the Dependency Style.
     */
    public void setDependencyStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_OPEN);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Generalization Style.
     */
    public void setGeneralizationStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,  "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDFILL, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_BLOCK);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
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
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Operation.
     * @return Operation.
     */
    public String getOperation() {
        return this.operation;
    }
    
    /**
     * Method responsible for defining the Operation.
     * @param operation Operation.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Method responsible for returning the Type.
     * @return Type.
     */
    public Integer getType() {
        return this.type;
    }

    /**
     * Method responsible for defining the Type.
     * @param type Type.
     */
    public void setType(Integer type) {
        this.type = type;
        this.setStyle();
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
     * Method responsible for returning the Selected Association.
     * @return Selected Association.
     */
    public Association getSelectedAssociation() {
        mxCell cell = (mxCell) this.graph.getSelectionCell();
        String id   = this.getIdentifiers().get(cell);
        return this.getDiagram().getAssociation(id);
    }
    
    /**
     * Method responsible for returning the Selected Element.
     * @return Selected Element.
     */
    public Element getSelectedElement() {
        mxCell cell = (mxCell) this.graph.getSelectionCell();
        String id   = this.getIdentifiers().get(cell);
        return this.getDiagram().getElement(id);
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
     * Method responsible for returning the Panel Operation.
     * @return Panel Operation.
     */
    public abstract PanelOperation getPanelOperation();
    
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
     * Method responsible for returning the Scroll Pane Diagram.
     * @return Scroll Pane Diagram.
     */
    public JScrollPane getScrollPaneDiagram() {
        return this.scrollPanes.get("scrollPaneDiagram");
    }
}
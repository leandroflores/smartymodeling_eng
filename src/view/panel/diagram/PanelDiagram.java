package view.panel.diagram;

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
import java.awt.Dimension;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.Panel;
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
    private final ViewMenu viewMenu;
    private final Diagram diagram;
    protected String  operation;
    protected Integer type;
    protected Double  zoom;
    protected Object  parent;
    protected mxGraph graph;
    protected mxGraphComponent component;
    protected HashMap<Object, String> identifiers;
    protected HashMap<String, Object> objects;
    
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
     * Method responsible for initializing the Components.
     */
    protected void initComponents() {
        this.setOperation("Click");
//        this.setLayout(new GridLayout(2, 0));
        this.type = 0;
    }
    
    /**
     * Method responsible for adding Operations Panel on View.
     */
    public abstract void addOperationsPanel(); 
    
    /**
     * Method responsible for returning Association Items.
     * @return Association Items.
     */
    public abstract Object[] getAssociationItems();
    
    /**
     * Method responsible for reseting Background of Operations Panel.
     */
    public abstract void resetBackground();
    
    /**
     * Method responsible for defining Style.
     */
    public abstract void setStyle();
    
    /**
     * Method responsible for adding the Diagram Panel Controllers.
     */
    public abstract void addControllers();
    
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
     * Method responsible for adding Modeling Panel.
     */
    public void addModelingPanel() {
        this.graph       = this.createMxGraph();
        this.parent      = this.graph.getDefaultParent();
        this.zoom        = 1.0d;
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
        this.graph.getModel().beginUpdate();
            this.updateDiagram();
        this.graph.getModel().endUpdate();
        this.graph.refresh();
        
        this.graph.setAllowDanglingEdges(false);
        this.graph.setAllowNegativeCoordinates(false);
        this.graph.setAllowLoops(true);
        this.graph.setSplitEnabled(false);
        this.setStyle();
        
        this.component = new mxGraphComponent(this.graph);        
        
        this.component.getGraphControl().addMouseListener((ControllerPanelDiagram) this.controller);
        this.component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelDiagram) controller);
        this.graph.setDisconnectOnMove(false);
        this.graph.setCellsDisconnectable(false);
        
        this.component.setPageBackgroundColor(Color.WHITE);
        this.component.setPreferredSize(new Dimension(1075, 500));
        this.component.setEnterStopsCellEditing(true);
        this.component.refresh();
     
        mxParallelEdgeLayout layout = new mxParallelEdgeLayout(this.graph);
                             layout.execute(this.graph.getDefaultParent());
        
        this.createScrollPane("scrollPaneDiagram");
        this.getScrollPaneDiagram().setViewportView(this.component);
        this.add(this.getScrollPaneDiagram());
    }
    
    /**
     * Method responsible for cleaning the Diagram.
     */
    public void clearDiagram() {
//        this.graph.removeCells(this.graph.getChildCells(this.graph.getDefaultParent(), true, true));
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
     * Method responsible for adding Diagram Elements.
     */
    public void addElements() {
        for (int i = 0; i < this.diagram.getElementsList().size(); i++) {
            Element element = this.diagram.getElementsList().get(i);
            this.graph.getStylesheet().putCellStyle(element.getStyleLabel(), element.getStyle());
            String  title   = this.diagram.getStereotypes(element, "\n") + element.getName();
            mxCell  cell    = (mxCell) this.graph.insertVertex(this.parent, null, title, element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
            this.identifiers.put(cell, element.getId());
            this.objects.put(element.getId(), cell);
        }
    }
    
    /**
     * Method responsible for adding Diagram Associations.
     */
    public void addAssociations() {
        for (int i = 0; i < this.diagram.getAssociationsList().size(); i++) {
            Association association = this.diagram.getAssociationsList().get(i);
            this.graph.getStylesheet().putCellStyle(association.getStyleLabel(), association.getStyle());
            mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, null, association.getTitle(), this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
            mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                       geometry.setPoints(association.getPoints());
                       ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);
            this.identifiers.put(edge, association.getId());
            this.objects.put(association.getId(), edge);
        }
    }
    
    /**
     * Method responsible for setting Click Operation.
     */
    public void setClick() {
        this.resetBackground();
        this.setOperation("Click");
        this.getClickButton().setBackground(this.getFocusColor());
    }
    
    /**
     * Method responsible for returning Default Edge Style.
     * @return Default Edge Style.
     */
    protected Map<String, Object> getDefaultEdgeStyle() {
        return this.getGraph().getStylesheet().getStyles().get("defaultEdge");
    }
    
    /**
     * Method responsible for returning Image by URL.
     * @param  url URL Image.
     * @return Association Image.
     */
    protected ImageIcon getAssociationImage(String url) {
        return new ImageIcon("src/images/icons/associations/" + url + ".png");
    }
    
    /**
     * Method responsible for setting Dependency Style.
     */
    public void setDependencyStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_OPEN);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
    }
    
    /**
     * Method responsible for setting Generalization Style.
     */
    public void setGeneralizationStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,      "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_BLOCK);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
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
     * Method responsible for returning the Scroll Pane Diagram.
     * @return Scroll Pane Diagram.
     */
    public JScrollPane getScrollPaneDiagram() {
        return this.scrollPanes.get("scrollPaneDiagram");
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
     * Method responsible for returning Click Button.
     * @return Click Button.
     */
    public JButton getClickButton() {
        return this.buttons.get("clickButton");
    }
    
    /**
     * Method responsible for returning Variability Button.
     * @return Variability Button.
     */
    public JButton getVariabilityButton() {
        return this.buttons.get("variabilityButton");
    }
    
    /**
     * Method responsible for returning Edit Button.
     * @return Edit Button.
     */
    public JButton getEditButton() {
        return this.buttons.get("editButton");
    }
    
    /**
     * Method responsible for returning Delete Button.
     * @return Delete Button.
     */
    public JButton getDeleteButton() {
        return this.buttons.get("deleteButton");
    }
    
    /**
     * Method responsible for returning Association ComboBox.
     * @return Association ComboBox.
     */
    public JComboBox getAssociationComboBox() {
        return this.comboBoxes.get("associationComboBox");
    }
    
    /**
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
}
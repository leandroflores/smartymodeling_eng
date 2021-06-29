package view.panel.instance;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxConstants;
import com.mxgraph.util.mxEvent;
import controller.view.panel.instance.ControllerPanelInstance;
import controller.view.panel.instance.event.ControllerEventFocus;
import controller.view.panel.instance.event.ControllerEventMove;
import controller.view.panel.instance.event.ControllerEventPoints;
import controller.view.panel.instance.event.ControllerEventResize;
import java.util.HashMap;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JScrollPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Relationship;
import view.panel.PanelGraph;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelInstance</b>.</p>
 * <p>Class responsible for defining the <b>Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-06
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.PanelGraph
 */
public abstract class PanelInstance extends PanelGraph {
    protected final ViewMenu viewMenu;
    protected final Instance instance;
    
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
        setLayout(new GridLayout(1, 1));
    }
    
    @Override
    protected void addComponents() {
        addModelingPanel();
        addControllers();
    }
    
    @Override
    public void addControllers() {
        component.getGraph().addListener(mxEvent.CELLS_MOVED,   new ControllerEventMove(this));
        component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
    }
    
    @Override
    public void addModelingPanel() {
        identifiers = new HashMap<>();
        objects     = new HashMap<>();
            initGraph();
            loadDefaultStyles();
            initGraphComponent();
            initGraphLayout();
            addGraphPanel();
        component.refresh();
    }
    
    @Override
    protected void initGraph() {
        super.initGraph();
        graph.setAllowLoops(false);
    }
    
    @Override
    protected void initGraphComponent() {
        super.initGraphComponent();
        component.getGraphControl().addMouseListener((ControllerPanelInstance) controller);
        component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelInstance) controller);
    }
    
    @Override
    protected void addGraphPanel() {
        createScrollPane("instance");
        getScrollPaneInstance().setViewportView(component);
        add(getScrollPaneInstance());
    }
    
    @Override
    public void updateGraph() {
        clearGraph();
        identifiers = new HashMap<>();
        objects     = new HashMap<>();
        
        addArtifacts();
        addRelationships();
    }
    
    /**
     * Method responsible for adding the Instance Artifacts.
     */
    public void addArtifacts() {
        for (Artifact artifact : getInstance().getArtifactsList())
            addArtifact(artifact, artifact.getElement());
    }
    
    /**
     * Method responsible for adding the Artifact Cell.
     * @param artifact Artifact.
     * @param element Element.
     */
    protected void addArtifact(Artifact artifact, Element element) {
        addStyle(artifact.getStyleLabel(), artifact.getStyle());
        String title = element.getName();
        mxCell cell  = (mxCell) graph.insertVertex(parent, artifact.getId(), title, artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for adding a Artifact.
     * @param artifact Artifact.
     * @param cell mxCell.
     */
    protected void addArtifactCell(Artifact artifact, mxCell cell) {
        identifiers.put(cell, artifact.getId());
        objects.put(artifact.getId(), cell);
    }
    
    /**
     * Method responsible for adding a Identifier.
     * @param object Object Key.
     * @param id Identifier.
     */
    protected void addIdentifier(Object object, String id) {
        identifiers.put(object, id);
    }
    
    /**
     * Method responsible for returning the Artifact Id by Element.
     * @param  element Element.
     * @return Artifact Id.
     */
    public String getId(Element element) {
        for (Artifact artifact : instance.getArtifactsList()) {
            if (artifact.getElement().equals(element))
                return artifact.getId();
        }
        return null;
    }
    
    /**
     * Method responsible for adding the Instance Relationships.
     */
    public void addRelationships() {
        for (Relationship relationship : instance.getRelationshipsList()) 
            addRelationship(relationship, relationship.getAssociation());
    }
    
    /**
     * Method responsible for adding the Relationship Edge.
     * @param relationship Relationship.
     * @param association Association.
     */
    protected void addRelationship(Relationship relationship, Association association) {
        addStyle(relationship.getStyleLabel(), relationship.getStyle());
        String title = getTitle(relationship);
        mxCell edge  = (mxCell) graph.insertEdge(parent, relationship.getId(), title, objects.get(getId(relationship.getAssociation().getSource())), objects.get(getId(relationship.getAssociation().getTarget())), relationship.getStyleLabel());
        updatePoints(relationship, edge);
        addRelationshipCell(relationship, edge);
    }
    
    /**
     * Method responsible for updating the Relationship Points.
     * @param relationship Relationship.
     * @param edge Edge Cell.
     */
    public void updatePoints(Relationship relationship, mxCell edge) {
        mxGeometry geometry = getModel().getGeometry(edge);
                   geometry.setPoints(relationship.getPoints());
        getModel().setGeometry(edge, geometry);
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
    protected void addRelationshipCell(Relationship relationship, mxCell cell) {
        identifiers.put(cell, relationship.getId());
        objects.put(relationship.getId(), cell);
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
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return viewMenu;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return instance;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return instance.getDiagram();
    }
    
    /**
     * Method responsible for returning the Selected Relationship.
     * @return Selected Relationship.
     */
    public Relationship getSelectedRelationship() {
        mxCell cell = (mxCell) graph.getSelectionCell();
        String id   = getIdentifiers().get(cell);
        return getInstance().getRelationship(id);
    }
    
    /**
     * Method responsible for returning the Selected Artifact.
     * @return Selected Artifact.
     */
    public Artifact getSelectedArtifact() {
        mxCell cell = (mxCell) graph.getSelectionCell();
        String id   = getIdentifiers().get(cell);
        return getInstance().getArtifact(id);
    }
    
    /**
     * Method responsible for returning the Identifiers HashMap.
     * @return Identifiers HashMap.
     */
    public HashMap<Object, String> getIdentifiers() {
        return identifiers;
    }
    
    /**
     * Method responsible for returning the Objects HashMap.
     * @return Objects HashMap.
     */
    public HashMap<String, Object> getObjects() {
        return objects;
    }
    
    /**
     * Method responsible for returning the Scroll Pane Instance.
     * @return Scroll Pane Instance.
     */
    public JScrollPane getScrollPaneInstance() {
        return getScrollPane("instance");
    }
}
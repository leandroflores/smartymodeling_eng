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
import view.structural.ViewMenu;

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
    protected HashMap identifiers;
    protected HashMap objects;
    
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
     * Method responsible for adding the Modeling Panel.
     */
    public void addModelingPanel() {
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
            super.initGraph();
            this.loadDefaultStyles();
            this.initGraphComponent();
            super.initGraphLayout();
            this.addGraphPanel();
        this.component.refresh();
    }
    
    
    @Override
    protected void initGraph() {
        super.initGraph();
        this.graph.setAllowLoops(false);
    }
    
    @Override
    protected void initGraphComponent() {
        super.initGraphComponent();
        this.component.getGraphControl().addMouseListener((ControllerPanelInstance) this.controller);
        this.component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelInstance) this.controller);
    }
    
    /**
     * Method responsible for adding the Graph Panel.
     */
    protected void addGraphPanel() {
        this.createScrollPane("scrollPaneInstance");
        this.getScrollPaneInstance().setViewportView(this.component);
        this.add(this.getScrollPaneInstance());
    }
    
    @Override
    public void updateGraph() {
        this.clearGraph();
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
        this.addArtifacts();
        this.addRelationships();
    }
    
    /**
     * Method responsible for adding the Instance Artifacts.
     */
    public void addArtifacts() {
        for (Artifact artifact : this.getInstance().getArtifactsList())
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
        this.addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for adding a Artifact.
     * @param artifact Artifact.
     * @param cell mxCell.
     */
    protected void addArtifactCell(Artifact artifact, mxCell cell) {
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
        for (Relationship relationship : this.instance.getRelationshipsList()) 
            this.addRelationship(relationship, relationship.getAssociation());
    }
    
    /**
     * Method responsible for adding the Relationship Edge.
     * @param relationship Relationship.
     * @param association Association.
     */
    protected void addRelationship(Relationship relationship, Association association) {
        this.addStyle(relationship.getStyleLabel(), relationship.getStyle());
        String title = this.getTitle(relationship);
        mxCell edge  = (mxCell) this.graph.insertEdge(this.parent, relationship.getId(), title, this.objects.get(this.getId(relationship.getAssociation().getSource())), this.objects.get(this.getId(relationship.getAssociation().getTarget())), relationship.getStyleLabel());
        this.updatePoints(relationship, edge);
        this.addRelationshipCell(relationship, edge);
    }
    
    /**
     * Method responsible for updating the Relationship Points.
     * @param relationship Relationship.
     * @param edge Edge Cell.
     */
    public void updatePoints(Relationship relationship, mxCell edge) {
        mxGeometry geometry = this.getModel().getGeometry(edge);
                   geometry.setPoints(relationship.getPoints());
        this.getModel().setGeometry(edge, geometry);
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
     * Method responsible for returning the Scroll Pane Instance.
     * @return Scroll Pane Instance.
     */
    public JScrollPane getScrollPaneInstance() {
        return this.scrollPanes.get("scrollPaneInstance");
    }
}
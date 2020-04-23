package view.panel.diagram;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.Dimension;
import java.util.HashMap;
import java.awt.GridBagLayout;
import javax.swing.JScrollPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import style.association.StyleAssociation;
import view.panel.PanelGraph;
import view.panel.operation.PanelOperation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.base.Diagram
 * @see    view.panel.PanelGraph
 */
public abstract class PanelDiagram extends PanelGraph {
    protected final ViewMenu   viewMenu;
    protected final Diagram    diagram;
    protected PanelOperation   panel;
    protected StyleAssociation style;
    protected String  operation;
    protected Integer type;
    protected HashMap identifiers;
    protected HashMap objects;
    
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
    
    /**
     * Method responsible for setting Click Operation.
     */
    public void setClick() {
        this.getPanelOperation().resetBackground();
        this.setOperation("Click");
        this.getPanelOperation().getClickButton().setBackground(this.getFocusColor());
    }
    
    @Override
    protected void addComponents() {
        this.initPanelOperation();
        this.addModelingPanel();
        this.addControllers();
    }
    
    /**
     * Method responsible for initializing the Panel Operation.
     */
    public abstract void initPanelOperation();
    
    /**
     * Method responsible for initializing the Style Association.
     */
    protected void initStyleAssociation() {
        this.style = new StyleAssociation();
    }
    
    /**
     * Method responsible for setting the Style.
     */
    public abstract void setStyle();
    
    /**
     * Method responsible for adding the Diagram Panel Controllers.
     */
    public abstract void addControllers();
    
    /**
     * Method responsible for adding the Modeling Panel.
     */
    public void addModelingPanel() {
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
            super.initGraph();
            this.loadDefaultStyles();
            this.initStyleAssociation();
            this.initGraphComponent();
            super.initGraphLayout();
            this.addGraphPanel();
        this.component.refresh();
        this.setStyle();
    }
    
    @Override
    protected void initGraphComponent() {
        super.initGraphComponent();
        this.component.getGraphControl().addMouseListener((ControllerPanelDiagram) this.controller);
        this.component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelDiagram) this.controller);
    }
    
    /**
     * Method responsible for adding the Graph Panel.
     */
    protected void addGraphPanel() {
        this.createScrollPane("scrollPaneDiagram");
        this.getScrollPaneDiagram().setViewportView(this.component);
        this.getScrollPaneDiagram().setPreferredSize(new Dimension(100, 100));
        this.add(this.getPanelOperation(), this.createStartConstraint());
        this.add(this.getScrollPaneDiagram(), this.createBodyConstraint());
    }
    
    @Override
    public void updateGraph() {
        this.clearGraph();
        this.identifiers = new HashMap<>();
        this.objects     = new HashMap<>();
        
        this.setClick();
        
        this.addElements();
        this.addAssociations();
        this.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for adding the Diagram Elements.
     */
    public void addElements() {
        for (Element element : this.getDiagram().getElementsList())
            this.addElement(element);
    }
    
    /**
     * Method responsible for adding the Element Cell.
     * @param element Element.
     */
    protected void addElement(Element element) {
        this.addStyle(element.getStyleLabel(), element.getStyle());
        String title = this.getTitle(element);
        mxCell cell  = (mxCell) this.getGraph().insertVertex(this.getParentCell(), element.getId(), title, element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
               cell.setConnectable(true);
        this.addElementCell(element, cell);
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
    protected void addElementCell(Element element, mxCell cell) {
        this.identifiers.put(cell, element.getId());
        this.objects.put(element.getId(), cell);
    }
    
    /**
     * Method responsible for selecting a Cell by Id.
     * @param id Cell Id.
     */
    public void setSelected(String id) {
        mxCell cell = (mxCell) this.objects.get(id);
        if (cell != null)
            this.getGraph().setSelectionCell(cell);
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
        for (Association association : this.getDiagram().getAssociationsList())
            this.addAssociation(association);
    }
    
    /**
     * Method responsible for adding the Association Edge.
     * @param association Association.
     */
    protected void addAssociation(Association association) {
        this.addStyle(association.getStyleLabel(), association.getStyle());
        String title = this.getTitle(association);
        mxCell edge  = (mxCell) this.getGraph().insertEdge(this.parent, association.getId(), title, this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
        this.updatePoints(association, edge);
        this.addAssociationCell(association, edge);
    }
    
    /**
     * Method responsible for updating the Association Points.
     * @param association Association.
     * @param edge Edge Cell.
     */
    public void updatePoints(Association association, mxCell edge) {
        mxGeometry geometry = this.getModel().getGeometry(edge);
                   geometry.setPoints(association.getPoints());
        this.getModel().setGeometry(edge, geometry);
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
    protected void addAssociationCell(Association association, mxCell cell) {
        this.identifiers.put(cell, association.getId());
        this.objects.put(association.getId(), cell);
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
     * Method responsible for setting the Default Style.
     */
    public void setDefaultStyle() {
        this.getStyle().setDefaultStyle(this.getEdgeStyle());
    }
    
    /**
     * Method responsible for setting the Dependency Style.
     */
    public void setDependencyStyle() {
        this.getStyle().setDependencyStyle(this.getEdgeStyle());
    }
    
    /**
     * Method responsible for setting the Generalization Style.
     */
    public void setGeneralizationStyle() {
        this.getStyle().setGeneralizationStyle(this.getEdgeStyle());
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
     * Method responsible for returning the Panel Operation.
     * @return Panel Operation.
     */
    public abstract PanelOperation getPanelOperation();
    
    /**
     * Method responsible for returning the Style Association.
     * @return Style Association.
     */
    public StyleAssociation getStyle() {
        return this.style;
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
     * Method responsible for returning the Scroll Pane Diagram.
     * @return Scroll Pane Diagram.
     */
    public JScrollPane getScrollPaneDiagram() {
        return this.getScrollPane("scrollPaneDiagram");
    }
}
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
    protected String  operation;
    protected Integer type;
    protected PanelOperation panel;
    protected StyleAssociation style;
    
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
        setOperation("Click");
        setLayout(new GridBagLayout());
        type = 0;
    }
    
    /**
     * Method responsible for setting Click Operation.
     */
    public void setClick() {
        getPanelOperation().resetBackground();
        setOperation("Click");
        getPanelOperation().getClickButton().setBackground(getFocusColor());
    }
    
    @Override
    protected void addComponents() {
        initPanelOperation();
        addModelingPanel();
        addControllers();
    }
    
    /**
     * Method responsible for initializing the Panel Operation.
     */
    public abstract void initPanelOperation();
    
    /**
     * Method responsible for initializing the Style Association.
     */
    protected void initStyleAssociation() {
        style = new StyleAssociation();
    }
    
    /**
     * Method responsible for setting the Style.
     */
    public abstract void setStyle();
    
    @Override
    public void addModelingPanel() {
        identifiers = new HashMap<>();
        objects     = new HashMap<>();
            initGraph();
            loadDefaultStyles();
            initStyleAssociation();
            initGraphComponent();
            initGraphLayout();
            addGraphPanel();
        component.refresh();
        setStyle();
    }
    
    @Override
    protected void initGraphComponent() {
        super.initGraphComponent();
        component.getGraphControl().addMouseListener((ControllerPanelDiagram) controller);
        component.getGraphControl().getGraphContainer().addKeyListener((ControllerPanelDiagram) controller);
    }
    
    @Override
    protected void addGraphPanel() {
        createScrollPane("diagram");
        getScrollPaneDiagram().setViewportView(component);
        getScrollPaneDiagram().setPreferredSize(new Dimension(100, 100));
        add(getPanelOperation(), createStartConstraint());
        add(getScrollPaneDiagram(), createBodyConstraint());
    }
    
    @Override
    public void updateGraph() {
        clearGraph();
        identifiers = new HashMap<>();
        objects     = new HashMap<>();
        
        setClick();
        
        addElements();
        addAssociations();
        setZoom(zoom);
    }
    
    /**
     * Method responsible for adding the Diagram Elements.
     */
    public void addElements() {
        for (Element element : getDiagram().getElementsList())
            addElement(element);
    }
    
    /**
     * Method responsible for adding the Element Cell.
     * @param element Element.
     */
    protected void addElement(Element element) {
        addStyle(element.getStyleLabel(), element.getStyle());
        String title = getTitle(element);
        mxCell cell  = (mxCell) getGraph().insertVertex(getParentCell(), element.getId(), title, element.getPosition().x, element.getPosition().y, element.getSize().x, element.getSize().y, element.getStyleLabel());
               cell.setConnectable(true);
        addElementCell(element, cell);
    }
    
    /**
     * Method responsible for returning the Element Title.
     * @param  element Element.
     * @return Element Title.
     */
    protected String getTitle(Element element) {
        return diagram.getStereotypes(element, "\n") + element.getName();
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     * @param cell mxCell.
     */
    protected void addElementCell(Element element, mxCell cell) {
        identifiers.put(cell, element.getId());
        objects.put(element.getId(), cell);
    }
    
    /**
     * Method responsible for selecting a Cell by Id.
     * @param id Cell Id.
     */
    public void setSelected(String id) {
        mxCell cell = (mxCell) objects.get(id);
        if (cell != null)
            getGraph().setSelectionCell(cell);
    }
    
    /**
     * Method responsible for returning the Selected Element.
     * @return Selected Element.
     */
    public Element getSelectedElement() {
        mxCell cell = (mxCell) graph.getSelectionCell();
        String id   = getIdentifiers().get(cell);
        return getDiagram().getElement(id);
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
     * Method responsible for returning the Identifier by Object.
     * @param  object Object.
     * @return Identifier by Object.
     */
    public String getIdentifier(Object object) {
        return getIdentifiers().get(object);
    }
    
    /**
     * Method responsible for adding the Diagram Associations.
     */
    public void addAssociations() {
        for (Association association : getDiagram().getAssociationsList())
            addAssociation(association);
    }
    
    /**
     * Method responsible for adding the Association Edge.
     * @param association Association.
     */
    protected void addAssociation(Association association) {
        addStyle(association.getStyleLabel(), association.getStyle());
        String title = getTitle(association);
        mxCell edge  = (mxCell) getGraph().insertEdge(parent, association.getId(), title, objects.get(association.getSource().getId()), objects.get(association.getTarget().getId()), association.getStyleLabel());
        updatePoints(association, edge);
        addAssociationCell(association, edge);
    }
    
    /**
     * Method responsible for updating the Association Points.
     * @param association Association.
     * @param edge Edge Cell.
     */
    public void updatePoints(Association association, mxCell edge) {
        mxGeometry geometry = getModel().getGeometry(edge);
                   geometry.setPoints(association.getPoints());
        getModel().setGeometry(edge, geometry);
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
        identifiers.put(cell, association.getId());
        objects.put(association.getId(), cell);
    }
    
    /**
     * Method responsible for returning the Selected Association.
     * @return Selected Association.
     */
    public Association getSelectedAssociation() {
        mxCell cell = (mxCell) graph.getSelectionCell();
        String id   = getIdentifiers().get(cell);
        return getDiagram().getAssociation(id);
    }
    
    /**
     * Method responsible for setting the Default Style.
     */
    public void setDefaultStyle() {
        getStyle().setDefaultStyle(getEdgeStyle());
    }
    
    /**
     * Method responsible for setting the Dependency Style.
     */
    public void setDependencyStyle() {
        getStyle().setDependencyStyle(getEdgeStyle());
    }
    
    /**
     * Method responsible for setting the Generalization Style.
     */
    public void setGeneralizationStyle() {
        getStyle().setGeneralizationStyle(getEdgeStyle());
    }
    
    /**
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return viewMenu;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
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
        return style;
    }
    
    /**
     * Method responsible for returning the Operation.
     * @return Operation.
     */
    public String getOperation() {
        return operation;
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
        return type;
    }

    /**
     * Method responsible for defining the Type.
     * @param type Type.
     */
    public void setType(Integer type) {
        this.type = type;
        setStyle();
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
     * Method responsible for returning the Scroll Pane Diagram.
     * @return Scroll Pane Diagram.
     */
    public JScrollPane getScrollPaneDiagram() {
        return getScrollPane("diagram");
    }
}
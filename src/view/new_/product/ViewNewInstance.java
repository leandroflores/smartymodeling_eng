package view.new_.product;

import controller.view.new_.product.ControllerViewNewInstance;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.product.Instance;
import model.structural.base.variability.Variability;
import view.edit.panel.base.product.PanelBaseArtifacts;
import view.edit.panel.base.product.PanelBaseNewInstance;
import view.edit.panel.base.product.PanelBaseOptional;
import view.edit.panel.base.product.PanelBaseVarPoints;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewInstance</b>.</p>
 * <p>Class responsible for defining the <b>New Instance View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.new_.product.
 * @see    model.structural.base.product.Instance
 * @see    view.new_.ViewNew
 */
public final class ViewNewInstance extends ViewNew {
    private final Instance instance;
    private PanelBaseNewInstance panelBaseNewInstance;
    private PanelBaseOptional    panelBaseOptional;
    private PanelBaseVarPoints   panelBaseVarPoints;
    private PanelBaseArtifacts   panelBaseArtifacts;
    private HashMap<String, Integer> elements;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewInstance(ViewMenu view) {
        super(view);
        this.instance   = new Instance();
        this.controller = new ControllerViewNewInstance(this);
        this.title      = "New Instance";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(575, 520);
        this.addHeader();
        this.addComponents();
        this.addFooter();
        this.addPanelBaseNewInstance();
    }

    @Override
    public void addComponents() {
        this.addTabbedPane();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Tabbed Pane.
     */
    private void addTabbedPane() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(500, 400));
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base New Instance.
     */
    public void addPanelBaseNewInstance() {
        this.panelBaseNewInstance = new PanelBaseNewInstance(this, this.instance);
        this.tabbedPane.removeAll();
        this.tabbedPane.add("Instance", this.panelBaseNewInstance);
        this.getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for adding the Panel Base Optional.
     */
    public void addPanelBaseOptional() {
        this.resetElements();
        this.instance.reset();
        this.panelBaseOptional = new PanelBaseOptional(this);
        this.tabbedPane.add("Optional", this.panelBaseOptional);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(1));
        this.tabbedPane.setEnabledAt(0, false);
        this.getInsertButton().setEnabled(false);
    }

    /**
     * Method responsible for removing the Panel Base Optional.
     */
    public void removePanelBaseOptional() {
        this.tabbedPane.getComponent(0).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(0));
        this.tabbedPane.remove(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Var Points.
     */
    public void addPanelBaseVarPoints() {
        this.panelBaseVarPoints = new PanelBaseVarPoints(this);
        this.tabbedPane.add("Variation Points", this.panelBaseVarPoints);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(2));
        this.tabbedPane.setEnabledAt(1, false);
        this.getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for removing the Panel Base Optional.
     */
    public void removePanelBaseVarPoints() {
        this.tabbedPane.getComponent(1).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(1));
        this.tabbedPane.remove(2);
    }
    
    /**
     * Method responsible for adding the Panel Base Artifacts.
     */
    public void addPanelBaseArtifacts() {
        this.panelBaseArtifacts = new PanelBaseArtifacts(this);
        this.tabbedPane.add("Artifacts", this.panelBaseArtifacts);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(3));
        this.tabbedPane.setEnabledAt(2, false);
        this.getInsertButton().setEnabled(true);        
    }
    
    /**
     * Method responsible for removing the Panel Base Artifacts.
     */
    public void removePanelBaseArtifacts() {
        this.tabbedPane.getComponent(2).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(2));
        this.tabbedPane.remove(3);
        this.getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for returning if a Element is Variation Point.
     * @param element Element.
     * @return Element is Variation Point.
     */
    public boolean isVariationPoint(Element element) {
        return this.elements.get(element.getId()) > 0;
    }
    
    /**
     * Method responsible for returning the Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> getVariabilities() {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : this.instance.getDiagram().getVariabilitiesList()) {
            if (this.elements.get(variability.getVariationPoint().getId()) > 0)
                filter.add(variability);
        }
        return filter;
    }
    
    /**
     * Method responsible for reseting the Elements.
     */
    private void resetElements() {
        this.elements = new HashMap();
        for (Element element : this.instance.getDiagram().getElementsList())
            this.elements.put(element.getId(), 0);
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     */
    public void add(Element element) {
        this.elements.put(element.getId(), this.elements.get(element.getId()) + 1);
    }
    
    /**
     * Method responsible for returning the Elements Size.
     * @return Elements Size.
     */
    public Integer getElementsSize() {
        Integer count = 0;
        for (Map.Entry<String, Integer> element : this.elements.entrySet())
               count += (element.getValue() > 0) ? 1 : 0;
        return count;
    }
    
    /**
     * Method responsible for returning if the Instance contains a Association.
     * @param  association Association.
     * @return Instance contains Association.
     */
    private boolean contains(Association association) {
        if (this.elements.get(association.getSource().getId()) > 0)
            return true;
        return this.elements.get(association.getTarget().getId()) > 0;
    }
    
    /**
     * Method responsible for returning the Associations Size.
     * @return Associations Size.
     */
    public Integer getAssociationsSize() {
        Integer count = 0;
        for (Association association : this.getDiagram().getAssociationsList())
                count += this.contains(association) ? 1 : 0;
        return  count;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewNewInstance getController() {
        return (ControllerViewNewInstance) this.controller;
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
     * Method responsible for returning the Panel Base New Instance.
     * @return Panel Base New Instance.
     */
    public PanelBaseNewInstance getPanelBaseNewInstance() {
        return this.panelBaseNewInstance;
    }

    /**
     * Method responsible for returning the Panel Base Optional.
     * @return Panel Base Optional.
     */
    public PanelBaseOptional getPanelBaseOptional() {
        return this.panelBaseOptional;
    }

    /**
     * Method responsible for returning the Panel Base Var Points.
     * @return Panel Base Var Points.
     */
    public PanelBaseVarPoints getPanelBaseVarPoints() {
        return this.panelBaseVarPoints;
    }

    /**
     * Method responsible for returning the Panel Base Artifats.
     * @return Panel Base Artifats.
     */
    public PanelBaseArtifacts getPanelBaseArtifacts() {
        return this.panelBaseArtifacts;
    }

    /**
     * Method responsible for returning the Elements Map.
     * @return Elements Map.
     */
    public HashMap<String, Integer> getElements() {
        return this.elements;
    }
}
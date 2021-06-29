package view.panel.new_.base.product;

import controller.view.modal.new_.base.product.ControllerViewNewInstance;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.product.Instance;
import model.structural.base.variability.Variability;
import view.modal.new_.base.product.ViewNewInstance;
import view.panel.new_.PanelNew;
import view.panel.new_.base.product.instance.PanelBaseArtifacts;
import view.panel.new_.base.product.instance.PanelBaseInstance;
import view.panel.new_.base.product.instance.PanelBaseOptional;
import view.panel.new_.base.product.instance.PanelBaseVarPoints;

/**
 * <p>Class of View <b>PanelNewInstance</b>.</p> 
 * <p>Class responsible for defining a <b>New Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    model.structural.base.product.Instance
 * @see    view.panel.new_.PanelNew
 */
public final class PanelNewInstance extends PanelNew {
    private final Instance instance;
    private HashMap elements;
    
    /**
     * Default constructor method of Class.
     * @param view View New Instance.
     * @param instance Instance.
     */
    public PanelNewInstance(ViewNewInstance view, Instance instance) {
        super(view);
        this.instance = instance;
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseInstance();
    }
    
    /**
     * Method responsible for adding the Panel Base Instance.
     */
    public void addPanelBaseInstance() {
        addPanel("base_instance", new PanelBaseInstance(this, instance));
        tabbedPane.removeAll();
        createScrollPane("base_instance", getPanelBaseInstance());
        tabbedPane.add("Instance", getScrollPane("base_instance"));
    }
    
    /**
     * Method responsible for adding the Panel Base Optional.
     */
    public void addPanelBaseOptional() {
        resetElements();
        instance.reset();
        addPanel("optional", new PanelBaseOptional(this, instance));
        createScrollPane("optional", getPanelBaseOptional());
        getScrollPane("optional").setPreferredSize(new Dimension(600, 350));
        tabbedPane.add("Optional", getScrollPane("optional"));
        tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(1));
        tabbedPane.setEnabledAt(0, false);
        getView().getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for removing the Panel Base Optional.
     */
    public void removePanelBaseOptional() {
        tabbedPane.getComponent(0).setEnabled(true);
        tabbedPane.setSelectedComponent(tabbedPane.getComponent(0));
        tabbedPane.remove(1);
        resetElements();
    }
    
    /**
     * Method responsible for adding the Panel Base Var Points.
     */
    public void addPanelBaseVarPoints() {
        addPanel("variation_points", new PanelBaseVarPoints(this, instance));
        createScrollPane("variation_points", getPanelBaseVarPoints());
        getScrollPane("variation_points").setPreferredSize(new Dimension(600, 350));
        tabbedPane.add("Variation Points", getScrollPane("variation_points"));
        tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(2));
        tabbedPane.setEnabledAt(1, false);
        getView().getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for removing the Panel Base Optional.
     */
    public void removePanelBaseVarPoints() {
        tabbedPane.getComponent(1).setEnabled(true);
        tabbedPane.setSelectedComponent(tabbedPane.getComponent(1));
        tabbedPane.remove(2);
        resetElements();
    }
    
    /**
     * Method responsible for adding the Panel Base Artifacts.
     */
    public void addPanelBaseArtifacts() {
        addPanel("artifacts", new PanelBaseArtifacts(this, instance));
        tabbedPane.add("Artifacts", getPanelBaseArtifacts());
        tabbedPane.setSelectedComponent(tabbedPane.getComponentAt(3));
        tabbedPane.setEnabledAt(2, false);
        getView().getInsertButton().setEnabled(true);
    }
    
    /**
     * Method responsible for removing the Panel Base Artifacts.
     */
    public void removePanelBaseArtifacts() {
        tabbedPane.getComponent(2).setEnabled(true);
        tabbedPane.setSelectedComponent(tabbedPane.getComponent(2));
        tabbedPane.remove(3);
        getView().getInsertButton().setEnabled(false);
        resetVariants();
    }
    
    /**
     * Method responsible for returning if a Element is Variation Point.
     * @param element Element.
     * @return Element is Variation Point.
     */
    public boolean isVariationPoint(Element element) {
        return getElements().get(element.getId()) > 0;
    }
    
    /**
     * Method responsible for returning the Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> getVariabilities() {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : instance.getDiagram().getVariabilitiesList()) {
            if (getElements().get(variability.getVariationPoint().getId()) > 0)
                filter.add(variability);
        }
        return filter;
    }
    
    /**
     * Method responsible for reseting the Elements.
     */
    private void resetElements() {
        elements = new HashMap();
        for (Element element : instance.getDiagram().getElementsList())
            elements.put(element.getId(), 0);
    }
    
    /**
     * Method responsible for reset Variants.
     */
    private void resetVariants() {
        List<Element> filter = instance.getDiagram().filterOptionalElements();
        for (Map.Entry<String, Integer> element : getElements().entrySet()) {
            if (!filter.contains(instance.getDiagram().getElement(element.getKey())))
                element.setValue(0);
        }
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     */
    public void add(Element element) {
        elements.put(element.getId(), getElements().get(element.getId()) + 1);
    }
    
    /**
     * Method responsible for returning the Elements Size.
     * @return Elements Size.
     */
    public Integer getElementsSize() {
        Integer count = 0;
        for (Map.Entry<String, Integer> element : getElements().entrySet())
               count += (element.getValue() > 0) ? 1 : 0;
        return count;
    }
    
    /**
     * Method responsible for returning if the Instance contains a Association.
     * @param  association Association.
     * @return Instance contains Association.
     */
    private boolean contains(Association association) {
        return (getElements().get(association.getSource().getId()) > 0
             && getElements().get(association.getTarget().getId()) > 0);
    }
    
    /**
     * Method responsible for returning the Associations Size.
     * @return Associations Size.
     */
    public Integer getAssociationsSize() {
        Integer count  = 0;
        for (Association association : getDiagram().getAssociationsList())
                count += contains(association) ? 1 : 0;
        return  count;
    }
    
    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return (PanelBaseInstance) getPanel("base_instance");
    }
    
    /**
     * Method responsible for returning the Panel Base Optional.
     * @return Panel Base Optional.
     */
    public PanelBaseOptional getPanelBaseOptional() {
        return (PanelBaseOptional) getPanel("optional");
    }

    /**
     * Method responsible for returning the Panel Base Var Points.
     * @return Panel Base Var Points.
     */
    public PanelBaseVarPoints getPanelBaseVarPoints() {
        return (PanelBaseVarPoints) getPanel("variation_points");
    }

    /**
     * Method responsible for returning the Panel Base Artifats.
     * @return Panel Base Artifats.
     */
    public PanelBaseArtifacts getPanelBaseArtifacts() {
        return (PanelBaseArtifacts) getPanel("artifacts");
    }
    
    /**
     * Method responsible for returning the Elements Map.
     * @return Elements Map.
     */
    public HashMap<String, Integer> getElements() {
        return elements;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return getInstance().getDiagram();
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return instance;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewNewInstance getController() {
        return (ControllerViewNewInstance) getView().getController();
    }
    
    @Override
    public ViewNewInstance getView() {
        return (ViewNewInstance) viewNew;
    }
}
package view.panel.new_.base.product;

import controller.view.modal.new_.base.product.ControllerViewNewInstance;
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
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseInstance();
    }
    
    /**
     * Method responsible for adding the Panel Base Instance.
     */
    public void addPanelBaseInstance() {
        this.addPanel("panelBaseInstance", new PanelBaseInstance(this, this.instance));
        this.tabbedPane.removeAll();
        this.tabbedPane.add("Instance", this.getPanelBaseInstance());
//        this.getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for adding the Panel Base Optional.
     */
    public void addPanelBaseOptional() {
        this.resetElements();
        this.instance.reset();
        this.addPanel("panelBaseOptional", new PanelBaseOptional(this, this.instance));
        this.tabbedPane.add("Optional", this.getPanelBaseOptional());
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(1));
        this.tabbedPane.setEnabledAt(0, false);
        this.getView().getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for removing the Panel Base Optional.
     */
    public void removePanelBaseOptional() {
        this.tabbedPane.getComponent(0).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(0));
        this.tabbedPane.remove(1);
        this.resetElements();
    }
    
    /**
     * Method responsible for adding the Panel Base Var Points.
     */
    public void addPanelBaseVarPoints() {
        this.addPanel("panelBaseVarPoints", new PanelBaseVarPoints(this, this.instance));
        this.tabbedPane.add("Variation Points", this.getPanelBaseVarPoints());
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(2));
        this.tabbedPane.setEnabledAt(1, false);
        this.getView().getInsertButton().setEnabled(false);
    }
    
    /**
     * Method responsible for removing the Panel Base Optional.
     */
    public void removePanelBaseVarPoints() {
        this.tabbedPane.getComponent(1).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(1));
        this.tabbedPane.remove(2);
        this.resetElements();
    }
    
    /**
     * Method responsible for adding the Panel Base Artifacts.
     */
    public void addPanelBaseArtifacts() {
        this.addPanel("panelBaseArtifacts", new PanelBaseArtifacts(this, this.instance));
        this.tabbedPane.add("Artifacts", this.getPanelBaseArtifacts());
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(3));
        this.tabbedPane.setEnabledAt(2, false);
        this.getView().getInsertButton().setEnabled(true);
    }
    
    /**
     * Method responsible for removing the Panel Base Artifacts.
     */
    public void removePanelBaseArtifacts() {
        this.tabbedPane.getComponent(2).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(2));
        this.tabbedPane.remove(3);
        this.getView().getInsertButton().setEnabled(false);
        this.resetVariants();
    }
    
    /**
     * Method responsible for returning if a Element is Variation Point.
     * @param element Element.
     * @return Element is Variation Point.
     */
    public boolean isVariationPoint(Element element) {
        return this.getElements().get(element.getId()) > 0;
    }
    
    /**
     * Method responsible for returning the Variabilities List.
     * @return Variabilities List.
     */
    public List<Variability> getVariabilities() {
        List<Variability> filter = new ArrayList<>();
        for (Variability variability : this.instance.getDiagram().getVariabilitiesList()) {
            if (this.getElements().get(variability.getVariationPoint().getId()) > 0)
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
     * Method responsible for reset Variants.
     */
    private void resetVariants() {
        List<Element> filter = this.instance.getDiagram().filterOptionalElements();
        for (Map.Entry<String, Integer> element : this.getElements().entrySet()) {
            if (!filter.contains(this.instance.getDiagram().getElement(element.getKey())))
                element.setValue(0);
        }
    }
    
    /**
     * Method responsible for adding a Element.
     * @param element Element.
     */
    public void add(Element element) {
        this.elements.put(element.getId(), this.getElements().get(element.getId()) + 1);
    }
    
    /**
     * Method responsible for returning the Elements Size.
     * @return Elements Size.
     */
    public Integer getElementsSize() {
        Integer count = 0;
        for (Map.Entry<String, Integer> element : this.getElements().entrySet())
               count += (element.getValue() > 0) ? 1 : 0;
        return count;
    }
    
    /**
     * Method responsible for returning if the Instance contains a Association.
     * @param  association Association.
     * @return Instance contains Association.
     */
    private boolean contains(Association association) {
        return    (this.getElements().get(association.getSource().getId()) > 0)
               && (this.getElements().get(association.getTarget().getId()) > 0);
    }
    
    /**
     * Method responsible for returning the Associations Size.
     * @return Associations Size.
     */
    public Integer getAssociationsSize() {
        Integer count  = 0;
        for (Association association : this.getDiagram().getAssociationsList())
                count += this.contains(association) ? 1 : 0;
        return  count;
    }
    
    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return (PanelBaseInstance) this.getPanel("panelBaseInstance");
    }
    
    /**
     * Method responsible for returning the Panel Base Optional.
     * @return Panel Base Optional.
     */
    public PanelBaseOptional getPanelBaseOptional() {
        return (PanelBaseOptional) this.getPanel("panelBaseOptional");
    }

    /**
     * Method responsible for returning the Panel Base Var Points.
     * @return Panel Base Var Points.
     */
    public PanelBaseVarPoints getPanelBaseVarPoints() {
        return (PanelBaseVarPoints) this.getPanel("panelBaseVarPoints");
    }

    /**
     * Method responsible for returning the Panel Base Artifats.
     * @return Panel Base Artifats.
     */
    public PanelBaseArtifacts getPanelBaseArtifacts() {
        return (PanelBaseArtifacts) this.getPanel("panelBaseArtifacts");
    }
    
    /**
     * Method responsible for returning the Elements Map.
     * @return Elements Map.
     */
    public HashMap<String, Integer> getElements() {
        return this.elements;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.getInstance().getDiagram();
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewNewInstance getController() {
        return (ControllerViewNewInstance) this.getView().getController();
    }
    
    @Override
    public ViewNewInstance getView() {
        return (ViewNewInstance) this.viewNew;
    }
}
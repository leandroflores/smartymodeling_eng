package view.new_.product;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.product.Artefact;
import model.structural.base.product.Instance;
import model.structural.base.variability.Variability;
import view.edit.panel.base.product.PanelBaseArtefacts;
import view.edit.panel.base.product.PanelBaseInstance;
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
    private PanelBaseInstance  panelBaseInstance;
    private PanelBaseOptional  panelBaseOptional;
    private PanelBaseVarPoints panelBaseVarPoints;
    private PanelBaseArtefacts panelBaseArtefacts;
    private HashMap<String, Integer> elements;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewInstance(ViewMenu view) {
        super(view);
//        this.controller = new ControllerViewNewProduct_Final(this);
        this.title      = "New Instance";
        this.instance   = new Instance();
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(650, 570);
        this.addHeader();
        this.addComponents();
        this.addFooter();
//        this.setValues();
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
            this.addPanelBaseInstance();
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Instance.
     */
    public void addPanelBaseInstance() {
        this.panelBaseInstance = new PanelBaseInstance(this, this.instance);
        this.tabbedPane.removeAll();
        this.tabbedPane.add("Instance", this.panelBaseInstance);
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
     * Method responsible for adding the Panel Base Artefacts.
     */
    public void addPanelBaseArtefacts() {
        this.panelBaseArtefacts = new PanelBaseArtefacts(this);
        this.tabbedPane.add("Artefacts", this.panelBaseArtefacts);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponentAt(3));
        this.tabbedPane.setEnabledAt(2, false);
    }
    
    /**
     * Method responsible for removing the Panel Base Artefacts.
     */
    public void removePanelBaseArtefacts() {
        this.tabbedPane.getComponent(2).setEnabled(true);
        this.tabbedPane.setSelectedComponent(this.tabbedPane.getComponent(2));
        this.tabbedPane.remove(3);
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
     * Method responsible for updating the Instance Elements.
     */
    public void updateInstance() {
        for (Map.Entry<String, Integer> artefact : this.elements.entrySet()) {
            if (artefact.getValue() > 0)
                this.instance.addArtefact(new Artefact(this.instance.getDiagram().getElement(artefact.getKey())));
        }
    }
    
    /**
     * Method responsible for adding the New Instance.
     */
    public void addNewInstance() {
        if (this.instance.getProduct() != null) {
            this.instance.getProduct().addInstance(this.instance);
            this.view.showInstance(this.instance);
        }
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
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return this.panelBaseInstance;
    }

    /**
     * Method responsible for returning the Elements Map.
     * @return Elements Map.
     */
    public HashMap<String, Integer> getElements() {
        return this.elements;
    }
}
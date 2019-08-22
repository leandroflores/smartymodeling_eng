package view.panel.modeling;

import com.mxgraph.util.mxCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import model.structural.base.Diagram;
import model.structural.base.product.Product;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import view.Panel;
import view.panel.diagram.PanelDiagram;
import view.panel.diagram.types.PanelActivityDiagram;
import view.panel.diagram.types.PanelClassDiagram;
import view.panel.diagram.types.PanelComponentDiagram;
import view.panel.diagram.types.PanelSequenceDiagram;
import view.panel.diagram.types.PanelUseCaseDiagram;
import view.panel.logo.PanelLogo;
import view.panel.product.PanelProduct;
import view.panel.tabbed.PanelTabbed;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelModeling</b>.</p>
 * <p>Class responsible for defining the <b>Modeling Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    view.Panel
 * @see    view.structural.ViewMenu
 */
public final class PanelModeling extends Panel {
    private final ViewMenu viewMenu;
    private PanelTabbed panelTabbed;
    private HashMap<String, Component> tabs;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelModeling(ViewMenu view) {
        super();
        this.viewMenu = view;
        this.tabs     = new HashMap<>();
//        this.controller = new ControllerPainelModelagem(this);
        this.addComponents();
        this.clear();
    }
    
    @Override
    protected void addComponents() {
        this.panelTabbed = new PanelTabbed(this);
        this.add(this.panelTabbed);
    }
    
    /**
     * Method responsible for adding a Tab to Panel Modeling.
     * @param id Component Id.
     * @param title Title Tab.
     * @param component Tab Component.
     */
    private void addTab(String id, String title, Component component) {
        Component get = this.tabs.get(id);
        if (get == null) {
            this.panelTabbed.addTab(title, component);
            this.tabs.put(id, component);
            get = component;
        }
        this.panelTabbed.setSelectedComponent(get);
    }
    
    /**
     * Method responsible for adding a Diagram.
     * @param diagram Diagram.
     */
    public void addDiagram(Diagram diagram) {
        this.addTab(diagram.getId(), diagram.getName(), this.createPanelDiagram(diagram));
    }
    
    /**
     * Method responsible for adding a Product.
     * @param product Product. 
     */
    public void addProduct(Product product) {
        PanelProduct component = this.createPanelProduct(product);
        this.addTab(product.getId(), product.getName(), component);
    }
    
    /**
     * Method responsible for updating Diagrams.
     */
    public void updateDiagrams() {
        for (Component component : this.panelTabbed.getComponents()) {
            if (component instanceof PanelDiagram)
                ((PanelDiagram) component).updateDiagram();
        }
    }
    
    /**
     * Method responsible for returning a new Diagrams Panel.
     * @param  diagram Diagram.
     * @return New Diagram Panel.
     */
    private PanelDiagram createPanelDiagram(Diagram diagram) {
        switch (diagram.getType()) {
            case "Activity":
                return new PanelActivityDiagram(this.viewMenu,  (ActivityDiagram) diagram);
            case "Class":
                return new PanelClassDiagram(this.viewMenu,     (ClassDiagram)   diagram);
            case "Component":
                return new PanelComponentDiagram(this.viewMenu, (ComponentDiagram) diagram);
            case "Sequence":
                return new PanelSequenceDiagram(this.viewMenu,  (SequenceDiagram) diagram);
            case "UseCase":
                return new PanelUseCaseDiagram(this.viewMenu,   (UseCaseDiagram) diagram);
            default:
                break;
        }
        return null;
    }
    
    /**
     * Method responsible for returning a New Product Panel.
     * @param  product Product.
     * @return New Product Panel.
     */
    private PanelProduct createPanelProduct(Product product) {
        return new PanelProduct(this.viewMenu, product);
    }
    
    /**
     * Method responsible for removing a Diagram from Panel Modeling.
     * @param diagram Diagram.
     */
    public void removeDiagram(Diagram diagram) {
        if (this.tabs.get(diagram.getId()) != null) {
            this.panelTabbed.remove(this.tabs.get(diagram.getId()));
            this.tabs.remove(diagram.getId());
            this.updateUI();
        }
    }
    
    /**
     * Method responsible for removing a Product from Panel Modeling.
     * @param product Product.
     */
    public void removeProduct(Product product) {
        if (this.tabs.get(product.getId()) != null) {
            this.panelTabbed.remove(this.tabs.get(product.getId()));
            this.tabs.remove(product.getId());
            this.updateUI();
        }
    }
    
    /**
     * Method responsible for updating a Diagram.
     * @param diagram Diagram.
     */
    public void updateDiagram(Diagram diagram) {
        if (this.tabs.get(diagram.getId()) != null) {
            this.removeDiagram(diagram);
            this.addDiagram(diagram);
        }
    }
    
    /**
     * Method responsible for defining the Zoom.
     * @param zoom Zoom Value.
     */
    public void setZoom(Double zoom) {
        if (this.getPanelDiagram() != null)
            this.getPanelDiagram().setZoom(zoom);
        else if (this.getPanelProduct() != null);
            this.getPanelProduct().setZoom(zoom);
    }
    
    /**
     * Method responsible for returning a Diagram Image.
     * @return Diagram Image.
     */
    public BufferedImage getImage() {
        if (this.getPanelDiagram() != null)
            return mxCellRenderer.createBufferedImage(this.getPanelDiagram().getGraph(), null, 1, Color.WHITE, true, null);
        else if (this.getPanelProduct() != null)
            return mxCellRenderer.createBufferedImage(this.getPanelProduct().getGraph(), null, 1, Color.WHITE, true, null);
        return null;
    }
    
    /**
     * Method responsible for clearing the Diagrams.
     */
    public void clear() {
        this.tabs = new HashMap<>();
        this.panelTabbed.removeAll();
        if (this.viewMenu.getProject() == null)
            this.addTab("Start", "Start", new PanelLogo(this.viewMenu));
    }
    
    /**
     * Method responsible for returning Panel Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanelDiagram() {
        Component component = this.panelTabbed.getSelectedComponent();
        if (component instanceof PanelDiagram)
            return (PanelDiagram) component;
        return null;
    }
    
    /**
     * Method responsible for returning Panel Product.
     * @return Panel Product.
     */
    public PanelProduct getPanelProduct() {
        Component component = this.panelTabbed.getSelectedComponent();
        if (component instanceof PanelProduct)
            return (PanelProduct) component;
        return null;
    }
    
    /**
     * Method responsible for returning the Panel Tabbed.
     * @return Panel Tabbed.
     */
    public PanelTabbed getPanelTabbed() {
        return this.panelTabbed;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
}
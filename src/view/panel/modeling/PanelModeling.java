package view.panel.modeling;

import com.mxgraph.util.mxCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import view.Panel;
import view.panel.diagram.PanelDiagram;
import view.panel.diagram.types.PanelActivityDiagram;
import view.panel.diagram.types.PanelClassDiagram;
import view.panel.diagram.types.PanelComponentDiagram;
import view.panel.diagram.types.PanelFeatureDiagram;
import view.panel.diagram.types.PanelSequenceDiagram;
import view.panel.diagram.types.PanelUseCaseDiagram;
import view.panel.instance.PanelInstance;
import view.panel.instance.types.PanelActivityInstance;
import view.panel.instance.types.PanelClassInstance;
import view.panel.instance.types.PanelComponentInstance;
import view.panel.instance.types.PanelSequenceInstance;
import view.panel.instance.types.PanelUseCaseInstance;
import view.panel.logo.PanelLogo;
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
        this.addTab(diagram.getId(),  diagram.getName(), this.createPanelDiagram(diagram));
    }
    
    /**
     * Method responsible for adding a Instance.
     * @param instance Instance.
     */
    public void addInstance(Instance instance) {
        this.addTab(instance.getCompleteId(), instance.getName(), this.createPanelInstance(instance));
    }
    
    /**
     * Method responsible for updating the Modeling Panel.
     */
    public void updateModelingPanel() {
        for (Component component : this.panelTabbed.getComponents()) {
            if (component instanceof PanelDiagram)
                ((PanelDiagram)  component).updateGraph();
            else if (component instanceof PanelInstance)
                ((PanelInstance) component).updateGraph();
        }
    }
    
    /**
     * Method responsible for updating the Instance Panels.
     */
    public void updateInstancePanels() {
        for (Component component : this.panelTabbed.getComponents()) {
            if (component instanceof PanelInstance) {
                if (((PanelInstance) component).getInstance().isEmpty())
                    this.removeInstance(((PanelInstance) component).getInstance());
                else
                    ((PanelInstance) component).updateGraph();
            }
        }
    }
    
    /**
     * Method responsible for returning a new Diagrams Panel.
     * @param  diagram Diagram.
     * @return New Diagram Panel.
     */
    private PanelDiagram createPanelDiagram(Diagram diagram) {
        switch (diagram.getType()) {
            case "Feature":
                return new PanelFeatureDiagram(this.viewMenu,   (FeatureDiagram) diagram);
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
     * Method responsible for returning a New Instance Panel.
     * @param  instance Instance.
     * @return New Instance Panel.
     */
    private PanelInstance createPanelInstance(Instance instance) {
        switch (instance.getDiagram().getType()) {
            case "Activity":
                return new PanelActivityInstance(this.viewMenu,  instance);
            case "Class":
                return new PanelClassInstance(this.viewMenu,     instance);
            case "Component":
                return new PanelComponentInstance(this.viewMenu, instance);
            case "Sequence":
                return new PanelSequenceInstance(this.viewMenu,  instance);
            case "UseCase":
                return new PanelUseCaseInstance(this.viewMenu,   instance);
            default:
                break;
        }
        return null;
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
     * Method responsible for removing a Instance from Panel Modeling.
     * @param instance Instance.
     */
    public void removeInstance(Instance instance) {
        if (this.tabs.get(instance.getCompleteId()) != null) {
            this.panelTabbed.remove(this.tabs.get(instance.getCompleteId()));
            this.tabs.remove(instance.getCompleteId());
            this.updateUI();
        }
    }
    
    /**
     * Method responsible for updating a Diagram.
     * @param diagram Diagram.
     */
    public void updateDiagram(Diagram diagram) {
        if (this.tabs.get(diagram.getId()) != null) {
            PanelDiagram panel = this.getPanelDiagram(diagram);
            Double  zoom       = panel.getGraph().getView().getScale();
            Integer horizontal = panel.getScrollPaneDiagram().getHorizontalScrollBar().getValue();
            Integer vertical   = panel.getScrollPaneDiagram().getVerticalScrollBar().getValue();
            
            this.removeDiagram(diagram);
            this.addDiagram(diagram);
            
            panel = this.getPanelDiagram(diagram);
            panel.getGraph().getView().setScale(zoom);
            panel.getScrollPaneDiagram().getHorizontalScrollBar().setValue(horizontal);
            panel.getScrollPaneDiagram().getVerticalScrollBar().setValue(vertical);
        }
    }
    
    /**
     * Method responsible for updating a Instance.
     * @param instance Instance.
     */
    public void updateInstance(Instance instance) {
        if (this.tabs.get(instance.getCompleteId()) != null) {
            PanelInstance panel = this.getPanelInstance(instance);
            Double  zoom        = panel.getGraph().getView().getScale();
            Integer horizontal  = panel.getScrollPaneInstance().getHorizontalScrollBar().getValue();
            Integer vertical    = panel.getScrollPaneInstance().getVerticalScrollBar().getValue();
                this.removeInstance(instance);
                this.addInstance(instance);
            panel = this.getPanelInstance(instance);
            panel.getGraph().getView().setScale(zoom);
            panel.getScrollPaneInstance().getHorizontalScrollBar().setValue(horizontal);
            panel.getScrollPaneInstance().getVerticalScrollBar().setValue(vertical);
        }
    }
    
    /**
     * Method responsible for setting the Zoom.
     * @param zoom Zoom Value.
     */
    public void setZoom(Double zoom) {
        if (this.getPanelDiagram() != null)
            this.getPanelDiagram().setZoom(zoom);
        else if (this.getPanelInstance() != null)
            this.getPanelInstance().setZoom(zoom);
    }
    
    /**
     * Method responsible for returning a Image.
     * @return Image.
     */
    public BufferedImage getImage() {
//        Color color = new Color(0.925f, 0.925f, 0.925f, 1.00f);
        Color color = Color.WHITE;
        if (this.getPanelDiagram() != null)
            return mxCellRenderer.createBufferedImage(this.getPanelDiagram().getGraph(),  null, 1, color, true, null);
        else if (this.getPanelInstance() != null)
            return mxCellRenderer.createBufferedImage(this.getPanelInstance().getGraph(), null, 1, color, true, null);
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
     * Method responsible for returning the Panel Diagram.
     * @param  diagram Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanelDiagram(Diagram diagram) {
        return (PanelDiagram) this.tabs.get(diagram.getId());
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
     * Method responsible for returning the Panel Instance.
     * @param  instance Instance.
     * @return Panel Instance.
     */
    public PanelInstance getPanelInstance(Instance instance) {
        return (PanelInstance) this.tabs.get(instance.getCompleteId());
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    public PanelInstance getPanelInstance() {
        Component component = this.panelTabbed.getSelectedComponent();
        if (component instanceof PanelInstance)
            return (PanelInstance) component;
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
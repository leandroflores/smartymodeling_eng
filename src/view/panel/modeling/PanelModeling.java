package view.panel.modeling;

import com.mxgraph.util.mxCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import view.panel.Panel;
import view.panel.PanelGraph;
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
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelModeling</b>.</p>
 * <p>Class responsible for defining the <b>Modeling Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    view.panel.Panel
 * @see    view.main.structural.ViewMenu
 */
public final class PanelModeling extends Panel {
    private final ViewMenu viewMenu;
    private PanelTabbed panelTabbed;
    private HashMap panels;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelModeling(ViewMenu view) {
        super();
        viewMenu = view;
        panels   = new HashMap<>();
        addComponents();
        clear();
    }
    
    @Override
    protected void addComponents() {
        panelTabbed = new PanelTabbed(this);
        add(panelTabbed);
    }
    
    /**
     * Method responsible for updating the Panel Main.
     */
    public void updatePanelMain() {
        viewMenu.getPanelMain().setModeling(!isVoid());
        /*if (isVoid())
            viewMenu.getPanelMain().setModeling(false);
        else 
            viewMenu.getPanelMain().setModeling(true);
            viewMenu.getPanelMain().setOriginalZoom(getSelectedPanel().getZoom() != 1.0d);*/
    }
    
    /**
     * Method responsible for returning if Panel Modeling is Void.
     * @return Panel Modeling is Void.
     */
    private boolean isVoid() {
        return viewMenu.getProject() == null || panelTabbed.getTabCount() == 0;
    }
    
    /**
     * Method responsible for adding a Tab to Panel Modeling.
     * @param id Component Id.
     * @param title Title Tab.
     * @param component Tab Component.
     */
    private void addTab(String id, String title, Component component) {
        Component get = getPanels().get(id);
        if (get == null) {
            panelTabbed.addTab(title, null, component, id);
            panels.put(id, component);
            get = component;
        }
        updatePanelMain();
        panelTabbed.setSelectedComponent(get);
    }
    
    /**
     * Method responsible for adding a Diagram.
     * @param diagram Diagram.
     */
    public void addDiagram(Diagram diagram) {
        addTab(diagram.getId(), diagram.getName(), createPanelDiagram(diagram));
    }
    
    /**
     * Method responsible for returning a New Panel Diagram.
     * @param  diagram Diagram.
     * @return New Panel Diagram.
     */
    private PanelDiagram createPanelDiagram(Diagram diagram) {
        switch (diagram.getType()) {
            case "Feature":
                return new PanelFeatureDiagram(viewMenu, (FeatureDiagram) diagram);
            case "Activity":
                return new PanelActivityDiagram(viewMenu, (ActivityDiagram) diagram);
            case "Class":
                return new PanelClassDiagram(viewMenu, (ClassDiagram)   diagram);
            case "Component":
                return new PanelComponentDiagram(viewMenu, (ComponentDiagram) diagram);
            case "Sequence":
                return new PanelSequenceDiagram(viewMenu, (SequenceDiagram) diagram);
            case "UseCase":
                return new PanelUseCaseDiagram(viewMenu, (UseCaseDiagram) diagram);
            default:
                break;
        }
        return null;
    }
    
    /**
     * Method responsible for updating a Diagram.
     * @param diagram Diagram.
     */
    public void updateDiagram(Diagram diagram) {
        if (panels.get(diagram.getId()) != null) {
            PanelDiagram panel = getPanelDiagram(diagram);
                         panel.updateGraph();
            getViewMenu().setZoom(panel.getZoom());
            panelTabbed.updateUI();
            updateUI();
        }
    }
    
    /**
     * Method responsible for setting selected the Cell.
     * @param diagram Diagram.
     * @param id Object Id.
     */
    public void setSelected(Diagram diagram, String id) {
        if (getPanelDiagram(diagram) != null) 
            getPanelDiagram(diagram).setSelected(id);
    }
    
    /**
     * Method responsible for updating a Diagram Tab.
     * @param diagram Diagram.
     */
    public void updateTab(Diagram diagram) {
        if (getPanels().get(diagram.getId()) != null)
            panelTabbed.updateTab(diagram);
    }
    
    /**
     * Method responsible for removing a Diagram from Panel Modeling.
     * @param diagram Diagram.
     */
    public void removeDiagram(Diagram diagram) {
        if (panels.get(diagram.getId()) != null) {
            panelTabbed.remove(diagram.getId(), getPanels().get(diagram.getId()));
            panels.remove(diagram.getId());
            updateUI();
        }
        updatePanelMain();
    }
    
    /**
     * Method responsible for updating the Modeling Panels.
     */
    public void updateModelingPanels() {
        for (Component component : panelTabbed.getComponents()) {
            if (component instanceof PanelGraph)
                ((PanelGraph) component).updateGraph();
        }
    }
    
    /**
     * Method responsible for adding a Instance.
     * @param instance Instance.
     */
    public void addInstance(Instance instance) {
        addTab(instance.getCompleteId(), instance.getName(), createPanelInstance(instance));
    }
    
    /**
     * Method responsible for returning a New Instance Panel.
     * @param  instance Instance.
     * @return New Instance Panel.
     */
    private PanelInstance createPanelInstance(Instance instance) {
        switch (instance.getDiagram().getType()) {
            case "Activity":
                return new PanelActivityInstance(viewMenu, instance);
            case "Class":
                return new PanelClassInstance(viewMenu, instance);
            case "Component":
                return new PanelComponentInstance(viewMenu, instance);
            case "Sequence":
                return new PanelSequenceInstance(viewMenu, instance);
            case "UseCase":
                return new PanelUseCaseInstance(viewMenu, instance);
            default:
                break;
        }
        return null;
    }
    
    /**
     * Method responsible for updating a Instance.
     * @param instance Instance.
     */
    public void updateInstance(Instance instance) {
        if (panels.get(instance.getCompleteId()) != null) {
            PanelInstance panel = getPanelInstance(instance);
                          panel.updateGraph();
            panelTabbed.setTitleAt(getIndex(panel), "Test");
            updateUI();
        }
    }
    
    /**
     * Method responsible for updating a Instance Tab.
     * @param instance Instance.
     */
    public void updateTab(Instance instance) {
        if (panels.get(instance.getCompleteId()) != null) {
            panelTabbed.updateTab(instance);
        }
    }
    
    /**
     * Method responsible for removing a Instance from Panel Modeling.
     * @param instance Instance.
     */
    public void removeInstance(Instance instance) {
        if (panels.get(instance.getCompleteId()) != null) {
            panelTabbed.remove(instance.getCompleteId(), getPanels().get(instance.getCompleteId()));
            panels.remove(instance.getCompleteId());
            updateUI();
        }
        updatePanelMain();
    }
    
    /**
     * Method responsible for updating the Instance Panels.
     */
    public void updateInstancePanels() {
        for (Component component : panelTabbed.getComponents()) {
            if (component instanceof PanelInstance) {
                if (((PanelInstance) component).getInstance().isEmpty())
                    removeInstance(((PanelInstance) component).getInstance());
                else
                    ((PanelInstance) component).updateGraph();
            }
        }
    }
    
    /**
     * Method responsible for setting the Zoom.
     * @param zoom Zoom Value.
     */
    public void setZoom(Double zoom) {
        if (getSelectedPanel() != null)
            getSelectedPanel().setZoom(zoom);
    }
    
    /**
     * Method responsible for returning a Image.
     * @return Image.
     */
    public BufferedImage getImage() {
//        Color color = new Color(0.925f, 0.925f, 0.925f, 1.00f);
        Color color = Color.WHITE;
        if (getPanelDiagram() != null)
            return mxCellRenderer.createBufferedImage(getPanelDiagram().getGraph(),  null, 1, color, true, null);
        else if (getPanelInstance() != null)
            return mxCellRenderer.createBufferedImage(getPanelInstance().getGraph(), null, 1, color, true, null);
        return null;
    }
    
    /**
     * Method responsible for clearing the Diagrams.
     */
    public void clear() {
        panels = new HashMap<>();
        panelTabbed.removeAll();
        if (viewMenu.getProject() == null)
            addTab("Start", "Start", new PanelLogo(viewMenu));
        updatePanelMain();
    }
    
    /**
     * Method responsible for returning the Component Index.
     * @param  component Component.
     * @return Component Index.
     */
    public Integer getIndex(Component component) {
        for (int i = 0; i < panelTabbed.getComponents().length; i++) {
            if (panelTabbed.getComponents()[i].equals(component))
                return i - 1;
        }
        return -1;
    }
    
    /**
     * Method responsible for returning the Selected Panel.
     * @return Selected Panel.
     */
    public PanelGraph getSelectedPanel() {
        if (getPanelDiagram() != null)
            return getPanelDiagram();
        return getPanelInstance();
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @param  diagram Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanelDiagram(Diagram diagram) {
        return (PanelDiagram) panels.get(diagram.getId());
    }
    
    /**
     * Method responsible for returning Panel Diagram.
     * @return Panel Diagram.
     */
    public PanelDiagram getPanelDiagram() {
        Component component = panelTabbed.getSelectedComponent();
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
        return (PanelInstance) panels.get(instance.getCompleteId());
    }
    
    /**
     * Method responsible for returning the Panel Instance.
     * @return Panel Instance.
     */
    public PanelInstance getPanelInstance() {
        Component component = panelTabbed.getSelectedComponent();
        if (component instanceof PanelInstance)
            return (PanelInstance) component;
        return null;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return viewMenu;
    }
    
    /**
     * Method responsible for returning the Panel Tabbed.
     * @return Panel Tabbed.
     */
    public PanelTabbed getPanelTabbed() {
        return panelTabbed;
    }
    
    /**
     * Method responsible for returning the Map Panels.
     * @return Map Panels.
     */
    public Map<String, Component> getPanels() {
        return panels;
    }
}
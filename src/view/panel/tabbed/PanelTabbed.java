package view.panel.tabbed;

import java.awt.Component;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import view.panel.Panel;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>PanelTabbed</b>.</p>
 * <p>Class responsible for defining the <b>Tabbed Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    javax.swing.JTabbedPane
 */
public class PanelTabbed extends JTabbedPane {
    private final PanelModeling panel;
    private final HashMap tabs;

    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     */
    public PanelTabbed(PanelModeling panel) {
        super();
        this.panel = panel;
        this.tabs  = new HashMap();
    }

    @Override
    public void addTab(String title, Icon icon, Component component, String id) {
        super.addTab(title, icon, component, id);
        Panel tab = new PanelTabTitle(panel, component, title, icon);
        tabs.put(id, tab);
        setTabComponentAt(getTabCount() - 1, tab);
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        addTab(title, icon, component, null);
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
    }
    
    /**
     * Method responsible for updating the Diagram Tab.
     * @param diagram Diagram.
     */
    public void updateTab(Diagram diagram) {
        getTab(diagram.getId()).setTitle(diagram.getName());
    }
    
    /**
     * Method responsible for updating the Instance Tab.
     * @param instance Instance.
     */
    public void updateTab(Instance instance) {
        getTab(instance.getCompleteId()).setTitle(instance.getName());
    }
    
    /**
     * Method responsible for removing the Tab by Id.
     * @param id Tab Id.
     * @param component Tab Component.
     */
    public void remove(String id, Component component) {
        super.remove(component);
        tabs.remove(id);
    }
    
    /**
     * Method responsible for returning the Tab by Id.
     * @param  id Tab Id.
     * @return Tab found.
     */
    public PanelTabTitle getTab(String id) {
        return (PanelTabTitle) tabs.get(id);
    }
}
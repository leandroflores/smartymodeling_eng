package view.panel.tabbed;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>PanelTabbed</b>.</p>
 * <p>Class responsible for defining the <b>Tabbed Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    javax.swing.JTabbedPane
 */
public class PanelTabbed extends JTabbedPane {
    private final PanelModeling panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     */
    public PanelTabbed(PanelModeling panel) {
        super();
        this.panel = panel;
    }

    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        this.setTabComponentAt(this.getTabCount() - 1, new PanelTabTitle(this.panel, component, title, icon));
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        this.addTab(title, icon, component, null);
    }

    @Override
    public void addTab(String title, Component component) {
        this.addTab(title, null, component);
    }
}
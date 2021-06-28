package view.panel.dependency;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.structural.base.Element;
import view.panel.Panel;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseDependency</b>.</p> 
 * <p>Class responsible for defining the <b>Dependency Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    view.panel.Panel
 */
public final class PanelDependency extends Panel {
    private final ViewMenu view;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param element Element.
     */
    public PanelDependency(ViewMenu view, Element element) {
        this.view    = view;
        this.element = element;
        setSettings();
        addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        setLayout(new GridLayout(2, 0));
        setMinimumSize(new Dimension(150, 150));
        setPreferredSize(new Dimension(150, 150));
    }
    
    @Override
    protected void addComponents() {
        createTable("dependency");
        addColumns("dependency", new String[] {"Name", "Source", "Target", "Type"});
        setColumnsSize("dependency", new int[] {50, 200, 150, 50});
        add(getScrollDependencyTable());
        
        add(createButton("delete", "Delete", "delete.png"));
    }
    
    /**
     * Method responsible for returning the Dependency Table.
     * @return Dependency Table.
     */
    public JTable getDependencyTable() {
        return getTable("dependency");
    }
    
    /**
     * Method responsible for returning the Scroll Dependency Table.
     * @return Scroll Dependency Table.
     */
    public JScrollPane getScrollDependencyTable() {
        return getScrollPane("dependency");
    }
}
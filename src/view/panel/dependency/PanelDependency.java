package view.panel.dependency;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.structural.base.Element;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseDependency</b>.</p> 
 * <p>Class responsible for defining the <b>Dependency Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-30
 * @see    view.panel.Panel
 */
public final class PanelDependency extends Panel {
    private final ViewMenu viewMenu;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param element Element.
     */
    public PanelDependency(ViewMenu view, Element element) {
        this.viewMenu = view;
        this.element  = element;
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(2, 0));
        this.setMinimumSize(new Dimension(150, 150));
        this.setPreferredSize(new Dimension(150, 150));
    }
    
    @Override
    protected void addComponents() {
//        this.addLines(1);
        
        this.createTable("dependencyTable");
        
        String[] columns = {"Name", "Source", "Target", "Type"};
        this.addColumns("dependencyTable", columns);
        
        int[]    size    = {50, 200, 150, 50};
        this.setColumnsSize("dependencyTable",size);
        
        this.add(this.getScrollDependencyTable());
        
//        this.addLines(1);
        
        this.add(this.createButton("deleteButton", "Delete", "delete.png"));
    }
    
    /**
     * Method responsible for returning the Dependency Table.
     * @return Dependency Table.
     */
    public JTable getDependencyTable() {
        return this.getTable("dependencyTable");
    }
    
    /**
     * Method responsible for returning the Scroll Dependency Table.
     * @return Scroll Dependency Table.
     */
    public JScrollPane getScrollDependencyTable() {
        return this.getScrollPane("dependencyTable");
    }
}
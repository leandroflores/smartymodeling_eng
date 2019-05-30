package view.edit.panel.association;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.structural.base.Element;
import model.structural.base.Project;
import view.Panel;

/**
 * <p>Class of View <b>PanelBaseDiagram</b>.</p> 
 * <p>Class responsible for defining the <b>Dependency Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  30/05/2019
 * @see    view.Panel
 */
public final class PanelDependency extends Panel {
    private final Project project;
    private final Element element;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element Element.
     */
    public PanelDependency(Project project, Element element) {
        this.project = project;
        this.element = element;
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(2, 0));
        this.setPreferredSize(new Dimension(300, 100));
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
        return this.tables.get("dependencyTable");
    }
    
    /**
     * Method responsible for returning the Scroll Dependency Table.
     * @return Scroll Dependency Table.
     */
    public JScrollPane getScrollDependencyTable() {
        return this.scrollPanes.get("dependencyTable");
    }
}
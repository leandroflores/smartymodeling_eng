package view.panel.edit;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEdit</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Edit</b> the Elements of SMartyModeling.</p>
 * @author Leandro
 * @since  11/06/2019
 * @see    view.Panel
 */
public class PanelEdit extends Panel {
    protected final ViewMenu viewMenu;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelEdit(ViewMenu viewMenu) {
        this.viewMenu   = viewMenu;
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 200));
    }
    
    @Override
    protected void addComponents() {}
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
}
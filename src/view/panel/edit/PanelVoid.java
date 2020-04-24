package view.panel.edit;

import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelVoid</b>.</p> 
 * <p>Class responsible for defining a <b>Void Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-24
 * @see    view.panel.Panel
 */
public class PanelVoid extends PanelEdit {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelVoid(ViewMenu view) {
        super(view);
    }
    
    @Override
    protected void addPanels() {}
}
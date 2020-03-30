package view.query;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import view.ViewModal;
import view.panel.modeling.PanelModeling;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewQuery</b>.</p>
 * <p>Class responsible for defining the <b>Query View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2019
 * @see    controller.view.query.ControllerViewQuery
 * @see    view.ViewModal
 */
public abstract class ViewQuery extends ViewModal {
    protected final ViewMenu view;
    protected final PanelModeling panel;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewQuery(ViewMenu view) {
        super(view);
        this.view  = view;
        this.panel = null;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param panel Panel Modeling.
     */
    public ViewQuery(PanelModeling panel) {
        super(panel.getViewMenu());
        this.view  = panel.getViewMenu();
        this.panel = panel;
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("refreshButton", " Refresh ", "refresh"));
        this.add(this.createButton("clearButton",   "  Clear  ", "clear"));
        this.add(this.createButton("cancelButton",  "  Cancel ", "back"));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.view;
    }
    
    /**
     * Method responsible for returning the Refresh Button.
     * @return Refresh Button.
     */
    public JButton getRefreshButton() {
        return this.buttons.get("refreshButton");
    }
    
    /**
     * Method responsible for returning the Clear Button.
     * @return Clear Button.
     */
    public JButton getClearButton() {
        return this.buttons.get("clearButton");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return this.buttons.get("cancelButton");
    }
}
package view.panel.edit.base.product;

import java.awt.Dimension;
import model.structural.base.product.Instance;
import view.panel.base.product.PanelBaseInstance;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditInstance</b>.</p> 
 * <p>Class responsible for defining a <b>Instance Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-25
 * @see    model.structural.base.product.Instance
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditInstance extends PanelEdit {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     */
    public PanelEditInstance(ViewMenu view, Instance instance) {
        super(view);
        this.instance = instance;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseInstance();
    }
    
    /**
     * Method responsible for adding the Panel Base Instance.
     */
    private void addPanelBaseInstance() {
        this.addPanel("panelBaseInstance", new PanelBaseInstance(this.viewMenu, this.instance));
        this.createScrollPane("scrollPanelBaseInstance",  this.getPanelBaseInstance());
        this.getScrollPane("scrollPanelBaseInstance").setViewportView(this.getPanelBaseInstance());
        this.tabbedPane.add("Instance", this.getScrollPane("scrollPanelBaseInstance"));
    }
    
    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return (PanelBaseInstance) this.getPanel("panelBaseInstance");
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
}
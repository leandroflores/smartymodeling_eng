package view.edit.base.product;

import controller.view.edit.base.product.ControllerViewEditInstance;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.product.Instance;
import view.edit.ViewEdit;
import view.panel.base.product.PanelBaseInstance;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditInstance</b>.</p>
 * <p>Class responsible for defining the <b>Instance Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.edit.base.product.ControllerViewEditInstance
 * @see    model.structural.base.product.Instance
 * @see    view.edit.ViewEdit
 */
public final class ViewEditInstance extends ViewEdit {
    private final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param instance Instance.
     */
    public ViewEditInstance(PanelModeling panel, Instance instance) {
        super(panel);
        this.instance    = instance;
        this.controller = new ControllerViewEditInstance(this);
        this.title      = "Edit Instance Data";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 350);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 225));
            this.addPanelBaseInstance();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Instance.
     */
    private void addPanelBaseInstance() {
        this.addPanel("panelBaseInstance", new PanelBaseInstance(this.getViewMenu(), this.instance));
        this.createScrollPane("scrollPanelBaseInstance",  this.getPanelBaseInstance());
        this.getScrollPanelBaseInstance().setViewportView(this.getPanelBaseInstance());
        this.tabbedPane.add("Instance", this.getScrollPanelBaseInstance());
    }
    
    /**
     * Method responsible for returning the Panel Base Instance.
     * @return Panel Base Instance.
     */
    public PanelBaseInstance getPanelBaseInstance() {
        return (PanelBaseInstance) this.getPanel("panelBaseInstance");
    }
    
    /**
     * Method responsible for returning Panel Base Instance.
     * @return Panel Base Instance.
     */
    public JScrollPane getScrollPanelBaseInstance() {
        return this.getScrollPane("scrollPanelBaseInstance");
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
}
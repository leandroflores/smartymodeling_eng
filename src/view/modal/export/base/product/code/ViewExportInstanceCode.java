package view.modal.export.base.product.code;

import controller.view.modal.export.base.product.code.ControllerViewExportInstanceCode;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.product.Instance;
import view.modal.export.ViewExport;
import view.panel.export.base.product.code.PanelExportInstanceCode;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportInstanceCode</b>.</p>
 * <p>Class responsible for defining the <b>Export Instance Code View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-29
 * @see    controller.view.modal.export.base.product.code.ControllerViewExportInstanceCode
 * @see    model.structural.base.product.Instance
 * @see    view.modal.export.ViewExport
 * @see    view.panel.export.base.product.code.PanelExportInstanceCode
 */
public final class ViewExportInstanceCode extends ViewExport {
    private Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewExportInstanceCode(ViewMenu view) {
        super(view);
        this.controller = new ControllerViewExportInstanceCode(this);
        this.title      = "Export Instance Code";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 420);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 300));
            this.addPanelExportInstanceCode();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Export Instance Code.
     */
    private void addPanelExportInstanceCode() {
        this.addPanel("panelExportInstanceCode", new PanelExportInstanceCode(this.getViewMenu()));
        this.createScrollPane("scrollPanelExportInstanceCode",  this.getPanelExportInstanceCode());
        this.getScrollPane("scrollPanelExportInstanceCode").setViewportView(this.getPanelExportInstanceCode());
        this.tabbedPane.add("Export Instance Code", this.getScrollPane("scrollPanelExportInstanceCode"));
    }
    
    /**
     * Method responsible for returning the Panel Export Instance Code.
     * @return Panel Export Instance Code.
     */
    public PanelExportInstanceCode getPanelExportInstanceCode() {
        return (PanelExportInstanceCode) this.getPanel("panelExportInstanceCode");
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }

    /**
     * Method responsible for setting the Instance.
     * @param instance Instance.
     */
    public void setInstance(Instance instance) {
        this.instance = instance;
    }
}
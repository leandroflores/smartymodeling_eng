package view.export.code;

import controller.view.export.code.ControllerViewExportInstanceCode;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.product.Instance;
import view.export.ViewExport;
import view.panel.export.code.PanelExportInstanceCode;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExportInstanceCode</b>.</p>
 * <p>Class responsible for defining the <b>Export Instance Code View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/01/2020
 * @see    controller.view.export.code.ControllerViewExportInstanceCode
 * @see    model.structural.base.product.Instance
 * @see    view.export.ViewExport
 * @see    view.panel.export.code.PanelExportInstanceCode
 */
public final class ViewExportInstanceCode extends ViewExport {
    private Instance instance;
    private PanelExportInstanceCode panelExportInstanceCode;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ViewExportInstanceCode(ViewMenu viewMenu) {
        super(viewMenu);
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
        this.panelExportInstanceCode = new PanelExportInstanceCode(this.getViewMenu());
        this.createScrollPane("scrollPanelExportInstanceCode",  this.panelExportInstanceCode);
        this.getScrollPanelExportInstanceCode().setViewportView(this.panelExportInstanceCode);
        this.tabbedPane.add("Export Instance Code", this.getScrollPanelExportInstanceCode());
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
    
    /**
     * Method responsible for returning the Panel Export Instance Code.
     * @return Panel Export Instance Code.
     */
    public PanelExportInstanceCode getPanelExportInstanceCode() {
        return this.panelExportInstanceCode;
    }
    
    /**
     * Method responsible for returning the Panel Export Instance Code.
     * @return Panel Export Instance Code.
     */
    public JScrollPane getScrollPanelExportInstanceCode() {
        return this.getScrollPane("scrollPanelExportInstanceCode");
    }
}
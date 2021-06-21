package view.modal.export;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import view.modal.ViewModal;
import view.main.structural.ViewMenu;
import view.panel.export.PanelExport;

/**
 * <p>Class of View <b>ViewExport</b>.</p>
 * <p>Class responsible for defining the <b>Export View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-05
 * @see    controller.view.export.ControllerViewExport
 * @see    view.modal.ViewModal
 */
public abstract class ViewExport extends ViewModal {
    protected final ViewMenu view;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewExport(ViewMenu view) {
        super(view);
        this.view = view;
    }
    
    @Override
    public void initComponents() {
        setSize(new Dimension(600, 420));
        addHeader();
        addComponents();
        addFooter();
    }
    
    @Override
    public void addComponents() {
        addTabbedPane();
        addPanelExport();
        addLines(1);
    }
    
    /**
     * Method responsible for adding the Export Tabbed Pane.
     */
    protected void addTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(550, 300));
        add(tabbedPane);
    }
    
    /**
     * Method responsible for adding the Export Panel.
     */
    protected void addPanelExport() {
        addPanel("export", createPanelExport());
        createScrollPane("export", getPanelExport());
        getScrollPane("export").setViewportView(getPanelExport());
        tabbedPane.removeAll();
        tabbedPane.add(title, getScrollPane("export"));
    }
        
    /**
     * Method responsible for creating the Export Panel.
     * @return Export Panel.
     */
    protected abstract PanelExport createPanelExport();
    
    @Override
    public void addFooter() {
        add(createButton("export", " Export ", "export"));
        add(createButton("cancel", " Cancel ", "cancel"));
    }
    
    /**
     * Method responsible for creating the Panel Export.
     * @return Panel Export.
     */
    public PanelExport getPanelExport() {
        return (PanelExport) getPanel("export");
    }
    
    /**
     * Method responsible for returning the Export Button.
     * @return Export Button.
     */
    public JButton getExportButton() {
        return getButton("export");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return getButton("cancel");
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getView() {
        return view;
    }
}
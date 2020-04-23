package view.modal.export;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import view.modal.ViewModal;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExport</b>.</p>
 * <p>Class responsible for defining the <b>Export View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-05
 * @see    controller.view.export.ControllerViewExport
 * @see    view.modal.ViewModal
 */
public abstract class ViewExport extends ViewModal {
    protected final ViewMenu viewMenu;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewExport(ViewMenu view) {
        super(view);
        this.viewMenu = view;
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("exportButton", " Export ", "export"));
        this.add(this.createButton("cancelButton", " Cancel ", "cancel"));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Export Button.
     * @return Export Button.
     */
    public JButton getExportButton() {
        return this.getButton("exportButton");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return this.getButton("cancelButton");
    }
}
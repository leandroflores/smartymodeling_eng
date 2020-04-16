package view.export;

import javax.swing.JButton;
import javax.swing.JTabbedPane;
import view.ViewModal;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewExport</b>.</p>
 * <p>Class responsible for defining the <b>Export View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/11/2019
 * @see    controller.view.export.ControllerViewExport
 * @see    view.ViewModal
 */
public abstract class ViewExport extends ViewModal {
    protected final ViewMenu viewMenu;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ViewExport(ViewMenu viewMenu) {
        super(viewMenu);
        this.viewMenu = viewMenu;
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
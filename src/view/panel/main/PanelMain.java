package view.panel.main;

import controller.view.panel.main.ControllerPanelMain;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Iterator;
import javax.swing.JButton;
import view.panel.Panel;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelMain</b>.</p> 
 * <p>Class responsible for defining the <b>Main Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    controller.view.panel.main.ControllerPanelMain
 * @see    view.panel.Panel
 * @see    view.main.structural.ViewMenu
 */
public final class PanelMain extends Panel {
    private final ViewMenu viewMenu;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelMain(ViewMenu view) {
        viewMenu   = view;
        controller = new ControllerPanelMain(this);
        setLayout(new GridLayout(0, 18));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        setMinimumSize(new Dimension(0, 35));
        addComponents();
    }
    
    @Override
    protected void addComponents() {
        addProjectButtons();
        addActionButtons();
        addZoomButtons();
        addExportButtons();
        addExtraButtons();
    }

    /**
     * Method responsible for returning Image Path.
     * @param  url Image URL.
     * @return Image Path.
     */
    private String getPath(String url) {
        return "main/" + url + ".png";
    }
    
    /**
     * Method responsible for adding Project Buttons.
     */
    private void addProjectButtons() {
        add(createLabel(""));
        add(createButton("new_project",   "", "New Project",   getPath("new-project")));
        add(createButton("open_project",  "", "Open Project",  getPath("open-project")));
        add(createButton("save_project",  "", "Save Project",  getPath("save-project")));
        add(createButton("close_project", "", "Close Project", getPath("close-project")));
    }
    
    /**
     * Method responsible for adding Action Buttons.
     */
    private void addActionButtons() {
        add(createLabel(""));
        add(createButton("undo", "", "Undo", getPath("undo")));
        add(createButton("redo", "", "Redo", getPath("redo")));
    }
    
    /**
     * Method responsible for adding Zoom Buttons.
     */
    private void addZoomButtons() {
        add(createLabel(""));
        add(createButton("zoom_100", "", "Zoom 100%", getPath("zoom-original")));
        add(createButton("zoom_in",  "", "Zoom +",    getPath("zoom-in")));
        add(createButton("zoom_out", "", "Zoom -",    getPath("zoom-out")));
    }
    
    /**
     * Method responsible for adding Export Buttons.
     */
    private void addExportButtons() {
        add(createLabel(""));
        add(createButton("export_image", "", "Export Image", getPath("export-image")));
        add(createButton("export_pdf",   "", "Export PDF",   getPath("export-pdf")));
    }
    
    /**
     * Method responsible for adding Extra Buttons.
     */
    private void addExtraButtons() {
        add(createLabel(""));
//        add(createButton("help",    "", "Help",    getPath("help")));
        add(createButton("version", "", "Version", getPath("version")));
        add(createLabel(""));
    }
    
    /**
     * Method responsible for activating Panel.
     */
    public void activate() {
        Iterator<JButton> iterator = getButtons().values().iterator();
        while (iterator.hasNext())
               iterator.next().setEnabled(true);
    }
    
    /**
     * Method responsible for setting No Project properties.
     */
    public void setNoProject() {
        viewMenu.getMenuItemSaveProject().setEnabled(false);
        viewMenu.getMenuItemSaveAs().setEnabled(false);
        viewMenu.getMenuItemCloseProject().setEnabled(false);
        
        getSaveProjectButton().setEnabled(false);
        getCloseProjectButton().setEnabled(false);
        getRedoButton().setEnabled(false);
        getUndoButton().setEnabled(false);
        getOriginalZoomButton().setEnabled(false);
        getZoomInButton().setEnabled(false);
        getZoomOutButton().setEnabled(false);
        getExportImageButton().setEnabled(false);
        getExportPdfButton().setEnabled(false);
    }
    
    /**
     * Method responsible for setting the Modeling Buttons.
     * @param enabled Enabled Flag.
     */
    public void setModeling(boolean enabled) {
        getOriginalZoomButton().setEnabled(enabled);
        getZoomInButton().setEnabled(enabled);
        getZoomOutButton().setEnabled(enabled);
        getExportImageButton().setEnabled(enabled);
        getExportPdfButton().setEnabled(enabled);
    }
    
    /**
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return viewMenu;
    }
    
    /**
     * Method responsible for returning New Project Button.
     * @return New Project Button.
     */
    public JButton getNewProjectButton() {
        return getButton("new_project");
    }
    
    /**
     * Method responsible for returning Open Project Button.
     * @return Open Project Button.
     */
    public JButton getOpenProjectButton() {
        return getButton("open_project");
    }
    
    /**
     * Method responsible for returning Save Project Button.
     * @return Save Project Button.
     */
    public JButton getSaveProjectButton() {
        return getButton("save_project");
    }
    
    /**
     * Method responsible for returning Close Project Button.
     * @return Close Project Button.
     */
    public JButton getCloseProjectButton() {
        return getButton("close_project");
    }
    
    /**
     * Method responsible for returning Undo Button.
     * @return Undo Button.
     */
    public JButton getUndoButton() {
        return getButton("undo");
    }
    
    /**
     * Method responsible for returning Redo Button.
     * @return Redo Button.
     */
    public JButton getRedoButton() {
        return getButton("redo");
    }
    
    /**
     * Method responsible for returning Original Zoom Button.
     * @return Original Zoom Button.
     */
    public JButton getOriginalZoomButton() {
        return getButton("zoom_100");
    }
    
    /**
     * Method responsible for returning Zoom In Button.
     * @return Zoom In Button.
     */
    public JButton getZoomInButton() {
        return getButton("zoom_in");
    }
    
    /**
     * Method responsible for returning Zoom Out Button.
     * @return Zoom Out Button.
     */
    public JButton getZoomOutButton() {
        return getButton("zoom_out");
    }
    
    /**
     * Method responsible for returning Export Image Button.
     * @return Export Image Button.
     */
    public JButton getExportImageButton() {
        return getButton("export_image");
    }
    
    /**
     * Method responsible for returning Export Pdf Button.
     * @return Export Pdf Button.
     */
    public JButton getExportPdfButton() {
        return getButton("export_pdf");
    }
    
    /**
     * Method responsible for returning Help Button.
     * @return Help Button.
     */
    public JButton getHelpButton() {
        return getButton("help");
    }
    
    /**
     * Method responsible for returning Version Button.
     * @return Version Button.
     */
    public JButton getVersionButton() {
        return getButton("version");
    }
}
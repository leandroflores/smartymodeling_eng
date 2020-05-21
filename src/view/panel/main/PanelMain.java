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
     * @param viewMenu View Menu.
     */
    public PanelMain(ViewMenu viewMenu) {
        this.viewMenu   = viewMenu;
        this.controller = new ControllerPanelMain(this);
        this.setLayout(new GridLayout(0, 18));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        this.setMinimumSize(new Dimension(0, 35));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.addProjectButtons();
        this.addActionButtons();
        this.addZoomButtons();
        this.addExportButtons();
        this.addExtraButtons();
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
        this.add(this.createLabel(""));
        this.add(this.createButton("newProjectButton",   "", "New Project",   this.getPath("new-project")));
        this.add(this.createButton("openProjectButton",  "", "Open Project",  this.getPath("open-project")));
        this.add(this.createButton("saveProjectButton",  "", "Save Project",  this.getPath("save-project")));
        this.add(this.createButton("closeProjectButton", "", "Close Project", this.getPath("close-project")));
    }
    
    /**
     * Method responsible for adding Action Buttons.
     */
    private void addActionButtons() {
        this.add(this.createLabel(""));
        this.add(this.createButton("undoButton", "", "Undo", this.getPath("undo")));
        this.add(this.createButton("redoButton", "", "Redo", this.getPath("redo")));
    }
    
    /**
     * Method responsible for adding Zoom Buttons.
     */
    private void addZoomButtons() {
        this.add(this.createLabel(""));
        this.add(this.createButton("originalZoomButton", "", "Zoom 100%", this.getPath("zoom-original")));
        this.add(this.createButton("zoomInButton",       "", "Zoom +",    this.getPath("zoom-in")));
        this.add(this.createButton("zoomOutButton",      "", "Zoom -",    this.getPath("zoom-out")));
    }
    
    /**
     * Method responsible for adding Export Buttons.
     */
    private void addExportButtons() {
        this.add(this.createLabel(""));
        this.add(this.createButton("exportImageButton", "", "Export Image", this.getPath("export-image")));
        this.add(this.createButton("exportPdfButton",   "", "Export PDF",   this.getPath("export-pdf")));
    }
    
    /**
     * Method responsible for adding Extra Buttons.
     */
    private void addExtraButtons() {
        this.add(this.createLabel(""));
//        this.add(this.createButton("helpButton",    "", "Help",    this.getPath("help")));
        this.add(this.createButton("versionButton", "", "Version", this.getPath("version")));
        this.add(this.createLabel(""));
    }
    
    /**
     * Method responsible for activating Panel.
     */
    public void activate() {
        Iterator<JButton> iterator = this.getButtons().values().iterator();
        while (iterator.hasNext())
               iterator.next().setEnabled(true);
    }
    
    /**
     * Method responsible for setting No Project properties.
     */
    public void setNoProject() {
        this.viewMenu.getMenuItemSaveProject().setEnabled(false);
        this.viewMenu.getMenuItemSaveAs().setEnabled(false);
        this.viewMenu.getMenuItemCloseProject().setEnabled(false);
        
        this.getSaveProjectButton().setEnabled(false);
        this.getCloseProjectButton().setEnabled(false);
        this.getRedoButton().setEnabled(false);
        this.getUndoButton().setEnabled(false);
        this.getOriginalZoomButton().setEnabled(false);
        this.getZoomInButton().setEnabled(false);
        this.getZoomOutButton().setEnabled(false);
        this.getExportImageButton().setEnabled(false);
        this.getExportPdfButton().setEnabled(false);
    }
    
    /**
     * Method responsible for setting the Modeling Buttons.
     * @param enabled Enabled Flag.
     */
    public void setModeling(boolean enabled) {
        this.getOriginalZoomButton().setEnabled(enabled);
        this.getZoomInButton().setEnabled(enabled);
        this.getZoomOutButton().setEnabled(enabled);
        this.getExportImageButton().setEnabled(enabled);
        this.getExportPdfButton().setEnabled(enabled);
    }
    
    /**
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning New Project Button.
     * @return New Project Button.
     */
    public JButton getNewProjectButton() {
        return this.getButton("newProjectButton");
    }
    
    /**
     * Method responsible for returning Open Project Button.
     * @return Open Project Button.
     */
    public JButton getOpenProjectButton() {
        return this.getButton("openProjectButton");
    }
    
    /**
     * Method responsible for returning Save Project Button.
     * @return Save Project Button.
     */
    public JButton getSaveProjectButton() {
        return this.getButton("saveProjectButton");
    }
    
    /**
     * Method responsible for returning Close Project Button.
     * @return Close Project Button.
     */
    public JButton getCloseProjectButton() {
        return this.getButton("closeProjectButton");
    }
    
    /**
     * Method responsible for returning Undo Button.
     * @return Undo Button.
     */
    public JButton getUndoButton() {
        return this.getButton("undoButton");
    }
    
    /**
     * Method responsible for returning Redo Button.
     * @return Redo Button.
     */
    public JButton getRedoButton() {
        return this.getButton("redoButton");
    }
    
    /**
     * Method responsible for returning Original Zoom Button.
     * @return Original Zoom Button.
     */
    public JButton getOriginalZoomButton() {
        return this.getButton("originalZoomButton");
    }
    
    /**
     * Method responsible for returning Zoom In Button.
     * @return Zoom In Button.
     */
    public JButton getZoomInButton() {
        return this.getButton("zoomInButton");
    }
    
    /**
     * Method responsible for returning Zoom Out Button.
     * @return Zoom Out Button.
     */
    public JButton getZoomOutButton() {
        return this.getButton("zoomOutButton");
    }
    
    /**
     * Method responsible for returning Export Image Button.
     * @return Export Image Button.
     */
    public JButton getExportImageButton() {
        return this.getButton("exportImageButton");
    }
    
    /**
     * Method responsible for returning Export Pdf Button.
     * @return Export Pdf Button.
     */
    public JButton getExportPdfButton() {
        return this.getButton("exportPdfButton");
    }
    
    /**
     * Method responsible for returning Help Button.
     * @return Help Button.
     */
    public JButton getHelpButton() {
        return this.getButton("helpButton");
    }
    
    /**
     * Method responsible for returning Version Button.
     * @return Version Button.
     */
    public JButton getVersionButton() {
        return this.getButton("versionButton");
    }
}
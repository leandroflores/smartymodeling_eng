package view.panel.main;

import java.util.Iterator;
import javax.swing.JButton;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of Panel <b>PanelMain</b>.</p> 
 * <p>Class responsible for defining the <b>Main Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    view.Panel
 * @see    view.structural.ViewMenu
 */
public class PanelMain extends Panel {
    private final ViewMenu viewMenu;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelMain(ViewMenu viewMenu) {
        this.viewMenu   = viewMenu;
//        this.controller = new ControllerPainelPrincipal(this);
        
    }
    
    @Override
    protected void addComponents() {
        this.addProjectButtons();
        this.addActionButtons();
        this.addZoomButtons();
        this.addExtraButtons();
    }

    /**
     * Method responsible for returning Image Path.
     * @param  url Image URL.
     * @return Image Path.
     */
    private String getPath(String url) {
        return "/main/" + url + ".png";
    }
    
    /**
     * Method responsible for adding Project Buttons.
     */
    private void addProjectButtons() {
        this.add(this.createButton("newProjectButton",   "", "New Project",   this.getPath("new-project")));
        this.add(this.createButton("openProjectButton",  "", "Open Project",  this.getPath("open-project")));
        this.add(this.createButton("saveProjectButton",  "", "Save Project",  this.getPath("save-project")));
        this.add(this.createButton("closeProjectButton", "", "Close Project", this.getPath("close-project")));
        this.add(this.createLabel("", 15));
    }
    
    /**
     * Method responsible for adding Action Buttons.
     */
    private void addActionButtons() {
        this.add(this.createButton("undoButton", "", "Undo", this.getPath("undo")));
        this.add(this.createButton("redoButton", "", "Redo", this.getPath("redo")));
        this.add(this.createLabel("", 15));
    }
    
    /**
     * Method responsible for adding Zoom Buttons.
     */
    private void addZoomButtons() {
        this.add(this.createButton("zoomOriginalButton", "", "Zoom 100%", this.getPath("zoom-original")));
        this.add(this.createButton("zoomInButton",       "", "Zoom +",    this.getPath("zoom-in")));
        this.add(this.createButton("zoomOutButton",      "", "Zoom -",    this.getPath("zoom-out")));
        this.add(this.createLabel("", 15));
    }
    
    /**
     * Method responsible for adding Extra Buttons.
     */
    private void addExtraButtons() {
        this.add(this.createButton("exportImageButton", "", "Export Image", this.getPath("export-image")));
        this.add(this.createLabel("", 15));
        this.add(this.createButton("helpButton",        "", "Help",         this.getPath("help")));
        this.add(this.createButton("versionButton",     "", "Version",      this.getPath("version")));
    }
    
    /**
     * Method responsible for activating Panel.
     */
    public void activate() {
        Iterator<JButton> iterator = this.buttons.values().iterator();
        while (iterator.hasNext())
            iterator.next().setEnabled(true);
    }
    
    /**
     * Method responsible for setting No Project properties.
     */
    public void setNoProject() {
        this.viewMenu.getMenuItemSaveProject().setEnabled(false);
        this.viewMenu.getMenuItemCloseProject().setEnabled(false);
        
        this.getSaveProjectButton().setEnabled(false);
        this.getCloseProjectButton().setEnabled(false);
        this.getRedoButton().setEnabled(false);
        this.getUndoButton().setEnabled(false);
        this.getButtonZoomOriginal().setEnabled(false);
        this.getButtonZoomIn().setEnabled(false);
        this.getButtonZoomOut().setEnabled(false);
        this.getButtonExportarImagem().setEnabled(false);
    }
    
    /**
     * Method responsible for returning View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning New Project JButton.
     * @return New Project JButton.
     */
    public JButton getNewProjectButton() {
        return this.buttons.get("newProjectButton");
    }
    
    /**
     * Method responsible for returning JButton Open Project.
     * @return JButton Open Project.
     */
    public JButton getOpenProjectButton() {
        return this.buttons.get("openProjectButton");
    }
    
    /**
     * Method responsible for returning Save Project JButton.
     * @return Save Project JButton.
     */
    public JButton getSaveProjectButton() {
        return this.buttons.get("saveProjectButton");
    }
    
    /**
     * Method responsible for returning Close Project JButton.
     * @return Close Project JButton.
     */
    public JButton getCloseProjectButton() {
        return this.buttons.get("closeProjectButton");
    }
    
    /**
     * Method responsible for returning Undo JButton.
     * @return Undo JButton.
     */
    public JButton getUndoButton() {
        return this.buttons.get("undoButton");
    }
    
    /**
     * Method responsible for returning Redo JButton.
     * @return Redo JButton.
     */
    public JButton getRedoButton() {
        return this.buttons.get("buttonRefazer");
    }
    
    /**
     * Metodo responsavel por retornar o JButton Zoom Original.
     * @return JButton Zoom Original.
     */
    public JButton getButtonZoomOriginal() {
        return this.buttons.get("buttonZoomOriginal");
    }
    
    /**
     * Metodo responsavel por retornar o JButton Zoom In.
     * @return JButton Zoom In.
     */
    public JButton getButtonZoomIn() {
        return this.buttons.get("buttonZoomIn");
    }
    
    /**
     * Metodo responsavel por retornar o JButton Zoom Out.
     * @return JButton Zoom Out.
     */
    public JButton getButtonZoomOut() {
        return this.buttons.get("buttonZoomOut");
    }
    
    /**
     * Metodo responsavel por retornar o JButton Exportar Imagem.
     * @return JButton Exportar Imagem.
     */
    public JButton getButtonExportarImagem() {
        return this.buttons.get("buttonExportarImagem");
    }
    
    /**
     * Metodo responsavel por retornar o JButton Ajuda.
     * @return JButton Ajuda.
     */
    public JButton getButtonAjuda() {
        return this.buttons.get("buttonAjuda");
    }
    
    /**
     * Metodo responsavel por retornar o JButton Informacoes.
     * @return JButton Informacoes.
     */
    public JButton getButtonInformacoes() {
        return this.buttons.get("buttonInformacoes");
    }
}
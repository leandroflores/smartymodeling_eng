package controller.view.panel.export;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.export.PanelExport;

/**
 * <p>Class of Controller <b>ControllerPanelExportDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelExportDiagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    controller.view.ControllerPanel
 * @see    view.panel.export.PanelExport
 */
public abstract class ControllerPanelExport extends ControllerPanel {
    private final PanelExport panelExport;

    /**
     * Default constructor method of Class.
     * @param panel Panel Export.
     */
    public ControllerPanelExport(PanelExport panel) {
        super(panel);
        this.panelExport = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelExport.getSearchDirectoryButton().equals(event.getSource()))
            this.updateDirectoryPath();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for updating the Directory Path.
     */
    protected void updateDirectoryPath() {
        if (this.panelExport.getSearchDirectoryChooser().showSaveDialog(this.panelExport.getViewMenu()) != 1) {
            String path = this.panelExport.getSearchDirectoryChooser().getSelectedFile().getAbsolutePath();
            if (!path.equals(""))
                this.panelExport.getDirectoryTextField().setText(path);
        }
    }
}
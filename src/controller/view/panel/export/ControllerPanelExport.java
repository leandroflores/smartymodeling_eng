package controller.view.panel.export;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.panel.export.PanelExport;

/**
 * <p>Class of Controller <b>ControllerPanelExport</b>.</p>
 * <p>Class responsible for controlling the <b>Export Panel</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.export.PanelExport
 */
public abstract class ControllerPanelExport extends ControllerPanel {

    /**
     * Default constructor method of Class.
     * @param panel Panel Export.
     */
    public ControllerPanelExport(PanelExport panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getPanel().getSearchDirectoryButton().equals(event.getSource()))
            updateDirectoryPath();
        else if (getPanel().getContextComboBox().equals(event.getSource()))
            update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for updating the Directory Path.
     */
    protected void updateDirectoryPath() {
        if (getPanel().getSearchDirectoryChooser().showSaveDialog(getPanel().getViewMenu()) != 1) {
            String path = getPanel().getSearchDirectoryChooser().getSelectedFile().getAbsolutePath();
            if (!path.equals(""))
                getPanel().getDirectoryTextField().setText(path);
        }
    }
    
    /**
     * Method responsible for updating the Panel Export.
     */
    protected abstract void update();
    
    @Override
    public PanelExport getPanel() {
        return (PanelExport) panel;
    }
}
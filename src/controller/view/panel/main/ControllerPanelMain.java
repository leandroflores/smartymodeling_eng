package controller.view.panel.main;

import com.itextpdf.text.DocumentException;
import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import view.message.ViewError;
import view.panel.main.PanelMain;
import view.system.ViewSystemInformation;

/**
 * <p>Class of Controller <b>ControllerPanelMain</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Main Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.ControllerPanel
 * @see    view.panel.main.PanelMain
 */
public class ControllerPanelMain extends ControllerPanel {
    private final PanelMain panelMain;

    /**
     * Default constructor method of Class.
     * @param panel Panel Main.
     */
    public ControllerPanelMain(PanelMain panel) {
        super(panel);
        this.panelMain = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelMain.getNewProjectButton().equals(event.getSource()))
            this.panelMain.getViewMenu().getController().showNewProject();
        else if (this.panelMain.getOpenProjectButton().equals(event.getSource()))
            this.panelMain.getViewMenu().getController().showOpenProject();
        else if (this.panelMain.getSaveProjectButton().equals(event.getSource()))
            this.panelMain.getViewMenu().getController().saveProject();
        else if (this.panelMain.getCloseProjectButton().equals(event.getSource()))
            this.panelMain.getViewMenu().getController().showCloseProject();
        else if (this.panelMain.getOriginalZoomButton().equals(event.getSource()))
            this.panelMain.getViewMenu().setOriginalZoom();
        else if (this.panelMain.getZoomInButton().equals(event.getSource()))
            this.panelMain.getViewMenu().zoomIn();
        else if (this.panelMain.getZoomOutButton().equals(event.getSource()))
            this.panelMain.getViewMenu().zoomOut();
        else if (this.panelMain.getExportImageButton().equals(event.getSource()))
            this.exportImage();
        else if (this.panelMain.getVersionButton().equals(event.getSource()))
            new ViewSystemInformation(this.panelMain.getViewMenu()).setVisible(true);
    }
    
    /**
     * Method responsible for exporting the Image.
     */
    public void exportImage() {
        try {
            this.panelMain.getViewMenu().getController().exportImage();
        } catch (DocumentException | MalformedURLException exception) {
            new ViewError(this.panelMain.getViewMenu(), "Error to Export Image!").setVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {}
}
package controller.view.panel.main;

import com.itextpdf.text.DocumentException;
import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import view.modal.message.ViewError;
import view.panel.main.PanelMain;
import view.modal.system.ViewSystemInformation;

/**
 * <p>Class of Controller <b>ControllerPanelMain</b>.</p>
 * <p>Class responsible for controlling the <b>PanelMain</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-28
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.main.PanelMain
 */
public class ControllerPanelMain extends ControllerPanel {

    /**
     * Default constructor method of Class.
     * @param panel Panel Main.
     */
    public ControllerPanelMain(PanelMain panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getPanel().getNewProjectButton().equals(event.getSource()))
            getPanel().getViewMenu().getController().showNewProject();
        else if (getPanel().getOpenProjectButton().equals(event.getSource()))
            getPanel().getViewMenu().getController().showOpenProject();
        else if (getPanel().getSaveProjectButton().equals(event.getSource()))
            getPanel().getViewMenu().getController().saveProject();
        else if (getPanel().getCloseProjectButton().equals(event.getSource()))
            getPanel().getViewMenu().getController().showCloseProject();
        else if (getPanel().getExportImageButton().equals(event.getSource()))
            this.exportImage();
        else if (getPanel().getExportPdfButton().equals(event.getSource()))
            this.exportPdf();
        else if (getPanel().getVersionButton().equals(event.getSource()))
            new ViewSystemInformation(getPanel().getViewMenu()).setVisible(true);
        else
            this.actionZoom(event);
    }
    
    /**
     * Method responsible for Forward the File Menu.
     * @param event Action Event.
     */
    private void actionZoom(ActionEvent event) {
        if (getPanel().getViewMenu().getPanelModeling().getSelectedPanel() != null) {
            if (getPanel().getOriginalZoomButton().equals(event.getSource()))
                getPanel().getViewMenu().setOriginalZoom();
            else if (getPanel().getZoomInButton().equals(event.getSource()))
                getPanel().getViewMenu().zoomIn();
            else if (getPanel().getZoomOutButton().equals(event.getSource()))
                getPanel().getViewMenu().zoomOut();
        }
    }
    
    /**
     * Method responsible for exporting the Image.
     */
    public void exportImage() {
        try {
            getPanel().getViewMenu().getController().exportImage();
        } catch (DocumentException | MalformedURLException exception) {
            new ViewError(getPanel().getViewMenu(), "Error to Export Image!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for exporting the Pdf.
     */
    public void exportPdf() {
        try {
            getPanel().getViewMenu().getController().exportPdfImage();
        } catch (DocumentException exception) {
            new ViewError(getPanel().getViewMenu(), "Error to Export Pdf!").setVisible(true);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    @Override
    public PanelMain getPanel() {
        return (PanelMain) panel;
    }
}
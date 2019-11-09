package controller.view.panel.export;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import view.panel.export.PanelExportDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelExportDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelExportDiagram</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.PanelExportDiagram
 */
public class ControllerPanelExportDiagram extends ControllerPanelExport {
    private final PanelExportDiagram panelExportDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Diagram.
     */
    public ControllerPanelExportDiagram(PanelExportDiagram panel) {
        super(panel);
        this.panelExportDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelExportDiagram.getDiagramComboBox().equals(event.getSource()))
            this.updateDiagram();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for updating the Diagram.
     */
    private void updateDiagram() {
        if (this.panelExportDiagram.getDiagramComboBox().getSelectedItem() != null)
            this.panelExportDiagram.setDiagram((Diagram) this.panelExportDiagram.getDiagramComboBox().getSelectedItem());
    }
}
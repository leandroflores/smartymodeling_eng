package controller.view.panel.export.code;

import controller.view.panel.export.ControllerPanelExport;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import view.panel.export.code.PanelExportDiagramCode;

/**
 * <p>Class of Controller <b>ControllerPanelExportDiagramCode</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelExportDiagramCode</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  19/01/2020
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.code.PanelExportDiagramCode
 */
public class ControllerPanelExportDiagramCode extends ControllerPanelExport {
    private final PanelExportDiagramCode panelExportDiagramCode;

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Diagram Code.
     */
    public ControllerPanelExportDiagramCode(PanelExportDiagramCode panel) {
        super(panel);
        this.panelExportDiagramCode = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelExportDiagramCode.getDiagramComboBox().equals(event.getSource()))
            this.updateDiagram();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for updating the Diagram.
     */
    private void updateDiagram() {
        if (this.panelExportDiagramCode.getDiagramComboBox().getSelectedItem() != null)
            this.panelExportDiagramCode.setDiagram((Diagram) this.panelExportDiagramCode.getDiagramComboBox().getSelectedItem());
    }
}
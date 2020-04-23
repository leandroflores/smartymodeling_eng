package controller.view.panel.export.base.code;

import controller.view.panel.export.ControllerPanelExport;
import model.structural.base.Diagram;
import view.panel.export.base.code.PanelExportDiagramCode;

/**
 * <p>Class of Controller <b>ControllerPanelExportDiagramCode</b>.</p>
 * <p>Class responsible for controlling the <b>PanelExportDiagramCode</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-01-19
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.base.code.PanelExportDiagramCode
 */
public class ControllerPanelExportDiagramCode extends ControllerPanelExport {

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Diagram Code.
     */
    public ControllerPanelExportDiagramCode(PanelExportDiagramCode panel) {
        super(panel);
    }
    
    /**
     * Method responsible for returning the Selected Diagram.
     * @return Selected Diagram.
     */
    private Diagram getSelectedDiagram() {
        return (Diagram) this.getPanel().getContextComboBox().getSelectedItem();
    }
    
    @Override
    public void update() {
        this.getPanel().getNameTextField().setText(this.getSelectedDiagram().getName());
        this.getPanel().setDiagram(this.getSelectedDiagram());
    }
    
    @Override
    public PanelExportDiagramCode getPanel() {
        return (PanelExportDiagramCode) this.panel;
    }
}
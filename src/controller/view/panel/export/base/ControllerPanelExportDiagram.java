package controller.view.panel.export.base;

import controller.view.panel.export.ControllerPanelExport;
import model.structural.base.Diagram;
import view.panel.export.base.PanelExportDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelExportDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelExportDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    controller.view.panel.export.ControllerPanelExport
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.base.PanelExportDiagram
 */
public class ControllerPanelExportDiagram extends ControllerPanelExport {

    /**
     * Default constructor method of Class.
     * @param panel Panel Export Diagram.
     */
    public ControllerPanelExportDiagram(PanelExportDiagram panel) {
        super(panel);
    }
    
    @Override
    protected void update() {
        if (this.getPanel().getContextComboBox().getSelectedItem() != null)
            this.getPanel().setDiagram((Diagram) this.getPanel().getContextComboBox().getSelectedItem());
    }
    
    @Override
    public PanelExportDiagram getPanel() {
        return (PanelExportDiagram) this.panel;
    }
}
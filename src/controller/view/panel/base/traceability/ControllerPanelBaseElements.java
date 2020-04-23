package controller.view.panel.base.traceability;

import java.awt.event.ActionEvent;
import view.panel.base.traceability.PanelBaseElements;

/**
 * <p>Class of Controller <b>ControllerPanelBaseElements</b>.</p>
 * <p>Class responsible for controlling the <b>PanelBaseElements</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBase
 * @see    view.panel.base.traceability.PanelBaseElements
 */
public class ControllerPanelBaseElements extends ControllerPanelBase {

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Elements.
     */
    public ControllerPanelBaseElements(PanelBaseElements panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getPanel().getDiagramComboBox().equals(event.getSource()))
            this.getPanel().updateValues();
        else if (this.getPanel().getAddElementButton().equals(event.getSource()))
            this.getPanel().addElement();
        else if (this.getPanel().getDelElementButton().equals(event.getSource()))
            this.getPanel().delElement();
        this.update();
    }
    
    @Override
    protected void update() {
        this.refresh();
    }
    
    @Override
    public PanelBaseElements getPanel() {
        return (PanelBaseElements) this.panel;
    }
}
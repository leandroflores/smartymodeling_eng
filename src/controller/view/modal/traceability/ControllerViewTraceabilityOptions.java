package controller.view.modal.traceability;

import controller.view.modal.ControllerViewModal;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.traceability.Traceability;
import view.modal.traceability.ViewTraceabilityOptions;

/**
 * <p>Class of Controller <b>ControllerViewTraceabilityOptions</b>.</p>
 * <p>Class responsible for controlling the <b>ViewTraceabilityOptions</b> Events of SMartyModeling.</p>
 * @author Renan
 * @since  2021-06-21
 * @see    controller.view.modal.ControllerViewModal
 * @see    view.modal.traceability.ViewTraceabilityOptions
 */
public class ControllerViewTraceabilityOptions extends ControllerViewModal {
    
    /**
     * Default constructor method of Class.
     * @param view View Traceability Options.
     */
    public ControllerViewTraceabilityOptions(ViewTraceabilityOptions view) {
        super(view);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (getView().getButtonSave().equals(event.getSource()))
            saveTraceability();
        else if (getView().getButtonCancel().equals(event.getSource()))
            getView().dispose();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
        if (F1 == event.getKeyCode())
            saveTraceability();
        else if (F2 == event.getKeyCode())
            getView().dispose();
    }
    
    /**
     * Method responsible for adding a Traceability.
     */
    private void saveTraceability() {
        Traceability traceability = getView().getTraceability();
                     traceability.setDestination(getView().getPanelOptions().getDestination());
        getView().dispose();
    }
    
    @Override
    public ViewTraceabilityOptions getView() {
        return (ViewTraceabilityOptions) viewModal;
    }
}
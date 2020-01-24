package controller.view.new_.traceability;

import controller.view.new_.ControllerViewNew;
import java.awt.event.ActionEvent;
import model.structural.base.traceability.Traceability;
import view.message.ViewError;
import view.new_.traceability.ViewNewTraceability;

/**
 * <p>Class of Controller <b>ControllerViewNewTraceability</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewNewTraceability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  05/07/2019
 * @see    controller.view.new_.ControllerViewNew
 * @see    view.new_.traceability.ViewNewTraceability
 */
public class ControllerViewNewTraceability extends ControllerViewNew {
    private final ViewNewTraceability viewNewTraceability;

    /**
     * Default constructor method of Class.
     * @param viewNew View New Traceability.
     */
    public ControllerViewNewTraceability(ViewNewTraceability viewNew) {
        super(viewNew);
        this.viewNewTraceability = viewNew;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }

    /**
     * Method responsible for checking the Variability Name.
     * @return Name is checked.
     */
    public boolean checkName() {
        return this.check(this.viewNewTraceability.getPanelBaseTraceability().getNameTextField(), "Name is required!");
    }
    
    /**
     * Method responsible for checking the Variability Description.
     * @return Description is checked.
     */
    public boolean checkDescription() {
        return this.check(this.viewNewTraceability.getPanelBaseTraceability().getDescriptionTextField(), "Description is required!");
    }
    
    /**
     * Method responsible for checking the Elements.
     * @return Elements are checkeds.
     */
    public boolean checkElements() {
        if (this.viewNewTraceability.getTraceability().getElements().isEmpty()) {
            new ViewError(this.viewNewTraceability, "Add some Element!").setVisible(true);
            return false;
        }
        return true;
    }
    
    @Override
    public boolean check() {
        return this.checkName()
            && this.checkDescription()
            && this.checkElements();
    }

    @Override
    public void insert() {
        Traceability traceability = this.viewNewTraceability.getTraceability();
        this.viewNewTraceability.getProject().addTraceability(traceability);
        this.close();
    }
}
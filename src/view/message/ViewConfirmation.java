package view.message;

import controller.view.message.ControllerViewConfirmation;
import javax.swing.JButton;
import view.interfaces.Operation;
import view.View;
import view.ViewModal;
import view.ViewStyle;

/**
 * <p>Class of View <b>ViewConfirmation</b>.</p>
 * <p>Class responsible for defining the <b>Confirmation View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.message.ControllerViewConfirmation
 * @see    view.interfaces.Operation
 * @see    view.View
 * @see    view.ViewModal
 */
public final class ViewConfirmation extends ViewModal {
    private final Operation operation;
    private final String message;
    
    /**
     * Default constructor method of Class.
     * @param view View.
     * @param message Message.
     */
    public ViewConfirmation(View view, String message) {
        super(view);
        this.operation  = (Operation) view;
        this.message    = message;
        this.controller = new ControllerViewConfirmation(this);
        this.initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param viewModal View Modal.
     * @param message Message.
     */
    public ViewConfirmation(ViewModal viewModal, String message) {
        super(viewModal);
        this.operation  = (Operation) viewModal;
        this.message    = message;
        this.controller = new ControllerViewConfirmation(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Message");
        this.setSize(this.message.length() + 400, 200);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addHeader() {
        this.addLines(1);
        this.add(this.createLabelImage("icons/header/help.png"));
        this.addLines(1);
    }
    
    @Override
    public void addComponents() {
        this.add(this.createLabel(this.message));
        this.addLines(1);
    }

    @Override
    public void addFooter() {
        this.add(this.createButton("buttonYes",  "   Yes  ", "yes"));
        this.add(this.createButton("buttonNo",   "   No   ", "not"));
        this.add(this.createButton("buttonBack", " Cancel ", "back"));
    }
    
    /**
     * Method responsible for returning the Operation View.
     * @return Operation View.
     */
    public Operation getView() {
        return this.operation;
    }
    
    /**
     * Method responsible for returning the JButton Yes.
     * @return JButton Yes.
     */
    public JButton getButtonYes() {
        return this.buttons.get("buttonYes");
    }
    
    /**
     * Method responsible for returning the JButton No.
     * @return JButton No.
     */
    public JButton getButtonNo() {
        return this.buttons.get("buttonNo");
    }
    
    /**
     * Method responsible for returning the JButton Back.
     * @return JButton Back.
     */
    public JButton getButtonBack() {
        return this.buttons.get("buttonBack");
    }
}
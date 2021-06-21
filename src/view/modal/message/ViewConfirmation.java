package view.modal.message;

import controller.view.modal.message.ControllerViewConfirmation;
import java.awt.Dimension;
import javax.swing.JButton;
import view.interfaces.Operation;
import view.View;
import view.modal.ViewModal;
import view.style.ViewStyle;

/**
 * <p>Class of View <b>ViewConfirmation</b>.</p>
 * <p>Class responsible for defining the <b>Confirmation View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.view.modal.message.ControllerViewConfirmation
 * @see    view.modal.ViewModal
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
        initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param view View Modal.
     * @param message Message.
     */
    public ViewConfirmation(ViewModal view, String message) {
        super(view);
        this.operation  = (Operation) view;
        this.message    = message;
        this.controller = new ControllerViewConfirmation(this);
        initComponents();
    }

    @Override
    public void initComponents() {
        setTitle(ViewStyle.SYSTEM + "Message");
        setSize(new Dimension(message.length() + 400, 200));
        addHeader();
        addComponents();
        addFooter();
    }
    
    @Override
    public void addHeader() {
        addLines(1);
        add(createLabelImage("icons/header/help.png"));
        addLines(1);
    }
    
    @Override
    public void addComponents() {
        add(createLabel(message));
        addLines(1);
    }

    @Override
    public void addFooter() {
        add(createButton("yes",  "   Yes  ", "yes"));
        add(createButton("no",   "   No   ", "not"));
        add(createButton("back", " Cancel ", "back"));
    }
    
    /**
     * Method responsible for returning the Operation View.
     * @return Operation View.
     */
    public Operation getView() {
        return operation;
    }
    
    /**
     * Method responsible for returning the JButton Yes.
     * @return JButton Yes.
     */
    public JButton getButtonYes() {
        return getButton("yes");
    }
    
    /**
     * Method responsible for returning the JButton No.
     * @return JButton No.
     */
    public JButton getButtonNo() {
        return getButton("no");
    }
    
    /**
     * Method responsible for returning the JButton Back.
     * @return JButton Back.
     */
    public JButton getButtonBack() {
        return getButton("back");
    }
}
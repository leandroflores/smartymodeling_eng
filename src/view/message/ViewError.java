package view.message;

import controller.view.message.ControllerViewError;
import javax.swing.JButton;
import view.View;
import view.ViewModal;
import view.ViewStyle;

/**
 * <p>Class of View <b>ViewError</b>.</p>
 * <p>Class responsible for defining the <b>Error View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.message.ControllerViewError
 * @see    view.View
 * @see    view.ViewModal
 */
public final class ViewError extends ViewModal {
    private final String message;
    
    /**
     * Default constructor method of Class.
     * @param view View.
     * @param message Error Message.
     */
    public ViewError(View view, String message) {
        super(view);
        this.message    = message;
        this.controller = new ControllerViewError(this);
        this.initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param viewModal View Modal.
     * @param message Error Message.
     */
    public ViewError(ViewModal viewModal, String message) {
        super(viewModal);
        this.message    = message;
        this.controller = new ControllerViewError(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Error");
        this.setSize(this.message.length() + 500, 150);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addHeader() {
        this.addLines(1);
        this.add(this.createLabelImage("icons/delete.png"));
    }
    
    @Override
    public void addComponents() {
        this.add(this.createLabel(this.message));
        this.addLines(1);
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("buttonOk", "   Ok   ", "yes"));
    }

    /**
     * Method responsible for returning the JButton Ok.
     * @return JButton Ok.
     */
    public JButton getButtonOk() {
        return this.getButton("buttonOk");
    }
}
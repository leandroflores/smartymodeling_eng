package view.message;

import controller.view.message.ControllerViewMessage;
import javax.swing.JButton;
import view.View;
import view.ViewModal;
import view.ViewStyle;

/**
 * <p>Class of View <b>ViewMessage</b>.</p>
 * <p>Class responsible for defining the <b>Message View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.message.ControllerViewMessage
 * @see    view.View
 * @see    view.ViewModal
 */
public final class ViewMessage extends ViewModal {
    private final String message;
    
    /**
     * Default constructor method of Class.
     * @param view View.
     * @param message Message.
     */
    public ViewMessage(View view, String message) {
        super(view);
        this.message    = message;
        this.controller = new ControllerViewMessage(this);
        this.initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param viewModal View Modal.
     * @param message Message.
     */
    public ViewMessage(ViewModal viewModal, String message) {
        super(viewModal);
        this.message    = message;
        this.controller = new ControllerViewMessage(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Message");
        this.setSize(this.message.length() + 400, 150);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addHeader() {
        this.addLinhas(1);
        this.add(this.createLabelImage("icons/info.png"));
    }
    
    @Override
    public void addComponents() {
        this.add(this.createLabel(this.message));
        this.addLinhas(1);
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
        return this.buttons.get("buttonOk");
    }
}
package view.modal.message;

import controller.view.modal.message.ControllerViewMessage;
import java.awt.Dimension;
import javax.swing.JButton;
import view.View;
import view.modal.ViewModal;
import view.style.ViewStyle;

/**
 * <p>Class of View <b>ViewMessage</b>.</p>
 * <p>Class responsible for defining the <b>Message View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.view.modal.message.ControllerViewMessage
 * @see    view.modal.ViewModal
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
        initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param view View Modal.
     * @param message Message.
     */
    public ViewMessage(ViewModal view, String message) {
        super(view);
        this.message    = message;
        this.controller = new ControllerViewMessage(this);
        initComponents();
    }

    @Override
    public void initComponents() {
        setTitle(ViewStyle.SYSTEM + "Message");
        setSize(new Dimension(message.length() + 400, 150));
        addHeader();
        addComponents();
        addFooter();
    }
    
    @Override
    public void addHeader() {
        addLines(1);
        add(createLabelImage("icons/info.png"));
    }
    
    @Override
    public void addComponents() {
        add(createLabel(message));
        addLines(1);
    }

    @Override
    public void addFooter() {
        add(createButton("ok", "   Ok   ", "yes"));
    }
    
    /**
     * Method responsible for returning the JButton Ok.
     * @return JButton Ok.
     */
    public JButton getButtonOk() {
        return getButton("ok");
    }
}
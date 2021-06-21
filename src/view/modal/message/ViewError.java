package view.modal.message;

import controller.view.modal.message.ControllerViewError;
import java.awt.Dimension;
import javax.swing.JButton;
import view.View;
import view.modal.ViewModal;
import view.style.ViewStyle;

/**
 * <p>Class of View <b>ViewError</b>.</p>
 * <p>Class responsible for defining the <b>Error View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.view.modal.message.ControllerViewError
 * @see    view.modal.ViewModal
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
        initComponents();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param view View Modal.
     * @param message Error Message.
     */
    public ViewError(ViewModal view, String message) {
        super(view);
        this.message    = message;
        this.controller = new ControllerViewError(this);
        initComponents();
    }

    @Override
    public void initComponents() {
        setTitle(ViewStyle.SYSTEM + "Error");
        setSize(new Dimension(message.length() + 500, 150));
        addHeader();
        addComponents();
        addFooter();
    }
    
    @Override
    public void addHeader() {
        addLines(1);
        add(createLabelImage("icons/delete.png"));
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
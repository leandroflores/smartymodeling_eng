package view.modal.message;

import controller.view.modal.message.ControllerViewSave;
import java.awt.Dimension;
import javax.swing.JButton;
import view.modal.ViewModal;
import view.style.ViewStyle;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewSave</b>.</p>
 * <p>Class responsible for defining the <b>Save View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 * @see    controller.view.modal.message.ControllerViewSave
 * @see    view.modal.ViewModal
 */
public final class ViewSave extends ViewModal {
    private final ViewMenu view;
    private final Integer  code;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param code Operation Code.
     */
    public ViewSave(ViewMenu view, Integer code) {
        super(view);
        this.view       = view;
        this.code       = code;
        this.controller = new ControllerViewSave(this);
        initComponents();
    }

    @Override
    public void initComponents() {
        setTitle(ViewStyle.SYSTEM + "Message");
        setSize(new Dimension(600, 190));
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
        add(createLabel("Do you want to save Project changes?"));
        addLines(1);
    }

    @Override
    public void addFooter() {
        add(createButton("yes",  "  Yes   ", "yes"));
        add(createButton("no",   "   No   ", "not"));
        add(createButton("back", " Cancel ", "back"));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getView() {
        return view;
    }
    
    /**
     * Method responsible for returning the Code.
     * @return Code.
     */
    public Integer getCode() {
        return code;
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
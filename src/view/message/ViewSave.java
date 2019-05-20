package view.message;

import controller.view.message.ControllerViewSave;
import javax.swing.JButton;
import view.ViewModal;
import view.ViewStyle;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewSave</b>.</p>
 * <p>Class responsible for defining the <b>Save View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.view.message.ControllerViewSave
 * @see    view.ViewModal
 * @see    visao.estruturais.ViewMenu
 */
public final class ViewSave extends ViewModal {
    private final ViewMenu viewMenu;
    private final Integer  code;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param code Operation Code.
     */
    public ViewSave(ViewMenu view, Integer code) {
        super(view);
        this.viewMenu   = view;
        this.code       = code;
        this.controller = new ControllerViewSave(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Confirmation");
        this.setSize(600, 190);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addHeader() {
        this.addLinhas(1);
        this.add(this.createLabelImage("icons/header/help.png"));
        this.addLinhas(1);
    }
    
    @Override
    public void addComponents() {
        this.add(this.createLabel("Do you want to save Project changes?"));
        this.addLinhas(1);
    }

    @Override
    public void addFooter() {
        this.add(this.createButton("buttonYes",  "  Yes   ", "yes"));
        this.add(this.createButton("buttonNo",   "   No   ", "not"));
        this.add(this.createButton("buttonBack", "  Back  ", "back"));
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Code.
     * @return Code.
     */
    public Integer getCode() {
        return this.code;
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
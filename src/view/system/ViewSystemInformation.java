package view.system;

import controller.view.system.ControllerViewSystemInformation;
import javax.swing.JButton;
import view.ViewModal;
import view.ViewStyle;
import view.structural.ViewMenu;
import view.system.panel.PanelInformation;

/**
 * <p>Class of View <b>ViewSystemInformation</b>.</p>
 * <p>Class responsible for defining the <b>System Information View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    controller.view.system.ControllerViewSystemInformation
 * @see    view.ViewModal
 */
public final class ViewSystemInformation extends ViewModal {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewSystemInformation(ViewMenu view) {
        super(view);
        this.controller = new ControllerViewSystemInformation(this);
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Information");
        this.setSize(900, 300);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addHeader() {
        this.addLines(1);
        this.add(this.createLabelImage("system/information.png"));
        this.addLines(1);
    }
    
    @Override
    public void addComponents() {
        this.add(new PanelInformation());
        
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("buttonOk", "   Ok   ", "yes"));
    }

    /**
     * Method responsible for returning JButton Ok.
     * @return JButton Ok.
     */
    public JButton getButtonOk() {
        return this.getButton("buttonOk");
    }
}
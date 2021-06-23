package view.modal.system;

import controller.view.modal.system.ControllerViewSystemInformation;
import java.awt.Dimension;
import javax.swing.JButton;
import view.modal.ViewModal;
import view.style.ViewStyle;
import view.main.structural.ViewMenu;
import view.panel.system.PanelInformation;

/**
 * <p>Class of View <b>ViewSystemInformation</b>.</p>
 * <p>Class responsible for defining the <b>System Information View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    controller.view.modal.system.ControllerViewSystemInformation
 * @see    view.modal.ViewModal
 */
public final class ViewSystemInformation extends ViewModal {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewSystemInformation(ViewMenu view) {
        super(view);
        controller = new ControllerViewSystemInformation(this);
        initComponents();
    }
    
    @Override
    public void initComponents() {
        setTitle(ViewStyle.SYSTEM + "Information");
        setSize(new Dimension(900, 300));
        addHeader();
        addComponents();
        addFooter();
    }
    
    @Override
    public void addHeader() {
        addLines(1);
        add(createLabelImage("system/information.png"));
        addLines(1);
    }
    
    @Override
    public void addComponents() {
        add(new PanelInformation());
        
    }
    
    @Override
    public void addFooter() {
        add(createButton("ok", "   Ok   ", "yes"));
    }

    /**
     * Method responsible for returning JButton Ok.
     * @return JButton Ok.
     */
    public JButton getButtonOk() {
        return getButton("ok");
    }
}
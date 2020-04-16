package view.delete;

import funct.FunctView;
import javax.swing.JButton;
import javax.swing.JLabel;
import view.View;
import view.ViewModal;
import view.panel.modeling.PanelModeling;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewDelete</b>.</p>
 * <p>Class responsible for defining the <b>Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.delete.ControllerViewDelete
 * @see    view.panel.modeling.PanelModeling
 * @see    view.ViewModal
 */
public abstract class ViewDelete extends ViewModal {
    protected final ViewMenu view;
    protected final PanelModeling panel;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewDelete(ViewMenu view) {
        super(view);
        this.view  = view;
        this.panel = null;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param panel Panel Modeling.
     */
    public ViewDelete(PanelModeling panel) {
        super(panel.getViewMenu());
        this.view  = panel.getViewMenu();
        this.panel = panel;
        this.setSettings();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setSize(650, 150);
    }
    
    @Override
    public void addHeader() {
        this.add(new JLabel(new FunctView().createImage("icons/delete.png")));
    }
    
    /**
     * Method responsible for adding the Components on View.
     * @param message Delete Message.
     */
    protected void addComponents(String message) {
        this.addLines(1);
        
        this.addHeader();
        this.add(new JLabel("Confirm Delete: " +  message + "?"));
        
        this.addLines(1);
        
        this.addFooter();
    }
    
    @Override
    public void addFooter() {
        this.add(this.createButton("yesButton", "   Yes   ", "yes"));
        this.add(this.createButton("notButton", "   No    ", "not"));
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    public PanelModeling getPanelModeling() {
        return this.panel;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public View getView() {
        return this.panel.getViewMenu();
    }
    
    /**
     * Method responsible for returning the Yes Button.
     * @return Yes Button.
     */
    public JButton getYesButton() {
        return this.getButton("yesButton");
    }

    /**
     * Method responsible for returning the Not Button.
     * @return Not Button.
     */
    public JButton getNotButton() {
        return this.getButton("notButton");
    }
}
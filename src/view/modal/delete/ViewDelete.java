package view.modal.delete;

import funct.FunctView;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.structural.base.Project;
import view.View;
import view.modal.ViewModal;
import view.style.ViewStyle;
import view.panel.modeling.PanelModeling;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewDelete</b>.</p>
 * <p>Class responsible for defining the <b>Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.modal.delete.ControllerViewDelete
 * @see    view.modal.ViewModal
 */
public abstract class ViewDelete extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    protected final PanelModeling panel;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewDelete(ViewMenu view) {
        super(view);
        this.view    = view;
        this.project = view.getProject();
        this.panel   = null;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param panel Panel Modeling.
     */
    public ViewDelete(PanelModeling panel) {
        super(panel.getViewMenu());
        this.view    = panel.getViewMenu();
        this.project = panel.getViewMenu().getProject();
        this.panel   = panel;
    }
    
    @Override
    public void initComponents() {
        this.setSize(650, 150);
    }
    
    @Override
    public void addHeader() {
        this.setTitle(ViewStyle.SYSTEM + this.title);
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
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
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
    public View getViewMenu() {
        return this.panel.getViewMenu();
    }
}
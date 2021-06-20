package view.modal.delete;

import funct.FunctView;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.structural.base.Project;
import view.View;
import view.modal.ViewModal;
import view.style.ViewStyle;
import view.panel.modeling.PanelModeling;
import view.main.structural.ViewMenu;

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
        setSize(650, 150);
    }
    
    @Override
    public void addHeader() {
        setTitle(ViewStyle.SYSTEM + title);
        add(new JLabel(new FunctView().createImage("icons/delete.png")));
    }
    
    /**
     * Method responsible for adding the Components on View.
     * @param message Delete Message.
     */
    protected void addComponents(String message) {
        addLines(1);
        addHeader();
        add(new JLabel("Confirm Delete: " +  message + "?"));
        addLines(1);
        addFooter();
    }
    
    @Override
    public void addFooter() {
        add(createButton("yes", "   Yes   ", "yes"));
        add(createButton("not", "   No    ", "not"));
    }
    
    /**
     * Method responsible for returning the Yes Button.
     * @return Yes Button.
     */
    public JButton getYesButton() {
        return getButton("yes");
    }

    /**
     * Method responsible for returning the Not Button.
     * @return Not Button.
     */
    public JButton getNotButton() {
        return getButton("not");
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return project;
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    public PanelModeling getPanelModeling() {
        return panel;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public View getViewMenu() {
        return panel.getViewMenu();
    }
}
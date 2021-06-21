package view.modal.evaluation;

import controller.view.modal.evaluation.ControllerViewEvaluation;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.panel.modeling.PanelModeling;
import view.main.structural.ViewMenu;
import view.panel.evaluation.PanelEvaluation;

/**
 * <p>Class of View <b>ViewEvaluation</b>.</p>
 * <p>Class responsible for defining the <b>Evaluation View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-03-28
 * @see    controller.view.modal.evaluation.ControllerViewEvaluation
 * @see    view.modal.ViewModal
 */
public abstract class ViewEvaluation extends ViewModal {
    protected final ViewMenu view;
    protected final PanelModeling panel;
    protected final Project project;
    protected JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewEvaluation(ViewMenu view) {
        super(view);
        this.view    = view;
        this.panel   = null;
        this.project = view.getProject();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param panel Panel Modeling.
     */
    public ViewEvaluation(PanelModeling panel) {
        super(panel.getViewMenu());
        this.view    = panel.getViewMenu();
        this.panel   = panel;
        this.project = panel.getViewMenu().getProject();
    }
    
    @Override
    public void initComponents() {
        setSize(new Dimension(600, 520));
        addHeader();
        addComponents();
        addFooter();
    }
    
    @Override
    public void addComponents() {
        addTabbedPane();
        addPanelEvaluation();
        addLines(1);
    }
    
    /**
     * Method responsible for adding the Evaluation Tabbed Pane.
     */
    protected void addTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(500, 400));
        add(tabbedPane);
    }
    
    /**
     * Method responsible for adding the Evaluation Panel.
     */
    protected void addPanelEvaluation() {
        addPanel("evaluation", createPanelEvaluation());
        createScrollPane("evaluation", getPanelEvaluation());
        getScrollPane("evaluation").setViewportView(getPanelEvaluation());
        tabbedPane.removeAll();
        tabbedPane.add("Evaluation", getScrollPane("evaluation"));
    }
    
    /**
     * Method responsible for creating the Evaluation Panel.
     * @return Evaluation Panel.
     */
    protected abstract PanelEvaluation createPanelEvaluation();
    
    @Override
    public void addFooter() {
        add(createButton("refresh", " Refresh ", "refresh"));
        add(createButton("clear",   "  Clear  ", "clear"));
        add(createButton("cancel",  "  Cancel ", "back"));
    }
    
    /**
     * Method responsible for creating the Panel Evaluation.
     * @return Panel Evaluation.
     */
    public PanelEvaluation getPanelEvaluation() {
        return (PanelEvaluation) getPanel("evaluation");
    }
    
    /**
     * Method responsible for returning the Refresh Button.
     * @return Refresh Button.
     */
    public JButton getRefreshButton() {
        return getButton("refresh");
    }
    
    /**
     * Method responsible for returning the Clear Button.
     * @return Clear Button.
     */
    public JButton getClearButton() {
        return getButton("clear");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return getButton("cancel");
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewEvaluation getController() {
        return (ControllerViewEvaluation) controller;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return project;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return view;
    }
}
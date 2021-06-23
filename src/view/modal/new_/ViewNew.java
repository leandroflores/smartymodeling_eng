package view.modal.new_;

import java.awt.Dimension;
import javax.swing.JButton;
import model.structural.base.Project;
import view.modal.ViewModal;
import view.main.structural.ViewMenu;
import view.panel.Panel;

/**
 * <p>Class of View <b>ViewNew</b>.</p>
 * <p>Class responsible for defining the <b>New View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-04
 * @see    controller.view.modal.new_.ControllerViewNew
 * @see    view.modal.ViewModal
 */
public abstract class ViewNew extends ViewModal {
    protected final ViewMenu view;
    protected final Project  project;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNew(ViewMenu view) {
        super(view);
        this.view    = view;
        this.project = view.getProject();
    }
    
    @Override
    public void initComponents() {
        addHeader();
        addComponents();
        addFooter();
        setSize(getViewDimension());
    }
    
    /**
     * Method responsible for returning the View Dimension.
     * @return View Dimension.
     */
    protected abstract Dimension getViewDimension();
    
    @Override
    public void addComponents() {
        addPanel("new", createPanelNew());
        getPanelNew().setPreferredSize(getPanelDimension());
        add(getPanelNew());
        addLines(1);
    }
    
    /**
     * Method responsible for creating the Panel New.
     * @return Panel New.
     */
    protected abstract Panel createPanelNew();
    
    /**
     * Method responsible for returning the Panel Dimension.
     * @return Panel Dimension.
     */
    protected abstract Dimension getPanelDimension();
    
    @Override
    public void addFooter() {
        add(createButton("insert", " Insert ", "insert"));
        add(createButton("cancel", " Cancel ", "back"));
    }
    
    /**
     * Method responsible for returning the Panel New.
     * @return Panel New.
     */
    protected Panel getPanelNew() {
        return (Panel) getPanel("new");
    }
    
    /**
     * Method responsible for returning the Insert Button.
     * @return Insert Button.
     */
    public JButton getInsertButton() {
        return getButton("insert");
    }
    
    /**
     * Method responsible for returning the Cancel Button.
     * @return Cancel Button.
     */
    public JButton getCancelButton() {
        return getButton("cancel");
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
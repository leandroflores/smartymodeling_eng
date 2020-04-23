package view.modal.new_.base.requirement;

import controller.view.modal.new_.base.requirement.ControllerViewNewRequirement;
import java.awt.Dimension;
import model.structural.base.requirement.Requirement;
import view.modal.new_.ViewNew;
import view.panel.base.requirement.PanelBaseRequirement;
import view.panel.edit.base.requirement.PanelEditRequirement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewRequirement</b>.</p>
 * <p>Class responsible for defining the <b>New Requirement View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.modal.new_.base.requirement.ControllerViewNewRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewRequirement extends ViewNew {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewRequirement(ViewMenu view) {
        super(view);
        this.requirement = new Requirement();
        this.controller  = new ControllerViewNewRequirement(this);
        this.title       = "New Requirement";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 450));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelBaseRequirement();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement.
     */
    private void addPanelBaseRequirement() {
        this.addPanel("panelEditRequirement", new PanelEditRequirement(this.view, this.requirement, 0));
        this.getPanel("panelEditRequirement").setPreferredSize(new Dimension(500, 325));
        this.add(this.getPanel("panelEditRequirement"));
    }
    
    /**
     * Method responsible for returning the Panel Base Requirement.
     * @return Panel Base Requirement.
     */
    public PanelBaseRequirement getPanelBaseRequirement() {
        return ((PanelEditRequirement) this.getPanel("panelEditRequirement")).getPanelBaseRequirement();
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
}
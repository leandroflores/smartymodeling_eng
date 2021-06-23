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
        requirement = new Requirement();
        controller  = new ControllerViewNewRequirement(this);
        title       = "New Requirement";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 450);
    }
    
    @Override
    protected PanelEditRequirement createPanelNew() {
        return new PanelEditRequirement(view, requirement, 0);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 325);
    }
    
    @Override
    protected PanelEditRequirement getPanelNew() {
        return (PanelEditRequirement) super.getPanelNew();
    }
    
    /**
     * Method responsible for returning the Panel Base Requirement.
     * @return Panel Base Requirement.
     */
    public PanelBaseRequirement getPanelBaseRequirement() {
        return getPanelNew().getPanelBaseRequirement();
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return requirement;
    }
}
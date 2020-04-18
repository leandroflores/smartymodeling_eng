package view.delete.requirement;

import controller.view.delete.requirement.ControllerViewDeleteRequirement;
import model.structural.base.requirement.Requirement;
import view.ViewStyle;
import view.delete.ViewDelete;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewDeleteTraceability</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Delete View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.delete.requirement.ControllerViewDeleteRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.delete.ViewDelete
 */
public final class ViewDeleteRequirement extends ViewDelete {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param requirement Requirement.
     */
    public ViewDeleteRequirement(PanelModeling panel, Requirement requirement) {
        super(panel);
        this.requirement = requirement;
        this.controller  = new ControllerViewDeleteRequirement(this);
        this.initComponents();
    }

    @Override
    public void initComponents() {
        this.setTitle(ViewStyle.SYSTEM + "Delete Requirement");
        this.addComponents();
    }

    @Override
    public void addComponents() {
        super.addComponents(this.requirement.toString());
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
}
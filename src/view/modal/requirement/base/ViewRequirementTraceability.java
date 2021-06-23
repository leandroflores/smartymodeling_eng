package view.modal.requirement.base;

import controller.view.modal.requirement.base.ControllerViewRequirementTraceability;
import java.awt.Dimension;
import view.main.structural.ViewMenu;
import view.modal.requirement.ViewRequirement;
import view.panel.requirement.base.PanelRequirementTraceability;

/**
 * <p>Class of View <b>ViewRequirementTraceability</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Traceability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-27
 * @see    controller.view.modal.requirement.base.ControllerViewRequirementTraceability
 * @see    view.modal.requirement.ViewRequirement
 */
public final class ViewRequirementTraceability extends ViewRequirement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewRequirementTraceability(ViewMenu view) {
        super(view);
        controller = new ControllerViewRequirementTraceability(this);
        title      = "Requirement Traceability";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(700, 560);
    }
    
    @Override
    protected PanelRequirementTraceability createPanelRequirement() {
        return new PanelRequirementTraceability(this);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(650, 450);
    }
}
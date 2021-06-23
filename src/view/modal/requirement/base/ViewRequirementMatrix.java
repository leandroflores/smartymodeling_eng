package view.modal.requirement.base;

import controller.view.modal.requirement.base.ControllerViewRequirementMatrix;
import java.awt.Dimension;
import view.main.structural.ViewMenu;
import view.modal.requirement.ViewRequirement;
import view.panel.requirement.base.PanelRequirementMatrix;

/**
 * <p>Class of View <b>ViewRequirementMatrix</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Matrix View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-25
 * @see    controller.view.modal.requirement.base.ControllerViewRequirementMatrix
 * @see    view.modal.requirement.ViewRequirement
 */
public final class ViewRequirementMatrix extends ViewRequirement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewRequirementMatrix(ViewMenu view) {
        super(view);
        controller = new ControllerViewRequirementMatrix(this);
        title      = "Requirement Matrix";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(700, 550);
    }
    
    @Override
    protected PanelRequirementMatrix createPanelRequirement() {
        return new PanelRequirementMatrix(this);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(650, 450);
    }
}
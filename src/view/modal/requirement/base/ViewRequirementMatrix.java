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
        this.controller = new ControllerViewRequirementMatrix(this);
        this.title      = "Requirement Matrix";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(700, 550));
        this.updateTitle();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelRequirementMatrix();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Requirement Matrix.
     */
    private void addPanelRequirementMatrix() {
        this.addPanel("panelRequirementMatrix", new PanelRequirementMatrix(this));
        this.getPanel("panelRequirementMatrix").setPreferredSize(new Dimension(650, 450));
        this.getPanel("panelRequirementMatrix").setMinimumSize(new Dimension(650, 450));
        this.add(this.getPanel("panelRequirementMatrix"));
    }
}
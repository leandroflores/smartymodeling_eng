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
        this.controller = new ControllerViewRequirementTraceability(this);
        this.title      = "Requirement Traceability";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(700, 560));
        this.updateTitle();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelRequirementTraceability();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Requirement Traceability.
     */
    private void addPanelRequirementTraceability() {
        this.addPanel("panelRequirementTraceability", new PanelRequirementTraceability(this));
        this.getPanel("panelRequirementTraceability").setPreferredSize(new Dimension(650, 450));
        this.getPanel("panelRequirementTraceability").setMinimumSize(new Dimension(650, 450));
        this.add(this.getPanel("panelRequirementTraceability"));
    }
}
package view.modal.edit.base.requirement;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.requirement.Requirement;
import view.modal.edit.ViewEdit;
import view.panel.base.requirement.traceability.PanelBaseRequirementDiagram;
import view.panel.base.requirement.traceability.PanelBaseRequirementTraceability;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.edit.base.requirement.ControllerViewEditRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditRequirementTraceability extends ViewEdit {
    private Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param requirement Requirement.
     */
    public ViewEditRequirementTraceability(PanelModeling panel, Requirement requirement) {
        super(panel);
        this.requirement = requirement;
//        this.controller  = new ControllerViewEditRequirement(this);
        this.title       = "Edit Requirement Traceability";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 350);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 225));
            this.addPanelBaseRequirementTraceability();
            this.addPanelBaseRequirementDiagrams();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement Traceability.
     */
    private void addPanelBaseRequirementTraceability() {
        this.addPanel("panelBaseRequirement", new PanelBaseRequirementTraceability(this.getViewMenu(), this.requirement));
        this.createScrollPane("scrollPanelBaseRequirement",  this.getPanel("panelBaseRequirement"));
        this.getScrollPane("scrollPanelBaseRequirement").setViewportView(this.getPanel("panelBaseRequirement"));
        this.tabbedPane.add("Requirement", this.getScrollPane("scrollPanelBaseRequirement"));
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement Diagrams.
     */
    private void addPanelBaseRequirementDiagrams() {
        String[] types = {"Feature", "UseCase", "Class", "Component", "Sequence", "Activity"};
        for (int i = 0; i < types.length; i++) {
            String type      = types[i];
            String panel_id  = "panelBaseRequirement" + type;
            String scroll_id = "scrollPanelBaseRequirement" + type;
            this.addPanel(panel_id, new PanelBaseRequirementDiagram(this.getViewMenu(), this.requirement, type));
            this.createScrollPane(scroll_id, this.getPanel(panel_id));
            this.getScrollPane(scroll_id).setViewportView(this.getPanel(panel_id));
            this.tabbedPane.add(type, this.getScrollPane(scroll_id));
            this.tabbedPane.setEnabledAt(i + 1, this.getPanelBaseRequirement(panel_id).existsDiagram());
        }
    }
    
    
    public void updateDiagramPanels(Requirement requirement) {
        String[] types = {"Feature", "UseCase", "Class", "Component", "Sequence", "Activity"};
        for (String type : types)
            ((PanelBaseRequirementDiagram) this.getPanel("panelBaseRequirement" + type)).setRequirement(requirement);
    }
    
    /**
     * Method responsible for returning the Panel Base Requirement Traceability.
     * @param  id Panel Id.
     * @return Panel Base Requirement Traceability.
     */
    public PanelBaseRequirementDiagram getPanelBaseRequirement(String id) {
        return (PanelBaseRequirementDiagram) this.getPanel(id);
    }
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
}
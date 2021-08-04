package view.panel.edit.base.requirement;

import java.awt.Dimension;
import model.structural.base.requirement.Requirement;
import view.panel.base.requirement.PanelBaseRequirement;
import view.panel.base.requirement.traceability.PanelBaseRequirementDiagram;
import view.panel.edit.PanelEdit;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditRequirement</b>.</p> 
 * <p>Class responsible for defining the <b>Edit Requirement Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    model.structural.base.requirement.Requirement
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditRequirement extends PanelEdit {
    private final Requirement requirement;
    private final boolean traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     * @param index Tab Index.
     * @param traceability Traceability Flag.
     */
    public PanelEditRequirement(ViewMenu view, Requirement requirement, Integer index, boolean traceability) {
        super(view);
        this.requirement  = requirement;
        this.traceability = traceability;
        setPreferredSize(new Dimension(200, 100));
        addComponents();
        tabbedPane.setSelectedIndex(index);
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseRequirement();
        addPanelBaseRequirementDiagrams();
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement.
     */
    private void addPanelBaseRequirement() {
        addPanel("base_requirement", new PanelBaseRequirement(getViewMenu(), requirement, traceability));
        createScrollPane("base_requirement", getPanelBaseRequirement());
        getScrollPane("base_requirement").setViewportView(getPanelBaseRequirement());
        tabbedPane.add("Requirement", getScrollPane("base_requirement"));
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement Diagrams.
     */
    private void addPanelBaseRequirementDiagrams() {
        String[] types = {"Feature", "UseCase", "Class", "Component", "Sequence", "Activity"};
        for (int i = 0; i < types.length; i++) {
            String type      = types[i];
            String panel_id  = "base_requirement" + type;
            String scroll_id = "base_requirement" + type;
            addPanel(panel_id, new PanelBaseRequirementDiagram(getViewMenu(), requirement, type));
            createScrollPane(scroll_id, getPanel(panel_id));
            getScrollPane(scroll_id).setViewportView(getPanel(panel_id));
            tabbedPane.add(type, getScrollPane(scroll_id));
            tabbedPane.setEnabledAt(i + 1, getPanelBaseRequirement(panel_id).existsDiagram());
        }
    }
    
    /**
     * Method responsible for returning the Panel Base Requirement.
     * @return Panel Base Requirement.
     */
    public PanelBaseRequirement getPanelBaseRequirement() {
        return (PanelBaseRequirement) getPanel("base_requirement");
    }
    
    /**
     * Method responsible for returning the Panel Base Requirement Traceability.
     * @param  id Panel Id.
     * @return Panel Base Requirement Traceability.
     */
    public PanelBaseRequirementDiagram getPanelBaseRequirement(String id) {
        return (PanelBaseRequirementDiagram) getPanel(id);
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return requirement;
    }
}
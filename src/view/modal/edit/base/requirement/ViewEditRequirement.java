package view.modal.edit.base.requirement;

import controller.view.modal.edit.base.requirement.ControllerViewEditRequirement;
import java.awt.Dimension;
import model.structural.base.requirement.Requirement;
import view.modal.edit.ViewEdit;
import view.panel.base.requirement.PanelBaseRequirement;
import view.panel.edit.base.requirement.PanelEditRequirement;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.modal.edit.base.requirement.ControllerViewEditRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditRequirement extends ViewEdit {
    private final Requirement requirement;
    private final Integer index;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param requirement Requirement.
     * @param index Tab Index.
     */
    public ViewEditRequirement(PanelModeling panel, Requirement requirement, Integer index) {
        super(panel.getViewMenu());
        this.requirement = requirement;
        this.controller  = new ControllerViewEditRequirement(this);
        this.title       = "Edit Requirement Data";
        this.index       = index;
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 425));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.addPanelEditRequirement();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Requirement.
     */
    private void addPanelEditRequirement() {
        this.addPanel("panelEditRequirement", new PanelEditRequirement(this.view, this.requirement, this.index));
        this.getPanel("panelEditRequirement").setPreferredSize(new Dimension(500, 300));
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
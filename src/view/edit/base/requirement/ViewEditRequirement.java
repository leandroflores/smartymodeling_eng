package view.edit.base.requirement;

import controller.view.edit.base.requirement.ControllerViewEditRequirement;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.requirement.Requirement;
import view.edit.ViewEdit;
import view.panel.base.requirement.PanelBaseRequirement;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditRequirement</b>.</p>
 * <p>Class responsible for defining the <b>Requirement Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-18
 * @see    controller.view.edit.base.requirement.ControllerViewEditRequirement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.edit.ViewEdit
 */
public final class ViewEditRequirement extends ViewEdit {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param requirement Requirement.
     */
    public ViewEditRequirement(PanelModeling panel, Requirement requirement) {
        super(panel);
        this.requirement = requirement;
        this.controller  = new ControllerViewEditRequirement(this);
        this.title       = "Edit Requirement Data";
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
            this.addPanelBaseRequirement();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement.
     */
    private void addPanelBaseRequirement() {
        this.addPanel("panelBaseRequirement", new PanelBaseRequirement(this.getViewMenu(), this.requirement));
        this.createScrollPane("scrollPanelBaseRequirement",  this.getPanelBaseRequirement());
        this.getScrollPanelBaseRequirement().setViewportView(this.getPanelBaseRequirement());
        this.tabbedPane.add("Requirement", this.getScrollPanelBaseRequirement());
    }
    
    /**
     * Method responsible for returning the Panel Base Requirement.
     * @return Panel Base Requirement.
     */
    public PanelBaseRequirement getPanelBaseRequirement() {
        return (PanelBaseRequirement) this.getPanel("panelBaseRequirement");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Requirement.
     * @return Scroll Panel Base Requirement.
     */
    public JScrollPane getScrollPanelBaseRequirement() {
        return this.getScrollPane("scrollPanelBaseRequirement");
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
}
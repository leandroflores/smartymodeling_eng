package view.panel.edit.base.requirement;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.requirement.Requirement;
import view.panel.base.requirement.PanelBaseRequirement;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

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
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param requirement Requirement.
     */
    public PanelEditRequirement(ViewMenu viewMenu, Requirement requirement) {
        super(viewMenu);
        this.requirement = requirement;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
            this.addPanelBaseRequirement();
        this.add(this.tabbedPane);
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
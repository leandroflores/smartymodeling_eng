package view.new_.base.requirement.traceability;

import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.requirement.Requirement;
import view.new_.ViewNew;
import view.panel.base.requirement.traceability.PanelBaseRequirementTraceability;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewRequirementTraceability</b>.</p>
 * <p>Class responsible for defining the <b>New Traceability Requirement View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.new_.base.requirement.
 * @see    model.structural.base.requirement.Requirement
 * @see    view.new_.ViewNew
 */
public final class ViewNewRequirementTraceability extends ViewNew {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     */
    public ViewNewRequirementTraceability(ViewMenu view, Requirement requirement) {
        super(view);
        this.requirement = requirement;
//        this.controller  = new ControllerViewNewRequirement(this);
        this.title       = "Add Element to Requirement";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 450));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(450, 325));
            this.addPanelBaseRequirementTraceability();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement Traceability.
     */
    private void addPanelBaseRequirementTraceability() {
        this.addPanel("panelBaseRequirementTraceability", new PanelBaseRequirementTraceability(this.view, this.requirement));
        this.createScrollPane("scrollPanelBaseRequirementTraceability", this.getPanel("panelBaseRequirementTraceability"));
        this.getScrollPane("scrollPanelBaseRequirementTraceability").setViewportView(this.getPanel("panelBaseRequirementTraceability"));
        this.tabbedPane.add("Add Element", this.getScrollPane("scrollPanelBaseRequirementTraceability"));
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
}
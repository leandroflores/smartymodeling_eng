package view.modal.new_.base.requirement.traceability;

import controller.view.modal.new_.base.requirement.traceability.ControllerViewNewAddElement;
import java.awt.Dimension;
import javax.swing.JTabbedPane;
import model.structural.base.requirement.Requirement;
import view.modal.new_.ViewNew;
import view.panel.base.requirement.traceability.PanelBaseAddElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewAddElement</b>.</p>
 * <p>Class responsible for defining the <b>New Add Element View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-21
 * @see    controller.view.modal.new_.base.requirement.traceability.ControllerViewNewAddElement
 * @see    model.structural.base.requirement.Requirement
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewAddElement extends ViewNew {
    private final Requirement requirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param requirement Requirement.
     */
    public ViewNewAddElement(ViewMenu view, Requirement requirement) {
        super(view);
        this.requirement = requirement;
        this.controller  = new ControllerViewNewAddElement(this);
        this.title       = "Add Element to Requirement";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(new Dimension(600, 350));
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(500, 230));
            this.addPanelBaseRequirementAddElement();
        this.add(this.tabbedPane);
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement Add Element.
     */
    private void addPanelBaseRequirementAddElement() {
        this.addPanel("panelBaseRequirementAddElement", new PanelBaseAddElement(this.view, this.requirement));
        this.createScrollPane("scrollPanelBaseRequirementAddElement", this.getPanel("panelBaseRequirementAddElement"));
        this.getScrollPane("scrollPanelBaseRequirementAddElement").setViewportView(this.getPanel("panelBaseRequirementAddElement"));
        this.tabbedPane.add("Add Element", this.getScrollPane("scrollPanelBaseRequirementAddElement"));
    }
    
    public PanelBaseAddElement getPanelBase() {
        return (PanelBaseAddElement) this.getPanel("panelBaseRequirementAddElement");
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
}
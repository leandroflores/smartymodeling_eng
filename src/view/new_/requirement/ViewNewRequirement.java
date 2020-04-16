package view.new_.requirement;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.requirement.Requirement;
import view.edit.panel.base.requirement.PanelBaseRequirement;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewRequirement</b>.</p>
 * <p>Class responsible for defining the <b>New Requirement View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-15
 * @see    controller.view.new_.
 * @see    model.structural.base.requirement.Requirement
 * @see    view.new_.ViewNew
 */
public final class ViewNewRequirement extends ViewNew {
    private final Requirement requirement;
    private PanelBaseRequirement panelBaseRequirement;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewRequirement(ViewMenu view) {
        super(view);
        this.requirement  = new Requirement();
//        this.controller   = new ControllerViewNewTraceability(this);
        this.title        = "New Requirement";
        this.initComponents();
    }
    
    @Override
    public void initComponents() {
        this.setSize(600, 445);
        this.addHeader();
        this.addComponents();
        this.addFooter();
    }
    
    @Override
    public void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(550, 325));
        
        this.addPanelBaseRequirement();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Requirement.
     */
    private void addPanelBaseRequirement() {
        this.panelBaseRequirement = new PanelBaseRequirement(this.getViewMenu(), this.requirement);
        this.createScrollPane("scrollPanelBaseRequirement",  this.panelBaseRequirement);
        this.getScrollPanelBaseRequirement().setViewportView(this.panelBaseRequirement);
        this.tabbedPane.add("Requirement", this.getScrollPanelBaseRequirement());
    }
    
    /**
     * Method responsible for returning the Requirement.
     * @return Requirement.
     */
    public Requirement getRequirement() {
        return this.requirement;
    }
    
    /**
     * Method responsible for returning the Panel Base Requirement.
     * @return Panel Base Requirement.
     */
    public PanelBaseRequirement getPanelBaseRequirement() {
        return this.panelBaseRequirement;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Requirement.
     * @return Scroll Panel Base Requirement.
     */
    public JScrollPane getScrollPanelBaseRequirement() {
        return this.getScrollPane("scrollPanelBaseRequirement");
    }
}
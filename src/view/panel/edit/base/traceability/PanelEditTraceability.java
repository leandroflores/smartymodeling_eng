package view.panel.edit.base.traceability;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseTraceability;
import view.panel.edit.PanelEdit;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditTraceability</b>.</p> 
 * <p>Class responsible for defining a Panel for Edit the <b>Traceability</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  23/07/2019
 * @see    model.structural.base.traceability.Traceability
 * @see    view.panel.edit.PanelEdit
 */
public final class PanelEditTraceability extends PanelEdit {
    private final Project project;
    private final Traceability traceability;
    private PanelBaseTraceability panelBaseTraceability;
    private PanelBaseElements panelBaseElements;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param traceability Traceability.
     */
    public PanelEditTraceability(ViewMenu viewMenu, Traceability traceability) {
        super(viewMenu);
        this.project      = this.viewMenu.getProject();
        this.traceability = traceability;
        this.setPreferredSize(new Dimension(200, 100));
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        this.tabbedPane = new JTabbedPane();
        this.tabbedPane.setPreferredSize(new Dimension(100, 100));
        
        this.addPanelBaseTraceability();
        this.addPanelBaseElements();
        
        this.add(this.tabbedPane);
    }
    
    /**
     * Method responsible for adding the Panel Base Traceability.
     */
    private void addPanelBaseTraceability() {
        this.panelBaseTraceability = new PanelBaseTraceability(this.viewMenu, this.traceability);
        this.createScrollPane("scrollPanelBaseTraceability",  this.panelBaseTraceability);
        this.getScrollPanelBaseTraceability().setViewportView(this.panelBaseTraceability);
        this.tabbedPane.add("Traceability", this.getScrollPanelBaseTraceability());
    }
    
    /**
     * Method responsible for adding the Panel Base Elements.
     */
    private void addPanelBaseElements() {
        this.panelBaseElements = new PanelBaseElements(this.viewMenu, this.traceability);
        this.createScrollPane("scrollPanelBaseElements",  this.panelBaseElements);
        this.getScrollPanelBaseElements().setViewportView(this.panelBaseElements);
        this.tabbedPane.add("Elements", this.getScrollPanelBaseElements());
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Traceability.
     * @return Scroll Panel Base Traceability.
     */
    public JScrollPane getScrollPanelBaseTraceability() {
        return this.getScrollPane("scrollPanelBaseTraceability");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Elements.
     * @return Scroll Panel Base Elements.
     */
    public JScrollPane getScrollPanelBaseElements() {
        return this.getScrollPane("scrollPanelBaseElements");
    }
}
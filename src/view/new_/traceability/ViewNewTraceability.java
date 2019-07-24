package view.new_.traceability;

import controller.view.new_.traceability.ControllerViewNewTraceability;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import view.edit.panel.base.traceability.PanelBaseElements;
import view.edit.panel.base.traceability.PanelBaseTraceability;
import view.new_.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewEditTraceability</b>.</p>
 * <p>Class responsible for defining the <b>New Traceability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  22/07/2019
 * @see    controller.view.new_.traceability.ControllerViewNewTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.new_.ViewNew
 */
public final class ViewNewTraceability extends ViewNew { 
    private final Project project;
    private final Traceability traceability;
    private PanelBaseTraceability panelBaseTraceability;
    private PanelBaseElements panelBaseElements;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewNewTraceability(ViewMenu view, Project project) {
        super(view);
        this.project      = project;
        this.traceability = new Traceability();
        this.controller   = new ControllerViewNewTraceability(this);
        this.title        = "New Traceability";
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
        
        this.addPanelBaseTraceability();
        this.addPanelBaseElements();
        
        this.add(this.tabbedPane);
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Base Traceability.
     */
    private void addPanelBaseTraceability() {
        this.panelBaseTraceability = new PanelBaseTraceability(this.getViewMenu(), this.traceability);
        this.createScrollPane("scrollPanelBaseTraceability", this.panelBaseTraceability);
        this.getScrollPanelBaseTraceability().setViewportView(this.panelBaseTraceability);
        this.tabbedPane.add("Traceability", this.getScrollPanelBaseTraceability());
    }
    
    /**
     * Method responsible for adding the Panel Base Elements.
     */
    private void addPanelBaseElements() {
        this.panelBaseElements  = new PanelBaseElements(this.getViewMenu(), this.traceability);
        this.createScrollPane("scrollPanelElements",  this.panelBaseElements);
        this.getScrollPanelElements().setViewportView(this.panelBaseElements);
        this.tabbedPane.add("Elements", this.getScrollPanelElements());
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
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return this.panelBaseTraceability;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Traceability.
     * @return Scroll Panel Base Traceability.
     */
    public JScrollPane getScrollPanelBaseTraceability() {
        return this.scrollPanes.get("scrollPanelBaseTraceability");
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return this.panelBaseElements;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Elements.
     * @return Scroll Panel Elements.
     */
    public JScrollPane getScrollPanelElements() {
        return this.scrollPanes.get("scrollPanelElements");
    }
}
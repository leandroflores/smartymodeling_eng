package view.new_.base.traceability;

import controller.view.new_.base.traceability.ControllerViewNewTraceability;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseTraceability;
import view.new_.base.ViewNew;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>ViewNewTraceability</b>.</p>
 * <p>Class responsible for defining the <b>New Traceability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.new_.base.traceability.ControllerViewNewTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.new_.base.ViewNew
 */
public final class ViewNewTraceability extends ViewNew {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param project Project.
     */
    public ViewNewTraceability(ViewMenu view, Project project) {
        super(view);
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
        this.addPanel("panelBaseTraceability", new PanelBaseTraceability(this.getViewMenu(), this.traceability));
        this.createScrollPane("scrollPanelBaseTraceability",  this.getPanelBaseTraceability());
        this.getScrollPanelBaseTraceability().setViewportView(this.getPanelBaseTraceability());
        this.tabbedPane.add("Traceability", this.getScrollPanelBaseTraceability());
    }
    
    /**
     * Method responsible for adding the Panel Base Elements.
     */
    private void addPanelBaseElements() {
        this.addPanel("panelBaseElements", new PanelBaseElements(this.getViewMenu(), this.traceability));
        this.createScrollPane("scrollPanelElements",  this.getPanelBaseElements());
        this.getScrollPanelElements().setViewportView(this.getPanelBaseElements());
        this.tabbedPane.add("Elements", this.getScrollPanelElements());
    }
    
    /**
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return (PanelBaseTraceability) this.getPanel("panelBaseTraceability");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Base Traceability.
     * @return Scroll Panel Base Traceability.
     */
    public JScrollPane getScrollPanelBaseTraceability() {
        return this.getScrollPane("scrollPanelBaseTraceability");
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return (PanelBaseElements) this.getPanel("panelBaseElements");
    }
    
    /**
     * Method responsible for returning the Scroll Panel Elements.
     * @return Scroll Panel Elements.
     */
    public JScrollPane getScrollPanelElements() {
        return this.getScrollPane("scrollPanelElements");
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
}
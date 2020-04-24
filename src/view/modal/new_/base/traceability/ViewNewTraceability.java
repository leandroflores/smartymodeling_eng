package view.modal.new_.base.traceability;

import controller.view.modal.new_.base.traceability.ControllerViewNewTraceability;
import java.awt.Dimension;
import model.structural.base.traceability.Traceability;
import view.panel.base.traceability.PanelBaseElements;
import view.panel.base.traceability.PanelBaseTraceability;
import view.modal.new_.ViewNew;
import view.main.structural.ViewMenu;
import view.panel.edit.base.traceability.PanelEditTraceability;

/**
 * <p>Class of View <b>ViewNewTraceability</b>.</p>
 * <p>Class responsible for defining the <b>New Traceability View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.modal.new_.base.traceability.ControllerViewNewTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.modal.new_.ViewNew
 */
public final class ViewNewTraceability extends ViewNew {
    private final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ViewNewTraceability(ViewMenu view) {
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
        this.addPanelEditTraceability();
        this.addLines(1);
    }
    
    /**
     * Method responsible for adding the Panel Edit Traceability.
     */
    private void addPanelEditTraceability() {
        this.addPanel("panelEditTraceability", new PanelEditTraceability(this.view, this.traceability));
        this.getPanel("panelEditTraceability").setPreferredSize(new Dimension(500, 325));
        this.add(this.getPanel("panelEditTraceability"));
    }
    
    /**
     * Method responsible for returning the Panel Base Traceability.
     * @return Panel Base Traceability.
     */
    public PanelBaseTraceability getPanelBaseTraceability() {
        return ((PanelEditTraceability) this.getPanel("panelEditTraceability")).getPanelBaseTraceability();
    }
    
    /**
     * Method responsible for returning the Panel Base Elements.
     * @return Panel Base Elements.
     */
    public PanelBaseElements getPanelBaseElements() {
        return ((PanelEditTraceability) this.getPanel("panelEditTraceability")).getPanelBaseElements();
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return this.traceability;
    }
}
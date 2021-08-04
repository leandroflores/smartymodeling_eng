package view.modal.traceability;

import controller.view.modal.traceability.ControllerViewTraceabilityOptions;
import java.awt.Dimension;
import javax.swing.JButton;
import model.structural.base.Project;
import model.structural.base.traceability.Traceability;
import view.main.structural.ViewMenu;
import view.modal.ViewModal;
import view.panel.base.requirement.traceability.PanelTraceabilityOptions;
import view.style.ViewStyle;

/**
 * <p>Class of View <b>ViewTraceabilityOptions</b>.</p>
 * <p>Class responsible for defining a <b>Traceability Options View</b> of SMartyModeling.</p>
 * @author Renan
 * @since  2021-06-28
 * @see    controller.view.modal.traceability.ControllerViewTraceabilityOptions
 * @see    model.structural.base.traceability.Traceability
 * @see    view.modal.ViewModal
 */
public final class ViewTraceabilityOptions extends ViewModal {
    protected final ViewMenu view;
    protected final Traceability traceability;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param traceability Traceability.
     */
    public ViewTraceabilityOptions(ViewMenu view, Traceability traceability) {
        super(view);
        this.view         = view;
        this.traceability = traceability;            
        this.controller   = new ControllerViewTraceabilityOptions(this);
        initComponents();
    }

    @Override
    public void initComponents() {
        setTitle(ViewStyle.SYSTEM + "TraceabilityOptions");
        setSize(new Dimension(500, 425));
        addHeader();
        addComponents();
        addFooter();
    }

    @Override
    public void addHeader() {
        addLines(1);
        add(createLabel("Requirements for Traceability"));
        addLines(1);
        add(createLabel("Owner: ", 75));
        add(createTextFieldNoEditable("owner", traceability.getOwner().toString(), 20));
        addLines(1);
    }
        
    @Override
    public void addComponents() {
        addPanel("option", new PanelTraceabilityOptions(view.getProject(), traceability));
        add(getPanelOptions());
    }
    
    @Override
    public void addFooter() {
        addLines(1);
        add(createButton("save",   "   Save   ", "save"));
        add(createButton("cancel", "  Cancel  ", "cancel"));
        addLines(1);
    }
    
    /**
     * Method responsible for returning the Panel Options.
     * @return Panel Options.
     */
    public PanelTraceabilityOptions getPanelOptions(){
        return (PanelTraceabilityOptions) getPanel("option");
    }
    
    /**
     * Method responsible for returning the Button Save.
     * @return Button Save.
     */
    public JButton getButtonSave(){
        return getButton("save");
    }
    
    /**
     * Method responsible for returning the Button Cancel.
     * @return Button Cancel.
     */
    public JButton getButtonCancel(){
        return getButton("cancel");
    }
    
    /**
     * Method responsible for returning the Traceability.
     * @return Traceability.
     */
    public Traceability getTraceability() {
        return traceability;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject(){
        return view.getProject();
    }
}
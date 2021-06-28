package view.panel.base.traceability;

import controller.view.panel.base.traceability.ControllerPanelBaseTraceability;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.traceability.Traceability;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseTraceability</b>.</p>
 * <p>Class responsible for defining a <b>Traceability Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBaseTraceability
 * @see    model.structural.base.traceability.Traceability
 * @see    view.panel.base.traceability.PanelBase
 */
public final class PanelBaseTraceability extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param traceability Traceability.
     */
    public PanelBaseTraceability(ViewMenu view, Traceability traceability) {
        super(view, traceability);
        controller = new ControllerPanelBaseTraceability(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(2, 2));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", traceability.getName(), 15));
        
        add(createLabel("Description*: "));
        add(createTextField("description", traceability.getDescription(), 15));
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Name Description Field.
     * @return Description Text Field.
     */
    public JTextField getDescriptionTextField() {
        return getTextField("description");
    }
}
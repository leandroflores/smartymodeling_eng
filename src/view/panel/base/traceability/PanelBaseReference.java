package view.panel.base.traceability;

import controller.view.panel.base.traceability.ControllerPanelBaseReference;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.traceability.Reference;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseReference</b>.</p>
 * <p>Class responsible for defining a <b>Reference Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-22
 * @see    controller.view.panel.base.traceability.ControllerPanelBaseReference
 * @see    model.structural.base.traceability.Reference
 * @see    view.panel.base.traceability.PanelBase
 */
public final class PanelBaseReference extends PanelBase {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param reference Reference.
     */
    public PanelBaseReference(ViewMenu view, Reference reference) {
        super(view, reference);
        controller = new ControllerPanelBaseReference(this);
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
        add(createTextField("name", reference.getName(), 15));
        
        add(createLabel("Description*: "));
        add(createTextField("description", reference.getDescription(), 15));
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
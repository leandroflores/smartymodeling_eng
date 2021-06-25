package view.panel.base.diagram;

import controller.view.panel.base.diagram.ControllerPanelBaseAssociation;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import model.structural.base.association.Association;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAssociation</b>.</p>
 * <p>Class responsible for defining a <b>Association Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-07
 * @see    controller.view.panel.base.diagram.ControllerPanelBaseAssociation
 * @see    model.structural.base.association.Association
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseAssociation extends view.panel.base.PanelBaseAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     * @param association Association.
     */
    public PanelBaseAssociation(ViewMenu view, Diagram diagram, Association association) {
        super(view, diagram, association);
        controller = new ControllerPanelBaseAssociation(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Source: ", 120));
        add(createTextFieldNoEditable("source", getAssociation().getSource().getName(), 20));
        
        add(createLabel("Target: ", 120));
        add(createTextFieldNoEditable("target", getAssociation().getTarget().getName(), 20));
        
        add(createLabel("Type: ", 120));
        add(createTextFieldNoEditable("type", getAssociation().getType(), 20));
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return getTextField("source");
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return getTextField("target");
    }
    
    /**
     * Method responsible for returning the Type Text Field.
     * @return Type Text Field.
     */
    public JTextField getTypeTextField() {
        return getTextField("type");
    }
}
package view.panel.base;

import controller.view.panel.base.ControllerPanelBaseDiagram;
import java.awt.GridLayout;
import javax.swing.JTextField;
import model.structural.base.Diagram;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseDiagram</b>.</p>
 * <p>Class responsible for defining a <b>Diagram Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-29
 * @see    controller.view.panel.base.ControllerPanelBaseDiagram
 * @see    model.structural.base.Diagram
 * @see    view.panel.base.PanelBase
 */
public final class PanelBaseDiagram extends PanelBase {
    private final Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Diagram.
     */
    public PanelBaseDiagram(ViewMenu view, Diagram diagram) {
        super(view);
        this.diagram    = diagram;
        this.controller = new ControllerPanelBaseDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 2));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.diagram.getName(), 15));
        
        this.add(this.createLabel("Type: "));
        this.add(this.createTextFieldNoEditable("typeTextField", this.diagram.getType(), 15));
    }
    
    /**
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning Type Text Field.
     * @return Type Text Field.
     */
    public JTextField getTypeTextField() {
        return this.getTextField("typeTextField");
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
    }
}
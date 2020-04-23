package view.panel.base.diagram.sequence.base;

import controller.view.panel.base.diagram.sequence.base.ControllerPanelBaseLifelineUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.LifelineUML;
import view.panel.base.PanelBaseElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseLifelineUML</b>.</p>
 * <p>Class responsible for defining the <b>Lifeline UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-03
 * @see    controller.view.panel.base.diagram.sequence.base.ControllerPanelBaseLifelineUML
 * @see    model.structural.diagram.sequence.base.LifelineUML
 * @see    view.panel.base.PanelBaseElement
 */
public final class PanelBaseLifelineUML extends PanelBaseElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     * @param lifeline Lifeline UML.
     */
    public PanelBaseLifelineUML(ViewMenu view, SequenceDiagram diagram, LifelineUML lifeline) {
        super(view, diagram, lifeline);
        this.controller = new ControllerPanelBaseLifelineUML(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setActorUML();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(3, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.getElement().getName(), 25));
        
        this.add(this.createLabel("Actor: "));
        this.add(this.createComboBox("actorComboBox", this.getProject().getElements("actor").toArray(), 30));
        this.getActorComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Mandatory: "));
        this.add(this.createCheckBox("mandatoryCheckBox", "", this.getElement().isMandatory()));
    }
    
    /**
     * Method responsible for setting the Actor UML.
     */
    private void setActorUML() {
        if (this.getElement().getActor() != null)
            this.getActorComboBox().setSelectedItem(this.getElement().getActor());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Actor Combo Box.
     * @return Actor Combo Box.
     */
    public JComboBox getActorComboBox() {
        return this.getComboBox("actorComboBox");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return this.getCheckBox("mandatoryCheckBox");
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.diagram;
    }
    
    @Override
    public LifelineUML getElement() {
        return (LifelineUML) this.element;
    }
}
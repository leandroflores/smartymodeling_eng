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
        controller = new ControllerPanelBaseLifelineUML(this);
        setDefaultProperties();
        addComponents();
        setActorUML();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        setLayout(new GridLayout(3, 2, 2, 5));
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Name*: "));
        add(createTextField("name", getElement().getName(), 25));
        
        add(createLabel("Actor: "));
        add(createComboBox("actor", getProject().getElements("actor").toArray(), 30));
        getActorComboBox().setPreferredSize(new Dimension(325, 30));
        
        add(createLabel("Mandatory: "));
        add(createCheckBox("mandatory", "", getElement().isMandatory()));
    }
    
    /**
     * Method responsible for setting the Actor UML.
     */
    private void setActorUML() {
        if (getElement().getActor() != null)
            getActorComboBox().setSelectedItem(getElement().getActor());
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Actor Combo Box.
     * @return Actor Combo Box.
     */
    public JComboBox getActorComboBox() {
        return getComboBox("actor");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return getCheckBox("mandatory");
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
    
    @Override
    public LifelineUML getElement() {
        return (LifelineUML) element;
    }
}
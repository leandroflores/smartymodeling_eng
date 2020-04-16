package view.edit.panel.base.sequence;

import controller.view.edit.panel.base.sequence.ControllerPanelBaseLifelineUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.sequence.base.LifelineUML;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseLifelineUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Lifeline UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/10/2019
 * @see    controller.view.edit.panel.base.sequence.ControllerPanelBaseLifelineUML
 * @see    model.structural.diagram.sequence.base.LifelineUML
 * @see    view.Panel
 */
public final class PanelBaseLifelineUML extends Panel {
    private final ViewMenu viewMenu;
    private final SequenceDiagram diagram;
    private final LifelineUML lifelineUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Sequence Diagram.
     * @param lifelineUML Lifeline UML.
     */
    public PanelBaseLifelineUML(ViewMenu viewMenu, SequenceDiagram diagram, LifelineUML lifelineUML) {
        this.viewMenu    = viewMenu;
        this.diagram     = diagram;
        this.lifelineUML = lifelineUML;
        this.controller   = new ControllerPanelBaseLifelineUML(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(3, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.lifelineUML.getName(), 25));
        
        this.add(this.createLabel("Actor: "));
        this.add(this.createComboBox("actorComboBox", this.diagram.getProject().getElements("actor").toArray(), 30));
        this.getActorComboBox().setPreferredSize(new Dimension(325, 30));
        
        this.add(this.createLabel("Mandatory: "));
        this.add(this.createCheckBox("mandatoryCheckBox", "", this.lifelineUML.isMandatory()));
    }
    
    /**
     * Method responsible for setting the Lifeline Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.lifelineUML.getName());
        this.setActorUML();
        this.getMandatoryCheckBox().setSelected(this.lifelineUML.isMandatory());
    }
    
    /**
     * Method responsible for setting the Actor UML.
     */
    private void setActorUML() {
        if (this.lifelineUML.getActor() != null)
            this.getActorComboBox().setSelectedItem(this.lifelineUML.getActor());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Sequence Diagram.
     * @return Sequence Diagram.
     */
    public SequenceDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Lifeline UML.
     * @return Lifeline UML.
     */
    public LifelineUML getLifelineUML() {
        return this.lifelineUML;
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
}
package view.edit.panel.base.sequence;

import controller.view.edit.panel.base.sequence.ControllerPanelBaseMessageUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.sequence.base.InstanceUML;
import model.structural.diagram.sequence.base.LifelineUML;
import model.structural.diagram.sequence.base.association.MessageUML;
import view.panel.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMessageUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Message UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/10/2019
 * @see    controller.view.edit.panel.base.sequence.ControllerPanelBaseMessageUML
 * @see    model.structural.diagram.sequence.base.association.MessageUML
 * @see    view.panel.Panel
 */
public final class PanelBaseMessageUML extends Panel {
    private final ViewMenu viewMenu;
    private final SequenceDiagram diagram;
    private final MessageUML messageUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Sequence Diagram.
     * @param messageUML Message UML.
     */
    public PanelBaseMessageUML(ViewMenu viewMenu, SequenceDiagram diagram, MessageUML messageUML) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.messageUML = messageUML;
        this.controller = new ControllerPanelBaseMessageUML(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(4, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: "));
        this.add(this.createTextFieldNoEditable("sourceTextField", "", 25));
        
        this.add(this.createLabel("Target: "));
        this.add(this.createTextFieldNoEditable("targetTextField", "", 25));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.messageUML.getName(), 25));
        
        this.add(this.createLabel("Method: "));
        this.add(this.createComboBox("methodComboBox", this.getMethodsList().toArray(), 30));
        this.getMethodComboBox().setPreferredSize(new Dimension(325, 30));
    }
    
    /**
     * Method responsible for returning the Methods List.
     * @return Methods List.
     */
    private List<MethodUML> getMethodsList() {
        if (this.messageUML.getTarget() instanceof InstanceUML)
            return this.getMethodsList((InstanceUML) this.messageUML.getTarget());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Methods List by Instance UML.
     * @param  instance Instance UML.
     * @return Methods List.
     */
    private List<MethodUML> getMethodsList(InstanceUML instance) {
        if (instance.getClassUML() != null)
            return new ArrayList<>(((InstanceUML) this.messageUML.getTarget()).getClassUML().getAllMethods());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for setting the Lifeline Values.
     */
    public void setValues() {
        this.setSource();
        this.setTarget();
        this.getNameTextField().setText(this.messageUML.getName());
        this.setMethodUML();
    }
    
    /**
     * Method responsible for setting the Source.
     */
    private void setSource() {
        if (this.messageUML.getSource() instanceof LifelineUML)
            this.getSourceTextField().setText(((LifelineUML) this.messageUML.getSource()).getSignature());
        else 
            this.getSourceTextField().setText(((InstanceUML) this.messageUML.getSource()).getSignature());
    }
    
    /**
     * Method responsible for setting the Target.
     */
    private void setTarget() {
        if (this.messageUML.getTarget() instanceof LifelineUML)
            this.getTargetTextField().setText(((LifelineUML) this.messageUML.getTarget()).getSignature());
        else 
            this.getTargetTextField().setText(((InstanceUML) this.messageUML.getTarget()).getSignature());
    }
    
    /**
     * Method responsible for setting the Method UML.
     */
    private void setMethodUML() {
        if (this.messageUML.getMethod() != null)
            this.getMethodComboBox().setSelectedItem(this.messageUML.getMethod());
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
     * Method responsible for returning the Message UML.
     * @return Message UML.
     */
    public MessageUML getMessageUML() {
        return this.messageUML;
    }
    
    /**
     * Method responsible for returning the Source Text Field.
     * @return Source Text Field.
     */
    public JTextField getSourceTextField() {
        return this.getTextField("sourceTextField");
    }
    
    /**
     * Method responsible for returning the Target Text Field.
     * @return Target Text Field.
     */
    public JTextField getTargetTextField() {
        return this.getTextField("targetTextField");
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.getTextField("nameTextField");
    }
    
    /**
     * Method responsible for returning the Method Combo Box.
     * @return Method Combo Box.
     */
    public JComboBox getMethodComboBox() {
        return this.getComboBox("methodComboBox");
    }
}
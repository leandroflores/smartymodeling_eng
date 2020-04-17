package view.panel.base.diagram.sequence.base.association;

import controller.view.panel.base.diagram.sequence.base.association.ControllerPanelBaseMessageUML;
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
import view.panel.base.PanelBaseAssociation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseMessageUML</b>.</p>
 * <p>Class responsible for defining a <b>Message UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-04
 * @see    controller.view.panel.base.diagram.sequence.base.association.ControllerPanelBaseMessageUML
 * @see    model.structural.diagram.sequence.base.association.MessageUML
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseMessageUML extends PanelBaseAssociation {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Sequence Diagram.
     * @param message Message UML.
     */
    public PanelBaseMessageUML(ViewMenu view, SequenceDiagram diagram, MessageUML message) {
        super(view, diagram, message);
        this.controller = new ControllerPanelBaseMessageUML(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setValues();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(4, 2, 2, 5));
        super.setDefaultProperties();
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: "));
        this.add(this.createTextFieldNoEditable("sourceTextField", "", 25));
        
        this.add(this.createLabel("Target: "));
        this.add(this.createTextFieldNoEditable("targetTextField", "", 25));
        
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.getAssociation().getName(), 25));
        
        this.add(this.createLabel("Method: "));
        this.add(this.createComboBox("methodComboBox", this.getMethodsList().toArray(), 30));
        this.getMethodComboBox().setPreferredSize(new Dimension(325, 30));
    }
    
    /**
     * Method responsible for returning the Methods List.
     * @return Methods List.
     */
    private List<MethodUML> getMethodsList() {
        if (this.getAssociation().getTarget() instanceof InstanceUML)
            return this.getMethodsList((InstanceUML) this.getAssociation().getTarget());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Methods List by Instance UML.
     * @param  instance Instance UML.
     * @return Methods List.
     */
    private List<MethodUML> getMethodsList(InstanceUML instance) {
        if (instance.getClassUML() != null)
            return new ArrayList<>(((InstanceUML) this.getAssociation().getTarget()).getClassUML().getAllMethods());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for setting the Lifeline UML Values.
     */
    public void setValues() {
        this.setSource();
        this.setTarget();
        this.getNameTextField().setText(this.getAssociation().getName());
        this.setMethodUML();
    }
    
    /**
     * Method responsible for setting the Source.
     */
    private void setSource() {
        if (this.getAssociation().getSource() instanceof LifelineUML)
            this.getSourceTextField().setText(((LifelineUML) this.getAssociation().getSource()).getSignature());
        else 
            this.getSourceTextField().setText(((InstanceUML) this.getAssociation().getSource()).getSignature());
    }
    
    /**
     * Method responsible for setting the Target.
     */
    private void setTarget() {
        if (this.getAssociation().getTarget() instanceof LifelineUML)
            this.getTargetTextField().setText(((LifelineUML) this.getAssociation().getTarget()).getSignature());
        else 
            this.getTargetTextField().setText(((InstanceUML) this.getAssociation().getTarget()).getSignature());
    }
    
    /**
     * Method responsible for setting the Method UML.
     */
    private void setMethodUML() {
        if (this.getAssociation().getMethod() != null)
            this.getMethodComboBox().setSelectedItem(this.getAssociation().getMethod());
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
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) this.diagram;
    }
    
    @Override
    public MessageUML getAssociation() {
        return (MessageUML) this.association;
    }
}
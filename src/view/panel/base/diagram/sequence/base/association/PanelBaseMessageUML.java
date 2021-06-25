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
import view.main.structural.ViewMenu;

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
        controller = new ControllerPanelBaseMessageUML(this);
        setDefaultProperties();
        addComponents();
        setValues();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        super.setDefaultProperties();
        setLayout(new GridLayout(4, 2, 2, 5));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Source: "));
        add(createTextFieldNoEditable("source", "", 25));
        
        add(createLabel("Target: "));
        add(createTextFieldNoEditable("target", "", 25));
        
        add(createLabel("Name*: "));
        add(createTextField("name", getAssociation().getName(), 25));
        
        add(createLabel("Method: "));
        add(createComboBox("method", getMethodsList().toArray(), 30));
        getMethodComboBox().setPreferredSize(new Dimension(325, 30));
    }
    
    /**
     * Method responsible for returning the Methods List.
     * @return Methods List.
     */
    private List<MethodUML> getMethodsList() {
        if (getAssociation().getTarget() instanceof InstanceUML)
            return getMethodsList((InstanceUML) getAssociation().getTarget());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Methods List by Instance UML.
     * @param  instance Instance UML.
     * @return Methods List.
     */
    private List<MethodUML> getMethodsList(InstanceUML instance) {
        if (instance.getClassUML() != null)
            return new ArrayList<>(((InstanceUML) getAssociation().getTarget()).getClassUML().getAllMethods());
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for setting the Lifeline UML Values.
     */
    public void setValues() {
        setSource();
        setTarget();
        getNameTextField().setText(getAssociation().getName());
        setMethodUML();
    }
    
    /**
     * Method responsible for setting the Source.
     */
    private void setSource() {
        if (getAssociation().getSource() instanceof LifelineUML)
            getSourceTextField().setText(((LifelineUML) getAssociation().getSource()).getSignature());
        else 
            getSourceTextField().setText(((InstanceUML) getAssociation().getSource()).getSignature());
    }
    
    /**
     * Method responsible for setting the Target.
     */
    private void setTarget() {
        if (getAssociation().getTarget() instanceof LifelineUML)
            getTargetTextField().setText(((LifelineUML) getAssociation().getTarget()).getSignature());
        else 
            getTargetTextField().setText(((InstanceUML) getAssociation().getTarget()).getSignature());
    }
    
    /**
     * Method responsible for setting the Method UML.
     */
    private void setMethodUML() {
        if (getAssociation().getMethod() != null)
            getMethodComboBox().setSelectedItem(getAssociation().getMethod());
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
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return getTextField("name");
    }
    
    /**
     * Method responsible for returning the Method Combo Box.
     * @return Method Combo Box.
     */
    public JComboBox getMethodComboBox() {
        return getComboBox("method");
    }
    
    @Override
    public SequenceDiagram getDiagram() {
        return (SequenceDiagram) diagram;
    }
    
    @Override
    public MessageUML getAssociation() {
        return (MessageUML) association;
    }
}
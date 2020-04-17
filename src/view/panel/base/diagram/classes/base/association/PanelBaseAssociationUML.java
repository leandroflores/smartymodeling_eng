package view.panel.base.diagram.classes.base.association;

import controller.view.panel.base.diagram.classes.base.association.ControllerPanelBaseAssociationUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.panel.base.PanelBaseAssociation;
import view.panel.edit.diagram.classes.base.association.PanelEditAssociationUML;

/**
 * <p>Class of View <b>PanelBaseAssociationUML</b>.</p> 
 * <p>Class responsible for defining a <b>Association UML Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-15
 * @see    controller.view.panel.base.diagram.classes.base.association.ControllerPanelBaseAssociationUML
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.panel.base.PanelBaseAssociation
 */
public final class PanelBaseAssociationUML extends PanelBaseAssociation {
    private final PanelEditAssociationUML panelEdit;
    
    /**
     * Default constructor method of Class.
     * @param panel View Edit Association UML.
     * @param diagram Class Diagram.
     * @param association Association UML.
     */
    public PanelBaseAssociationUML(PanelEditAssociationUML panel, ClassDiagram diagram, AssociationUML association) {
        super(panel.getViewMenu(), diagram, association);
        this.panelEdit  = panel;
        this.controller = new ControllerPanelBaseAssociationUML(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        this.setLayout(new GridLayout(5, 2));
        super.setDefaultProperties();
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: ", 120));
        this.add(this.createTextFieldNoEditable("sourceTextField", this.getAssociation().getSource().getName(), 20));
        
        this.add(this.createLabel("Target: ", 120));
        this.add(this.createTextFieldNoEditable("targetTextField", this.getAssociation().getTarget().getName(), 20));
        
        this.add(this.createLabel("Name: ", 120));
        this.add(this.createTextField("nameTextField", this.getAssociation().getName(), 20));
        
        this.add(this.createLabel("Category: ", 120));
        this.add(this.createTextFieldNoEditable("categoryTextField", this.getAssociation().getCategory(), 20));
        
        this.add(this.createLabel("Directed: ", 120));
        this.add(this.createCheckBox("directedCheckBox", "Yes", this.getAssociation().isDirection()));
    }
    
    /**
     * Method responsible for updating the Panel Base Source.
     */
    public void updatePanelBaseSource() {
        this.panelEdit.getPanelBaseSource().updateValues();
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
     * Method responsible for returning the Category Text Field.
     * @return Category Text Field.
     */
    public JTextField getCategoryTextField() {
        return this.getTextField("categoryTextField");
    }
    
    /**
     * Method responsible for returning the Directed Check Box.
     * @return Directed Check Box.
     */
    public JCheckBox getDirectedCheckBox() {
        return this.getCheckBox("directedCheckBox");
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public AssociationUML getAssociation() {
        return (AssociationUML) this.association;
    }
}
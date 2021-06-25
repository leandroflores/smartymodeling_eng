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
        panelEdit  = panel;
        controller = new ControllerPanelBaseAssociationUML(this);
        setDefaultProperties();
        addComponents();
        getController().setReady();
    }
    
    @Override
    protected void setDefaultProperties() {
        super.setDefaultProperties();
        setLayout(new GridLayout(5, 2));
        setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        add(createLabel("Source: ", 120));
        add(createTextFieldNoEditable("source", getAssociation().getSource().getName(), 20));
        
        add(createLabel("Target: ", 120));
        add(createTextFieldNoEditable("target", getAssociation().getTarget().getName(), 20));
        
        add(createLabel("Name: ", 120));
        add(createTextField("name", getAssociation().getName(), 20));
        
        add(createLabel("Category: ", 120));
        add(createTextFieldNoEditable("category", getAssociation().getCategory(), 20));
        
        add(createLabel("Directed: ", 120));
        add(createCheckBox("directed", "Yes", getAssociation().isDirection()));
    }
    
    /**
     * Method responsible for updating the Panel Base Source.
     */
    public void updatePanelBaseSource() {
        panelEdit.getPanelBaseSource().updateValues();
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
     * Method responsible for returning the Category Text Field.
     * @return Category Text Field.
     */
    public JTextField getCategoryTextField() {
        return getTextField("category");
    }
    
    /**
     * Method responsible for returning the Directed Check Box.
     * @return Directed Check Box.
     */
    public JCheckBox getDirectedCheckBox() {
        return getCheckBox("directed");
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
    
    @Override
    public AssociationUML getAssociation() {
        return (AssociationUML) association;
    }
}
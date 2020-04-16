package view.edit.panel.base.classes.association;

import controller.view.edit.panel.base.classes.association.ControllerPanelBaseAssociationUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.Panel;
import view.panel.edit.base.classes.association.PanelEditAssociationUML;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseAssociationUML</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Association UML Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  15/11/2019
 * @see    controller.view.edit.panel.base.classes.association.ControllerPanelBaseAssociationUML
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.Panel
 */
public final class PanelBaseAssociationUML extends Panel {
    private final ViewMenu viewMenu;
    private final PanelEditAssociationUML panelEdit;
    private final ClassDiagram diagram;
    private final AssociationUML associationUML;
    
    /**
     * Default constructor method of Class.
     * @param panelEdit View Edit Association UML.
     * @param diagram Class Diagram.
     * @param associationUML Association UML.
     */
    public PanelBaseAssociationUML(PanelEditAssociationUML panelEdit, ClassDiagram diagram, AssociationUML associationUML) {
        this.viewMenu       = panelEdit.getViewMenu();
        this.panelEdit      = panelEdit;
        this.diagram        = diagram;
        this.associationUML = associationUML;
        this.controller     = new ControllerPanelBaseAssociationUML(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2));
        this.setPreferredSize(new Dimension(50, 50));
        this.setSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Source: ", 120));
        this.add(this.createTextFieldNoEditable("sourceTextField", "", 20));
        
        this.add(this.createLabel("Target: ", 120));
        this.add(this.createTextFieldNoEditable("targetTextField", "", 20));
        
        this.add(this.createLabel("Name: ", 120));
        this.add(this.createTextField("nameTextField", "", 20));
        
        this.add(this.createLabel("Category: ", 120));
        this.add(this.createTextFieldNoEditable("categoryTextField", "", 20));
        
        this.add(this.createLabel("Directed: ", 120));
        this.add(this.createCheckBox("directedCheckBox", "Yes", this.associationUML.isDirection()));
    }
    
    /**
     * Method responsible for updating the Panel Base Source.
     */
    public void updatePanelBaseSource() {
        this.panelEdit.getPanelBaseSource().updateValues();
    }
    
    /**
     * Method responsible for setting the Association UML Values.
     */
    public void setValues() {
        this.getSourceTextField().setText(this.associationUML.getSource().getName());
        this.getTargetTextField().setText(this.associationUML.getTarget().getName());
        this.getNameTextField().setText(this.associationUML.getName());
        this.getCategoryTextField().setText(this.associationUML.getCategory());
        this.getDirectedCheckBox().setSelected(this.associationUML.isDirection());
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Association UML.
     * @return Association UML.
     */
    public AssociationUML getAssociationUML() {
        return this.associationUML;
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
}
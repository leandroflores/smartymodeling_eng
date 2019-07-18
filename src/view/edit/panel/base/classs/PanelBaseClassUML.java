package view.edit.panel.base.classs;

import controller.view.edit.panel.base.classs.ControllerPanelBaseClassUML;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.ClassUML;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelBaseClassUML</b>.</p> 
 * <p>Class responsible for defining a Base Panel for the <b>Class UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  17/06/2019
 * @see    controller.view.edit.panel.base.classs.
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    view.Panel
 */
public final class PanelBaseClassUML extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final ClassUML classUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param classUML Class UML.
     */
    public PanelBaseClassUML(ViewMenu viewMenu, ClassDiagram diagram, ClassUML classUML) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.classUML   = classUML;
        this.controller = new ControllerPanelBaseClassUML(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(5, 2, 2, 5));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Name*: "));
        this.add(this.createTextField("nameTextField", this.classUML.getName(), 25));
        
        this.add(this.createLabel("Abstract: "));
        this.add(this.createCheckBox("abstractCheckBox",  "", this.classUML.isAbstract()));
        
        this.add(this.createLabel("Final: "));
        this.add(this.createCheckBox("finalCheckBox",     "", this.classUML.isFinal()));
        
        this.add(this.createLabel("Mandatory: "));
        this.add(this.createCheckBox("mandatoryCheckBox", "", this.classUML.isMandatory()));
    }
    
    /**
     * Method responsible for setting the Class UML Values.
     */
    public void setValues() {
        this.getNameTextField().setText(this.classUML.getName());
        this.getAbstractCheckBox().setSelected(this.classUML.isAbstract());
        this.getFinalCheckBox().setSelected(this.classUML.isFinal());
        this.getMandatoryCheckBox().setSelected(this.classUML.isMandatory());
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
     * Method responsible for returning the Class UML.
     * @return Class UML.
     */
    public ClassUML getClassUML() {
        return this.classUML;
    }
    
    /**
     * Method responsible for returning the Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning the Abstract Check Box.
     * @return Abstract Check Box.
     */
    public JCheckBox getAbstractCheckBox() {
        return this.checkBoxes.get("abstractCheckBox");
    }
    
    /**
     * Method responsible for returning the Final Check Box.
     * @return Final Check Box.
     */
    public JCheckBox getFinalCheckBox() {
        return this.checkBoxes.get("finalCheckBox");
    }
    
    /**
     * Method responsible for returning the Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return this.checkBoxes.get("mandatoryCheckBox");
    }
}
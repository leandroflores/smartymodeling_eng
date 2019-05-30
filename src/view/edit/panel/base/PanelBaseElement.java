package view.edit.panel.base;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import view.Panel;

/**
 * <p>Class of View <b>PanelBaseElement</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Element Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  30/05/2019
 * @see    view.Panel
 */
public final class PanelBaseElement extends Panel {
    
    /**
     * Default constructor method of Class.
     */
    public PanelBaseElement() {
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setPreferredSize(new Dimension(300, 100));
    }
    
    @Override
    protected void addComponents() {
        this.addLines(1);
        
        this.add(this.createLabel("Name*: ", 120));
        this.add(this.createTextField("nameTextField", "", 20));
        
        this.addLines(1);
        
        this.add(this.createLabel("Mandatory: ", 120));
        this.add(this.createCheckBox("mandatoryCheckBox", ""));
        this.add(this.createLabel("", 250));
        
        this.addLines(1);
    }
    
    /**
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
    
    /**
     * Method responsible for returning Mandatory Check Box.
     * @return Mandatory Check Box.
     */
    public JCheckBox getMandatoryCheckBox() {
        return this.checkBoxes.get("mandatoryCheckBox");
    }
}
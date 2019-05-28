package view.edit.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import view.Panel;

/**
 * <p>Class of Panel <b>PanelProject</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Project</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    view.Panel
 */
public final class PanelProject extends Panel {
    
    /**
     * Default constructor method of Class.
     */
    public PanelProject() {
        this.setSettings();
        this.addComponents();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(0, 2));
        this.setSize(200, 200);
        this.setPreferredSize(new Dimension(400, 50));
    }
    
    @Override
    protected void addComponents() {
        this.add(this.createLabel("Path*: ", 120));
        this.add(this.createTextFieldNoEditable("pathTextField", "", 30));

        this.add(this.createLabel("Name*: ", 120));
        this.add(this.createTextField("nameTextField", "", 30));
    }
    
    /**
     * Method responsible for returning Path Text Field.
     * @return Path Text Field.
     */
    public JTextField getPathTextField() {
        return this.textFields.get("pathTextField");
    }
    
    /**
     * Method responsible for returning Name Text Field.
     * @return Name Text Field.
     */
    public JTextField getNameTextField() {
        return this.textFields.get("nameTextField");
    }
}
package view.edit.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import view.Panel;

/**
 * <p>Class of View <b>PanelDataProject</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Data Project</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    view.Panel
 */
public final class PanelDataProject extends Panel {
    
    /**
     * Default constructor method of Class.
     */
    public PanelDataProject() {
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
        
        this.add(this.createLabel("Path*: ", 80));
        this.add(this.createTextFieldNoEditable("pathTextField", "", 15));

        this.addLines(1);
        
        this.add(this.createLabel("Name*: ", 80));
        this.add(this.createTextField("nameTextField", "", 15));
        
        this.addLines(1);
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
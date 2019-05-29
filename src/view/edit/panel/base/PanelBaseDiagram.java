package view.edit.panel.base;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import view.Panel;

/**
 * <p>Class of View <b>PanelBaseDiagram</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Diagram Base</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  29/05/2019
 * @see    view.Panel
 */
public final class PanelBaseDiagram extends Panel {
    
    /**
     * Default constructor method of Class.
     */
    public PanelBaseDiagram() {
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
        
        this.add(this.createLabel("Name*: ", 80));
        this.add(this.createTextField("nameTextField", "", 15));
        
        this.addLines(1);
        
        this.add(this.createLabel("Type: ",  80));
        this.add(this.createTextFieldNoEditable("typeTextField", "", 15));
        
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
     * Method responsible for returning Type Text Field.
     * @return Type Text Field.
     */
    public JTextField getTypeTextField() {
        return this.textFields.get("typeTextField");
    }
}
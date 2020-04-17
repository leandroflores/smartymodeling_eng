package controller.view.panel;

import controller.Controller;
import funct.FunctDate;
import funct.FunctString;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.JTextComponent;
import view.panel.Panel;

/**
 * <p>Class of Controller <b>ControllerPanel</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the Panels of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.Controller
 * @see    view.panel.Panel
 */
public abstract class ControllerPanel extends Controller {
    protected final Panel panel;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel.
     */
    public ControllerPanel(Panel panel) {
        this.panel = panel;
    }
    
    @Override
    public abstract void actionPerformed(ActionEvent event);
    
    /**
     * Method responsible for returning the Default Color.
     * @return Default Color.
     */
    protected Color getDefaultColor() {
        return this.panel.getBackground();
    }
    
    /**
     * Method responsible for returning the Focus Color.
     * @return Focus Color.
     */
    protected Color getFocusColor() {
        return ColorUIResource.LIGHT_GRAY;
    }
    
    /**
     * Method responsible for checking the Source Cardinality.
     * @param  value Source Cardinality.
     * @return Value is checked.
     */
    protected boolean checkCardinality(String value) {
        return    value.equals("*") 
               || value.matches("\\d") 
               || value.matches("\\d\\.\\.\\*") 
               || value.matches("\\d\\.\\.\\d");
    }
    
    /**
     * Method responsible for returning the Min by String Value.
     * @param  value String Value.
     * @return Min parsed.
     */
    protected Integer getMin(String value) {
        if (value.equals("*"))
            return 0;
        if (value.contains(".."))
            return Integer.parseInt(value.substring(0, value.indexOf(".")));
        return Integer.parseInt(value);
    }
    
    /**
     * Method responsible for returning the Max by String Value.
     * @param  value String Value.
     * @return Max parsed.
     */
    protected Integer getMax(String value) {
        if (value.equals("*"))
            return Integer.MAX_VALUE;
        if (value.contains(".."))
            return this.getValue(value.substring(value.lastIndexOf(".") + 1));
        return Integer.parseInt(value);
    }
    
    /**
     * Method responsible for returning the Integer by Value.
     * @param  value String Value.
     * @return Integer parsed.
     */
    protected Integer getValue(String value) {
        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException exception) {
            return Integer.MAX_VALUE;
        }
    }
    
    /**
     * Method responsible for returning the Double by Value.
     * @param  value String Value.
     * @return Double parsed.
     */
    protected Double getDoubleValue(String value) {
        try {
            return Double.parseDouble(value);
        }catch (NumberFormatException exception) {
            return 0.0d;
        }
    }
    
    /**
     * Method responsible for returning the Text of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return String of JTextComponent.
     */
    protected String getString(JTextComponent textComponent) {
        return new FunctString().removeSpecialChars(textComponent.getText().trim());
    }
    
    /**
     * Method responsible for returning the Date of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return Date of JTextComponent.
     */
    protected Date getDate(JTextField textComponent) {
        return new FunctDate().createDate(textComponent.getText().trim());
    }
    
    /**
     * Method responsible for returning the Integer of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return Integer of JTextComponent.
     */
    protected Integer getInteger(JTextComponent textComponent) {
        return Integer.parseInt(textComponent.getText().trim());
    }
    
    /**
     * Method responsible for returning the Double of JTextComponent.
     * @param  textComponent JTextComponent.
     * @return Double of JTextComponent.
     */
    protected Double getDouble(JTextComponent textComponent) {
        return Double.parseDouble(textComponent.getText().trim());
    }
    
    /**
     * Method responsible for returning the Value of JComboBox.
     * @param  comboBox JComboBox.
     * @return Value of JComboBox.
     */
    protected String getValue(JComboBox comboBox) {
        return comboBox.getSelectedItem().toString();
    }
    
    /**
     * Method responsible for returning the Panel.
     * @return Panel.
     */
    public Panel getPanel() {
        return this.panel;
    }
}
package controller.view;

import controller.Controller;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.plaf.ColorUIResource;
import view.Panel;

/**
 * <p>Class of Controller <b>ControllerPanel</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the Panels of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controller.Controller
 * @see    view.Panel
 */
public abstract class ControllerPanel extends Controller {
    private final Panel panel;
    
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
}
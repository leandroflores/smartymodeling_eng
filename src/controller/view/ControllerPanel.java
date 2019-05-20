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
}
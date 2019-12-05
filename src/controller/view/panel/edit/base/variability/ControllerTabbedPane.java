package controller.view.panel.edit.base.variability;

import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.edit.panel.base.variability.PanelBaseVariability;
import view.edit.panel.base.variability.PanelBaseVariants;

/**
 * <p>Class of Controller <b>ControllerTabbedPane</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Tabbed Pane</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/12/2019
 * @see    view.panel.diagram.types.PanelActivityDiagram
 */
public class ControllerTabbedPane implements ChangeListener {
    private final JTabbedPane tabbedPane;
    
    /**
     * Default constructor method of Class.
     * @param tabbedPane 
     */
    public ControllerTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }

    @Override
    public void stateChanged(ChangeEvent event) {
        Component component = tabbedPane.getSelectedComponent();
        if (component instanceof  JScrollPane) {
            JScrollPane scroll = (JScrollPane) component;
            Component   view   = scroll.getViewport().getView();
            if (view instanceof PanelBaseVariability)
                ((PanelBaseVariability) view).setValues();
            else if (view instanceof PanelBaseVariants)
                ((PanelBaseVariants) view).setValues();
        }
    }
}

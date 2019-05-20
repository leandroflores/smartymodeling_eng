package controller.view;

import controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import view.View;

/**
 * <p>Class of Controller <b>ControllerView</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the Views of SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    controlador.Controller
 * @see    visao.View
 */
public abstract class ControllerView extends Controller {
    private final View view;
    
    /**
     * Default constructor method of Class.
     * @param view View.
     */
    public ControllerView(View view) {
        this.view = view;
    }
    
    @Override
    public abstract void actionPerformed(ActionEvent event);

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ESC)
            this.view.dispose();
    }
}
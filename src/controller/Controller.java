package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * <p>Class of Controller <b>Controller</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the Views of SMartyModeling.</p>
 * @author Leandro
 * @since  19/05/2019
 * @see    java.awt.event.ActionListener
 * @see    java.awt.event.KeyListener
 */
public abstract class Controller implements ActionListener, KeyListener {
    protected static final int   SPACE  = KeyEvent.VK_SPACE;
    protected static final int   DELETE = KeyEvent.VK_DELETE;
    protected static final int   ENTER  = KeyEvent.VK_ENTER;
    protected static final int   ESC    = KeyEvent.VK_ESCAPE;
    protected static final int   F1     = KeyEvent.VK_F1;
    protected static final int   F2     = KeyEvent.VK_F2;
    protected static final int   F3     = KeyEvent.VK_F3;
    protected static final int   F4     = KeyEvent.VK_F4;
    protected static final int   F5     = KeyEvent.VK_F5;
    protected static final int   F6     = KeyEvent.VK_F6;
    protected static final int   F7     = KeyEvent.VK_F7;
    protected static final int   F8     = KeyEvent.VK_F8;
    protected static final int   F9     = KeyEvent.VK_F9;
    protected static final int   F10    = KeyEvent.VK_F10;
    protected static final int   F11    = KeyEvent.VK_F11;
    protected static final int   F12    = KeyEvent.VK_F12;
    protected static final int   DOWN   = KeyEvent.VK_DOWN;
    protected static final int   LEFT   = KeyEvent.VK_LEFT;
    protected static final int   RIGHT  = KeyEvent.VK_RIGHT;
    protected static final int   UP     = KeyEvent.VK_UP;
    
    /**
     * Abstract Method responsible for actions.
     * @param event Action Event.
     */
    @Override
    public abstract void actionPerformed(ActionEvent event);
    
    /**
     * Abstract Method responsible for actions from KeyBoard.
     * @param event Key Event.
     */
    @Override
    public abstract void keyPressed(KeyEvent event);

    @Override
    public void keyTyped(KeyEvent event) {}

    @Override
    public void keyReleased(KeyEvent event) {}
    
    /**
     * Method responsible for checking a required field.
     * @param  string String.
     * @return String checked.
     */
    protected boolean check(String string) {
        return string.trim().equals("") == false;
    }
    
    /**
     * Method responsible for checking a Year String.
     * @param  string String.
     * @return String checked.
     */
    protected boolean checkYear(String string) {
        return string.matches("\\d\\d\\d\\d");
    }
    
    /**
     * Method responsible for checking a Date String.
     * @param  string String.
     * @return String checked.
     */
    protected boolean checkDate(String string) {
        return string.matches("\\d{2}\\/\\d{2}\\/\\d{4}");
    }
    
    /**
     * Method responsible for checking a Number String.
     * @param  string String.
     * @return String checked.
     */
    protected boolean checkNumbers(String string) {
        return string.matches("\\d+");
    }
}
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>Class of Test <b>ControllerTest</b>.</p>
 * <p>Class responsible for testing the <b>Controller</b> class.</p>
 * @author Leandro
 * @date   2022-12-22
 * @see    controller.Controller
 */
public class ControllerTest {
    private static ControllerMock INSTANCE;
    
    public ControllerTest() {}
    
    @Before
    public void setUp() {
        INSTANCE = new ControllerMock();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Method responsible for testing the actionPerformed(ActionEvent) method.
     */
    @Test
    public void testActionPerformed() {
        ActionEvent event = null;
        Controller instance = new ControllerMock();
        instance.actionPerformed(event);
        assertNull(event);
    }

    /**
     * Method responsible for testing the keyPressed(KeyEvent) method.
     */
    @Test
    public void testKeyPressed() {
        KeyEvent event = null;
        Controller instance = new ControllerMock();
        instance.keyPressed(event);
        assertNull(event);
    }

    /**
     * Method responsible for testing the keyTyped(KeyEvent) method.
     */
    @Test
    public void testKeyTyped() {
        KeyEvent event = null;
        Controller instance = new ControllerMock();
        instance.keyTyped(event);
        assertNull(event);
    }

    /**
     * Method responsible for testing the keyReleased(KeyEvent) method.
     */
    @Test
    public void testKeyReleased() {
        KeyEvent event = null;
        Controller instance = new ControllerMock();
        instance.keyReleased(event);
        assertNull(event);
    }

    /**
     * Method responsible for testing the check(String) method.
     */
    @Test
    public void testCheck() {
        assertFalse(INSTANCE.check(""));
        assertFalse(INSTANCE.check(" "));
        assertTrue(INSTANCE.check(" a "));
        assertFalse(INSTANCE.check("    "));
        assertTrue(INSTANCE.check("string"));
    }

    /**
     * Method responsible for testing the checkYear(String) method.
     */
    @Test
    public void testCheckYear() {
        assertTrue(INSTANCE.checkYear("2000"));
        assertFalse(INSTANCE.checkYear("2"));
        assertTrue(INSTANCE.checkYear("2022"));
        assertFalse(INSTANCE.checkYear("202"));
        assertFalse(INSTANCE.checkYear("202A"));
        assertFalse(INSTANCE.checkYear("20222"));
        assertFalse(INSTANCE.checkYear("-2022"));
    }

    /**
     * Method responsible for testing the checkDate(String) method.
     */
    @Test
    public void testCheckDate() {
        assertTrue(INSTANCE.checkDate("20/01/2022"));
        assertFalse(INSTANCE.checkDate("2O/01/2010"));
        assertFalse(INSTANCE.checkDate("20/01/222"));
        assertFalse(INSTANCE.checkDate("20/01/22"));
        assertTrue(INSTANCE.checkDate("99/99/9999"));
        assertFalse(INSTANCE.checkDate("20-01-2012"));
    }

    /**
     * Method responsible for testing the checkUSDate(String) method.
     */
    @Test
    public void testCheckUSDate() {
        assertFalse(INSTANCE.checkUSDate("20/01/2022"));
        assertFalse(INSTANCE.checkUSDate("2O/01/2010"));
        assertTrue(INSTANCE.checkUSDate("2222-01-22"));
        assertFalse(INSTANCE.checkUSDate("20/01/22"));
        assertTrue(INSTANCE.checkUSDate("9999-99-99"));
    }

    /**
     * Method responsible for testing the checkNumbers(String) method.
     */
    @Test
    public void testCheckNumbers() {
        assertTrue(INSTANCE.checkNumbers("0"));
        assertFalse(INSTANCE.checkNumbers("-3"));
        assertFalse(INSTANCE.checkNumbers("0.43"));
        assertTrue(INSTANCE.checkNumbers("10"));
        assertTrue(INSTANCE.checkNumbers("180"));
    }

    public class ControllerMock extends Controller {

        @Override
        public void actionPerformed(ActionEvent event) {}

        @Override
        public void keyPressed(KeyEvent event) {}
        
        @Override
        public void keyTyped(KeyEvent event) {}

        @Override
        public void keyReleased(KeyEvent event) {}
    }
    
}

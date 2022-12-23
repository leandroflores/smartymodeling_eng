package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leandro
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
     * Test of actionPerformed method, of class Controller.
     */
    @Test
    public void testActionPerformed() {
        System.out.println("actionPerformed");
        ActionEvent event = null;
        Controller instance = new ControllerMock();
        instance.actionPerformed(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyPressed method, of class Controller.
     */
    @Test
    public void testKeyPressed() {
        System.out.println("keyPressed");
        KeyEvent event = null;
        Controller instance = new ControllerMock();
        instance.keyPressed(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyTyped method, of class Controller.
     */
    @Test
    public void testKeyTyped() {
        System.out.println("keyTyped");
        KeyEvent event = null;
        Controller instance = new ControllerMock();
        instance.keyTyped(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keyReleased method, of class Controller.
     */
    @Test
    public void testKeyReleased() {
        System.out.println("keyReleased");
        KeyEvent event = null;
        Controller instance = new ControllerMock();
        instance.keyReleased(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of check method, of class Controller.
     */
    @Test
    public void testCheck() {
        System.out.println("* ControllerTest: testCheck()");
        
        assertFalse(INSTANCE.check(""));
        assertFalse(INSTANCE.check(" "));
        assertTrue(INSTANCE.check(" a "));
        assertFalse(INSTANCE.check("    "));
        assertTrue(INSTANCE.check("string"));
    }

    /**
     * Test of checkYear method, of class Controller.
     */
    @Test
    public void testCheckYear() {
        System.out.println("* ControllerTest: testCheckYear()");
        
        assertTrue(INSTANCE.checkYear("2000"));
        assertFalse(INSTANCE.checkYear("2"));
        assertTrue(INSTANCE.checkYear("2022"));
        assertFalse(INSTANCE.checkYear("202"));
        assertFalse(INSTANCE.checkYear("202A"));
        assertFalse(INSTANCE.checkYear("20222"));
        assertFalse(INSTANCE.checkYear("-2022"));
    }

    /**
     * Test of checkDate method, of class Controller.
     */
    @Test
    public void testCheckDate() {
        System.out.println("* ControllerTest: testCheckDate()");
        
        assertTrue(INSTANCE.checkDate("20/01/2022"));
        assertFalse(INSTANCE.checkDate("2O/01/2010"));
        assertFalse(INSTANCE.checkDate("20/01/222"));
        assertFalse(INSTANCE.checkDate("20/01/22"));
        assertTrue(INSTANCE.checkDate("99/99/9999"));
        assertFalse(INSTANCE.checkDate("20-01-2012"));
    }

    /**
     * Test of checkUSDate method, of class Controller.
     */
    @Test
    public void testCheckUSDate() {
        System.out.println("checkUSDate");
        String string = "";
        Controller instance = new ControllerMock();
        boolean expResult = false;
        boolean result = instance.checkUSDate(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkNumbers method, of class Controller.
     */
    @Test
    public void testCheckNumbers() {
        System.out.println("checkNumbers");
        String string = "";
        Controller instance = new ControllerMock();
        boolean expResult = false;
        boolean result = instance.checkNumbers(string);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ControllerMock extends Controller {

        @Override
        public void actionPerformed(ActionEvent event) {}

        @Override
        public void keyPressed(KeyEvent event) {}
    }
    
}

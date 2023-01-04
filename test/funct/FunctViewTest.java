package funct;

import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>Class of Test <b>FunctViewTest</b>.</p>
 * <p>Class responsible for testing the <b>FunctView</b> class.</p>
 * @author Leandro
 * @date   2023-01-03
 * @see    funct.FunctView
 */
public class FunctViewTest {
    private static FunctView FUNCT_VIEW;
    
    public FunctViewTest() {
        FUNCT_VIEW = new FunctView();
    }
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Method responsible for returning the Icon Image.
     * @return Icon Image.
     */
    private ImageIcon createIconImage() {
        return 
            new ImageIcon(
                Toolkit.getDefaultToolkit().getImage(
                        getClass().getResource("/images/icon.png")
                )
            );
    } 
    
    /**
     * Method responsible for testing the createImage(String) method.
     */
    @Test
    public void testCreateImage() {
        ImageIcon icon = this.createIconImage();
        ImageIcon result = FUNCT_VIEW.createImage("icon.png");
        
        assertEquals(icon.getImage(), result.getImage());
        assertEquals(icon.getIconWidth(), result.getIconWidth());
        assertEquals(icon.getIconHeight(), result.getIconHeight());
    }

    /**
     * Method responsible for testing the getLabelImage(String) method.
     */
    @Test
    public void testGetLabelImage() {
        JLabel label  = new JLabel((Icon) this.createIconImage());
        JLabel result = FUNCT_VIEW.getLabelImage("icon.png");
        
        assertEquals(label.getText(), result.getText());
        assertEquals(label.getIcon().getIconWidth(), result.getIcon().getIconWidth());
        assertEquals(label.getIcon().getIconHeight(), result.getIcon().getIconHeight());
    }
}

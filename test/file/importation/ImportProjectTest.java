package file.importation;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import model.structural.base.Project;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 * <p>Class of Test <b>ImportProjectTest</b>.</p>
 * <p>Class responsible for testing the <b>ImportProject</b> class.</p>
 * @author Leandro
 * @date   2023-01-03
 * @see    file.importation.ImportProject
 * @see    model.structural.base.Project
 */
public class ImportProjectTest {
    private static Project project;
    
    @Before
    public void setUp() {
        try {
            project = new ImportProject("test/_files/test.smty").getProject();
        }catch (IOException | ParserConfigurationException | XPathExpressionException | SAXException exception) {
            project = null;
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Method responsible for testing the getProject() method.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetProject() throws Exception {
        assertEquals(project.getName(), "Project0");
        assertEquals(project.getVersion(), "1.0");
    }

    /**
     * Method responsible for testing the getElement(String) method.
     */
    @Test
    public void testGetElement() {

    }

    /**
     * Test of getStereotype method, of class ImportProject.
     */
    @Test
    public void testGetStereotype() {
        
    }

    /**
     * Test of getDiagram method, of class ImportProject.
     */
    @Test
    public void testGetDiagram() {
        
    }
    
}

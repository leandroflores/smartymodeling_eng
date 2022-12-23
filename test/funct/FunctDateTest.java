package funct;

import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>Class of Test <b>FunctDateTest</b>.</p>
 * <p>Class responsible for testing the <b>FunctDate</b> class.</p>
 * @author Leandro
 * @date   2022-10-07
 * @see    funct.FunctDate
 */
public class FunctDateTest {
    private static FunctDate FUNCT_DATE;
    
    public FunctDateTest() {
        FUNCT_DATE = new FunctDate();
    }
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Method responsible for testing the createDate(String) method.
     */
    @Test
    public void testCreateDate() {
        assertEquals(FUNCT_DATE.createDate("22/08/2022"), new Date(122,  7, 22));
        assertEquals(FUNCT_DATE.createDate("01/01/2021"), new Date(121,  0, 1));
        assertEquals(FUNCT_DATE.createDate("01/12/2000"), new Date(100, 11, 1));
        assertEquals(FUNCT_DATE.createDate("07/10/2022"), new Date(122,  9, 7));
    }
    
    /**
     * Method responsible for testing the getCurrentDate() method.
     */
    @Test
    public void testGetCurrentDate() {
        assertEquals(FUNCT_DATE.getCurrentDate(), new Date());
    }

    /**
     * Method responsible for testing the getCurrentUSFormattedDate() method.
     */
    @Test
    public void testGetCurrentUSFormattedDate() {
        assertEquals(FUNCT_DATE.getCurrentUSFormattedDate(), 
                     FUNCT_DATE.getFormattedUSDate(new Date()));
    }
    
    /**
     * Method responsible for testing the getFormattedDate(String, String) method.
     */
    @Test
    public void testGetFormattedDateWithFormat() {
        Date date = FUNCT_DATE.createDate("22/09/2022");
                
        assertEquals(FUNCT_DATE.getFormattedDate("yyyy-MM-dd", date), "2022-09-22");
        assertEquals(FUNCT_DATE.getFormattedDate("dd/MM/yyyy", date), "22/09/2022");
        assertEquals(FUNCT_DATE.getFormattedDate("ddMMyyyy", date), "22092022");
    }

    /**
     * Method responsible for testing the getFormattedDate(String) method.
     */
    @Test
    public void testGetFormattedDate() {
        assertEquals(FUNCT_DATE.getFormattedDate(FUNCT_DATE.createDate("22/09/2022")), "22/09/2022");
        assertEquals(FUNCT_DATE.getFormattedDate(FUNCT_DATE.createDate("30/01/2021")), "30/01/2021");
        assertEquals(FUNCT_DATE.getFormattedDate(FUNCT_DATE.createDate("15/04/2007")), "15/04/2007");
        assertEquals(FUNCT_DATE.getFormattedDate(FUNCT_DATE.createDate("05/03/1953")), "05/03/1953");
    }

    /**
     * Method responsible for testing the getFormattedUSDate(String) method.
     */
    @Test
    public void testGetFormattedUSDate() {
        assertEquals(FUNCT_DATE.getFormattedUSDate(FUNCT_DATE.createDate("22/09/2022")), "2022-09-22");
        assertEquals(FUNCT_DATE.getFormattedUSDate(FUNCT_DATE.createDate("30/01/2021")), "2021-01-30");
        assertEquals(FUNCT_DATE.getFormattedUSDate(FUNCT_DATE.createDate("15/04/2007")), "2007-04-15");
        assertEquals(FUNCT_DATE.getFormattedUSDate(FUNCT_DATE.createDate("05/03/1953")), "1953-03-05");
    }    
}

package funct;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * <p>Class of Test <b>FunctStringTest</b>.</p>
 * <p>Class responsible for testing the <b>FunctString</b> class.</p>
 * @author Leandro
 * @date   2022-10-07
 * @see    funct.FunctString
 */
public class FunctStringTest {
    private static FunctString FUNCT_STRING;
    
    public FunctStringTest() {
        FUNCT_STRING = new FunctString();
    }
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}

    /**
     * Method responsible for testing the getString(String) method.
     */
    @Test
    public void testGetString() {
        assertEquals(FUNCT_STRING.getString(""), "");
        assertEquals(FUNCT_STRING.getString("Avava"), "Avava");
        assertEquals(FUNCT_STRING.getString("< >"), "");
        assertEquals(FUNCT_STRING.getString(" ? : * +-a"), ":  a");
        assertEquals(FUNCT_STRING.getString("aa!%$%vv"), "aavv");
    }

    /**
     * Method responsible for testing the getString(char, int) method.
     */
    @Test
    public void testGetStringWithParameters() {
        assertEquals(FUNCT_STRING.getString('a', -2), "");
        assertEquals(FUNCT_STRING.getString(' ', 0), "");
        assertEquals(FUNCT_STRING.getString(' ', 1), " ");
        assertEquals(FUNCT_STRING.getString('$', 5), "$$$$$");
        assertEquals(FUNCT_STRING.getString('B', 9), "BBBBBBBBB");
    }

    /**
     * Method responsible for testing the getSpaces(int) method.
     */
    @Test
    public void testGetSpaces() {
        assertEquals(FUNCT_STRING.getSpaces(-7), "");
        assertEquals(FUNCT_STRING.getSpaces(-3), "");
        assertEquals(FUNCT_STRING.getSpaces(0),  "");
        assertEquals(FUNCT_STRING.getSpaces(3),  "   ");
        assertEquals(FUNCT_STRING.getSpaces(10), "          ");
    }

    /**
     * Method responsible for testing the initUpperCase(String) method.
     */
    @Test
    public void testInitUpperCase() {
        assertEquals(FUNCT_STRING.initUpperCase(""), "");
        assertEquals(FUNCT_STRING.initUpperCase("A"), "A");
        assertEquals(FUNCT_STRING.initUpperCase(" b"), " b");
        assertEquals(FUNCT_STRING.initUpperCase("b Cd"), "B cd");
        assertEquals(FUNCT_STRING.initUpperCase("aAAAAA"), "Aaaaaa");
        assertEquals(FUNCT_STRING.initUpperCase("AAAAAA"), "Aaaaaa");
    }

    /**
     * Method responsible for testing the getInitUpperCase(String) method.
     */
    @Test
    public void testGetInitUpperCase() {
        assertEquals(FUNCT_STRING.getInitUpperCase(""), "");
        assertEquals(FUNCT_STRING.getInitUpperCase("acde"), "Acde");
        assertEquals(FUNCT_STRING.getInitUpperCase("Acde"), "Acde");
        assertEquals(FUNCT_STRING.getInitUpperCase("a b z e"), "A B Z E");
        assertEquals(FUNCT_STRING.getInitUpperCase("Ab BC ED"), "Ab Bc Ed");
    }

    /**
     * Method responsible for testing the removeSpecialChars(String) method.
     */
    @Test
    public void testRemoveSpecialChars() {
        assertEquals(FUNCT_STRING.removeSpecialChars(""), "");
        assertEquals(FUNCT_STRING.removeSpecialChars("1"), "1");
        assertEquals(FUNCT_STRING.removeSpecialChars("test"), "test");
        assertEquals(FUNCT_STRING.removeSpecialChars("execução"), "execucao");
        assertEquals(FUNCT_STRING.removeSpecialChars("parabéns"), "parabens");
        assertEquals(FUNCT_STRING.removeSpecialChars("triângulo"), "triangulo");
    }

    /**
     * Method responsible for testing the replaceChar(char) method.
     */
    @Test
    public void testReplaceChar() {
        assertEquals(FUNCT_STRING.replaceChar('á'), 'a');
        assertEquals(FUNCT_STRING.replaceChar('Ê'), 'E');
        assertEquals(FUNCT_STRING.replaceChar('ç'), 'c');
        assertEquals(FUNCT_STRING.replaceChar('À'), 'A');
        assertEquals(FUNCT_STRING.replaceChar('p'), 'p');
        assertEquals(FUNCT_STRING.replaceChar('õ'), 'o');
    }

    /**
     * Method responsible for testing the md5(String) method.
     */
    @Test
    public void testMd5() {
        assertEquals(FUNCT_STRING.md5(""),  "d41d8cd98f00b204e9800998ecf8427e");
        assertEquals(FUNCT_STRING.md5("A"), "7fc56270e7a70fa81a5935b72eacbe29");
        assertEquals(FUNCT_STRING.md5("12345"), "827ccb0eea8a706c4c34a16891f84e7b");
        assertEquals(FUNCT_STRING.md5("SENHA"), "85ee0fe4f155a9af2657d85054ad63ca");
        assertEquals(FUNCT_STRING.md5("senha"), "e8d95a51f3af4a3b134bf6bb680a213a");
    }
}
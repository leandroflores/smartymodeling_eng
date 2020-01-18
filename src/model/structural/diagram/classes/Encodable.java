package model.structural.diagram.classes;

/**
 * <p>Inteface of Model <b>Encodable</b>.</p>
 * <p>Inteface responsible for defining the <b>Code Exporting</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  18/01/2020
 */
public interface Encodable {
    
    /**
     * Method responsible for exporting the Code.
     * @return Code.
     */
    public abstract String exportCode();
}
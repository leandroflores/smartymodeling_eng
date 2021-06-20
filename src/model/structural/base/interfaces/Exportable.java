package model.structural.base.interfaces;

/**
 * <p>Inteface of Model <b>Exportable</b>.</p>
 * <p>Inteface responsible for defining the <b>Exporting Data</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-20
 */
public interface Exportable {
    
    /**
     * Method responsible for exporting Datas.
     * @return Exporting Data.
     */
    public abstract String export();
}
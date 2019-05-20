package model.structural;

/**
 * <p>Classe de Modelo <b>Link</b>.</p>
 * <p>Classe responsavel por representar o <b>Link</b> no SMartyModeling.</p>
 * @author Leandro
 * @since  16/05/2019
 * @see    model.structural.Exportable
 */
public class Link implements Exportable {
    private Element    elemento;
    private Stereotype estereotipo;
    
    /**
     * Metodo responsavel por retornar o Id do Link.
     * @return Id do Link.
     */
    public String getId() {
        return "LINK#" + this.elemento.getId() + "-" + this.estereotipo.getId();
    }

    /**
     * Metodo responsavel por retornar o Element do Link.
     * @return Element do Link.
     */
    public Element getElemento() {
        return this.elemento;
    }

    /**
     * Metodo responsavel por definir o Element do Link.
     * @param elemento Element do Link.
     */
    public void setElemento(Element elemento) {
        this.elemento = elemento;
    }

    /**
     * Metodo responsavel por retornar o Stereotype do Link.
     * @return Stereotype do Link.
     */
    public Stereotype getEstereotipo() {
        return this.estereotipo;
    }

    /**
     * Metodo responsavel por definir o Stereotype do Link.
     * @param estereotipo Stereotype do Link.
     */
    public void setEstereotipo(Stereotype estereotipo) {
        this.estereotipo = estereotipo;
    }
    
    @Override
    public String export() {
        String export  = "    <link";
               export += " elemento=\""    + this.elemento.getId()    + "\"";
               export += " estereotipo=\"" + this.estereotipo.getId() + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.elemento.toString() + " - " + this.estereotipo.toString();
    }
}
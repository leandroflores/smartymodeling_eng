package model.structural;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>Class of Model <b>Inheritance</b>.</p>
 * <p>Class responsible for representing the <b>Inheritance</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.Association
 * @see    model.structural.Element
 */
public class Inheritance extends Association {
    
    /**
     * Metodo construtor padrao da Classe.
     * @param origem Element Origem.
     * @param destino Element Destino.
     */
    public Inheritance(Element origem, Element destino) {
        this.source  = origem;
        this.target = destino;
        this.type    = "heranca";
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public String getStyleLabel() {
        return "estiloHeranca";
    }

    @Override
    public Map getStyle() {
        Map    estilo = new HashMap<>();
               estilo.put(mxConstants.STYLE_STROKECOLOR, "#000000");
               estilo.put(mxConstants.STYLE_DASHED, "0");
               estilo.put(mxConstants.STYLE_EDITABLE, "0");
               estilo.put(mxConstants.STYLE_STARTARROW, mxConstants.ARROW_SPACING);
               estilo.put(mxConstants.STYLE_ENDARROW, mxConstants.ARROW_BLOCK);
               estilo.put(mxConstants.STYLE_FILLCOLOR, "#FFFFFF");
        return estilo;
    }
    
    @Override
    public int hashCode() {
        int    hash = 3;
               hash = 19 * hash + Objects.hashCode(this.source);
               hash = 19 * hash + Objects.hashCode(this.target);
        return hash;
    }
    
    @Override
    public boolean equals(Object objeto) {
        if (objeto instanceof Inheritance == false)
            return false;
        return this.source.equals(((Inheritance) objeto).getSource())
            && this.target.equals(((Inheritance) objeto).getTarget());
    }
}
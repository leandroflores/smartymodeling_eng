package model.structural.diagram.classs;

import com.mxgraph.util.mxConstants;
import java.util.HashMap;
import java.util.Map;
import model.structural.Element;

/**
 * <p>Classe de Modelo <b>AttributeUML</b>.</p>
 * <p>Classe responsavel por representar o <b>Atributo UML</b> no SMartyModeling.</p>
 * @author Leandro
 * @since  04/04/2019
 * @see    model.structural.Element
 */
public class AttributeUML extends Element {
    private Entity  entity;
    private TypeUML typeUML;
    private String  visibility;
    private boolean static_;
    private boolean final_;
    
    /**
     * Default constructor method of Class.
     */
    public AttributeUML() {
        this.id         = "";
        this.name       = "attribute";
        this.type       = "attribute";
        this.entity     = null;
        this.typeUML    = null;
        this.visibility = "private";
        this.static_    = false;
        this.final_     = false;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param element W3C Element.
     */
    public AttributeUML(org.w3c.dom.Element element) {
        this.id         = element.getAttribute("id");
        this.name       = element.getAttribute("nome");
        this.type       = "attribute";
        this.entity     = null;
        this.typeUML    = null;
        this.visibility = element.getAttribute("visibility");
        this.static_    = element.getAttribute("static_").trim().equals("true");
        this.final_     = element.getAttribute("final_").trim().equals("true");
    }

    /**
     * Method responsible for returning Entity.
     * @return Entity.
     */
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Method responsible for defining Entity.
     * @param entity Entity.
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * Method responsible for returning Type UML.
     * @return Type UML.
     */
    public TypeUML getTypeUML() {
        return this.typeUML;
    }

    /**
     * Method responsible for defining Type UML.
     * @param typeUML Type UML.
     */
    public void setTypeUML(TypeUML typeUML) {
        this.typeUML = typeUML;
    }

    /**
     * Method responsible for returning Visibility.
     * @return Visibility.
     */
    public String getVisibility() {
        return this.visibility;
    }

    /**
     * Method responsible for returning Visibility.
     * @param visibility Visibility.
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * Method responsible for returning Static Flag.
     * @return Static Flag.
     */
    public boolean isStatic() {
        return this.static_;
    }

    /**
     * Method responsible for defining Static Flag.
     * @param static_ Static Flag.
     */
    public void setStatic(boolean static_) {
        this.static_ = static_;
    }

    /**
     * Method responsible for returning Final Flag.
     * @return Final Flag.
     */
    public boolean isFinal() {
        return this.final_;
    }

    /**
     * Method responsible for defining Final Flag.
     * @param final_ Final Flag.
     */
    public void setFinal(boolean final_) {
        this.final_ = final_;
    }
    
    /**
     * Method responsible for printing Attribute Data.
     * @return Attribute Data.
     */
    public String print() {
        String attribute  = this.printVisibility();
               attribute += (this.static_)  ? " static" : "";
               attribute += (this.final_)   ? " final"  : "";
               attribute += " " + this.printTypeUML();
               attribute += " " + this.name;
        return attribute;
    }
    
    /**
     * Method responsible for printing Visibility.
     * @return Visibility.
     */
    private String printVisibility() {
        if (this.visibility.equals("package"))
            return "";
        if (this.visibility.equals("default"))
            return "";
        return this.visibility;
    }
    
    /**
     * Method responsible for printing Import.
     * @return Import.
     */
    public String printImport() {
        if (this.typeUML.getPath().contains("."))
            return this.typeUML.getPath();
        return "";
    }
    
    /**
     * Method responsible for printing Type UML.
     * @return Type UML.
     */
    private String printTypeUML() {
        if (this.typeUML == null)
            return "Object";
        return this.typeUML.getName();
    }
    
    /**
     * Metodo responsavel por retornar o Simbolo da Visibilidade UML.
     * @return Simbolo da Visibilidade UML.
     */
    private String getSimboloVisibilidade() {
        if (this.visibility.equals("public"))
            return "+";
        if (this.visibility.equals("protected"))
            return "#";
        if (this.visibility.equals("private"))
            return "-";
        return "~";
    }
    
    /**
     * Metodo responsavel por retornar a Descricao do Atributo UML.
     * @return Descricao do Atributo UML.
     */
    public String getAssinatura() {
        return this.getSimboloVisibilidade() + " " + this.nome + " : " + this.printTipo();
    }
    
    @Override
    public String getIcon() {
        return "src/imagens/icones/diagrama/classes/atributo.png";
    }
    
    @Override
    public String getTitle() {
        return this.getAssinatura();
    }
    
    @Override
    public String getStyleLabel() {
        return "estiloAtributoUML" + this.id;
    }
    
    @Override
    public Map getStyle() {
        Map    estilo = new HashMap<>();
               estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               estilo.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
               estilo.put(mxConstants.STYLE_FONTCOLOR,   "#000000");
               estilo.put(mxConstants.STYLE_FILLCOLOR,   "#9999FF");
               estilo.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               estilo.put(mxConstants.STYLE_FONTSTYLE, this.static_ ? 1 : 4);
               estilo.put(mxConstants.STYLE_EDITABLE,  "1");
               estilo.put(mxConstants.STYLE_FONTSIZE,  "12");
               estilo.put(mxConstants.STYLE_RESIZABLE, "0");
               estilo.put(mxConstants.STYLE_MOVABLE,   "0");
               estilo.put(mxConstants.STYLE_FOLDABLE,  "0");
        return estilo;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo do Atributo UML.
     * @return Tipo do Atributo UML.
     */
    private String exportarTipo() {
        if (this.tipo == null)
            return "tipo=\"TIPO#21\" ";
        return "tipo=\"" + this.tipo.getId() + "\" ";
    }
    
    @Override
    public String export() {
        String export  = "      <atributo ";
               export += "id=\""           + this.id           + "\" ";
               export += "nome=\""         + this.nome         + "\" ";
               export += this.exportarTipo();
               export += "visibilidade=\"" + this.visibility + "\" ";
               export += "estatico=\""     + this.static_     + "\" ";
               export += "constante=\""    + this.final_    + "\"";
               export += "/>\n";
        return export;
    }
    
    @Override
    public String toString() {
        return this.getAssinatura();
    }
}
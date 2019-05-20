package model.structural.diagram.classs;

import com.mxgraph.util.mxConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import modelo.estruturais.Exportavel;
import modelo.estruturais.Modelavel;
import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>MetodoUML</b>.</p>
 * <p>Classe responsavel por representar o <b>Metodo UML</b> no SMartyModeling.</p>
 * @author Leandro
 * @since  15/04/2019
 * @see    modelo.estruturais.Exportavel
 * @see    modelo.estruturais.Modelavel
 * @see    modelo.estruturais.diagrama.classes.ParametroUML
 * @see    model.structural.diagram.classs.TypeUML
 */
public class MetodoUML implements Exportavel, Modelavel {
    private String   id;
    private String   nome;
    private Entity entidade;
    private String   visibilidade;
    private TypeUML  retorno;
    private boolean  construtor;
    private boolean  estatico;
    private boolean  definitivo;
    private boolean  abstrato;
    private List<ParametroUML> parametros;
    
    /**
     * Metodo construtor padrao da Classe.
     */
    public MetodoUML() {
        this.id           = "";
        this.nome         = "metodo";
        this.entidade     = null;
        this.visibilidade = "public";
        this.retorno      = null;
        this.construtor   = false;
        this.estatico     = false;
        this.definitivo   = false;
        this.abstrato     = false;
        this.parametros   = new ArrayList<>();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param element Elemento W3C.
     */
    public MetodoUML(Element element) {
        this.id           = element.getAttribute("id");
        this.nome         = element.getAttribute("nome");
        this.entidade     = null;
        this.visibilidade = element.getAttribute("visibilidade");
        this.retorno      = null;
        this.construtor   = element.getAttribute("construtor").equals("true");
        this.estatico     = element.getAttribute("estatico").equals("true");
        this.definitivo   = element.getAttribute("definitivo").equals("true");
        this.abstrato     = element.getAttribute("abstrato").equals("true");
        this.parametros   = new ArrayList<>();
        this.setRetorno(element);
    }

    /**
     * Metodo responsavel por retornar o Id do Metodo UML.
     * @return Id do Metodo UML.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id do Metodo UML.
     * @param id Id do Metodo UML.
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Metodo responsavel por retornar o Nome do Metodo UML.
     * @return Nome do Metodo UML.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Metodo UML.
     * @param nome Nome do Metodo UML.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo responsavel por retornar a Entity do Metodo UML.
     * @return Entity do Metodo UML.
     */
    public Entity getEntidade() {
        return this.entidade;
    }

    /**
     * Metodo responsavel por definir a Entity do Metodo UML.
     * @param entidade Entity do Metodo UML.
     */
    public void setEntidade(Entity entidade) {
        this.entidade = entidade;
    }
    
    /**
     * Metodo responsavel por retornar a Visibilidade do Metodo UML.
     * @return Visibilidade do Metodo UML.
     */
    public String getVisibilidade() {
        return this.visibilidade;
    }

    /**
     * Metodo responsavel por definir a Visibilidade do Metodo UML.
     * @param visibilidade Visibilidade do Metodo UML.
     */
    public void setVisibilidade(String visibilidade) {
        this.visibilidade = visibilidade;
    }
    
    /**
     * Metodo responsavel por retornar o Retorno do Metodo UML.
     * @return Retorno do Metodo UML.
     */
    public TypeUML getRetorno() {
        return this.retorno;
    }
    
    /**
     * Metodo responsavel por definir o Retorno do Metodo UML.
     * @param retorno Retorno do Metodo UML.
     */
    public void setRetorno(TypeUML retorno) {
        this.retorno = retorno;
    }
    
    /**
     * Metodo responsavel por definir o Retorno do Metodo UML.
     * @param element Elemento W3C.
     */
    private void setRetorno(Element element) {
        if (this.construtor)
            this.retorno = null;
    }
    
    /**
     * Metodo responsavel por retornar a flag Construtor do Metodo UML.
     * @return Flag Construtor do Metodo UML.
     */
    public boolean isConstrutor() {
        return this.construtor;
    }

    /**
     * Metodo responsavel por definir a flag Construtor do Metodo UML.
     * @param construtor Flag Construtor do Metodo UML.
     */
    public void setConstrutor(boolean construtor) {
        this.construtor = construtor;
    }
    
    /**
     * Metodo responsavel por retornar a flag Estatico do Metodo UML.
     * @return Flag Estatico do Metodo UML.
     */
    public boolean isEstatico() {
        return this.estatico;
    }

    /**
     * Metodo responsavel por definir a flag Estatico do Metodo UML.
     * @param estatico Flag Estatico do Metodo UML.
     */
    public void setEstatico(boolean estatico) {
        this.estatico = estatico;
    }
    
    /**
     * Metodo responsavel por retornar a flag Definitivo do Metodo UML.
     * @return Flag Definitivo do Metodo UML.
     */
    public boolean isDefinitivo() {
        return this.definitivo;
    }
    
    /**
     * Metodo responsavel por definir a flag Definitivo do Metodo UML.
     * @param definitivo Flag Definitivo do Metodo UML.
     */
    public void setDefinitivo(boolean definitivo) {
        this.definitivo = definitivo;
    }

    /**
     * Metodo responsavel por retornar a flag Abstrato do Metodo UML.
     * @return Flag Abstrato do Metodo UML.
     */
    public boolean isAbstrato() {
        return this.abstrato;
    }
    
    /**
     * Metodo responsavel por definir a flag Abstrato do Metodo UML.
     * @param abstrato Flag Abstrato do Metodo UML.
     */
    public void setAbstrato(boolean abstrato) {
        this.abstrato = abstrato;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Parametros do Metodo UML.
     * @return Lista de Parametros do Metodo UML.
     */
    public List<ParametroUML> getParametros() {
        return this.parametros;
    }
    
    /**
     * Metodo responsavel por adicionar um Parametro ao Metodo UML.
     * @param parametro Parametro a ser adicionado.
     */
    public void addParametro(ParametroUML parametro) {
        if (this.parametros.contains(parametro) == false)
            this.parametros.add(parametro);
    }
    
    /**
     * Metodo responsavel por definir uma Lista de Parametros do Metodo UML.
     * @param parametros Lista de Parametros do Metodo UML.
     */
    public void setParametros(List<ParametroUML> parametros) {
        this.parametros = parametros;
    }
    
    /**
     * Metodo responsavel por retornar uma String com os Parametros de um Metodo UML.
     * @return String com os Parametros de um Metodo UML.
     */
    private String parametros() {
        if (this.parametros.isEmpty())   
            return "()";
        if (this.parametros.size() == 1)
            return "(" + this.parametros.get(0).getTitulo() + ")";
        String toReturn  = "(" + this.parametros.get(0).getTitulo();
        for (int i = 1; i < this.parametros.size(); ++i)
               toReturn += ", " + this.parametros.get(i).getTitulo();
        return toReturn + ")";
    }
    
    /**
     * Metodo responsavel por retornar uma String com os Parametros de um Metodo UML.
     * @return String com os Parametros de um Metodo UML.
     */
    private String printParametros() {
        if (this.parametros.isEmpty())   
            return "()";
        if (this.parametros.size() == 1)
            return "(" + this.parametros.get(0).print() + ")";
        String toReturn = "(" + this.parametros.get(0).print();
        for (int i = 1; i < this.parametros.size(); ++i)
               toReturn += ", " + this.parametros.get(i).print();
        return toReturn + ")";
    }
    
    /**
     * Metodo responsavel por retornar os Dados para Impressao de um Metodo UML.
     * @return Dados para Impressao de um Metodo UML.
     */
    public String print() {
        String metodo  = "    ";
               metodo += this.visibilidade;
               metodo += (this.estatico)   ? " static"   : "";
               metodo += (this.abstrato)   ? " abstract" : "";
               metodo += (this.definitivo) ? " final"    : "";
               metodo += " " + this.retorno();
               metodo += " " + this.nome;
               metodo += this.printParametros();
               metodo += (this.abstrato)   ? ";\n" : " {" + this.printRetorno();
        return metodo;
    }
    
    /**
     * Metodo responsavel por retornar o Retorno do Metodo UML.
     * @return Retorno do Metodo UML.
     */
    private String printRetorno() {
        if (this.construtor)
            return "}\n";
        if (this.retorno.getName().equals("void"))
            return "\n    }\n";
        if (this.retorno.getName().equals("int"))
            return "\n        return 0;\n    }\n";
        if (this.retorno.getName().equals("Integer"))
            return "\n    return 0;\n    }\n";
        if (this.retorno.getName().equals("Long"))
            return "    return 0L;\n    }\n";
        if (this.retorno.getName().equals("long"))
            return "    return 0L;\n    }\n";
        if (this.retorno.getName().equals("String"))
            return "    return \"\";\n    }\n";
        if (this.retorno.getName().equals("char"))
            return "    return '';\n    }\n";
        if (this.retorno.getName().equals("boolean"))
            return "    return true;\n    }\n";
        return "\n    return null;\n    }\n";
    }
    
    /**
     * Metodo responsavel por retornar a Assinatura do Metodo UML.
     * @return Assinatura do Metodo UML.
     */
    public String getAssinatura() {
        return this.nome + this.parametros();
    }
    
    /**
     * Metodo responsavel por retornar a Assinatura Completa do Metodo UML.
     * @return Assinatura Completa do Metodo UML.
     */
    public String getAssinaturaCompleta() {
        return this.getSimboloVisibilidade() + " " + this.getAssinatura() + this.printTipo();
    }
    
    /**
     * Metodo responsavel por retornar o Tipo do Retorno do Metodo UML.
     * @return Tipo do Retorno do Metodo UML.
     */
    private String printTipo() {
        if (this.construtor)
            return "";
        if (this.retorno == null)
            return "";
        return " : " + this.retorno.getName();
    }
    
    /**
     * Metodo responsavel por retornar o Simbolo da Visibilidade.
     * @return Simbolo da Visibilidade.
     */
    private String getSimboloVisibilidade() {
        if (this.visibilidade.equals("public"))
            return "+";
        if (this.visibilidade.equals("protected"))
            return "#";
        if (this.visibilidade.equals("private"))
            return "-";
        return "~";
    }
    
    /**
     * Metodo responsavel retornar a Descricao do Metodo UML.
     * @return Descricao do Metodo UML.
     */
    public String getDescricao() {
        return this.getSimboloVisibilidade() + " " + this.nome + this.printParametros() + this.retorno();
    }
    
    /**
     * Metodo responsavel por retornar o retorno do Metodo UML.
     * @return Retorno do Metodo UML.
     */
    private String retorno() {
        if (this.construtor)
            return "";
        if (this.retorno == null)
            return " : void";
        return " : " + this.retorno.getName();
    }
    
    /**
     * Metodo responsavel por retornar o Icone do Metodo UML.
     * @return Icone do Metodo UML.
     */
    public String getIcone() {
        return "src/imagens/icones/diagrama/classes/metodo.png";
    }
    
    @Override
    public String getTitulo() {
        return this.getAssinaturaCompleta();
    }
    
    @Override
    public String getRotuloEstilo() {
        return "estiloMetodoUML" + this.id;
    }
    
    @Override
    public Map getEstilo() {
        Map    estilo = new HashMap<>();
               estilo.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_RECTANGLE);
               estilo.put(mxConstants.STYLE_ALIGN, mxConstants.ALIGN_LEFT);
               estilo.put(mxConstants.STYLE_FONTCOLOR, "#000000");
               estilo.put(mxConstants.STYLE_FONTSTYLE, this.getFont());
               estilo.put(mxConstants.STYLE_FILLCOLOR, "#9999FF");
               estilo.put(mxConstants.STYLE_STROKECOLOR, "#9999FF");
               estilo.put(mxConstants.STYLE_EDITABLE, "1");
               estilo.put(mxConstants.STYLE_FONTSIZE, "12");
               estilo.put(mxConstants.STYLE_RESIZABLE, "0");
               estilo.put(mxConstants.STYLE_MOVABLE, "0");
               estilo.put(mxConstants.STYLE_FOLDABLE, "0");
        return estilo;
    }
    
    /**
     * Metodo responsavel por retornar o Codigo da Fonte.
     * @return Codigo da Fonte.
     */
    private int getFont() {
        if (this.abstrato && this.estatico)
            return 3;
        if (this.abstrato)
            return 2;
        if (this.estatico)
            return 1;
        return 4;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo do Retorno do Metodo UML.
     * @return Tipo do Retorno do Metodo UML.
     */
    private String exportarRetorno() {
        if (this.retorno == null)
            return "retorno=\"TIPO#42\" ";
        return "retorno=\"" + this.retorno.getId() + "\" ";
    }
    
    @Override
    public String exportar() {
        String export  = "      <metodo ";
               export += "id=\""           + this.id           + "\" ";
               export += "nome=\""         + this.nome         + "\" ";
               export += this.exportarRetorno();
               export += "visibilidade=\"" + this.visibilidade + "\" ";
               export += "construtor=\""   + this.construtor   + "\" ";
               export += "estatico=\""     + this.estatico     + "\" ";
               export += "definitivo=\""   + this.definitivo   + "\" ";
               export += "abstrato=\""     + this.abstrato     + "\"";
               export += ">\n";
               export += exportParametros();
               export += "      </metodo>\n";
        return export;
    }
    
    /**
     * Metodo responsavel por retornar a String com os Parametros do Metodo UML.
     * @return Parametros do Metodo UML.
     */
    private String exportParametros() {
        String export = "";
        for (int i = 0; i < this.parametros.size(); i++)
               export += this.parametros.get(i).exportar();
        return export;
    }
    
    @Override
    public boolean equals(Object objeto) {
        if (objeto == null)
            return false;
        if (objeto instanceof MetodoUML == false)
            return false;
        return this.getAssinatura().equals(((MetodoUML) objeto).getAssinatura());
    }

    @Override
    public int hashCode() {
        int    hash = 7;
               hash = 73 * hash + Objects.hashCode(this.nome);
        return hash;
    }
    
    @Override
    public String toString() {
        return this.getDescricao();
    }
}
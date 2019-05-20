package model.structural;

import org.w3c.dom.Element;

/**
 * <p>Classe de Modelo <b>Perfil</b>.</p>
 * <p>Classe responsavel por representar o <b>Perfil</b> no SMartyModeling.</p>
 * @author Leandro
 * @since  13/02/2019
 * @see    model.structural.Exportable
 */
public class Perfil implements Exportable {
    private boolean estereotipo;
    private String  obrigatorio;
    private String  opcional;
    private String  pontoDeVariacao;
    private String  inclusivo;
    private String  exclusivo;
    private String  mutex;
    private String  requires;
    
    /**
     * Metodo construtor padrao da Classe.
     */
    public Perfil() {
        this.estereotipo     = true;
        this.obrigatorio     = "mandatory";
        this.opcional        = "opcional";
        this.pontoDeVariacao = "variationPoint";
        this.inclusivo       = "alternative_OR";
        this.exclusivo       = "alternative_XOR";
        this.mutex           = "mutex";
        this.requires        = "requires";
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param elemento Elemento W3C.
     */
    public Perfil(Element elemento) {
        this.estereotipo     = elemento.getAttribute("estereotipo").contains("true");
        this.obrigatorio     = elemento.getElementsByTagName("obrigatorio").item(0).getTextContent().trim();
        this.opcional        = elemento.getElementsByTagName("opcional").item(0).getTextContent().trim();
        this.pontoDeVariacao = elemento.getElementsByTagName("pontoDeVariacao").item(0).getTextContent().trim();
        this.inclusivo       = elemento.getElementsByTagName("inclusivo").item(0).getTextContent().trim();
        this.exclusivo       = elemento.getElementsByTagName("exclusivo").item(0).getTextContent().trim();
        this.mutex           = elemento.getElementsByTagName("mutex").item(0).getTextContent().trim();
        this.requires        = elemento.getElementsByTagName("requires").item(0).getTextContent().trim();
    }

    /**
     * Metodo responsavel por retornar a Flag do Estereotipo.
     * @return Flag do Estereotipo.
     */
    public boolean isEstereotipo() {
        return this.estereotipo;
    }

    /**
     * Metodo responsavel por definir a Flag do Estereotipo.
     * @param estereotipo Flag do Estereotipo.
     */
    public void setEstereotipo(boolean estereotipo) {
        this.estereotipo = estereotipo;
    }
    
    /**
     * Metodo responsavel por retornar o Estereotipo do Perfil.
     * @param  identificador Identificador.
     * @return Estereotipo do Perfil.
     */
    private String getEstereotipo(String identificador) {
        if (this.estereotipo)
            return "<<" + identificador + ">>";
        return identificador;
    }
    
    /**
     * Metodo responsavel por retornar o Obrigatorio do Perfil.
     * @return Obrigatorio do Perfil.
     */
    public String getObrigatorio() {
        return this.obrigatorio;
    }
    
    /**
     * Metodo responsavel por retornar o Identificador Obrigatorio do Perfil.
     * @return Identificador Obrigatorio do Perfil.
     */
    public String getIdentificadorObrigatorio() {
        return this.getEstereotipo(this.obrigatorio);
    }

    /**
     * Metodo responsavel por definir o Obrigatorio do Perfil.
     * @param obrigatorio Obrigatorio do Perfil.
     */
    public void setObrigatorio(String obrigatorio) {
        this.obrigatorio = obrigatorio;
    }

    /**
     * Metodo responsavel por retornar o Opcional do Perfil.
     * @return Opcional do Perfil.
     */
    public String getOpcional() {
        return this.opcional;
    }
    
    /**
     * Metodo responsavel por retornar o Identificador Opcional do Perfil.
     * @return Identificador Opcional do Perfil.
     */
    public String getIdentificadorOpcional() {
        return this.getEstereotipo(this.opcional);
    }

    /**
     * Metodo responsavel por definir o Opcional do Perfil.
     * @param opcional Opcional do Perfil.
     */
    public void setOpcional(String opcional) {
        this.opcional = opcional;
    }

    /**
     * Metodo responsavel por retornar o Ponto de Variacao do Perfil.
     * @return Ponto de Variacao do Perfil.
     */
    public String getPontoDeVariacao() {
        return this.pontoDeVariacao;
    }

    /**
     * Metodo responsavel por retornar o Identificador Ponto de Variacao do Perfil.
     * @return Identificador Ponto de Variacao do Perfil.
     */
    public String getIdentificadorPontoDeVariacao() {
        return this.getEstereotipo(this.pontoDeVariacao);
    }
    
    /**
     * Metodo responsavel por definir o Ponto de Variacao do Perfil.
     * @param pontoDeVariacao Ponto de Variacao do Perfil.
     */
    public void setPontoDeVariacao(String pontoDeVariacao) {
        this.pontoDeVariacao = pontoDeVariacao;
    }

    /**
     * Metodo responsavel por retornar o Inclusivo do Perfil.
     * @return Inclusivo do Perfil.
     */
    public String getInclusivo() {
        return this.inclusivo;
    }
    
    /**
     * Metodo responsavel por retornar o Identificador Inclusivo do Perfil.
     * @return Identificador Inclusivo do Perfil.
     */
    public String getIdentificadorInclusivo() {
        return this.getEstereotipo(this.inclusivo);
    }

    /**
     * Metodo responsavel por definir o Inclusivo do Perfil.
     * @param inclusivo Inclusivo do Perfil.
     */
    public void setInclusivo(String inclusivo) {
        this.inclusivo = inclusivo;
    }

    /**
     * Metodo responsavel por retornar o Exclusivo do Perfil.
     * @return Exclusivo do Perfil.
     */
    public String getExclusivo() {
        return this.exclusivo;
    }
    
    /**
     * Metodo responsavel por retornar o Identificador Exclusivo do Perfil.
     * @return Identificador Exclusivo do Perfil.
     */
    public String getIdentificadorExclusivo() {
        return this.getEstereotipo(this.exclusivo);
    }

    /**
     * Metodo responsavel por definir o Exclusivo do Perfil.
     * @param exclusivo Exclusivo do Perfil.
     */
    public void setExclusivo(String exclusivo) {
        this.exclusivo = exclusivo;
    }

    /**
     * Metodo responsavel por retornar o Mutex do Perfil.
     * @return Mutex do Perfil.
     */
    public String getMutex() {
        return this.mutex;
    }
    
    /**
     * Metodo responsavel por retornar o Identificador Mutex do Perfil.
     * @return Identificador Mutex do Perfil.
     */
    public String getIdentificadorMutex() {
        return this.getEstereotipo(this.mutex);
    }

    /**
     * Metodo responsavel por definir o Mutex do Perfil.
     * @param mutex Mutex do Perfil.
     */
    public void setMutex(String mutex) {
        this.mutex = mutex;
    }

    /**
     * Metodo responsavel por retornar o Requires do Perfil.
     * @return Requires do Perfil.
     */
    public String getRequires() {
        return this.requires;
    }
    
    /**
     * Metodo responsavel por retornar o Identificador Requires do Perfil.
     * @return Identificador Requires do Perfil.
     */
    public String getIdentificadorRequires() {
        return this.getEstereotipo(this.requires);
    }

    /**
     * Metodo responsavel por definir o Requires do Perfil.
     * @param requires Requires do Perfil.
     */
    public void setRequires(String requires) {
        this.requires = requires;
    }

    @Override
    public String export() {
        String export  = "  <perfil estereotipo=\"" + this.estereotipo     + "\">\n";
               export += "    <obrigatorio>"        + this.obrigatorio     + "</obrigatorio>\n";
               export += "    <opcional>"           + this.opcional        + "</opcional>\n";
               export += "    <pontoDeVariacao>"    + this.pontoDeVariacao + "</pontoDeVariacao>\n";
               export += "    <inclusivo>"          + this.inclusivo       + "</inclusivo>\n";
               export += "    <exclusivo>"          + this.exclusivo       + "</exclusivo>\n";
               export += "    <mutex>"              + this.mutex           + "</mutex>\n";
               export += "    <requires>"           + this.requires        + "</requires>\n";
               export += "  </perfil>\n";
        return export;
    }
    
    @Override
    public String toString() {
        String perfil =  "Obrigatorio    = " + this.obrigatorio     + "\n";
               perfil += "Opcional       = " + this.opcional        + "\n";
               perfil += "Ponto Variacao = " + this.pontoDeVariacao + "\n";
               perfil += "Inclusivo      = " + this.inclusivo       + "\n";
               perfil += "Exclusivo      = " + this.exclusivo       + "\n";
               perfil += "Mutex          = " + this.mutex           + "\n";
               perfil += "Requires       = " + this.requires        + "\n";
        return perfil;
    }
}
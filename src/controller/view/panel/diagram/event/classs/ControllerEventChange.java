package controller.view.panel.diagram.event.classs;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Element;
import model.structural.diagram.classs.base.AttributeUML;
import model.structural.diagram.classs.base.MethodUML;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventChange</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Changing Events</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventChange extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventChange(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object cell = this.panel.getGraph().getSelectionCell();
        String id   = this.getId(cell);
            this.change(cell, id);
        this.panel.updateDiagram();
        this.panel.getViewMenu().getPanelTree().updateUI();
    }
    
    /**
     * Method responsible for changing the Selected Element.
     * @param element Selected Element.
     * @param id Element Id.
     */
    private void change(Object element, String id) {
        if      (this.panel.getDiagram().getElement(id) instanceof AttributeUML)
            this.changeAttribute(element, (AttributeUML) this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) instanceof MethodUML)
            this.updateMetodo(element,   (MethodUML)    this.panel.getDiagram().getElement(id));
        else if (this.panel.getDiagram().getElement(id) != null)
            this.updateElemento(element, (Element)      this.panel.getDiagram().getElemento(id));
    }
    
    /**
     * Method responsible for changing the Attribute UML.
     * @param object Graph Object.
     * @param attribute Attribute UML.
     */
    private void changeAttribute(Object object, AttributeUML attribute) {
        mxCell cell      = (mxCell) object;
        String signature = cell.getValue().toString().trim();
        if (this.checkAttribute(signature)) {
            attribute.setVisibilidade(this.getVisibilidade(signature));
            attribute.setNome(this.getNome(signature, ":"));
            attribute.setTipo(this.getTipo(signature));
            this.panel.getViewMenu().getPainelModelagem().updateDiagrama();
            this.panel.getViewMenu().setSalvo(false);
        }
    }
    
    /**
     * Method responsible for checking the Attribute Signature.
     * @param  signature Attribute Signature.
     * @return Attribute Signature checked.
     */
    private boolean checkAttribute(String signature) {
        return     this.checkVisibility(signature) 
               &&  signature.contains(":")
               && !this.getNome(signature, ":").equals("");
    }
    
    /**
     * Metodo responsavel por atualizar o Elemento.
     * @param objeto Objeto do Grafo.
     * @param elemento Elemento.
     */
    private void updateElemento(Object objeto, Elemento elemento) {
        mxCell celula = (mxCell) objeto;
        if (celula.getValue().toString().equals("") == false) {
            elemento.setNome(celula.getValue().toString());
//            this.painel.getViewMenu().getPainelModelagem().getViewMenu().update();
            this.panel.getViewMenu().setSalvo(false);
        }
    }
    
    
    
    /**
     * Metodo responsavel por atualizar o Metodo UML.
     * @param objeto Objeto do Grafo.
     * @param metodo Metodo UML.
     */
    private void updateMetodo(Object objeto, MetodoUML metodo) {
        mxCell celula     = (mxCell) objeto;
        String assinatura = celula.getValue().toString().trim();
        if (this.checkMetodo(assinatura)) {
            metodo.setVisibilidade(this.getVisibilidade(assinatura));
            metodo.setNome(this.getNome(assinatura, "("));
            metodo.setParametros(this.getParametros(assinatura));
            metodo.setRetorno(this.getRetorno(assinatura));
            this.panel.getViewMenu().getPainelModelagem().updateDiagrama();
            this.panel.getViewMenu().setSalvo(false);
        }
    }
    
    /**
     * Metodo responsavel por checar os Simbolos da Assinatura do Metodo.
     * @param  assinatura Assinatura do Metodo.
     * @return Simbolos da Assinatura do Metodo validos.
     */
    private boolean checkMetodo(String assinatura) {
        return     this.checkVisibility(assinatura) 
               &&  assinatura.contains("(")
               &&  assinatura.contains(")")
               &&  assinatura.indexOf("(") < assinatura.indexOf(")")
               && !this.getNome(assinatura, "(").equals("")
               &&  assinatura.contains(":");
    }
    
    /**
     * Metodo responsavel por validar a Visibilidade da Assinatura.
     * @param  assinatura Assinatura.
     * @return Visibilidade da Assinatura valida.
     */
    private boolean checkVisibility(String assinatura) {
        return assinatura.startsWith("+")
            || assinatura.startsWith("-")
            || assinatura.startsWith("#")
            || assinatura.startsWith("~");
    }
    
    /**
     * Metodo responsavel por retornar a Visibilidade pela Assinatura.
     * @param  assinatura Assinatura.
     * @return Visilidade da Assinatura.
     */
    private String getVisibilidade(String assinatura) {
        if (assinatura.startsWith("+"))
            return "public";
        else if (assinatura.startsWith("#"))
            return "protected";
        else if (assinatura.startsWith("-"))
            return "private";
        return "default";
    }
    
    /**
     * Metodo responsavel por retornar o Nome pela Assinatura.
     * @param  assinatura Assinatura.
     * @param  simbolo Simbolo.
     * @return Nome da Assinatura.
     */
    private String getNome(String assinatura, String simbolo) {
        if (assinatura.contains(simbolo))
            return assinatura.substring(1, assinatura.indexOf(simbolo)).trim();
        return "";
    }
    
    /**
     * Metodo responsavel por retornar o Tipo pela Assinatura.
     * @param  assinatura Assinatura.
     * @return Tipo da Assinatura.
     */
    private TipoUML getTipo(String assinatura) {
        if (assinatura.contains(":"))
            return this.panel.getDiagrama().getProjeto().getTipoByNome(assinatura.substring(assinatura.indexOf(":") + 1).trim());
        return this.panel.getDiagrama().getTipoPadrao();
    }
    
    /**
     * Metodo responsavel por retornar a String de Parametros pela Assinatura do Metodo.
     * @param  assinatura Assinatura do Metodo.
     * @return String de Parametros pela Assinatura do Metodo.
     */
    private String getStringParametros(String assinatura) {
        return assinatura.substring(assinatura.indexOf("(") + 1, assinatura.indexOf(")")).trim();
    }
    
    /**
     * Metodo responsavel por retornar a Array de Parametros pela Assinatura do Metodo.
     * @param  assinatura Assinatura do Metodo.
     * @return Array de Parametros pela Assinatura do Metodo.
     */
    private String[] getListaParametros(String assinatura) {
        return this.getStringParametros(assinatura).split("\\,");
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Parametros pela Assinatura do Metodo.
     * @param  assinatura Assinatura do Metodo.
     * @return Lista de Parametros pela Assinatura do Metodo.
     */
    private List<ParametroUML> getParametros(String assinatura) {
        List<ParametroUML> parametros = new ArrayList<>();
        for (String string : this.getListaParametros(assinatura)) {
            ParametroUML parametro = this.getParametro(string.trim());
            if (!parametro.getNome().equals("") && parametro.getTipo() != null)
                parametros.add(parametro);
        }
        return  parametros;
    }
    
    /**
     * Metodo responsavel por retornar o Parametro UML pela String.
     * @param  string String.
     * @return Parametro UML pela String.
     */
    private ParametroUML getParametro(String string) {
        ParametroUML parametro = new ParametroUML();
                     parametro.setNome(this.getNomeParametro(string));
                     parametro.setTipo(this.getTipo(string));
        return       parametro;
    }
    
    /**
     * Metodo responsavel por retornar o Nome pela Assinatura do Parametro.
     * @param  assinatura Assinatura do Parametro.
     * @return Nome pela Assinatura do Parametro.
     */
    private String getNomeParametro(String assinatura) {
        if (assinatura.contains(":"))
            return assinatura.substring(0, assinatura.indexOf(":")).trim();
        return "";
    }
    
    /**
     * Metodo responsavel por retornar o Retorno pela Assinatura.
     * @param  assinatura Assinatura.
     * @return Retorno da Assinatura.
     */
    private TipoUML getRetorno(String assinatura) {
        if (assinatura.contains(":"))
            return this.panel.getDiagrama().getProjeto().getTipoByNome(assinatura.substring(assinatura.lastIndexOf(":") + 1).trim());
        return this.panel.getDiagrama().getTipoPadrao();
    }
    
    /**
     * Method responsible for returning the Element Id by Cell.
     * @param  cell Selected Cell.
     * @return Element Id.
     */
    private String getId(Object cell) {
        if (this.panel.getIdentifiers().get(cell) != null)
            return this.panel.getIdentifiers().get(cell);
        return this.getElementId((mxCell) cell);
    }
    
    /**
     * Method responsible for returning the Element Id by Name Cell.
     * @param  cell Name Cell.
     * @return Element Id.
     */
    private String getElementId(mxCell cell) {
        if (cell != null) {
            if (cell.getId().endsWith("(name)"))
                return cell.getId().substring(0, cell.getId().indexOf("("));
            return cell.getId();
        }
        return "";
    }
}
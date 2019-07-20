package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;

/**
 * <p>Class of Model <b>ComponentDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Component Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/07/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.component.base.ComponentUML
 * @see    model.structural.diagram.component.base.InterfaceUML
 * @see    model.structural.diagram.component.base.association.ComunicationUML
 */
public final class ComponentDiagram extends Diagram {
    private HashMap<String, ComponentUML> components;
    private HashMap<String, InterfaceUML> interfaces;
    private HashMap<String, Association>  comunications;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ComponentDiagram(Project project) {
        super(project);
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ComponentDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }
    
    @Override
    public void init() {
        this.type          = "Component";
        this.components    = new HashMap<>();
        this.interfaces    = new HashMap<>();
        this.comunications = new HashMap<>();
    }
    
    /**
     * Method responsible for returning the next Component Id.
     * @return Next Component Id.
     */
    private String nextComponentId() {
        return this.nextId("COMPONENT#");
    }
    
    /**
     * Method responsible for adding a Component UML.
     * @param componentUML Component UML.
     */
    public void addComponent(ComponentUML componentUML) {
        componentUML.setId(this.nextComponentId());
        if (this.components.get(componentUML.getId()) == null) {
            this.components.put(componentUML.getId(), componentUML);
            this.addElement(componentUML);
        }
    }
    
    /**
     * Method responsible for removing a Component UML.
     * @param componentUML Component UML.
     */
    public void removeComponent(ComponentUML componentUML) {
        this.removeAssociacoes(componentUML);
        this.removeElement(componentUML);
        this.components.remove(componentUML.getId());
    }
    
    /**
     * Method responsible for returning the Components List.
     * @return Components List.
     */
    public List<ComponentUML> getComponents() {
        return new ArrayList<>(this.components.values());
    }
    
    /**
     * Metodo responsavel por retornar o Proximo Id para a Interface UML.
     * @return Proximo Id para a Interface UML.
     */
    private String nextIdInterface() {
        return this.nextId("INTERFACE#");
    }
    
    /**
     * Metodo responsavel por adicionar a Interface UML.
     * @param interfaceUML Interface UML.
     */
    public void addInterface(InterfaceUML interfaceUML) {
        interfaceUML.setId(this.nextIdInterface());
        if (this.interfaces.get(interfaceUML.getId()) == null) {
            this.interfaces.put(interfaceUML.getId(), interfaceUML);
            this.addElemento(interfaceUML);
        }
    }
    
    /**
     * Metodo responsavel por remover a Interface UML.
     * @param interfaceUML Interface UML.
     */
    public void removeInterface(InterfaceUML interfaceUML) {
        this.removeAssociacoes(interfaceUML);
        this.removeElemento(interfaceUML);
        this.interfaces.remove(interfaceUML.getId());
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Interfaces.
     * @return Lista de Interfaces.
     */
    public List<InterfaceUML> getInterfaces() {
        return new ArrayList<>(this.interfaces.values());
    }
    
    /**
     * Metodo responsavel por adicionar a Comunicacao UML.
     * @param comunicacao Comunicacao UML.
     */
    public void addComunicacao(ComunicacaoUML comunicacao) {
        if (this.comunications.get(comunicacao.getId()) == null) {
            this.comunications.put(comunicacao.getId(), comunicacao);
            this.addAssociacao(comunicacao);
        }
    }
    
    /**
     * Metodo responsavel por remover a Comunicacao UML.
     * @param comunicacao Comunicacao UML.
     */
    public void removeComunicacao(ComunicacaoUML comunicacao) {
        this.removeAssociacao(comunicacao);
        this.associacoes.remove(comunicacao.getId());
    }
    
    /**
     * Metodo responsavel por remover as Associacoes pelo Elemento.
     * @param elemento Elemento.
     */
    private void removeAssociacoes(Elemento elemento) {
        this.removeAssociacao(elemento, this.comunications);
    }
    
    /**
     * Metodo responsavel por remover uma Associacao pelo Elemento.
     * @param elemento Elemento.
     * @param map Map de Associacoes.
     */
    private void removeAssociacao(Elemento elemento, Map<String, Associacao> map) {
        for (Associacao associacao : map.values()) {
            if (associacao.getOrigem().equals(elemento)
             || associacao.getDestino().equals(elemento))
                this.removeAssociacao(associacao);
        }
    }
    
    @Override
    public String getIcone() {
        return "src/imagens/icones/diagrama/componentes.png";
    }
    
    @Override
    public ComponentDiagram getClone() {
        try {
            ComponentDiagram diagrama = (ComponentDiagram) super.clone();
                                diagrama.setElementos(new HashMap<>(this.elementos));
                                diagrama.setAssociacoes(new HashMap<>(this.associacoes));
                                diagrama.setVariabilidades(new HashMap<>(this.variabilidades));
            return              diagrama;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Erro");
            return null;
        }
    }
    
    @Override
    public String toString() {
        String diagram  = "Id            = " + this.id            + "\n";
               diagram += "Components    = " + this.components    + "\n";
               diagram += "Interfaces    = " + this.interfaces    + "\n";
               diagram += "Comunications = " + this.comunications + "\n";
        return diagram;
    }
}
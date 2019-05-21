package model.structural;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import model.structural.diagram.classs.TypeUML;
import model.structural.variability.Mutex;
import model.structural.variability.Requires;
import model.structural.variability.Variability;
import org.w3c.dom.Element;

/**
 * <p>Class of Model <b>Diagram</b>.</p>
 * <p>Class responsible for representing <b>Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  20/05/2019
 * @see    model.structural.Exportable
 */
public abstract class Diagram implements Exportable {
    protected Project project;
    protected String  id;
    protected String  name;
    protected String  type;
    protected HashMap elements;
    protected HashMap associations;
    protected HashMap variabilities;
    
    /**
     * Default constructor method of Class.
     */
    public Diagram() {
        this.elements      = new HashMap<>();
        this.associations  = new HashMap<>();
        this.variabilities = new HashMap<>();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     */
    public Diagram(Project project) {
        this();
        this.project = project;
        this.id      = "";
        this.name    = "Diagram";
        this.type    = "";
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element W3C.
     */
    public Diagram(Project project, Element element) {
        this();
        this.project = project;
        this.id      = element.getAttribute("id");
        this.name    = element.getAttribute("name");
        this.type    = element.getAttribute("type");
    }

    /**
     * Method responsible for returning Diagram Project.
     * @return Diagram Project.
     */
    public Project getProject() {
        return this.project;
    }

    /**
     * Method responsible for defining Diagram Project.
     * @param project Diagram Project.
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
    /**
     * Metodo responsavel por retornar o Id do Diagram.
     * @return Id do Diagram.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id do Diagram.
     * @param id Id do Diagram.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome do Diagram.
     * @return Nome do Diagram.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Metodo responsavel por definir o Nome do Diagram.
     * @param name Nome do Diagram.
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo do Diagram.
     * @return Tipo do Diagram.
     */
    public String getType() {
        return this.type;
    }
    
    /**
     * Metodo responsavel por definir o Tipo do Diagram.
     * @param type Tipo do Diagram.
     */
    public void setType(String type) {
        this.type = type;
    }
    
    /**
     * Metodo responsavel por retornar o Proximo Id.
     * @param  rotulo Rotulo do Objeto.
     * @return Proximo Id.
     */
    public String nextId(String rotulo) {
        return this.project.nextId(rotulo);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Elementos.
     * @return Lista de Elementos.
     */
    public HashMap<String, Element> getElementos() {
        return this.elements;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Elementos.
     * @return Lista de Elementos.
     */
    public List<Element> getListaElementos() {
        ArrayList<Element> lista = new ArrayList<>(this.elements.values());
                            lista.sort(this.getComparatorElemento());
        return              lista;
    }
    
    
    public List<Element> filterElementosOpcionais() {
        List<Element> lista  = this.getListaElementos();
        List<Element> filter = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (this.filterVariantes(lista.get(i), "").isEmpty())
                filter.add(lista.get(i));
        }
        return filter;
    }
    
    /**
     * Metodo responsavel por filtrar os Elementos pela flag Obrigatorio.
     * @param  obrigatorio Flag obrigatorio.
     * @return Lista de Elementos filtrados.
     */
    public List<Element> filterElementos(boolean obrigatorio) {
        List<Element> lista  = this.getListaElementos();
        List<Element> filter = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).isObrigatorio() == obrigatorio)
                filter.add(lista.get(i));
        }
        return filter;
    }
    
    /**
     * Metodo responsavel por retornar o Comparator do Element.
     * @return Comparator do Element.
     */
    private Comparator<Element> getComparatorElemento() {
        return new Comparator<Element>() {
            @Override
            public int compare(Element elemento1, Element elemento2) {
                if (elemento1.getTipo().compareTo(elemento2.getTipo()) == 0)
                     return elemento1.getName().compareTo(elemento2.getName());
                return elemento1.getTipo().compareTo(elemento2.getTipo());
            }
        };
    }
    
    /**
     * Metodo responsavel por adicionar um Element.
     * @param elemento Element.
     */
    public void addElemento(Element elemento) {
        this.elements.put(elemento.getId(), elemento);
        this.project.objects.put(elemento.getId(), elemento);
    }
    
    /**
     * Metodo responsavel por atualizar o Nome do Element.
     * @param elemento Element.
     * @param nome Nome do Element.
     */
    public void updateElemento(Element elemento, String nome) {
        String nomeValido = nome.replaceAll(this.getProject().getPerfil().getIdentificadorObrigatorio()     + "\n", "")
                                .replaceAll(this.getProject().getPerfil().getIdentificadorOpcional()        + "\n", "")
                                .replaceAll(this.getProject().getPerfil().getIdentificadorPontoDeVariacao() + "\n", "")
                                .replaceAll(this.getProject().getPerfil().getIdentificadorInclusivo()       + "\n", "")
                                .replaceAll(this.getProject().getPerfil().getIdentificadorExclusivo()       + "\n", "")
                                .replaceAll(this.getProject().getPerfil().getIdentificadorMutex()           + "\n", "")
                                .replaceAll(this.getProject().getPerfil().getIdentificadorRequires()        + "\n", "")
                                .replaceAll(">", "").replaceAll("<", "");
        elemento.setName(nomeValido);
    }
    
    /**
     * Metodo responsavel por retornar um Element pelo Id.
     * @param  id Id do Element.
     * @return Element encontrado.
     */
    public Element getElemento(String id) {
        return (Element) this.elements.get(id);
    }
    
    /**
     * Metodo responsavel por remover um Element.
     * @param elemento Element.
     */
    public void removeElemento(Element elemento) {
        this.removeAssociacao(elemento);
        this.removeVariabilidade(elemento);
        this.project.objects.remove(elemento.getId());
        this.elements.remove(elemento.getId());
    }
    
    /**
     * Metodo responsavel por definir a Lista de Elementos.
     * @param elementos Lista de Elementos.
     */
    public void setElementos(HashMap<String, Element> elementos) {
        this.elements = elementos;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Associacoes.
     * @return Lista de Associacoes.
     */
    public HashMap<String, Association> getAssociacoes() {
        return this.associations;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Associacoes.
     * @return Lista de Associacoes.
     */
    public List<Association> getListaAssociacoes() {
        ArrayList<Association> lista = new ArrayList<>(this.associations.values());
                              lista.sort(this.getComparatorAssociacao());
        return                lista;
    }
    
    /**
     * Metodo responsavel por retornar o Comparator da Association.
     * @return Comparator da Association.
     */
    private Comparator<Association> getComparatorAssociacao() {
        return new Comparator<Association>() {
            @Override
            public int compare(Association associacao1, Association associacao2) {
                if (associacao1.getType().compareTo(associacao2.getType()) == 0)
                     return associacao1.getType().compareTo(associacao2.getType());
                return associacao1.getType().compareTo(associacao2.getType());
            }
        };
    }
    
    /**
     * Metodo responsavel por adicionar o Requires.
     * @param requires Requires.
     */
    public void addRequires(Requires requires) {
        if (this.associations.containsKey(requires.getId()) == false)
            this.addAssociacao(requires);
    }
    
    /**
     * Metodo responsavel por remover o Requires.
     * @param requires Requires.
     */
    public void removeRequires(Requires requires) {
        this.removeAssociacao(requires);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Requires do Diagram.
     * @return Lista de Requires do Diagram.
     */
    public List<Requires> getListaRequires() {
        List<Requires> requires = new ArrayList<>();
        for (int i = 0; i < this.getListaAssociacoes().size(); i++) {
            if (this.getListaAssociacoes().get(i) instanceof Requires)
                requires.add((Requires) this.getListaAssociacoes().get(i));
        }
        return requires;    
    }
    
    /**
     * Metodo responsavel por adicionar o Mutex.
     * @param mutex Mutex.
     */
    public void addMutex(Mutex mutex) {
        if (this.associations.containsKey(mutex.getId()) == false)
            this.addAssociacao(mutex);
    }
    
    /**
     * Metodo responsavel por remover o Mutex.
     * @param mutex Mutex.
     */
    public void removeMutex(Requires mutex) {
        this.removeAssociacao(mutex);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Mutex do Diagram.
     * @return Lista de Mutex do Diagram.
     */
    public List<Mutex> getListaMutex() {
        List<Mutex> mutex = new ArrayList<>();
        for (int i = 0; i < this.getListaAssociacoes().size(); i++) {
            if (this.getListaAssociacoes().get(i) instanceof Mutex)
                mutex.add((Mutex) this.getListaAssociacoes().get(i));
        }
        return mutex;
    }
    
    /**
     * Metodo responsavel por adicionar a Inheritance.
     * @param heranca Inheritance.
     */
    public void addHeranca(Inheritance heranca) {
        if (this.associations.containsKey(heranca.getId()) == false)
            this.addAssociacao(heranca);
    }
    
    /**
     * Metodo responsavel por remover a Inheritance.
     * @param heranca Inheritance.
     */
    public void removeHeranca(Inheritance heranca) {
        this.removeAssociacao(heranca);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Herancas do Diagram.
     * @return Lista de Herancas do Diagram.
     */
    public List<Inheritance> getListaHerancas() {
        List<Inheritance> herancas = new ArrayList<>();
        for (int i = 0; i < this.getListaAssociacoes().size(); i++) {
            if (this.getListaAssociacoes().get(i) instanceof Inheritance)
                herancas.add((Inheritance) this.getListaAssociacoes().get(i));
        }
        return herancas;
    }
    
    /**
     * Metodo responsavel por adicionar uma Association.
     * @param associacao Association.
     */
    public void addAssociacao(Association associacao) {
        this.associations.put(associacao.getId(), associacao);
        this.project.objects.put(associacao.getId(), associacao);
    }
    
    /**
     * Metodo responsavel por retornar uma Association pelo Id.
     * @param  id Id da Association.
     * @return Association encontrada.
     */
    public Association getAssociacao(String id) {
        return (Association) this.associations.get(id);
    }
    
    /**
     * Metodo responsavel por remover uma Association.
     * @param associacao Association.
     */
    public void removeAssociacao(Association associacao) {
        this.project.objects.remove(associacao.getId());
        this.associations.remove(associacao.getId());
    }
    
    /**
     * Metodo responsavel por remover a Association pelo Element.
     * @param elemento Element.
     */
    private void removeAssociacao(Element elemento) {
        for (Association associacao : this.getListaAssociacoes()) {
            if (associacao.contains(elemento))
                this.removeAssociacao(associacao);
        }
    }
    
    /**
     * Metodo responsavel por definir a Lista de Associacoes.
     * @param associacoes Lista de Associacoes.
     */
    public void setAssociacoes(HashMap<String, Association> associacoes) {
        this.associations = associacoes;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Variabilidades.
     * @return Lista de Variabilidades.
     */
    public HashMap<String, Variability> getVariabilidades() {
        return this.variabilities;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Variabilidades.
     * @return Lista de Variabilidades.
     */
    public List<Variability> getListaVariabilidades() {
        ArrayList<Variability> lista = new ArrayList<>(this.variabilities.values());
                                 lista.sort(this.getComparatorVariabilidade());
        return                   lista;
    }
    
    /**
     * Metodo responsavel por retornar o Comparator da Variability.
     * @return Comparator da Variability.
     */
    private Comparator<Variability> getComparatorVariabilidade() {
        return new Comparator<Variability>() {
            @Override
            public int compare(Variability variabilidade1, Variability variabilidade2) {
                return variabilidade1.getName().compareTo(variabilidade2.getName());
            }
        };
    }
    
    /**
     * Metodo responsavel por adicionar uma Variability.
     * @param variabilidade Variability.
     */
    public void addVariabilidade(Variability variabilidade) {
        variabilidade.setId(this.project.nextVariabilityId());
        if (this.variabilities.get(variabilidade.getId()) == null) {
            this.variabilities.put(variabilidade.getId(), variabilidade);
            this.project.variabilities.put(variabilidade.getId(), variabilidade);
        }
    }
    
    /**
     * Metodo responsavel por retornar uma Variability pelo Id.
     * @param  id Id da Variability.
     * @return Variability encontrada.
     */
    public Variability getVariabilidade(String id) {
        return (Variability) this.variabilities.get(id);
    }
    
    /**
     * Metodo responsavel por remover uma Variability.
     * @param variabilidade Variability.
     */
    public void removeVariabilidade(Variability variabilidade) {
        this.project.variabilities.remove(variabilidade.getId());
        this.variabilities.remove(variabilidade.getId());
    }
    
    /**
     * Metodo responsavel por remover a Variability pelo Element.
     * @param elemento Element.
     */
    private void removeVariabilidade(Element elemento) {
        this.removePontoDeVariacao(elemento);
        this.removeVariantes(elemento);
    }
    
    /**
     * Metodo responsavel por remover as Variabilidades pelo Ponto de Variacao.
     * @param elemento Ponto de Variacao.
     */
    private void removePontoDeVariacao(Element elemento) {
        for (Variability variabilidade : this.filterPontosDeVariacao(elemento))
            this.removeVariabilidade(variabilidade);
    }
    
    /**
     * Metodo responsavel por remover a Variante das Variabilidades.
     * @param elemento Variante.
     */
    private void removeVariantes(Element elemento) {
        for (Variability variabilidade : this.filterVariantes(elemento, "")) {
            variabilidade.removeVariant(elemento);
            if (variabilidade.emptyVariants())
                this.removeVariabilidade(variabilidade);
        }
    }
    
    /**
     * Metodo responsavel por definir a Lista de Variabilidades.
     * @param variabilidades Lista de Variabilidades.
     */
    public void setVariabilidades(HashMap<String, Variability> variabilidades) {
        this.variabilities = variabilidades;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo Padrao.
     * @return Tipo Padrao.
     */
    public TypeUML getTipoPadrao() {
        return this.project.getObjectType();
    }
    
    /**
     * Metodo responsavel por retornar o Tipo void.
     * @return Tipo void.
     */
    public TypeUML getTipoVoid() {
        return this.project.getVoidType();
    }
    
    /**
     * Metodo responsavel por retornar os Estereotipos de um Element.
     * @param  elemento Element do Diagram.
     * @return Estereotipos do Element.
     */
    public String getEstereotipos(Element elemento) {
        String estereotipo = this.getEstereotipoOpcional(elemento)
                           + this.getEstereotipoPontoDeVariacao(elemento);
        String inclusivo   = this.getEstereotipoInclusivo(elemento);
        String exclusivo   = this.getEstereotipoExclusivo(elemento);
        String toReturn    = "";
        if (!inclusivo.equals("") || !exclusivo.equals(""))
               toReturn   += estereotipo.replaceAll(this.getProject().getPerfil().getIdentificadorObrigatorio() + "\n", "");
        else
               toReturn   += estereotipo;
        return toReturn   + inclusivo + exclusivo;
    }
    
    /**
     * Metodo responsavel por retornar o Estereotipo Opcional do Element.
     * @param  elemento Element do Diagram.
     * @return Estereotipo Opcional do Element.
     */
    public String getEstereotipoOpcional(Element elemento) {
        if (elemento.isObrigatorio())
            return this.getProject().getPerfil().getIdentificadorObrigatorio() + "\n";
        return this.getProject().getPerfil().getIdentificadorOpcional() + "\n";
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Pontos da Variacao do Element.
     * @param  elemento Element.
     * @return Lista de Pontos da Variacao.
     */
    public List<Variability> filterPontosDeVariacao(Element elemento) {
        List<Variability> lista  = this.getListaVariabilidades();
        List<Variability> filter = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getVariationPoint().equals(elemento))
                filter.add(lista.get(i));
        }
        return filter;
    }
    
    /**
     * Metodo responsavel por retornar o Estereotipo Ponto de Variacao do Element.
     * @param  elemento Element do Diagram.
     * @return Estereotipo Ponto de Variacao do Element.
     */
    public String getEstereotipoPontoDeVariacao(Element elemento) {
        return this.filterPontosDeVariacao(elemento).isEmpty() ? "" : this.getProject().getPerfil().getIdentificadorPontoDeVariacao() + "\n";
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Variantes do Element.
     * @param  elemento Element.
     * @param  tipo Tipo da Variability.
     * @return Lista de Variantes.
     */
    public List<Variability> filterVariantes(Element elemento, String tipo) {
        List<Variability> lista  = this.getListaVariabilidades();
        List<Variability> filter = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getConstraint().toLowerCase().contains(tipo)
            &&  lista.get(i).isVariante(elemento))
                filter.add(lista.get(i));
        }
        return filter;
    }
    
    /**
     * Metodo responsavel por retornar o Estereotipo Inclusivo do Element.
     * @param  elemento Element do Diagram.
     * @return Estereotipo Inclusivo do Element.
     */
    public String getEstereotipoInclusivo(Element elemento) {
        return this.filterVariantes(elemento, "inclusiva").isEmpty() ? "" : this.getProject().getPerfil().getIdentificadorInclusivo() + "\n";
    }
    
    /**
     * Metodo responsavel por retornar o Estereotipo Exclusivo do Element.
     * @param  elemento Element do Diagram.
     * @return Estereotipo Exclusivo do Element.
     */
    public String getEstereotipoExclusivo(Element elemento) {
        return this.filterVariantes(elemento, "exclusiva").isEmpty() ? "" : this.getProject().getPerfil().getIdentificadorExclusivo() + "\n";
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Associacoes pelo Element e Classe.
     * @param  elemento Element.
     * @param  classe Classe da Association.
     * @return Lista de Associacoes.
     */
    public List<Association> filterAssociacoes(Element elemento, Class classe) {
        List<Association> lista  = this.getListaAssociacoes();
        List<Association> filter = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getClass().equals(classe)
            &&  lista.get(i).contains(elemento))
                filter.add(lista.get(i));
        }
        return filter;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Requires pelo Element.
     * @param  elemento Element.
     * @return Lista de Requires.
     */
    public List<Association> filterRequires(Element elemento) {
        return this.filterAssociacoes(elemento, Requires.class);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Mutex pelo Element.
     * @param  elemento Element.
     * @return Lista de Mutex.
     */
    public List<Association> filterMutex(Element elemento) {
        return this.filterAssociacoes(elemento, Mutex.class);
    }
    
    /**
     * Metodo responsavel por Exportar os Dados dos Elementos do Diagram.
     * @return String com os Dados dos Elementos.
     */
    private String exportarElementos() {
        String export  = "";
        for (Element elemento : this.getListaElementos())
               export += elemento.export();
        return export;
    }
    
    /**
     * Metodo responsavel por Exportar os Dados das Associacoes do Diagram.
     * @return String com os Dados das Associacoes.
     */
    private String exportarAssociacoes() {
        String export  = "";
        for (Association associacao : this.getListaAssociacoes())
               export += associacao.export();
        return export;
    }
    
    /**
     * Metodo responsavel por Exportar os Dados das Variabilidades do Diagram.
     * @return String com os Dados das Variabilidades.
     */
    private String exportarVariabilidades() {
        String export  = "";
        for (Variability variabilidade : this.getListaVariabilidades())
               export += variabilidade.exportar();
        return export;
    }
    
    @Override
    public String export() {
        String export  = "  <diagrama id=\"" + this.id + "\" nome=\"" + this.name + "\" tipo=\"" + this.type + "\">\n";
               export += this.exportarElementos();
               export += this.exportarAssociacoes();
               export += this.exportarVariabilidades();
               export += "  </diagrama>\n";
        return export;
    }
    
    /**
     * Metodo responsavel por inicializar os Dados.
     */
    public abstract void init();
    
    /**
     * Metodo responsavel por retornar o Caminho do Icone do Diagram.
     * @return Caminho do Icone do Diagram.
     */
    public abstract String getIcone();
    
    /**
     * Metodo responsavel por retornar um Clone do Diagram.
     * @return Clone do Diagram.
     */
    public abstract Diagram getClone();
    
    @Override
    public String toString() {
        return this.id + " - " + this.name + "(" + this.type + ")";
    }
}
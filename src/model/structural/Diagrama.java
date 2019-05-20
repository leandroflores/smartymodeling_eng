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
 * <p>Classe de Modelo <b>Diagrama</b>.</p>
 * <p>Classe responsavel por representar o <b>Diagrama</b> no SMartyModeling.</p>
 * @author Leandro
 * @since  14/01/2019
 * @see    model.structural.Exportable
 */
public abstract class Diagrama implements Cloneable, Exportable {
    protected Project projeto;
    protected String  id;
    protected String  nome;
    protected String  tipo;
    protected HashMap elementos;
    protected HashMap associacoes;
    protected HashMap variabilidades;
    
    /**
     * Metodo construtor padrao da Classe.
     */
    public Diagrama() {
        this.elementos      = new HashMap<>();
        this.associacoes    = new HashMap<>();
        this.variabilidades = new HashMap<>();
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param projeto Project.
     */
    public Diagrama(Project projeto) {
        this();
        this.projeto = projeto;
        this.id      = "";
        this.nome    = "Diagrama";
        this.tipo    = "";
    }
    
    /**
     * Metodo construtor alternativo da Classe.
     * @param projeto Project.
     * @param elemento Element W3C.
     */
    public Diagrama(Project projeto, Element elemento) {
        this();
        this.projeto = projeto;
        this.id      = elemento.getAttribute("id");
        this.nome    = elemento.getAttribute("nome");
        this.tipo    = elemento.getAttribute("tipo");
    }

    /**
     * Metodo responsavel por retornar o Project do Diagrama.
     * @return Project do Diagrama.
     */
    public Project getProjeto() {
        return this.projeto;
    }

    /**
     * Metodo responsavel por definir o Project do Diagrama.
     * @param projeto Project do Diagrama.
     */
    public void setProjeto(Project projeto) {
        this.projeto = projeto;
    }
    
    /**
     * Metodo responsavel por retornar o Id do Diagrama.
     * @return Id do Diagrama.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Metodo responsavel por definir o Id do Diagrama.
     * @param id Id do Diagrama.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Metodo responsavel por retornar o Nome do Diagrama.
     * @return Nome do Diagrama.
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Metodo responsavel por definir o Nome do Diagrama.
     * @param nome Nome do Diagrama.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo do Diagrama.
     * @return Tipo do Diagrama.
     */
    public String getTipo() {
        return this.tipo;
    }
    
    /**
     * Metodo responsavel por definir o Tipo do Diagrama.
     * @param tipo Tipo do Diagrama.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Metodo responsavel por retornar o Proximo Id.
     * @param  rotulo Rotulo do Objeto.
     * @return Proximo Id.
     */
    public String nextId(String rotulo) {
        return this.projeto.nextId(rotulo);
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Elementos.
     * @return Lista de Elementos.
     */
    public HashMap<String, Element> getElementos() {
        return this.elementos;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Elementos.
     * @return Lista de Elementos.
     */
    public List<Element> getListaElementos() {
        ArrayList<Element> lista = new ArrayList<>(this.elementos.values());
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
        this.elementos.put(elemento.getId(), elemento);
        this.projeto.objects.put(elemento.getId(), elemento);
    }
    
    /**
     * Metodo responsavel por atualizar o Nome do Element.
     * @param elemento Element.
     * @param nome Nome do Element.
     */
    public void updateElemento(Element elemento, String nome) {
        String nomeValido = nome.replaceAll(this.getProjeto().getPerfil().getIdentificadorObrigatorio()     + "\n", "")
                                .replaceAll(this.getProjeto().getPerfil().getIdentificadorOpcional()        + "\n", "")
                                .replaceAll(this.getProjeto().getPerfil().getIdentificadorPontoDeVariacao() + "\n", "")
                                .replaceAll(this.getProjeto().getPerfil().getIdentificadorInclusivo()       + "\n", "")
                                .replaceAll(this.getProjeto().getPerfil().getIdentificadorExclusivo()       + "\n", "")
                                .replaceAll(this.getProjeto().getPerfil().getIdentificadorMutex()           + "\n", "")
                                .replaceAll(this.getProjeto().getPerfil().getIdentificadorRequires()        + "\n", "")
                                .replaceAll(">", "").replaceAll("<", "");
        elemento.setName(nomeValido);
    }
    
    /**
     * Metodo responsavel por retornar um Element pelo Id.
     * @param  id Id do Element.
     * @return Element encontrado.
     */
    public Element getElemento(String id) {
        return (Element) this.elementos.get(id);
    }
    
    /**
     * Metodo responsavel por remover um Element.
     * @param elemento Element.
     */
    public void removeElemento(Element elemento) {
        this.removeAssociacao(elemento);
        this.removeVariabilidade(elemento);
        this.projeto.objects.remove(elemento.getId());
        this.elementos.remove(elemento.getId());
    }
    
    /**
     * Metodo responsavel por definir a Lista de Elementos.
     * @param elementos Lista de Elementos.
     */
    public void setElementos(HashMap<String, Element> elementos) {
        this.elementos = elementos;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Associacoes.
     * @return Lista de Associacoes.
     */
    public HashMap<String, Association> getAssociacoes() {
        return this.associacoes;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Associacoes.
     * @return Lista de Associacoes.
     */
    public List<Association> getListaAssociacoes() {
        ArrayList<Association> lista = new ArrayList<>(this.associacoes.values());
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
        if (this.associacoes.containsKey(requires.getId()) == false)
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
     * Metodo responsavel por retornar a Lista de Requires do Diagrama.
     * @return Lista de Requires do Diagrama.
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
        if (this.associacoes.containsKey(mutex.getId()) == false)
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
     * Metodo responsavel por retornar a Lista de Mutex do Diagrama.
     * @return Lista de Mutex do Diagrama.
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
        if (this.associacoes.containsKey(heranca.getId()) == false)
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
     * Metodo responsavel por retornar a Lista de Herancas do Diagrama.
     * @return Lista de Herancas do Diagrama.
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
        this.associacoes.put(associacao.getId(), associacao);
        this.projeto.objects.put(associacao.getId(), associacao);
    }
    
    /**
     * Metodo responsavel por retornar uma Association pelo Id.
     * @param  id Id da Association.
     * @return Association encontrada.
     */
    public Association getAssociacao(String id) {
        return (Association) this.associacoes.get(id);
    }
    
    /**
     * Metodo responsavel por remover uma Association.
     * @param associacao Association.
     */
    public void removeAssociacao(Association associacao) {
        this.projeto.objects.remove(associacao.getId());
        this.associacoes.remove(associacao.getId());
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
        this.associacoes = associacoes;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Variabilidades.
     * @return Lista de Variabilidades.
     */
    public HashMap<String, Variability> getVariabilidades() {
        return this.variabilidades;
    }
    
    /**
     * Metodo responsavel por retornar a Lista de Variabilidades.
     * @return Lista de Variabilidades.
     */
    public List<Variability> getListaVariabilidades() {
        ArrayList<Variability> lista = new ArrayList<>(this.variabilidades.values());
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
        variabilidade.setId(this.projeto.nextVariabilityId());
        if (this.variabilidades.get(variabilidade.getId()) == null) {
            this.variabilidades.put(variabilidade.getId(), variabilidade);
            this.projeto.variabilities.put(variabilidade.getId(), variabilidade);
        }
    }
    
    /**
     * Metodo responsavel por retornar uma Variability pelo Id.
     * @param  id Id da Variability.
     * @return Variability encontrada.
     */
    public Variability getVariabilidade(String id) {
        return (Variability) this.variabilidades.get(id);
    }
    
    /**
     * Metodo responsavel por remover uma Variability.
     * @param variabilidade Variability.
     */
    public void removeVariabilidade(Variability variabilidade) {
        this.projeto.variabilities.remove(variabilidade.getId());
        this.variabilidades.remove(variabilidade.getId());
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
        this.variabilidades = variabilidades;
    }
    
    /**
     * Metodo responsavel por retornar o Tipo Padrao.
     * @return Tipo Padrao.
     */
    public TypeUML getTipoPadrao() {
        return this.projeto.getObjectType();
    }
    
    /**
     * Metodo responsavel por retornar o Tipo void.
     * @return Tipo void.
     */
    public TypeUML getTipoVoid() {
        return this.projeto.getVoidType();
    }
    
    /**
     * Metodo responsavel por retornar os Estereotipos de um Element.
     * @param  elemento Element do Diagrama.
     * @return Estereotipos do Element.
     */
    public String getEstereotipos(Element elemento) {
        String estereotipo = this.getEstereotipoOpcional(elemento)
                           + this.getEstereotipoPontoDeVariacao(elemento);
        String inclusivo   = this.getEstereotipoInclusivo(elemento);
        String exclusivo   = this.getEstereotipoExclusivo(elemento);
        String toReturn    = "";
        if (!inclusivo.equals("") || !exclusivo.equals(""))
               toReturn   += estereotipo.replaceAll(this.getProjeto().getPerfil().getIdentificadorObrigatorio() + "\n", "");
        else
               toReturn   += estereotipo;
        return toReturn   + inclusivo + exclusivo;
    }
    
    /**
     * Metodo responsavel por retornar o Estereotipo Opcional do Element.
     * @param  elemento Element do Diagrama.
     * @return Estereotipo Opcional do Element.
     */
    public String getEstereotipoOpcional(Element elemento) {
        if (elemento.isObrigatorio())
            return this.getProjeto().getPerfil().getIdentificadorObrigatorio() + "\n";
        return this.getProjeto().getPerfil().getIdentificadorOpcional() + "\n";
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
     * @param  elemento Element do Diagrama.
     * @return Estereotipo Ponto de Variacao do Element.
     */
    public String getEstereotipoPontoDeVariacao(Element elemento) {
        return this.filterPontosDeVariacao(elemento).isEmpty() ? "" : this.getProjeto().getPerfil().getIdentificadorPontoDeVariacao() + "\n";
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
     * @param  elemento Element do Diagrama.
     * @return Estereotipo Inclusivo do Element.
     */
    public String getEstereotipoInclusivo(Element elemento) {
        return this.filterVariantes(elemento, "inclusiva").isEmpty() ? "" : this.getProjeto().getPerfil().getIdentificadorInclusivo() + "\n";
    }
    
    /**
     * Metodo responsavel por retornar o Estereotipo Exclusivo do Element.
     * @param  elemento Element do Diagrama.
     * @return Estereotipo Exclusivo do Element.
     */
    public String getEstereotipoExclusivo(Element elemento) {
        return this.filterVariantes(elemento, "exclusiva").isEmpty() ? "" : this.getProjeto().getPerfil().getIdentificadorExclusivo() + "\n";
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
     * Metodo responsavel por Exportar os Dados dos Elementos do Diagrama.
     * @return String com os Dados dos Elementos.
     */
    private String exportarElementos() {
        String export  = "";
        for (Element elemento : this.getListaElementos())
               export += elemento.export();
        return export;
    }
    
    /**
     * Metodo responsavel por Exportar os Dados das Associacoes do Diagrama.
     * @return String com os Dados das Associacoes.
     */
    private String exportarAssociacoes() {
        String export  = "";
        for (Association associacao : this.getListaAssociacoes())
               export += associacao.export();
        return export;
    }
    
    /**
     * Metodo responsavel por Exportar os Dados das Variabilidades do Diagrama.
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
        String export  = "  <diagrama id=\"" + this.id + "\" nome=\"" + this.nome + "\" tipo=\"" + this.tipo + "\">\n";
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
     * Metodo responsavel por retornar o Caminho do Icone do Diagrama.
     * @return Caminho do Icone do Diagrama.
     */
    public abstract String getIcone();
    
    /**
     * Metodo responsavel por retornar um Clone do Diagrama.
     * @return Clone do Diagrama.
     */
    public abstract Diagrama getClone();
    
    @Override
    public String toString() {
        return this.id + " - " + this.nome + "(" + this.tipo + ")";
    }
}
package view;

/**
 * <p>Interface <b>View</b>.</p>
 * <p>Interface responsavel por definir as <b>Operacoes Basicas</b> das Classes de Visao do Sistema.</p>
 * @author Leandro
 * @since  12/07/2018
 */
public interface InterfaceView {
    
    /**
     * Metodo responsavel por inicializar os componentes de uma View.
     */
    public void initComponents();
     
    /**
     * Metodo responsavel por adicionar o Cabecalho de uma View.
     */
    public void addHeader();
    
    /**
     * Metodo responsavel por adicionar os componentes de uma View.
     */
    public void addComponents();
    
    /**
     * Metodo responsavel por adicionar o Rodape de uma View.
     */
    public void addFooter();
}
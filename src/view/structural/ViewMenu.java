package view.structural;

import controlador.visao.estruturais.ControllerViewMenu;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneLayout;
import model.structural.Diagram;
import model.structural.Project;
import visao.Operacao;
import visao.View;
import visao.ViewStyle;
import visao.painel.modelagem.PainelModelagem;
import visao.painel.principal.PainelPrincipal;
import visao.painel.projeto.PainelProjeto;

/**
 * <p>Class of View <b>ViewMenu</b>.</p>
 * <p>Classe responsavel por definir a <b>Interface de Menu Principal</b> do SMartyModeling.</p>
 * @author Leandro
 * @since  14/01/2018
 * @see    controlador.visao.estruturais.ControllerViewMenu
 * @see    visao.Operacao
 * @see    visao.View
 */
public final class ViewMenu extends View implements Operacao {
    private Project  projeto;
    private Double   zoom;
    private boolean  salvo;
    private JMenuBar menuBar;
    
    private PainelPrincipal painelPrincipal;
    private PainelProjeto   painelProjeto;
    private PainelModelagem painelModelagem;
    
    /**
     * Metodo constutor padrao da Classe.
     */
    public ViewMenu() {
        super();
        this.controller = new ControllerViewMenu(this);
        this.projeto    = null;
        this.zoom       = 1.00d;
        this.salvo      = true;
        this.initComponents();
        this.update();
    }
    
    /**
     * Metodo responsavel por inicializar os componentes da View.
     */
    private void initComponents() {
        this.addKeyListener(this.controller);
//        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle(ViewStyle.SISTEMA + "Menu Principal");
        this.addMenu();
        this.createFileChooserArquivo("fileChooserProjeto");
        this.createFileChooserImage("fileChooserImagem");
        this.addPainelPrincipal();
        this.addPainelProjeto();
        this.addPainelModelagem();
    }
    
    /**
     * Metodo responsavel por adicionar a barra de menus ao Frame.
     */
    private void addMenu() {
        this.menuBar = new JMenuBar();
        
        this.createMenuArquivo();
        this.createMenuDiagrama();
        this.createMenuLinhaProduto();
        this.createMenuSistema();
        
        this.menuBar.add(this.getMenuArquivo());
        this.menuBar.add(this.getMenuDiagrama());
        this.menuBar.add(this.getMenuLinhaProduto());
        this.menuBar.add(this.getMenuSistema());
        
        this.setJMenuBar(this.menuBar);
    }
    
    /**
     * Metodo responsavel por criar o Menu de Arquivo da View Menu.
     */
    private void createMenuArquivo() {
        this.createMenu("menuArquivo", "Arquivo");
        
        this.createMenuItem("menuItemNovoProjeto",   "Novo Projeto",   "arquivo/novo.png");
        this.getMenuItemNovoProjeto().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemAbrirProjeto",  "Abrir Projeto",  "arquivo/abrir.png");
        this.getMenuItemAbrirProjeto().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemSalvarProjeto", "Salvar Projeto", "arquivo/salvar.png");
        this.getMenuItemSalvarProjeto().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemFecharProjeto", "Fechar Projeto", "arquivo/fechar.png");
        this.getMenuItemFecharProjeto().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemSairSistema",   "Sair",           "arquivo/sair.png");
        this.getMenuItemSairSistema().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        
        this.getMenuArquivo().add(this.getMenuItemNovoProjeto());
        this.getMenuArquivo().addSeparator();
        this.getMenuArquivo().add(this.getMenuItemAbrirProjeto());
        this.getMenuArquivo().add(this.getMenuItemSalvarProjeto());
        this.getMenuItemSalvarProjeto().setEnabled(false);
        this.getMenuArquivo().addSeparator();
        this.getMenuArquivo().add(this.getMenuItemFecharProjeto());
        this.getMenuItemFecharProjeto().setEnabled(false);
        this.getMenuArquivo().addSeparator();
        this.getMenuArquivo().add(this.getMenuItemSairSistema());
    }
    
    /**
     * Metodo responsavel por criar o Menu de Diagram da View Menu.
     */
    private void createMenuDiagrama() {
        this.createMenu("menuDiagrama", "Diagrama");
        
        this.createMenuItem("menuItemDiagramaAtividades",  "Diagrama de Atividades",   "diagrama/atividades.png");
        this.createMenuItem("menuItemDiagramaCasosDeUso",  "Diagrama de Casos de Uso", "diagrama/casos-de-uso.png");
        this.createMenuItem("menuItemDiagramaClasses",     "Diagrama de Classes",      "diagrama/classes.png");
        this.createMenuItem("menuItemDiagramaComponentes", "Diagrama de Componentes",  "diagrama/componentes.png");
        this.createMenuItem("menuItemDiagramaSequencia",   "Diagrama de Sequência",    "diagrama/sequencia.png");
        
        this.getMenuDiagrama().add(this.getMenuItemDiagramaAtividades());
        this.getMenuDiagrama().add(this.getMenuItemDiagramaCasosDeUso());
        this.getMenuDiagrama().add(this.getMenuItemDiagramaClasses());
        this.getMenuDiagrama().add(this.getMenuItemDiagramaComponentes());
        this.getMenuDiagrama().add(this.getMenuItemDiagramaSequencia());
    }
    
    /**
     * Metodo responsavel por criar o Menu de Linha de Produto da View Menu.
     */
    private void createMenuLinhaProduto() {
        this.createMenu("menuLinhaProduto", "Linha de Produto");
        
        this.createMenuItem("menuItemInstanciarProduto",        "Instanciar Produto",        "sistema/sobre.png");
        this.createMenuItem("menuItemGerenciarIdentificadores", "Gerenciar Identificadores", "sistema/sobre.png");
        
        this.getMenuLinhaProduto().add(this.getMenuItemInstanciarProduto());
        this.getMenuLinhaProduto().add(this.getMenuItemGerenciarIdentificadores());
    }
    
    /**
     * Metodo responsavel por criar o Menu de Sistema da View Menu.
     */
    private void createMenuSistema() {
        this.createMenu("menuSistema", "Sistema");
        
        this.createMenuItem("menuItemSistemaSobre", "Sobre", "sistema/sobre.png");
        this.createMenuItem("menuItemSistemaSite",  "Site",  "sistema/site.png");
        this.createMenuItem("menuItemSistemaSair",  "Sair",  "sistema/sair.png");
        
        this.getMenuSistema().add(this.getMenuItemSistemaSobre());
        this.getMenuSistema().add(this.getMenuItemSistemaSite());
        this.getMenuSistema().addSeparator();
        this.getMenuSistema().add(this.getMenuItemSistemaSair());
    }
    
    /**
     * Metodo responsavel por atualizar o Titulo da View.
     */
    public void setTitle() {
        if (this.projeto == null)
            this.setTitle(ViewStyle.SISTEMA + "Menu Principal");
        else
            this.setTitle(ViewStyle.SISTEMA + this.projeto.getPath());
    }
    
    /**
     * Metodo responsavel por adicionar o Painel Principal da View Menu.
     */
    private void addPainelPrincipal() {
        this.setTitle();
        this.painelPrincipal = new PainelPrincipal(this);
        this.painelPrincipal.setPreferredSize(new Dimension(1356, 35));
        this.getContentPane().add(this.painelPrincipal);
    }
    
    /**
     * Metodo responsavel por adicionar o Painel do Project.
     */
    private void addPainelProjeto() {
        this.painelProjeto = new PainelProjeto(this);
        this.createScrollPane("scrollPaneProjeto");
        this.getScrollPaneProjeto().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.getScrollPaneProjeto().setPreferredSize(new Dimension(250, 625));
        this.getScrollPaneProjeto().setLayout(new ScrollPaneLayout());
        this.getContentPane().add(this.getScrollPaneProjeto());
    }
    
    /**
     * Metodo responsavel por adicionar o Painel de Modelagem a View Menu.
     */
    private void addPainelModelagem() {
        this.painelModelagem = new PainelModelagem(this);
        this.createScrollPane("scrollPaneModelagem");
        this.getScrollPaneModelagem().setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.getScrollPaneModelagem().setViewportView(this.painelModelagem);
        this.getScrollPaneModelagem().setPreferredSize(new Dimension(1100, 625));
        this.getContentPane().add(this.getScrollPaneModelagem(), BorderLayout.EAST);
    }
    
    /**
     * Metodo Teste por enquanto...
     * @param diagrama 
     */
    public void showDiagrama(Diagram diagrama) {
        this.painelModelagem.addDiagrama(diagrama);
        this.painelModelagem.updateUI();
    }
    
    /**
     * Metodo responsavel por Atualizar os Componentes da View.
     */
    public void update() {
        this.setTitle();
        this.updatePainelPrincipal();
        this.updatePainelProjeto();
        this.updatePainelModelagem();
    }
    
    /**
     * Metodo responsavel por setar o padrao do Painel de Menu.
     */
    private void setPainelMenu() {
        Iterator<JMenuItem> iterator = this.menuItens.values().iterator();
        while (iterator.hasNext())
            iterator.next().setEnabled(true);
    }
    
    /**
     * Metodo responsavel por Bloquear os Menus Itens dos Diagramas.
     */
    private void lockDiagramas() {
        this.setMenuItemDiagramas(false);
    }
    
    /**
     * Metodo responsavel por Desbloquear os Menus Itens dos Diagramas.
     */
    private void unlockDiagramas() {
        this.setMenuItemDiagramas(true);
    }
    
    /**
     * Metodo responsavel por desabilitar os Menus Itens dos Diagramas.
     */
    private void setMenuItemDiagramas(boolean flag) {
        this.getMenuItemDiagramaAtividades().setEnabled(flag);
        this.getMenuItemDiagramaCasosDeUso().setEnabled(flag);
        this.getMenuItemDiagramaClasses().setEnabled(flag);
        this.getMenuItemDiagramaComponentes().setEnabled(flag);
        this.getMenuItemDiagramaSequencia().setEnabled(flag);
        
        this.getMenuItemInstanciarProduto().setEnabled(flag);
        this.getMenuItemGerenciarIdentificadores().setEnabled(flag);
    }
    
    /**
     * Metodo responsavel por setar o padrao dos Paineis nao Salvo.
     */
    public void updateSalvar() {
        if (this.salvo == true) {
            this.getMenuItemSalvarProjeto().setEnabled(false);
            this.getPainelPrincipal().getButtonSalvarProjeto().setEnabled(false);
            this.getPainelPrincipal().getButtonDesfazer().setEnabled(false);
            this.getPainelPrincipal().getButtonRefazer().setEnabled(false);
        }else {
            this.getMenuItemSalvarProjeto().setEnabled(true);
            this.getPainelPrincipal().getButtonSalvarProjeto().setEnabled(true);
        }
    }
    
    /**
     * Metodo responsavel por atualizar o Painel Principal.
     */
    public void updatePainelPrincipal() {
        this.setPainelMenu();
        this.painelPrincipal.upPainelPrincipal();
        if (this.projeto == null)
            this.painelPrincipal.setSemProjeto();
        this.updateSalvar();
    }
    
    /**
     * Metodo responsavel por atualizar o Painel de Project.
     */
    public void updatePainelProjeto() {
        if (this.projeto != null) {
            this.painelProjeto = new PainelProjeto(this);
            this.painelProjeto.init();
            this.getScrollPaneProjeto().setViewportView(this.painelProjeto);
            this.unlockDiagramas();
        }else {
            this.getScrollPaneProjeto().setViewportView(this.createLabel(""));
            this.lockDiagramas();
        }
    }
    
    /**
     * Metodo responsavel por atualizar o Painel de Modelagem.
     */
    public void updatePainelModelagem() {
        if (this.projeto == null)
            this.painelModelagem.clear();
        this.painelModelagem.updateDiagrama();
        this.painelModelagem.updateUI();
    }
    
    @Override
    public void operacao() {
        this.dispose();
    }
    
    /**
     * Metodo responsavel por redefinir o Zoom.
     */
    private void resetZoom() {
        this.getPainelPrincipal().getButtonZoomOriginal().setEnabled(this.zoom != 1.00d);
        this.getPainelPrincipal().getButtonZoomIn().setEnabled(this.zoom < 3.00d);
        this.getPainelPrincipal().getButtonZoomOut().setEnabled(this.zoom > 0.20d);
    }
    
    /**
     * Metodo responsavel por definir o Zoom Padrao.
     */
    public void zoomOriginal() {
        this.zoom = 1.0;
        this.resetZoom();
        this.painelModelagem.setZoom(this.zoom);
    }
    
    /**
     * Metodo responsavel por executar o Zoom In.
     */
    public void zoomIn() {
        this.zoom += 0.10;
        this.zoom  = (this.zoom > 3.00) ? 3.00 : this.zoom;
        this.resetZoom();
        this.painelModelagem.setZoom(this.zoom);
    }
    
    /**
     * Metodo responsavel por executar o Zoom Out.
     */
    public void zoomOut() {
        this.zoom -= 0.10;
        this.zoom  = (this.zoom <= 0.20) ? 0.20 : this.zoom;
        this.resetZoom();
        this.painelModelagem.setZoom(this.zoom);
    }
    
    /**
     * Metodo responsavel por retornar o Controller da ViewMenu.
     * @return Controller da ViewMenu.
     */
    public ControllerViewMenu getController() {
        return (ControllerViewMenu) this.controller;
    }
    
    /**
     * Metodo responsavel por retornar o Project.
     * @return Project.
     */
    public Project getProjeto() {
        return this.projeto;
    }
    
    /**
     * Metodo responsavel por definir o Project.
     * @param projeto Project.
     */
    public void setProjeto(Project projeto) {
        this.projeto = projeto;
    }
    
    /**
     * Metodo responsavel por retornar o JFileChooser Project.
     * @return JFileChooser Project.
     */
    public JFileChooser getFileChooserProjeto() {
        return this.fileChoosers.get("fileChooserProjeto");
    }
    
    /**
     * Metodo responsavel por retornar o JFileChooser Imagem.
     * @return JFileChooser Imagem.
     */
    public JFileChooser getFileChooserImagem() {
        return this.fileChoosers.get("fileChooserImagem");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Arquivo.
     * @return Menu Arquivo.
     */
    public JMenu getMenuArquivo() {
        return this.menus.get("menuArquivo");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Novo Project.
     * @return Menu Item de Novo Project.
     */
    public JMenuItem getMenuItemNovoProjeto() {
        return this.menuItens.get("menuItemNovoProjeto");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Abrir Project.
     * @return Menu Item de Abrir Project.
     */
    public JMenuItem getMenuItemAbrirProjeto() {
        return this.menuItens.get("menuItemAbrirProjeto");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Salvar Project.
     * @return Menu Item de Salvar Project.
     */
    public JMenuItem getMenuItemSalvarProjeto() {
        return this.menuItens.get("menuItemSalvarProjeto");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Fechar Project.
     * @return Menu Item de Fechar Project.
     */
    public JMenuItem getMenuItemFecharProjeto() {
        return this.menuItens.get("menuItemFecharProjeto");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item de Sair do Sistema.
     * @return Menu Item de Sair do Sistema.
     */
    public JMenuItem getMenuItemSairSistema() {
        return this.menuItens.get("menuItemSairSistema");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Diagram.
     * @return Menu Diagram.
     */
    public JMenu getMenuDiagrama() {
        return this.menus.get("menuDiagrama");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Diagram Atividades.
     * @return Menu Item do Diagram Atividades.
     */
    public JMenuItem getMenuItemDiagramaAtividades() {
        return this.menuItens.get("menuItemDiagramaAtividades");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Diagram Casos de Uso.
     * @return Menu Item do Diagram Casos de Uso.
     */
    public JMenuItem getMenuItemDiagramaCasosDeUso() {
        return this.menuItens.get("menuItemDiagramaCasosDeUso");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Diagram Classes.
     * @return Menu Item do Diagram Classes.
     */
    public JMenuItem getMenuItemDiagramaClasses() {
        return this.menuItens.get("menuItemDiagramaClasses");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Diagram Componentes.
     * @return Menu Item do Diagram Componentes.
     */
    public JMenuItem getMenuItemDiagramaComponentes() {
        return this.menuItens.get("menuItemDiagramaComponentes");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Diagram Sequencia.
     * @return Menu Item do Diagram Sequencia.
     */
    public JMenuItem getMenuItemDiagramaSequencia() {
        return this.menuItens.get("menuItemDiagramaSequencia");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Linha de Produto.
     * @return Menu Linha de Produto.
     */
    public JMenu getMenuLinhaProduto() {
        return this.menus.get("menuLinhaProduto");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item Instanciar Produto.
     * @return Menu Item Instanciar Produto.
     */
    public JMenuItem getMenuItemInstanciarProduto() {
        return this.menuItens.get("menuItemInstanciarProduto");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item Gerenciar Identificadores.
     * @return Menu Item Gerenciar Identificadores.
     */
    public JMenuItem getMenuItemGerenciarIdentificadores() {
        return this.menuItens.get("menuItemGerenciarIdentificadores");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Sistema.
     * @return Menu Sistema.
     */
    public JMenu getMenuSistema() {
        return this.menus.get("menuSistema");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Sistema Sobre.
     * @return Menu Item do Sistema Sobre.
     */
    public JMenuItem getMenuItemSistemaSobre() {
        return this.menuItens.get("menuItemSistemaSobre");
    }
    
    /**
     * Metodo responsavel por retornar o Menu Item do Sistema Site.
     * @return Menu Item do Sistema Site.
     */
    public JMenuItem getMenuItemSistemaSite() {
        return this.menuItens.get("menuItemSistemaSite");
    }

    /**
     * Metodo responsavel por retornar o Menu Item do Sistema Sair.
     * @return Menu Item do Sistema Sair.
     */
    public JMenuItem getMenuItemSistemaSair() {
        return this.menuItens.get("menuItemSistemaSair");
    }

    /**
     * Metodo responsavel por retornar a flag Salvo.
     * @return 
     */
    public boolean isSalvo() {
        return salvo;
    }

    /**
     * Metodo responsavel por definir a flag Salvo.
     * @param salvo Flag Salvo.
     */
    public void setSalvo(boolean salvo) {
        this.salvo = salvo;
        this.updateSalvar();
    }
    
    /**
     * Metodo responsavel por retornar o Painel Principal.
     * @return Painel Principal.
     */
    public PainelPrincipal getPainelPrincipal() {
        return this.painelPrincipal;
    }
    
    /**
     * Metodo responsavel por retornar o JScrollPane Project.
     * @return JScrollPane Project.
     */
    public JScrollPane getScrollPaneProjeto() {
        return this.scrollPanes.get("scrollPaneProjeto");
    }
    
    /**
     * Metodo responsavel por retornar o JPanel do Project.
     * @return JPanel do Project.
     */
    public PainelProjeto getPainelProjeto() {
        return this.painelProjeto;
    }
    
    /**
     * Metodo responsavel por retornar o JScrollPane Modelagem.
     * @return JScrollPane do Project.
     */
    public JScrollPane getScrollPaneModelagem() {
        return this.scrollPanes.get("scrollPaneModelagem");
    }
    
    /**
     * Metodo responsavel por retornar o Painel de Modelagem.
     * @return Painel de Modelagem.
     */
    public PainelModelagem getPainelModelagem() {
        return this.painelModelagem;
    }
    
    /**
     * Metodo responsavel por retornar o JPanel do Logo.
     * @return JPanel do Logo.
     */
    public JPanel getPainelLogo() {
        return this.panels.get("painelLogo");
    }
    
    /**
     * Metodo principal do Project.
     * @param args 
     */
    public static void main(String[] args) {
        new ViewMenu().setVisible(true);
    }
}
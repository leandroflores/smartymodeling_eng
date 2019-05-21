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
import view.Operation;
import view.View;
import view.ViewStyle;
import visao.painel.modelagem.PainelModelagem;
import visao.painel.principal.PainelPrincipal;
import visao.painel.projeto.PainelProjeto;

/**
 * <p>Class of View <b>ViewMenu</b>.</p>
 * <p>Class responsible for defining the <b>View Menu</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/05/2019
 * @see    controlador.visao.estruturais.ControllerViewMenu
 * @see    view.Operation
 * @see    view.View
 */
public final class ViewMenu extends View implements Operation {
    private Project  project;
    private Double   zoom;
    private boolean  save;
    private JMenuBar menuBar;
    
//    private PainelPrincipal painelPrincipal;
//    private PainelProjeto   painelProjeto;
//    private PainelModelagem painelModelagem;
    
    /**
     * Default constructor method of Class.
     */
    public ViewMenu() {
        super();
        this.controller = new ControllerViewMenu(this);
        this.project    = null;
        this.zoom       = 1.00d;
        this.save       = true;
        this.initComponents();
        this.update();
    }
    
    /**
     * Method responsible for initializing Components.
     */
    private void initComponents() {
        this.addKeyListener(this.controller);
//        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle(ViewStyle.SYSTEM + "Menu");
        this.addMenu();
        this.createFileChooser("fileChooserProject");
        this.createImageChooser("fileChooserImage");
        
//        this.addPainelPrincipal();
//        this.addPainelProjeto();
//        this.addPainelModelagem();
    }
    
    /**
     * Method responsible for adding Menu Bar to View.
     */
    private void addMenu() {
        this.menuBar = new JMenuBar();
        
        this.createFileMenu();
        this.createDiagramMenu();
        this.createProductLineMenu();
        this.createSystemMenu();
        
        this.menuBar.add(this.getFileMenu());
        this.menuBar.add(this.getMenuDiagram());
        this.menuBar.add(this.getMenuLinhaProduto());
        this.menuBar.add(this.getMenuSistema());
        
        this.setJMenuBar(this.menuBar);
    }
    
    /**
     * Method responsible for creating File Menu.
     */
    private void createFileMenu() {
        this.createMenu("menuFile", "File");
        
        this.createMenuItem("menuItemNewProject",   "New Project",   "file/new.png");
        this.getMenuItemNewProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemOpenProject",  "Open Projeto",  "file/open.png");
        this.getMenuItemOpenProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemSaveProject",  "Save Project",  "file/save.png");
        this.getMenuItemSaveProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemCloseProject", "Close Project", "file/close.png");
        this.getMenuItemCloseProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemExitSystem",   "Exit",          "file/exit.png");
        this.getMenuItemExitSystem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        
        this.getFileMenu().add(this.getMenuItemNewProject());
        this.getFileMenu().addSeparator();
        this.getFileMenu().add(this.getMenuItemOpenProject());
        this.getFileMenu().add(this.getMenuItemSaveProject());
        this.getMenuItemSaveProject().setEnabled(false);
        this.getFileMenu().addSeparator();
        this.getFileMenu().add(this.getMenuItemCloseProject());
        this.getMenuItemCloseProject().setEnabled(false);
        this.getFileMenu().addSeparator();
        this.getFileMenu().add(this.getMenuItemExitSystem());
    }
    
    /**
     * Method responsible for creating Diagram Menu.
     */
    private void createDiagramMenu() {
        this.createMenu("menuDiagram", "Diagram");
        
        this.createMenuItem("menuItemActivityDiagram",  "Activity Diagram",  "diagram/activity.png");
        this.createMenuItem("menuItemClassDiagram",     "Class Diagram",     "diagram/class.png");
        this.createMenuItem("menuItemComponentDiagram", "Component Diagram", "diagram/component.png");
        this.createMenuItem("menuItemSequenceDiagram",  "Sequence Diagram",  "diagram/sequence.png");
        this.createMenuItem("menuItemUseCaseDiagram",   "Use Case Diagram",  "diagram/use-case.png");
        
        this.getMenuDiagram().add(this.getMenuItemActivityDiagram());
        this.getMenuDiagram().add(this.getMenuItemClassDiagram());
        this.getMenuDiagram().add(this.getMenuItemComponentDiagram());
        this.getMenuDiagram().add(this.getMenuItemSequenceDiagram());
        this.getMenuDiagram().add(this.getMenuItemUseCaseDiagram());
    }
    
    /**
     * Method responsible for creating Product Line Menu.
     */
    private void createProductLineMenu() {
        this.createMenu("menuProductLine", "Product Line");
        
        this.createMenuItem("menuItemInstantiateProduct", "Instantiate Product", "system/info.png");
        this.createMenuItem("menuItemManageIdentifiers",  "Manage Identifiers",  "system/info.png");
        
        this.getMenuLinhaProduto().add(this.getMenuItemInstanciarProduto());
        this.getMenuLinhaProduto().add(this.getMenuItemGerenciarIdentificadores());
    }
    
    /**
     * Metodo responsavel por criar o Menu de Sistema da View Menu.
     */
    private void createSystemMenu() {
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
        if (this.project == null)
            this.setTitle(ViewStyle.SISTEMA + "Menu Principal");
        else
            this.setTitle(ViewStyle.SISTEMA + this.project.getPath());
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
        this.getMenuItemActivityDiagram().setEnabled(flag);
        this.getMenuItemUseCaseDiagram().setEnabled(flag);
        this.getMenuItemClassDiagram().setEnabled(flag);
        this.getMenuItemComponentDiagram().setEnabled(flag);
        this.getMenuItemSequenceDiagram().setEnabled(flag);
        
        this.getMenuItemInstanciarProduto().setEnabled(flag);
        this.getMenuItemGerenciarIdentificadores().setEnabled(flag);
    }
    
    /**
     * Metodo responsavel por setar o padrao dos Paineis nao Salvo.
     */
    public void updateSalvar() {
        if (this.save == true) {
            this.getMenuItemSaveProject().setEnabled(false);
            this.getPainelPrincipal().getButtonSalvarProjeto().setEnabled(false);
            this.getPainelPrincipal().getButtonDesfazer().setEnabled(false);
            this.getPainelPrincipal().getButtonRefazer().setEnabled(false);
        }else {
            this.getMenuItemSaveProject().setEnabled(true);
            this.getPainelPrincipal().getButtonSalvarProjeto().setEnabled(true);
        }
    }
    
    /**
     * Metodo responsavel por atualizar o Painel Principal.
     */
    public void updatePainelPrincipal() {
        this.setPainelMenu();
        this.painelPrincipal.upPainelPrincipal();
        if (this.project == null)
            this.painelPrincipal.setSemProjeto();
        this.updateSalvar();
    }
    
    /**
     * Metodo responsavel por atualizar o Painel de Project.
     */
    public void updatePainelProjeto() {
        if (this.project != null) {
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
        if (this.project == null)
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
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Metodo responsavel por definir o Project.
     * @param project Project.
     */
    public void setProject(Project project) {
        this.project = project;
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
     * Method responsible for returning File Menu.
     * @return File Menu.
     */
    public JMenu getFileMenu() {
        return this.menus.get("menuFile");
    }
    
    /**
     * Method responsible for returning Menu Item New Project.
     * @return Menu Item New Project.
     */
    public JMenuItem getMenuItemNewProject() {
        return this.menuItens.get("menuItemNewProject");
    }
    
    /**
     * Method responsible for returning Menu Item Open Project.
     * @return Menu Item Open Project.
     */
    public JMenuItem getMenuItemOpenProject() {
        return this.menuItens.get("menuItemOpenProject");
    }
    
    /**
     * Method responsible for returning Menu Item Save Project.
     * @return Menu Item Save Project.
     */
    public JMenuItem getMenuItemSaveProject() {
        return this.menuItens.get("menuItemSaveProject");
    }
    
    /**
     * Method responsible for returning Menu Item Close Project.
     * @return Menu Item Close Project.
     */
    public JMenuItem getMenuItemCloseProject() {
        return this.menuItens.get("menuItemCloseProject");
    }
    
    /**
     * Method responsible for returning Menu Item Exit System.
     * @return Menu Item Exit System.
     */
    public JMenuItem getMenuItemExitSystem() {
        return this.menuItens.get("menuItemExitSystem");
    }
    
    /**
     * Method responsible for returning Menu Diagram.
     * @return Menu Diagram.
     */
    public JMenu getMenuDiagram() {
        return this.menus.get("menuDiagram");
    }
    
    /**
     * Method responsible for returning Menu Item Activity Diagram.
     * @return Menu Item Activity Diagram.
     */
    public JMenuItem getMenuItemActivityDiagram() {
        return this.menuItens.get("menuItemActivityDiagram");
    }
    
    /**
     * Method responsible for returning Menu Item Class Diagram.
     * @return Menu Item Class Diagram.
     */
    public JMenuItem getMenuItemClassDiagram() {
        return this.menuItens.get("menuItemClassDiagram");
    }
    
    /**
     * Method responsible for returning Menu Item Component Diagram.
     * @return Menu Item Component Diagram.
     */
    public JMenuItem getMenuItemComponentDiagram() {
        return this.menuItens.get("menuItemComponentDiagram");
    }
    
    /**
     * Method responsible for returning Menu Item Sequence Diagram.
     * @return Menu Item Sequence Diagram.
     */
    public JMenuItem getMenuItemSequenceDiagram() {
        return this.menuItens.get("menuItemSequenceDiagram");
    }
    
    /**
     * Method responsible for returning Menu Item Use Case Diagram.
     * @return Menu Item Use Case Diagram.
     */
    public JMenuItem getMenuItemUseCaseDiagram() {
        return this.menuItens.get("menuItemUseCaseDiagram");
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
    public boolean isSave() {
        return save;
    }

    /**
     * Metodo responsavel por definir a flag Salvo.
     * @param save Flag Salvo.
     */
    public void setSave(boolean save) {
        this.save = save;
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
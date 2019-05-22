package view.structural;

import controller.view.structural.ControllerViewMenu;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import model.structural.base.Diagram;
import model.structural.base.Project;
import view.Operation;
import view.View;
import view.ViewStyle;

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
        this.menuBar.add(this.getMenuProductLine());
        this.menuBar.add(this.getMenuSystem());
        
        this.setJMenuBar(this.menuBar);
    }
    
    /**
     * Method responsible for creating File Menu.
     */
    private void createFileMenu() {
        this.createMenu("menuFile", "File");
        
        this.createMenuItem("menuItemNewProject",   "New Project",   "file/new.png");
        this.getMenuItemNewProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        this.createMenuItem("menuItemOpenProject",  "Open Project",  "file/open.png");
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
        
        this.createMenuItem("menuItemInstantiateNewProduct", "Instantiate New Product", "system/info.png");
        this.createMenuItem("menuItemManageIdentifiers",     "Manage Identifiers",      "system/info.png");
        
        this.getMenuProductLine().add(this.getMenuItemInstantiateNewProduct());
        this.getMenuProductLine().add(this.getMenuItemManageIdentifiers());
    }
    
    /**
     * Method responsible for creating System Menu.
     */
    private void createSystemMenu() {
        this.createMenu("menuSystem", "System");
        
        this.createMenuItem("menuItemSystemInfo", "Information", "system/info.png");
        this.createMenuItem("menuItemSystemSite", "Site",        "system/site.png");
        this.createMenuItem("menuItemSystemExit", "Exit",        "system/exit.png");
        
        this.getMenuSystem().add(this.getMenuItemSystemInfo());
        this.getMenuSystem().add(this.getMenuItemSystemSite());
        this.getMenuSystem().addSeparator();
        this.getMenuSystem().add(this.getMenuItemSystemExit());
    }
    
    /**
     * Method responsible for setting View Title.
     */
    public void setTitle() {
        if (this.project == null)
            this.setTitle(ViewStyle.SYSTEM + "Menu");
        else
            this.setTitle(ViewStyle.SYSTEM + this.project.getPath());
    }
    
    /**
     * Method responsible for adding Panel Main on View.
     */
    private void addPanelMain() {
        this.setTitle();
//        this.painelPrincipal = new PainelPrincipal(this);
//        this.painelPrincipal.setPreferredSize(new Dimension(1356, 35));
//        this.getContentPane().add(this.painelPrincipal);
    }
    
    /**
     * Method responsible for adding Panel Project on View.
     */
    private void addPanelProject() {
//        this.painelProjeto = new PainelProjeto(this);
//        this.createScrollPane("scrollPaneProjeto");
//        this.getScrollPaneProjeto().setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        this.getScrollPaneProjeto().setPreferredSize(new Dimension(250, 625));
//        this.getScrollPaneProjeto().setLayout(new ScrollPaneLayout());
//        this.getContentPane().add(this.getScrollPaneProjeto());
    }
    
    /**
     * Method responsible for adding Panel Modeling on View.
     */
    private void addPanelModeling() {
//        this.painelModelagem = new PainelModelagem(this);
//        this.createScrollPane("scrollPaneModelagem");
//        this.getScrollPaneModelagem().setBorder(BorderFactory.createLineBorder(Color.BLACK));
//        this.getScrollPaneModelagem().setViewportView(this.painelModelagem);
//        this.getScrollPaneModelagem().setPreferredSize(new Dimension(1100, 625));
//        this.getContentPane().add(this.getScrollPaneModelagem(), BorderLayout.EAST);
    }
    
    /**
     * Method responsible for showing a Diagram.
     * @param diagram Diagram.
     */
    public void showDiagrama(Diagram diagram) {
//        this.painelModelagem.addDiagrama(diagram);
//        this.painelModelagem.updateUI();
    }
    
    /**
     * Metodo responsavel por Atualizar os Componentes da View.
     */
    public void update() {
        this.setTitle();
        this.updatePanelMain();
        this.updatePanelProject();
        this.updatePanelModeling();
    }
    
    /**
     * Method responsible for setting Painel Default on View.
     */
    private void setPanelMenu() {
        Iterator<JMenuItem> iterator = this.menuItens.values().iterator();
        while (iterator.hasNext())
            iterator.next().setEnabled(true);
    }
    
    /**
     * Method responsible for locking Diagram Menu Items.
     */
    private void lockDiagramas() {
        this.setDiagramMenuItems(false);
    }
    
    /**
     * Method responsible for unlocking Diagram Menu Items.
     */
    private void unlockDiagramas() {
        this.setDiagramMenuItems(true);
    }
    
    /**
     * Method responsible for enabling Diagram Menu Items.
     * @param flag Enable Flag.
     */
    private void setDiagramMenuItems(boolean flag) {
        this.getMenuItemActivityDiagram().setEnabled(flag);
        this.getMenuItemUseCaseDiagram().setEnabled(flag);
        this.getMenuItemClassDiagram().setEnabled(flag);
        this.getMenuItemComponentDiagram().setEnabled(flag);
        this.getMenuItemSequenceDiagram().setEnabled(flag);
        
        this.getMenuItemInstantiateNewProduct().setEnabled(flag);
        this.getMenuItemManageIdentifiers().setEnabled(flag);
    }
    
    /**
     * Metodo responsavel por setar o padrao dos Paineis nao Salvo.
     */
    public void updateSave() {
        if (this.save == true) {
            this.getMenuItemSaveProject().setEnabled(false);
//            this.getPainelPrincipal().getButtonSalvarProjeto().setEnabled(false);
//            this.getPainelPrincipal().getButtonDesfazer().setEnabled(false);
//            this.getPainelPrincipal().getButtonRefazer().setEnabled(false);
        }else {
            this.getMenuItemSaveProject().setEnabled(true);
//            this.getPainelPrincipal().getButtonSalvarProjeto().setEnabled(true);
        }
    }
    
    /**
     * Method responsible for updating Panel Main.
     */
    public void updatePanelMain() {
        this.setPanelMenu();
//        this.painelPrincipal.upPainelPrincipal();
//        if (this.project == null)
//            this.painelPrincipal.setSemProjeto();
        this.updateSave();
    }
    
    /**
     * Method responsible for updating Panel Project.
     */
    public void updatePanelProject() {
        if (this.project != null) {
//            this.painelProjeto = new PainelProjeto(this);
//            this.painelProjeto.init();
//            this.getScrollPaneProjeto().setViewportView(this.painelProjeto);
            this.unlockDiagramas();
        }else {
//            this.getScrollPaneProjeto().setViewportView(this.createLabel(""));
            this.lockDiagramas();
        }
    }
    
    /**
     * Method responsible for updating Panel Modeling.
     */
    public void updatePanelModeling() {
//        if (this.project == null)
//            this.painelModelagem.clear();
//        this.painelModelagem.updateDiagrama();
//        this.painelModelagem.updateUI();
    }
    
    @Override
    public void operation() {
        this.dispose();
    }
    
    /**
     * Method responsible for resetting Zoom.
     */
    private void resetZoom() {
//        this.getPainelPrincipal().getButtonZoomOriginal().setEnabled(this.zoom != 1.00d);
//        this.getPainelPrincipal().getButtonZoomIn().setEnabled(this.zoom < 3.00d);
//        this.getPainelPrincipal().getButtonZoomOut().setEnabled(this.zoom > 0.20d);
    }
    
    /**
     * Method responsible for setting Original Zoom.
     */
    public void setOriginalZoom() {
        this.zoom = 1.0;
        this.resetZoom();
//        this.painelModelagem.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for running Zoom In.
     */
    public void zoomIn() {
        this.zoom += 0.10;
        this.zoom  = (this.zoom > 3.00) ? 3.00 : this.zoom;
        this.resetZoom();
//        this.painelModelagem.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for running Zoom Out.
     */
    public void zoomOut() {
        this.zoom -= 0.10;
        this.zoom  = (this.zoom <= 0.20) ? 0.20 : this.zoom;
        this.resetZoom();
//        this.painelModelagem.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for returning Controller.
     * @return Controller.
     */
    public ControllerViewMenu getController() {
        return (ControllerViewMenu) this.controller;
    }
    
    /**
     * Method responsible for returning Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for defining Project.
     * @param project Project.
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning JFileChooser Project.
     * @return JFileChooser Project.
     */
    public JFileChooser getFileChooserProject() {
        return this.fileChoosers.get("fileChooserProject");
    }
    
    /**
     * Method responsible for returning JFileChooser Image.
     * @return JFileChooser Image.
     */
    public JFileChooser getFileChooserImage() {
        return this.fileChoosers.get("fileChooserImage");
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
     * Method responsible for returning Menu Product Line.
     * @return Menu Product Line.
     */
    public JMenu getMenuProductLine() {
        return this.menus.get("menuProductLine");
    }
    
    /**
     * Method responsible for returning Menu Item Instantiate New Product.
     * @return Menu Item Instantiate New Product.
     */
    public JMenuItem getMenuItemInstantiateNewProduct() {
        return this.menuItens.get("menuItemInstantiateNewProduct");
    }
    
    /**
     * Method responsible for returning Menu Item Manage Identifiers.
     * @return Menu Item Manage Identifiers.
     */
    public JMenuItem getMenuItemManageIdentifiers() {
        return this.menuItens.get("menuItemManageIdentifiers");
    }
    
    /**
     * Method responsible for returning Menu System.
     * @return Menu System.
     */
    public JMenu getMenuSystem() {
        return this.menus.get("menuSystem");
    }
    
    /**
     * Method responsible for returning Menu Item System Info.
     * @return Menu Item System Info.
     */
    public JMenuItem getMenuItemSystemInfo() {
        return this.menuItens.get("menuItemSystemInfo");
    }
    
    /**
     * Method responsible for returning Menu Item System Site.
     * @return Menu Item System Site.
     */
    public JMenuItem getMenuItemSystemSite() {
        return this.menuItens.get("menuItemSystemSite");
    }

    /**
     * Method responsible for returning Menu Item System Exit.
     * @return Menu Item System Exit.
     */
    public JMenuItem getMenuItemSystemExit() {
        return this.menuItens.get("menuItemSystemExit");
    }

    /**
     * Method responsible for returning Save Flag.
     * @return Save Flag.
     */
    public boolean isSave() {
        return this.save;
    }

    /**
     * Method responsible for defining Save Flag.
     * @param save Save Flag.
     */
    public void setSave(boolean save) {
        this.save = save;
        this.updateSave();
    }
    
    /**
     * Metodo responsavel por retornar o Painel Principal.
     * @return Painel Principal.
     */
//    public PainelPrincipal getPainelPrincipal() {
//        return this.painelPrincipal;
//    }
    
    /**
     * Metodo responsavel por retornar o JScrollPane Project.
     * @return JScrollPane Project.
     */
//    public JScrollPane getScrollPaneProjeto() {
//        return this.scrollPanes.get("scrollPaneProjeto");
//    }
    
    /**
     * Metodo responsavel por retornar o JPanel do Project.
     * @return JPanel do Project.
     */
//    public PainelProjeto getPainelProjeto() {
//        return this.painelProjeto;
//    }
    
    /**
     * Metodo responsavel por retornar o JScrollPane Modelagem.
     * @return JScrollPane do Project.
     */
//    public JScrollPane getScrollPaneModelagem() {
//        return this.scrollPanes.get("scrollPaneModelagem");
//    }
    
    /**
     * Metodo responsavel por retornar o Painel de Modelagem.
     * @return Painel de Modelagem.
     */
//    public PainelModelagem getPainelModelagem() {
//        return this.painelModelagem;
//    }
    
    /**
     * Metodo responsavel por retornar o JPanel do Logo.
     * @return JPanel do Logo.
     */
//    public JPanel getPainelLogo() {
//        return this.panels.get("painelLogo");
//    }
    
    /**
     * Metodo principal do Project.
     * @param args 
     */
    public static void main(String[] args) {
        new ViewMenu().setVisible(true);
    }
}
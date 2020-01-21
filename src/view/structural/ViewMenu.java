package view.structural;

import controller.view.structural.ControllerViewMenu;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import view.interfaces.Operation;
import view.View;
import view.ViewStyle;
import view.panel.main.PanelMain;
import view.panel.modeling.PanelModeling;
import view.panel.project.PanelProject;

/**
 * <p>Class of View <b>ViewMenu</b>.</p>
 * <p>Class responsible for defining the <b>View Menu</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  21/05/2019
 * @see    controller.view.structural.ControllerViewMenu
 * @see    view.interfaces.Operation
 * @see    view.View
 */
public final class ViewMenu extends View implements Operation {
    private Project  project;
    private Double   zoom;
    private boolean  save;
    
    private JMenuBar   menuBar;
    private JSplitPane mainSplitPane;
    
    private PanelMain     panelMain;
    private PanelProject  panelProject;
    private PanelModeling panelModeling;
    
    /**
     * Default constructor method of Class.
     */
    public ViewMenu() {
        super();
        this.controller = new ControllerViewMenu(this);
        this.addComponentListener((ComponentListener) this.controller);
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
        this.setTitle(ViewStyle.SYSTEM + "Menu");
        this.addKeyListener(this.controller);
        this.setLayout(new BorderLayout(2, 4));
        this.addMenu();
        this.createFileChooser("fileChooserProject");
        this.createImageChooser("fileChooserImage");
        
        this.addPanelMain();
        this.addPanelProject();
    }
    
    /**
     * Method responsible for adding Menu Bar to View.
     */
    private void addMenu() {
        this.menuBar = new JMenuBar();
        
        this.createFileMenu();
        this.createDiagramMenu();
        this.createProductLineMenu();
        this.createEvaluationMenu();
        this.createExportMenu();
        this.createSystemMenu();
        
        this.menuBar.add(this.getFileMenu());
        this.menuBar.add(this.getMenuDiagram());
        this.menuBar.add(this.getMenuProductLine());
        this.menuBar.add(this.getMenuEvaluation());
        this.menuBar.add(this.getMenuExport());
        this.menuBar.add(this.getMenuSystem());
        
        this.setJMenuBar(this.menuBar);
    }
    
    /**
     * Method responsible for creating File Menu.
     */
    private void createFileMenu() {
        this.createMenu("menuFile", "File");
        
        this.createMenuItem("menuItemNewProject",   "New Project",   "file/new.png");
        this.createMenuItem("menuItemOpenProject",  "Open Project",  "file/open.png");
        this.createMenuItem("menuItemSaveProject",  "Save Project",  "file/save.png");
        this.createMenuItem("menuItemSaveAs",       "Save As",       "file/save.png");
        this.createMenuItem("menuItemCloseProject", "Close Project", "file/close.png");
        this.createMenuItem("menuItemExitSystem",   "Exit",          "file/exit.png");
        
        this.getMenuItemNewProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        this.getMenuItemOpenProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        this.getMenuItemSaveProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        this.getMenuItemCloseProject().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
        this.getMenuItemExitSystem().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
        
        this.getFileMenu().add(this.getMenuItemNewProject());
        this.getFileMenu().addSeparator();
        this.getFileMenu().add(this.getMenuItemOpenProject());
        this.getFileMenu().add(this.getMenuItemSaveProject());
        this.getFileMenu().add(this.getMenuItemSaveAs());
        this.getMenuItemSaveProject().setEnabled(false);
        this.getMenuItemSaveAs().setEnabled(false);
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
        
        this.getMenuItemActivityDiagram().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
        this.getMenuItemClassDiagram().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        this.getMenuItemComponentDiagram().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
        this.getMenuItemSequenceDiagram().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        this.getMenuItemUseCaseDiagram().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
        
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
        
        this.createMenuItem("menuItemEditProfile",        "Edit Profile",        "system/information.png");
        this.createMenuItem("menuItemNewProduct",         "New Product",         "product_line/product.png");
        this.createMenuItem("menuItemInstantiateProduct", "Instantiate Diagram", "system/information.png");
        this.createMenuItem("menuItemNewTraceability",    "New Traceability",    "product_line/traceability.png");
        
        this.getMenuItemEditProfile().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_MASK));
        this.getMenuItemNewProduct().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        this.getMenuItemInstantiateProduct().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
        this.getMenuItemNewTraceability().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
        
        this.getMenuProductLine().add(this.getMenuItemEditProfile());
        this.getMenuProductLine().add(this.getMenuItemNewProduct());
        this.getMenuProductLine().add(this.getMenuItemInstantiateProduct());
        this.getMenuProductLine().add(this.getMenuItemNewTraceability());
    }
    
    /**
     * Method responsible for creating the Evaluation Menu.
     */
    private void createEvaluationMenu() {
        this.createMenu("menuEvaluation", "Evaluation");
        
        this.createMenuItem("menuItemEvaluationMetric",  "New Metric",  "evaluation/metric.png");
        this.createMenuItem("menuItemEvaluationMeasure", "New Measure", "evaluation/measure.png");
        
        this.getMenuEvaluation().add(this.getMenuItemEvaluationMetric());
        this.getMenuEvaluation().add(this.getMenuItemEvaluationMeasure());
    }
    
    /**
     * Method responsible for creating the Export Menu.
     */
    private void createExportMenu() {
        this.createMenu("menuExport", "Export");
        
        this.createMenuItem("menuItemExportDiagram",     "Export Diagram",       "export/diagram.png");
        this.createMenuItem("menuItemExportProduct",      "Export Product",       "export/product.png");
        this.createMenuItem("menuItemExportDiagramCode",  "Export Diagram Code",  "export/code-diagram.png");
        this.createMenuItem("menuItemExportInstanceCode", "Export Instance Code", "export/code-instance.png");
        
        this.getMenuExport().add(this.getMenuItemExportDiagram());
        this.getMenuExport().add(this.getMenuItemExportProduct());
        this.getMenuExport().addSeparator();
        this.getMenuExport().add(this.getMenuItemExportDiagramCode());
        this.getMenuExport().add(this.getMenuItemExportInstanceCode());
    }
    
    /**
     * Method responsible for creating System Menu.
     */
    private void createSystemMenu() {
        this.createMenu("menuSystem", "System");
        
        this.createMenuItem("menuItemSystemInformation", "Information", "system/information.png");
        this.createMenuItem("menuItemSystemSite",        "Site",        "system/site.png");
        this.createMenuItem("menuItemSystemExit",        "Exit",        "system/exit.png");
        
        this.getMenuItemSystemInformation().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
        this.getMenuItemSystemSite().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK));
        this.getMenuItemSystemExit().setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        
        this.getMenuSystem().add(this.getMenuItemSystemInformation());
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

        this.panelMain = new PanelMain(this);
        this.createScrollPane("scrollPanelMain");
        this.getScrollPanelMain().setMinimumSize(new Dimension(200, 50));
        this.getScrollPanelMain().setPreferredSize(new Dimension(200, 50));
        this.getScrollPanelMain().setViewportView(this.panelMain);
        this.getContentPane().add(this.getScrollPanelMain(), BorderLayout.NORTH);
    }
    
    /**
     * Method responsible for returning a new Split Pane.
     * @param  flag Orientation Flag.
     * @return New Split Pane.
     */
    private JSplitPane createSplitPane(boolean flag) {
        JSplitPane split = new JSplitPane();
                   split.setOrientation(flag ? JSplitPane.HORIZONTAL_SPLIT : JSplitPane.VERTICAL_SPLIT);
//                   splitPane.setLayout(new GridLayout(2, 0));
        return     split;
    }
    
    /**
     * Method responsible for adding Panel Project on View.
     */
    private void addPanelProject() {
        this.initPanelProject();
        this.initPanelModeling();
        
        this.getScrollPanelProject().setMinimumSize(new Dimension(200, 100));
        this.getScrollPanelProject().setPreferredSize(new Dimension(250, 100));
//        this.getScrollPanelModeling().setMinimumSize(new Dimension(400, 100));
        
        this.getScrollPanelModeling().setPreferredSize(new Dimension((int) (this.getWidth() * 0.7), this.getHeight() - 100));
//        this.getScrollPanelModeling().setPreferredSize(new Dimension(500, 100));
       
        this.mainSplitPane = this.createSplitPane(true);
        this.mainSplitPane.setLeftComponent(this.getScrollPanelProject());
        this.mainSplitPane.setRightComponent(this.getScrollPanelModeling());
        
        this.getContentPane().add(this.mainSplitPane, BorderLayout.WEST);
    }
    
    /**
     * Method responsible for initializing the Panel Project.
     */
    private void initPanelProject() {
        this.panelProject = new PanelProject(this);
        this.createScrollPane("scrollPanelProject");
//        this.
        this.getScrollPanelProject().setViewportView(this.panelProject);
    }
    
    /**
     * Method responsible for initializing Modeling Panel.
     */
    private void initPanelModeling() {
        this.panelModeling = new PanelModeling(this);
        this.createScrollPane("scrollPanelModeling");
        this.getScrollPanelModeling().setViewportView(this.panelModeling);
    }
    
    /**
     * Method responsible for showing a Diagram.
     * @param diagram Diagram.
     */
    public void showDiagram(Diagram diagram) {
        this.panelModeling.addDiagram(diagram);
        this.panelModeling.updateUI();
    }
    
    /**
     * Method responsible for showing a Instance.
     * @param instance Instance.
     */
    public void showInstance(Instance instance) {
        this.panelModeling.addInstance(instance);
        this.panelModeling.updateUI();
    }
    
    /**
     * Method responsible for updating the View.
     */
    public void update() {
        this.setTitle();
        this.updatePanelMain();
        this.updatePanelTree();
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
        
        this.getMenuItemEditProfile().setEnabled(flag);
        this.getMenuItemNewProduct().setEnabled(flag);
        this.getMenuItemInstantiateProduct().setEnabled(flag);
        this.getMenuItemNewTraceability().setEnabled(flag);
        
        this.getMenuItemExportDiagram().setEnabled(flag);
        this.getMenuItemExportProduct().setEnabled(flag);
        this.getMenuItemExportDiagramCode().setEnabled(flag);
        this.getMenuItemExportInstanceCode().setEnabled(flag);
        
        this.getMenuItemEvaluationMetric().setEnabled(flag);
        this.getMenuItemEvaluationMeasure().setEnabled(flag);
    }
    
    /**
     * Method responsible for setting the Save Project Flag.
     */
    public void updateSave() {
        if (this.save == true) {
            this.getMenuItemSaveProject().setEnabled(false);
            this.getPanelMain().getSaveProjectButton().setEnabled(false);
            this.getPanelMain().getUndoButton().setEnabled(false);
            this.getPanelMain().getRedoButton().setEnabled(false);
        }else {
            this.getMenuItemSaveProject().setEnabled(true);
            this.getMenuItemSaveAs().setEnabled(true);
            this.getPanelMain().getSaveProjectButton().setEnabled(true);
        }
    }
    
    /**
     * Method responsible for updating Panel Main.
     */
    public void updatePanelMain() {
        this.setPanelMenu();
        this.panelMain.activate();
        if (this.project == null)
            this.panelMain.setNoProject();
        this.updateSave();
    }
    
    /**
     * Method responsible for updating Panel Tree.
     */
    public void updatePanelTree() {
        if (this.project != null) {
            this.panelProject = new PanelProject(this);
            this.getScrollPanelProject().setViewportView(this.panelProject);
            this.unlockDiagramas();
        }else {
            this.getScrollPanelProject().setViewportView(this.createLabel(""));
            this.lockDiagramas();
        }
    }
    
    /**
     * Method responsible for updating Panel Modeling.
     */
    public void updatePanelModeling() {
        if (this.project == null)
            this.panelModeling.clear();
        this.panelModeling.updateModelingPanel();
        this.panelModeling.updateUI();
    }
    
    @Override
    public void operation() {
        this.dispose();
    }
    
    /**
     * Method responsible for resetting Zoom.
     */
    private void resetZoom() {
        this.getPanelMain().getOriginalZoomButton().setEnabled(this.zoom != 1.00d);
        this.getPanelMain().getZoomInButton().setEnabled(this.zoom  < 3.00d);
        this.getPanelMain().getZoomOutButton().setEnabled(this.zoom > 0.20d);
    }
    
    /**
     * Method responsible for setting Original Zoom.
     */
    public void setOriginalZoom() {
        this.zoom = 1.0;
        this.resetZoom();
        this.panelModeling.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for running Zoom In.
     */
    public void zoomIn() {
        this.zoom += 0.10;
        this.zoom  = (this.zoom > 3.00) ? 3.00 : this.zoom;
        this.resetZoom();
        this.panelModeling.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for running Zoom Out.
     */
    public void zoomOut() {
        this.zoom -= 0.10;
        this.zoom  = (this.zoom <= 0.20) ? 0.20 : this.zoom;
        this.resetZoom();
        this.panelModeling.setZoom(this.zoom);
    }
    
    @Override
    protected JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = super.createScrollPane(id);
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        return      scrollPane;
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
     * Method responsible for returning Menu Item Save As.
     * @return Menu Item Save As.
     */
    public JMenuItem getMenuItemSaveAs() {
        return this.menuItens.get("menuItemSaveAs");
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
     * Method responsible for returning the Menu Item Edit Profile.
     * @return Menu Item Edit Profile.
     */
    public JMenuItem getMenuItemEditProfile() {
        return this.menuItens.get("menuItemEditProfile");
    }
    
    /**
     * Method responsible for returning the Menu Item New Product.
     * @return Menu Item New Product.
     */
    public JMenuItem getMenuItemNewProduct() {
        return this.menuItens.get("menuItemNewProduct");
    }
    
    /**
     * Method responsible for returning the Menu Item Instantiate Product.
     * @return Menu Item Instantiate Product.
     */
    public JMenuItem getMenuItemInstantiateProduct() {
        return this.menuItens.get("menuItemInstantiateProduct");
    }
    
    /**
     * Method responsible for returning the Menu Item New Traceability.
     * @return Menu Item New Traceability.
     */
    public JMenuItem getMenuItemNewTraceability() {
        return this.menuItens.get("menuItemNewTraceability");
    }
    
    /**
     * Method responsible for returning Menu Evaluation.
     * @return Menu Evaluation.
     */
    public JMenu getMenuEvaluation() {
        return this.menus.get("menuEvaluation");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluation Metric.
     * @return Menu Item Evaluation Metric.
     */
    public JMenuItem getMenuItemEvaluationMetric() {
        return this.menuItens.get("menuItemEvaluationMetric");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluation Measure.
     * @return Menu Item Evaluation Measure.
     */
    public JMenuItem getMenuItemEvaluationMeasure() {
        return this.menuItens.get("menuItemEvaluationMeasure");
    }
    
    /**
     * Method responsible for returning the Menu Export.
     * @return Menu Export.
     */
    public JMenu getMenuExport() {
        return this.menus.get("menuExport");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Diagram.
     * @return Menu Item Export Diagram.
     */
    public JMenuItem getMenuItemExportDiagram() {
        return this.menuItens.get("menuItemExportDiagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Product.
     * @return Menu Item Export Product.
     */
    public JMenuItem getMenuItemExportProduct() {
        return this.menuItens.get("menuItemExportProduct");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Diagram Code.
     * @return Menu Item Export Diagram Code.
     */
    public JMenuItem getMenuItemExportDiagramCode() {
        return this.menuItens.get("menuItemExportDiagramCode");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Instance Code.
     * @return Menu Item Export Instance Code.
     */
    public JMenuItem getMenuItemExportInstanceCode() {
        return this.menuItens.get("menuItemExportInstanceCode");
    }
    
    /**
     * Method responsible for returning Menu System.
     * @return Menu System.
     */
    public JMenu getMenuSystem() {
        return this.menus.get("menuSystem");
    }
    
    /**
     * Method responsible for returning Menu Item System Information.
     * @return Menu Item System Information.
     */
    public JMenuItem getMenuItemSystemInformation() {
        return this.menuItens.get("menuItemSystemInformation");
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
     * Method responsible for returning Panel Main.
     * @return Panel Main.
     */
    public PanelMain getPanelMain() {
        return this.panelMain;
    }
    
    /**
     * Method responsible for returning Panel Main Scroll.
     * @return Panel Main Scroll.
     */
    public JScrollPane getScrollPanelMain() {
        return this.scrollPanes.get("scrollPanelMain");
    }
    
    /**
     * Method responsible for returning the Panel Project.
     * @return Panel Project.
     */
    public PanelProject getPanelProject() {
        return this.panelProject;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Project.
     * @return Scroll Panel Project.
     */
    public JScrollPane getScrollPanelProject() {
        return this.scrollPanes.get("scrollPanelProject");
    }
    
    /**
     * Method responsible for returning Panel Modeling.
     * @return Panel Modeling.
     */
    public PanelModeling getPanelModeling() {
        return this.panelModeling;
    }
    
    /**
     * Method responsible for returning Panel Modeling Scroll.
     * @return Panel Modeling Scroll.
     */
    public JScrollPane getScrollPanelModeling() {
        return this.scrollPanes.get("scrollPanelModeling");
    }
    
    /**
     * Main Method of SMartyModeling.
     * @param args 
     */
    public static void main(String[] args) {
        new ViewMenu().setVisible(true);
    }
}
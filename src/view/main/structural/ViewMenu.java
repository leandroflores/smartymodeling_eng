package view.main.structural;

import controller.view.main.structural.ControllerViewMenu;
import file.importation.ImportProject;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import org.xml.sax.SAXException;
import view.interfaces.Operation;
import view.View;
import view.style.ViewStyle;
import view.panel.main.PanelMain;
import view.panel.modeling.PanelModeling;
import view.panel.project.PanelProject;

/**
 * <p>Class of View <b>ViewMenu</b>.</p>
 * <p>Class responsible for defining the <b>View Menu</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-21
 * @see    controller.view.main.structural.ControllerViewMenu
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
        this.project    = null;
        this.zoom       = 1.00d;
        this.save       = true;
        this.addComponentListener((ComponentListener) this.controller);
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
        this.createFileChooser("fileChooserOpenProject");
        this.createFileChooser("fileChooserSaveProject");
        this.createImageChooser("fileChooserImage");
        this.createPdfChooser("fileChooserPdf");
        
        this.addPanelMain();
        this.addPanelProject();
    }
    
    /**
     * Method responsible for adding Menu Bar to View.
     */
    private void addMenu() {
        this.menuBar = new JMenuBar();
        
        this.createFileMenu();
        this.createRequirementMenu();
        this.createDiagramMenu();
        this.createProductLineMenu();
        this.createEvaluationMenu();
        this.createExportMenu();
        this.createAboutMenu();
        
        this.menuBar.add(this.getMenu("file"));
        this.menuBar.add(this.getMenu("requirements"));
        this.menuBar.add(this.getMenu("diagram"));
        this.menuBar.add(this.getMenu("productLine"));
        this.menuBar.add(this.getMenu("evaluation"));
        this.menuBar.add(this.getMenu("export"));
        this.menuBar.add(this.getMenu("about"));
        
        this.setJMenuBar(this.menuBar);
    }
    
    /**
     * Method responsible for creating File Menu.
     */
    private void createFileMenu() {
        this.createMenu("file", "File");
        
        this.createMenuItem("new_project",   "New Project",   "menu/file/new.png",  KeyEvent.VK_N);
        this.createMenuItem("open_project",  "Open Project",  "menu/file/open.png", KeyEvent.VK_O);
        this.createMenuItem("save_project",  "Save Project",  "menu/file/save.png", KeyEvent.VK_S);
        this.createMenuItem("save_as",       "Save As",       "menu/file/save.png");
        this.createMenuItem("close_project", "Close Project", "menu/file/close.png");
        this.createMenuItem("exit_system",   "Exit",          "menu/file/exit.png", KeyEvent.VK_Q);
        
        this.getMenu("file").add(this.getMenuItemNewProject());
        this.getMenu("file").addSeparator();
        this.getMenu("file").add(this.getMenuItemOpenProject());
        this.getMenu("file").add(this.getMenuItemSaveProject());
        this.getMenu("file").add(this.getMenuItemSaveAs());
        this.getMenuItemSaveProject().setEnabled(false);
        this.getMenuItemSaveAs().setEnabled(false);
        this.getMenu("file").addSeparator();
        this.getMenu("file").add(this.getMenuItemCloseProject());
        this.getMenuItemCloseProject().setEnabled(false);
        this.getMenu("file").addSeparator();
        this.getMenu("file").add(this.getMenuItemExitSystem());
    }
    
    /**
     * Method responsible for creating the Requirement Menu.
     */
    private void createRequirementMenu() {
        this.createMenu("requirements", "Requirements");
        
        this.createMenuItem("new_requirement",          "New Requirement",          "menu/requirement/requirement.png",  KeyEvent.VK_R);
        this.createMenuItem("requirements_traceability", "Requirements Traceability", "menu/requirement/traceability.png", KeyEvent.VK_T);
        this.createMenuItem("requirements_matriz",       "Requirements Matrix",       "menu/requirement/matrix.png",       KeyEvent.VK_X);
        
        this.getMenu("requirements").add(this.getMenuItemNewRequirement());
        this.getMenu("requirements").addSeparator();
        this.getMenu("requirements").add(this.getMenuItemRequirementsTraceability());
        this.getMenu("requirements").add(this.getMenuItemRequirementsMatrix());
    }
    
    /**
     * Method responsible for creating Diagram Menu.
     */
    private void createDiagramMenu() {
        this.createMenu("diagram", "New Diagram");
        
        this.createMenuItem("feature_diagram",   "Feature Diagram",   "menu/diagram/feature.png",   KeyEvent.VK_F);
        this.createMenuItem("usecase_diagram",   "Use Case Diagram",  "menu/diagram/use-case.png",  KeyEvent.VK_U);
        this.createMenuItem("class_diagram",     "Class Diagram",     "menu/diagram/class.png",     KeyEvent.VK_C);
        this.createMenuItem("component_diagram", "Component Diagram", "menu/diagram/component.png", KeyEvent.VK_M);
        this.createMenuItem("sequence_diagram",  "Sequence Diagram",  "menu/diagram/sequence.png",  KeyEvent.VK_E);
        this.createMenuItem("activity_diagram",  "Activity Diagram",  "menu/diagram/activity.png",  KeyEvent.VK_A);
        
        this.getMenu("diagram").add(this.getMenuItemFeatureDiagram());
        this.getMenu("diagram").addSeparator();
        this.getMenu("diagram").add(this.getMenuItemUseCaseDiagram());
        this.getMenu("diagram").add(this.getMenuItemClassDiagram());
        this.getMenu("diagram").add(this.getMenuItemComponentDiagram());
        this.getMenu("diagram").add(this.getMenuItemSequenceDiagram());
        this.getMenu("diagram").add(this.getMenuItemActivityDiagram());
    }
    
    /**
     * Method responsible for creating Product Line Menu.
     */
    private void createProductLineMenu() {
        this.createMenu("productLine", "SPL Profile");
        
        this.createMenuItem("edit_profile",     "Edit Profile",     "menu/product_line/profile.png",      KeyEvent.VK_L);
        this.createMenuItem("new_product",      "New Product",      "menu/product_line/product.png",      KeyEvent.VK_P);
        this.createMenuItem("new_instance",     "New Instance",     "menu/product_line/instance.png",     KeyEvent.VK_I);
        this.createMenuItem("new_traceability", "New Traceability", "menu/product_line/traceability.png", KeyEvent.VK_Y);
        
        this.getMenu("productLine").add(this.getMenuItemEditProfile());
        this.getMenu("productLine").addSeparator();
        this.getMenu("productLine").add(this.getMenuItemNewProduct());
        this.getMenu("productLine").add(this.getMenuItemNewInstance());
        this.getMenu("productLine").add(this.getMenuItemNewTraceability());
    }
    
    /**
     * Method responsible for creating the Evaluation Menu.
     */
    private void createEvaluationMenu() {
        this.createMenu("evaluation", "Evaluation");
        
        this.createMenuItemAlt("new_metric",  "New Metric",  "menu/evaluation/metric.png",  KeyEvent.VK_M);
        this.createMenuItemAlt("new_measure", "New Measure", "menu/evaluation/measure.png", KeyEvent.VK_S);
        
        this.createMenuItemAlt("evaluate_project", "Evaluate Project", "menu/evaluation/project.png", KeyEvent.VK_P);
        this.createMenuItemAlt("evaluate_diagram", "Evaluate Diagram", "menu/evaluation/diagram.png", KeyEvent.VK_D);
        this.createMenuItemAlt("evaluate_product", "Evaluate Product", "menu/evaluation/product.png", KeyEvent.VK_R);
        
        this.getMenu("evaluation").add(this.getMenuItemNewMetric());
        this.getMenu("evaluation").add(this.getMenuItemNewMeasure());
        this.getMenu("evaluation").addSeparator();
        this.getMenu("evaluation").add(this.getMenuItemEvaluateProject());
        this.getMenu("evaluation").add(this.getMenuItemEvaluateDiagram());
        this.getMenu("evaluation").add(this.getMenuItemEvaluateProduct());
    }
    
    /**
     * Method responsible for creating the Export Menu.
     */
    private void createExportMenu() {
        this.createMenu("export", "Export");
        
        this.createMenuItem("export_diagram", "Export Diagram", "menu/export/diagram.png");
        this.createMenuItem("export_product", "Export Product", "menu/export/product.png");
        
        this.createMenuItem("diagram_code",   "Export Diagram Code",  "menu/export/code-diagram.png");
        this.createMenuItem("instance_code",  "Export Instance Code", "menu/export/code-instance.png");
        
        this.getMenu("export").add(this.getMenuItemExportDiagram());
        this.getMenu("export").add(this.getMenuItemExportProduct());
        this.getMenu("export").addSeparator();
        this.getMenu("export").add(this.getMenuItemExportDiagramCode());
        this.getMenu("export").add(this.getMenuItemExportInstanceCode());
    }
    
    /**
     * Method responsible for creating the About Menu.
     */
    private void createAboutMenu() {
        this.createMenu("about", "About");
        
        this.createMenuItem("about_info", "Information", "menu/about/information.png", KeyEvent.VK_F2);
        this.createMenuItem("about_site",  "Site",       "menu/about/site.png", KeyEvent.VK_W);
        this.createMenuItem("about_exit",  "Exit",       "menu/about/exit.png", KeyEvent.VK_F4);
        
        this.getMenu("about").add(this.getMenuItemAboutInformation());
        this.getMenu("about").add(this.getMenuItemAboutSite());
        this.getMenu("about").addSeparator();
        this.getMenu("about").add(this.getMenuItemAboutExit());
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
        this.getScrollPane("scrollPanelMain").setMinimumSize(new Dimension(200, 35));
        this.getScrollPane("scrollPanelMain").setPreferredSize(new Dimension(200, 35));
        this.getScrollPane("scrollPanelMain").setViewportView(this.panelMain);
        this.getContentPane().add(this.getScrollPane("scrollPanelMain"), BorderLayout.NORTH);
    }
    
    /**
     * Method responsible for returning a new Split Pane.
     * @param  flag Orientation Flag.
     * @return New Split Pane.
     */
    private JSplitPane createSplitPane(boolean flag) {
        JSplitPane split = new JSplitPane();
                   split.setOrientation(flag ? JSplitPane.HORIZONTAL_SPLIT : JSplitPane.VERTICAL_SPLIT);
        return     split;
    }
    
    /**
     * Method responsible for adding Panel Project on View.
     */
    private void addPanelProject() {
        this.initPanelProject();
        this.initPanelModeling();
        
        this.getScrollPane("scrollPanelProject").setMinimumSize(new Dimension(325, 150));
        this.getScrollPane("scrollPanelProject").setPreferredSize(new Dimension(325, 150));
        this.getScrollPane("scrollPanelModeling").setPreferredSize(new Dimension((int) (this.getWidth() - 375), this.getHeight() - 100));
        
        this.mainSplitPane = this.createSplitPane(true);
        this.mainSplitPane.setLeftComponent(this.getScrollPane("scrollPanelProject"));
        this.mainSplitPane.setRightComponent(this.getScrollPane("scrollPanelModeling"));
        
        this.getContentPane().add(this.mainSplitPane, BorderLayout.WEST);
    }
    
    /**
     * Method responsible for initializing the Panel Project.
     */
    private void initPanelProject() {
        this.panelProject = new PanelProject(this);
        this.createScrollPane("scrollPanelProject");
        this.getScrollPane("scrollPanelProject").setViewportView(this.panelProject);
    }
    
    /**
     * Method responsible for initializing Modeling Panel.
     */
    private void initPanelModeling() {
        this.panelModeling = new PanelModeling(this);
        this.createScrollPane("scrollPanelModeling");
        this.getScrollPane("scrollPanelModeling").setViewportView(this.panelModeling);
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
        Iterator<JMenuItem> iterator = this.getMenuItems().values().iterator();
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
        this.getMenuItemFeatureDiagram().setEnabled(flag);
        
        this.getMenuItemNewRequirement().setEnabled(flag);
        this.getMenuItemRequirementsTraceability().setEnabled(flag);
        this.getMenuItemRequirementsMatrix().setEnabled(flag);
        
        this.getMenuItemActivityDiagram().setEnabled(flag);
        this.getMenuItemUseCaseDiagram().setEnabled(flag);
        this.getMenuItemClassDiagram().setEnabled(flag);
        this.getMenuItemComponentDiagram().setEnabled(flag);
        this.getMenuItemSequenceDiagram().setEnabled(flag);
        
        this.getMenuItemEditProfile().setEnabled(flag);
        this.getMenuItemNewProduct().setEnabled(flag);
        this.getMenuItemNewInstance().setEnabled(flag);
        this.getMenuItemNewTraceability().setEnabled(flag);
        
        this.getMenuItemExportDiagram().setEnabled(flag);
        this.getMenuItemExportProduct().setEnabled(flag);
        this.getMenuItemExportDiagramCode().setEnabled(flag);
        this.getMenuItemExportInstanceCode().setEnabled(flag);
        
        this.getMenuItemNewMetric().setEnabled(flag);
        this.getMenuItemNewMeasure().setEnabled(flag);
        this.getMenuItemEvaluateProject().setEnabled(flag);
        this.getMenuItemEvaluateDiagram().setEnabled(flag);
        this.getMenuItemEvaluateProduct().setEnabled(flag);
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
        else
            this.getPanelModeling().updatePanelMain();
        this.updateSave();
    }
    
    /**
     * Method responsible for updating Panel Tree.
     */
    public void updatePanelTree() {
        if (this.project != null) {
            Integer index     = this.getTabIndex();
            this.panelProject = new PanelProject(this);
            this.getScrollPane("scrollPanelProject").setViewportView(this.panelProject);
            this.unlockDiagramas();
            this.panelProject.getPanelTree().getTabbedPane().setSelectedIndex(index);
        }else {
            this.getScrollPane("scrollPanelProject").setViewportView(this.createLabel(""));
            this.lockDiagramas();
        }
    }
    
    /**
     * Method responsible for returning the Tab Index.
     * @return Tab Index.
     */
    public Integer getTabIndex() {
        Integer index = this.panelProject.getPanelTree().getTabbedPane().getSelectedIndex();
        return  index < 0 ? 0 : index;
    }
    
    /**
     * Method responsible for setting the Tab Index.
     * @param index Tab Index.
     */
    public void setTabIndex(Integer index) {
        this.panelProject.getPanelTree().getTabbedPane().setSelectedIndex(index);
    }
    
    /**
     * Method responsible for updating Panel Modeling.
     */
    public void updatePanelModeling() {
        if (this.project == null)
            this.panelModeling.clear();
        this.panelModeling.updateModelingPanels();
        this.panelModeling.updateUI();
    }
    
    @Override
    public void operation() {
        this.dispose();
    }
    
    /**
     * Method responsible for resetting the Zoom.
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
        this.zoom  = this.getPanelModeling().getSelectedPanel().getZoom();
        this.zoom += 0.10;
        this.zoom  = (this.zoom > 3.00) ? 3.00 : this.zoom;
        this.resetZoom();
        this.panelModeling.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for running Zoom Out.
     */
    public void zoomOut() {
        this.zoom  = this.getPanelModeling().getSelectedPanel().getZoom();
        this.zoom -= 0.10;
        this.zoom  = (this.zoom <= 0.20) ? 0.20 : this.zoom;
        this.resetZoom();
        this.panelModeling.setZoom(this.zoom);
    }
    
    /**
     * Method responsible for setting the Zoom Value.
     * @param zoom Zoom Value.
     */
    public void setZoom(Double zoom) {
        this.zoom = zoom;
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerViewMenu getController() {
        return (ControllerViewMenu) this.controller;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return this.project;
    }
    
    /**
     * Method responsible for defining the Project.
     * @param project Project.
     */
    public void setProject(Project project) {
        this.project = project;
    }
    
    /**
     * Method responsible for returning the File Chooser Open Project.
     * @return File Chooser Open Project.
     */
    public JFileChooser getFileChooserOpenProject() {
        return this.getFileChooser("fileChooserOpenProject");
    }
    
    /**
     * Method responsible for returning the File Chooser Save Project.
     * @return File Chooser Save Project.
     */
    public JFileChooser getFileChooserSaveProject() {
        return this.getFileChooser("fileChooserSaveProject");
    }
    
    /**
     * Method responsible for returning the File Chooser Image.
     * @return File Chooser Image.
     */
    public JFileChooser getFileChooserImage() {
        return this.getFileChooser("fileChooserImage");
    }
    
    /**
     * Method responsible for returning the File Chooser Pdf.
     * @return File Chooser Pdf.
     */
    public JFileChooser getFileChooserPdf() {
        return this.getFileChooser("fileChooserPdf");
    }
    
    /**
     * Method responsible for returning the Menu Item New Project.
     * @return Menu Item New Project.
     */
    public JMenuItem getMenuItemNewProject() {
        return this.getMenuItem("new_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Open Project.
     * @return Menu Item Open Project.
     */
    public JMenuItem getMenuItemOpenProject() {
        return this.getMenuItem("open_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Save Project.
     * @return Menu Item Save Project.
     */
    public JMenuItem getMenuItemSaveProject() {
        return this.getMenuItem("save_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Save As.
     * @return Menu Item Save As.
     */
    public JMenuItem getMenuItemSaveAs() {
        return this.getMenuItem("save_as");
    }
    
    /**
     * Method responsible for returning the Menu Item Close Project.
     * @return Menu Item Close Project.
     */
    public JMenuItem getMenuItemCloseProject() {
        return this.getMenuItem("close_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Exit System.
     * @return Menu Item Exit System.
     */
    public JMenuItem getMenuItemExitSystem() {
        return this.getMenuItem("exit_system");
    }
    
    /**
     * Method responsible for returning Menu Item New Requirement.
     * @return Menu Item New Requirement.
     */
    public JMenuItem getMenuItemNewRequirement() {
        return this.getMenuItem("new_requirement");
    }
    
    /**
     * Method responsible for returning the Menu Item Requirements Traceability.
     * @return Menu Item Requirements Traceability.
     */
    public JMenuItem getMenuItemRequirementsTraceability() {
        return this.getMenuItem("requirements_traceability");
    }
    
    /**
     * Method responsible for returning the Menu Item Requirements Matrix.
     * @return Menu Item Requirements Matrix.
     */
    public JMenuItem getMenuItemRequirementsMatrix() {
        return this.getMenuItem("requirements_matriz");
    }
    
    /**
     * Method responsible for returning the Menu Item Feature Diagram.
     * @return Menu Item Feature Diagram.
     */
    public JMenuItem getMenuItemFeatureDiagram() {
        return this.getMenuItem("feature_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Use Case Diagram.
     * @return Menu Item Use Case Diagram.
     */
    public JMenuItem getMenuItemUseCaseDiagram() {
        return this.getMenuItem("usecase_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Class Diagram.
     * @return Menu Item Class Diagram.
     */
    public JMenuItem getMenuItemClassDiagram() {
        return this.getMenuItem("class_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Component Diagram.
     * @return Menu Item Component Diagram.
     */
    public JMenuItem getMenuItemComponentDiagram() {
        return this.getMenuItem("component_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Sequence Diagram.
     * @return Menu Item Sequence Diagram.
     */
    public JMenuItem getMenuItemSequenceDiagram() {
        return this.getMenuItem("sequence_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Activity Diagram.
     * @return Menu Item Activity Diagram.
     */
    public JMenuItem getMenuItemActivityDiagram() {
        return this.getMenuItem("activity_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Edit Profile.
     * @return Menu Item Edit Profile.
     */
    public JMenuItem getMenuItemEditProfile() {
        return this.getMenuItem("edit_profile");
    }
    
    /**
     * Method responsible for returning the Menu Item New Product.
     * @return Menu Item New Product.
     */
    public JMenuItem getMenuItemNewProduct() {
        return this.getMenuItem("new_product");
    }
    
    /**
     * Method responsible for returning the Menu Item New Instance.
     * @return Menu Item New Instance.
     */
    public JMenuItem getMenuItemNewInstance() {
        return this.getMenuItem("new_instance");
    }
    
    /**
     * Method responsible for returning the Menu Item New Traceability.
     * @return Menu Item New Traceability.
     */
    public JMenuItem getMenuItemNewTraceability() {
        return this.getMenuItem("new_traceability");
    }
    
    /**
     * Method responsible for returning the Menu Item New Metric.
     * @return Menu Item New Metric.
     */
    public JMenuItem getMenuItemNewMetric() {
        return this.getMenuItem("new_metric");
    }
    
    /**
     * Method responsible for returning the Menu Item New Measure.
     * @return Menu Item New Measure.
     */
    public JMenuItem getMenuItemNewMeasure() {
        return this.getMenuItem("new_measure");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluate Project.
     * @return Menu Item Evaluate Project.
     */
    public JMenuItem getMenuItemEvaluateProject() {
        return this.getMenuItem("evaluate_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluate Diagram.
     * @return Menu Item Evaluate Diagram.
     */
    public JMenuItem getMenuItemEvaluateDiagram() {
        return this.getMenuItem("evaluate_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluate Product.
     * @return Menu Item Evaluate Product.
     */
    public JMenuItem getMenuItemEvaluateProduct() {
        return this.getMenuItem("evaluate_product");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Diagram.
     * @return Menu Item Export Diagram.
     */
    public JMenuItem getMenuItemExportDiagram() {
        return this.getMenuItem("export_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Product.
     * @return Menu Item Export Product.
     */
    public JMenuItem getMenuItemExportProduct() {
        return this.getMenuItem("export_product");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Diagram Code.
     * @return Menu Item Export Diagram Code.
     */
    public JMenuItem getMenuItemExportDiagramCode() {
        return this.getMenuItem("diagram_code");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Instance Code.
     * @return Menu Item Export Instance Code.
     */
    public JMenuItem getMenuItemExportInstanceCode() {
        return this.getMenuItem("instance_code");
    }
    
    /**
     * Method responsible for returning the Menu Item About Information.
     * @return Menu Item About Information.
     */
    public JMenuItem getMenuItemAboutInformation() {
        return this.getMenuItem("about_info");
    }
    
    /**
     * Method responsible for returning the Menu Item About Site.
     * @return Menu Item About Site.
     */
    public JMenuItem getMenuItemAboutSite() {
        return this.getMenuItem("about_site");
    }

    /**
     * Method responsible for returning the Menu Item About Exit.
     * @return Menu Item About Exit.
     */
    public JMenuItem getMenuItemAboutExit() {
        return this.getMenuItem("about_exit");
    }

    /**
     * Method responsible for returning the Save Flag.
     * @return Save Flag.
     */
    public boolean isSave() {
        return this.save;
    }

    /**
     * Method responsible for setting the Save Flag.
     * @param save Save Flag.
     */
    public void setSave(boolean save) {
        this.save = save;
        this.updateSave();
    }
    
    /**
     * Method responsible for returning the Panel Main.
     * @return Panel Main.
     */
    public PanelMain getPanelMain() {
        return this.panelMain;
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
        return this.getScrollPane("scrollPanelProject");
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    public PanelModeling getPanelModeling() {
        return this.panelModeling;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Modeling.
     * @return Scroll Panel Modeling.
     */
    public JScrollPane getScrollPanelModeling() {
        return this.getScrollPane("scrollPanelModeling");
    }
    
    /**
     * Main Method of SMartyModeling.
     * @param args 
     */
//    public static void main(String[] args) {
//        new ViewMenu().setVisible(true);
//    }
    
    /**
     * Alternative Main Method of SMartyModeling.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                new ViewMenu().setVisible(true);
            }else {
                String   path     = args[0].trim();
//                String   path     = "C:\\Users\\Leandro\\Documents\\0.smty";
                Project  project_ = new ImportProject(path).getProject(); 
                ViewMenu view     = new ViewMenu();
                         view.setProject(project_);
                         view.update();
                         view.getPanelModeling().clear();
                         view.setVisible(true);
            }
        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException ex) {
            Logger.getLogger(ViewMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
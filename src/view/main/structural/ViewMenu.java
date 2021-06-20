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
        controller = new ControllerViewMenu(this);
        project    = null;
        zoom       = 1.00d;
        save       = true;
        addComponentListener((ComponentListener) controller);
        initComponents();
        update();
    }
    
    /**
     * Method responsible for initializing Components.
     */
    private void initComponents() {
        setTitle(ViewStyle.SYSTEM + "Menu");
        addKeyListener(controller);
        setLayout(new BorderLayout(2, 4));
        addMenu();
        createFileChooser("open_project");
        createFileChooser("save_project");
        createImageChooser("image");
        createPdfChooser("pdf");
        
        addPanelMain();
        addPanelProject();
    }
    
    /**
     * Method responsible for adding Menu Bar to View.
     */
    private void addMenu() {
        menuBar = new JMenuBar();
        
        createFileMenu();
        createRequirementMenu();
        createDiagramMenu();
        createProductLineMenu();
        createEvaluationMenu();
        createExportMenu();
        createAboutMenu();
        
        menuBar.add(getMenu("file"));
        menuBar.add(getMenu("requirements"));
        menuBar.add(getMenu("diagram"));
        menuBar.add(getMenu("productLine"));
        menuBar.add(getMenu("evaluation"));
        menuBar.add(getMenu("export"));
        menuBar.add(getMenu("about"));
        
        setJMenuBar(menuBar);
    }
    
    /**
     * Method responsible for creating File Menu.
     */
    private void createFileMenu() {
        createMenu("file", "File");
        
        createMenuItem("new_project",   "New Project",   "menu/file/new.png",  KeyEvent.VK_N);
        createMenuItem("open_project",  "Open Project",  "menu/file/open.png", KeyEvent.VK_O);
        createMenuItem("save_project",  "Save Project",  "menu/file/save.png", KeyEvent.VK_S);
        createMenuItem("save_as",       "Save As",       "menu/file/save.png");
        createMenuItem("close_project", "Close Project", "menu/file/close.png");
        createMenuItem("exit_system",   "Exit",          "menu/file/exit.png", KeyEvent.VK_Q);
        
        getMenu("file").add(getMenuItemNewProject());
        getMenu("file").addSeparator();
        getMenu("file").add(getMenuItemOpenProject());
        getMenu("file").add(getMenuItemSaveProject());
        getMenu("file").add(getMenuItemSaveAs());
        getMenuItemSaveProject().setEnabled(false);
        getMenuItemSaveAs().setEnabled(false);
        getMenu("file").addSeparator();
        getMenu("file").add(getMenuItemCloseProject());
        getMenuItemCloseProject().setEnabled(false);
        getMenu("file").addSeparator();
        getMenu("file").add(getMenuItemExitSystem());
    }
    
    /**
     * Method responsible for creating the Requirement Menu.
     */
    private void createRequirementMenu() {
        createMenu("requirements", "Requirements");
        
        createMenuItem("new_requirement",           "New Requirement",           "menu/requirement/requirement.png",  KeyEvent.VK_R);
        createMenuItem("requirements_traceability", "Requirements Traceability", "menu/requirement/traceability.png", KeyEvent.VK_T);
        createMenuItem("requirements_matrix",       "Requirements Matrix",       "menu/requirement/matrix.png",       KeyEvent.VK_X);
        
        getMenu("requirements").add(getMenuItemNewRequirement());
        getMenu("requirements").addSeparator();
        getMenu("requirements").add(getMenuItemRequirementsTraceability());
        getMenu("requirements").add(getMenuItemRequirementsMatrix());
    }
    
    /**
     * Method responsible for creating Diagram Menu.
     */
    private void createDiagramMenu() {
        createMenu("diagram", "New Diagram");
        
        createMenuItem("feature_diagram",   "Feature Diagram",   "menu/diagram/feature.png",   KeyEvent.VK_F);
        createMenuItem("usecase_diagram",   "Use Case Diagram",  "menu/diagram/use-case.png",  KeyEvent.VK_U);
        createMenuItem("class_diagram",     "Class Diagram",     "menu/diagram/class.png",     KeyEvent.VK_C);
        createMenuItem("component_diagram", "Component Diagram", "menu/diagram/component.png", KeyEvent.VK_M);
        createMenuItem("sequence_diagram",  "Sequence Diagram",  "menu/diagram/sequence.png",  KeyEvent.VK_E);
        createMenuItem("activity_diagram",  "Activity Diagram",  "menu/diagram/activity.png",  KeyEvent.VK_A);
        
        getMenu("diagram").add(getMenuItemFeatureDiagram());
        getMenu("diagram").addSeparator();
        getMenu("diagram").add(getMenuItemUseCaseDiagram());
        getMenu("diagram").add(getMenuItemClassDiagram());
        getMenu("diagram").add(getMenuItemComponentDiagram());
        getMenu("diagram").add(getMenuItemSequenceDiagram());
        getMenu("diagram").add(getMenuItemActivityDiagram());
    }
    
    /**
     * Method responsible for creating Product Line Menu.
     */
    private void createProductLineMenu() {
        createMenu("productLine", "SPL Profile");
        
        createMenuItem("edit_profile",     "Edit Profile",     "menu/product_line/profile.png",      KeyEvent.VK_L);
        createMenuItem("new_product",      "New Product",      "menu/product_line/product.png",      KeyEvent.VK_P);
        createMenuItem("new_instance",     "New Instance",     "menu/product_line/instance.png",     KeyEvent.VK_I);
        createMenuItem("new_traceability", "New Traceability", "menu/product_line/traceability.png", KeyEvent.VK_Y);
        
        getMenu("productLine").add(getMenuItemEditProfile());
        getMenu("productLine").addSeparator();
        getMenu("productLine").add(getMenuItemNewProduct());
        getMenu("productLine").add(getMenuItemNewInstance());
        getMenu("productLine").add(getMenuItemNewTraceability());
    }
    
    /**
     * Method responsible for creating the Evaluation Menu.
     */
    private void createEvaluationMenu() {
        createMenu("evaluation", "Evaluation");
        
        createMenuItemAlt("new_metric",  "New Metric",  "menu/evaluation/metric.png",  KeyEvent.VK_M);
        createMenuItemAlt("new_measure", "New Measure", "menu/evaluation/measure.png", KeyEvent.VK_S);
        
        createMenuItemAlt("evaluate_project", "Evaluate Project", "menu/evaluation/project.png", KeyEvent.VK_P);
        createMenuItemAlt("evaluate_diagram", "Evaluate Diagram", "menu/evaluation/diagram.png", KeyEvent.VK_D);
        createMenuItemAlt("evaluate_product", "Evaluate Product", "menu/evaluation/product.png", KeyEvent.VK_R);
        
        getMenu("evaluation").add(getMenuItemNewMetric());
        getMenu("evaluation").add(getMenuItemNewMeasure());
        getMenu("evaluation").addSeparator();
        getMenu("evaluation").add(getMenuItemEvaluateProject());
        getMenu("evaluation").add(getMenuItemEvaluateDiagram());
        getMenu("evaluation").add(getMenuItemEvaluateProduct());
    }
    
    /**
     * Method responsible for creating the Export Menu.
     */
    private void createExportMenu() {
        createMenu("export", "Export");
        
        createMenuItem("export_diagram", "Export Diagram", "menu/export/diagram.png");
        createMenuItem("export_product", "Export Product", "menu/export/product.png");
        
        createMenuItem("diagram_code",   "Export Diagram Code",  "menu/export/code-diagram.png");
        createMenuItem("instance_code",  "Export Instance Code", "menu/export/code-instance.png");
        
        getMenu("export").add(getMenuItemExportDiagram());
        getMenu("export").add(getMenuItemExportProduct());
        getMenu("export").addSeparator();
        getMenu("export").add(getMenuItemExportDiagramCode());
        getMenu("export").add(getMenuItemExportInstanceCode());
    }
    
    /**
     * Method responsible for creating the About Menu.
     */
    private void createAboutMenu() {
        createMenu("about", "About");
        
        createMenuItem("about_info", "Information", "menu/about/information.png", KeyEvent.VK_F2);
        createMenuItem("about_site", "Site", "menu/about/site.png", KeyEvent.VK_W);
        createMenuItem("about_exit", "Exit", "menu/about/exit.png", KeyEvent.VK_F4);
        
        getMenu("about").add(getMenuItemAboutInformation());
        getMenu("about").add(getMenuItemAboutSite());
        getMenu("about").addSeparator();
        getMenu("about").add(getMenuItemAboutExit());
    }
    
    /**
     * Method responsible for setting View Title.
     */
    public void setTitle() {
        if (project == null)
            setTitle(ViewStyle.SYSTEM + "Menu");
        else
            setTitle(ViewStyle.SYSTEM + project.getPath());
    }
    
    /**
     * Method responsible for adding Panel Main on View.
     */
    private void addPanelMain() {
        setTitle();

        panelMain = new PanelMain(this);
        createScrollPane("scrollPanelMain");
        getScrollPane("scrollPanelMain").setMinimumSize(new Dimension(200, 35));
        getScrollPane("scrollPanelMain").setPreferredSize(new Dimension(200, 35));
        getScrollPane("scrollPanelMain").setViewportView(panelMain);
        getContentPane().add(getScrollPane("scrollPanelMain"), BorderLayout.NORTH);
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
        initPanelProject();
        initPanelModeling();
        
        getScrollPane("project").setMinimumSize(new Dimension(325, 150));
        getScrollPane("project").setPreferredSize(new Dimension(325, 150));
        getScrollPane("modeling").setPreferredSize(new Dimension((int) (getWidth() - 375), getHeight() - 100));
        
        mainSplitPane = createSplitPane(true);
        mainSplitPane.setLeftComponent(getScrollPane("project"));
        mainSplitPane.setRightComponent(getScrollPane("modeling"));
        
        getContentPane().add(mainSplitPane, BorderLayout.WEST);
    }
    
    /**
     * Method responsible for initializing the Panel Project.
     */
    private void initPanelProject() {
        panelProject = new PanelProject(this);
        createScrollPane("project");
        getScrollPane("project").setViewportView(panelProject);
    }
    
    /**
     * Method responsible for initializing Modeling Panel.
     */
    private void initPanelModeling() {
        panelModeling = new PanelModeling(this);
        createScrollPane("modeling");
        getScrollPane("modeling").setViewportView(panelModeling);
    }
    
    /**
     * Method responsible for showing a Diagram.
     * @param diagram Diagram.
     */
    public void showDiagram(Diagram diagram) {
        panelModeling.addDiagram(diagram);
        panelModeling.updateUI();
    }
    
    /**
     * Method responsible for showing a Instance.
     * @param instance Instance.
     */
    public void showInstance(Instance instance) {
        panelModeling.addInstance(instance);
        panelModeling.updateUI();
    }
    
    /**
     * Method responsible for updating the View.
     */
    public void update() {
        setTitle();
        updatePanelMain();
        updatePanelTree();
        updatePanelModeling();
    }
    
    /**
     * Method responsible for setting Painel Default on View.
     */
    private void setPanelMenu() {
        Iterator<JMenuItem> iterator = getMenuItems().values().iterator();
        while (iterator.hasNext())
            iterator.next().setEnabled(true);
    }
    
    /**
     * Method responsible for locking Diagram Menu Items.
     */
    private void lockDiagramas() {
        setDiagramMenuItems(false);
    }
    
    /**
     * Method responsible for unlocking Diagram Menu Items.
     */
    private void unlockDiagramas() {
        setDiagramMenuItems(true);
    }
    
    /**
     * Method responsible for enabling Diagram Menu Items.
     * @param flag Enable Flag.
     */
    private void setDiagramMenuItems(boolean flag) {
        getMenuItemFeatureDiagram().setEnabled(flag);
        
        getMenuItemNewRequirement().setEnabled(flag);
        getMenuItemRequirementsTraceability().setEnabled(flag);
        getMenuItemRequirementsMatrix().setEnabled(flag);
        
        getMenuItemActivityDiagram().setEnabled(flag);
        getMenuItemUseCaseDiagram().setEnabled(flag);
        getMenuItemClassDiagram().setEnabled(flag);
        getMenuItemComponentDiagram().setEnabled(flag);
        getMenuItemSequenceDiagram().setEnabled(flag);
        
        getMenuItemEditProfile().setEnabled(flag);
        getMenuItemNewProduct().setEnabled(flag);
        getMenuItemNewInstance().setEnabled(flag);
        getMenuItemNewTraceability().setEnabled(flag);
        
        getMenuItemExportDiagram().setEnabled(flag);
        getMenuItemExportProduct().setEnabled(flag);
        getMenuItemExportDiagramCode().setEnabled(flag);
        getMenuItemExportInstanceCode().setEnabled(flag);
        
        getMenuItemNewMetric().setEnabled(flag);
        getMenuItemNewMeasure().setEnabled(flag);
        getMenuItemEvaluateProject().setEnabled(flag);
        getMenuItemEvaluateDiagram().setEnabled(flag);
        getMenuItemEvaluateProduct().setEnabled(flag);
    }
    
    /**
     * Method responsible for setting the Save Project Flag.
     */
    public void updateSave() {
        if (save == true) {
            getMenuItemSaveProject().setEnabled(false);
            getPanelMain().getSaveProjectButton().setEnabled(false);
            getPanelMain().getUndoButton().setEnabled(false);
            getPanelMain().getRedoButton().setEnabled(false);
        }else {
            getMenuItemSaveProject().setEnabled(true);
            getMenuItemSaveAs().setEnabled(true);
            getPanelMain().getSaveProjectButton().setEnabled(true);
        }
    }
    
    /**
     * Method responsible for updating Panel Main.
     */
    public void updatePanelMain() {
        setPanelMenu();
        panelMain.activate();
        if (project == null)
            panelMain.setNoProject();
        else
            getPanelModeling().updatePanelMain();
        updateSave();
    }
    
    /**
     * Method responsible for updating Panel Tree.
     */
    public void updatePanelTree() {
        if (project != null) {
            Integer index = getTabIndex();
            panelProject  = new PanelProject(this);
            getScrollPane("project").setViewportView(panelProject);
            unlockDiagramas();
            panelProject.getPanelTree().getTabbedPane().setSelectedIndex(index);
        }else {
            getScrollPane("project").setViewportView(createLabel(""));
            lockDiagramas();
        }
    }
    
    /**
     * Method responsible for returning the Tab Index.
     * @return Tab Index.
     */
    public Integer getTabIndex() {
        Integer index = panelProject.getPanelTree().getTabbedPane().getSelectedIndex();
        return  index < 0 ? 0 : index;
    }
    
    /**
     * Method responsible for setting the Tab Index.
     * @param index Tab Index.
     */
    public void setTabIndex(Integer index) {
        panelProject.getPanelTree().getTabbedPane().setSelectedIndex(index);
    }
    
    /**
     * Method responsible for updating Panel Modeling.
     */
    public void updatePanelModeling() {
        if (project == null)
            panelModeling.clear();
        panelModeling.updateModelingPanels();
        panelModeling.updateUI();
    }
    
    @Override
    public void operation() {
        dispose();
    }
    
    /**
     * Method responsible for resetting the Zoom.
     */
    private void resetZoom() {
        getPanelMain().getOriginalZoomButton().setEnabled(zoom != 1.00d);
        getPanelMain().getZoomInButton().setEnabled(zoom  < 3.00d);
        getPanelMain().getZoomOutButton().setEnabled(zoom > 0.20d);
    }
    
    /**
     * Method responsible for setting Original Zoom.
     */
    public void setOriginalZoom() {
        zoom = 1.0;
        resetZoom();
        panelModeling.setZoom(zoom);
    }
    
    /**
     * Method responsible for running Zoom In.
     */
    public void zoomIn() {
        zoom  = getPanelModeling().getSelectedPanel().getZoom();
        zoom += 0.10;
        zoom  = (zoom > 3.00) ? 3.00 : zoom;
        resetZoom();
        panelModeling.setZoom(zoom);
    }
    
    /**
     * Method responsible for running Zoom Out.
     */
    public void zoomOut() {
        zoom  = getPanelModeling().getSelectedPanel().getZoom();
        zoom -= 0.10;
        zoom  = (zoom <= 0.20) ? 0.20 : zoom;
        resetZoom();
        panelModeling.setZoom(zoom);
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
        return (ControllerViewMenu) controller;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    public Project getProject() {
        return project;
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
        return getFileChooser("open_project");
    }
    
    /**
     * Method responsible for returning the File Chooser Save Project.
     * @return File Chooser Save Project.
     */
    public JFileChooser getFileChooserSaveProject() {
        return getFileChooser("save_project");
    }
    
    /**
     * Method responsible for returning the File Chooser Image.
     * @return File Chooser Image.
     */
    public JFileChooser getFileChooserImage() {
        return getFileChooser("image");
    }
    
    /**
     * Method responsible for returning the File Chooser Pdf.
     * @return File Chooser Pdf.
     */
    public JFileChooser getFileChooserPdf() {
        return getFileChooser("pdf");
    }
    
    /**
     * Method responsible for returning the Menu Item New Project.
     * @return Menu Item New Project.
     */
    public JMenuItem getMenuItemNewProject() {
        return getMenuItem("new_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Open Project.
     * @return Menu Item Open Project.
     */
    public JMenuItem getMenuItemOpenProject() {
        return getMenuItem("open_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Save Project.
     * @return Menu Item Save Project.
     */
    public JMenuItem getMenuItemSaveProject() {
        return getMenuItem("save_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Save As.
     * @return Menu Item Save As.
     */
    public JMenuItem getMenuItemSaveAs() {
        return getMenuItem("save_as");
    }
    
    /**
     * Method responsible for returning the Menu Item Close Project.
     * @return Menu Item Close Project.
     */
    public JMenuItem getMenuItemCloseProject() {
        return getMenuItem("close_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Exit System.
     * @return Menu Item Exit System.
     */
    public JMenuItem getMenuItemExitSystem() {
        return getMenuItem("exit_system");
    }
    
    /**
     * Method responsible for returning Menu Item New Requirement.
     * @return Menu Item New Requirement.
     */
    public JMenuItem getMenuItemNewRequirement() {
        return getMenuItem("new_requirement");
    }
    
    /**
     * Method responsible for returning the Menu Item Requirements Traceability.
     * @return Menu Item Requirements Traceability.
     */
    public JMenuItem getMenuItemRequirementsTraceability() {
        return getMenuItem("requirements_traceability");
    }
    
    /**
     * Method responsible for returning the Menu Item Requirements Matrix.
     * @return Menu Item Requirements Matrix.
     */
    public JMenuItem getMenuItemRequirementsMatrix() {
        return getMenuItem("requirements_matrix");
    }
    
    /**
     * Method responsible for returning the Menu Item Feature Diagram.
     * @return Menu Item Feature Diagram.
     */
    public JMenuItem getMenuItemFeatureDiagram() {
        return getMenuItem("feature_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Use Case Diagram.
     * @return Menu Item Use Case Diagram.
     */
    public JMenuItem getMenuItemUseCaseDiagram() {
        return getMenuItem("usecase_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Class Diagram.
     * @return Menu Item Class Diagram.
     */
    public JMenuItem getMenuItemClassDiagram() {
        return getMenuItem("class_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Component Diagram.
     * @return Menu Item Component Diagram.
     */
    public JMenuItem getMenuItemComponentDiagram() {
        return getMenuItem("component_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Sequence Diagram.
     * @return Menu Item Sequence Diagram.
     */
    public JMenuItem getMenuItemSequenceDiagram() {
        return getMenuItem("sequence_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Activity Diagram.
     * @return Menu Item Activity Diagram.
     */
    public JMenuItem getMenuItemActivityDiagram() {
        return getMenuItem("activity_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Edit Profile.
     * @return Menu Item Edit Profile.
     */
    public JMenuItem getMenuItemEditProfile() {
        return getMenuItem("edit_profile");
    }
    
    /**
     * Method responsible for returning the Menu Item New Product.
     * @return Menu Item New Product.
     */
    public JMenuItem getMenuItemNewProduct() {
        return getMenuItem("new_product");
    }
    
    /**
     * Method responsible for returning the Menu Item New Instance.
     * @return Menu Item New Instance.
     */
    public JMenuItem getMenuItemNewInstance() {
        return getMenuItem("new_instance");
    }
    
    /**
     * Method responsible for returning the Menu Item New Traceability.
     * @return Menu Item New Traceability.
     */
    public JMenuItem getMenuItemNewTraceability() {
        return getMenuItem("new_traceability");
    }
    
    /**
     * Method responsible for returning the Menu Item New Metric.
     * @return Menu Item New Metric.
     */
    public JMenuItem getMenuItemNewMetric() {
        return getMenuItem("new_metric");
    }
    
    /**
     * Method responsible for returning the Menu Item New Measure.
     * @return Menu Item New Measure.
     */
    public JMenuItem getMenuItemNewMeasure() {
        return getMenuItem("new_measure");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluate Project.
     * @return Menu Item Evaluate Project.
     */
    public JMenuItem getMenuItemEvaluateProject() {
        return getMenuItem("evaluate_project");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluate Diagram.
     * @return Menu Item Evaluate Diagram.
     */
    public JMenuItem getMenuItemEvaluateDiagram() {
        return getMenuItem("evaluate_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Evaluate Product.
     * @return Menu Item Evaluate Product.
     */
    public JMenuItem getMenuItemEvaluateProduct() {
        return getMenuItem("evaluate_product");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Diagram.
     * @return Menu Item Export Diagram.
     */
    public JMenuItem getMenuItemExportDiagram() {
        return getMenuItem("export_diagram");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Product.
     * @return Menu Item Export Product.
     */
    public JMenuItem getMenuItemExportProduct() {
        return getMenuItem("export_product");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Diagram Code.
     * @return Menu Item Export Diagram Code.
     */
    public JMenuItem getMenuItemExportDiagramCode() {
        return getMenuItem("diagram_code");
    }
    
    /**
     * Method responsible for returning the Menu Item Export Instance Code.
     * @return Menu Item Export Instance Code.
     */
    public JMenuItem getMenuItemExportInstanceCode() {
        return getMenuItem("instance_code");
    }
    
    /**
     * Method responsible for returning the Menu Item About Information.
     * @return Menu Item About Information.
     */
    public JMenuItem getMenuItemAboutInformation() {
        return getMenuItem("about_info");
    }
    
    /**
     * Method responsible for returning the Menu Item About Site.
     * @return Menu Item About Site.
     */
    public JMenuItem getMenuItemAboutSite() {
        return getMenuItem("about_site");
    }

    /**
     * Method responsible for returning the Menu Item About Exit.
     * @return Menu Item About Exit.
     */
    public JMenuItem getMenuItemAboutExit() {
        return getMenuItem("about_exit");
    }

    /**
     * Method responsible for returning the Save Flag.
     * @return Save Flag.
     */
    public boolean isSave() {
        return save;
    }

    /**
     * Method responsible for setting the Save Flag.
     * @param save Save Flag.
     */
    public void setSave(boolean save) {
        this.save = save;
        updateSave();
    }
    
    /**
     * Method responsible for returning the Panel Main.
     * @return Panel Main.
     */
    public PanelMain getPanelMain() {
        return panelMain;
    }
    
    /**
     * Method responsible for returning the Panel Project.
     * @return Panel Project.
     */
    public PanelProject getPanelProject() {
        return panelProject;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Project.
     * @return Scroll Panel Project.
     */
    public JScrollPane getScrollPanelProject() {
        return getScrollPane("project");
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    public PanelModeling getPanelModeling() {
        return panelModeling;
    }
    
    /**
     * Method responsible for returning the Scroll Panel Modeling.
     * @return Scroll Panel Modeling.
     */
    public JScrollPane getScrollPanelModeling() {
        return getScrollPane("modeling");
    }
    
    /**
     * Main Method of SMartyModeling.
     * @param args 
     */
    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                new ViewMenu().setVisible(true);
            }else {
                String   path     = args[0].trim();
                Project  project_ = new ImportProject(path).getProject(); 
                ViewMenu view     = new ViewMenu();
                         view.setProject(project_);
                         view.update();
                         view.getPanelModeling().clear();
                         view.setVisible(true);
            }
        }catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException ex) {
            Logger.getLogger(ViewMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
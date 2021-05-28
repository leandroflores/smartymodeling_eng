package controller.view.main.structural;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import controller.view.ControllerView;
import file.exportation.ExportProject;
import file.exportation.pdf.ExportImage;
import file.exportation.pdf.ExportPdfImage;
import file.importation.ImportProject;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import org.xml.sax.SAXException;
import view.modal.edit.base.ViewEditProfile;
import view.modal.evaluation.base.ViewEvaluationDiagram;
import view.modal.evaluation.base.product.ViewEvaluationProduct;
import view.modal.export.base.ViewExportDiagram;
import view.modal.export.base.product.ViewExportProduct;
import view.modal.export.base.code.ViewExportDiagramCode;
import view.modal.export.base.product.code.ViewExportInstanceCode;
import view.modal.message.ViewError;
import view.modal.message.ViewMessage;
import view.modal.message.ViewSave;
import view.modal.new_.base.evaluation.ViewNewMeasure;
import view.modal.new_.base.evaluation.ViewNewMetric;
import view.modal.new_.base.product.ViewNewInstance;
import view.modal.new_.base.product.ViewNewProduct;
import view.modal.new_.base.traceability.ViewNewTraceability;
import view.modal.evaluation.base.ViewEvaluationProject;
import view.modal.new_.base.requirement.ViewNewRequirement;
import view.main.structural.ViewMenu;
import view.modal.requirement.base.ViewRequirementMatrix;
import view.modal.requirement.base.ViewRequirementTraceability;
import view.modal.system.ViewSystemInformation;

/**
 * <p>Class of Controller <b>ControllerViewMenu</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from <b>ViewMenu</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-22
 * @see    controller.view.ControllerView
 * @see    java.awt.event.ComponentListener
 * @see    view.main.structural.ViewMenu
 */
public class ControllerViewMenu extends ControllerView implements ComponentListener {
    private final ViewMenu viewMenu;
    private boolean flag;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public ControllerViewMenu(ViewMenu view) {
        super(view);
        viewMenu = view;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        flag = true;
        while (flag) {
            actionFile(event);
            actionRequirement(event);
            actionDiagram(event);
            actionProductLine(event);
            actionEvaluation(event);
            actionExport(event);
            actionAbout(event);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ESC)
            exit();
    }
    
    /**
     * Method responsible for Forward the File Menu.
     * @param event Action Event.
     */
    private void actionFile(ActionEvent event) {
        if (viewMenu.getMenuItemNewProject().equals(event.getSource()))
            showNewProject();
        else if (viewMenu.getMenuItemOpenProject().equals(event.getSource()))
            openProject();
        else if (viewMenu.getMenuItemSaveProject().equals(event.getSource()))
            saveProject();
        else if (viewMenu.getMenuItemSaveAs().equals(event.getSource()))
            saveProjectAs();
        else if (viewMenu.getMenuItemCloseProject().equals(event.getSource()))
            closeProject();
        else if (viewMenu.getMenuItemExitSystem().equals(event.getSource()))
            exit();
        else
            flag = true;
    }
    
    /**
     * Method responsible for showing a New Project.
     */
    public void showNewProject() {
        if (viewMenu.isSave())
            createNewProject();
        else
            new ViewSave(viewMenu, 1).setVisible(true);
    }
    
    /**
     * Method responsible for creating a New Project.
     */
    public void createNewProject() {
        viewMenu.setProject(new Project());
        viewMenu.getPanelModeling().clear();
        viewMenu.setSave(true);
        viewMenu.update();
    }
    
    /**
     * Method responsible for showing Open Project.
     */
    public void showOpenProject() {
        if (viewMenu.isSave())
            openProject();
        else
            new ViewSave(viewMenu, 2).setVisible(true);
    }
    
    /**
     * Method responsible for opening a Project.
     */
    public void openProject() {
        viewMenu.getFileChooserSaveProject().setDialogTitle("Open a Project");
        viewMenu.getFileChooserSaveProject().setApproveButtonText("Open");
        viewMenu.getFileChooserSaveProject().setApproveButtonToolTipText("Open a Project");
        viewMenu.getFileChooserSaveProject().setToolTipText("Open");
        if (viewMenu.getFileChooserSaveProject().showSaveDialog(viewMenu) != 1) {
            String path = viewMenu.getFileChooserSaveProject().getSelectedFile().getAbsolutePath();
            try {
                viewMenu.setProject(new ImportProject(path).getProject());
                viewMenu.update();
            }catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException exception) {
                new ViewError(viewMenu, "Error opening Project!").setVisible(true);
            }
        }
        viewMenu.getPanelModeling().clear();
        viewMenu.update();
    }
    
    /**
     * Method responsible for Saving a Project.
     */
    public void saveProject() {
        viewMenu.getFileChooserSaveProject().setDialogTitle("Save Project");
        viewMenu.getFileChooserSaveProject().setApproveButtonText("Save");
        viewMenu.getFileChooserSaveProject().setApproveButtonToolTipText("Save Project");
        viewMenu.getFileChooserSaveProject().setToolTipText("Save");
        String path = viewMenu.getProject().getPath();
        if (viewMenu.getProject().getPath().equals("New_Project.smty"))
            viewMenu.getProject().setPath(getPath());
        viewMenu.setTitle();
        exportProject();
    }
    
    /**
     * Method responsible for Saving As a Project.
     */
    public void saveProjectAs() {
        viewMenu.getFileChooserSaveProject().setDialogTitle("Save Project As");
        viewMenu.getFileChooserSaveProject().setApproveButtonText("Save As");
        viewMenu.getFileChooserSaveProject().setApproveButtonToolTipText("Save Project As");
        viewMenu.getFileChooserSaveProject().setToolTipText("Save");
        viewMenu.getProject().setPath(getPath());
    }
    
    /**
     * Method responsible for returning the Project Path.
     * @return Project Path.
     */
    public String getPath() {
        String path = "New_Project.smty";
        if (viewMenu.getFileChooserSaveProject().showSaveDialog(viewMenu) != 1) {
            path = viewMenu.getFileChooserSaveProject().getSelectedFile().getAbsolutePath();
            path = (path.toLowerCase().endsWith(".smty")) ? path : path + ".smty";
        }
        return path;
    }
    
    /**
     * Method responsible for exporting the Project.
     */
    public void exportProject() {
        try {
            if (!viewMenu.getProject().getPath().equalsIgnoreCase("New_Project.smty")) {
                new ExportProject(viewMenu.getProject(), viewMenu.getProject().getPath()).export();
                viewMenu.setSave(true);
            }
        }catch (IOException exception) {
            new ViewError(viewMenu, "Error writing Project File!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for showing Close Project.
     */
    public void showCloseProject() {
        if (viewMenu.isSave())
            closeProject();
        else
            new ViewSave(viewMenu, 3).setVisible(true);
    }
    
    /**
     * Method responsible for Closing Project.
     */
    public void closeProject() {
        viewMenu.setProject(null);
        viewMenu.update();
    }
    
    /**
     * Method responsible for Exit of the System.
     */
    public void exit() {
        if ((viewMenu.getProject() != null) && (!viewMenu.isSave()))
            new ViewSave(viewMenu, 4).setVisible(true);
        else
            viewMenu.dispose();
    }
    
    /**
     * Method responsible for Forward the Requirement Menu.
     * @param event Action Event.
     */
    private void actionRequirement(ActionEvent event) {
        if (viewMenu.getMenuItemNewRequirement().equals(event.getSource()))
            showNewRequirementView();
        else if (viewMenu.getMenuItemRequirementsTraceability().equals(event.getSource()))
            showRequirementsTraceabilityView();
        else if (viewMenu.getMenuItemRequirementsMatrix().equals(event.getSource()))
            showRequirementsMatrixView();
        else
            flag = true;
    }
    
    /**
     * Method responsible for showing the New Requirement View.
     */
    private void showNewRequirementView() {
        new ViewNewRequirement(viewMenu).setVisible(true);
    }
    
    /**
     * Method responsible for showing the Requirements Traceability View.
     */
    private void showRequirementsTraceabilityView() {
        if (!viewMenu.getProject().getRequirementsList().isEmpty())
            new ViewRequirementTraceability(viewMenu).setVisible(true);
        else
            new ViewMessage(viewMenu, "Project with no Requirements!").setVisible(true);
    }
    
    /**
     * Method responsible for showing the Requirements Matrix View.
     */
    private void showRequirementsMatrixView() {
        if (!viewMenu.getProject().getRequirementsList().isEmpty())
            new ViewRequirementMatrix(viewMenu).setVisible(true);
        else
            new ViewMessage(viewMenu, "Project with no Requirements!").setVisible(true);
    }
    
    /**
     * Method responsible for Forward the Diagram Menu.
     * @param event Action Event.
     */
    private void actionDiagram(ActionEvent event) {
        if (viewMenu.getMenuItemFeatureDiagram().equals(event.getSource()))
            createNewFeatureDiagram();
        else if (viewMenu.getMenuItemUseCaseDiagram().equals(event.getSource()))
            createNewUseCaseDiagram();
        else if (viewMenu.getMenuItemClassDiagram().equals(event.getSource()))
            createNewClassDiagram();
        else if (viewMenu.getMenuItemComponentDiagram().equals(event.getSource()))
            createNewComponentDiagram();
        else if (viewMenu.getMenuItemSequenceDiagram().equals(event.getSource()))
            createNewSequenceDiagram();
        else if (viewMenu.getMenuItemActivityDiagram().equals(event.getSource()))
            createNewActivityDiagram();
        else
            flag = true;
    }
    /**
     * Method responsible for creating a New Feature Diagram.
     */
    public void createNewFeatureDiagram() {
        showNewDiagram(new FeatureDiagram(viewMenu.getProject()), 1);
    }
    
    /**
     * Method responsible for creating a New Use Case Diagram.
     */
    public void createNewUseCaseDiagram() {
        showNewDiagram(new UseCaseDiagram(viewMenu.getProject()), 2);
    }
    
    /**
     * Method responsible for creating a New Class Diagram.
     */
    public void createNewClassDiagram() {
        showNewDiagram(new ClassDiagram(viewMenu.getProject()), 2);
    }
    
    /**
     * Method responsible for creating a New Component Diagram.
     */
    public void createNewComponentDiagram() {
        showNewDiagram(new ComponentDiagram(viewMenu.getProject()), 2);
    }
    
    /**
     * Method responsible for checking for a New Sequence Diagram.
     * @return New Sequence Diagram is able.
     */
    public boolean checkNewSequenceDiagram() {
        return  viewMenu.getProject() != null
            && !viewMenu.getProject().getElements("actor").isEmpty()
            && !viewMenu.getProject().getElements("class").isEmpty();
    }
    
    /**
     * Method responsible for creating a New Sequence Diagram.
     */
    public void createNewSequenceDiagram() {
        if (checkNewSequenceDiagram())
            showNewDiagram(new SequenceDiagram(viewMenu.getProject()), 2);
        else 
            new ViewError(viewMenu, "Project without Actors or Classes!").setVisible(true);
    }
    
    /**
     * Method responsible for creating a New Activity Diagram.
     */
    public void createNewActivityDiagram() {
        showNewDiagram(new ActivityDiagram(viewMenu.getProject()), 2);
    }
    
    /**
     * Method responsible for showing a New Diagram to Project.
     * @param diagram New Diagram.
     * @param index Tab Index.
     */
    private void showNewDiagram(Diagram diagram, int index) {
        viewMenu.getProject().addDiagram(diagram);
            diagram.setDefaultName();
        viewMenu.showDiagram(diagram);
        viewMenu.setTabIndex(index);
        viewMenu.update();
    }
    
    /**
     * Method responsible for Forward the Product Line Menu.
     * @param event Action Event.
     */
    private void actionProductLine(ActionEvent event) {
        if (viewMenu.getMenuItemEditProfile().equals(event.getSource()))
            showEditProfileView();
        else if (viewMenu.getMenuItemNewProduct().equals(event.getSource()))
            showNewProductView();
        else if (viewMenu.getMenuItemNewInstance().equals(event.getSource()))
            showNewInstanceView();
        else if (viewMenu.getMenuItemNewTraceability().equals(event.getSource()))
            showManualTraceabilityView();
        else
            flag = true;
    }
    
    /**
     * Method responsible for showing the Profile Edit View.
     */
    private void showEditProfileView() {
        new ViewEditProfile(viewMenu.getPanelModeling(), viewMenu.getProject().getProfile()).setVisible(true);
    }
    
    /**
     * Method responsible for showing the New Product View.
     */
    public void showNewProductView() {
        new ViewNewProduct(viewMenu).setVisible(true);
    }
    
    /**
     * Method responsible for showing the New Instance View.
     */
    public void showNewInstanceView() {
        new ViewNewInstance(viewMenu).setVisible(true);
    }
    
    /**
     * Method responsible for showing the New Manual Traceability.
     */
    public void showManualTraceabilityView() {
        if (!viewMenu.getProject().getDiagrams().isEmpty())
            new ViewNewTraceability(viewMenu).setVisible(true);
        else
            new ViewMessage(viewMenu, "Project with no Elements!").setVisible(true);
    }
    
    /**
     * Method responsible for Forward the Evaluation Menu.
     * @param event Action Event.
     */
    private void actionEvaluation(ActionEvent event) {
        if (viewMenu.getMenuItemNewMetric().equals(event.getSource()))
            showNewMetricView();
        else if (viewMenu.getMenuItemNewMeasure().equals(event.getSource()))
            showNewMeasureView();
        else if (viewMenu.getMenuItemEvaluateProject().equals(event.getSource()))
            showEvaluationProjectView();
        else if (viewMenu.getMenuItemEvaluateDiagram().equals(event.getSource()))
            showEvaluationDiagramView();
        else if (viewMenu.getMenuItemEvaluateProduct().equals(event.getSource()))
            showEvaluationProductView();
        else
            flag = true;
    }
    
    /**
     * Method responsible for showing the New Metric View.
     */
    public void showNewMetricView() {
        new ViewNewMetric(viewMenu).setVisible(true);
    }
    
    /**
     * Method responsible for showing the New Measure View.
     */
    public void showNewMeasureView() {
        if (!viewMenu.getProject().getMetricsList().isEmpty())
            new ViewNewMeasure(viewMenu).setVisible(true);
        else
            new ViewError(viewMenu, "Metrics List is void!").setVisible(true);
    }
    
    /**
     * Method responsible for showing the Evaluation Project View.
     */
    private void showEvaluationProjectView() {
        new ViewEvaluationProject(viewMenu, viewMenu.getProject()).setVisible(true);
    }
    
    /**
     * Method responsible for showing the Evaluation Diagram View.
     */
    private void showEvaluationDiagramView() {
        if (!viewMenu.getProject().getDiagramsList().isEmpty())
            new ViewEvaluationDiagram(viewMenu, viewMenu.getProject()).setVisible(true);
        else
            new ViewError(viewMenu, "Diagrams List is void!").setVisible(true);
    }
    
    /**
     * Method responsible for showing the Evaluation Product View.
     */
    private void showEvaluationProductView() {
        if (!viewMenu.getProject().getProductsList().isEmpty())
            new ViewEvaluationProduct(viewMenu, viewMenu.getProject()).setVisible(true);
        else
            new ViewError(viewMenu, "Product List is void!").setVisible(true);
    }
    
    /**
     * Method responsible for Forward the Export Menu.
     * @param event Action Event.
     */
    private void actionExport(ActionEvent event) {
        if (viewMenu.getMenuItemExportDiagram().equals(event.getSource()))
            showExportDiagramView();
        else if (viewMenu.getMenuItemExportProduct().equals(event.getSource()))
            showExportProductView();
        else if (viewMenu.getMenuItemExportDiagramCode().equals(event.getSource()))
            showExportDiagramCodeView();
        else if (viewMenu.getMenuItemExportInstanceCode().equals(event.getSource()))
            showExportInstanceCodeView();
        else
            flag = true;
    }
    
    /**
     * Method responsible for showing the Export Diagram View.
     */
    private void showExportDiagramView() {
        if (!viewMenu.getProject().getDiagrams().isEmpty())
            new ViewExportDiagram(viewMenu).setVisible(true);
        else
            new ViewMessage(viewMenu, "Project with no Diagrams!").setVisible(true);
    }
    
    /**
     * Method responsible for showing the Export Product View.
     */
    private void showExportProductView() {
        if (!viewMenu.getProject().getProductsList().isEmpty())
            new ViewExportProduct(viewMenu).setVisible(true);
        else
            new ViewMessage(viewMenu, "Project with no Products!").setVisible(true);
    }
    
    /**
     * Method responsible for showing the Export Diagram Code View.
     */
    private void showExportDiagramCodeView() {
        if (!viewMenu.getProject().getDiagrams("class").isEmpty())
            new ViewExportDiagramCode(viewMenu).setVisible(true);
        else
            new ViewMessage(viewMenu, "Project with no Class Diagrams!").setVisible(true);
        
    }
    
    /**
     * Method responsible for showing the Export Instance Code View.
     */
    private void showExportInstanceCodeView() {
        if (!viewMenu.getProject().getInstances("class").isEmpty())
            new ViewExportInstanceCode(viewMenu).setVisible(true);
        else
            new ViewMessage(viewMenu, "Project with no Class Instances!").setVisible(true);
    }
    
    /**
     * Method responsible for Forward the About Menu.
     * @param event Action Event.
     */
    private void actionAbout(ActionEvent event) {
        if (viewMenu.getMenuItemAboutInformation().equals(event.getSource()))
            new ViewSystemInformation(viewMenu).setVisible(true);
        else if (viewMenu.getMenuItemAboutExit().equals(event.getSource()))
            exit();
        flag = false;
    }
    
    /**
     * Method responsible for exporting the Image.
     * @throws com.itextpdf.text.DocumentException
     * @throws com.itextpdf.text.BadElementException
     * @throws java.net.MalformedURLException
     */
    public void exportImage() throws DocumentException, BadElementException, MalformedURLException {
        if (viewMenu.getFileChooserImage().showSaveDialog(viewMenu) != 1) {
            String        path     = viewMenu.getFileChooserImage().getSelectedFile().getAbsolutePath();
            BufferedImage modeling = viewMenu.getPanelModeling().getImage();
            BufferedImage image;
            if (modeling != null) {
                try {
                    path  = (path.toLowerCase().endsWith(".png")) ? path : path + ".png";
                    image = new ExportImage(modeling).getPNGImage();
                        ImageIO.write(image, "PNG", new File(path));
                    new ViewMessage(viewMenu, "Image exported Successfully!").setVisible(true);
                } catch (IOException exception) {
                    new ViewError(viewMenu, "Error to export Image!").setVisible(true);
                }
            }else {
                new ViewError(viewMenu, "Open a Diagram or Instance Panel!").setVisible(true);
            }
        }
    }
    
    /**
     * Method responsible for exporting the Pdf Image.
     * @throws BadElementException
     * @throws DocumentException 
     */
    public void exportPdfImage() throws BadElementException, DocumentException {
        if (viewMenu.getFileChooserPdf().showSaveDialog(viewMenu) != 1) {
            String        path     = viewMenu.getFileChooserPdf().getSelectedFile().getAbsolutePath();
            BufferedImage original = viewMenu.getPanelModeling().getImage();
            BufferedImage newImage;
            if (original != null) {
                try {
                    path     = (path.toLowerCase().endsWith(".pdf")) ? path : path + ".pdf";
                    newImage = new ExportImage(original).getPNGImage();
                    new ExportPdfImage(newImage, path).export();
                    new ViewMessage(viewMenu, "PDF exported Successfully!").setVisible(true);
                } catch (IOException exception) {
                    new ViewError(viewMenu, "Error to export PDF!").setVisible(true);
                }
            }else {
                new ViewError(viewMenu, "Open a Diagram or Instance Panel!").setVisible(true);
            }
        }
    }
    
    /**
     * Method responsible for resizing the Panel Project.
     */
    private void resizePanelProject() {
        Integer width  = new Double(viewMenu.getSize().getWidth() * 0.2).intValue();
        Integer height = new Double(viewMenu.getSize().getHeight()).intValue() - 70;
        viewMenu.getScrollPanelProject().setPreferredSize(new Dimension(width, height));
    }
    
    /**
     * Method responsible for resizing the Panel Modeling.
     */
    private void resizePanelModeling() {
        Integer width  = new Double(viewMenu.getSize().getWidth() * 0.8).intValue() - 30;
        Integer height = new Double(viewMenu.getSize().getHeight()).intValue() - 100;
        viewMenu.getScrollPanelModeling().setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    public void componentResized(ComponentEvent event) {
        resizePanelProject();
        resizePanelModeling();
    }

    @Override
    public void componentMoved(ComponentEvent event) {
        resizePanelProject();
        resizePanelModeling();
    }

    @Override
    public void componentShown(ComponentEvent event) {
        resizePanelProject();
        resizePanelModeling();
    }

    @Override
    public void componentHidden(ComponentEvent event) {
        resizePanelProject();
        resizePanelModeling();
    }
}
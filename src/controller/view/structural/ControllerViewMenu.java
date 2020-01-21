package controller.view.structural;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
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
import model.structural.base.Project;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.ComponentDiagram;
import model.structural.diagram.SequenceDiagram;
import model.structural.diagram.UseCaseDiagram;
import org.xml.sax.SAXException;
import view.edit.ViewEditProfile;
import view.export.ViewExportDiagram;
import view.export.ViewExportProduct;
import view.export.code.ViewExportDiagramCode;
import view.message.ViewError;
import view.message.ViewMessage;
import view.message.ViewSave;
import view.new_.evaluation.ViewNewMeasure;
import view.new_.evaluation.ViewNewMetric;
import view.new_.product.ViewNewInstance;
import view.new_.product.ViewNewProduct;
import view.new_.traceability.ViewNewTraceability;
import view.structural.ViewMenu;
import view.system.ViewSystemInformation;

/**
 * <p>Class of Controller <b>ControllerViewMenu</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from <b>ViewMenu</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  22/05/2019
 * @see    controller.view.ControllerView
 * @see    view.structural.ViewMenu
 */
public class ControllerViewMenu extends ControllerView implements ComponentListener {
    private final ViewMenu viewMenu;

    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public ControllerViewMenu(ViewMenu viewMenu) {
        super(viewMenu);
        this.viewMenu = viewMenu;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.viewMenu.getMenuItemNewProject().equals(event.getSource()))
            this.showNewProject();
        else if (this.viewMenu.getMenuItemOpenProject().equals(event.getSource()))
            this.openProject();
        else if (this.viewMenu.getMenuItemSaveProject().equals(event.getSource()))
            this.saveProject();
        else if (this.viewMenu.getMenuItemSaveAs().equals(event.getSource()))
            this.saveProjectAs();
        else if (this.viewMenu.getMenuItemCloseProject().equals(event.getSource()))
            this.closeProject();
        else if (this.viewMenu.getMenuItemExitSystem().equals(event.getSource()))
            this.exit();
        else if (this.viewMenu.getMenuItemActivityDiagram().equals(event.getSource()))
            this.newActivityDiagram();
        else if (this.viewMenu.getMenuItemClassDiagram().equals(event.getSource()))
            this.newClassDiagram();
        else if (this.viewMenu.getMenuItemComponentDiagram().equals(event.getSource()))
            this.newComponentDiagram();
        else if (this.viewMenu.getMenuItemSequenceDiagram().equals(event.getSource()))
            this.newSequenceDiagram();
        else if (this.viewMenu.getMenuItemUseCaseDiagram().equals(event.getSource()))
            this.newUseCaseDiagram();
        else if (this.viewMenu.getMenuItemEditProfile().equals(event.getSource()))
            this.editProfile();
        else if (this.viewMenu.getMenuItemNewProduct().equals(event.getSource()))
            this.newProduct();
        else if (this.viewMenu.getMenuItemInstantiateProduct().equals(event.getSource()))
            this.instantiateNewProduct();
        else if (this.viewMenu.getMenuItemNewTraceability().equals(event.getSource()))
            this.newTraceability();
        else if (this.viewMenu.getMenuItemEvaluationMetric().equals(event.getSource()))
            this.newMetric();
        else if (this.viewMenu.getMenuItemEvaluationMeasure().equals(event.getSource()))
            this.newMeasure();
        else if (this.viewMenu.getMenuItemExportDiagram().equals(event.getSource()))
            this.exportDiagram();
        else if (this.viewMenu.getMenuItemExportProduct().equals(event.getSource()))
            this.exportProduct();
        else if (this.viewMenu.getMenuItemExportDiagramCode().equals(event.getSource()))
            this.exportDiagramCode();
        else if (this.viewMenu.getMenuItemSystemInformation().equals(event.getSource()))
            new ViewSystemInformation(this.viewMenu).setVisible(true);
        else if (this.viewMenu.getMenuItemSystemExit().equals(event.getSource()))
            this.exit();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getKeyCode() == ESC)
            this.exit();
    }
    
    /**
     * Method responsible for showing a New Project.
     */
    public void showNewProject() {
        if (this.viewMenu.isSave())
            this.createNewProject();
        else
            new ViewSave(this.viewMenu, 1).setVisible(true);
    }
    
    /**
     * Method responsible for creating a New Project.
     */
    public void createNewProject() {
        this.viewMenu.setProject(new Project());
        this.viewMenu.getPanelModeling().clear();
        this.viewMenu.setSave(true);
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for showing Open Project.
     */
    public void showOpenProject() {
        if (this.viewMenu.isSave())
            this.openProject();
        else
            new ViewSave(this.viewMenu, 2).setVisible(true);
    }
    
    /**
     * Method responsible for opening a Project.
     */
    public void openProject() {
        this.viewMenu.getFileChooserProject().setDialogTitle("Open a Project");
        this.viewMenu.getFileChooserProject().setApproveButtonText("Open");
        this.viewMenu.getFileChooserProject().setApproveButtonToolTipText("Open a Project");
        this.viewMenu.getFileChooserProject().setToolTipText("Open");
        if (this.viewMenu.getFileChooserProject().showSaveDialog(this.viewMenu) != 1) {
            String path = this.viewMenu.getFileChooserProject().getSelectedFile().getAbsolutePath();
            try {
                this.viewMenu.setProject(new ImportProject(path).getProject());
                this.viewMenu.update();
            }catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException exception) {
                new ViewError(this.viewMenu, "Error opening Project!").setVisible(true);
            }
        }
        this.viewMenu.getPanelModeling().clear();
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for Saving a Project.
     */
    public void saveProject() {
        this.viewMenu.getFileChooserProject().setDialogTitle("Save Project");
        this.viewMenu.getFileChooserProject().setApproveButtonText("Save");
        this.viewMenu.getFileChooserProject().setApproveButtonToolTipText("Save Project");
        this.viewMenu.getFileChooserProject().setToolTipText("Save");
        String path = this.viewMenu.getProject().getPath();
        if (this.viewMenu.getProject().getPath().equals("New_Project.smty"))
            this.viewMenu.getProject().setPath(this.getPath());
        this.viewMenu.setTitle();
        this.exportProject();
    }
    
    /**
     * Method responsible for Saving As a Project.
     */
    public void saveProjectAs() {
        this.viewMenu.getFileChooserProject().setDialogTitle("Save Project As");
        this.viewMenu.getFileChooserProject().setApproveButtonText("Save");
        this.viewMenu.getFileChooserProject().setApproveButtonToolTipText("Save Project As");
        this.viewMenu.getFileChooserProject().setToolTipText("Save");
        this.viewMenu.getProject().setPath(this.getPath());
    }
    
    /**
     * Method responsible for returning Path.
     * @return Path.
     */
    public String getPath() {
        String path = "New_Project.smty";
        if (this.viewMenu.getFileChooserProject().showSaveDialog(this.viewMenu) != 1) {
            path = this.viewMenu.getFileChooserProject().getSelectedFile().getAbsolutePath();
            path = (path.toLowerCase().endsWith(".smty")) ? path : path + ".smty";
        }
        return path;
    }
    
    /**
     * Method responsible for exporting Project.
     */
    public void exportProject() {
        try {
            if (this.viewMenu.getFileChooserProject().getSelectedFile() != null) {
                new ExportProject(this.viewMenu.getProject(), this.viewMenu.getFileChooserProject().getSelectedFile().getAbsolutePath()).export();
                this.viewMenu.setSave(true);
            }
        }catch (IOException exception) {
            new ViewError(this.viewMenu, "Error writing Project File!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for showing Close Project.
     */
    public void showCloseProject() {
        if (this.viewMenu.isSave())
            this.closeProject();
        else
            new ViewSave(this.viewMenu, 3).setVisible(true);
    }
    
    /**
     * Method responsible for Closing Project.
     */
    public void closeProject() {
        this.viewMenu.setProject(null);
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for Exiting System.
     */
    public void exit() {
        if ((this.viewMenu.getProject() != null) && (this.viewMenu.isSave() == false))
            new ViewSave(this.viewMenu, 4).setVisible(true);
        else
            this.viewMenu.dispose();
    }
    
    /**
     * Method responsible for creating a New Activity Diagram.
     */
    private void newActivityDiagram() {
        if (this.viewMenu.getProject() != null) {
            ActivityDiagram diagram = new ActivityDiagram(this.viewMenu.getProject());
            this.viewMenu.getProject().addDiagram(diagram);
                            diagram.setDefaultName();
            this.viewMenu.showDiagram(diagram);
        }
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for creating a New Class Diagram.
     */
    private void newClassDiagram() {
        if (this.viewMenu.getProject() != null) {
            ClassDiagram diagram = new ClassDiagram(this.viewMenu.getProject());
            this.viewMenu.getProject().addDiagram(diagram);
                         diagram.setDefaultName();
            this.viewMenu.showDiagram(diagram);
        }
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for creating a New Component Diagram.
     */
    private void newComponentDiagram() {
        if (this.viewMenu.getProject() != null) {
            ComponentDiagram diagram = new ComponentDiagram(this.viewMenu.getProject());
            this.viewMenu.getProject().addDiagram(diagram);
                             diagram.setDefaultName();
            this.viewMenu.showDiagram(diagram);
        }
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for creating a New Sequence Diagram.
     */
    private void newSequenceDiagram() {
        if (this.viewMenu.getProject() != null) {
            SequenceDiagram diagram = new SequenceDiagram(this.viewMenu.getProject());
            this.viewMenu.getProject().addDiagram(diagram);
                            diagram.setDefaultName();
            this.viewMenu.showDiagram(diagram);
        }
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for creating a New Use Case Diagram.
     */
    private void newUseCaseDiagram() {
        if (this.viewMenu.getProject() != null) {
            UseCaseDiagram diagram = new UseCaseDiagram(this.viewMenu.getProject());
            this.viewMenu.getProject().addDiagram(diagram);
                           diagram.setDefaultName();
            this.viewMenu.showDiagram(diagram);
        }
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for editing the Profile.
     */
    private void editProfile() {
        if (this.viewMenu.getPanelModeling() != null)
            new ViewEditProfile(this.viewMenu.getPanelModeling(), this.viewMenu.getProject().getProfile()).setVisible(true);
    }
    
    /**
     * Method responsible for inserting a New Product.
     */
    private void newProduct() {
        if (this.viewMenu.getProject() != null)
            new ViewNewProduct(this.viewMenu, this.viewMenu.getProject()).setVisible(true);
    }
    
    /**
     * Method responsible for instantiating a New Product.
     */
    private void instantiateNewProduct() {
        if (this.viewMenu.getProject() != null)
            new ViewNewInstance(this.viewMenu).setVisible(true);
    }
    
    /**
     * Method responsible for inserting a New Traceability.
     */
    private void newTraceability() {
        if (this.viewMenu.getProject() != null) {
            if (!this.viewMenu.getProject().getDiagrams().isEmpty())
                new ViewNewTraceability(this.viewMenu, this.viewMenu.getProject()).setVisible(true);
            else
                new ViewMessage(this.viewMenu, "Project without Elements!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for inserting a New Metric.
     */
    private void newMetric() {
        if (this.viewMenu.getProject() != null)
            new ViewNewMetric(this.viewMenu, this.viewMenu.getProject()).setVisible(true);
    }
    
    /**
     * Method responsible for inserting a New Measure.
     */
    private void newMeasure() {
        if (this.viewMenu.getProject() != null) {
            if (this.viewMenu.getProject().getMetricsList().isEmpty() == false)
                new ViewNewMeasure(this.viewMenu, this.viewMenu.getProject()).setVisible(true);
            else
                new ViewError(this.viewMenu, "Metrics List is void!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for exporting a Diagram.
     */
    private void exportDiagram() {
        if (this.viewMenu.getProject() != null) {
            if (!this.viewMenu.getProject().getDiagrams().isEmpty())
                new ViewExportDiagram(this.viewMenu).setVisible(true);
            else
                new ViewMessage(this.viewMenu, "Project without Diagrams!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for exporting a Product.
     */
    private void exportProduct() {
        if (this.viewMenu.getProject() != null) {
            if (!this.viewMenu.getProject().getProductsList().isEmpty())
                new ViewExportProduct(this.viewMenu).setVisible(true);
            else
                new ViewMessage(this.viewMenu, "Project without Products!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for exporting the Diagram Code.
     */
    private void exportDiagramCode() {
        if (this.viewMenu.getProject() != null) {
            if (!this.viewMenu.getProject().getDiagrams("class").isEmpty())
                new ViewExportDiagramCode(this.viewMenu).setVisible(true);
            else
                new ViewMessage(this.viewMenu, "Project without Class Diagrams!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for exporting the Instance Code.
     */
    private void exportInstanceCode() {
        if (this.viewMenu.getProject() != null) {
            if (!this.viewMenu.getProject().getInstances("class").isEmpty())
                new ViewExportDiagramCode(this.viewMenu).setVisible(true);
            else
                new ViewMessage(this.viewMenu, "Project without Class Instances!").setVisible(true);
        }
    }
    
    /**
     * Method responsible for exporting the Pdf Image.
     * @param path Path.
     * @param image
     * @throws BadElementException
     * @throws IOException
     * @throws DocumentException 
     */
    public void exportPdfImage(String path, Image image) throws BadElementException, IOException, DocumentException  {
        ExportPdfImage export = new ExportPdfImage(image, path);
                       export.export();
    }
    
    /**
     * Method responsible for exporting the Image.
     * @throws com.itextpdf.text.DocumentException
     * @throws com.itextpdf.text.BadElementException
     * @throws java.net.MalformedURLException
     */
    public void exportImage() throws DocumentException, BadElementException, MalformedURLException {
        if (this.viewMenu.getFileChooserImage().showSaveDialog(this.viewMenu) != 1) {
            String        path     = this.viewMenu.getFileChooserImage().getSelectedFile().getAbsolutePath();
            BufferedImage original = this.viewMenu.getPanelModeling().getImage();
            BufferedImage image;
            if (original != null) {
                try {
                    path  = (path.toLowerCase().endsWith(".png")) ? path : path + ".png";
                    image = new ExportImage(original).getPNGImage();
                        ImageIO.write(image, "PNG", new File(path));
                    new ViewMessage(this.viewMenu, "Image exported Successfully!").setVisible(true);
                } catch (IOException exception) {
                    new ViewError(this.viewMenu, "Error to export Image!").setVisible(true);
                }
            }else {
                new ViewError(this.viewMenu, "Open a Diagram or Instance Panel!").setVisible(true);
            }
        }
    }
    
    /**
     * Method responsible for resizing the Panel Project.
     */
    private void resizePanelProject() {
        Integer width  = new Double(this.viewMenu.getSize().getWidth() * 0.2).intValue();
        Integer height = new Double(this.viewMenu.getSize().getHeight()).intValue() - 70;
        this.viewMenu.getScrollPanelProject().setPreferredSize(new Dimension(width, height));
    }
    
    /**
     * Method responsible for resizing the Panel Modeling.
     */
    private void resizePanelModeling() {
        Integer width  = new Double(this.viewMenu.getSize().getWidth() * 0.8).intValue() - 30;
        Integer height = new Double(this.viewMenu.getSize().getHeight()).intValue() - 100;
        this.viewMenu.getScrollPanelModeling().setPreferredSize(new Dimension(width, height));
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        this.resizePanelProject();
        this.resizePanelModeling();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        this.resizePanelProject();
        this.resizePanelModeling();
    }

    @Override
    public void componentShown(ComponentEvent e) {
        this.resizePanelProject();
        this.resizePanelModeling();
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        this.resizePanelProject();
        this.resizePanelModeling();
    }
}
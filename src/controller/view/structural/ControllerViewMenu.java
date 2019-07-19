package controller.view.structural;

import controller.view.ControllerView;
import file.exportation.ExportProject;
import file.importation.ImportProject;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import model.structural.base.Project;
import model.structural.diagram.ActivityDiagram;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.UseCaseDiagram;
import org.xml.sax.SAXException;
import view.message.ViewError;
import view.message.ViewSave;
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
public class ControllerViewMenu extends ControllerView {
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
        else if (this.viewMenu.getMenuItemInstantiateNewProduct().equals(event.getSource()))
            this.instantiateNewProduct();
//        else if (this.viewMenu.getMenuItemManageIdentifiers().equals(event.getSource()))
//            this.gerenciarIdentificadores();
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
        String path = this.viewMenu.getProject().getPath();
        if (this.viewMenu.getProject().getPath().equals("New_Project.smty"))
            this.viewMenu.getProject().setPath(this.getPath());
        this.viewMenu.setTitle();
        this.exportProject();
    }
    
    /**
     * Method responsible for returning Path.
     * @return Path.
     */
    public String getPath() {
        String path = "";
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
//            DiagramaComponentes diagrama = new DiagramaComponentes(this.viewMenu.getProjeto());
//            this.viewMenu.getProjeto().addDiagrama(diagrama);
//            this.viewMenu.showDiagram(diagrama);
        }
        this.viewMenu.update();
    }
    
    /**
     * Method responsible for creating a New Sequence Diagram.
     */
    private void newSequenceDiagram() {
        if (this.viewMenu.getProject() != null) {
//            DiagramaCasosDeUso diagrama = new DiagramaCasosDeUso(this.viewMenu.getProjeto());
//            this.viewMenu.getProjeto().addDiagrama(diagrama);
//            this.viewMenu.showDiagram(diagrama);
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
     * Method responsible for instantiating a New Product.
     */
    private void instantiateNewProduct() {
//        if (this.viewMenu.getPanelModeling().getPainelDiagrama() != null)
//            new ViewInstanciarProduto(this.viewMenu, this.viewMenu.getPanelModeling().getPainelDiagrama().getDiagrama()).setVisible(true);
    }
    
    /**
     * Metodo responsavel por abriar a View Gerenciar Identificador.
     * Method responsible for editing Profile.
     */
    private void editProfile() {
//        if (this.viewMenu.getPanelModeling().getPainelDiagrama() != null)
//            new ViewEditarPerfil(this.viewMenu.getPanelModeling(), this.viewMenu.getPanelModeling().getPainelDiagrama().getDiagrama().getProjeto().getPerfil()).setVisible(true);
    }
    
    /**
     * Method responsible for exporting Diagram Image.
     */
    public void exportDiagramImage() {
        if (this.viewMenu.getFileChooserImage().showSaveDialog(this.viewMenu) != 1) {
            String        path  = this.viewMenu.getFileChooserImage().getSelectedFile().getAbsolutePath();
//            BufferedImage image = this.viewMenu.getPanelModeling().getImage();
//            if (image != null) {
//                try {
//                    path = (path.toLowerCase().endsWith(".png")) ? path : path + ".png";
//                    ImageIO.write(image, "PNG", new File(path));
//                } catch (IOException exception) {
//                    new ViewErro(this.viewMenu, "Erro na Criação da Imagem!").setVisible(true);
//                }
//            }
        }
    }
}
package view.panel.export.code;

import controller.view.panel.export.code.ControllerPanelExportDiagramCode;
import javax.swing.JComboBox;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import model.structural.diagram.ClassDiagram;
import view.panel.export.PanelExport;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportDiagramCode</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Diagram Code Export</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  19/01/2020
 * @see    controller.view.panel.export.code.ControllerPanelExportDiagramCode
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportDiagramCode extends PanelExport {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelExportDiagramCode(ViewMenu viewMenu) {
        super(viewMenu);
        this.diagram    = null;
        this.controller = new ControllerPanelExportDiagramCode(this);
        this.setSettings();
        this.addComponents();
        this.getController().update();
    }
    
    @Override
    protected void addComponents() {
        super.addDirectoryField();
        
        this.add(this.createLabel("Diagram*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("diagramComboBox", new ControllerProject(this.project).getDiagrams("class"), 250), this.createConstraints(5, 1, 1, 1));
        this.setDiagram((Diagram) this.getDiagramComboBox().getSelectedItem());
        
        super.addNameTextField();
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelExportDiagramCode getController() {
        return (ControllerPanelExportDiagramCode) this.controller;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    /**
     * Method responsible for setting the Diagram.
     * @param diagram Diagram.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
    
    /**
     * Method responsible for returning the Diagram Combo Box.
     * @return Diagram Combo Box.
     */
    public JComboBox getDiagramComboBox() {
        return this.getComboBox("diagramComboBox");
    }
}
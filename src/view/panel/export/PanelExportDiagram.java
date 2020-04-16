package view.panel.export;

import controller.view.panel.export.ControllerPanelExportDiagram;
import javax.swing.JComboBox;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportDiagram</b>.</p> 
 * <p>Class responsible for defining a Panel for showing the <b>Diagram Export</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  08/11/2019
 * @see    controller.view.panel.export.ControllerPanelExportDiagram
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportDiagram extends PanelExport {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     */
    public PanelExportDiagram(ViewMenu viewMenu) {
        super(viewMenu);
        this.diagram    = null;
        this.controller = new ControllerPanelExportDiagram(this);
        this.setSettings();
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addDirectoryField();
        
        this.add(this.createLabel("Diagram*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("diagramComboBox", new ControllerProject(this.project).getDiagrams(), 250), this.createConstraints(4, 1, 1, 1));
        this.setDiagram((Diagram) this.getDiagramComboBox().getSelectedItem());
    }
    
    /**
     * Method responsible for returning the Controller.
     * @return Controller.
     */
    public ControllerPanelExportDiagram getController() {
        return (ControllerPanelExportDiagram) this.controller;
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.diagram;
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
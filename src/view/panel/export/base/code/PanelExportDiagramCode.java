package view.panel.export.base.code;

import controller.view.panel.export.base.code.ControllerPanelExportDiagramCode;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import model.structural.diagram.ClassDiagram;
import view.panel.export.PanelExport;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportDiagramCode</b>.</p> 
 * <p>Class responsible for defining a <b>Export Diagram Code Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2010-01-19
 * @see    controller.view.panel.export.base.code.ControllerPanelExportDiagramCode
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportDiagramCode extends PanelExport {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelExportDiagramCode(ViewMenu view) {
        super(view);
        this.diagram    = null;
        this.controller = new ControllerPanelExportDiagramCode(this);
        this.setDefaultProperties();
        this.addComponents();
        this.getController().update();
    }
    
    @Override
    protected void addComponents() {
        super.addDirectoryField();
        
        this.add(this.createLabel("Diagram*: "), this.createConstraints(1, 1, 0, 1));
        this.add(this.createComboBox("contextComboBox", new ControllerProject(this.project).getDiagrams("class"), 250), this.createConstraints(5, 1, 1, 1));
        this.setDiagram((Diagram) this.getContextComboBox().getSelectedItem());
        
        super.addNameTextField();
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
    
    @Override
    public ControllerPanelExportDiagramCode getController() {
        return (ControllerPanelExportDiagramCode) this.controller;
    }
}
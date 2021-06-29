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
 * @since  2020-01-19
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
        diagram    = null;
        controller = new ControllerPanelExportDiagramCode(this);
        setDefaultProperties();
        addComponents();
        getController().update();
    }
    
    @Override
    protected void addComponents() {
        addDirectoryField();
        
        add(createLabel("Diagram*: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("context", new ControllerProject(project).getDiagrams("class"), 250), createConstraints(5, 1, 1, 1));
        setDiagram((Diagram) getContextComboBox().getSelectedItem());
        
        addNameTextField();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
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
        return (ControllerPanelExportDiagramCode) controller;
    }
}
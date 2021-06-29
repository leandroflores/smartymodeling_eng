package view.panel.export.base;

import controller.view.panel.export.base.ControllerPanelExportDiagram;
import model.controller.structural.base.ControllerProject;
import model.structural.base.Diagram;
import view.panel.export.PanelExport;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelExportDiagram</b>.</p>
 * <p>Class responsible for defining a <b>Export Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-11-08
 * @see    controller.view.panel.export.base.ControllerPanelExportDiagram
 * @see    model.structural.base.Diagram
 * @see    view.panel.export.PanelExport
 */
public final class PanelExportDiagram extends PanelExport {
    private Diagram diagram;
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     */
    public PanelExportDiagram(ViewMenu view) {
        super(view);
        diagram    = null;
        controller = new ControllerPanelExportDiagram(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addComponents() {
        addDirectoryField();
        
        add(createLabel("Diagram*: "), createConstraints(1, 1, 0, 1));
        add(createComboBox("context", new ControllerProject(project).getDiagrams(), 250), createConstraints(4, 1, 1, 1));
        setDiagram((Diagram) getContextComboBox().getSelectedItem());
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return diagram;
    }
    
    /**
     * Method responsible for setting the Diagram.
     * @param diagram Diagram.
     */
    public void setDiagram(Diagram diagram) {
        this.diagram = diagram;
    }
    
    @Override
    public ControllerPanelExportDiagram getController() {
        return (ControllerPanelExportDiagram) controller;
    }
}
package view.edit.panel.base.classs.entity;

import java.awt.Dimension;
import java.awt.GridLayout;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classs.base.MethodUML;
import model.structural.diagram.classs.base.ParameterUML;
import view.Panel;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelParametersUML</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>Parameters UML</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/06/2019
 * @see    controller.view.edit.panel.base.classs.
 * @see    model.structural.diagram.classs.base.MethodUML
 * @see    view.Panel
 */
public final class PanelParametersUML extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final MethodUML methodUML;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param methodUML Method UML.
     */
    public PanelParametersUML(ViewMenu viewMenu, ClassDiagram diagram, MethodUML methodUML) {
        this.viewMenu   = viewMenu;
        this.diagram    = diagram;
        this.methodUML  = methodUML;
//        this.controller = new ControllerPanelBaseAttributeUML(this);
        this.setSettings();
        this.addComponents();
        this.setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        this.addTable();
        
        this.add(this.createButton("addButton",    "Add"));
        this.add(this.createButton("deleteButton", "Delete"));
        this.add(this.createButton("upButton",     "Up"));
        this.add(this.createButton("downButton",   "Down"));
    }
    
    private void addTable() {
        this.createTable("parametersTable");
        this.addColumns("parametersTable", new String[]{"Name", "Type"});
        this.add(this.scrollPanes.get("parametersTable"));
    }
    
    /**
     * Method responsible for setting the Attribute Values.
     */
    public void setValues() {
        this.addRows("parametersTable", this.getParametersUML());
    }
    
    /**
     * Method responsible for returning the 
     * @return 
     */
    private Object[][] getParametersUML() {
        Object[][] values    = new Object[this.methodUML.getParameters().size()][2];
        for (int i = 0;   i  < this.methodUML.getParameters().size(); i++)
                   values[i] = this.methodUML.getParameters().get(i).getValues();
        return     values;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return this.viewMenu;
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
    
    /**
     * Method responsible for returning the Method UML.
     * @return Method UML.
     */
    public MethodUML getMethodUML() {
        return this.methodUML;
    }
}
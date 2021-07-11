package view.panel.base.diagram.classes.entity;

import java.awt.Dimension;
import java.awt.GridLayout;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.MethodUML;
import view.panel.Panel;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelParametersUML</b>.</p> 
 * <p>Class responsible for defining a Panel for the <b>UML Parameters</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-09-18
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    view.panel.Panel
 */
public final class PanelParametersUML extends Panel {
    private final ViewMenu viewMenu;
    private final ClassDiagram diagram;
    private final MethodUML method;
    
    /**
     * Default constructor method of Class.
     * @param viewMenu View Menu.
     * @param diagram Class Diagram.
     * @param method Method UML.
     */
    public PanelParametersUML(ViewMenu viewMenu, ClassDiagram diagram, MethodUML method) {
        this.viewMenu = viewMenu;
        this.diagram  = diagram;
        this.method   = method;
        setSettings();
        addComponents();
        setValues();
    }
    
    /**
     * Method responsible for defining the Settings.
     */
    private void setSettings() {
        setLayout(new GridLayout(0, 1));
        setPreferredSize(new Dimension(50, 50));
    }
    
    @Override
    protected void addComponents() {
        addTable();
        
        add(createButton("add",  "Add"));
        add(createButton("del",  "Delete"));
        add(createButton("up",   "Up"));
        add(createButton("down", "Down"));
    }
    
    
    private void addTable() {
        createTable("parameters");
        addColumns("parameters", new String[]{"Name", "Type"});
        add(getScrollPane("parameters"));
    }
    
    /**
     * Method responsible for setting the Attribute Values.
     */
    public void setValues() {
        addRows("parameters", getParametersUML());
    }
    
    /**
     * Method responsible for returning the 
     * @return 
     */
    private Object[][] getParametersUML() {
        Object[][] values = new Object[method.getParameters().size()][2];
        for (int i = 0; i < method.getParameters().size(); i++)
            values[i] = method.getParameters().get(i).getValues();
        return values;
    }
    
    /**
     * Method responsible for returning the View Menu.
     * @return View Menu.
     */
    public ViewMenu getViewMenu() {
        return viewMenu;
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return diagram;
    }
    
    /**
     * Method responsible for returning the Method UML.
     * @return Method UML.
     */
    public MethodUML getMethod() {
        return method;
    }
}
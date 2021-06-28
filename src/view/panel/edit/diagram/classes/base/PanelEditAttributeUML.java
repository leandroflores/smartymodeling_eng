package view.panel.edit.diagram.classes.base;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.AttributeUML;
import view.panel.base.diagram.classes.base.PanelBaseAttributeUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditAttributeUML</b>.</p> 
 * <p>Class responsible for defining a <b>Attribute UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-12
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditAttributeUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param attribute Attribute UML.
     */
    public PanelEditAttributeUML(ViewMenu view, ClassDiagram diagram, AttributeUML attribute) {
        super(view, diagram, attribute);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanelBaseAttributeUML();
        addPanelStereotype();
        addPanelDependency();
    }
    
    /**
     * Method responsible for adding the Panel Base Attribute UML.
     */
    protected void addPanelBaseAttributeUML() {
        addPanel("base_attribute", new PanelBaseAttributeUML(viewMenu, getDiagram(), getElement()));
        createScrollPane("base_attribute", getPanelBaseAttributeUML());
        getScrollPane("base_attribute").setViewportView(getPanelBaseAttributeUML());
        tabbedPane.add("Attribute", getScrollPane("base_attribute"));
    }
    
    /**
     * Method responsible for returning the Panel Base Attribute UML.
     * @return Panel Base Attribute UML.
     */
    public PanelBaseAttributeUML getPanelBaseAttributeUML() {
        return (PanelBaseAttributeUML) getPanel("base_attribute");
    }
    
    @Override
    public AttributeUML getElement() {
        return (AttributeUML) element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
}
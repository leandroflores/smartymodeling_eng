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
        this.addComponents();
    }
    
    @Override
    protected void addPanels() {
        this.addPanelBaseAttributeUML();
        super.addPanelStereotype();
        super.addPanelDependency();
    }
    
    /**
     * Method responsible for adding the Panel Base Attribute UML.
     */
    protected void addPanelBaseAttributeUML() {
        this.addPanel("panelBaseAttributeUML", new PanelBaseAttributeUML(this.viewMenu, this.getDiagram(), this.getElement()));
        this.createScrollPane("scrollPanelBaseAttributeUML",  this.getPanelBaseAttributeUML());
        this.getScrollPane("scrollPanelBaseAttributeUML").setViewportView(this.getPanelBaseAttributeUML());
        this.tabbedPane.add("Attribute", this.getScrollPane("scrollPanelBaseAttributeUML"));
    }
    
    /**
     * Method responsible for returning the Panel Base Attribute UML.
     * @return Panel Base Attribute UML.
     */
    public PanelBaseAttributeUML getPanelBaseAttributeUML() {
        return (PanelBaseAttributeUML) this.getPanel("panelBaseAttributeUML");
    }
    
    @Override
    public AttributeUML getElement() {
        return (AttributeUML) this.element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
}
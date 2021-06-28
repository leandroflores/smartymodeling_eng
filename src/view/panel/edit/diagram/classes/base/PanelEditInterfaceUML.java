package view.panel.edit.diagram.classes.base;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.InterfaceUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;
import view.panel.base.diagram.classes.base.PanelBaseDescription;

/**
 * <p>Class of View <b>PanelEditInterfaceUML</b>.</p> 
 * <p>Class responsible for defining a <b>Interface UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-17
 * @see    model.structural.diagram.classes.base.InterfaceUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditInterfaceUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param interface_ Interface UML.
     */
    public PanelEditInterfaceUML(ViewMenu view, ClassDiagram diagram, InterfaceUML interface_) {
        super(view, diagram, interface_);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanels("Interface");
        addPanelBaseDescription();
    }
    
    /**
     * Method responsible for adding the Panel Base Description.
     */
    protected void addPanelBaseDescription() {
        addPanel("description", new PanelBaseDescription(viewMenu, getElement()));
        createScrollPane("description",  getPanelBaseDescription());
        getScrollPane("description").setViewportView(getPanelBaseDescription());
        tabbedPane.add("Description", getScrollPane("description"));
    }
    
    /**
     * Method responsible for returning the Panel Base Description.
     * @return Panel Base Description.
     */
    public PanelBaseDescription getPanelBaseDescription() {
        return (PanelBaseDescription) getPanel("description");
    }
    
    @Override
    public InterfaceUML getElement() {
        return (InterfaceUML) element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
}
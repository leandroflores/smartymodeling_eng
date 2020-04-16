package view.panel.edit.diagram.classes.base;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.InterfaceUML;
import view.panel.edit.base.PanelEditElement;
import view.structural.ViewMenu;

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
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addComponents("Interface");
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public InterfaceUML getElement() {
        return (InterfaceUML) this.element;
    }
}
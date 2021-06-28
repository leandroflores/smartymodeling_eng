package view.panel.edit.diagram.classes.base;

import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.edit.base.PanelEditElement;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelEditPackageUML</b>.</p> 
 * <p>Class responsible for defining a <b>Package UML Edit Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-08-14
 * @see    model.structural.diagram.classes.base.PackageUML
 * @see    view.panel.edit.base.PanelEditElement
 */
public final class PanelEditPackageUML extends PanelEditElement {
    
    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     * @param package_ Package UML.
     */
    public PanelEditPackageUML(ViewMenu view, ClassDiagram diagram, PackageUML package_) {
        super(view, diagram, package_);
        addComponents();
    }
    
    @Override
    protected void addPanels() {
        addPanels("Package");
    }
    
    @Override
    public PackageUML getElement() {
        return (PackageUML) element;
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
}
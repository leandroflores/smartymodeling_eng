package view.modal.edit.diagram.classes.base;

import controller.view.modal.edit.diagram.classes.base.ControllerViewEditPackageUML;
import java.awt.Dimension;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.base.PackageUML;
import view.modal.edit.ViewEdit;
import view.panel.base.diagram.PanelBaseElement;
import view.panel.base.stereotype.PanelStereotype;
import view.panel.edit.diagram.classes.base.PanelEditPackageUML;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>ViewEditPackageUML</b>.</p>
 * <p>Class responsible for defining the <b>Package UML Edit View</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-23
 * @see    controller.view.modal.edit.diagram.classes.base.ControllerViewEditPackageUML
 * @see    model.structural.diagram.classes.base.PackageUML
 * @see    view.modal.edit.ViewEdit
 */
public final class ViewEditPackageUML extends ViewEdit {
    private final ClassDiagram diagram;
    private final PackageUML package_;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param diagram Class Diagram.
     * @param package_ Package UML.
     */
    public ViewEditPackageUML(PanelModeling panel, ClassDiagram diagram, PackageUML package_) {
        super(panel.getViewMenu());
        this.diagram    = diagram;
        this.package_   = package_;
        this.controller = new ControllerViewEditPackageUML(this);
        this.title      = "Edit Package Data";
        initComponents();
    }
    
    @Override
    protected Dimension getViewDimension() {
        return new Dimension(600, 445);
    }
    
    @Override
    protected PanelEditPackageUML createPanelEdit() {
        return new PanelEditPackageUML(view, diagram, package_);
    }
    
    @Override
    protected Dimension getPanelDimension() {
        return new Dimension(500, 300);
    }
    
    @Override
    public PanelEditPackageUML getPanelEdit() {
        return (PanelEditPackageUML) super.getPanelEdit();
    }
    
    /**
     * Method responsible for returning the Panel Base Package.
     * @return Panel Base Package.
     */
    public PanelBaseElement getPanelBasePackageUML() {
        return getPanelEdit().getPanelBaseElement();
    }
    
    /**
     * Method responsible for returning the Panel Stereotype.
     * @return Panel Stereotype.
     */
    public PanelStereotype getPanelStereotype() {
        return getPanelEdit().getPanelStereotype();
    }
}
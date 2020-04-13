package controller.view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.delete.ViewDeleteElement;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelClassDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerPanelClassDiagram extends ControllerPanelDiagram {

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerPanelClassDiagram(PanelClassDiagram panel) {
        super(panel);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        switch (this.getPanelDiagram().getOperation()) {
            case "Package":
                this.addPackage(event);
                break;
            case "Class":
                this.addClass(event);
                break;
            case "Interface":
                this.addInterface(event);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for returning the Parent Package UML.
     * @param  event Mouse Event.
     * @return Parent Package UML.
     */
    private PackageUML getParent(MouseEvent event) {
        Object  cell    = this.getPanelDiagram().getComponent().getCellAt(event.getX(), event.getY());
        String  id      = this.getId(cell);
        Element element = this.getPanelDiagram().getDiagram().getElement(id);
        return  this.getParent(element);
    }
    
    /**
     * Method responsible for returning the Cell Id.
     * @param  cell Cell Object.
     * @return Cell Id.
     */
    private String getId(Object cell) {
        if ((cell != null) && (cell instanceof mxCell)) {
            String id = ((mxCell) cell).getId();
            return id.contains("(") ? id.substring(0, id.indexOf("(")) : id;
        }
        return "";
    }
    
    /**
     * Method responsible for returning the Package Parent.
     * @param  element Origin Element. 
     * @return Package Parent.
     */
    private PackageUML getParent(Element element) {
        if (element instanceof PackageUML)
            return (PackageUML) element;
        else if (element instanceof Entity)
            return ((Entity) element).getPackageUML();
        else if (element instanceof AttributeUML)
            return ((AttributeUML) element).getEntity().getPackageUML();
        else if (element instanceof MethodUML)
            return ((MethodUML) element).getEntity().getPackageUML();
        return null;
    }
    
    /**
     * Method responsible for setting the Parent.
     * @param parent Package UML.
     * @param package_ Package UML.
     */
    private void setParent(PackageUML parent, PackageUML package_) {
        if ((parent != null) && (!parent.equals(package_))) {
            Integer x = package_.getGlobalX() - parent.getGlobalX();
            Integer y = package_.getGlobalY() - parent.getGlobalY();
            package_.setPosition(x, y);
            package_.setParent(parent);
            parent.addPackage(package_);
            parent.updateSize();
        }
    }
    
    /**
     * Method responsible for setting the Parent.
     * @param package_ Package UML.
     * @param entity Entity.
     */
    private void setParent(PackageUML package_, Entity entity) {
        if (package_ != null) {
            Integer x = entity.getGlobalX() - package_.getGlobalX();
            Integer y = entity.getGlobalY() - package_.getGlobalY();
            entity.setPosition(x, y);
            entity.setPackageUML(package_);
            package_.addEntity(entity);
            package_.updateSize();
        }
    }
    
    /**
     * Method responsible for adding a New Package UML.
     * @param event Mouse Event.
     */
    public void addPackage(MouseEvent event) {
        PackageUML package_ = new PackageUML(this.getPanelDiagram().getDiagram());
                   package_.setPosition(event.getX(), event.getY());
                   package_.setGlobalPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addPackage(package_);
                   package_.setDefaultName();
        this.setParent(this.getParent(event), package_);
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New Class UML.
     * @param event Mouse Event.
     */
    public void addClass(MouseEvent event) {
        ClassUML class_ = new ClassUML(this.getPanelDiagram().getDiagram());
                 class_.setPosition(event.getX(), event.getY());
                 class_.setGlobalPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addClass(class_);
                 class_.setDefaultName();
        this.setParent(this.getParent(event), class_);
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New Interface UML.
     * @param event Mouse Event.
     */
    public void addInterface(MouseEvent event) {
        InterfaceUML interface_ = new InterfaceUML(this.getPanelDiagram().getDiagram());
                     interface_.setPosition(event.getX(), event.getY());
                     interface_.setGlobalPosition(event.getX(), event.getY());
        this.getPanelDiagram().getDiagram().addInterface(interface_);
                     interface_.setDefaultName();
        this.setParent(this.getParent(event), interface_);
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
    }
    
    @Override
    public void edit() {
        mxCell cell = (mxCell) this.getPanelDiagram().getGraph().getSelectionCell();
        String id   = this.getPanelDiagram().getIdentifier(cell);
        if (id != null)
            this.edit(cell, id);
    }
    
    /**
     * Method responsible for editing the Element by Id.
     * @param cell Graph Cell.
     * @param id Element Id.
     */
    private void edit(mxCell cell, String id) {
//        PanelModeling modeling = this.getPanelDiagram().getViewMenu().getPanelModeling();
//        if (this.getPanelDiagram().getDiagram().getElement(id) instanceof AttributeUML)
//            new ViewEditAttribute(modeling, this.getPanelDiagram().getDiagram(), (AttributeUML) this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
//        else if (this.panelDiagram.getDiagram().getElement(id) instanceof MethodUML)
//            new ViewEditMethod(this.panelDiagram.getViewMenu().getPanelModeling(),    this.panelDiagram.getDiagram(), (MethodUML) this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
//        else if (this.panelDiagram.getDiagram().getElement(id) != null)
//            new ViewEditElement(this.panelDiagram.getViewMenu().getPanelModeling(),   this.panelDiagram.getDiagram(), this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
//        else 
        if (cell.getId().endsWith("(name)"))
            this.editElement(cell);
    }
    
    /**
     * Method responsible for editing the Element by Cell.
     * @param cell Element Cell.
     */
    private void editElement(mxCell cell) {
        this.getPanelDiagram().getComponent().startEditingAtCell(cell);
        this.update();
    }
    
    @Override
    public void delete() {
        mxCell cell = (mxCell) this.getPanelDiagram().getGraph().getSelectionCell();
        String id   = this.getPanelDiagram().getIdentifier(cell);
        if (id != null)
            this.delete(id);
    }
    
    /**
     * Method responsible for deleting the Object Cell by Id.
     * @param id Element Id.
     */
    private void delete(String id) {
        if (this.getDiagram().getAssociation(id)  != null)
            this.delete(this.getDiagram().getAssociation(id));
        else if (this.getDiagram().getElement(id) != null)
            this.delete(this.getDiagram().getElement(id));
            
//        else if (this.panelDiagram.getDiagram().getElement(id) instanceof AttributeUML)
//            this.panelDiagram.getDiagram().removeAttribute((AttributeUML) this.panelDiagram.getDiagram().getElement(id));
//        else if (this.panelDiagram.getDiagram().getElement(id) instanceof MethodUML)
//            this.panelDiagram.getDiagram().removeMethod((MethodUML) this.panelDiagram.getDiagram().getElement(id));
//        else if (this.panelDiagram.getDiagram().getElement(id) != null)
//            new ViewDeleteElement(this.panelDiagram.getViewMenu().getPanelModeling(), this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
//        this.update();
    }
    
    /**
     * Method responsible for deleting a Association.
     * @param association Association.
     */
    private void delete(Association association) {
        this.getDiagram().removeAssociation(association);
        this.update();
    }
    
    /**
     * Method responsible for deleting a Attribute.
     * @param attribute Attribute.
     */
    private void delete(AttributeUML attribute) {
        this.getDiagram().removeAttribute(attribute);
        this.update();
    }
    
    /**
     * Method responsible for deleting a Method.
     * @param method Method.
     */
    private void delete(MethodUML method) {
        this.getDiagram().removeMethod(method);
        this.update();
    }
    
    /**
     * Method responsible for deleting a Element.
     * @param element Element.
     */
    private void delete(Element element) {
        new ViewDeleteElement(this.getPanelDiagram().getViewMenu().getPanelModeling(), element).setVisible(true);
        this.update();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.getPanelDiagram().getDiagram();
    }
    
    @Override
    protected PanelClassDiagram getPanelDiagram() {
        return (PanelClassDiagram) this.panel;
    }
}
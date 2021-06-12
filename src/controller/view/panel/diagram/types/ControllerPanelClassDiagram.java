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
import view.modal.delete.base.ViewDeleteElement;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelClassDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelClassDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    model.structural.diagram.ClassDiagram
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
        switch (getPanel().getOperation()) {
            case "Package":
                addPackage(event);
                break;
            case "Class":
                addClass(event);
                break;
            case "Interface":
                addInterface(event);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for returning the Parent Package.
     * @param  event Mouse Event.
     * @return Parent Package.
     */
    private PackageUML getParent(MouseEvent event) {
        Object  cell    = getPanel().getComponent().getCellAt(event.getX(), event.getY());
        String  id      = getId(cell);
        Element element = getDiagram().getElement(id);
        return  getParent(element);
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
     * Method responsible for adding a New UML Package.
     * @param event Mouse Event.
     */
    public void addPackage(MouseEvent event) {
        PackageUML package_ = new PackageUML(getDiagram());
                   package_.setPosition(event.getX(), event.getY());
                   package_.setGlobalPosition(event.getX(), event.getY());
                   getDiagram().addPackage(package_);
                   package_.setDefaultName();
        setParent(getParent(event), package_);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New UML Class.
     * @param event Mouse Event.
     */
    public void addClass(MouseEvent event) {
        ClassUML class_ = new ClassUML(getDiagram());
                 class_.setPosition(event.getX(), event.getY());
                 class_.setGlobalPosition(event.getX(), event.getY());
                 getDiagram().addClass(class_);
                 class_.setDefaultName();
        setParent(getParent(event), class_);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a New UML Interface.
     * @param event Mouse Event.
     */
    public void addInterface(MouseEvent event) {
        InterfaceUML interface_ = new InterfaceUML(getDiagram());
                     interface_.setPosition(event.getX(), event.getY());
                     interface_.setGlobalPosition(event.getX(), event.getY());
                     getDiagram().addInterface(interface_);
                     interface_.setDefaultName();
        setParent(getParent(event), interface_);
        getPanel().updateGraph();
        getPanel().getViewMenu().update();
    }
    
    @Override
    public void edit() {
        mxCell cell = (mxCell) getPanel().getGraph().getSelectionCell();
        String id   = getPanel().getIdentifier(cell);
        if (id != null)
            edit(cell, id);
    }
    
    /**
     * Method responsible for editing the Element by Id.
     * @param cell Graph Cell.
     * @param id Element Id.
     */
    private void edit(mxCell cell, String id) {
        if (cell.getId().endsWith("(name)"))
            editElement(cell);
    }
    
    /**
     * Method responsible for editing the Element by Cell.
     * @param cell Element Cell.
     */
    private void editElement(mxCell cell) {
        getPanel().getComponent().startEditingAtCell(cell);
        update();
    }
    
    @Override
    public void delete() {
        mxCell cell = (mxCell) getPanel().getGraph().getSelectionCell();
        String id   = getPanel().getIdentifier(cell);
        if (id != null)
            delete(id);
    }
    
    /**
     * Method responsible for deleting the Object Cell by Id.
     * @param id Element Id.
     */
    private void delete(String id) {
        if (getDiagram().getAssociation(id) != null)
            delete(getDiagram().getAssociation(id));
        else if (getDiagram().getElement(id) != null)
            delete(getDiagram().getElement(id));
        update();
    }
    
    /**
     * Method responsible for deleting a Association.
     * @param association Association.
     */
    private void delete(Association association) {
        getDiagram().removeAssociation(association);
    }
    
    /**
     * Method responsible for deleting a Attribute.
     * @param attribute Attribute.
     */
    private void delete(AttributeUML attribute) {
        getDiagram().removeAttribute(attribute);
    }
    
    /**
     * Method responsible for deleting a Method.
     * @param method Method.
     */
    private void delete(MethodUML method) {
        getDiagram().removeMethod(method);
    }
    
    /**
     * Method responsible for deleting a Element.
     * @param element Element.
     */
    private void delete(Element element) {
        new ViewDeleteElement(getPanel().getViewMenu().getPanelModeling(), getDiagram(), element).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public ClassDiagram getDiagram() {
        return getPanel().getDiagram();
    }
    
    @Override
    public PanelClassDiagram getPanel() {
        return (PanelClassDiagram) panel;
    }
}
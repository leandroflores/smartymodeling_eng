package controller.view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import model.structural.base.Element;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.delete.ViewDeleteElement;
import view.edit.ViewEditElement;
import view.edit.classs.ViewEditAttribute;
import view.edit.classs.ViewEditMethod;
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
    private final PanelClassDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerPanelClassDiagram(PanelClassDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelDiagram.getPackageButton().equals(event.getSource()))
            this.setAddPackage();
        else if (this.panelDiagram.getClassButton().equals(event.getSource()))
            this.setAddClass();
        else if (this.panelDiagram.getInterfaceButton().equals(event.getSource()))
            this.setAddInterface();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
    }
    
    @Override
    public void mousePressed(MouseEvent evento) {
        switch (this.panelDiagram.getOperation()) {
            case "Package":
                this.addPackage(evento);
                break;
            case "Class":
                this.addClass(evento);
                break;
            case "Interface":
                this.addInterface(evento);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for setting Add Package Operation.
     */
    public void setAddPackage() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getPackageButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Package");
    }
    
    /**
     * Method responsible for setting Add Class Operation.
     */
    public void setAddClass() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getClassButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Class");
    }
    
    /**
     * Method responsible for setting Add Interface Operation.
     */
    public void setAddInterface() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getInterfaceButton().setBackground(this.getFocusColor());
        this.panelDiagram.setOperation("Interface");
    }
    
    /**
     * Method responsible for returning the Parent Package UML.
     * @param  event Mouse Event.
     * @return Parent Package UML.
     */
    private PackageUML getParent(MouseEvent event) {
        Object  cell    = this.panelDiagram.getComponent().getCellAt(event.getX(), event.getY());
        String  id      = this.getId(cell);
        Element element = this.panelDiagram.getDiagram().getElement(id);
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
     * @param packageUML Package UML.
     */
    private void setParent(PackageUML parent, PackageUML packageUML) {
        if ((parent != null) && (!parent.equals(packageUML))) {
            Integer x = packageUML.getGlobalX() - parent.getGlobalX();
            Integer y = packageUML.getGlobalY() - parent.getGlobalY();
            packageUML.setPosition(x, y);
            packageUML.setParent(parent);
            parent.addPackage(packageUML);
            parent.updateSize();
        }
    }
    
    /**
     * Method responsible for setting the Parent.
     * @param parent Package UML.
     * @param entity Entity.
     */
    private void setParent(PackageUML parent, Entity entity) {
        if (parent != null) {
            Integer x = entity.getGlobalX() - parent.getGlobalX();
            Integer y = entity.getGlobalY() - parent.getGlobalY();
            entity.setPosition(x, y);
            entity.setPackageUML(parent);
            parent.addEntity(entity);
            parent.updateSize();
        }
    }
    
    /**
     * Method responsible for adding a new Package UML.
     * @param event Mouse Event.
     */
    public void addPackage(MouseEvent event) {
        PackageUML packageUML = new PackageUML(this.panelDiagram.getDiagram());
                   packageUML.setPosition(event.getX(), event.getY());
                   packageUML.setGlobalPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addPackage(packageUML);
                   packageUML.setDefaultName();
        this.setParent(this.getParent(event), packageUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Class UML.
     * @param event Mouse Event.
     */
    public void addClass(MouseEvent event) {
        ClassUML classUML = new ClassUML(this.panelDiagram.getDiagram());
                 classUML.setPosition(event.getX(), event.getY());
                 classUML.setGlobalPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addClass(classUML);
                 classUML.setDefaultName();
        this.setParent(this.getParent(event), classUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Method responsible for adding a new Interface UML.
     * @param event Mouse Event.
     */
    public void addInterface(MouseEvent event) {
        InterfaceUML interfaceUML = new InterfaceUML(this.panelDiagram.getDiagram());
                     interfaceUML.setPosition(event.getX(), event.getY());
                     interfaceUML.setGlobalPosition(event.getX(), event.getY());
        this.panelDiagram.getDiagram().addInterface(interfaceUML);
                     interfaceUML.setDefaultName();
        this.setParent(this.getParent(event), interfaceUML);
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
    }
    
    @Override
    public void edit() {
        if (this.panelDiagram.getGraph() != null) {
            mxCell cell = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
            String id   = this.panelDiagram.getIdentifiers().get(cell);
            if (id != null)
                this.edit(cell, id);
        }
    }
    
    /**
     * Method responsible for editing the Element by Id.
     * @param cell Graph Cell.
     * @param id Element Id.
     */
    private void edit(mxCell cell, String id) {
        if (this.panelDiagram.getDiagram().getElement(id) instanceof AttributeUML)
            new ViewEditAttribute(this.panelDiagram.getViewMenu().getPanelModeling(), this.panelDiagram.getDiagram(), (AttributeUML) this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
        else if (this.panelDiagram.getDiagram().getElement(id) instanceof MethodUML)
            new ViewEditMethod(this.panelDiagram.getViewMenu().getPanelModeling(),    this.panelDiagram.getDiagram(), (MethodUML) this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
        else if (this.panelDiagram.getDiagram().getElement(id) != null)
            new ViewEditElement(this.panelDiagram.getViewMenu().getPanelModeling(),   this.panelDiagram.getDiagram(), this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
        else if (cell.getId().endsWith("(name)"))
            this.editElement(cell);
    }
    
    /**
     * Method responsible for editing the Element.
     * @param cell Element Cell.
     */
    private void editElement(mxCell cell) {
        this.panelDiagram.getComponent().startEditingAtCell(cell);
        this.update();
    }
    
    @Override
    public void delete() {
        if (this.panelDiagram.getGraph() != null) {
            mxCell cell = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
            String id   = this.panelDiagram.getIdentifiers().get(cell);
            if (id != null)
                this.delete(id);
        }
    }
    
    /**
     * Method responsible for deleting the Element by Id.
     * @param id Element Id.
     */
    private void delete(String id) {
        if (this.panelDiagram.getDiagram().getAssociation(id) != null)
            this.panelDiagram.getDiagram().removeAssociation(this.panelDiagram.getDiagram().getAssociation(id));
        else if (this.panelDiagram.getDiagram().getElement(id) instanceof AttributeUML)
            this.panelDiagram.getDiagram().removeAttribute((AttributeUML) this.panelDiagram.getDiagram().getElement(id));
        else if (this.panelDiagram.getDiagram().getElement(id) instanceof MethodUML)
            this.panelDiagram.getDiagram().removeMethod((MethodUML) this.panelDiagram.getDiagram().getElement(id));
        else if (this.panelDiagram.getDiagram().getElement(id) != null)
            new ViewDeleteElement(this.panelDiagram.getViewMenu().getPanelModeling(), this.panelDiagram.getDiagram().getElement(id)).setVisible(true);
        this.update();
    }
    
    /**
     * Method responsible for updating the Panel Diagram.
     */
    private void update() {
        this.panelDiagram.updateDiagram();
        this.panelDiagram.getViewMenu().update();
        this.panelDiagram.getViewMenu().setSave(false);
    }
}
package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.util.mxEvent;
import controller.view.panel.diagram.association.types.ControllerEventAssociationClass;
import controller.view.panel.diagram.event.ControllerEventFocus;
import controller.view.panel.diagram.event.ControllerEventPoints;
import controller.view.panel.diagram.event.classes.ControllerEventChange;
import controller.view.panel.diagram.event.classes.ControllerEventEdit;
import controller.view.panel.diagram.event.classes.ControllerEventGroup;
import controller.view.panel.diagram.event.classes.ControllerEventMove;
import controller.view.panel.diagram.event.classes.ControllerEventResize;
import controller.view.panel.diagram.event.classes.ControllerEventSelect;
import controller.view.panel.diagram.types.ControllerPanelClassDiagram;
import java.awt.event.MouseListener;
import model.structural.base.Element;
import model.structural.base.Stereotype;
import model.structural.base.association.Association;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import style.association.types.StyleClassAssociation;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelClassOperation;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelClassDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    controller.view.panel.diagram.types.ControllerPanelClassDiagram
 * @see    model.structural.diagram.ClassDiagram
 * @see    view.panel.diagram.PanelDiagram
 */
public final class PanelClassDiagram extends PanelDiagram {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param diagram Class Diagram.
     */
    public PanelClassDiagram(ViewMenu view, ClassDiagram diagram) {
        super(view, diagram);
        controller = new ControllerPanelClassDiagram(this);
        setDefaultProperties();
        addComponents();
        setClick();
    }
    
    @Override
    public void initPanelOperation() {
        panel = new PanelClassOperation(this);
    }
    
    @Override
    public void initStyleAssociation() {
        style = new StyleClassAssociation();
    }
    
    @Override
    protected void addElement(Element element) {
        if (element instanceof PackageUML)
            addPackage((PackageUML) element);
        else if (element instanceof ClassUML)
            addClass((ClassUML) element);
        else if (element instanceof InterfaceUML)
            addInterface((InterfaceUML) element);
    }
    
    /**
     * Method responsible for adding the Package UML Cell.
     * @param package_ Package UML.
     */
    protected void addPackage(PackageUML package_) {
        if (package_.getParent() == null)
            addPackage(parent, package_);
    }
    
    /**
     * Method responsible for adding the Class UML Cell.
     * @param class_ Class UML.
     */
    protected void addClass(ClassUML class_) {
        if (class_.getPackageUML() == null)
            addClass(parent, class_);
    }
    
    /**
     * Method responsible for adding the Interface UML Cell.
     * @param interface_ Interface UML.
     */
    protected void addInterface(InterfaceUML interface_) {
        if (interface_.getPackageUML() == null)
            addInterface(parent, interface_);
    }
    
    /**
     * Method responsible for adding a Package UML.
     * @param parent Parent Package.
     * @param package_ Packagem UML.
     */
    private void addPackage(Object parent, PackageUML package_) {
        addStyle(package_.getStyleLabel(), package_.getStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, package_.getId(), "", package_.getPosition().x, package_.getPosition().y, package_.getSize().x, package_.getSize().y, package_.getStyleLabel());
               cell.setConnectable(false);
        insert(cell, package_);
            addPackages(package_, cell);
            addEntities(package_, cell);
        addElementCell(package_, cell);
    }
    
    /**
     * Method responsible for adding the Package Childs.
     * @param package_ Package UML.
     * @param parent Parent Vertex.
     */
    private void addPackages(PackageUML package_, Object parent) {
        for (PackageUML current : package_.getPackagesList())
            addPackage(parent, current);
    }
    
    /**
     * Method responsible for adding the Package Entities.
     * @param package_ Package UML.
     * @param parent Parent Vertex.
     */
    private void addEntities(PackageUML package_, Object parent) {
        for (Entity entity : package_.getEntitiesList()) {
            if (entity instanceof ClassUML)
                addClass(parent, (ClassUML) entity);
            else if (entity instanceof InterfaceUML)
                addInterface(parent, (InterfaceUML) entity);
        }
    }
    
    /**
     * Method responsible for adding the Class UML.
     * @param parent Parent Vertex.
     * @param class_ Class UML.
     */
    private void addClass(Object parent, ClassUML class_) {
        addStyle(class_.getStyleLabel(), class_.getStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, class_.getId(), "", class_.getPosition().x, class_.getPosition().y, class_.getSize().x, class_.getSize().y, class_.getStyleLabel());
               cell.setConnectable(true);
        insert(cell, class_);
        addElementCell(class_, cell);
    }
    
    /**
     * Method responsible for adding the Interface UML.
     * @param parent Parent Vertex.
     * @param interface_ Interface UML.
     */
    private void addInterface(Object parent, InterfaceUML interface_) {
        addStyle(interface_.getStyleLabel(), interface_.getStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, interface_.getId(), "", interface_.getPosition().x, interface_.getPosition().y, interface_.getSize().x, interface_.getSize().y, interface_.getStyleLabel());
               cell.setConnectable(true);
        insert(cell, interface_);
        addElementCell(interface_, cell);
    }
    
    /**
     * Method responsible for inserting a Package Cell.
     * @param cell Parent Cell.
     * @param package_ Package UML.
     */
    private void insert(mxCell cell, PackageUML package_) {
        addStyle("packageHeader", package_.getPackageStyle());
        addStyle("packageName",   package_.getNameStyle());
        
        mxCell head = (mxCell) getGraph().insertVertex(cell, package_.getId() + "(name)", "",  0,  0, package_.getWidth() * 0.3,                        15, "packageHeader");
               head.setConnectable(false);
        mxCell body = (mxCell) getGraph().insertVertex(cell, package_.getId() + "(body)", "",  0, 15, package_.getWidth(),       package_.getHeight() - 15, "packageHeader");
               body.setConnectable(false);
               
               addStereotypeCells(body, package_);
               addNameCell(body, package_);

        addIdentifier(head, package_.getId());
        addIdentifier(body, package_.getId());
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param package_ Package UML.
     */
    private void addStereotypeCells(mxCell parent, PackageUML package_) {
        Integer index = 0;
        for (Stereotype stereotype : getDiagram().getStereotypesList(package_)) {
            addStyle("stereotypeStyle", stereotype.getStyle());
            mxCell cell   = (mxCell) getGraph().insertVertex(parent, "LINK#" + package_.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (index * 21) + 5, package_.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
                   cell.setTerminal(null, true);
                   cell.setId(stereotype.getId());
                   index += 1; 
            addIdentifier(cell, stereotype.getId());
            addIdentifier(cell.getId(), package_.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param package_ Package UML.
     */
    private void addNameCell(mxCell parent, PackageUML package_) {
       addStyle("nameStyle", package_.getNameStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, package_.getId() + "(name)", package_.getName(), 5, package_.getNamePosition(), package_.getWidth() - 10, 25, "nameStyle");
               cell.setConnectable(false);
               cell.setId(package_.getId() + "(name)");
        addIdentifier(cell.getId(), package_.getId());
    }
    
    /**
     * Method responsible for inserting a Entity Cell.
     * @param cell Parent Cell.
     * @param entity Entity.
     */
    private void insert(mxCell cell, Entity entity) {
        addStereotypeCells(cell, entity);
        addInterfaceStereotypeCell(cell, entity);
        addNameCell(cell, entity);
        addLineCell(cell, entity.getNamePosition() + 27, entity);
        addNewAttributeCell(cell, entity);
        addAttributesCells(cell, entity);
        addLineCell(cell, entity.getMethodsPosition(), entity);
        addNewMethodCell(cell, entity);
        addMethodsCells(cell, entity);
    }

    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addStereotypeCells(mxCell parent, Entity entity) {
        Integer index = 0;
        for (Stereotype stereotype : getDiagram().getStereotypesList(entity)) {
            addStyle("stereotypeStyle", stereotype.getStyle());
            mxCell cell   = (mxCell) getGraph().insertVertex(parent, "LINK#" + entity.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (index * 21) + 5, entity.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
                   cell.setId(stereotype.getId());
                   index++; 
            addIdentifier(cell, stereotype.getId());
            addIdentifier(cell.getId(), entity.getId());
        }
    }
    
    /**
     * Method responsible for adding the Interface Stereotype Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addInterfaceStereotypeCell(mxCell parent, Entity entity) {
        if (entity instanceof InterfaceUML) {
            addStyle("stereotypeStyle", entity.getStereotypeStyle());
            mxCell cell = (mxCell) getGraph().insertVertex(parent, entity.getId() + "(interface)", "<<interface>>", 5, entity.getInterfacePosition(), entity.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
            addIdentifier(cell, entity.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNameCell(mxCell parent, Entity entity) {
        addStyle("nameStyle" + entity.getId(), entity.getNameStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, entity.getId() + "(name)", entity.getName(), 5, entity.getNamePosition(), entity.getWidth() - 10, 25, "nameStyle" + entity.getId());
               cell.setConnectable(false);
               cell.setId(entity.getId() + "(name)");
        addIdentifier(cell.getId(), entity.getId());
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addLineCell(mxCell parent, Integer y, Entity entity) {
        addStyle("lineStyle", entity.getLineStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, null, "", 0, y, entity.getWidth(), 1, "lineStyle");
               cell.setConnectable(false);
        addIdentifier(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Add Attribute Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNewAttributeCell(mxCell parent, Entity entity) {
        addStyle("newAttributeStyle", entity.getNewAttributeStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, entity.getId() + "(newAttribute)", "", 5, entity.getAttributesPosition(), 5, 5, "newAttributeStyle");
               cell.setConnectable(false);
        addIdentifier(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Attributes Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addAttributesCells(mxCell parent, Entity entity) {
        Integer index = 0;
        for (AttributeUML attribute : entity.getAttributesList()) {
            addStyle(attribute.getStyleLabel(), attribute.getStyle());
            mxCell cell   = (mxCell) getGraph().insertVertex(parent, attribute.getId(), attribute.getCompleteSignature(), 5, entity.getAttributesPosition() + (index * 16) + 6, entity.getWidth() - 10, 15, attribute.getStyleLabel());
                   cell.setConnectable(false);
                   cell.setId(attribute.getId());
                   index += 1; 
            addElementCell(attribute, cell);
            addIdentifier(cell.getId(), attribute.getId());
        }
    }
    
    /**
     * Method responsible for adding the New Method Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNewMethodCell(mxCell parent, Entity entity) {
        addStyle("newMethodStyle", entity.getNewMethodStyle());
        mxCell cell = (mxCell) getGraph().insertVertex(parent, entity.getId() + "(newMethod)", "", 5, entity.getMethodsPosition() + 5, 10, 5, "newMethodStyle");
               cell.setConnectable(false);
        addIdentifier(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Methods Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addMethodsCells(mxCell parent, Entity entity) {
        Integer index = 0;
        for (MethodUML method : entity.getMethodsList()) {
            addStyle(method.getStyleLabel(), method.getStyle());
            mxCell cell   = (mxCell) getGraph().insertVertex(parent, method.getId(), method.getCompleteSignature(), 5, entity.getMethodsPosition() + 10 + (index * 16), entity.getWidth() - 10, 15, method.getStyleLabel());
                   cell.setConnectable(false);
                   cell.setId(method.getId());
                   index++; 
            addElementCell(method, cell);
            addIdentifier(cell.getId(), method.getId());
        }
    }
    
    /**
     * Method responsible for adding the Association.
     * @param association Association.
     */
    @Override
    protected void addAssociation(Association association) {
        super.addAssociation(association);
        addCardinality(association);
    }
    
    /**
     * Method responsible for adding the Cardinality.
     * @param association Association.
     */
    private void addCardinality(Association association) {
        if (association instanceof AssociationUML) {
            addStyle(((AssociationUML) association).getCardinalityLabel(), 
                          ((AssociationUML) association).getCardinalityStyle());
            addSourceLabel((AssociationUML) association);
            addTargetLabel((AssociationUML) association);
        }
    }
    
    /**
     * Method responsible for adding the Source Label.
     * @param association Association UML.
     */
    private void addSourceLabel(AssociationUML association) {
        if (!association.isDirection()) {
            mxCell source = (mxCell) getGraph().insertVertex(parent, association.getId() + "(source)", association.getSourceLabel(), association.getSourceX(), association.getSourceY(), 30, 20, association.getCardinalityLabel());
                   source.setConnectable(false);
            addIdentifier(source, association.getId() + "(source)");
        }
    }
    
    /**
     * Method responsible for adding the Target Label.
     * @param association Association UML. 
     */
    public void addTargetLabel(AssociationUML association) {
        mxCell target = (mxCell) getGraph().insertVertex(parent, association.getId() + "(target)", association.getTargetLabel(), association.getTargetX(), association.getTargetY(), 30, 20, association.getCardinalityLabel());
               target.setConnectable(false);
        addIdentifier(target, association.getId() + "(target)");
    }
    
    @Override
    public void setStyle() {
        switch (getType()) {
            case 0:
                getStyle().setAssociationStyle(getEdgeStyle(), false);
                break;
            case 1:
                getStyle().setAssociationStyle(getEdgeStyle(), true);
                break;
            case 2:
                getStyle().setAggregationStyle(getEdgeStyle(), false);
                break;
            case 3:
                getStyle().setAggregationStyle(getEdgeStyle(), true);
                break;
            case 4:
                getStyle().setCompositionStyle(getEdgeStyle(), false);
                break;
            case 5:
                getStyle().setCompositionStyle(getEdgeStyle(), true);
                break;
            case 6:
                setGeneralizationStyle();
                break;
            case 7:
                getStyle().setRealizationStyle(getEdgeStyle());
                break;
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                setDependencyStyle();
                break;
            default:
                getStyle().setAssociationStyle(getEdgeStyle(), false);   
                break;
        }
    }
    
    @Override
    public void addControllers() {
        component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationClass(this));
        component.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventSelect(this));
        component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        component.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventGroup(this));
        
        component.addMouseListener((MouseListener) controller);
        component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
    
    @Override
    public PanelClassOperation getPanelOperation() {
        return (PanelClassOperation) panel;
    }
    
    @Override
    public StyleClassAssociation getStyle() {
        return (StyleClassAssociation) style;
    }
}
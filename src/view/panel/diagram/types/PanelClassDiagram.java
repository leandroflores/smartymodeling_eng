package view.panel.diagram.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxConstants;
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
import java.awt.GridBagConstraints;
import java.awt.event.MouseListener;
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
import view.panel.diagram.PanelDiagram;
import view.panel.operation.types.PanelClassOperation;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelClassDiagram</b>.</p>
 * <p>Class responsible for defining the <b>Class Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
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
        this.controller = new ControllerPanelClassDiagram(this);
        this.setDefaultProperties();
        this.addComponents();
        this.setClick();
    }
    
    @Override
    public void addOperationsPanel() {
        this.panel = new PanelClassOperation(this);
        this.add(this.panel, this.setStartConstraint(new GridBagConstraints()));
    }
    
    @Override
    public void addElements() {
        this.addPackages();
        this.addClasses();
        this.addInterfaces();
    }
    
    /**
     * Method responsible for adding the Diagram Packages.
     */
    private void addPackages() {
        for (PackageUML package_ : this.getDiagram().getPackagesList()) {
            if (package_.getParent() == null)
                this.addPackage(this.parent, package_);
        }
    }
    
    /**
     * Method responsible for adding a Package UML.
     * @param parent Parent Package.
     * @param package_ Packagem UML.
     */
    private void addPackage(Object parent, PackageUML package_) {
        this.addStyle(package_.getStyleLabel(), package_.getStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, package_.getId(), "", package_.getPosition().x, package_.getPosition().y, package_.getSize().x, package_.getSize().y, package_.getStyleLabel());
               cell.setConnectable(false);
        this.insert(cell, package_);
            this.addPackages(package_, cell);
            this.addEntities(package_, cell);
        this.addElement(package_, cell);
    }
    
    /**
     * Method responsible for adding the Package Childs.
     * @param package_ Package UML.
     * @param parent Parent Vertex.
     */
    private void addPackages(PackageUML package_, Object parent) {
        for (PackageUML current : package_.getPackagesList())
            this.addPackage(parent, current);
    }
    
    /**
     * Method responsible for adding the Package Entities.
     * @param package_ Package UML.
     * @param parent Parent Vertex.
     */
    private void addEntities(PackageUML package_, Object parent) {
        for (Entity entity : package_.getEntitiesList()) {
            if (entity instanceof ClassUML)
                this.addClass(parent, (ClassUML) entity);
            else if (entity instanceof InterfaceUML)
                this.addInterface(parent, (InterfaceUML) entity);
        }
    }
    
    /**
     * Method responsible for adding the Diagram Classes.
     */
    private void addClasses() {
        for (ClassUML class_ : this.getDiagram().getClassesList()) {
            if (class_.getPackageUML() == null)
                this.addClass(this.parent, class_);
        }
    }
    
    /**
     * Method responsible for adding the Class UML.
     * @param parent Parent Vertex.
     * @param class_ Class UML.
     */
    private void addClass(Object parent, ClassUML class_) {
        this.addStyle(class_.getStyleLabel(), class_.getStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, class_.getId(), "", class_.getPosition().x, class_.getPosition().y, class_.getSize().x, class_.getSize().y, class_.getStyleLabel());
               cell.setConnectable(true);
        this.insert(cell, class_);
        this.addElement(class_, cell);
    }
    
    /**
     * Method responsible for adding the Diagram Interfaces.
     */
    private void addInterfaces() {
        for (InterfaceUML interface_: this.getDiagram().getInterfacesList()) {
            if (interface_.getPackageUML() == null)
                this.addInterface(this.parent, interface_);
        }
    }
    
    /**
     * Method responsible for adding the Interface UML.
     * @param parent Parent Vertex.
     * @param interface_ Interface UML.
     */
    private void addInterface(Object parent, InterfaceUML interface_) {
        this.addStyle(interface_.getStyleLabel(), interface_.getStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, interface_.getId(), "", interface_.getPosition().x, interface_.getPosition().y, interface_.getSize().x, interface_.getSize().y, interface_.getStyleLabel());
               cell.setConnectable(true);
        this.insert(cell, interface_);
        this.addElement(interface_, cell);
    }
    
    /**
     * Method responsible for inserting a Package Cell.
     * @param cell Parent Cell.
     * @param package_ Package UML.
     */
    private void insert(mxCell cell, PackageUML package_) {
        this.addStyle("packageHeader", package_.getPackageStyle());
        this.addStyle("packageName",   package_.getNameStyle());
        
        mxCell head = (mxCell) this.graph.insertVertex(cell, package_.getId() + "(name)", "",  0,  0, package_.getWidth() * 0.3,                        15, "packageHeader");
               head.setConnectable(false);
        mxCell body = (mxCell) this.graph.insertVertex(cell, package_.getId() + "(body)", "",  0, 15, package_.getWidth(),       package_.getHeight() - 15, "packageHeader");
               body.setConnectable(false);
               
               this.addStereotypeCells(body, package_);
               this.addNameCell(body, package_);

        this.addIdentifier(head, package_.getId());
        this.addIdentifier(body, package_.getId());
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param package_ Package UML.
     */
    private void addStereotypeCells(mxCell parent, PackageUML package_) {
        Integer index = 0;
        for (Stereotype stereotype : this.getDiagram().getStereotypesList(package_)) {
            this.addStyle("stereotypeStyle", stereotype.getStyle());
            mxCell cell   = (mxCell) this.graph.insertVertex(parent, "LINK#" + package_.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (index * 21) + 5, package_.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
                   cell.setTerminal(null, true);
                   cell.setId(stereotype.getId());
                   index += 1; 
            this.addIdentifier(cell, stereotype.getId());
            this.addIdentifier(cell.getId(), package_.getId());
//            this.identifiers.put(cell.getIdentifier(), package_.getIdentifier());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param package_ Package UML.
     */
    private void addNameCell(mxCell parent, PackageUML package_) {
       this.addStyle("nameStyle", package_.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, package_.getId() + "(name)", package_.getName(), 5, package_.getNamePosition(), package_.getWidth() - 10, 25, "nameStyle");
               cell.setConnectable(false);
               cell.setId(package_.getId() + "(name)");
        this.identifiers.put(cell.getId(), package_.getId());
    }
    
    /**
     * Method responsible for inserting a Entity Cell.
     * @param cell Parent Cell.
     * @param entity Entity.
     */
    private void insert(mxCell cell, Entity entity) {
        this.addStereotypeCells(cell, entity);
        this.addInterfaceStereotypeCell(cell, entity);
        this.addNameCell(cell, entity);
        this.addLineCell(cell, entity.getNamePosition() + 27, entity);
        this.addNewAttributeCell(cell, entity);
        this.addAttributesCells(cell, entity);
        this.addLineCell(cell, entity.getMethodsPosition(), entity);
        this.addNewMethodCell(cell, entity);
        this.addMethodsCells(cell, entity);
    }

    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addStereotypeCells(mxCell parent, Entity entity) {
        Integer index = 0;
        for (Stereotype stereotype : this.getDiagram().getStereotypesList(entity)) {
            this.addStyle("stereotypeStyle", stereotype.getStyle());
            mxCell cell   = (mxCell) this.graph.insertVertex(parent, "LINK#" + entity.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (index * 21) + 5, entity.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
                   cell.setId(stereotype.getId());
                   index += 1; 
            this.addIdentifier(cell, stereotype.getId());
            this.addIdentifier(cell.getId(), entity.getId());
        }
    }
    
    /**
     * Method responsible for adding the Interface Stereotype Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addInterfaceStereotypeCell(mxCell parent, Entity entity) {
        if (entity instanceof InterfaceUML) {
            this.addStyle("stereotypeStyle", entity.getStereotypeStyle());
            mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(interface)", "<<interface>>", 5, entity.getInterfacePosition(), entity.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
            this.addIdentifier(cell, entity.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNameCell(mxCell parent, Entity entity) {
        this.addStyle("nameStyle" + entity.getId(), entity.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(name)", entity.getName(), 5, entity.getNamePosition(), entity.getWidth() - 10, 25, "nameStyle" + entity.getId());
               cell.setConnectable(false);
               cell.setId(entity.getId() + "(name)");
        this.addIdentifier(cell.getId(), entity.getId());
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addLineCell(mxCell parent, Integer y, Entity entity) {
        this.addStyle("lineStyle", entity.getLineStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, null, "", 0, y, entity.getWidth(), 1, "lineStyle");
               cell.setConnectable(false);
        this.addIdentifier(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Add Attribute Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNewAttributeCell(mxCell parent, Entity entity) {
        this.addStyle("newAttributeStyle", entity.getNewAttributeStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(newAttribute)", "", 5, entity.getAttributesPosition(), 5, 5, "newAttributeStyle");
               cell.setConnectable(false);
        this.addIdentifier(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Attributes Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addAttributesCells(mxCell parent, Entity entity) {
        Integer index = 0;
        for (AttributeUML attribute : entity.getAttributesList()) {
            this.addStyle(attribute.getStyleLabel(), attribute.getStyle());
            mxCell cell   = (mxCell) this.graph.insertVertex(parent, attribute.getId(), attribute.getCompleteSignature(), 5, entity.getAttributesPosition() + (index * 16) + 6, entity.getWidth() - 10, 15, attribute.getStyleLabel());
                   cell.setConnectable(false);
                   cell.setId(attribute.getId());
                   index += 1; 
            this.addElement(attribute, cell);
            this.addIdentifier(cell.getId(), attribute.getId());
        }
    }
    
    /**
     * Method responsible for adding the New Method Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNewMethodCell(mxCell parent, Entity entity) {
        this.addStyle("newMethodStyle", entity.getNewMethodStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(newMethod)", "", 5, entity.getMethodsPosition() + 5, 10, 5, "newMethodStyle");
               cell.setConnectable(false);
        this.addIdentifier(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Methods Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addMethodsCells(mxCell parent, Entity entity) {
        Integer index = 0;
        for (MethodUML method : entity.getMethodsList()) {
            this.addStyle(method.getStyleLabel(), method.getStyle());
            mxCell cell   = (mxCell) this.graph.insertVertex(parent, method.getId(), method.getCompleteSignature(), 5, entity.getMethodsPosition() + 10 + (index * 16), entity.getWidth() - 10, 15, method.getStyleLabel());
                   cell.setConnectable(false);
                   cell.setId(method.getId());
                   index += 1; 
            this.addElement(method, cell);
            this.addIdentifier(cell.getId(), method.getId());
        }
    }
    
    @Override
    public void addAssociations() {
        for (Association association : this.getDiagram().getAssociationsList())
            this.addAssociation(association);
    }
    
    /**
     * Method responsible for adding the Association.
     * @param association Association.
     */
    private void addAssociation(Association association) {
        this.addStyle(association.getStyleLabel(), association.getStyle());
        mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, association.getId(), association.getTitle(), this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
        mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                   geometry.setPoints(association.getPoints());
        this.getModel().setGeometry(edge, geometry);
        this.addAssociation(association, edge);
        this.addCardinality(association);
    }
    
    /**
     * Method responsible for adding the Cardinality.
     * @param association Association.
     */
    private void addCardinality(Association association) {
        if (association instanceof AssociationUML) {
            this.addStyle(((AssociationUML) association).getCardinalityLabel(), 
                          ((AssociationUML) association).getCardinalityStyle());
            this.addSourceLabel((AssociationUML) association);
            this.addTargetLabel((AssociationUML) association);
        }
    }
    
    /**
     * Method responsible for adding the Source Label.
     * @param association Association UML.
     */
    private void addSourceLabel(AssociationUML association) {
        if (!association.isDirection()) {
            mxCell source = (mxCell) this.graph.insertVertex(this.parent, association.getId() + "(source)", association.getSourceLabel(), association.getSourceX(), association.getSourceY(), 30, 20, association.getCardinalityLabel());
                   source.setConnectable(false);
            this.addIdentifier(source, association.getId() + "(source)");
        }
    }
    
    /**
     * Method responsible for adding the Target Label.
     * @param association Association UML. 
     */
    public void addTargetLabel(AssociationUML association) {
        mxCell target = (mxCell) this.graph.insertVertex(this.parent, association.getId() + "(target)", association.getTargetLabel(), association.getTargetX(), association.getTargetY(), 30, 20, association.getCardinalityLabel());
               target.setConnectable(false);
        this.addIdentifier(target, association.getId() + "(target)");
    }
    
    @Override
    public void setStyle() {
        switch (this.getType()) {
            case 0:
                this.setAssociationStyle(false);
                break;
            case 1:
                this.setAssociationStyle(true);
                break;
            case 2:
                this.setAggregationStyle(false);
                break;
            case 3:
                this.setAggregationStyle(true);
                break;
            case 4:
                this.setCompositionStyle(false);
                break;
            case 5:
                this.setCompositionStyle(true);
                break;
            case 6:
                this.setGeneralizationStyle();
                break;
            case 7:
                this.setRealizationStyle();
                break;
            case 8:
                this.setDependencyStyle();
                break;
            case  9:
            case 10:
                this.setRealizationStyle();
                break;
            default:
                this.setAssociationStyle(false);    
                break;
        }
    }
    
    /**
     * Method responsible for setting the Association Style.
     * @param direction Direction Flag.
     */
    private void setAssociationStyle(boolean direction) {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Aggregation Style.
     * @param direction Direction Flag.
     */
    private void setAggregationStyle(boolean direction) {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,    "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTSIZE, "15");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTFILL, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_DIAMOND);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Composition Style.
     * @param direction Direction Flag.
     */
    private void setCompositionStyle(boolean direction) {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,    "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTFILL, "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTSIZE, "15");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_DIAMOND);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    direction ? mxConstants.ARROW_OPEN : mxConstants.ARROW_SPACING);
    }
    
    /**
     * Method responsible for setting the Realization Style.
     */
    private void setRealizationStyle() {
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_DASHED,    "1");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDSIZE,   "10");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTFILL, "0");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_FONTCOLOR,   "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STROKECOLOR, "#000000");
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_STARTARROW,  mxConstants.ARROW_SPACING);
        this.getDefaultEdgeStyle().put(mxConstants.STYLE_ENDARROW,    mxConstants.ARROW_BLOCK);
    }
    
    @Override
    public void addControllers() {
        this.component.getConnectionHandler().addListener(mxEvent.CONNECT, new ControllerEventAssociationClass(this));
        this.component.getGraph().getSelectionModel().addListener(mxEvent.CHANGE, new ControllerEventSelect(this));
        this.component.addListener(mxEvent.START_EDITING, new ControllerEventEdit(this));
        this.component.addListener(mxEvent.LABEL_CHANGED, new ControllerEventChange(this));
        this.component.getGraph().addListener(mxEvent.CELLS_RESIZED, new ControllerEventResize(this));
        this.component.getGraph().addListener(mxEvent.CELLS_MOVED, new ControllerEventMove(this));
        this.component.getGraph().addListener(mxEvent.MOVE_CELLS, new ControllerEventGroup(this));
        
        this.component.addMouseListener((MouseListener) this.controller);
        this.component.getGraphControl().addMouseListener(new ControllerEventFocus(this));
        this.component.getGraphControl().addMouseListener(new ControllerEventPoints(this));
    }
    
    @Override
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
    
    @Override
    public PanelClassOperation getPanelOperation() {
        return (PanelClassOperation) this.panel;
    }
}
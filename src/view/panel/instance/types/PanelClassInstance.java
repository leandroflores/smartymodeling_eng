package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.instance.ControllerPanelInstance;
import java.util.List;
import javax.swing.BoxLayout;
import model.structural.base.association.Association;
import model.structural.base.product.Artefact;
import model.structural.base.product.Instance;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.instance.PanelInstance;
import view.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelClassInstance</b>.</p>
 * <p>Class responsible for defining the <b>Class Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  07/10/2019
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    controller.view.panel.instance.event.ControllerEventMove
 * @see    controller.view.panel.instance.event.ControllerEventResize
 * @see    model.structural.diagram.ClassDiagram
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelClassInstance extends PanelInstance {
    private final ClassDiagram diagram;

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Instance.
     * @param diagram Class Diagram.
     */
    public PanelClassInstance(ViewMenu view, Instance instance, ClassDiagram diagram) {
        super(view, instance);
        this.diagram    = diagram;
        this.controller = new ControllerPanelInstance(this);
        this.addComponents();
    }
    
    @Override
    public void addComponents() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.addInstancePanel();
        this.addControllers();
    }
    
    @Override
    public void addElements() {
        this.addPackages();
        this.addClasses();
        this.addInterfaces();
    }
    
    /**
     * Method responsible for adding the Instance Packages.
     */
    private void addPackages() {
        for (Artefact artefact : this.instance.getArtefactsList()) {
            if (artefact.getElement() instanceof PackageUML) {
                if (((PackageUML) artefact.getElement()).getParent() == null)
                    this.addPackage(this.parent, artefact);
            }
        }
    }
    
    /**
     * Method responsible for adding a Package of a Parent.
     * @param parent Object Parent.
     * @param artefact Artefact.
     */
    private void addPackage(Object parent, Artefact artefact) {
        this.graph.getStylesheet().putCellStyle(artefact.getStyleLabel(), artefact.getStyle());
        mxCell   vertex  = (mxCell) this.graph.insertVertex(parent, artefact.getId(), "", artefact.getPosition().x, artefact.getPosition().y, artefact.getSize().x, artefact.getSize().y, artefact.getStyleLabel());
                 vertex.setConnectable(false);
        this.insert(vertex, artefact);
            this.addPackages(artefact, vertex);
            this.addEntities(artefact, vertex);
        this.identifiers.put(vertex, artefact.getId());
        this.objects.put(artefact.getId(), vertex);
    }
    
    /**
     * Method responsible for adding the Package Childs.
     * @param artefact Artefact.
     * @param parent Parent Vertex.
     */
    private void addPackages(Artefact artefact, Object parent) {
        for (PackageUML current : ((PackageUML) artefact.getElement()).getPackagesList())
            this.addPackage(parent, artefact);
    }
    
    /**
     * Method responsible for adding the Package Entities.
     * @param artefact Artefact.
     * @param parent Parent Vertex.
     */
    private void addEntities(Artefact artefact, Object parent) {
        for (Entity entity : ((PackageUML) artefact.getElement()).getEntitiesList()) {
            Artefact entityArtefact = this.instance.getArtefact(entity);
            if (entityArtefact != null) {
                if (entity instanceof ClassUML)
                    this.addClass(parent, entityArtefact);
                else if (entity instanceof InterfaceUML)
                    this.addInterface(parent, entityArtefact);
            }
        }
    }
    
    /**
     * Method responsible for adding the Instance Classes.
     */
    private void addClasses() {
        for (Artefact artefact : this.instance.getArtefactsList()) {
            if (artefact.getElement() instanceof ClassUML) {                
                if (((ClassUML) artefact.getElement()).getPackageUML() == null)
                    this.addClass(this.parent, artefact);
            }
        }
    }
    
    /**
     * Method responsible for adding the Class UML.
     * @param parent Parent Vertex.
     * @param artefact Artefact.
     */
    private void addClass(Object parent, Artefact artefact) {
        this.graph.getStylesheet().putCellStyle(artefact.getStyleLabel(), artefact.getStyle());
        mxCell vertex = (mxCell) this.graph.insertVertex(parent, artefact.getId(), "", artefact.getPosition().x, artefact.getPosition().y, artefact.getSize().x, artefact.getSize().y, artefact.getStyleLabel());
               vertex.setConnectable(false);
        this.insert(vertex, (Entity) artefact.getElement());
        this.identifiers.put(vertex, artefact.getId());
        this.objects.put(artefact.getId(), vertex);
    }
    
    /**
     * Method responsible for adding the Instance Interfaces.
     */
    private void addInterfaces() {
        for (Artefact artefact : this.instance.getArtefactsList()) {
            if (artefact.getElement() instanceof InterfaceUML) {
                if (((InterfaceUML) artefact.getElement()).getPackageUML() == null)
                    this.addInterface(this.parent, artefact);
            }
        }
    }
    
    /**
     * Method responsible for adding the Interface UML.
     * @param parent Parent Vertex.
     * @param artefact Artefact.
     */
    private void addInterface(Object parent, Artefact artefact) {
        this.graph.getStylesheet().putCellStyle(artefact.getStyleLabel(), artefact.getStyle());
        mxCell vertex = (mxCell) this.graph.insertVertex(parent, artefact.getId(), "", artefact.getPosition().x, artefact.getPosition().y, artefact.getSize().x, artefact.getSize().y, artefact.getStyleLabel());
               vertex.setConnectable(false);
        this.insert(vertex, (Entity) artefact.getElement());
        this.identifiers.put(vertex, artefact.getId());
        this.objects.put(artefact.getId(), vertex);
    }
    
    /**
     * Method responsible for inserting a Package Cell.
     * @param vertex Parent Vertex.
     * @param artefact Artefact.
     */
    private void insert(mxCell vertex, Artefact artefact) {
        PackageUML packageUML = (PackageUML) artefact.getElement();
        this.graph.getStylesheet().putCellStyle("packageHeader", packageUML.getPackageStyle());
        this.graph.getStylesheet().putCellStyle("packageName",   packageUML.getNameStyle());
        
        mxCell head = (mxCell) this.graph.insertVertex(vertex, artefact.getId() + "(name)",                    "",  0,  0, packageUML.getWidth() * 0.3,                          15, "packageHeader");
        mxCell body = (mxCell) this.graph.insertVertex(vertex, artefact.getId() + "(body)",                    "",  0, 15, packageUML.getWidth(),       packageUML.getHeight() - 15, "packageHeader");
                    this.addNameCell(body, packageUML);
               head.setConnectable(false);
               body.setConnectable(false);
        
        this.identifiers.put(head, packageUML.getId());
        this.identifiers.put(body, packageUML.getId());
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param packageUML Package UML.
     */
    private void addNameCell(mxCell parent, PackageUML packageUML) {
        this.graph.getStylesheet().putCellStyle("nameStyle", packageUML.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, packageUML.getId() + "(name)", packageUML.getName(), 5, packageUML.getNamePosition(), packageUML.getWidth() - 10, 25, "nameStyle");
               cell.setConnectable(false);
               cell.setId(packageUML.getId() + "(name)");
        this.identifiers.put(cell.getId(), packageUML.getId());
    }
    
    /**
     * Method responsible for inserting a Entity Cell.
     * @param vertex Parent Vertex.
     * @param entity Entity.
     */
    private void insert(mxCell vertex, Entity entity) {
        this.addInterfaceStereotypeCell(vertex, entity);
        this.addNameCell(vertex, entity);
        this.addLineCell(vertex, entity.getNamePosition() + 27, entity);
        this.addAttributesCells(vertex, entity);
        this.addLineCell(vertex, entity.getMethodsPosition(), entity);
        this.addMethodsCells(vertex, entity);
    }

    /**
     * Method responsible for adding the Interface Stereotype Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addInterfaceStereotypeCell(mxCell parent, Entity entity) {
        if (entity instanceof InterfaceUML) {
            this.graph.getStylesheet().putCellStyle("stereotypeStyle",  entity.getStereotypeStyle());
            mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(interface)", "<<interface>>", 5, entity.getInterfacePosition(), entity.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
            this.identifiers.put(cell, entity.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addNameCell(mxCell parent, Entity entity) {
        this.graph.getStylesheet().putCellStyle("nameStyle", entity.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, entity.getId() + "(name)", entity.getName(), 5, entity.getNamePosition(), entity.getWidth() - 10, 25, "nameStyle");
               cell.setConnectable(false);
               cell.setId(entity.getId() + "(name)");
        this.identifiers.put(cell.getId(), entity.getId());
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addLineCell(mxCell parent, Integer y, Entity entity) {
        this.graph.getStylesheet().putCellStyle("lineStyle", entity.getLineStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, null, "", 0, y, entity.getWidth(), 1, "lineStyle");
               cell.setConnectable(false);
        this.identifiers.put(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Attributes Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addAttributesCells(mxCell parent, Entity entity) {
        List<AttributeUML>  attributes = entity.getAttributesList();
        for (int i = 0; i < attributes.size(); i++) {
            AttributeUML attribute = attributes.get(i);
            this.graph.getStylesheet().putCellStyle(attribute.getStyleLabel(), attribute.getStyle());
            mxCell       cell      = (mxCell) this.graph.insertVertex(parent, attribute.getId(), attribute.getCompleteSignature(), 5, entity.getAttributesPosition() + (i * 16) + 6, entity.getWidth() - 10, 15, attribute.getStyleLabel());
                         cell.setConnectable(false);
                         cell.setId(attribute.getId());
            this.identifiers.put(cell,         attribute.getId());
            this.identifiers.put(cell.getId(), attribute.getId());
        }
    }
    
    /**
     * Method responsible for adding the Methods Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addMethodsCells(mxCell parent, Entity entity) {
        List<MethodUML>     methods = entity.getMethodsList();
        for (int i = 0; i < methods.size(); i++) {
            MethodUML method = methods.get(i);
            this.graph.getStylesheet().putCellStyle(method.getStyleLabel(), method.getStyle());
            mxCell    cell   = (mxCell) this.graph.insertVertex(parent, method.getId(), method.getCompleteSignature(), 5, entity.getMethodsPosition() + 10 + (i * 16), entity.getWidth() - 10, 15, method.getStyleLabel());
                      cell.setConnectable(false);
                      cell.setId(method.getId());
            this.identifiers.put(cell,         method.getId());
            this.identifiers.put(cell.getId(), method.getId());
        }
    }
    
    @Override
    public void addAssociations() {
        List<Association>   associations = this.diagram.getAssociationsList();
        for (int i = 0; i < associations.size(); i++) {
            Association association = associations.get(i);
            this.graph.getStylesheet().putCellStyle(association.getStyleLabel(), association.getStyle());
            this.addNormalAssociation(association);
            if (association instanceof AssociationUML)
                this.addAssociationUML((AssociationUML) association); 
        }
    }
    
    /**
     * Method responsible for adding the Normal Association.
     * @param association Association.
     */
    private void addNormalAssociation(Association association) {
        mxCell edge = (mxCell) this.graph.insertEdge(this.parent, association.getId(), association.getTitle(), this.objects.get(association.getSource().getId()), this.objects.get(association.getTarget().getId()), association.getStyleLabel());
        this.identifiers.put(edge, association.getId());
    }
    
    /**
     * Method responsible for adding the Association UML.
     * @param associationUML Association UML.
     */
    private void addAssociationUML(AssociationUML associationUML) {
        this.graph.getStylesheet().putCellStyle(associationUML.getCardinalityLabel(), associationUML.getCardinalityStyle());
        mxCell source = (mxCell) this.graph.insertVertex(this.parent, associationUML.getId() + "(source)", associationUML.getSourceLabel(), associationUML.getSourceX(), associationUML.getSourceY(), 30, 20, associationUML.getCardinalityLabel());
               source.setConnectable(false);
        mxCell target = (mxCell) this.graph.insertVertex(this.parent, associationUML.getId() + "(target)", associationUML.getTargetLabel(), associationUML.getTargetX(), associationUML.getTargetY(), 30, 20, associationUML.getCardinalityLabel());
               target.setConnectable(false);
        this.identifiers.put(source, associationUML.getId() + "(source)");
        this.identifiers.put(target, associationUML.getId() + "(target)");
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
}
package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import controller.view.panel.instance.ControllerPanelInstance;
import model.structural.base.Element;
import model.structural.base.association.Association;
import model.structural.base.product.Artifact;
import model.structural.base.product.Instance;
import model.structural.base.product.Relationship;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import view.panel.instance.PanelInstance;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelClassInstance</b>.</p>
 * <p>Class responsible for defining the <b>Class Instance Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-10-07
 * @see    controller.view.panel.instance.ControllerPanelInstance
 * @see    model.structural.base.product.Instance
 * @see    view.panel.instance.PanelInstance
 */
public final class PanelClassInstance extends PanelInstance {

    /**
     * Default constructor method of Class.
     * @param view View Menu.
     * @param instance Class Instance.
     */
    public PanelClassInstance(ViewMenu view, Instance instance) {
        super(view, instance);
        this.controller = new ControllerPanelInstance(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void addArtifact(Artifact artifact, Element element) {
        if (element instanceof PackageUML)
            this.addArtifact(artifact, (PackageUML) element);
        else if (element instanceof ClassUML)
            this.addArtifact(artifact, (ClassUML) element);
        else if (element instanceof InterfaceUML)
            this.addArtifact(artifact, (InterfaceUML) element);
    }
    
    /**
     * Method responsible for adding the Package UML of a Artifact.
     * @param artifact Artifact.
     * @param package_ Package UML.
     */
    protected void addArtifact(Artifact artifact, PackageUML package_) {
        if (package_.getParent() == null)
            this.addPackage(this.parent, artifact);
    }
    
    /**
     * Method responsible for adding a Package of a Parent.
     * @param parent Object Parent.
     * @param artifact Artifact.
     */
    private void addPackage(Object parent, Artifact artifact) {
        this.addStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell cell  = (mxCell) this.graph.insertVertex(parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        this.insert(cell, artifact);
        this.addPackages((PackageUML) artifact.getElement(), cell);
        this.addEntities((PackageUML) artifact.getElement(), cell);
        this.addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for adding the Package Childs.
     * @param package_ Artifact.
     * @param parent Parent Vertex.
     */
    private void addPackages(PackageUML package_, Object parent) {
        for (PackageUML current : package_.getPackagesList()) {
            Artifact subpackage = this.instance.getArtifact(current);
            if (subpackage != null)
                this.addPackage(parent, subpackage);
        }
    }
    
    /**
     * Method responsible for adding the Package Entities.
     * @param package_ Package UML.
     * @param parent Parent Vertex.
     */
    private void addEntities(PackageUML package_, Object parent) {
        for (Entity entity : package_.getEntitiesList()) {
            Artifact artifact = this.getInstance().getArtifact(entity);
            if (artifact != null) {
                if (entity instanceof ClassUML)
                    this.addClass(parent, artifact);
                else if (entity instanceof InterfaceUML)
                    this.addInterface(parent, artifact);
            }
        }
    }
    
    /**
     * Method responsible for adding the Class UML of a Artifact.
     * @param artifact Artifact.
     * @param class_ Class UML.
     */
    protected void addArtifact(Artifact artifact, ClassUML class_) {
        if (class_.getPackageUML() == null)
            this.addClass(this.parent, artifact);
    }
    
    /**
     * Method responsible for adding the Class UML.
     * @param parent Parent Vertex.
     * @param artifact Artifact.
     */
    private void addClass(Object parent, Artifact artifact) {
        this.addStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        this.insert(cell, artifact, (Entity) artifact.getElement());
        this.addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for adding the Interface UML of a Artifact.
     * @param artifact Artifact.
     * @param interface_ Interface UML.
     */
    protected void addArtifact(Artifact artifact, InterfaceUML interface_) {
        if (interface_.getPackageUML() == null)
            this.addInterface(this.parent, artifact);
    }
    
    /**
     * Method responsible for adding the Interface UML.
     * @param parent Parent Vertex.
     * @param artifact Artifact.
     */
    private void addInterface(Object parent, Artifact artifact) {
        this.addStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               cell.setConnectable(false);
        this.insert(cell, artifact, (Entity) artifact.getElement());
        this.addArtifactCell(artifact, cell);
    }
    
    /**
     * Method responsible for inserting a Package Cell.
     * @param vertex Parent Vertex.
     * @param artifact Artifact.
     */
    private void insert(mxCell vertex, Artifact artifact) {
        PackageUML package_ = (PackageUML) artifact.getElement();
        this.addStyle("packageHeader", package_.getPackageStyle());
        this.addStyle("packageName",   package_.getNameStyle());
        
        mxCell head = (mxCell) this.graph.insertVertex(vertex, artifact.getId() + "(name)", "",  0,  0, artifact.getWidth() * 0.3,                        15, "packageHeader");
        mxCell body = (mxCell) this.graph.insertVertex(vertex, artifact.getId() + "(body)", "",  0, 15, artifact.getWidth(),       artifact.getHeight() - 15, "packageHeader");
                    this.addNameCell(body, package_);
               head.setConnectable(false);
               body.setConnectable(false);
        this.addIdentifier(head, artifact.getId());
        this.addIdentifier(body, artifact.getId());
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param packageUML Package UML.
     */
    private void addNameCell(mxCell parent, PackageUML packageUML) {
        this.addStyle("nameStyle" + packageUML.getId(), packageUML.getNameStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, packageUML.getId() + "(name)", packageUML.getName(), 5, packageUML.getNamePosition(), packageUML.getWidth() - 10, 25, "nameStyle" + packageUML.getId());
               cell.setConnectable(false);
        this.addIdentifier(cell.getId(), packageUML.getId());
    }
    
    /**
     * Method responsible for inserting a Entity Cell.
     * @param vertex Parent Vertex.
     * @param artifact Artifact.
     * @param entity Entity.
     */
    private void insert(mxCell vertex, Artifact artifact, Entity entity) {
        this.addInterfaceStereotypeCell(vertex, artifact, entity);
        this.addNameCell(vertex, artifact, entity);
        this.addLineCell(vertex, artifact, entity, this.getFirstLinePosition(entity));
        this.addAttributesCells(vertex, artifact, entity);
        this.addLineCell(vertex, artifact, entity, this.getSecondLinePosition(entity));
        this.addMethodsCells(vertex, artifact, entity);
    }
    
    /**
     * Method responsible for returning the First Line Position.
     * @param  entity Entity.
     * @return First Line Position.
     */
    private Integer getFirstLinePosition(Entity entity) {
        return entity.isInterface() ? 55 : 35;
    }
    
    /**
     * Method responsible for returning the Second Line Position.
     * @param  entity Entity.
     * @return Second Line Position.
     */
    private Integer getSecondLinePosition(Entity entity) {
        return this.getFirstLinePosition(entity) + 5
             + entity.getAttributesList().size() * 16 
             + 5;
    }

    /**
     * Method responsible for adding the Interface Stereotype Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     * @param entity Entity.
     */
    private void addInterfaceStereotypeCell(mxCell parent, Artifact artifact, Entity entity) {
        if (entity.isInterface()) {
            this.addStyle("stereotypeStyle", entity.getStereotypeStyle());
            mxCell cell = (mxCell) this.graph.insertVertex(parent, artifact.getId(), "<<interface>>", 5, 5, artifact.getWidth() - 10, 20, "stereotypeStyle");
                   cell.setConnectable(false);
            this.addIdentifier(cell, artifact.getId());
        }
    }
    
    /**
     * Method responsible for adding the Name Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     * @param entity Entity.
     */
    private void addNameCell(mxCell parent, Artifact artifact, Entity entity) {
        this.addStyle("nameStyle" + artifact.getId(), this.getStyle(entity.getNameStyle()));
        mxCell cell = (mxCell) this.graph.insertVertex(parent, artifact.getId() + "(name)", entity.getName(), 5, entity.isInterface() ? 25 : 5, artifact.getWidth() - 10, 25, "nameStyle" + artifact.getId());
               cell.setConnectable(false);
        this.addIdentifier(cell.getId(), entity.getId());
    }
    
    /**
     * Method responsible for adding the Line Cell.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     * @param entity Entity.
     * @param position Position.
     */
    private void addLineCell(mxCell parent, Artifact artifact, Entity entity, Integer position) {
        this.addStyle("lineStyle", entity.getLineStyle());
        mxCell cell = (mxCell) this.graph.insertVertex(parent, null, "", 0, position, artifact.getWidth(), 1, "lineStyle");
               cell.setConnectable(false);
        this.addIdentifier(cell, entity.getId());
    }
    
    /**
     * Method responsible for adding the Attributes Cells.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     * @param entity Entity.
     */
    private void addAttributesCells(mxCell parent, Artifact artifact, Entity entity) {
        Integer index = 0;
        for (AttributeUML attribute : entity.getAttributesList()) {
            this.addStyle(attribute.getStyleLabel(), attribute.getStyle());
            Integer position = this.getFirstLinePosition(entity) + (index * 16) + 5;
            mxCell  vertex   = (mxCell) this.graph.insertVertex(parent, attribute.getId(), attribute.getCompleteSignature(), 5, position, artifact.getWidth() - 10, 15, attribute.getStyleLabel());
                    vertex.setConnectable(false);
                    index   += 1; 
        }
    }
    
    /**
     * Method responsible for adding the Methods Cells.
     * @param parent Parent Cell.
     * @param artifact Artifact.
     * @param entity Entity.
     */
    private void addMethodsCells(mxCell parent, Artifact artifact, Entity entity) {
        Integer index = 0;
        for (MethodUML method : entity.getMethodsList()) {
            this.addStyle(method.getStyleLabel(), method.getStyle());
            Integer position = this.getSecondLinePosition(entity) + (index * 16) + 5;
            mxCell  vertex   = (mxCell) this.graph.insertVertex(parent, method.getId(), method.getCompleteSignature(), 5, position, artifact.getWidth() - 10, 15, method.getStyleLabel());
                    vertex.setConnectable(false);
                    index   += 1; 
        }
    }
    
    @Override
    protected void addRelationship(Relationship relationship, Association association) {
        super.addRelationship(relationship, association);
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
    public ClassDiagram getDiagram() {
        return (ClassDiagram) this.instance.getDiagram();
    }
}
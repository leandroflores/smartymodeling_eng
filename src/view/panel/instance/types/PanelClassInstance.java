package view.panel.instance.types;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import controller.view.panel.instance.ControllerPanelInstance;
import java.util.List;
import javax.swing.BoxLayout;
import model.structural.base.Stereotype;
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
    public void addArtifacts() {
        this.addPackages();
        this.addClasses();
        this.addInterfaces();
    }
    
    /**
     * Method responsible for adding the Instance Packages.
     */
    private void addPackages() {
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.getElement() instanceof PackageUML) {
                if (((PackageUML) artifact.getElement()).getParent() == null)
                    this.addPackage(this.parent, artifact);
            }
        }
    }
    
    /**
     * Method responsible for adding a Package of a Parent.
     * @param parent Object Parent.
     * @param artifact Artifact.
     */
    private void addPackage(Object parent, Artifact artifact) {
        this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell   vertex  = (mxCell) this.graph.insertVertex(parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
                 vertex.setConnectable(false);
        this.insert(vertex, artifact);
            this.addPackages(artifact, vertex);
            this.addEntities(artifact, vertex);
        this.identifiers.put(vertex, artifact.getId());
        this.objects.put(artifact.getId(), vertex);
    }
    
    /**
     * Method responsible for adding the Package Childs.
     * @param artifact Artifact.
     * @param parent Parent Vertex.
     */
    private void addPackages(Artifact artifact, Object parent) {
        for (PackageUML current : ((PackageUML) artifact.getElement()).getPackagesList()) {
            if (this.instance.contains(current))
                this.addPackage(parent, artifact);
        }
    }
    
    /**
     * Method responsible for adding the Package Entities.
     * @param artifact Artifact.
     * @param parent Parent Vertex.
     */
    private void addEntities(Artifact artifact, Object parent) {
        for (Entity entity : ((PackageUML) artifact.getElement()).getEntitiesList()) {
            Artifact current = this.instance.getArtifact(entity);
            if ((current != null) && (this.instance.contains(entity))) {
                if (entity instanceof ClassUML)
                    this.addClass(parent, current);
                else if (entity instanceof InterfaceUML)
                    this.addInterface(parent, current);
            }
        }
    }
    
    /**
     * Method responsible for adding the Instance Classes.
     */
    private void addClasses() {
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.getElement() instanceof ClassUML) {                
                if (((ClassUML) artifact.getElement()).getPackageUML() == null)
                    this.addClass(this.parent, artifact);
            }
        }
    }
    
    /**
     * Method responsible for adding the Class UML.
     * @param parent Parent Vertex.
     * @param artifact Artifact.
     */
    private void addClass(Object parent, Artifact artifact) {
        this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell vertex = (mxCell) this.graph.insertVertex(parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               vertex.setConnectable(false);
        this.insert(vertex, (Entity) artifact.getElement());
        this.identifiers.put(vertex, artifact.getId());
        this.objects.put(artifact.getId(), vertex);
    }
    
    /**
     * Method responsible for adding the Instance Interfaces.
     */
    private void addInterfaces() {
        for (Artifact artifact : this.instance.getArtifactsList()) {
            if (artifact.getElement() instanceof InterfaceUML) {
                if (((InterfaceUML) artifact.getElement()).getPackageUML() == null)
                    this.addInterface(this.parent, artifact);
            }
        }
    }
    
    /**
     * Method responsible for adding the Interface UML.
     * @param parent Parent Vertex.
     * @param artifact Artifact.
     */
    private void addInterface(Object parent, Artifact artifact) {
        this.graph.getStylesheet().putCellStyle(artifact.getStyleLabel(), artifact.getStyle());
        mxCell vertex = (mxCell) this.graph.insertVertex(parent, artifact.getId(), "", artifact.getPosition().x, artifact.getPosition().y, artifact.getSize().x, artifact.getSize().y, artifact.getStyleLabel());
               vertex.setConnectable(false);
        this.insert(vertex, (Entity) artifact.getElement());
        this.identifiers.put(vertex, artifact.getId());
        this.objects.put(artifact.getId(), vertex);
    }
    
    /**
     * Method responsible for inserting a Package Cell.
     * @param vertex Parent Vertex.
     * @param artifact Artifact.
     */
    private void insert(mxCell vertex, Artifact artifact) {
        PackageUML packageUML = (PackageUML) artifact.getElement();
        this.graph.getStylesheet().putCellStyle("packageHeader", packageUML.getPackageStyle());
        this.graph.getStylesheet().putCellStyle("packageName",   packageUML.getNameStyle());
        
        mxCell head = (mxCell) this.graph.insertVertex(vertex, artifact.getId() + "(name)",                    "",  0,  0, packageUML.getWidth() * 0.3,                          15, "packageHeader");
        mxCell body = (mxCell) this.graph.insertVertex(vertex, artifact.getId() + "(body)",                    "",  0, 15, packageUML.getWidth(),       packageUML.getHeight() - 15, "packageHeader");
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
        this.addStereotypeCells(vertex, entity);
        this.addInterfaceStereotypeCell(vertex, entity);
        this.addNameCell(vertex, entity);
        this.addLineCell(vertex, entity.getNamePosition() + 27, entity);
        this.addAttributesCells(vertex, entity);
        this.addLineCell(vertex, entity.getMethodsPosition(), entity);
        this.addMethodsCells(vertex, entity);
    }
    
    /**
     * Method responsible for adding the Stereotype Cells.
     * @param parent Parent Cell.
     * @param entity Entity.
     */
    private void addStereotypeCells(mxCell parent, Entity entity) {
        List<Stereotype>    stereotypes = this.diagram.getStereotypesList(entity);
        for (int i = 0; i < stereotypes.size(); i++) {
            Stereotype stereotype = stereotypes.get(i);
            if (stereotype.isPrimitive() == false) {
                this.graph.getStylesheet().putCellStyle("stereotypeStyle", stereotype.getStyle()); 
                mxCell     cell       = (mxCell) this.graph.insertVertex(parent, "LINK#" + entity.getId() + "-" + stereotype.getId(), stereotype.toString(), 5, (i * 21) + 5, entity.getWidth() - 10, 20, "stereotypeStyle");
                           cell.setConnectable(false);
                           cell.setId(stereotype.getId());
                this.identifiers.put(cell,         stereotype.getId());
                this.identifiers.put(cell.getId(), stereotype.getId());
            }
        }
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
        this.graph.getStylesheet().putCellStyle("nameStyle", this.getStyle(entity.getNameStyle()));
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
            this.graph.getStylesheet().putCellStyle(attribute.getStyleLabel(), this.getStyle(attribute.getStyle()));
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
            this.graph.getStylesheet().putCellStyle(method.getStyleLabel(), this.getStyle(method.getStyle()));
            mxCell    cell   = (mxCell) this.graph.insertVertex(parent, method.getId(), method.getCompleteSignature(), 5, entity.getMethodsPosition() + 10 + (i * 16), entity.getWidth() - 10, 15, method.getStyleLabel());
                      cell.setConnectable(false);
                      cell.setId(method.getId());
            this.identifiers.put(cell,         method.getId());
            this.identifiers.put(cell.getId(), method.getId());
        }
    }
    
    @Override
    public void addRelationships() {
        for (Relationship relationship : this.instance.getRelationshipsList()) {
            this.graph.getStylesheet().putCellStyle(relationship.getStyleLabel(), relationship.getStyle());
            this.addNormalRelationship(relationship);
            if (relationship.getAssociation() instanceof AssociationUML)
                this.addDirectedRelationship(relationship); 
        }
    }
    
    /**
     * Method responsible for adding the Normal Association.
     * @param relationship Association.
     */
    private void addNormalRelationship(Relationship relationship) {
        mxCell     edge     = (mxCell) this.graph.insertEdge(this.parent, relationship.getId(), relationship.getTitle(), this.objects.get(this.getId(relationship.getAssociation().getSource())), this.objects.get(this.getId(relationship.getAssociation().getTarget())), relationship.getStyleLabel());
        mxGeometry geometry = ((mxGraphModel) (this.graph.getModel())).getGeometry(edge);
                   geometry.setPoints(relationship.getPoints());
        ((mxGraphModel) (this.graph.getModel())).setGeometry(edge, geometry);
        this.identifiers.put(edge, relationship.getId());
        this.objects.put(relationship.getId(), edge);
    }
    
    /**
     * Method responsible for adding the Directed Relationship.
     * @param relationship Relationship.
     */
    private void addDirectedRelationship(Relationship relationship) {
        AssociationUML associationUML = (AssociationUML) relationship.getAssociation();
        this.graph.getStylesheet().putCellStyle(associationUML.getCardinalityLabel(), this.getStyle(associationUML.getCardinalityStyle()));
        mxCell source = (mxCell) this.graph.insertVertex(this.parent, relationship.getId() + "(source)", associationUML.getSourceLabel(), relationship.getSourceX(), relationship.getSourceY(), 30, 20, associationUML.getCardinalityLabel());
               source.setConnectable(false);
        mxCell target = (mxCell) this.graph.insertVertex(this.parent, relationship.getId() + "(target)", associationUML.getTargetLabel(), relationship.getTargetX(), relationship.getTargetY(), 30, 20, associationUML.getCardinalityLabel());
               target.setConnectable(false);
        this.identifiers.put(source, relationship.getId() + "(source)");
        this.identifiers.put(target, relationship.getId() + "(target)");
    }
    
    /**
     * Method responsible for returning the Class Diagram.
     * @return Class Diagram.
     */
    public ClassDiagram getDiagram() {
        return this.diagram;
    }
}
package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.classes.base.association.RealizationUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.classes.base.TypeUML;

/**
 * <p>Class of Model <b>ClassDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Class Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    model.structural.diagram.classes.base.InterfaceUML
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    model.structural.diagram.classes.base.PackageUML
 */
public final class ClassDiagram extends Diagram {
    private HashMap<String, PackageUML>   packagesUML;
    private HashMap<String, ClassUML>     classUML;
    private HashMap<String, InterfaceUML> interfacesUML;
    private HashMap<String, AttributeUML> attributesUML;
    private HashMap<String, MethodUML>    methodsUML;
    private HashMap<String, Association>  associationsUML;
    private HashMap<String, Association>  realizationsUML;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ClassDiagram(Project project) {
        super(project);
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ClassDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }
    
    @Override
    public void init() {
        this.type            = "Class";
        this.packagesUML     = new HashMap<>();
        this.classUML        = new HashMap<>();
        this.interfacesUML   = new HashMap<>();
        this.attributesUML   = new HashMap<>();
        this.methodsUML      = new HashMap<>();
        this.associationsUML = new HashMap<>();
        this.realizationsUML = new HashMap<>();
    }
    
    /**
     * Method responsible for changing the Type UML.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeTypeUML(TypeUML oldType, TypeUML newType) {
        this.changeClassTypes(oldType, newType);
        this.changeInterfaceTypes(oldType, newType);
    }
    
    /**
     * Method responsible for returning the next Package Id.
     * @return Next Package Id.
     */
    private String nextPackageId() {
        return this.nextId("PACKAGE#");
    }
    
    /**
     * Method responsible for adding a Package UML.
     * @param package_ Package UML.
     */
    public void addPackage(PackageUML package_) {
        package_.setId(this.nextPackageId());
        if (this.packagesUML.get(package_.getId()) == null) {
            this.packagesUML.put(package_.getId(), package_);
            this.addElement(package_);
            package_.setDiagram(this);
        }
    }
    
    /**
     * Method responsible for removing a Package UML.
     * @param packageUML Package UML.
     */
    public void removePackage(PackageUML packageUML) {
        this.updateParents(packageUML);
        this.removePackages(packageUML);
        this.removeEntities(packageUML);
        this.removeAssociations(packageUML);
        this.removeElement(packageUML);
        this.packagesUML.remove(packageUML.getId());
    }
    
    /**
     * Method responsible for reseting a Entity from your Package.
     * @param entity Entity.
     */
    public void resetPackage(Entity entity) {
        if (entity.getPackageUML() != null)
            entity.getPackageUML().removeEntity(entity);
    }
    
    /**
     * Method responsible for updating the Parent Packages.
     * @param packageUML Package UML.
     */
    private void updateParents(PackageUML packageUML) {
        for (PackageUML current : this.getPackagesList())
            current.removePackage(packageUML);
    }
    
    /**
     * Method responsible for removing the Packages from a Package UML.
     * @param packageUML Package UML.
     */
    private void removePackages(PackageUML packageUML) {
        for (PackageUML current : packageUML.getPackagesList())
            this.removePackage(current);
    }
    
    /**
     * Method responsible for removing the Entities from a Package UML.
     * @param packageUML Package UML.
     */
    private void removeEntities(PackageUML packageUML) {
        for (Entity current : packageUML.getEntitiesList()) {
            if (current instanceof ClassUML)
                this.removeClass((ClassUML) current);
            else
                this.removeInterface((InterfaceUML) current);
        }
    }
    
    /**
     * Method responsible for returning the Packages List.
     * @return Packages List.
     */
    public List<PackageUML> getPackagesList() {
        return new ArrayList<>(this.packagesUML.values());
    }
    
    /**
     * Method responsible for returning the next Class Id.
     * @return Next Class Id.
     */
    private String nextClassId() {
        return this.nextId("CLASS#");
    }
    
    /**
     * Method responsible for adding a Class UML.
     * @param class_ Class UML.
     */
    public void addClass(ClassUML class_) {
        class_.setId(this.nextClassId());
        if (this.classUML.get(class_.getId()) == null) {
            this.classUML.put(class_.getId(), class_);
            this.addElement(class_);
            this.project.addEntityType(class_);
            class_.setDiagram(this);
        }
    }
    
    /**
     * Method responsible for removing a Class UML.
     * @param class_ Class UML.
     */
    public void removeClass(ClassUML class_) {
        this.project.removeEntityType(class_);
        this.project.reset(class_);
        this.removeAttributes(class_);
        this.removeMethods(class_);
        this.removeAssociations(class_);
        this.removeElement(class_);
        this.resetPackage(class_);
        this.classUML.remove(class_.getId());
    }
    
    /**
     * Method responsible for changing the Class Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeClassTypes(TypeUML oldType, TypeUML newType) {
        List<ClassUML> list = this.getClassList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeType(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Class List.
     * @return Class List.
     */
    public List<ClassUML> getClassList() {
        return new ArrayList<>(this.classUML.values());
    }
    
    /**
     * Method responsible for returning the next Interface Id.
     * @return Next Interface Id.
     */
    private String nextInterfaceId() {
        return this.nextId("INTERFACE#");
    }
    
    /**
     * Method responsible for adding a Interface UML.
     * @param interface_ Interface UML.
     */
    public void addInterface(InterfaceUML interface_) {
        interface_.setId(this.nextInterfaceId());
        if (this.interfacesUML.get(interface_.getId()) == null) {
            this.interfacesUML.put(interface_.getId(), interface_);
            this.addElement(interface_);
            this.project.addEntityType(interface_);
            interface_.setDiagram(this);
        }
    }
    
    /**
     * Method responsible for removing a Interface UML.
     * @param interface_ Interface UML.
     */
    public void removeInterface(InterfaceUML interface_) {
        this.project.removeEntityType(interface_);
        this.removeAttributes(interface_);
        this.removeMethods(interface_);
        this.removeAssociations(interface_);
        this.removeElement(interface_);
        this.resetPackage(interface_);
        this.interfacesUML.remove(interface_.getId());
    }
    
    /**
     * Method responsible for changing the Interface Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeInterfaceTypes(TypeUML oldType, TypeUML newType) {
        for (InterfaceUML interfaceUML : this.getInterfacesList())
           interfaceUML.changeType(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Interfaces List.
     * @return Interfaces List.
     */
    public List<InterfaceUML> getInterfacesList() {
        return new ArrayList<>(this.interfacesUML.values());
    }
    
    /**
     * Method responsible for removing the Attributes from a Entity.
     * @param entity Entity.
     */
    private void removeAttributes(Entity entity) {
        for (AttributeUML attribute : entity.getAttributesList())
            this.removeAttribute(attribute);
    }
    
    /**
     * Method responsible for returning the Entities List.
     * @return Entities List.
     */
    public List<Entity> getEntitiesList() {
        List   entities = new ArrayList<>();
               entities.addAll(this.getClassList());
               entities.addAll(this.getInterfacesList());
        return entities;
    }
    
    /**
     * Method responsible for returning the next Attribute Id.
     * @return Next Attribute Id.
     */
    public String nextAttributeId() {
        return this.nextId("ATTRIBUTE#");
    }
    
    /**
     * Method responsible for adding a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void addAttribute(AttributeUML attribute) {
        attribute.setId(this.nextAttributeId());
        if (this.attributesUML.get(attribute.getId()) == null) {
            this.attributesUML.put(attribute.getId(), attribute);
            this.addElement(attribute);
        }
    }
    
    /**
     * Method responsible for removing a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void removeAttribute(AttributeUML attribute) {
        attribute.getEntity().removeAttribute(attribute);
        this.removeElement(attribute);
        this.attributesUML.remove(attribute.getId());
    }
    
    /**
     * Method responsible for removing the Methods from a Entity.
     * @param entity Entity.
     */
    private void removeMethods(Entity entity) {
        for (MethodUML method : entity.getMethodsList())
            this.removeMethod(method);
    }
    
    /**
     * Method responsible for returning the next Method Id.
     * @return Next Method Id.
     */
    public String nextMethodId() {
        return this.nextId("METHOD#");
    }
    
    /**
     * Method responsible for adding a Method UML.
     * @param method Method UML.
     */
    public void addMethod(MethodUML method) {
        method.setId(this.nextMethodId());
        if (this.methodsUML.get(method.getId()) == null) {
            this.methodsUML.put(method.getId(), method);
            this.addElement(method);
        }
    }
    
    /**
     * Method responsible for removing a Method UML.
     * @param method Method UML.
     */
    public void removeMethod(MethodUML method) {
        method.getEntity().removeMethod(method);
        this.project.reset(method);
        this.removeElement(method);
        this.methodsUML.remove(method.getId());
    }
    
    /**
     * Method responsible for adding a Realization UML.
     * @param realization Realization UML.
     */
    public void addRealizationUML(RealizationUML realization) {
        realization.setId(this.nextId(realization));
        if (this.realizationsUML.get(realization.getId()) == null) {
            this.realizationsUML.put(realization.getId(), realization);
            this.addAssociation(realization);
        }
    }
    
    /**
     * Method responsible for returning the Realizations of a Class.
     * @param  entity Class UML.
     * @return Realizations List.
     */
    public List<Association> getRealizations(ClassUML entity) {
        return this.getTargetAssociations("realization", entity);
    }
    
    /**
     * Method responsible for removing a Realization UML.
     * @param realization Realization UML.
     */
    public void removeRealizationUML(RealizationUML realization) {
        super.removeAssociation(realization);
        this.realizationsUML.remove(realization.getId());
    }
    
    /**
     * Method responsible for adding a Association UML.
     * @param association Association UML.
     */
    public void addAssociationUML(AssociationUML association) {
        association.setId(this.nextId(association));
        if (this.associationsUML.get(association .getId()) == null) {
            this.associationsUML.put(association .getId(), association);
            this.addAssociation(association);
        }
    }
    
    /**
     * Method responsible for removing a Association UML.
     * @param association Association UML.
     */
    public void removeAssociationUML(AssociationUML association) {
        super.removeAssociation(association);
        this.associationsUML.remove(association.getId());
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.associationsUML);
        this.removeAssociation(element, this.realizationsUML);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof AssociationUML)
            this.removeAssociationUML((AssociationUML) association);
        else if (association instanceof RealizationUML)
            this.removeRealizationUML((RealizationUML) association);
        else
            super.removeAssociation(association);
    }
    
    /**
     * Method responsible for returning the Visibilities Array.
     * @return Visibilities Array.
     */
    public String[] getVisibilities() {
        return new String[] {"private", "protected", "default", "public"};
    }
    
    /**
     * Method responsible for Shift X for Cardinalities.
     * @param element Element.
     * @param distance Shift Distance.
     */
    public void dx(Element element, Integer distance) {
        List<AssociationUML> filter = this.getAssociations("association");
        for (AssociationUML association : filter) {
            association.dxSource(association.isSource(element) ? distance : 0);
            association.dxTarget(association.isTarget(element) ? distance : 0);
        }
    }
    
    /**
     * Method responsible for Shift Y for Cardinalities.
     * @param element Element.
     * @param distance Shift Distance.
     */
    public void dy(Element element, Integer distance) {
        List<AssociationUML> filter = this.getAssociations("association");
        for (AssociationUML association : filter) {
            association.dySource(association.isSource(element) ? distance : 0);
            association.dyTarget(association.isTarget(element) ? distance : 0);
        }
    }
    
    /**
     * Method responsible for returning the References to export.
     * @return References to export.
     */
    private String exportReferences() {
        String  export  = "";
        for (PackageUML current : this.getPackagesList()) {
            if (current.getParent() != null)
                export += "    <reference package=\"" + current.getId() + "\" parent=\"" + current.getParent().getId() + "\"/>\n";
        }
        return  export;
    }
    
    @Override
    public String export() {
        String export  = "  <diagram id=\"" + this.id + "\" name=\"" + this.name + "\" type=\"" + this.type + "\">\n";
               export += this.export((List<Element>) this.getList(this.packagesUML));
               export += this.export((List<Element>) this.getList(this.classUML));
               export += this.export((List<Element>) this.getList(this.interfacesUML));
               export += this.exportAssociations();
               export += this.exportReferences();
               export += this.exportVariabilities();
               export += "  </diagram>\n";
        return export;
    }
    
    @Override
    public List<Element> getTreeElementsList() {
        List<Element> filter  = new ArrayList<>();
        for (Element  element : this.getElementsList()) {
            if ((element instanceof PackageUML)  && ((PackageUML) element).getParent() == null)
                filter.add(element);
            else if ((element instanceof Entity) && ((Entity) element).getPackageUML() == null)
                filter.add(element);
        }
        return  filter;
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "class.png";
    }
    
    @Override
    public ClassDiagram getClone() {
        try {
            ClassDiagram diagram = (ClassDiagram) super.clone();
                         diagram.setElements(new HashMap<>(this.elements));
                         diagram.setAssociations(new HashMap<>(this.associationsUML));
                         diagram.setVariabilities(new HashMap<>(this.variabilities));
            return       diagram;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error");
            return null;
        }
    }
    
    @Override
    public String toString() {
        return super.toString();
    }
}
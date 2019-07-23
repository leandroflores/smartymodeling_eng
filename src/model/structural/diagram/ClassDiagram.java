package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        }
    }
    
    /**
     * Method responsible for removing a Package UML.
     * @param package_ Package UML.
     */
    public void removePackage(PackageUML package_) {
        this.removeEntities(package_);
        this.removeAssociations(package_);
        this.removeElement(package_);
        this.packagesUML.remove(package_.getId());
    }
    
    /**
     * Method responsible for removing the Entities from a Package UML.
     * @param package_ Package UML.
     */
    private void removeEntities(PackageUML package_) {
        List<Entity> list = package_.getEntitiesList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof ClassUML)
                this.removeClass((ClassUML) list.get(i));
            else if (list.get(i) instanceof InterfaceUML)
                this.removeInterface((InterfaceUML) list.get(i));
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
     * @param classe_ Class UML.
     */
    public void removeClass(ClassUML classe_) {
        this.project.removeEntityType(classe_);
        this.removeAttributes(classe_);
        this.removeMethods(classe_);
        this.removeAssociations(classe_);
        this.removeElement(classe_);
        this.classUML.remove(classe_.getId());
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
        this.interfacesUML.remove(interface_.getId());
    }
    
    /**
     * Method responsible for changing the Interface Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeInterfaceTypes(TypeUML oldType, TypeUML newType) {
        List<InterfaceUML>  list = this.getInterfacesList();
        for (int i = 0; i < list.size(); i++)
            list.get(i).changeType(oldType, newType);
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
        this.removeElement(method);
        this.methodsUML.remove(method.getId());
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.associationsUML);
        this.removeAssociation(element, this.realizationsUML);
    }
    
    /**
     * Method responsible for removing the Association by Element.
     * @param element Element.
     * @param map Associations Map.
     */
    private void removeAssociation(Element element, Map<String, Association> map) {
        for (Association association : map.values()) {
            if (association.contains(element))
                this.removeAssociation(association);
        }
    }
    
    /**
     * Method responsible for adding a Realization UML.
     * @param realization Realization UML.
     */
    public void addRealizationUML(RealizationUML realization) {
        if (this.realizationsUML.get(realization.getId()) == null) {
            this.realizationsUML.put(realization.getId(), realization);
            this.addAssociation(realization);
        }
    }
    
    /**
     * Method responsible for removing a Realization UML.
     * @param realization Realization UML.
     */
    public void removeRealizationUML(RealizationUML realization) {
        this.removeAssociation(realization);
        this.realizationsUML.remove(realization.getId());
    }
    
    /**
     * Method responsible for adding a Association UML.
     * @param association Association UML.
     */
    public void addAssociationUML(AssociationUML association) {
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
        this.removeAssociation(association);
        this.associationsUML.remove(association.getId());
    }
    
    /**
     * Method responsible for returning the Visibilities Array.
     * @return Visibilities Array.
     */
    public String[] getVisibilities() {
        return new String[] {"private", "protected", "default", "public"};
    }
    
    @Override
    public String getIcon() {
        return "diagram/class";
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
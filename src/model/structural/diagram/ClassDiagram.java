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
import model.structural.diagram.classes.base.association.Abstraction;
import model.structural.diagram.classes.base.association.Usage;

/**
 * <p>Class of Model <b>ClassDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Class Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.classes.base.AttributeUML
 * @see    model.structural.diagram.classes.base.ClassUML
 * @see    model.structural.diagram.classes.base.InterfaceUML
 * @see    model.structural.diagram.classes.base.MethodUML
 * @see    model.structural.diagram.classes.base.PackageUML
 */
public final class ClassDiagram extends Diagram {
    private HashMap<String, PackageUML>   packages;
    private HashMap<String, ClassUML>     classes;
    private HashMap<String, InterfaceUML> interfaces;
    private HashMap<String, AttributeUML> attributes;
    private HashMap<String, MethodUML>    methods;
    private HashMap<String, Association>  associations_;
    private HashMap<String, Association>  realizations;
    private HashMap<String, Association>  abstractions;
    private HashMap<String, Association>  usages;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ClassDiagram(Project project) {
        super(project);
        init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ClassDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        init();
    }
    
    @Override
    public void init() {
        type          = "Class";
        packages      = new HashMap<>();
        classes       = new HashMap<>();
        interfaces    = new HashMap<>();
        attributes    = new HashMap<>();
        methods       = new HashMap<>();
        associations_ = new HashMap<>();
        realizations  = new HashMap<>();
        abstractions  = new HashMap<>();
        usages        = new HashMap<>();
    }
    
    /**
     * Method responsible for changing the Type UML.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    public void changeTypeUML(TypeUML oldType, TypeUML newType) {
        changeClassTypes(oldType, newType);
        changeInterfaceTypes(oldType, newType);
    }
    
    /**
     * Method responsible for returning the next Package Id.
     * @return Next Package Id.
     */
    private String nextPackageId() {
        return nextId("PACKAGE#");
    }
    
    /**
     * Method responsible for adding a Package UML.
     * @param package_ Package UML.
     */
    public void addPackage(PackageUML package_) {
        package_.setId(nextPackageId());
        if (packages.get(package_.getId()) == null) {
            packages.put(package_.getId(), package_);
            addElement(package_);
            package_.setDiagram(this);
        }
    }
    
    /**
     * Method responsible for removing a Package UML.
     * @param packageUML Package UML.
     */
    public void removePackage(PackageUML packageUML) {
        updateParents(packageUML);
        removePackages(packageUML);
        removeEntities(packageUML);
        removeAssociations(packageUML);
        removeElement(packageUML);
        packages.remove(packageUML.getId());
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
        for (PackageUML current : getPackagesList())
            current.removePackage(packageUML);
    }
    
    /**
     * Method responsible for removing the Packages from a Package UML.
     * @param packageUML Package UML.
     */
    private void removePackages(PackageUML packageUML) {
        for (PackageUML current : packageUML.getPackagesList())
            removePackage(current);
    }
    
    /**
     * Method responsible for removing the Entities from a Package UML.
     * @param packageUML Package UML.
     */
    private void removeEntities(PackageUML packageUML) {
        for (Entity current : packageUML.getEntitiesList()) {
            if (current instanceof ClassUML)
                removeClass((ClassUML) current);
            else
                removeInterface((InterfaceUML) current);
        }
    }
    
    /**
     * Method responsible for returning the Packages List.
     * @return Packages List.
     */
    public List<PackageUML> getPackagesList() {
        return new ArrayList<>(packages.values());
    }
    
    /**
     * Method responsible for returning the next Class Id.
     * @return Next Class Id.
     */
    private String nextClassId() {
        return nextId("CLASS#");
    }
    
    /**
     * Method responsible for adding a Class UML.
     * @param class_ Class UML.
     */
    public void addClass(ClassUML class_) {
        class_.setId(nextClassId());
        if (classes.get(class_.getId()) == null) {
            classes.put(class_.getId(), class_);
            addElement(class_);
            project.addEntityType(class_);
            class_.setDiagram(this);
        }
    }
    
    /**
     * Method responsible for removing a Class UML.
     * @param class_ Class UML.
     */
    public void removeClass(ClassUML class_) {
        project.removeEntityType(class_);
        project.reset(class_);
        removeAttributes(class_);
        removeMethods(class_);
        removeAssociations(class_);
        removeElement(class_);
        resetPackage(class_);
        classes.remove(class_.getId());
    }
    
    /**
     * Method responsible for changing the Class Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeClassTypes(TypeUML oldType, TypeUML newType) {
        for (ClassUML classUML : getClassesList())
             classUML.changeType(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Classes List.
     * @return Classes List.
     */
    public List<ClassUML> getClassesList() {
        return new ArrayList<>(classes.values());
    }
    
    /**
     * Method responsible for returning the next Interface Id.
     * @return Next Interface Id.
     */
    private String nextInterfaceId() {
        return nextId("INTERFACE#");
    }
    
    /**
     * Method responsible for adding a Interface UML.
     * @param interface_ Interface UML.
     */
    public void addInterface(InterfaceUML interface_) {
        interface_.setId(nextInterfaceId());
        if (interfaces.get(interface_.getId()) == null) {
            interfaces.put(interface_.getId(), interface_);
            addElement(interface_);
            project.addEntityType(interface_);
            interface_.setDiagram(this);
        }
    }
    
    /**
     * Method responsible for removing a Interface UML.
     * @param interface_ Interface UML.
     */
    public void removeInterface(InterfaceUML interface_) {
        project.removeEntityType(interface_);
        removeAttributes(interface_);
        removeMethods(interface_);
        removeAssociations(interface_);
        removeElement(interface_);
        resetPackage(interface_);
        interfaces.remove(interface_.getId());
    }
    
    /**
     * Method responsible for changing the Interface Types.
     * @param oldType Old Type.
     * @param newType New Type.
     */
    private void changeInterfaceTypes(TypeUML oldType, TypeUML newType) {
        for (InterfaceUML interfaceUML : getInterfacesList())
           interfaceUML.changeType(oldType, newType);
    }
    
    /**
     * Method responsible for returning the Interfaces List.
     * @return Interfaces List.
     */
    public List<InterfaceUML> getInterfacesList() {
        return new ArrayList<>(interfaces.values());
    }
    
    /**
     * Method responsible for removing the Attributes from a Entity.
     * @param entity Entity.
     */
    private void removeAttributes(Entity entity) {
        for (AttributeUML attribute : entity.getAttributesList())
            removeAttribute(attribute);
    }
    
    /**
     * Method responsible for returning the Entities List.
     * @return Entities List.
     */
    public List<Entity> getEntitiesList() {
        List   entities = new ArrayList<>();
               entities.addAll(getClassesList());
               entities.addAll(getInterfacesList());
        return entities;
    }
    
    /**
     * Method responsible for returning the next Attribute Id.
     * @return Next Attribute Id.
     */
    public String nextAttributeId() {
        return nextId("ATTRIBUTE#");
    }
    
    /**
     * Method responsible for adding a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void addAttribute(AttributeUML attribute) {
        attribute.setId(nextAttributeId());
        if (attributes.get(attribute.getId()) == null) {
            attributes.put(attribute.getId(), attribute);
            addElement(attribute);
        }
    }
    
    /**
     * Method responsible for removing a Attribute UML.
     * @param attribute Attribute UML.
     */
    public void removeAttribute(AttributeUML attribute) {
        attribute.getEntity().removeAttribute(attribute);
        removeElement(attribute);
        attributes.remove(attribute.getId());
    }
    
    /**
     * Method responsible for returning the Attributes List.
     * @return Attributes List.
     */
    public List<AttributeUML> getAttributesList() {
        return new ArrayList<>(attributes.values());
    }
    
    /**
     * Method responsible for removing the Methods from a Entity.
     * @param entity Entity.
     */
    private void removeMethods(Entity entity) {
        for (MethodUML method : entity.getMethodsList())
            removeMethod(method);
    }
    
    /**
     * Method responsible for returning the next Method Id.
     * @return Next Method Id.
     */
    public String nextMethodId() {
        return nextId("METHOD#");
    }
    
    /**
     * Method responsible for adding a Method UML.
     * @param method Method UML.
     */
    public void addMethod(MethodUML method) {
        method.setId(nextMethodId());
        if (methods.get(method.getId()) == null) {
            methods.put(method.getId(), method);
            addElement(method);
        }
    }
    
    /**
     * Method responsible for removing a Method UML.
     * @param method Method UML.
     */
    public void removeMethod(MethodUML method) {
        method.getEntity().removeMethod(method);
        project.reset(method);
        removeElement(method);
        methods.remove(method.getId());
    }
    
    /**
     * Method responsible for returning the Methods List.
     * @return Methods List.
     */
    public List<MethodUML> getMethodsList() {
        return new ArrayList<>(methods.values());
    }
    
    /**
     * Method responsible for adding a Realization UML.
     * @param realization Realization UML.
     */
    public void addRealizationUML(RealizationUML realization) {
        realization.setId(nextId(realization));
        if (realizations.get(realization.getId()) == null) {
            realizations.put(realization.getId(), realization);
            addAssociation(realization);
        }
    }
    
    /**
     * Method responsible for returning the Realizations of a Entity.
     * @param  entity Entity.
     * @return Realizations List.
     */
    public List<Association> getRealizations(Entity entity) {
        if (entity.isClass())
            return getRealizations((ClassUML) entity);
        return new ArrayList<>();
    }
    
    /**
     * Method responsible for returning the Realizations of a Class.
     * @param  class_ Class UML.
     * @return Realizations List.
     */
    public List<Association> getRealizations(ClassUML class_) {
        return getTargetAssociations("realization", class_);
    }
    
    /**
     * Method responsible for removing a Realization UML.
     * @param realization Realization UML.
     */
    public void removeRealizationUML(RealizationUML realization) {
        super.removeAssociation(realization);
        realizations.remove(realization.getId());
    }
    
    /**
     * Method responsible for adding a Association UML.
     * @param association Association UML.
     */
    public void addAssociationUML(AssociationUML association) {
        association.setId(nextId(association));
        if (associations_.get(association .getId()) == null) {
            associations_.put(association .getId(), association);
            addAssociation(association);
        }
    }
    
    /**
     * Method responsible for removing a Association UML.
     * @param association Association UML.
     */
    public void removeAssociationUML(AssociationUML association) {
        super.removeAssociation(association);
        associations_.remove(association.getId());
    }
    
    /**
     * Method responsible for adding a Abstraction.
     * @param abstraction Abstraction.
     */
    public void addAbstraction(Abstraction abstraction) {
        abstraction.setId(nextId(abstraction));
        if (abstractions.get(abstraction .getId()) == null) {
            abstractions.put(abstraction .getId(), abstraction);
            addAssociation(abstraction);
        }
    }
    
    /**
     * Method responsible for removing a Abstraction.
     * @param abstraction Abstraction.
     */
    public void removeAbstraction(Abstraction abstraction) {
        super.removeAssociation(abstraction);
        abstractions.remove(abstraction.getId());
    }
    
    /**
     * Method responsible for adding a Usage.
     * @param usage Usage.
     */
    public void addUsage(Usage usage) {
        usage.setId(nextId(usage));
        if (usages.get(usage .getId()) == null) {
            usages.put(usage .getId(), usage);
            addAssociation(usage);
        }
    }
    
    /**
     * Method responsible for removing a Usage.
     * @param usage Usage.
     */
    public void removeAbstraction(Usage usage) {
        super.removeAssociation(usage);
        usages.remove(usage.getId());
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        removeAssociation(element, associations_);
        removeAssociation(element, realizations);
        removeAssociation(element, abstractions);
        removeAssociation(element, usages);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof AssociationUML)
            removeAssociationUML((AssociationUML) association);
        else if (association instanceof RealizationUML)
            removeRealizationUML((RealizationUML) association);
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
     * Method responsible for returning the Abstract Visibilities Array.
     * @return Abstract Visibilities Array.
     */
    public String[] getAbstractVisibilites() {
        return new String[] {"default", "public"};
    }
    
    /**
     * Method responsible for Shift X for Cardinalities.
     * @param element Element.
     * @param distance Shift Distance.
     */
    public void dx(Element element, Integer distance) {
        List<AssociationUML> filter = getAssociations("association");
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
        List<AssociationUML> filter = getAssociations("association");
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
        for (PackageUML current : getPackagesList()) {
            if (current.getParent() != null)
                export += "    <nested package=\"" + current.getId() + "\" parent=\"" + current.getParent().getId() + "\"/>\n";
        }
        return  export;
    }
    
    @Override
    public String export() {
        String export  = "  <diagram id=\"" + id + "\" name=\"" + name + "\" type=\"" + type + "\">\n";
               export += export((List<Element>) getList(packages));
               export += export((List<Element>) getList(classes));
               export += export((List<Element>) getList(interfaces));
               export += exportAssociations();
               export += exportReferences();
               export += exportVariabilities();
               export += "  </diagram>\n";
        return export;
    }
    
    @Override
    public List<Element> getTreeElementsList() {
        List<Element> filter  = new ArrayList<>();
        for (Element  element : getElementsList()) {
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
    public String getInstanceIcon() {
        return "icons/product/instance/instance-class.png";
    }
    
    @Override
    public ClassDiagram getClone() {
        try {
            ClassDiagram diagram = (ClassDiagram) super.clone();
                         diagram.setElements(new HashMap<>(elements));
                         diagram.setAssociations(new HashMap<>(associations_));
                         diagram.setVariabilities(new HashMap<>(variabilities));
            return       diagram;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }
}
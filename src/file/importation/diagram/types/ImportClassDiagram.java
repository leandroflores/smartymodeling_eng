package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Project;
import model.structural.diagram.ClassDiagram;
import model.structural.diagram.classes.Entity;
import model.structural.diagram.classes.base.association.AssociationUML;
import model.structural.diagram.classes.base.association.RealizationUML;
import model.structural.diagram.classes.base.AttributeUML;
import model.structural.diagram.classes.base.ClassUML;
import model.structural.diagram.classes.base.InterfaceUML;
import model.structural.diagram.classes.base.MethodUML;
import model.structural.diagram.classes.base.PackageUML;
import model.structural.diagram.classes.base.ParameterUML;
import model.structural.diagram.classes.base.TypeUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportClassDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Class Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-06-03
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.ClassDiagram
 */
public class ImportClassDiagram extends ImportDiagram {
    private final Project project;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportClassDiagram(Project project, Element element) {
        this.project = project;
        this.diagram = new ClassDiagram(project, element);
        this.element = element;
    }
    
    @Override
    protected void importElements() {
        this.importPackages();
        this.importClasses();
        this.importInterfaces();
    }
    
    /**
     * Method responsible for importing the UML Packages.
     */
    private void importPackages() {
        NodeList list = this.element.getElementsByTagName("package");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current  = (Element) list.item(i);
            PackageUML package_ = new PackageUML(current);
            this.getDiagram().addPackage(package_);
        }
    }
    
    /**
     * Method responsible for updating the Parent Entity.
     * @param parent Parent Id.
     * @param entity Entity.
     */
    private void updateParent(String parent, Entity entity) {
        if (this.getElement(parent) instanceof PackageUML) {
            PackageUML package_ = (PackageUML) this.getElement(parent);
                       package_.addEntity(entity);
                       entity.setPackageUML(package_);
        }
    }
    
    /**
     * Method responsible for importing the UML Classes.
     */
    private void importClasses() {
        NodeList list = this.element.getElementsByTagName("class");
        for (int i = 0; i < list.getLength(); i++) {
            Element  current = (Element) list.item(i);
            ClassUML class_  = new ClassUML(current);
                     class_.setTypeUML(this.project.getEntityType(class_));
                this.addAttributes(current, class_);
                this.addMethods(current, class_);
            this.getDiagram().addClass(class_);
            this.updateParent(current.getAttribute("parent"), class_);
        }
    }
    
    /**
     * Method responsible for importing the UML Interfaces.
     */
    private void importInterfaces() {
        NodeList list = this.element.getElementsByTagName("interface");
        for (int i = 0; i < list.getLength(); i++) {
            Element      current    = (Element) list.item(i);
            InterfaceUML interface_ = new InterfaceUML(current);
                         interface_.setTypeUML(this.project.getEntityType(interface_));
                this.addAttributes(current, interface_);
                this.addMethods(current, interface_);
            this.getDiagram().addInterface(interface_);
            this.updateParent(current.getAttribute("parent"), interface_);
        }
    }
    
    /**
     * Method responsible for adding the Entity Attributes.
     * @param node W3C Element.
     * @param entity Entity.
     */
    private void addAttributes(Element node, Entity entity) {
        NodeList list = node.getElementsByTagName("attribute");
        for (int i = 0; i < list.getLength(); i++) {
            Element      current   = (Element) list.item(i);
            AttributeUML attribute = new AttributeUML(current);
                         attribute.setTypeUML(this.getType(current.getAttribute("type")));
                         attribute.setEntity(entity);
                         entity.addAttribute(attribute);
            this.getDiagram().addAttribute(attribute);
        }
    }
    
    /**
     * Method responsible for adding the Entity Methods.
     * @param node W3C Element.
     * @param entity Entity.
     */
    private void addMethods(Element node, Entity entity) {
        NodeList list = node.getElementsByTagName("method");
        for (int i = 0; i < list.getLength(); i++) {
            Element   current = (Element) list.item(i);
            MethodUML method  = new MethodUML(current);
                      method.setEntity(entity);
                      method.setReturn(this.getType(current.getAttribute("return")));
                this.importParameters(current, method);
                entity.addMethod(method);
            this.getDiagram().addMethod(method);
        }
    }
    
    /**
     * Method responsible for adding the Method Parameters.
     * @param node W3C Element.
     * @param method Method UML. 
     */
    private void importParameters(Element node, MethodUML method) {
        NodeList list = node.getElementsByTagName("parameter");
        for (int i = 0; i < list.getLength(); i++) {
            Element      current   = (Element) list.item(i);
            ParameterUML parameter = new ParameterUML(current);
                         parameter.setType(this.getType(current.getAttribute("type")));
            method.addParameter(parameter);
        }
    }
    
    /**
     * Method responsible for returning a Type by Id.
     * @param  id Type Id.
     * @return Type found.
     */
    private TypeUML getType(String id) {
        if (this.project.getTypes().get(id) == null)
            return this.diagram.getObjectType();
        return (TypeUML) this.project.getTypes().get(id);
    }
    
    @Override
    protected void importAssociations() {
        this.importRealizations();
        this.importAssociationsUML();
        this.importReferences();
    }
    
    /**
     * Method responsible for importing the UML Realizations.
     */
    private void importRealizations() {
        NodeList list = this.element.getElementsByTagName("realization");
        for (int i = 0; i < list.getLength(); i++) {
            Element        current     = (Element) list.item(i);
            RealizationUML realization = new RealizationUML(current);
                           realization.setSource((ClassUML)     this.getElement(current.getAttribute("class")));
                           realization.setTarget((InterfaceUML) this.getElement(current.getAttribute("interface")));
                           super.addPoints(current, realization);
            this.getDiagram().addRealizationUML(realization);
        }
    }
    
    /**
     * Method responsible for importing the UML Associations.
     */
    private void importAssociationsUML() {
        NodeList list = this.element.getElementsByTagName("association");
        for (int i = 0; i < list.getLength(); i++) {
            Element        current     = (Element) list.item(i);
            AssociationUML association = new AssociationUML(current);
                this.setSource(current, association);
                this.setTarget(current, association);
                super.addPoints(current, association);
            this.getDiagram().addAssociationUML(association);
        }
    }
    
    /**
     * Method responsible for setting the Source Association.
     * @param node W3C Element.
     * @param association Association UML.
     */
    private void setSource(Element node, AssociationUML association) {
        Element item = (Element) node.getElementsByTagName("source").item(0);
                association.setSource(item);
                association.setSource(this.getElement(item.getAttribute("entity")));
    }
    
    /**
     * Method responsible for setting the Target Association.
     * @param node W3C Element.
     * @param association Association UML.
     */
    private void setTarget(Element node, AssociationUML association) {
        Element item = (Element) node.getElementsByTagName("target").item(0);
                association.setTarget(item);
                association.setTarget(this.getElement(item.getAttribute("entity")));
    }
    
    /**
     * Method responsible for importing the References.
     */
    protected void importReferences() {
        NodeList list =  this.element.getElementsByTagName("reference");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current  = (Element)    list.item(i);
            PackageUML package_ = (PackageUML) this.getElement(current.getAttribute("package"));
            PackageUML parent   = (PackageUML) this.getElement(current.getAttribute("parent"));
                       package_.setParent(parent);
                       parent.addPackage(package_);
        }
    }
    
    @Override
    protected ClassDiagram getDiagram() {
        return (ClassDiagram) this.diagram;
    }
}
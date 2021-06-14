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
import model.structural.diagram.classes.base.association.Abstraction;
import model.structural.diagram.classes.base.association.Usage;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of File <b>ImportClassDiagram</b>.</p>
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
        importPackages();
        importClasses();
        importInterfaces();
    }
    
    /**
     * Method responsible for importing the UML Packages.
     */
    private void importPackages() {
        NodeList list = element.getElementsByTagName("package");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current  = (Element) list.item(i);
            PackageUML package_ = new PackageUML(current, getDiagram());
            getDiagram().addPackage(package_);
        }
    }
    
    /**
     * Method responsible for updating the Parent Entity.
     * @param parent Parent Id.
     * @param entity Entity.
     */
    private void updateParent(String parent, Entity entity) {
        if (getElement(parent) instanceof PackageUML) {
            PackageUML package_ = (PackageUML) getElement(parent);
                       package_.addEntity(entity);
                       entity.setPackageUML(package_);
        }
    }
    
    /**
     * Method responsible for importing the UML Classes.
     */
    private void importClasses() {
        NodeList list = element.getElementsByTagName("class");
        for (int i = 0; i < list.getLength(); i++) {
            Element  current = (Element) list.item(i);
            ClassUML class_  = new ClassUML(current, getDiagram());
                     class_.setTypeUML(project.getEntityType(class_));
                     class_.setDescription(getDescription(current));
                addAttributes(current, class_);
                addMethods(current, class_);
            getDiagram().addClass(class_);
            updateParent(current.getAttribute("parent"), class_);
        }
    }
    
    /**
     * Method responsible for importing the UML Interfaces.
     */
    private void importInterfaces() {
        NodeList list = element.getElementsByTagName("interface");
        for (int i = 0; i < list.getLength(); i++) {
            Element      current    = (Element) list.item(i);
            InterfaceUML interface_ = new InterfaceUML(current, getDiagram());
                         interface_.setTypeUML(project.getEntityType(interface_));
                         interface_.setDescription(getDescription(current));
                addAttributes(current, interface_);
                addMethods(current, interface_);
            getDiagram().addInterface(interface_);
            updateParent(current.getAttribute("parent"), interface_);
        }
    }
    
    /**
     * Method responsible for returning the Entity Description.
     * @param  element W3C Element.
     * @return Entity Description.
     */
    private String getDescription(Element element) {
        if (element.getElementsByTagName("description").getLength() > 0)
            return element.getElementsByTagName("description").item(0).getTextContent();
        return "";
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
            AttributeUML attribute = new AttributeUML(current, getDiagram());
                         attribute.setTypeUML(getType(current.getAttribute("type")));
                         attribute.setEntity(entity);
                         entity.addAttribute(attribute);
            getDiagram().addAttribute(attribute);
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
            MethodUML method  = new MethodUML(current, getDiagram());
                      method.setEntity(entity);
                      method.setReturn(getType(current.getAttribute("return")));
                importParameters(current, method);
                entity.addMethod(method);
            getDiagram().addMethod(method);
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
                         parameter.setType(getType(current.getAttribute("type")));
            method.addParameter(parameter);
        }
    }
    
    /**
     * Method responsible for returning a Type by Id.
     * @param  id Type Id.
     * @return Type found.
     */
    private TypeUML getType(String id) {
        if (project.getTypes().get(id) == null)
            return diagram.getObjectType();
        return (TypeUML) project.getTypes().get(id);
    }
    
    @Override
    protected void importAssociations() {
        importRealizations();
        importAbstractions();
        importUsages();
        importAssociationsUML();
        importReferences();
    }
    
    /**
     * Method responsible for importing the UML Realizations.
     */
    private void importRealizations() {
        NodeList list = element.getElementsByTagName("realization");
        for (int i = 0; i < list.getLength(); i++) {
            Element        current     = (Element) list.item(i);
            RealizationUML realization = new RealizationUML(current);
                           realization.setSource((ClassUML) getElement(current.getAttribute("class")));
                           realization.setTarget((InterfaceUML) getElement(current.getAttribute("interface")));
                           addPoints(current, realization);
            getDiagram().addRealizationUML(realization);
        }
    }
    
    /**
     * Method responsible for importing the Abstractions.
     */
    private void importAbstractions() {
        NodeList list = element.getElementsByTagName("abstraction");
        for (int i = 0; i < list.getLength(); i++) {
            Element     current     = (Element) list.item(i);
            Abstraction abstraction = new Abstraction(current);
                        abstraction.setSource(getElement(current.getAttribute("source")));
                        abstraction.setTarget(getElement(current.getAttribute("target")));
                        addPoints(current, abstraction);
            getDiagram().addAbstraction(abstraction);
        }
    }
    
    /**
     * Method responsible for importing the Usages.
     */
    private void importUsages() {
        NodeList list = element.getElementsByTagName("usage");
        for (int i = 0; i < list.getLength(); i++) {
            Element current = (Element) list.item(i);
            Usage   usage   = new Usage(current);
                    usage.setSource(getElement(current.getAttribute("source")));
                    usage.setTarget(getElement(current.getAttribute("target")));
                    addPoints(current, usage);
            getDiagram().addUsage(usage);
        }
    }
    
    /**
     * Method responsible for importing the UML Associations.
     */
    private void importAssociationsUML() {
        NodeList list = element.getElementsByTagName("association");
        for (int i = 0; i < list.getLength(); i++) {
            Element        current     = (Element) list.item(i);
            AssociationUML association = new AssociationUML(current);
                setSource(current, association);
                setTarget(current, association);
                addPoints(current, association);
            getDiagram().addAssociationUML(association);
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
                association.setSource(getElement(item.getAttribute("entity")));
    }
    
    /**
     * Method responsible for setting the Target Association.
     * @param node W3C Element.
     * @param association Association UML.
     */
    private void setTarget(Element node, AssociationUML association) {
        Element item = (Element) node.getElementsByTagName("target").item(0);
                association.setTarget(item);
                association.setTarget(getElement(item.getAttribute("entity")));
    }
    
    /**
     * Method responsible for importing the References.
     */
    protected void importReferences() {
        NodeList list =  element.getElementsByTagName("reference");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current  = (Element)    list.item(i);
            PackageUML package_ = (PackageUML) getElement(current.getAttribute("package"));
            PackageUML parent   = (PackageUML) getElement(current.getAttribute("parent"));
                       package_.setParent(parent);
                       parent.addPackage(package_);
        }
    }
    
    @Override
    protected ClassDiagram getDiagram() {
        return (ClassDiagram) diagram;
    }
}
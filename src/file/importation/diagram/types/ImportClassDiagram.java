package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Diagram;
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
 * @since  03/06/2019
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.ClassDiagram
 */
public class ImportClassDiagram extends ImportDiagram {
    private final Project project;
    private final ClassDiagram classDiagram;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportClassDiagram(Project project, Element element) {
        this.project      = project;
        this.classDiagram = new ClassDiagram(project, element);
        this.diagram      = this.classDiagram;
        this.element      = element;
    }
    
    @Override
    public Diagram importDiagram() {
                this.importPackages();
                this.importClasses();
                this.importInterfaces();
                this.importRealizations();
                this.importAssociations();
                this.importReferences();
               super.importRelationships();
               super.importVariabilities();
        return  this.diagram;
    }
    
    /**
     * Method responsible for importing the Packages.
     */
    private void importPackages() {
        NodeList packages = this.element.getElementsByTagName("package");
        for (int i = 0; i < packages.getLength(); i++) {
            Element    current    = (Element) packages.item(i);
            PackageUML packageUML = new PackageUML(current);
            this.classDiagram.addPackage(packageUML);
        }
    }
    
    /**
     * Method responsible for updating the Parent Entity.
     * @param parent Parent Id.
     * @param entity Entity.
     */
    private void updateParent(String parent, Entity entity) {
        if (this.diagram.getElement(parent) instanceof PackageUML) {
            PackageUML packageUML = (PackageUML) this.diagram.getElement(parent);
                       packageUML.addEntity(entity);
                       entity.setPackageUML(packageUML);
        }
    }
    
    /**
     * Method responsible for importing the Classes.
     */
    private void importClasses() {
        NodeList classes = this.element.getElementsByTagName("class");
        for (int i = 0; i < classes.getLength(); i++) {
            Element  current  = (Element) classes.item(i);
            ClassUML classUML = new ClassUML(current);
                     classUML.setTypeUML(this.project.getEntityType(classUML));
                this.importAttributes(current, classUML);
                this.importMethods(current, classUML);
            this.classDiagram.addClass(classUML);
            this.updateParent(current.getAttribute("parent"), classUML);
        }
    }
    
    /**
     * Method responsible for importing the Interfaces.
     */
    private void importInterfaces() {
        NodeList interfaces = this.element.getElementsByTagName("interface");
        for (int i = 0; i < interfaces.getLength(); i++) {
            Element      current      = (Element) interfaces.item(i);
            InterfaceUML interfaceUML = new InterfaceUML(current);
                         interfaceUML.setTypeUML(this.project.getEntityType(interfaceUML));
                this.importAttributes(current, interfaceUML);
                this.importMethods(current, interfaceUML);
            this.classDiagram.addInterface(interfaceUML);
            this.updateParent(current.getAttribute("parent"), interfaceUML);
        }
    }
    
    /**
     * Method responsible for importing the Attributes.
     * @param node W3C Element.
     * @param entity Entity.
     */
    private void importAttributes(Element node, Entity entity) {
        NodeList attributes = node.getElementsByTagName("attribute");
        for (int i = 0; i < attributes.getLength(); i++) {
            Element      current      = (Element) attributes.item(i);
            AttributeUML attributeUML = new AttributeUML(current);
                         attributeUML.setTypeUML(this.getType(current.getAttribute("type")));
                         attributeUML.setEntity(entity);
                         entity.addAttribute(attributeUML);
                         this.classDiagram.addAttribute(attributeUML);
        }
    }
    
    /**
     * Method responsible for importing the Methods.
     * @param node W3C Element.
     * @param entity Entity.
     */
    private void importMethods(Element node, Entity entity) {
        NodeList methods = node.getElementsByTagName("method");
        for (int i = 0; i < methods.getLength(); i++) {
            Element   current   = (Element) methods.item(i);
            MethodUML methodUML = new MethodUML(current);
                      methodUML.setEntity(entity);
                      methodUML.setReturn(this.getType(current.getAttribute("return")));
                      this.importParameters(current, methodUML);
                      entity.addMethod(methodUML);
                      this.classDiagram.addMethod(methodUML);
        }
    }
    
    /**
     * Method responsible for importing the Parameters.
     * @param node W3C Element.
     * @param method Method UML. 
     */
    private void importParameters(Element node, MethodUML method) {
        NodeList parameters = node.getElementsByTagName("parameter");
        for (int i = 0; i < parameters.getLength(); i++) {
            Element      current      = (Element) parameters.item(i);
            ParameterUML parameterUML = new ParameterUML(current);
                         parameterUML.setType(this.getType(current.getAttribute("type")));
                         method.addParameter(parameterUML);
        }
    }
    
    /**
     * Method responsible for returning a Type by Id.
     * @param  id Type Id.
     * @return Type found.
     */
    private TypeUML getType(String id) {
        if (this.diagram.getProject().getTypes().get(id) == null)
            return this.diagram.getObjectType();
        return (TypeUML) this.diagram.getProject().getTypes().get(id);
    }
    
    /**
     * Method responsible for importing the Realizations.
     */
    private void importRealizations() {
        NodeList realizations = this.element.getElementsByTagName("realization");
        for (int i = 0; i < realizations.getLength(); i++) {
            Element        current        = (Element) realizations.item(i);
            ClassUML       classUML       = (ClassUML)    this.diagram.getElement(current.getAttribute("class"));
            InterfaceUML   interfaceUML   = (InterfaceUML) this.diagram.getElement(current.getAttribute("interface"));
            RealizationUML realizationUML = new RealizationUML(classUML, interfaceUML);
                           realizationUML.setId(current.getAttribute("id"));
                           super.addPoints(current, realizationUML);
            this.classDiagram.addRealizationUML(realizationUML);
        }
    }
    
    /**
     * Method responsible for importing the Associations.
     */
    private void importAssociations() {
        NodeList associations = this.element.getElementsByTagName("association");
        for (int i = 0; i < associations.getLength(); i++) {
            Element        current        = (Element) associations.item(i);
            AssociationUML associationUML = new AssociationUML(current);
                           associationUML.setId(current.getAttribute("id"));
                           this.setSource(current, associationUML);
                           this.setTarget(current, associationUML);
                           super.addPoints(current, associationUML);
            this.classDiagram.addAssociationUML(associationUML);
        }
    }
    
    /**
     * Method responsible for setting the Source Association.
     * @param node W3C Element.
     * @param associationUML Association UML.
     */
    private void setSource(Element node, AssociationUML associationUML) {
        Element item = (Element) node.getElementsByTagName("source").item(0);
                associationUML.setSource(item);
                associationUML.setSource(this.diagram.getElement(item.getAttribute("entity")));
    }
    
    /**
     * Method responsible for setting the Target Association.
     * @param node W3C Element.
     * @param associationUML Association UML.
     */
    private void setTarget(Element node, AssociationUML associationUML) {
        Element item = (Element) node.getElementsByTagName("target").item(0);
                associationUML.setTarget(item);
                associationUML.setTarget(this.diagram.getElement(item.getAttribute("entity")));
    }
    
    /**
     * Method responsible for importing the References.
     */
    protected void importReferences() {
        NodeList references = this.element.getElementsByTagName("reference");
        for (int i = 0; i < references.getLength(); i++) {
            Element    current    = (Element)    references.item(i);
            PackageUML packageUML = (PackageUML) this.diagram.getElement(current.getAttribute("package"));
            PackageUML parent     = (PackageUML) this.diagram.getElement(current.getAttribute("parent"));
                       packageUML.setParent(parent);
                       parent.addPackage(packageUML);
        }
    }
}
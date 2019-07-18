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
        this.importClass();
        this.importInterfaces();
        this.importRealizations();
        this.importAssociations();
        this.importRelationships();
        super.importVariabilities();
        return this.diagram;
    }
    
    /**
     * Method responsible for importing the Class.
     */
    private void importClass() {
        NodeList classs = this.element.getElementsByTagName("class");
        for (int i = 0; i < classs.getLength(); i++) {
            Element  current = (Element) classs.item(i);
            ClassUML class_  = new ClassUML(current);
                     class_.setTypeUML(this.project.getEntityType(class_));
                this.importAttributes(current, class_);
                this.importMethods(current, class_);
            this.classDiagram.addClass(class_);
        }
    }
    
    /**
     * Method responsible for importing the Interfaces.
     */
    private void importInterfaces() {
        NodeList interfaces = this.element.getElementsByTagName("interface");
        for (int i = 0; i < interfaces.getLength(); i++) {
            Element      current    = (Element) interfaces.item(i);
            InterfaceUML interface_ = new InterfaceUML(current);
                         interface_.setTypeUML(this.project.getEntityType(interface_));
                this.importAttributes(current, interface_);
                this.importMethods(current, interface_);
            this.classDiagram.addInterface(interface_);
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
            Element      current   = (Element) attributes.item(i);
            AttributeUML attribute = new AttributeUML(current);
                         attribute.setTypeUML(this.getType(current.getAttribute("type")));
                         attribute.setEntity(entity);
                         entity.addAttribute(attribute);
                         this.classDiagram.addAttribute(attribute);
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
            Element   current = (Element) methods.item(i);
            MethodUML method  = new MethodUML(current);
                      method.setEntity(entity);
                      method.setReturn(this.getType(current.getAttribute("return")));
                      this.importParameters(current, method);
                      entity.addMethod(method);
                      this.classDiagram.addMethod(method);
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
            Element      current   = (Element) parameters.item(i);
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
            Element        current     = (Element) realizations.item(i);
            ClassUML       class_      = (ClassUML)    this.diagram.getElement(current.getAttribute("class"));
            InterfaceUML   interface_  = (InterfaceUML) this.diagram.getElement(current.getAttribute("interface"));
            RealizationUML realization = new RealizationUML(class_, interface_);
            this.classDiagram.addRealizationUML(realization);
        }
    }
    
    /**
     * Method responsible for importing the Associations.
     */
    protected void importAssociations() {
        NodeList associations = this.element.getElementsByTagName("association");
        for (int i = 0; i < associations.getLength(); i++) {
            Element        current     = (Element) associations.item(i);
            AssociationUML association = new AssociationUML(current);
                           association.setSource(this.diagram.getElement(current.getAttribute("source")));
                           association.setTarget(this.diagram.getElement(current.getAttribute("target")));
            this.classDiagram.addAssociationUML(association);
        }
    }
}
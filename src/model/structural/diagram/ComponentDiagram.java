package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.diagram.component.base.ComponentUML;
import model.structural.diagram.component.base.InterfaceUML;
import model.structural.diagram.component.base.association.ComunicationUML;

/**
 * <p>Class of Model <b>ComponentDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Component Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-07-20
 * @see    model.structural.base.Diagram
 * @see    model.structural.base.association.Association
 * @see    model.structural.diagram.component.base.ComponentUML
 * @see    model.structural.diagram.component.base.InterfaceUML
 * @see    model.structural.diagram.component.base.association.ComunicationUML
 */
public final class ComponentDiagram extends Diagram {
    private HashMap<String, ComponentUML> components;
    private HashMap<String, InterfaceUML> interfaces;
    private HashMap<String, Association>  comunications;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public ComponentDiagram(Project project) {
        super(project);
        init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ComponentDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        init();
    }
    
    @Override
    public void init() {
        type          = "Component";
        components    = new HashMap<>();
        interfaces    = new HashMap<>();
        comunications = new HashMap<>();
    }
    
    /**
     * Method responsible for adding a Component UML.
     * @param componentUML Component UML.
     */
    public void addComponent(ComponentUML componentUML) {
        componentUML.setId(nextId(componentUML));
        if (components.get(componentUML.getId()) == null) {
            components.put(componentUML.getId(), componentUML);
            addElement(componentUML);
        }
    }
    
    /**
     * Method responsible for removing a Component UML.
     * @param componentUML Component UML.
     */
    public void removeComponent(ComponentUML componentUML) {
        removeAssociations(componentUML);
        removeElement(componentUML);
        components.remove(componentUML.getId());
    }
    
    /**
     * Method responsible for returning the Components List.
     * @return Components List.
     */
    public List<ComponentUML> getComponentsList() {
        return new ArrayList<>(components.values());
    }
    
    /**
     * Method responsible for adding a Interface UML.
     * @param interfaceUML Interface UML.
     */
    public void addInterface(InterfaceUML interfaceUML) {
        interfaceUML.setId(nextId(interfaceUML));
        if (interfaces.get(interfaceUML.getId()) == null) {
            interfaces.put(interfaceUML.getId(), interfaceUML);
            addElement(interfaceUML);
        }
    }
    
    /**
     * Method responsible for removing a Interface UML.
     * @param interfaceUML Interface UML.
     */
    public void removeInterface(InterfaceUML interfaceUML) {
        removeAssociations(interfaceUML);
        removeElement(interfaceUML);
        interfaces.remove(interfaceUML.getId());
    }
    
    /**
     * Method responsible for returning the Interfaces List.
     * @return Interfaces List.
     */
    public List<InterfaceUML> getInterfacesList() {
        return new ArrayList<>(interfaces.values());
    }
    
    /**
     * Method responsible for adding a Comunication UML.
     * @param comunicationUML Comunication UML.
     */
    public void addComunication(ComunicationUML comunicationUML) {
        comunicationUML.setId(nextId(comunicationUML));
        if (comunications.get(comunicationUML.getId()) == null) {
            comunications.put(comunicationUML.getId(), comunicationUML);
            addAssociation(comunicationUML);
        }
    }
    
    /**
     * Method responsible for removing a Comunication UML.
     * @param comunicationUML Comunication UML.
     */
    public void removeComunication(ComunicationUML comunicationUML) {
        super.removeAssociation(comunicationUML);
        comunications.remove(comunicationUML.getId());
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        removeAssociation(element, comunications);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof ComunicationUML)
            removeComunication((ComunicationUML) association);
        else
            super.removeAssociation(association);
    }
    
    @Override
    public String getIcon() {
        return super.getFolder() + "component.png";
    }
    
    @Override
    public String getInstanceIcon() {
        return "icons/product/instance/instance-component.png";
    }
    
    @Override
    public ComponentDiagram getClone() {
        try {
            ComponentDiagram diagram = (ComponentDiagram) super.clone();
                             diagram.setElements(new HashMap<>(elements));
                             diagram.setAssociations(new HashMap<>(associations));
                             diagram.setVariabilities(new HashMap<>(variabilities));
            return           diagram;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }
}
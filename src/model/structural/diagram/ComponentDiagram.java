package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
 * @since  20/07/2019
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
        this.init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ComponentDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        this.init();
    }
    
    @Override
    public void init() {
        this.type          = "Component";
        this.components    = new HashMap<>();
        this.interfaces    = new HashMap<>();
        this.comunications = new HashMap<>();
    }
    
    /**
     * Method responsible for returning the next Component Id.
     * @return Next Component Id.
     */
    private String nextComponentId() {
        return this.nextId("COMPONENT#");
    }
    
    /**
     * Method responsible for adding a Component UML.
     * @param componentUML Component UML.
     */
    public void addComponent(ComponentUML componentUML) {
        componentUML.setId(this.nextComponentId());
        if (this.components.get(componentUML.getId()) == null) {
            this.components.put(componentUML.getId(), componentUML);
            this.addElement(componentUML);
        }
    }
    
    /**
     * Method responsible for removing a Component UML.
     * @param componentUML Component UML.
     */
    public void removeComponent(ComponentUML componentUML) {
        this.removeAssociations(componentUML);
        this.removeElement(componentUML);
        this.components.remove(componentUML.getId());
    }
    
    /**
     * Method responsible for returning the Components List.
     * @return Components List.
     */
    public List<ComponentUML> getComponents() {
        return new ArrayList<>(this.components.values());
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
     * @param interfaceUML Interface UML.
     */
    public void addInterface(InterfaceUML interfaceUML) {
        interfaceUML.setId(this.nextInterfaceId());
        if (this.interfaces.get(interfaceUML.getId()) == null) {
            this.interfaces.put(interfaceUML.getId(), interfaceUML);
            this.addElement(interfaceUML);
        }
    }
    
    /**
     * Method responsible for removing a Interface UML.
     * @param interfaceUML Interface UML.
     */
    public void removeInterface(InterfaceUML interfaceUML) {
        this.removeAssociations(interfaceUML);
        this.removeElement(interfaceUML);
        this.interfaces.remove(interfaceUML.getId());
    }
    
    /**
     * Method responsible for returning the Interfaces List.
     * @return Interfaces List.
     */
    public List<InterfaceUML> getInterfaces() {
        return new ArrayList<>(this.interfaces.values());
    }
    
    /**
     * Method responsible for adding a Comunication UML.
     * @param comunicationUML Comunication UML.
     */
    public void addComunication(ComunicationUML comunicationUML) {
        if (this.comunications.get(comunicationUML.getId()) == null) {
            this.comunications.put(comunicationUML.getId(), comunicationUML);
            this.addAssociation(comunicationUML);
        }
    }
    
    /**
     * Method responsible for removing a Comunication UML.
     * @param comunicationUML Comunication UML.
     */
    public void removeComunication(ComunicationUML comunicationUML) {
        this.removeAssociation(comunicationUML);
        this.associations.remove(comunicationUML.getId());
    }
    
    /**
     * Method responsible for removing the Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        this.removeAssociation(element, this.comunications);
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
    
    @Override
    public String getIcon() {
        return "diagram/component";
    }
    
    @Override
    public ComponentDiagram getClone() {
        try {
            ComponentDiagram diagram = (ComponentDiagram) super.clone();
                             diagram.setElements(new HashMap<>(this.elements));
                             diagram.setAssociations(new HashMap<>(this.associations));
                             diagram.setVariabilities(new HashMap<>(this.variabilities));
            return           diagram;
        } catch (CloneNotSupportedException exception) {
            System.out.println("Error");
            return null;
        }
    }
    
    @Override
    public String toString() {
        String diagram  = "Id            = " + this.id            + "\n";
               diagram += "Components    = " + this.components    + "\n";
               diagram += "Interfaces    = " + this.interfaces    + "\n";
               diagram += "Comunications = " + this.comunications + "\n";
        return diagram;
    }
}
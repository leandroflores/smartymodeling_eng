package model.structural.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.structural.base.association.Association;
import model.structural.base.Diagram;
import model.structural.base.Element;
import model.structural.base.Project;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.association.ExtendUML;
import model.structural.diagram.usecase.base.association.IncludeUML;
import model.structural.diagram.usecase.base.association.CommunicationUML;
import model.structural.diagram.usecase.base.UseCaseUML;

/**
 * <p>Class of Model <b>UseCaseDiagram</b>.</p>
 * <p>Class responsible for representing the <b>Use Case Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-22
 * @see    model.structural.base.association.Association
 * @see    model.structural.base.Diagram
 * @see    model.structural.diagram.usecase.base.ActorUML
 * @see    model.structural.diagram.usecase.base.UseCaseUML
 */
public final class UseCaseDiagram extends Diagram {
    private HashMap<String, ActorUML>    actorsUML;
    private HashMap<String, UseCaseUML>  useCasesUML;
    private HashMap<String, Association> communicationsUML;
    private HashMap<String, Association> extendsUML;
    private HashMap<String, Association> includesUML;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public UseCaseDiagram(Project project) {
        super(project);
        init();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public UseCaseDiagram(Project project, org.w3c.dom.Element element) {
        super(project, element);
        init();
    }
    
    @Override
    public void init() {
        type              = "UseCase";
        actorsUML         = new HashMap<>();
        useCasesUML       = new HashMap<>();
        communicationsUML = new HashMap<>();
        extendsUML        = new HashMap<>();
        includesUML       = new HashMap<>();
    }
    
    /**
     * Method responsible for adding a Actor UML.
     * @param actorUML Actor UML.
     */
    public void addActor(ActorUML actorUML) {
        actorUML.setId(nextId(actorUML));
        if (actorsUML.get(actorUML.getId()) == null) {
            actorsUML.put(actorUML.getId(), actorUML);
            addElement(actorUML);
        }
    }
    
    /**
     * Method responsible for removing a Actor UML.
     * @param actorUML Actor UML.
     */
    public void removeActor(ActorUML actorUML) {
        project.reset(actorUML);
        removeAssociations(actorUML);
        removeElement(actorUML);
        actorsUML.remove(actorUML.getId());
    }
    
    /**
     * Method responsible for returning Actors List.
     * @return Actors List.
     */
    public List<ActorUML> getActorsList() {
        return new ArrayList<>(actorsUML.values());
    }
    
    /**
     * Method responsible for adding a Use Case UML.
     * @param useCaseUML Use Case UML.
     */
    public void addUseCase(UseCaseUML useCaseUML) {
        useCaseUML.setId(nextId(useCaseUML));
        if (useCasesUML.get(useCaseUML.getId()) == null) {
            useCasesUML.put(useCaseUML.getId(), useCaseUML);
            addElement(useCaseUML);
        }
    }
    
    /**
     * Method responsible for removing a Use Case UML.
     * @param useCaseUML Use Case UML.
     */
    public void removeUseCase(UseCaseUML useCaseUML) {
        removeAssociations(useCaseUML);
        removeElement(useCaseUML);
        useCasesUML.remove(useCaseUML.getId());
    }
    
    /**
     * Method responsible for returning Use Cases List.
     * @return Use Cases List.
     */
    public List<UseCaseUML> getUseCasesList() {
        return new ArrayList<>(useCasesUML.values());
    }
    
    /**
     * Method responsible for adding a Communication UML.
     * @param communicationUML Communication UML.
     */
    public void addCommunication(CommunicationUML communicationUML) {
        communicationUML.setId(nextId(communicationUML));
        if (communicationsUML.get(communicationUML.getId()) == null) {
            communicationsUML.put(communicationUML.getId(), communicationUML);
            addAssociation(communicationUML);
        }
    }
    
    /**
     * Method responsible for removing a Communication UML.
     * @param communicationUML Communication UML.
     */
    public void removeCommunication(CommunicationUML communicationUML) {
        super.removeAssociation(communicationUML);
        communicationsUML.remove(communicationUML.getId());
    }
    
    /**
     * Method responsible for adding a Extend UML.
     * @param extendUML Extend UML.
     */
    public void addExtend(ExtendUML extendUML) {
        extendUML.setId(nextId(extendUML));
        if (extendsUML.get(extendUML.getId()) == null) {
            extendsUML.put(extendUML.getId(), extendUML);
            addAssociation(extendUML);
        }
    }
    
    /**
     * Method responsible for removing a Extend UML.
     * @param extendUML Extend UML.
     */
    public void removeExtend(ExtendUML extendUML) {
        super.removeAssociation(extendUML);
        extendsUML.remove(extendUML.getId());
    }
    
    /**
     * Method responsible for adding a Include UML.
     * @param includeUML Include UML.
     */
    public void addInclude(IncludeUML includeUML) {
        includeUML.setId(nextId(includeUML));
        if (includesUML.get(includeUML.getId()) == null) {
            includesUML.put(includeUML.getId(), includeUML);
            addAssociation(includeUML);
        }
    }
    
    /**
     * Method responsible for removing a Include UML.
     * @param includeUML Include UML.
     */
    public void removeInclude(IncludeUML includeUML) {
        super.removeAssociation(includeUML);
        includesUML.remove(includeUML.getId());
    }
    
    /**
     * Method responsible for removing Associations by Element.
     * @param element Element.
     */
    private void removeAssociations(Element element) {
        removeAssociation(element, communicationsUML);
        removeAssociation(element, extendsUML);
        removeAssociation(element, includesUML);
    }
    
    @Override
    public void removeAssociation(Association association) {
        if (association instanceof CommunicationUML)
            removeCommunication((CommunicationUML) association);
        else if (association instanceof ExtendUML)
            removeExtend((ExtendUML) association);
        else if (association instanceof IncludeUML)
            removeInclude((IncludeUML) association);
        else
            super.removeAssociation(association);
    }
    
    @Override
    public String getIcon() {
        return getFolder() + "use-case.png";
    }
    
    @Override
    public String getInstanceIcon() {
        return "icons/product/instance/instance-use-case.png";
    }
    
    @Override
    public UseCaseDiagram getClone() {
        try {
            UseCaseDiagram diagram = (UseCaseDiagram) clone();
                           diagram.setElements(new HashMap<>(elements));
                           diagram.setAssociations(new HashMap<>(associations));
                           diagram.setVariabilities(new HashMap<>(variabilities));
            return         diagram;
        } catch (CloneNotSupportedException exception) {
            return null;
        }
    }
}
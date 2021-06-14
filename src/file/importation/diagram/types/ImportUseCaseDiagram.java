package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Project;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.association.ExtendUML;
import model.structural.diagram.usecase.base.association.IncludeUML;
import model.structural.diagram.usecase.base.association.CommunicationUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of File <b>ImportUseCaseDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Use Case Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.UseCaseDiagram
 */
public class ImportUseCaseDiagram extends ImportDiagram {
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportUseCaseDiagram(Project project, Element element) {
        this.diagram = new UseCaseDiagram(project, element);
        this.element = element;
    }
    
    @Override
    protected void importElements() {
        importActors();
        importUseCases();
    }
    
    /**
     * Method responsible for importing UML Actors.
     */
    private void importActors() {
        NodeList list = element.getElementsByTagName("actor");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addActor(new ActorUML((Element) list.item(i), getDiagram()));
    }
    
    /**
     * Method responsible for importing UML Use Cases.
     */
    private void importUseCases() {
        NodeList list = element.getElementsByTagName("useCase");
        for (int i = 0; i < list.getLength(); i++)
            getDiagram().addUseCase(new UseCaseUML((Element) list.item(i), getDiagram()));
    }
    
    @Override
    protected void importAssociations() {
        importCommunications();
        importExtends();
        importIncludes();
    }
    
    /**
     * Method responsible for importing the Communications.
     */
    private void importCommunications() {
        NodeList list = element.getElementsByTagName("communication");
        for (int i = 0; i < list.getLength(); i++) {
            Element          current       = (Element) list.item(i);
            CommunicationUML communication = new CommunicationUML(current);
                             communication.setSource((ActorUML) getElement(current.getAttribute("actor")));
                             communication.setTarget((UseCaseUML) getElement(current.getAttribute("useCase")));
                addPoints(current, communication);
            getDiagram().addCommunication(communication);
        }
    }
    
    /**
     * Method responsible for importing the Extends.
     */
    private void importExtends() {
        NodeList list = element.getElementsByTagName("extend");
        for (int i = 0; i < list.getLength(); i++) {
            Element   current = (Element) list.item(i);
            ExtendUML extend  = new ExtendUML(current);
                      extend.setSource((UseCaseUML) getElement(current.getAttribute("source")));
                      extend.setTarget((UseCaseUML) getElement(current.getAttribute("target")));
                addPoints(current, extend);
            getDiagram().addExtend(extend);
        }
    }
    
    /**
     * Method responsible for importing the Includes.
     */
    private void importIncludes() {
        NodeList list = element.getElementsByTagName("include");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current = (Element) list.item(i);
            IncludeUML include = new IncludeUML(current);
                       include.setSource((UseCaseUML) getElement(current.getAttribute("source")));
                       include.setTarget((UseCaseUML) getElement(current.getAttribute("target")));
                addPoints(current, include);
            getDiagram().addInclude(include);
        }
    }
    
    @Override
    protected UseCaseDiagram getDiagram() {
        return (UseCaseDiagram) diagram;
    }
}
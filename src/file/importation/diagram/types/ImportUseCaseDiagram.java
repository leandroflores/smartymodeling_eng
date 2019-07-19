package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.diagram.UseCaseDiagram;
import model.structural.diagram.usecase.base.ActorUML;
import model.structural.diagram.usecase.base.association.ExtendUML;
import model.structural.diagram.usecase.base.association.IncludeUML;
import model.structural.diagram.usecase.base.association.RealizationUML;
import model.structural.diagram.usecase.base.UseCaseUML;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportUseCaseDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Use Case Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.UseCaseDiagram
 */
public class ImportUseCaseDiagram extends ImportDiagram {
    private final UseCaseDiagram useCaseDiagram;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportUseCaseDiagram(Project project, Element element) {
        this.useCaseDiagram = new UseCaseDiagram(project, element);
        this.diagram        = this.useCaseDiagram;
        this.element        = element;
    }
    
    @Override
    public Diagram importDiagram() {
                this.importActors();
                this.importUseCases();
                this.importRealizations();
                this.importExtends();
                this.importIncludes();
               super.importRelationships();
               super.importVariabilities();
        return  this.diagram;
    }
    
    /**
     * Method responsible for importing Actors.
     */
    private void importActors() {
        NodeList actors = this.element.getElementsByTagName("actor");
        for (int i = 0; i < actors.getLength(); i++)
            this.useCaseDiagram.addActor(new ActorUML((Element) actors.item(i)));
    }
    
    /**
     * Method responsible for importing Use Cases.
     */
    private void importUseCases() {
        NodeList useCases = this.element.getElementsByTagName("useCase");
        for (int i = 0; i < useCases.getLength(); i++)
            this.useCaseDiagram.addUseCase(new UseCaseUML((Element) useCases.item(i)));
    }
    
    /**
     * Method responsible for importing Realizations.
     */
    private void importRealizations() {
        NodeList realizations = this.element.getElementsByTagName("realization");
        for (int i = 0; i < realizations.getLength(); i++) {
            Element        current     = (Element) realizations.item(i);
            ActorUML       actor       = (ActorUML)   this.diagram.getElement(current.getAttribute("actor"));
            UseCaseUML     useCase     = (UseCaseUML) this.diagram.getElement(current.getAttribute("useCase"));
            RealizationUML realization = new RealizationUML(actor, useCase);
            this.useCaseDiagram.addRealization(realization);
        }
    }
    
    /**
     * Method responsible for importing Extends.
     */
    private void importExtends() {
        NodeList extends_ = this.element.getElementsByTagName("extend");
        for (int i = 0; i < extends_.getLength(); i++) {
            Element    current = (Element) extends_.item(i);
            UseCaseUML source  = (UseCaseUML) this.diagram.getElement(current.getAttribute("source"));
            UseCaseUML target  = (UseCaseUML) this.diagram.getElement(current.getAttribute("target"));
            ExtendUML  extend  = new ExtendUML(source, target);
            this.useCaseDiagram.addExtend(extend);
        }
    }
    
    /**
     * Method responsible for importing Includes.
     */
    private void importIncludes() {
        NodeList includes = this.element.getElementsByTagName("include");
        for (int i = 0; i < includes.getLength(); i++) {
            Element    current = (Element) includes.item(i);
            UseCaseUML source  = (UseCaseUML) this.diagram.getElement(current.getAttribute("source"));
            UseCaseUML target  = (UseCaseUML) this.diagram.getElement(current.getAttribute("target"));
            IncludeUML include = new IncludeUML(source, target);
            this.useCaseDiagram.addInclude(include);
        }
    }
}
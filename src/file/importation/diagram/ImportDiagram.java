package file.importation.diagram;

import model.structural.base.Diagram;
import model.structural.base.association.Dependency;
import model.structural.base.association.Generalization;
import model.structural.base.variability.Mutex;
import model.structural.base.variability.Requires;
import model.structural.base.variability.Variability;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    model.structural.base.Diagram
 * @see    org.w3c.dom.Element
 */
public abstract class ImportDiagram {
    protected Diagram diagram;
    protected Element element;
    
    /**
     * Method responsible for imporing the Diagram.
     * @return Diagram.
     */
    public abstract Diagram importDiagram();
    
    /**
     * Method responsible for importing the Variabilities.
     */
    protected void importVariabilities() {
        this.addVariabilities();
        this.addMutex();
        this.addRequires();
    }
    
    /**
     * Method responsible for importing the Relationships.
     */
    protected void importRelationships() {
        this.addGeneralizations();
        this.addDependencies();
    }
    
    /**
     * Method responsible for adding the Variabilities.
     */
    protected void addVariabilities() {
        NodeList variabilities = this.element.getElementsByTagName("variability");
        for (int i = 0; i < variabilities.getLength(); i++) {
            Element     current     = (Element) variabilities.item(i);
            Variability variability = new Variability(current);
                        variability.setVariationPoint(this.diagram.getElement(current.getAttribute("variationPoint")));
            this.diagram.addVariability(variability);
            NodeList    variants    = current.getElementsByTagName("variant");
            for (int x = 0; x < variants.getLength(); x++)
                variability.addVariant(this.diagram.getElement(((Element) variants.item(x)).getAttribute("id")));
        }
    }
    
    /**
     * Method responsible for adding Mutex.
     */
    protected void addMutex() {
        NodeList list = this.element.getElementsByTagName("mutex");
        for (int i = 0; i < list.getLength(); i++) {
            Element current = (Element) list.item(i);
            Mutex   mutex   = new Mutex(this.diagram.getElement(current.getAttribute("source")), this.diagram.getElement(current.getAttribute("target")));
            this.diagram.addAssociation(mutex);
        }
    }
    
    /**
     * Method responsible for adding Requires.
     */
    protected void addRequires() {
        NodeList list = this.element.getElementsByTagName("requires");
        for (int i = 0; i < list.getLength(); i++) {
            Element  current  = (Element) list.item(i);
            Requires requires = new Requires(this.diagram.getElement(current.getAttribute("source")), this.diagram.getElement(current.getAttribute("target")));
            this.diagram.addAssociation(requires);
        }
    }
    
    /**
     * Method responsible for adding Generalization.
     */
    protected void addGeneralizations() {
        NodeList list = this.element.getElementsByTagName("generalization");
        for (int i = 0; i < list.getLength(); i++) {
            Element        current        = (Element) list.item(i);
            Generalization generalization = new Generalization(this.diagram.getElement(current.getAttribute("source")), this.diagram.getElement(current.getAttribute("target")));
            this.diagram.addAssociation(generalization);
        }
    }
    
    /**
     * Method responsible for adding Dependency.
     */
    protected void addDependencies() {
        NodeList list = this.element.getElementsByTagName("dependency");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current    = (Element) list.item(i);
            Dependency dependency = new Dependency(this.diagram.getElement(current.getAttribute("source")), this.diagram.getElement(current.getAttribute("target")));
            this.diagram.addAssociation(dependency);
        }
    }
}
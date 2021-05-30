package file.importation.diagram;

import com.mxgraph.util.mxPoint;
import model.structural.base.Diagram;
import model.structural.base.Project;
import model.structural.base.association.Association;
import model.structural.base.association.Dependency;
import model.structural.base.association.Generalization;
import model.structural.base.variability.Mutex;
import model.structural.base.variability.Requires;
import model.structural.base.variability.Variability;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of File <b>ImportDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Diagram</b> in SMartyModeling.</p>
 * @author Leandro 
 * @since  2019-05-23
 * @see    model.structural.base.Diagram
 * @see    org.w3c.dom.Element
 */
public abstract class ImportDiagram {
    protected Diagram diagram;
    protected Element element;
    
    /**
     * Method responsible for importing the Diagram.
     * @return Diagram.
     */
    public Diagram importDiagram() {
               importElements();
               importAssociations();
               importRelationships();
               importVariabilities();
        return diagram;
    }
    
    /**
     * Method responsible for importing the Diagram Elements.
     */
    protected abstract void importElements();
    
    /**
     * Method responsible for importing the Diagram Associations.
     */
    protected abstract void importAssociations();
    
    /**
     * Method responsible for importing the Variabilities.
     */
    protected void importVariabilities() {
        addVariabilities();
        addMutex();
        addRequires();
    }
    
    /**
     * Method responsible for importing the Relationships.
     */
    protected void importRelationships() {
        addGeneralizations();
        addDependencies();
    }
    
    /**
     * Method responsible for adding the Variabilities.
     */
    protected void addVariabilities() {
        NodeList variabilities = element.getElementsByTagName("variability");
        for (int i = 0; i < variabilities.getLength(); i++) {
            Element     current_    = (Element) variabilities.item(i);
            Variability variability = new Variability(current_);
                        variability.setVariationPoint(diagram.getElement(current_.getAttribute("variationPoint")));
                        addVariants(variability, current_);
            diagram.addVariability(variability);
        }
    }
    
    /**
     * Method responsible for adding the Variability Variants.
     * @param variability Variability.
     * @param node W3C Element.
     */
    protected void addVariants(Variability variability, Element node) {
        NodeList variants = node.getElementsByTagName("variant");
        for (int i = 0; i < variants.getLength(); i++)
            variability.addVariant(diagram.getElement(((Element) variants.item(i)).getAttribute("id")));
    }
    
    /**
     * Method responsible for adding the Association Points.
     * @param node W3C Element.
     * @param association Association.
     */
    protected void addPoints(Element node, Association association) {
        NodeList points = node.getElementsByTagName("point");
        for (int i = 0; i < points.getLength(); i++)
            association.addPoint(getPoint((Element) points.item(i)));
    }
    
    /**
     * Method responsible for returning the Point by W3C Element.
     * @param  node W3C Element.
     * @return Point.
     */
    protected mxPoint getPoint(Element node) {
        return new mxPoint(Double.parseDouble(node.getAttribute("x").trim()), 
                           Double.parseDouble(node.getAttribute("y").trim()));
    }
    
    /**
     * Method responsible for adding Mutex.
     */
    protected void addMutex() {
        NodeList list = element.getElementsByTagName("mutex");
        for (int i = 0; i < list.getLength(); i++) {
            Element current = (Element) list.item(i);
            Mutex   mutex   = new Mutex(diagram.getElement(current.getAttribute("source")),
                                        diagram.getElement(current.getAttribute("target")));
                    mutex.setId(current.getAttribute("id"));
                    addPoints(current, mutex);
            diagram.addAssociation(mutex);
        }
    }
    
    /**
     * Method responsible for adding Requires.
     */
    protected void addRequires() {
        NodeList list = element.getElementsByTagName("requires");
        for (int i = 0; i < list.getLength(); i++) {
            Element  current  = (Element) list.item(i);
            Requires requires = new Requires(diagram.getElement(current.getAttribute("source")), 
                                             diagram.getElement(current.getAttribute("target")));
                     requires.setId(current.getAttribute("id"));
                     addPoints(current, requires);
            diagram.addAssociation(requires);
        }
    }
    
    /**
     * Method responsible for adding Generalization.
     */
    protected void addGeneralizations() {
        NodeList list = element.getElementsByTagName("generalization");
        for (int i = 0; i < list.getLength(); i++) {
            Element        current = (Element) list.item(i);
            Generalization general = new Generalization(diagram.getElement(current.getAttribute("source")), 
                                                        diagram.getElement(current.getAttribute("target")));
                           general.setId(current.getAttribute("id"));
                           addPoints(current, general);
            diagram.addAssociation(general);
        }
    }
    
    /**
     * Method responsible for adding Dependency.
     */
    protected void addDependencies() {
        NodeList list = element.getElementsByTagName("dependency");
        for (int i = 0; i < list.getLength(); i++) {
            Element    current    = (Element) list.item(i);
            Dependency dependency = new Dependency(diagram.getElement(current.getAttribute("source")), 
                                                   diagram.getElement(current.getAttribute("target")));
                       dependency.setId(current.getAttribute("id"));
                       addPoints(current, dependency);
            diagram.addAssociation(dependency);
        }
    }
    
    /**
     * Method responsible for returning the Element by Id.
     * @param  id Element Id.
     * @return Element by Id.
     */
    protected model.structural.base.Element getElement(String id) {
        return diagram.getElement(id);
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    protected Diagram getDiagram() {
        return diagram;
    }
    
    /**
     * Method responsible for returning the Project.
     * @return Project.
     */
    protected Project getProject() {
        return diagram.getProject();
    }
}
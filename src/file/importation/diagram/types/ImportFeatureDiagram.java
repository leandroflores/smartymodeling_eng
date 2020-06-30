package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Project;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.Variability;
import model.structural.diagram.feature.base.association.Combination;
import model.structural.diagram.feature.base.association.Connection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportFeatureDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Feature Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-23
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.FeatureDiagram
 */
public class ImportFeatureDiagram extends ImportDiagram {

    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportFeatureDiagram(Project project, Element element) {
        this.diagram = new FeatureDiagram(project, element);
        this.element = element;
    }
    
    @Override
    protected void importElements() {
        this.importFeatures();
        this.importVariabilities();
    }

    /**
     * Method responsible for importing the Features.
     */
    private void importFeatures() {
        NodeList list = this.element.getElementsByTagName("feature");
        for (int i = 0; i < list.getLength(); i++)
            this.getDiagram().addFeature(new Feature((Element) list.item(i), this.getDiagram()));
    }
    
    @Override
    protected void addVariabilities() {
        NodeList variabilities = this.element.getElementsByTagName("variability");
        for (int i = 0; i < variabilities.getLength(); i++) {
            Element     current     = (Element) variabilities.item(i);
            Variability variability = new Variability(current, this.diagram);
                        variability.setVariationPoint((Feature) this.diagram.getElement(current.getAttribute("variationPoint")));
                        this.addVariants(variability, current);
            this.getDiagram().addVariability(variability);
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
            variability.addVariant((Feature) this.diagram.getElement(((Element) variants.item(i)).getAttribute("id")));
    }
    
    @Override
    protected void importAssociations() {
        this.importConnections();
        this.importCombinations();
    }
    
    /**
     * Method responsible for importing the Connections.
     */
    private void importConnections() {
        NodeList connections = this.element.getElementsByTagName("connection");
        for (int i = 0; i < connections.getLength(); i++) {
            Element    current    = (Element) connections.item(i);
            Connection connection = new Connection(current);
                       connection.setSource(this.getElement(current.getAttribute("source")));
                       connection.setTarget(this.getElement(current.getAttribute("target")));
                super.addPoints(current, connection);
            this.getDiagram().addConnection(connection);
        }
    }
    
    /**
     * Method responsible for importing the Combinations.
     */
    private void importCombinations() {
        NodeList combinations = this.element.getElementsByTagName("combination");
        for (int i = 0; i < combinations.getLength(); i++) {
            Element     current     = (Element) combinations.item(i);
            Combination combination = new Combination(current);
                        combination.setSource(this.getElement(current.getAttribute("source")));
                        combination.setTarget(this.getElement(current.getAttribute("target")));
                super.addPoints(current, combination);
            this.getDiagram().addCombination(combination);
        }
    }
    
    @Override
    protected FeatureDiagram getDiagram() {
        return (FeatureDiagram) this.diagram;
    }
}
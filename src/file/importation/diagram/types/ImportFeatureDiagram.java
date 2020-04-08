package file.importation.diagram.types;

import file.importation.diagram.ImportDiagram;
import model.structural.base.Project;
import model.structural.diagram.FeatureDiagram;
import model.structural.diagram.feature.base.Feature;
import model.structural.diagram.feature.base.association.Connection;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * <p>Class of Import <b>ImportFeatureDiagram</b>.</p>
 * <p>Class responsible for <b>Importing Feature Diagram</b> in SMartyModeling.</p>
 * @author Leandro
 * @since  23/05/2019
 * @see    file.importation.diagram.ImportDiagram
 * @see    model.structural.diagram.FeatureDiagram
 */
public class ImportFeatureDiagram extends ImportDiagram {
    private final FeatureDiagram featureDiagram;

    /**
     * Default constructor method of Class.
     * @param project Project.
     * @param element W3C Element.
     */
    public ImportFeatureDiagram(Project project, Element element) {
        this.featureDiagram = new FeatureDiagram(project, element);
        this.diagram        = this.featureDiagram;
        this.element        = element;
    }
    
    @Override
    protected void importElements() {
        this.importFeatures();
    }

    /**
     * Method responsible for importing Use Cases.
     */
    private void importFeatures() {
        NodeList features = this.element.getElementsByTagName("feature");
        for (int i = 0; i < features.getLength(); i++)
            this.featureDiagram.addFeature(new Feature((Element) features.item(i)));
    }
    
    @Override
    protected void importAssociations() {
        this.importConnections();
    }
    
    /**
     * Method responsible for importing the Connections.
     */
    private void importConnections() {
        NodeList connections = this.element.getElementsByTagName("connection");
        for (int i = 0; i < connections.getLength(); i++) {
            Element    current    = (Element) connections.item(i);
            Feature    source     = (Feature)   this.diagram.getElement(current.getAttribute("source"));
            Feature    target     = (Feature) this.diagram.getElement(current.getAttribute("target"));
            Connection connection = new Connection(source, target);
                       connection.setId(current.getAttribute("id"));
                       connection.setCategory(current.getAttribute("category"));
                       super.addPoints(current, connection);
            this.featureDiagram.addConnection(connection);
        }
    }
}
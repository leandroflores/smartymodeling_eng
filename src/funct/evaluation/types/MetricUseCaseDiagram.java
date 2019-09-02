package funct.evaluation.types;

import funct.evaluation.types.usecase.MetricActorUML;
import funct.evaluation.types.usecase.MetricUseCaseUML;
import model.structural.diagram.UseCaseDiagram;

/**
 * <p>Class of Metric <b>MetricUseCaseDiagram</b>.</p>
 * <p>Class responsible por extract the Metrics of <b>Use Case Diagram</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 * @see    funct.evaluation.types.usecase.MetricActorUML
 * @see    model.structural.diagram.UseCaseDiagram
 */
public class MetricUseCaseDiagram {
    private final UseCaseDiagram   diagram;
    private final MetricActorUML   metricActorUML;
    private final MetricUseCaseUML metricUseCaseUML;
    
    /**
     * Default constructor method of Class.
     * @param diagram Use Case Diagram.
     */
    public MetricUseCaseDiagram(UseCaseDiagram diagram) {
        this.diagram          = diagram;
        this.metricActorUML   = new MetricActorUML(diagram);
        this.metricUseCaseUML = new MetricUseCaseUML(diagram);
    }
    
    /**
     * Method responsible for returning the Actor Metric Value.
     * @param  parameters Parameters List.
     * @return Actor Metric Value.
     */
    public Double getActorMetric(Object[] parameters) {
        return this.metricActorUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Use Case Metric Value.
     * @param  parameters Parameters List.
     * @return Use Case Metric Value.
     */
    public Double getUseCaseMetric(Object[] parameters) {
        return this.metricUseCaseUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Diagram Values.
     * @return Diagram Values.
     */
    public String[] getValues() {
        String[] values    = new String[2];
                 values[0] = Integer.toString(this.diagram.getActorsList().size());
                 values[1] = Integer.toString(this.diagram.getUseCasesList().size());
        return   values;
    }
}
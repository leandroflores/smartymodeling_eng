package funct.evaluation.types;

import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.types.component.EvaluationComponentUML;
import funct.evaluation.types.component.EvaluationInterfaceUML;
import model.structural.diagram.ComponentDiagram;

/**
 * <p>Class of Evaluation <b>EvaluationComponentDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Component Diagram</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationDiagram
 * @see    funct.evaluation.types.component.EvaluationComponentUML
 * @see    funct.evaluation.types.component.EvaluationInterfaceUML
 * @see    model.structural.diagram.ComponentDiagram
 */
public class EvaluationComponentDiagram extends EvaluationDiagram {
    private final ComponentDiagram diagram;
    private final EvaluationComponentUML evaluationComponentUML;
    private final EvaluationInterfaceUML evaluationInterfaceUML;
    
    /**
     * Default constructor method of Class.
     * @param diagram Component Diagram.
     */
    public EvaluationComponentDiagram(ComponentDiagram diagram) {
        super(diagram);
        this.diagram = diagram;
        this.evaluationComponentUML = new EvaluationComponentUML(diagram);
        this.evaluationInterfaceUML = new EvaluationInterfaceUML(diagram);
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (keyword.toLowerCase().equals("component"))
            return this.getComponentMetric(this.getDefaultFilters(filter));
        else if (keyword.toLowerCase().equals("interface"))
            return this.getInterfaceMetric(this.getDefaultFilters(filter));
        return null;
    }
    
    /**
     * Method responsible for returning the Component Metric Value.
     * @param  parameters Parameters List.
     * @return Component Metric Value.
     */
    public Double getComponentMetric(Object[] parameters) {
               this.addObjects(this.evaluationComponentUML.filter(parameters));
        return this.evaluationComponentUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Interface Metric Value.
     * @param  parameters Parameters List.
     * @return Interface Metric Value.
     */
    public Double getInterfaceMetric(Object[] parameters) {
               this.addObjects(this.evaluationInterfaceUML.filter(parameters));
        return this.evaluationInterfaceUML.getMetricValue(parameters);
    }
    
    @Override
    public String[] getValues() {
        String[] values    = new String[2];
                 values[0] = Integer.toString(this.diagram.getComponentsList().size());
                 values[1] = Integer.toString(this.diagram.getInterfacesList().size());
        return   values;
    }
}
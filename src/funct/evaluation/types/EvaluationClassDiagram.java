package funct.evaluation.types;

import funct.evaluation.base.EvaluationDiagram;
import funct.evaluation.types.classes.EvaluationAttributeUML;
import funct.evaluation.types.classes.EvaluationClassUML;
import funct.evaluation.types.classes.EvaluationInterfaceUML;
import funct.evaluation.types.classes.EvaluationMethodUML;
import funct.evaluation.types.classes.EvaluationPackageUML;
import model.structural.diagram.ClassDiagram;

/**
 * <p>Class of Evaluation <b>EvaluationClassDiagram</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Class Diagram</b>.</p>
 * @author Leandro
 * @since  27/03/2020
 * @see    funct.evaluation.base.EvaluationDiagram
 * @see    funct.evaluation.types.classes.EvaluationAttributeUML
 * @see    funct.evaluation.types.classes.EvaluationClassUML
 * @see    funct.evaluation.types.classes.EvaluationInterfaceUML
 * @see    funct.evaluation.types.classes.EvaluationMethodUML
 * @see    funct.evaluation.types.classes.EvaluationPackageUML
 * @see    model.structural.diagram.ClassDiagram
 */
public class EvaluationClassDiagram extends EvaluationDiagram {
    private final ClassDiagram diagram;
    private final EvaluationPackageUML   evaluationPackageUML;
    private final EvaluationClassUML     evaluationClassUML;
    private final EvaluationInterfaceUML evaluationInterfaceUML;
    private final EvaluationAttributeUML evaluationAttributeUML;
    private final EvaluationMethodUML    evaluationMethodUML;
    
    /**
     * Default constructor method of Class.
     * @param diagram Class Diagram.
     */
    public EvaluationClassDiagram(ClassDiagram diagram) {
        super(diagram);
        this.diagram                = diagram;
        this.evaluationPackageUML   = new EvaluationPackageUML(diagram);
        this.evaluationClassUML     = new EvaluationClassUML(diagram);
        this.evaluationInterfaceUML = new EvaluationInterfaceUML(diagram);
        this.evaluationAttributeUML = new EvaluationAttributeUML(diagram);
        this.evaluationMethodUML    = new EvaluationMethodUML(diagram);
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        if (keyword.toLowerCase().equals("package"))
            return this.getPackageMetric(this.getDefaultFilters(filter));
        else if (keyword.toLowerCase().equals("class"))
            return this.getClassMetric(this.getDetailFilters(filter));
        else if (keyword.toLowerCase().equals("interface"))
            return this.getInterfaceMetric(this.getDefaultFilters(filter));
        else if (keyword.toLowerCase().equals("attribute"))
            return this.getAttributeMetric(this.getDetailFilters(filter));
        else if (keyword.toLowerCase().equals("method"))
            return this.getMethodMetric(this.getDetailFilters(filter));
        return null;
    }
    
    /**
     * Method responsible for returning the Attribute Filters.
     * @param  filter Clause Filter.
     * @return Attribute Filters.
     */
    private Object[] getDetailFilters(String filter) {
        Object[] filters    = new Object[7];
                 filters[0] = this.getNames(filter);
                     filter = this.clearNames(filter);
                 filters[1] = this.getStereotypes(filter);
                     filter = this.clearStereotypes(filter);
                 filters[2] = this.getMandatory(filter);
                 filters[3] = this.getAbstract(filter);
                 filters[4] = this.getFinal(filter);
                 filters[5] = this.getStatic(filter);
                 filters[6] = this.getVisibility(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Package Metric Value.
     * @param  parameters Parameters List.
     * @return Package Metric Value.
     */
    public Double getPackageMetric(Object[] parameters) {
               this.addObjects(this.evaluationPackageUML.filter(parameters));
        return this.evaluationPackageUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Class Metric Value.
     * @param  parameters Parameters List.
     * @return Class Metric Value.
     */
    public Double getClassMetric(Object[] parameters) {
               this.addObjects(this.evaluationClassUML.filter(parameters));
        return this.evaluationClassUML.getMetricValue(parameters);
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
    
    /**
     * Method responsible for returning the Attribute Metric Value.
     * @param  parameters Parameters List.
     * @return Attribute Metric Value.
     */
    public Double getAttributeMetric(Object[] parameters) {
               this.addObjects(this.evaluationAttributeUML.filter(parameters));
        return this.evaluationAttributeUML.getMetricValue(parameters);
    }
    
    /**
     * Method responsible for returning the Method Metric Value.
     * @param  parameters Parameters List.
     * @return Method Metric Value.
     */
    public Double getMethodMetric(Object[] parameters) {
               this.addObjects(this.evaluationMethodUML.filter(parameters));
        return this.evaluationMethodUML.getMetricValue(parameters);
    }
    
    @Override
    public String[] getValues() {
        String[] values    = new String[2];
                 values[0] = Integer.toString(this.diagram.getClassesList().size());
                 values[1] = Integer.toString(this.diagram.getInterfacesList().size());
        return   values;
    }
}
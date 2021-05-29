package funct.evaluation.base.product;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Project;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;

/**
 * <p>Class of Funct <b>EvaluationInstance</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Instance</b> in the SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-01
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Project
 * @see    model.structural.base.product.Instance
 * @see    model.structural.base.product.Product
 */
public class EvaluationInstance extends Evaluation {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public EvaluationInstance(Project project) {
        super(project);
        product = null;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param product_ Product.
     */
    public EvaluationInstance(Project project, Product product_) {
        super(project);
        product = product_;
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        List   list = filter(getArtifactFilters(filter));
        String size = Integer.toString(list.size());
        return Double.parseDouble(size);
    }
    
    /**
     * Method responsible for filtering the Instances by Parameters.
     * @param  parameters Parameters List.
     * @return Instances filtered.
     */
    public List filter(Object[] parameters) {
           List filter = filterContext();
                filter = filterType(filter, (List<String>) parameters[0]);
                filter = filterName(filter, (List<String>) parameters[1]);
                addObjects(getList(filter));
        return  filter;
    }
    
    /**
     * Method responsible for filtering the Instances List by Context.
     * @return Instances List by Context.
     */
    protected List<Instance> filterContext() {
        return product != null ? product.getInstancesList() : project.getInstancesList();
    }
    
    /**
     * Method responsible for filtering the Instances by Types List.
     * @param  list Instances List.
     * @param  types Types List.
     * @return Instances filtered by Types List.
     */
    protected List<Instance> filterType(List<Instance> list, List<String> types) {
        return isVoid(types) ? list : selectInstancesByTypes(list, types);
    }
    
    /**
     * Method responsible for selecting the Instances by Types List.
     * @param  list Instances List.
     * @param  types Types List.
     * @return Instances selected by Types List.
     */
    private List<Instance> selectInstancesByTypes(List<Instance> list, List<String> types) {
        List filter = new ArrayList<>();
        for (Instance instance : list) {
            if (types.contains(instance.getDiagram().getType()))
                filter.add(instance);
        }
        return  filter;
    }
    
    /**
     * Method responsible for filtering the Instances by Names List.
     * @param  list Instances List.
     * @param  names Names List.
     * @return Instances filtered by Names List.
     */
    protected List<Instance> filterName(List<Instance> list, List<String> names) {
        return isVoid(names) ? list : selectInstancesByNames(list, names);
    }
    
    /**
     * Method responsible for selecting the Instances by Names List.
     * @param  list Instances List.
     * @param  names Names List.
     * @return Instances selected by Names List.
     */
    private List<Instance> selectInstancesByNames(List<Instance> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Instance artifact : list) {
            if (names.contains(artifact.getName()))
                filter.add(artifact);
        }
        return  filter;
    }
    
    /**
     * Method responsible for returning the Summary List of Instances List.
     * @param  filter Instances List.
     * @return Summary List.
     */
    private List getList(List<Instance> filter) {
        List   list = new ArrayList();
        for (Instance instance : filter)
               list.add(instance.getSummary());
        return list;
    }
}
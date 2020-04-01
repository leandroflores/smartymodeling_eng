package funct.evaluation.base.types;

import funct.evaluation.Evaluation;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Project;
import model.structural.base.product.Artifact;
import model.structural.base.product.Product;

/**
 * <p>Class of Evaluation <b>EvaluationArtifact</b>.</p>
 * <p>Class responsible for <b>Evaluate</b> the <b>Artifacts</b>.</p>
 * @author Leandro
 * @since  01/04/2020
 * @see    funct.evaluation.Evaluation
 * @see    model.structural.base.Project
 * @see    model.structural.base.product.Artifact
 * @see    model.structural.base.product.Product
 */
public class EvaluationArtifact extends Evaluation {
    private final Product product;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public EvaluationArtifact(Project project) {
        super(project);
        this.product = null;
    }
    
    /**
     * Alternative constructor method of Class.
     * @param project Project.
     * @param product Product.
     */
    public EvaluationArtifact(Project project, Product product) {
        super(project);
        this.product = product;
    }
    
    @Override
    public Double getClauseValue(String keyword, String filter) {
        List   list = this.filter(this.getArtifactFilters(filter));
        String size = Integer.toString(list.size());
        return Double.parseDouble(size);
    }
    
    /**
     * Method responsible for filtering the Artifacts by Parameters.
     * @param  parameters Parameters List.
     * @return Artifacts filtered.
     */
    public List filter(Object[] parameters) {
           List filter = this.filterContext();
                filter = this.filterType(filter, (List<String>) parameters[0]);
                filter = this.filterName(filter, (List<String>) parameters[1]);
        return  filter;
    }
    
    /**
     * Method responsible for filtering the Artifacts List by Context.
     * @return Artifacts List by Context.
     */
    protected List<Artifact> filterContext() {
        return this.product != null ?
               this.product.getArtifactsList() :
               this.project.getArtifactsList();
    }
    
    /**
     * Method responsible for filtering the Artifacts by Types List.
     * @param  list Artifacts List.
     * @param  types Types List.
     * @return Artifacts filtered by Types List.
     */
    protected List<Artifact> filterType(List<Artifact> list, List<String> types) {
        return this.isVoid(types) ? list : this.selectTypes(list, types);
    }
    
    /**
     * Method responsible for selecting the Artifacts by Types List.
     * @param  list Artifacts List.
     * @param  types Types List.
     * @return Artifacts selected by Types List.
     */
    private List<Artifact> selectTypes(List<Artifact> list, List<String> types) {
        List filter = new ArrayList<>();
        for (Artifact artifact : list) {
            if (types.contains(artifact.getElement().getType()))
                filter.add(artifact);
        }
        return  filter;
    }
    
    /**
     * Method responsible for filtering the Artifacts by Names List.
     * @param  list Artifacts List.
     * @param  names Names List.
     * @return Artifacts filtered by Names List.
     */
    protected List<Artifact> filterName(List<Artifact> list, List<String> names) {
        return this.isVoid(names) ? list : this.selectNames(list, names);
    }
    
    /**
     * Method responsible for selecting the Artifacts by Names List.
     * @param  list Artifacts List.
     * @param  names Names List.
     * @return Artifacts selected by Names List.
     */
    private List<Artifact> selectNames(List<Artifact> list, List<String> names) {
        List filter = new ArrayList<>();
        for (Artifact artifact : list) {
            if (names.contains(artifact.getElement().getType()))
                filter.add(artifact);
        }
        return  filter;
    }
}
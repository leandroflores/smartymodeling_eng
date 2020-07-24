package funct.evaluation;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import model.structural.base.Project;

/**
 * <p>Class of Evaluation <b>Evaluation</b>.</p>
 * <p>Class responsible for operations involving <b>Evaluation</b> and <b>Measures</b>.</p>
 * @author Leandro
 * @since  2019-09-02
 * @see    model.structural.base.Project
 */
public abstract class Evaluation {
    protected final Project project;
    protected List<Object> objects;
    
    /**
     * Default constructor method of Class.
     * @param project Project.
     */
    public Evaluation(Project project) {
        this.project = project;
        this.objects = new ArrayList<>();
    }
    
    /**
     * Method responsible for adding the Objects List.
     * @param list Objects List.
     */
    protected void addObjects(List list) {
        this.objects.addAll(new ArrayList<>(list));
        this.objects.add("\n");
    }
    
    /**
     * Method responsible for returning if List is Void.
     * @param  list List.
     * @return List is Void.
     */
    protected boolean isVoid(List<String> list) {
        return list == null   
            || list.isEmpty()  
            || list.get(0).trim().equalsIgnoreCase("*");
    }
    
    /**
     * Method responsible for returning the Expression.
     * @param  expression Expression.
     * @return Expression.
     */
    public String getExpression(String expression) {
        String toReturn = "";
        if (this.checkToken(expression, "(", ")")) {
            for (int i = 0; i < expression.length(); i++) {
                if (this.checkCharacter(expression.charAt(i))) {
                    toReturn += expression.charAt(i);
                }else {
                    String valor  = expression.substring(i, expression.indexOf(")", i) + 1);
                        toReturn += this.getClauseValue(valor);
                               i  = expression.indexOf(")", i);
                }
            }
        }
        return toReturn;
    }
    
    /**
     * Method responsible for returning the Clause Keyword.
     * @param  clause Operation Clause.
     * @return Clause Keyword.
     */
    protected String getKeyword(String clause) {
        if (!clause.contains("("))
            return "";
        return clause.substring(0, clause.indexOf("("));
    }
    
    /**
     * Method responsible for returning the Clause Filter.
     * @param  clause Operation Clause.
     * @return Clause Filter.
     */
    protected String getFilter(String clause) {
        if ((!clause.contains("(")) || (!clause.contains(")")))
            return "";
        if (clause.indexOf("(") > clause.indexOf(")"))
            return "";
        return clause.substring(clause.indexOf("(") + 1, clause.indexOf(")"));
    }
    
    /**
     * Method responsible for returning the Value Clause.
     * @param  clause Operation Clause.
     * @return Clause Value.
     */
    protected Double getClauseValue(String clause) {
        return this.getClauseValue(this.getKeyword(clause), this.getFilter(clause));
    }
    
    /**
     * Method responsible for returning the Clause Value.
     * @param  keyword Keyword.
     * @param  filter Filter.
     * @return Clause Value.
     */
    public abstract Double getClauseValue(String keyword, String filter);
    
    /**
     * Method responsible for checking the Expression Character.
     * @param  character Character.
     * @return Charactere is checked.
     */
    public boolean checkCharacter(char character) {
        switch (character) {
            case '(':
            case ')':
            case '+':
            case '-':
            case '*':
            case '/':
            case ' ':
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                return true;
        }
        return false;
    }
    
    /**
     * Method responsible for cheking the Token.
     * @param  filter String Filter.
     * @param  begin Begin Delimiter.
     * @param  end End Delimiter.
     * @return Token is checked.
     */
    private boolean checkToken(String filter, String begin, String end) {
        return filter.contains(begin)
            && filter.contains(end)
            && filter.indexOf(begin) < filter.indexOf(end);
    }
    
    /**
     * Method responsible for clearing the Token of a Filter.
     * @param  filter String Filter.
     * @param  begin Begin Delimiter.
     * @param  end End Delimiter.
     * @return New Filter.
     */
    protected String clearToken(String filter, String begin, String end) {
        if (this.checkToken(filter, begin, end))
            return filter.substring(0, filter.indexOf(begin)) + filter.substring(filter.indexOf(end) + 1);
        return filter;
    }
    
    /**
     * Method responsible for returning the Parameters List.
     * @param  filter String Filter.
     * @param  begin Begin Delimiter.
     * @param  end End Delimiter.
     * @return Parameters List.
     */
    protected List<String> getParameters(String filter, String begin, String end) {
        if (!this.checkToken(filter, begin, end))
            return new ArrayList<>();
        List   list = new ArrayList<>();
        for (String nome : filter.substring(filter.indexOf(begin) + 1, filter.indexOf(end)).split(","))
               list.add(nome.trim());
        this.clearToken(filter, begin, end);
        return list;
    }
    
    /**
     * Method responsible for returning the Filter Visibility.
     * @param  filter Clause Filter.
     * @return Filter Visibility.
     */
    protected String getVisibility(String filter) {
        if (filter.toUpperCase().contains("PUBLIC"))
            return "public";
        if (filter.toUpperCase().contains("PROTECTED"))
            return "protected";
        if (filter.toUpperCase().contains("PRIVATE"))
            return "private";
        if (filter.toUpperCase().contains("DEFAULT"))
            return "default";
        if (filter.toUpperCase().contains("PACKAGE"))
            return "default";
        return "";
    }
    
    /**
     * Method responsible for returning the Filter Constraint.
     * @param  filter Clause Filter.
     * @return Filter Constraint.
     */
    protected String getConstraint(String filter) {
        if (filter.toLowerCase().contains("inclusive"))
            return "inclusive";
        if (filter.toLowerCase().contains("exclusive"))
            return "exclusive";
        return "";
    }
    
    /**
     * Method responsible for returning the Filter Mandatory Flag.
     * @param  filter Clause Filter.
     * @return Filter Mandatory Flag.
     */
    protected Boolean getMandatory(String filter) {
        if (filter.toUpperCase().contains("MANDATORY"))
            return true;
        if (filter.toUpperCase().contains("OPTIONAL"))
            return false;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Final Flag.
     * @param  filter Clause Filter.
     * @return Filter Final Flag.
     */
    protected Boolean getFinal(String filter) {
        if (filter.toUpperCase().contains("NO-FINAL"))
            return false;
        if (filter.toUpperCase().contains("NO-LEAF"))
            return false;
        if (filter.toUpperCase().contains("FINAL"))
            return true;
        if (filter.toUpperCase().contains("LEAF"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Abstract Flag.
     * @param  filter Clause Filter.
     * @return Filter Abstract Flag.
     */
    protected Boolean getAbstract(String filter) {
        if (filter.toUpperCase().contains("NO-ABSTRACT"))
            return false;
        if (filter.toUpperCase().contains("ABSTRACT"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Static Flag.
     * @param  filter Clause Filter.
     * @return Filter Static Flag.
     */
    protected Boolean getStatic(String filter) {
        if (filter.toUpperCase().contains("NO-STATIC"))
            return false;
        if (filter.toUpperCase().contains("STATIC"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Getter Flag.
     * @param  filter Clause Filter.
     * @return Filter Getter Flag.
     */
    protected Boolean getGetter(String filter) {
        if (filter.toUpperCase().contains("NO-GETTER"))
            return false;
        if (filter.toUpperCase().contains("GETTER"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Setter Flag.
     * @param  filter Clause Filter.
     * @return Filter Setter Flag.
     */
    protected Boolean getSetter(String filter) {
        if (filter.toUpperCase().contains("NO-SETTER"))
            return false;
        if (filter.toUpperCase().contains("SETTER"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Overwritten Flag.
     * @param  filter Clause Filter.
     * @return Filter Overwritten Flag.
     */
    protected Boolean getOverwritten(String filter) {
        if (filter.toUpperCase().contains("NO-WRITTEN"))
            return false;
        if (filter.toUpperCase().contains("OVERWRITTEN"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Specific Flag.
     * @param  filter Clause Filter.
     * @return Filter Specific Flag.
     */
    protected Boolean getSpecific(String filter) {
        if (filter.toUpperCase().contains("NO-SPECIFIC"))
            return false;
        if (filter.toUpperCase().contains("SPECIFIC"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Value Expression.
     * @param  expression Operation Expression.
     * @return Expression Value.
     * @throws ScriptException Expression Error.
     */
    public Double getValue(String expression) throws ScriptException {
        return Double.parseDouble(new ScriptEngineManager().getEngineByName("JavaScript").eval(expression).toString());
    }
    
    /**
     * Method responsible for returning the Expression Final Value.
     * @param  expression Clause Expression.
     * @return Final Value Expression.
     * @throws ScriptException Expression Error.
     */
    public Double getFinalValue(String expression) throws ScriptException {
        return this.getValue(this.getExpression(expression));
    }
    
    /**
     * Method responsible for returning the Element Filters.
     * @param  filter Clause Filter.
     * @return Element Filters.
     */
    protected Object[] getElementFilters(String filter) {
        Object[] filters     = new Object[12];
                 filters[0]  = this.getParameters(filter, "[", "]");
                 filters[1]  = this.getParameters(filter, "<", ">");
                 filters[2]  = this.getParameters(filter, "{", "}");
                 filters[3]  = this.getMandatory(filter);
                 filters[4]  = this.getAbstract(filter);
                 filters[5]  = this.getFinal(filter);
                 filters[6]  = this.getStatic(filter);
                 filters[7]  = this.getVisibility(filter);
                 filters[8]  = this.getGetter(filter);
                 filters[9]  = this.getSetter(filter);
                 filters[10] = this.getOverwritten(filter);
                 filters[11] = this.getSpecific(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Association Filters.
     * @param  filter Clause Filter.
     * @return Association Filters.
     */
    protected Object[] getAssociationFilters(String filter) {
        Object[] filters    = new Object[3];
                 filters[0] = this.getParameters(filter, "[", "]");
                 filters[1] = this.getParameters(filter, "<", ">");
                 filters[2] = this.getParameters(filter, "{", "}");
        return   filters;
    }
    
    /**
     * Method responsible for returning the Artifact Filters.
     * @param  filter Clause Filter.
     * @return Artifact Filters.
     */
    protected Object[] getArtifactFilters(String filter) {
        Object[] filters    = new Object[2];
                 filters[0] = this.getParameters(filter, "{", "}");
                 filters[1] = this.getParameters(filter, "[", "]");
        return   filters;
    }
    
    /**
     * Method responsible for returning the Variability Filters.
     * @param  filter Clause Filter.
     * @return Variability Filters.
     */
    protected Object[] getVariabilityFilters(String filter) {
        Object[] filters    = new Object[4];
                 filters[0] = this.getParameters(filter, "[", "]");
                 filters[1] = this.getParameters(filter, "{", "}");
                 filters[2] = this.getParameters(filter, "<", ">");
                 filters[3] = this.getConstraint(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Objects List.
     * @return Objects List.
     */
    public List<Object> getObjects() {
        return this.objects;
    }
}
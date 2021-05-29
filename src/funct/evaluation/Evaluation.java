package funct.evaluation;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import model.structural.base.Project;

/**
 * <p>Class of Funct <b>Evaluation</b>.</p>
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
     * @param project_ Project.
     */
    public Evaluation(Project project_) {
        project = project_;
        objects = new ArrayList<>();
    }
    
    /**
     * Method responsible for adding the Objects List.
     * @param list Objects List.
     */
    protected void addObjects(List list) {
        objects.addAll(new ArrayList<>(list));
        objects.add("\n");
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
        if (checkToken(expression, "(", ")")) {
            for (int i = 0; i < expression.length(); i++) {
                if (checkCharacter(expression.charAt(i))) {
                    toReturn += expression.charAt(i);
                }else {
                    String valor  = expression.substring(i, expression.indexOf(")", i) + 1);
                        toReturn += getClauseValue(valor);
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
        return getClauseValue(getKeyword(clause), getFilter(clause));
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
     * Method responsible for returning the Element Filters.
     * @param  filter Clause Filter.
     * @return Element Filters.
     */
    protected Object[] getElementFilters(String filter) {
        Object[] filters     = new Object[13];
                 filters[0]  = getParameters(filter, "[", "]");
                 filters[1]  = getParameters(filter, "<", ">");
                 filters[2]  = getParameters(filter, "{", "}");
                 filters[3]  = getMandatory(filter);
                 filters[4]  = getAbstract(filter);
                 filters[5]  = getFinal(filter);
                 filters[6]  = getStatic(filter);
                 filters[7]  = getConstructor(filter);
                 filters[8]  = getVisibility(filter);
                 filters[9]  = getGetter(filter);
                 filters[10] = getSetter(filter);
                 filters[11] = getOverwritten(filter);
                 filters[12] = getSpecific(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Association Filters.
     * @param  filter Clause Filter.
     * @return Association Filters.
     */
    protected Object[] getAssociationFilters(String filter) {
        Object[] filters    = new Object[3];
                 filters[0] = getParameters(filter, "[", "]");
                 filters[1] = getParameters(filter, "{", "}");
                 filters[2] = getParameters(filter, "<", ">");
        return   filters;
    }
    
    /**
     * Method responsible for returning the Artifact Filters.
     * @param  filter Clause Filter.
     * @return Artifact Filters.
     */
    protected Object[] getArtifactFilters(String filter) {
        Object[] filters    = new Object[2];
                 filters[0] = getParameters(filter, "{", "}");
                 filters[1] = getParameters(filter, "[", "]");
        return   filters;
    }
    
    /**
     * Method responsible for returning the Variability Filters.
     * @param  filter Clause Filter.
     * @return Variability Filters.
     */
    protected Object[] getVariabilityFilters(String filter) {
        Object[] filters    = new Object[4];
                 filters[0] = getParameters(filter, "[", "]");
                 filters[1] = getParameters(filter, "{", "}");
                 filters[2] = getParameters(filter, "<", ">");
                 filters[3] = getConstraint(filter);
        return   filters;
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
        if (checkToken(filter, begin, end))
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
        if (!checkToken(filter, begin, end))
            return new ArrayList<>();
        List   list = new ArrayList<>();
        for (String nome : filter.substring(filter.indexOf(begin) + 1, filter.indexOf(end)).split(","))
               list.add(nome.trim());
        clearToken(filter, begin, end);
        return list;
    }
    
    /**
     * Method responsible for returning the Filter Visibility.
     * @param  filter Clause Filter.
     * @return Filter Visibility.
     */
    protected String getVisibility(String filter) {
        if (filter.toLowerCase().contains("public"))
            return "public";
        if (filter.toLowerCase().contains("protected"))
            return "protected";
        if (filter.toLowerCase().contains("private"))
            return "private";
        if (filter.toLowerCase().contains("default"))
            return "default";
        if (filter.toLowerCase().contains("package"))
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
        if (filter.toLowerCase().contains("mandatory"))
            return true;
        if (filter.toLowerCase().contains("optional"))
            return false;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Final Flag.
     * @param  filter Clause Filter.
     * @return Filter Final Flag.
     */
    protected Boolean getFinal(String filter) {
        if (filter.toLowerCase().contains("no-final"))
            return false;
        if (filter.toLowerCase().contains("no-leaf"))
            return false;
        if (filter.toLowerCase().contains("final"))
            return true;
        if (filter.toLowerCase().contains("leaf"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Abstract Flag.
     * @param  filter Clause Filter.
     * @return Filter Abstract Flag.
     */
    protected Boolean getAbstract(String filter) {
        if (filter.toLowerCase().contains("no-abstract"))
            return false;
        if (filter.toLowerCase().contains("abstract"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Static Flag.
     * @param  filter Clause Filter.
     * @return Filter Static Flag.
     */
    protected Boolean getStatic(String filter) {
        if (filter.toLowerCase().contains("no-static"))
            return false;
        if (filter.toLowerCase().contains("static"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Constructor Flag.
     * @param  filter Clause Filter.
     * @return Filter Constructor Flag.
     */
    protected Boolean getConstructor(String filter) {
        if (filter.toLowerCase().contains("no-constructor"))
            return false;
        if (filter.toLowerCase().contains("constructor"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Getter Flag.
     * @param  filter Clause Filter.
     * @return Filter Getter Flag.
     */
    protected Boolean getGetter(String filter) {
        if (filter.toLowerCase().contains("no-getter"))
            return false;
        if (filter.toLowerCase().contains("getter"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Setter Flag.
     * @param  filter Clause Filter.
     * @return Filter Setter Flag.
     */
    protected Boolean getSetter(String filter) {
        if (filter.toLowerCase().contains("no-setter"))
            return false;
        if (filter.toLowerCase().contains("setter"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Overwritten Flag.
     * @param  filter Clause Filter.
     * @return Filter Overwritten Flag.
     */
    protected Boolean getOverwritten(String filter) {
        if (filter.toLowerCase().contains("overwritten"))
            return true;
        return null;
    }
    
    /**
     * Method responsible for returning the Filter Specific Flag.
     * @param  filter Clause Filter.
     * @return Filter Specific Flag.
     */
    protected Boolean getSpecific(String filter) {
        if (filter.toLowerCase().contains("specific"))
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
        return getValue(getExpression(expression));
    }
    
    /**
     * Method responsible for returning the Objects List.
     * @return Objects List.
     */
    public List<Object> getObjects() {
        return objects;
    }
}
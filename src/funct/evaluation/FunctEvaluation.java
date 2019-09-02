package funct.evaluation;

import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * <p>Class of Functions <b>FunctEvaluation</b>.</p>
 * <p>Class responsible for operations involving <b>Metrics</b> and <b>Measures</b>.</p>
 * @author Leandro
 * @since  02/09/2019
 */
public class FunctEvaluation {
    
    /**
     * Method responsible for returning the Expression.
     * @param  expression Expression.
     * @return Expression.
     */
    public String getExpression(String expression) {
        String toReturn = "";
        for (int i = 0; i < expression.length(); i++) {
            if (this.checkCharacter(expression.charAt(i))) {
                toReturn += expression.charAt(i);
            }else {
                String valor = expression.substring(i, expression.indexOf(")", i) + 1);
                   toReturn += this.getClauseValue(valor);
                          i  = expression.indexOf(")", i);
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
        String keyword = this.getKeyword(clause);
        String filter  = this.getFilter(clause);
        System.out.println("Keyword: " + keyword);
        System.out.println("Filter.: " + filter);
        System.out.println("");
        return null;
    }
    
    /**
     * Method responsible for returning the Actor Filters.
     * @param  filter Clause Filter.
     * @return Actor Filters.
     */
    private Object[] getActorFilters(String filter) {
        Object[] filters    = new Object[2];
                 filters[0] = this.getNames(filter);
                     filter = this.clearNames(filter);
                 filters[1] = this.getMandatory(filter);
        return   filters;
    }
    
    /**
     * Method responsible for returning the Use Case Filters.
     * @param  filter Clause Filter.
     * @return Use Case Filters.
     */
    private Object[] getUseCaseFilters(String filter) {
        Object[] filters    = new Object[2];
                 filters[0] = this.getNames(filter);
                     filter = this.clearNames(filter);
                 filters[1] = this.getMandatory(filter);
        return   filters;
    }
    
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
     * Method responsible for clearing the Name.
     * @param  filter Clause Filter.
     * @return New Filter.
     */
    private String clearNames(String filter) {
        if (!filter.contains("[") || (!filter.contains("]")))
            return filter;
        if (filter.indexOf("[") > filter.indexOf("]"))
            return filter;
        return filter.substring(0, filter.indexOf("[")) + filter.substring(filter.indexOf("]") + 1);
    }
    
    /**
     * Method responsible for returning the Names List.
     * @param  filter Clause Filter.
     * @return Names List.
     */
    private List<String> getNames(String filter) {
        if (!filter.contains("[") || (!filter.contains("]")))
            return new ArrayList<>();
        if (filter.indexOf("[") > filter.indexOf("]"))
            return new ArrayList<>();
        List<String> toReturn = new ArrayList<>();
        for (String nome : filter.substring(filter.indexOf("[") + 1, filter.indexOf("]")).split(","))
               toReturn.add(nome.trim());
        return toReturn;
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
        if (filter.toUpperCase().contains("DEFAULT"))
            return "default";
        if (filter.toUpperCase().contains("PACKAGE"))
            return "default";
        if (filter.toUpperCase().contains("PRIVATE"))
            return "private";
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
}
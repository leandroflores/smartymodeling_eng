package controller.view.query;

import java.awt.event.ActionEvent;
import view.query.ViewQueryEvaluation;

/**
 * <p>Class of Controller <b>ControllerViewQueryEvaluation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>ViewQueryEvaluation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/03/2020
 * @see    controller.view.query.ControllerViewQuery
 * @see    view.query.ViewQueryEvaluation
 */
public class ControllerViewQueryEvaluation extends ControllerViewQuery {
    private final ViewQueryEvaluation viewQueryEvaluation;

    /**
     * Default constructor method of Class.
     * @param viewQuery View Query Evaluation.
     */
    public ControllerViewQueryEvaluation(ViewQueryEvaluation viewQuery) {
        super(viewQuery);
        this.viewQueryEvaluation = viewQuery;
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
    }
    
    @Override
    public void apply() {
        System.out.println("Apply");
        System.out.println("");
    }

    @Override
    public void clear() {
        this.viewQueryEvaluation.getPanelQueryEvaluation().clear();
    }
}
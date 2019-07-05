package model.controller.structural.base.variability;

/**
 * <p>Class of Controller <b>ControllerVariability</b>.</p>
 * <p>Class responsible for defining the <b>Variability Controller</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  04/07/2019
 * @see    model.structural.base.variability.Variability
 */
public class ControllerVariability {
    public static final String[] TYPES    = {"EXCLUSIVE", "INCLUSIVE"};
    public static final String[] BINDINGS = {"DESIGN_TIME", "LINK_TIME", "COMPILE_TIME", "RUNTIME"};
}
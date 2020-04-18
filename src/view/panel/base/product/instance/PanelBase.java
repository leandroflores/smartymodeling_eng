package view.panel.base.product.instance;

import java.awt.Dimension;
import javax.swing.JButton;
import model.structural.base.product.Instance;
import view.new_.base.ViewNew;
import view.new_.base.product.ViewNewInstance;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Instance Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.panel.base.
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final ViewNew viewNew;
    
    /**
     * Default constructor method of Class.
     * @param viewNew View New Instance.
     */
    public PanelBase(ViewNewInstance viewNew) {
        super(viewNew.getViewMenu());
        this.viewNew  = viewNew;
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    public void addFooter() {
        this.add(this.createButton("returnButton", " Return ", "Return", "back.png"));
        this.add(this.createButton("nextButton",   "  Next  ", " Next ", "next.png"));
        
        this.getReturnButton().setPreferredSize(new Dimension(150, 30));
        this.getNextButton().setPreferredSize(new Dimension(150, 30));
    }
    
    /**
     * Method responsible for returning the Return Button.
     * @return Return Button.
     */
    public JButton getReturnButton() {
        return this.getButton("returnButton");
    }
    
    /**
     * Method responsible for returning the Next Button.
     * @return Next Button.
     */
    public JButton getNextButton() {
        return this.getButton("nextButton");
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.getViewNew().getInstance();
    }
    
    /**
     * Method responsible for returning the View New Instance.
     * @return View New Instance.
     */
    public ViewNewInstance getViewNew() {
        return (ViewNewInstance) this.viewNew;
    }
}
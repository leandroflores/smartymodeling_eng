package view.panel.new_.base.product.instance;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import model.structural.base.product.Product;
import view.modal.new_.base.product.ViewNewInstance;
import view.panel.new_.PanelNew;
import view.panel.new_.base.product.PanelNewInstance;

/**
 * <p>Class of View <b>PanelBase</b>.</p> 
 * <p>Class responsible for defining a Abstract Model for <b>Instance Base Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-17
 * @see    controller.view.panel.base.
 * @see    view.panel.base.PanelBase
 */
public abstract class PanelBase extends view.panel.base.PanelBase {
    protected final PanelNew panelNew;
    protected final Instance instance;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel New Instance.
     * @param instance Instance.
     */
    public PanelBase(PanelNewInstance panel, Instance instance) {
        super(panel.getViewMenu());
        this.panelNew = panel;
        this.instance = instance;
    }
    
    /**
     * Method responsible for returning the Panel Footer.
     * @return Panel Footer.
     */
    protected JPanel getFooter() {
        JPanel footer = new JPanel();
               footer.setLayout(new GridLayout(1, 2));
               footer.add(this.createButton("returnButton", " Return ", "Return", "back.png", new Dimension(175, 30)));
               footer.add(this.createButton("nextButton",   "  Next  ", " Next ", "next.png", new Dimension(175, 30)));
        return footer;
    }
    
    /**
     * Method responsible for adding the Panel Footer.
     */
    public abstract void addFooter();
    
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
     * Method responsible for returning the Product.
     * @return Product.
     */
    public Product getProduct() {
        return this.getInstance().getProduct();
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        return this.getInstance().getDiagram();
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        return this.instance;
    }
    
    /**
     * Method responsible for returning the View New Instance.
     * @return View New Instance.
     */
    public ViewNewInstance getViewNew() {
        return (ViewNewInstance) this.getPanelNew().getView();
    }
    
    /**
     * Method responsible for returning the Panel New Instance.
     * @return Panel New Instance.
     */
    public PanelNewInstance getPanelNew() {
        return (PanelNewInstance) this.panelNew;
    }
}
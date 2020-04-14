package view.panel.tabbed;

import controller.view.panel.tabbed.ControllerPanelTabTitle;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import view.Panel;
import view.panel.diagram.PanelDiagram;
import view.panel.instance.PanelInstance;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>PanelTabTitle</b>.</p>
 * <p>Class responsible for defining the <b>Tab Title Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.panel.tabbed.ControllerPanelTabTitle
 * @see    view.Panel
 */
public final class PanelTabTitle extends Panel {
    private final PanelModeling panel;
    private final Component component;
    private String  title;
    private JLabel  titleLabel;
    private JButton closeButton;
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Modeling.
     * @param component Component.
     * @param title Title.
     * @param icon Icon.
     */
    public PanelTabTitle(PanelModeling panel, final Component component, String title, Icon icon) {
        this.controller = new ControllerPanelTabTitle(this);
        this.panel      = panel;
        this.component  = component;
        this.title      = title;
        this.addComponents();
    }
       
    @Override
    protected void addComponents() {
        this.setOpaque(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        this.addTitleLabel();
        this.addCloseButton();
    }
    
    /**
     * Method responsible for adding the Title Label.
     */
    private void addTitleLabel() {
        this.titleLabel = this.createLabel(this.title);
        this.add(this.titleLabel);
    }
        
    /**
     * Method responsible for adding the Close Button.
     */
    private void addCloseButton() {
        this.closeButton = new JButton("X");
        this.closeButton.setMargin(new Insets(0, 0, 0, 0));
        this.closeButton.addActionListener(this.controller);
        this.add(this.closeButton);
    }
    
    /**
     * Method responsible for defining the Title.
     * @param title Title.
     */
    public void setTitle(String title) {
        this.title = title;
        this.titleLabel.setText(this.title);
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        if (this.getComponent() instanceof PanelDiagram)
            return ((PanelDiagram) this.getComponent()).getDiagram();
        return null;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        if (this.getComponent() instanceof PanelInstance)
            return ((PanelInstance) this.getComponent()).getInstance();
        return null;
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    public PanelModeling getPanelModeling() {
        return this.panel;
    }
    
    /**
     * Method responsible for returning the Component.
     * @return Component.
     */
    public Component getComponent() {
        return this.component;
    }
    
    /**
     * Method responsible for returning the Close Button.
     * @return Close Button.
     */
    public JButton getCloseButton() {
        return this.closeButton;
    }
}
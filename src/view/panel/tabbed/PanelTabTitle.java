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
import view.panel.Panel;
import view.panel.diagram.PanelDiagram;
import view.panel.instance.PanelInstance;
import view.panel.modeling.PanelModeling;

/**
 * <p>Class of View <b>PanelTabTitle</b>.</p>
 * <p>Class responsible for defining the <b>Tab Title Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.panel.tabbed.ControllerPanelTabTitle
 * @see    view.panel.Panel
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
        addComponents();
    }
       
    @Override
    protected void addComponents() {
        setOpaque(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
        addTitleLabel();
        addCloseButton();
    }
    
    /**
     * Method responsible for adding the Title Label.
     */
    private void addTitleLabel() {
        titleLabel = createLabel(title);
        add(titleLabel);
    }
        
    /**
     * Method responsible for adding the Close Button.
     */
    private void addCloseButton() {
        closeButton = new JButton("X");
        closeButton.setMargin(new Insets(0, 0, 0, 0));
        closeButton.addActionListener(controller);
        add(closeButton);
    }
    
    /**
     * Method responsible for defining the Title.
     * @param title Title.
     */
    public void setTitle(String title) {
        this.title = title;
        titleLabel.setText(title);
    }
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    public Diagram getDiagram() {
        if (getComponent() instanceof PanelDiagram)
            return ((PanelDiagram) getComponent()).getDiagram();
        return null;
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    public Instance getInstance() {
        if (getComponent() instanceof PanelInstance)
            return ((PanelInstance) getComponent()).getInstance();
        return null;
    }
    
    /**
     * Method responsible for returning the Panel Modeling.
     * @return Panel Modeling.
     */
    public PanelModeling getPanelModeling() {
        return panel;
    }
    
    /**
     * Method responsible for returning the Component.
     * @return Component.
     */
    public Component getComponent() {
        return component;
    }
    
    /**
     * Method responsible for returning the Close Button.
     * @return Close Button.
     */
    public JButton getCloseButton() {
        return closeButton;
    }
}
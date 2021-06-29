package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelFeatureOperation;
import javax.swing.JButton;
import view.panel.operation.PanelOperation;
import view.panel.diagram.types.PanelFeatureDiagram;

/**
 * <p>Class of View <b>PanelFeatureOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Feature Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-04-09
 * @see    controller.view.panel.operation.types.ControllerPanelFeatureOperation
 * @see    view.panel.operation.PanelOperation
 * @see    view.panel.diagram.types.PanelFeatureDiagram
 */
public final class PanelFeatureOperation extends PanelOperation {
    
    /**
     * Default constructor method of Class.
     * @param panel Panel Feature Diagram.
     */
    public PanelFeatureOperation(PanelFeatureDiagram panel) {
        super(panel);
        controller = new ControllerPanelFeatureOperation(this);
        setDefaultProperties();
        addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addComponents();
        getVariabilityButton().setVisible(false);
    }
    
    @Override
    protected void addDiagramButtons() {
        add(createButton("feature",   "", "New Feature",               "diagram/feature/feature.png"));
        add(createButton("inclusive", "", "New Inclusive Variability", "diagram/feature/inclusive.png"));
        add(createButton("exclusive", "", "New Exclusive Variability", "diagram/feature/exclusive.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons  = {
                 getAssociationImage("feature/mandatory"),
                 getAssociationImage("feature/optional"),
                 getAssociationImage("feature/variant")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        getClickButton().setBackground(getDefaultColor());
        getFeatureButton().setBackground(getDefaultColor());
        getInclusiveButton().setBackground(getDefaultColor());
        getExclusiveButton().setBackground(getDefaultColor());
    }
    
    @Override
    public PanelFeatureDiagram getPanelDiagram() {
        return (PanelFeatureDiagram) panel;
    }
    
    /**
     * Method responsible for returning the Feature Button.
     * @return Feature Button.
     */
    public JButton getFeatureButton() {
        return getButton("feature");
    }
    
    /**
     * Method responsible for returning the Inclusive Button.
     * @return Inclusive Button.
     */
    public JButton getInclusiveButton() {
        return getButton("inclusive");
    }
    
    /**
     * Method responsible for returning the Exclusive Button.
     * @return Exclusive Button.
     */
    public JButton getExclusiveButton() {
        return getButton("exclusive");
    }
}
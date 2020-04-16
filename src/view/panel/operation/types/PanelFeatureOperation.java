package view.panel.operation.types;

import controller.view.panel.operation.types.ControllerPanelFeatureOperation;
import javax.swing.JButton;
import view.panel.operation.PanelOperation;
import view.panel.diagram.types.PanelFeatureDiagram;

/**
 * <p>Class of View <b>PanelFeatureOperation</b>.</p> 
 * <p>Class responsible for defining a Panel for <b>Feature Operation</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  09/04/2019
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
        this.controller = new ControllerPanelFeatureOperation(this);
        this.setDefaultProperties();
        this.addComponents();
    }
    
    @Override
    protected void addComponents() {
        super.addComponents();
        this.getVariabilityButton().setVisible(false);
    }
    
    @Override
    protected void addDiagramButtons() {
        this.add(this.createButton("featureButton", "", "New Feature", "diagram/feature/feature.png"));
    }
    
    @Override
    public Object[] getAssociationsIcons() {
        Object[] icons  = {
                 this.getAssociationImage("feature/mandatory"),
                 this.getAssociationImage("feature/optional"),
                 this.getAssociationImage("feature/inclusive"),
                 this.getAssociationImage("feature/exclusive")};
        return   icons;
    }

    @Override
    public void resetBackground() {
        this.getClickButton().setBackground(this.getDefaultColor());
        this.getFeatureButton().setBackground(this.getDefaultColor());
    }
    
    @Override
    public PanelFeatureDiagram getPanelDiagram() {
        return (PanelFeatureDiagram) this.panelDiagram;
    }
    
    /**
     * Method responsible for returning the Feature Button.
     * @return Feature Button.
     */
    public JButton getFeatureButton() {
        return this.getButton("featureButton");
    }
}
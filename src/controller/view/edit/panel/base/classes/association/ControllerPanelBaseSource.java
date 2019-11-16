package controller.view.edit.panel.base.classes.association;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.diagram.classes.base.association.AssociationUML;
import view.edit.panel.base.classes.association.PanelBaseSource;

/**
 * <p>Class of Controller <b>ControllerPanelBaseSource</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>PanelBaseSource</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  16/11/2019
 * @see    controller.view.ControllerPanel
 * @see    model.structural.diagram.classes.base.association.AssociationUML
 * @see    view.edit.panel.base.classes.association.PanelBaseSource
 */
public class ControllerPanelBaseSource extends ControllerPanel {
    private final PanelBaseSource panelBaseSource;
    private final AssociationUML  associationUML;

    /**
     * Default constructor method of Class.
     * @param panel Panel Base Source.
     */
    public ControllerPanelBaseSource(PanelBaseSource panel) {
        super(panel);
        this.panelBaseSource = panel;
        this.associationUML  = panel.getAssociationUML();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        this.update();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        this.update();
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        this.update();
    }
    
    /**
     * Method responsible for updating the Source Cardinality.
     * @return Source Cardinality.
     */
    private void updateCardinality() {
        String value = this.panelBaseSource.getCardinalityTextField().getText().trim();
        if (this.checkCardinality(value)) {
            this.associationUML.setSourceMin(this.getMin(value));
            this.associationUML.setSourceMax(this.getMax(value));
        }
    }
    
    /**
     * Method responsible for setting the Source Association Values.
     */
    private void update() {
        this.panelBaseSource.getAssociationUML().setSourceName(this.panelBaseSource.getNameTextField().getText().trim());
        this.updateCardinality();
        this.panelBaseSource.getViewMenu().getPanelProject().getPanelTree().updateUI();
        this.panelBaseSource.getViewMenu().getPanelModeling().updateModelingPanel();
        this.panelBaseSource.getViewMenu().setSave(false);
    }
}
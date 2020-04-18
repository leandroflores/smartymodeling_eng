package controller.view.panel.operation;

import com.mxgraph.model.mxCell;
import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.delete.base.ViewDeleteElement;
import view.new_.base.variability.ViewNewVariability;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of Controller <b>ControllerPanelOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Operation Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  10/04/2020
 * @see    controller.view.panel.ControllerPanel
 * @see    view.panel.operation.PanelOperation
 */
public abstract class ControllerPanelOperation extends ControllerPanel {

    /**
     * Default constructor method of Class.
     * @param panel Panel Operation.
     */
    public ControllerPanelOperation(PanelOperation panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event != null) {
            if (this.getPanelOperation().getClickButton().equals(event.getSource()))
                this.getPanelDiagram().updateGraph();
            else if (this.getPanelOperation().getVariabilityButton().equals(event.getSource()))
                this.addVariability();
            else if (this.getPanelOperation().getEditButton().equals(event.getSource()))
                this.edit();
            else if (this.getPanelOperation().getDeleteButton().equals(event.getSource()))
                this.delete();
            else if (this.getPanelOperation().getAssociationComboBox().equals(event.getSource()))
                this.getPanelDiagram().setType(this.getPanelOperation().getAssociationComboBox().getSelectedIndex());
            this.getPanelDiagram().getViewMenu().setSave(false);
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for setting the Diagram Panel Operation.
     * @param button Button Pressed.
     * @param operation Button Operation.
     */
    protected void setOperation(JButton button, String operation) {
        this.getPanelOperation().resetBackground();
        button.setBackground(this.getFocusColor());
        this.getPanelDiagram().setOperation(operation);
    }
    
    /**
     * Method responsible for adding a New Variability.
     */
    public void addVariability() {
        this.getPanelOperation().resetBackground();
        this.getPanelOperation().getClickButton().setBackground(this.getDefaultColor());
        this.getPanelDiagram().setOperation("Click");
        if (!this.getPanelDiagram().getDiagram().getElementsList().isEmpty())
            new ViewNewVariability(this.getPanelDiagram().getViewMenu().getPanelModeling(), 
                                   this.getPanelDiagram().getDiagram(),
                                   this.getVariationPoint()).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Variation Point.
     * @return Variation Point.
     */
    protected Element getVariationPoint() {
        Element element = this.getPanelDiagram().getSelectedElement();
        if (element != null && element.isDefault())
            return element;
        return null;
    }
    
    /**
     * Method responsible for Editing the Cell Selected.
     */
    public void edit() {
        mxCell cell = (mxCell) this.getPanelDiagram().getGraph().getSelectionCell();
        String id   = this.getPanelDiagram().getIdentifiers().get(cell);
        if (this.getPanelDiagram().getDiagram().getElement(id) != null)
            this.getPanelDiagram().getComponent().startEditingAtCell(cell);
        this.getPanelDiagram().setClick();
    }
    
    /**
     * Method responsible for Deleting the Cell Selected.
     */
    public void delete() {
        mxCell cell = (mxCell) this.getPanelDiagram().getGraph().getSelectionCell();
        String id   = this.getPanelDiagram().getIdentifiers().get(cell);
        if (this.getPanelDiagram().getDiagram().getElement(id) != null)
            this.delete(this.getPanelDiagram().getDiagram().getElement(id));
        else if (this.getPanelDiagram().getDiagram().getAssociation(id) != null)
            this.delete(this.getPanelDiagram().getDiagram().getAssociation(id));
    }
    
    /**
     * Method responsible for Deleting a Element.
     * @param element Element.
     */
    private void delete(Element element) {
        new ViewDeleteElement(this.getPanelDiagram().getViewMenu().getPanelModeling(),
                              this.getPanelDiagram().getDiagram(),
                              element).setVisible(true);
        this.getPanelDiagram().updateGraph();
    }
    
    /**
     * Method responsible for Deleting a Association.
     * @param association Association.
     */
    private void delete(Association association) {
        this.getPanelDiagram().getDiagram().removeAssociation(association);
        this.getPanelDiagram().updateGraph();
    }
    
    /**
     * Method responsible for returning the Panel Operation.
     * @return Panel Operation.
     */
    protected PanelOperation getPanelOperation() {
        return (PanelOperation) this.panel;
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    protected PanelDiagram getPanelDiagram() {
        return this.getPanelOperation().getPanelDiagram();
    }
}
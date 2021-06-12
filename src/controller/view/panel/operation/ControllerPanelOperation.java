package controller.view.panel.operation;

import com.mxgraph.model.mxCell;
import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.modal.delete.base.ViewDeleteElement;
import view.modal.new_.base.variability.ViewNewVariability;
import view.panel.diagram.PanelDiagram;
import view.panel.operation.PanelOperation;

/**
 * <p>Class of Controller <b>ControllerPanelOperation</b>.</p>
 * <p>Class responsible for controlling the <b>Operation Panel</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2020-04-10
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
            if (getPanel().getClickButton().equals(event.getSource()))
                getPanelDiagram().updateGraph();
            else if (getPanel().getVariabilityButton().equals(event.getSource()))
                addVariability();
            else if (getPanel().getEditButton().equals(event.getSource()))
                edit();
            else if (getPanel().getDeleteButton().equals(event.getSource()))
                delete();
            else if (getPanel().getAssociationComboBox().equals(event.getSource()))
                getPanelDiagram().setType(getPanel().getAssociationComboBox().getSelectedIndex());
            getPanelDiagram().getViewMenu().setSave(false);
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
        getPanel().resetBackground();
        button.setBackground(getFocusColor());
        getPanelDiagram().setOperation(operation);
    }
    
    /**
     * Method responsible for adding a New Variability.
     */
    public void addVariability() {
        getPanel().resetBackground();
        getPanel().getClickButton().setBackground(getDefaultColor());
        getPanelDiagram().setOperation("Click");
        if (!getPanelDiagram().getDiagram().getElementsList().isEmpty())
            new ViewNewVariability(getPanelDiagram().getViewMenu().getPanelModeling(), getPanelDiagram().getDiagram(), getVariationPoint()).setVisible(true);
    }
    
    /**
     * Method responsible for returning the Variation Point.
     * @return Variation Point.
     */
    protected Element getVariationPoint() {
        Element element = getPanelDiagram().getSelectedElement();
        if (element != null && element.isDefault())
            return element;
        return null;
    }
    
    /**
     * Method responsible for Editing the Cell Selected.
     */
    public void edit() {
        mxCell cell = (mxCell) getPanelDiagram().getGraph().getSelectionCell();
        String id   = getPanelDiagram().getIdentifiers().get(cell);
        if (getPanelDiagram().getDiagram().getElement(id) != null)
            getPanelDiagram().getComponent().startEditingAtCell(cell);
        getPanelDiagram().setClick();
    }
    
    /**
     * Method responsible for Deleting the Cell Selected.
     */
    public void delete() {
        mxCell cell = (mxCell) getPanelDiagram().getGraph().getSelectionCell();
        String id   = getPanelDiagram().getIdentifiers().get(cell);
        if (getPanelDiagram().getDiagram().getElement(id) != null)
            delete(getPanelDiagram().getDiagram().getElement(id));
        else if (getPanelDiagram().getDiagram().getAssociation(id) != null)
            delete(getPanelDiagram().getDiagram().getAssociation(id));
    }
    
    /**
     * Method responsible for Deleting a Element.
     * @param element Element.
     */
    protected void delete(Element element) {
        new ViewDeleteElement(getPanelDiagram().getViewMenu().getPanelModeling(), getPanelDiagram().getDiagram(), element).setVisible(true);
        getPanelDiagram().updateGraph();
    }
    
    /**
     * Method responsible for Deleting a Association.
     * @param association Association.
     */
    protected void delete(Association association) {
        getPanelDiagram().getDiagram().removeAssociation(association);
        getPanelDiagram().updateGraph();
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    protected PanelDiagram getPanelDiagram() {
        return getPanel().getPanelDiagram();
    }
    
    @Override
    public PanelOperation getPanel() {
        return (PanelOperation) panel;
    }
}
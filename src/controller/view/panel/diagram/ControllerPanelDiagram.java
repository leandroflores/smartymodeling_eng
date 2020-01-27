package controller.view.panel.diagram;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.delete.ViewDeleteElement;
import view.new_.variability.ViewNewVariability;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    controller.view.ControllerPanel
 * @see    java.awt.event.MouseListener
 * @see    view.panel.diagram.PanelDiagram
 */
public abstract class ControllerPanelDiagram extends ControllerPanel implements MouseListener {
    private final PanelDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerPanelDiagram(PanelDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panelDiagram.getClickButton().equals(event.getSource()))
            this.panelDiagram.updateDiagram();
        else if (this.panelDiagram.getVariabilityButton().equals(event.getSource()))
            this.addVariability();
        else if (this.panelDiagram.getEditButton().equals(event.getSource()))
            this.edit();
        else if (this.panelDiagram.getDeleteButton().equals(event.getSource()))
            this.delete();
        else if (this.panelDiagram.getAssociationComboBox().equals(event.getSource()))
            this.panelDiagram.setType(this.panelDiagram.getAssociationComboBox().getSelectedIndex());
        this.panelDiagram.getViewMenu().setSave(false);
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case DELETE:
                this.delete();
                this.panelDiagram.getViewMenu().setSave(false);
                break;
            case F2:
                this.edit();
                this.panelDiagram.getViewMenu().setSave(false);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.move(event);
                this.panelDiagram.getViewMenu().setSave(false);
                break;
            default:
                break;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
//        System.out.println("Clicked");
    }
    
    @Override
    public void mousePressed(MouseEvent event) {
        System.out.println("Pressed");
        System.out.println(this.panelDiagram.getGraph().getSelectionCell());
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        if (this.panelDiagram.getOperation().equalsIgnoreCase("Click"))
            this.update(event);
    }
    
    /**
     * Method responsible for updating the Association Points.
     * @param event Mouse Event.
     */
    public void update(MouseEvent event) {
        if (this.panelDiagram.getGraph().getSelectionCell() != null) {
            mxCell cell = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
            String id   = this.panelDiagram.getIdentifiers().get(cell);
            if (this.panelDiagram.getDiagram().getAssociation(id) != null)
                this.updatePoints(this.panelDiagram.getDiagram().getAssociation(id), cell);
        }
    }
    
    /**
     * Method responsible for updating the Association Points from a Selected Cell.
     * @param association Association.
     * @param cell Selected Cell.
     */
    private void updatePoints(Association association, mxCell cell) {
        mxGeometry geometry = ((mxGraphModel) (this.panelDiagram.getGraph().getModel())).getGeometry(cell);
                   association.setPoints(geometry.getPoints());
        this.panelDiagram.getViewMenu().setSave(false);
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}
    
    /**
     * Method responsible for defining the Add Variability Operation.
     */
    public void addVariability() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getClickButton().setBackground(this.getDefaultColor());
        this.panelDiagram.setOperation("Click");
        if (this.panelDiagram.getDiagram().getElementsList().isEmpty() == false) {
            new ViewNewVariability(this.panelDiagram.getViewMenu().getPanelModeling(), this.panelDiagram.getDiagram()).setVisible(true);
            this.panelDiagram.getViewMenu().setSave(false);
        }
    }
    
    /**
     * Method responsible for moving the Element on Diagram.
     * @param event Key Event.
     */
    public void move(KeyEvent event) {
        mxCell  cell    = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
        String  id      = this.panelDiagram.getIdentifiers().get(cell);
        Element element = this.panelDiagram.getDiagram().getElement(id);
        if (element != null) {
            if (event.getKeyCode() == KeyEvent.VK_UP)
                element.dy(-10);
            if (event.getKeyCode() == KeyEvent.VK_DOWN)
                element.dy(10);
            if (event.getKeyCode() == KeyEvent.VK_LEFT)
                element.dx(-10);
            if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                element.dx(10);
            this.panelDiagram.updateDiagram();
            this.panelDiagram.getGraph().setSelectionCell(this.panelDiagram.getObjects().get(element.getId()));
        }
    }
    
    /**
     * Method responsible for editing the Cell Selected.
     */
    public void edit() {
        if (this.panelDiagram.getGraph() != null) {
            mxCell cell = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
            String id   = this.panelDiagram.getIdentifiers().get(cell);
            if (this.panelDiagram.getDiagram().getElement(id) != null)
                  this.panelDiagram.getComponent().startEditingAtCell(cell);
            this.panelDiagram.setClick();
        }
    }
    
    /**
     * Method responsible for deleting the Cell Selected.
     */
    public void delete() {
        if (this.panelDiagram.getGraph() != null) {
            mxCell cell = (mxCell) this.panelDiagram.getGraph().getSelectionCell();
            String id   = this.panelDiagram.getIdentifiers().get(cell);
            if (this.panelDiagram.getDiagram().getElement(id) != null)
                this.delete(this.panelDiagram.getDiagram().getElement(id));
            else if (this.panelDiagram.getDiagram().getAssociation(id) != null)
                this.delete(this.panelDiagram.getDiagram().getAssociation(id));
        }
    }
    
    /**
     * Method responsible for removing a Element.
     * @param element Element.
     */
    private void delete(Element element) {
        new ViewDeleteElement(this.panelDiagram.getViewMenu().getPanelModeling(), element).setVisible(true);
        this.panelDiagram.updateDiagram();
    }
    
    /**
     * Method responsible for removing a Association.
     * @param association Association.
     */
    private void delete(Association association) {
        this.panelDiagram.getDiagram().removeAssociation(association);
        this.panelDiagram.updateDiagram();
    }
    
    /**
     * Method responsible for returning Element.
     * @return Element.
     */
    private Element getElement() {
        Object objeto = this.panelDiagram.getComponent().getGraph().getSelectionCell();
        if (objeto != null)
            return this.getElement(this.panelDiagram.getIdentifiers().get(objeto));
        return null;
    }
    
    /**
     * Method responsible for returning Diagram Element.
     * @param  id Element Id.
     * @return Diagram Element.
     */
    private Element getElement(String id) {
        return this.panelDiagram.getDiagram().getElement(id);
    }
}
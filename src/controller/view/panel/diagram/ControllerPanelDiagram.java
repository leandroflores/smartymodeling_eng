package controller.view.panel.diagram;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
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

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerPanelDiagram(PanelDiagram panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {}
    
    @Override
    public void keyPressed(KeyEvent event) {
        switch (event.getKeyCode()) {
            case F2:
                this.edit();
                this.getPanelDiagram().getViewMenu().setSave(false);
                break;
            case DELETE:
                this.delete();
                this.getPanelDiagram().getViewMenu().setSave(false);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                this.move(event);
                this.getPanelDiagram().getViewMenu().setSave(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {}
    
    @Override
    public void mousePressed(MouseEvent event) {}

    @Override
    public void mouseReleased(MouseEvent event) {
        if (this.getPanelDiagram().getOperation().equalsIgnoreCase("Click"))
            this.update(event);
    }
    
    /**
     * Method responsible for updating the Association Points.
     * @param event Mouse Event.
     */
    public void update(MouseEvent event) {
        if (this.getPanelDiagram().getGraph().getSelectionCell() != null) {
            mxCell cell = (mxCell) this.getPanelDiagram().getGraph().getSelectionCell();
            String id   = this.getPanelDiagram().getIdentifiers().get(cell);
            if (this.getPanelDiagram().getDiagram().getAssociation(id) != null)
                this.updatePoints(this.getPanelDiagram().getDiagram().getAssociation(id), cell);
        }
    }
    
    /**
     * Method responsible for updating the Association Points of a Selected Cell.
     * @param association Association.
     * @param cell Selected Cell.
     */
    private void updatePoints(Association association, mxCell cell) {
        mxGeometry geometry = this.getPanelDiagram().getModel().getGeometry(cell);
                   association.setPoints(geometry.getPoints());
        this.getPanelDiagram().getViewMenu().setSave(false);
        this.getPanelDiagram().updateUI();
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}
    
    /**
     * Method responsible for adding a New Variability.
     */
    public void addVariability() {
//        this.getPanelOperation().resetBackground();
//        this.getPanelOperation().getClickButton().setBackground(this.getDefaultColor());
        this.getPanelDiagram().setOperation("Click");
        if (!this.getPanelDiagram().getDiagram().getElementsList().isEmpty())
            new ViewNewVariability(this.getPanelDiagram().getViewMenu().getPanelModeling(), this.getPanelDiagram().getDiagram()).setVisible(true);
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
        new ViewDeleteElement(this.getPanelDiagram().getViewMenu().getPanelModeling(), element).setVisible(true);
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
     * Method responsible for Moving by Key Event.
     * @param event Key Event.
     */
    public void move(KeyEvent event) {
        mxCell  cell    = (mxCell) this.getPanelDiagram().getGraph().getSelectionCell();
        String  id      = this.getPanelDiagram().getIdentifiers().get(cell);
        Element element = this.getPanelDiagram().getDiagram().getElement(id);
        if (element != null) {
            this.move(element, event);
            this.getPanelDiagram().updateGraph();
            this.getPanelDiagram().getGraph().setSelectionCell(this.getPanelDiagram().getObjects().get(element.getId()));
        }
    }
    
    /**
     *  Method responsible for Moving a Element by Key Event.
     * @param element Selected Element.
     * @param event Key Event.
     */
    protected void move(Element element, KeyEvent event) {
        switch (event.getKeyCode()) {
            case UP:
                element.dy(-10);
                break;
            case DOWN:
                element.dy(10);
                break;
            case LEFT:
                element.dx(-10);
                break;
            case RIGHT:
                element.dx(10);
                break;
            default:
                break;
        }
    }
    
    /**
     * Method responsible for updating the Panel Diagram.
     */
    protected void update() {
        this.getPanelDiagram().updateGraph();
        this.getPanelDiagram().getViewMenu().update();
        this.getPanelDiagram().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    protected PanelDiagram getPanelDiagram() {
        return (PanelDiagram) this.panel;
    }
}
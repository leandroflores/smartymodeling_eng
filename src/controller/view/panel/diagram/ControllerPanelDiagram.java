package controller.view.panel.diagram;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.modal.delete.base.ViewDeleteElement;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>PanelDiagram</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    controller.view.panel.ControllerPanel
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
                edit();
                getPanelDiagram().getViewMenu().setSave(false);
                break;
            case DELETE:
                delete();
                getPanelDiagram().getViewMenu().setSave(false);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                move(event);
                getPanelDiagram().getViewMenu().setSave(false);
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
        if (getPanelDiagram().getOperation().equalsIgnoreCase("Click"))
            update(event);
    }
    
    /**
     * Method responsible for updating the Association Points.
     * @param event Mouse Event.
     */
    public void update(MouseEvent event) {
        if (getPanelDiagram().getGraph().getSelectionCell() != null) {
            mxCell cell = (mxCell) getPanelDiagram().getGraph().getSelectionCell();
            String id   = getPanelDiagram().getIdentifiers().get(cell);
            if (getPanelDiagram().getDiagram().getAssociation(id) != null)
                updatePoints(getPanelDiagram().getDiagram().getAssociation(id), cell);
        }
    }
    
    /**
     * Method responsible for updating the Association Points of a Selected Cell.
     * @param association Association.
     * @param cell Selected Cell.
     */
    private void updatePoints(Association association, mxCell cell) {
        mxGeometry geometry = getPanelDiagram().getModel().getGeometry(cell);
                   association.setPoints(geometry.getPoints());
        getPanelDiagram().getViewMenu().setSave(false);
        getPanelDiagram().updateUI();
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}
    
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
    private void delete(Element element) {
        new ViewDeleteElement(getPanelDiagram().getViewMenu().getPanelModeling(), 
                              getPanelDiagram().getDiagram(),
                              element).setVisible(true);
        getPanelDiagram().updateGraph();
    }
    
    /**
     * Method responsible for Deleting a Association.
     * @param association Association.
     */
    private void delete(Association association) {
        getPanelDiagram().getDiagram().removeAssociation(association);
        getPanelDiagram().updateGraph();
    }
    
    /**
     * Method responsible for Moving by Key Event.
     * @param event Key Event.
     */
    public void move(KeyEvent event) {
        mxCell  cell    = (mxCell) getPanelDiagram().getGraph().getSelectionCell();
        String  id      = getPanelDiagram().getIdentifiers().get(cell);
        Element element = getPanelDiagram().getDiagram().getElement(id);
        if (element != null) {
            move(element, event);
            getPanelDiagram().updateGraph();
            getPanelDiagram().getGraph().setSelectionCell(getPanelDiagram().getObjects().get(element.getId()));
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
        getPanelDiagram().updateGraph();
        getPanelDiagram().getViewMenu().update();
        getPanelDiagram().getViewMenu().setSave(false);
    }
    
    /**
     * Method responsible for returning the Panel Diagram.
     * @return Panel Diagram.
     */
    protected PanelDiagram getPanelDiagram() {
        return (PanelDiagram) panel;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.view.panel.diagram.event;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.model.mxGraphModel;
import com.mxgraph.util.mxPoint;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import model.structural.base.Element;
import model.structural.base.association.Association;
import view.panel.diagram.PanelDiagram;

/**
 *
 * @author Leandro
 */
public class ControllerEventPoints extends MouseAdapter {
    private final PanelDiagram panel;
    
    public ControllerEventPoints(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void mouseClicked(MouseEvent event) {
        System.out.println("mouseClicked");
        Object  object = this.panel.getComponent().getCellAt(event.getX(), event.getY());
        if (object != null) {
            String id = this.panel.getIdentifiers().get(object);
            if (this.panel.getDiagram().getElement(id) != null)
                this.updatePanelEdit(this.panel.getDiagram().getElement(id)); 
            else if (this.panel.getDiagram().getAssociation(id) != null)
                this.updatePoints((mxCell) object, this.panel.getDiagram().getAssociation(id), event);
        }
    }
    
    /**
     * Method responsible for updating the Panel Edit.
     * @param element Element selected.
     */
    private void updatePanelEdit(Element element) {
        this.panel.getViewMenu().getPanelProject().initPanelEditElement(this.panel.getDiagram(), element);
        this.panel.getViewMenu().getPanelProject().updatePanelEdit();
        this.panel.getGraph().setSelectionCell(this.panel.getObjects().get(element.getId()));
    }
    
    private void updatePoints(mxCell edge, Association association, MouseEvent event) {
        if (event.getClickCount() == 2) {
            System.out.println("doubleClicked");
            mxPoint    point    = new mxPoint(event.getX(), event.getY());
            mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(edge);
                       this.updatePoints(geometry, point);
                       ((mxGraphModel) (this.panel.getGraph().getModel())).setGeometry(edge, geometry);
                       System.out.println("Points: " + geometry.getPoints());
        }else if (event.getButton() == MouseEvent.BUTTON3) {
            mxPoint    point    = new mxPoint(event.getX(), event.getY());
            System.out.println("leftClicked: " + point);
            mxGeometry geometry = ((mxGraphModel) (this.panel.getGraph().getModel())).getGeometry(edge);
                       geometry.getPoints().remove(point);
                ((mxGraphModel) (this.panel.getGraph().getModel())).setGeometry(edge, geometry);
                       System.out.println("Points: " + geometry.getPoints());
        }
    }
    
    private void updatePoints(mxGeometry geometry, mxPoint point) {
        List<mxPoint> points = this.getPoints(point);
        if (geometry.getPoints() == null)
            geometry.setPoints(points);
        else
            geometry.getPoints().addAll(points);
    }
    
    private List<mxPoint> getPoints(mxPoint point) {
        List<mxPoint> points = new ArrayList<>();
                      points.add(point);
        return        points;
    }
}

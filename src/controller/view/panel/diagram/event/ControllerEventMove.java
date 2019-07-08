package controller.view.panel.diagram.event;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import model.structural.base.Element;
import view.panel.diagram.PanelDiagram;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Modeling Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    visao.painel.diagrama.PainelDiagrama
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Diagram.
     */
    public ControllerEventMove(PanelDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject event) {
        Object  cell    = this.panel.getGraph().getSelectionCell();
        String  id      = this.panel.getIdentifiers().get(cell);
        Element element = this.panel.getDiagram().getElement(id);
        System.out.println("Element: " + element);
        if (element != null) {
            element.dx(((Double) event.getProperty("dx")).intValue());
            element.dy(((Double) event.getProperty("dy")).intValue());
            this.panel.getViewMenu().setSave(false);
        }
    }
}
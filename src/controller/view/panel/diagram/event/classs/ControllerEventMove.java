package controller.view.panel.diagram.event.classs;

import com.mxgraph.util.mxEventObject;
import com.mxgraph.util.mxEventSource;
import com.mxgraph.util.mxEventSource.mxIEventListener;
import view.panel.diagram.types.PanelClassDiagram;

/**
 * <p>Class of Controller <b>ControllerEventMove</b>.</p>
 * <p>Class responsible for defining the <b>Controller</b> for <b>Moving Events</b> on Class Diagram Panel of SMartyModeling.</p>
 * @author Leandro
 * @since  03/06/2019
 * @see    view.panel.diagram.types.PanelClassDiagram
 */
public class ControllerEventMove extends mxEventSource implements mxIEventListener {
    private final PanelClassDiagram panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Class Diagram.
     */
    public ControllerEventMove(PanelClassDiagram panel) {
        this.panel = panel;
    }
    
    @Override
    public void invoke(Object object, mxEventObject evento) {
        
//        System.out.println("AA");
//         System.out.println(object);
//         System.out.println((mxGraph) object);
//         System.out.println(this.painel.getGrafo());
//         System.out.println(evento.getProperties());
//         
//         System.out.println(this.painel.getGrafo().getSelectionCell());
//         System.out.println(this.painel.getGrafo().getSelectionCell().getClass());
        
         
         
//        try {
//            Object   objetoA  = this.painel.getGrafo().getSelectionCell();
//            Object   objetoC  = evento.getProperties();
//            Object   objetoB  = ((mxCell) this.painel.getGrafo().getSelectionCell()).getParent();
//            System.out.println(objetoA);
//            System.out.println(this.painel.getObjetos().get(objetoA));
//            System.out.println(objetoB);
//            System.out.println(this.painel.getObjetos().get(objetoB));
//        }catch (Exception exception) {
//            System.out.println("Erro");
//        }
//        System.out.println("");
        
//        
////        System.out.println(object);
////        System.out.println(object instanceof mxCell);
//        System.out.println(objeto);
//        System.out.println(objeto instanceof mxCell);
//        System.out.println(((mxCell) objeto).getParent());
//        System.out.println(((mxCell) objeto).getParent() instanceof mxCell);
//        System.out.println();
        
//        if (elemento != null) {
//            elemento.dx(((Double) evento.getProperty("dx")).intValue());
//            elemento.dy(((Double) evento.getProperty("dy")).intValue());
//            this.painel.getViewMenu().setSalvo(false);
//        }
    }
}
package controller.view.panel.diagram.types;

import controller.view.panel.diagram.ControllerPanelDiagram;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import view.panel.diagram.types.PanelUseCaseDiagram;

/**
 * <p>Class of Controller <b>ControllerPanelUseCaseDiagram</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Use Case Diagram Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  28/05/2019
 * @see    controller.view.panel.diagram.ControllerPanelDiagram
 * @see    view.panel.diagram.types.PanelUseCaseDiagram
 */
public class ControllerPanelUseCaseDiagram extends ControllerPanelDiagram {
    private final PanelUseCaseDiagram panelDiagram;

    /**
     * Default constructor method of Class.
     * @param panel Panel Use Case Diagram.
     */
    public ControllerPanelUseCaseDiagram(PanelUseCaseDiagram panel) {
        super(panel);
        this.panelDiagram = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        super.actionPerformed(event);
        if (this.panelDiagram.getButtonAtor().equals(event.getSource()))
            this.addAtor();
        else if (this.panelDiagram.getButtonCasoDeUso().equals(event.getSource()))
            this.addCasoDeUso();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        super.keyPressed(event);
    }
    
    @Override
    public void mousePressed(MouseEvent evento) {
        if (this.panelDiagram.getOperacao().equals("Ator"))
            this.addAtor(evento);
        else if (this.panelDiagram.getOperacao().equals("CasoDeUso"))
            this.addCasoDeUso(evento);
    }
    
    /**
     * Metodo responsavel por definir a Operacao Adicionar Ator.
     */
    public void addAtor() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getButtonAtor().setBackground(this.getCorSelect());
        this.panelDiagram.setOperacao("Ator");
    }
    
    /**
     * Metodo responsavel por definir a Operacao Adicionar Caso de Uso.
     */
    public void addCasoDeUso() {
        this.panelDiagram.resetBackground();
        this.panelDiagram.getButtonCasoDeUso().setBackground(this.getCorSelect());
        this.panelDiagram.setOperacao("CasoDeUso");
    }

    /**
     * Metodo responsavel por adicionar um Novo Ator UML.
     * @param evento Evento do Mouse.
     */
    public void addAtor(MouseEvent evento) {
        AtorUML ator = new AtorUML();
                ator.setPosicao(evento.getX(), evento.getY());
        this.panelDiagram.getDiagrama().addAtor(ator);
        this.panelDiagram.atualizarDiagrama();
        this.panelDiagram.getViewMenu().update();
    }
    
    /**
     * Metodo responsavel por adicionar um Novo Caso de Uso UML.
     * @param evento Evento do Mouse.
     */
    public void addCasoDeUso(MouseEvent evento) {
        CasoDeUsoUML casoDeUso = new CasoDeUsoUML();
                     casoDeUso.setPosicao(evento.getX(), evento.getY());
        this.panelDiagram.getDiagrama().addCasoDeUso(casoDeUso);
        this.panelDiagram.atualizarDiagrama();
        this.panelDiagram.getViewMenu().update();
    }
}
package controller.view.panel.tabbed;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import view.panel.tabbed.PanelTabTitle;

/**
 *
 * @author Leandro
 */
public class ControllerPanelTabTitle extends ControllerPanel {
    private final PanelTabTitle panel;

    /**
     * Default constructor method of Class.
     * @param panel Panel Tab Title.
     */
    public ControllerPanelTabTitle(PanelTabTitle panel) {
        super(panel);
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.panel.getCloseButton().equals(event.getSource()))
            this.closeTab();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for returning the Diagram.
     * @return 
     */
    private Diagram getDiagram() {
        return this.panel.getPanelModeling().getPanelDiagram().getDiagram();
    }
    
    /**
     * Method responsible for closing the Tab.
     */
    private void closeTab() {
        if (this.panel.getPanelModeling().getPanelDiagram() == null) {
            System.out.println("AA");
            this.panel.getPanelModeling().clear();
        }else {
            System.out.println(this.getDiagram().getId());
            this.panel.getPanelModeling().removeDiagram(this.getDiagram());
            System.out.println();
        }
//        this.panel.upd
//        this.panel.getPanelModeling().getPanelTabbed().remove
//        System.out.println("Close Tab: " + this.panel.getComponent());
////        this.panel.getPanelModeling().
//        System.out.println();
    }
}
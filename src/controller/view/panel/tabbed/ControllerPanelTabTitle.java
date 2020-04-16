package controller.view.panel.tabbed;

import controller.view.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import view.panel.tabbed.PanelTabTitle;

/**
 * <p>Class of Controller <b>ControllerPanelTabTitle</b>.</p>
 * <p>Class responsible for controlling the <b>Events</b> from the <b>Panel Tab Title</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
 * @see    controller.view.ControllerPanel
 * @see    view.panel.tabbed.PanelTabTitle
 */
public class ControllerPanelTabTitle extends ControllerPanel {

    /**
     * Default constructor method of Class.
     * @param panel Panel Tab Title.
     */
    public ControllerPanelTabTitle(PanelTabTitle panel) {
        super(panel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (this.getPanel().getCloseButton().equals(event.getSource()))
            this.closeTab();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    private Diagram getDiagram() {
        return this.getPanel().getPanelModeling().getPanelDiagram().getDiagram();
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    private Instance getInstance() {
        return this.getPanel().getPanelModeling().getPanelInstance().getInstance();
    }
    
    /**
     * Method responsible for closing the Tab.
     */
    private void closeTab() {
        if (this.getPanel().getDiagram() != null)
            this.getPanel().getPanelModeling().removeDiagram(this.getPanel().getDiagram());
        else if (this.getPanel().getInstance() != null)
            this.getPanel().getPanelModeling().removeInstance(this.getPanel().getInstance());
        else
            this.getPanel().getPanelModeling().getPanelTabbed().removeAll();
    }
    
    @Override
    public PanelTabTitle getPanel() {
        return (PanelTabTitle) this.panel;
    }
}
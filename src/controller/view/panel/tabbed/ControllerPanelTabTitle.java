package controller.view.panel.tabbed;

import controller.view.panel.ControllerPanel;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import model.structural.base.Diagram;
import model.structural.base.product.Instance;
import view.panel.tabbed.PanelTabTitle;

/**
 * <p>Class of Controller <b>ControllerPanelTabTitle</b>.</p>
 * <p>Class responsible for controlling the <b>PanelTabTitle</b> Events of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-31
 * @see    controller.view.panel.ControllerPanel
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
        if (getPanel().getCloseButton().equals(event.getSource()))
            closeTab();
    }

    @Override
    public void keyPressed(KeyEvent event) {}
    
    /**
     * Method responsible for returning the Diagram.
     * @return Diagram.
     */
    private Diagram getDiagram() {
        return getPanel().getPanelModeling().getPanelDiagram().getDiagram();
    }
    
    /**
     * Method responsible for returning the Instance.
     * @return Instance.
     */
    private Instance getInstance() {
        return getPanel().getPanelModeling().getPanelInstance().getInstance();
    }
    
    /**
     * Method responsible for closing the Tab.
     */
    private void closeTab() {
        if (getPanel().getDiagram() != null)
            getPanel().getPanelModeling().removeDiagram(getPanel().getDiagram());
        else if (getPanel().getInstance() != null)
            getPanel().getPanelModeling().removeInstance(getPanel().getInstance());
        else
            getPanel().getPanelModeling().getPanelTabbed().removeAll();
        getPanel().getPanelModeling().updatePanelMain();
    }
    
    @Override
    public PanelTabTitle getPanel() {
        return (PanelTabTitle) panel;
    }
}
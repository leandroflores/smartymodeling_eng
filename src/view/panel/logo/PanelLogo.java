package view.panel.logo;

import funct.FunctView;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import view.panel.Panel;
import view.main.structural.ViewMenu;

/**
 * <p>Class of View <b>PanelLogo</b>.</p>
 * <p>Class responsible for defining the <b>Logo Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-27
 * @see    view.panel.Panel
 * @see    view.main.structural.ViewMenu
 */
public final class PanelLogo extends Panel {
    private final ViewMenu viewMenu;
    
    /**
     * Metodo construtor padrao da Classe.
     * @param view View Menu.
     */
    public PanelLogo(ViewMenu view) {
        viewMenu = view;
        addComponents();
    }
    
    @Override
    protected void addComponents() {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(new JLabel(new FunctView().createImage("logo-eng.png")));
        setPreferredSize(new Dimension(1000, 500));
    }
}
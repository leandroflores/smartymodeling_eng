package view.panel.tabbed;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * <p>Class of View <b>PanelTabbed</b>.</p>
 * <p>Class responsible for defining the <b>Tabbed Panel</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  27/05/2019
 * @see    javax.swing.JTabbedPane
 */
public class PanelTabbed extends JTabbedPane {

    /**
     * Default constructor method of Class.
     */
    public PanelTabbed() {
        super();
    }

    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        this.setTabComponentAt(this.getTabCount() - 1, new CloseButtonTab(component, title, icon));
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        this.addTab(title, icon, component, null);
    }

    @Override
    public void addTab(String title, Component component) {
        this.addTab(title, null, component);
    }

    /* Button */
    public class CloseButtonTab extends JPanel {
        private final Component component;
        private final String title;

        public CloseButtonTab(final Component component, String title, Icon icon) {
            this.component = component;
            this.title     = title;
            this.initComponents();
        }
        
        /**
         * 
         */
        private void initComponents() {
            this.setOpaque(false);
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 3, 3));
            this.add(new JLabel(this.title));
            this.add(this.createCloseButton());
        }
        
        /**
         * Metodo responsavel por adicionar o Label na View.
         */
        private void addLabel() {
            this.add(new JLabel(this.title));
        }
        
        private JButton createCloseButton() {
            JButton button = new JButton("X");
                    button.setMargin(new Insets(0, 0, 0, 0));
                    button.addMouseListener(new CloseListener(this.component, this.title));
            return  button;
        }
    }
    /* ClickListener */
    public class CloseListener implements MouseListener {
        private final Component component;
        private final String title;

        /**
         * Default constructor method of Class.
         * @param component Component.
         * @param title Title.
         */
        public CloseListener(Component component, String title) {
            this.component = component;
            this.title     = title;
        }

        @Override
        public void mouseClicked(MouseEvent event) {
            if (event.getSource() instanceof JButton) {
                JButton     button = (JButton)     event.getSource();
                JTabbedPane tabbed = (JTabbedPane) button.getParent().getParent().getParent();
                System.out.println(this.title);
                            tabbed.remove(this.component);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent event) {}

        @Override
        public void mouseExited(MouseEvent event) {}
    }
}
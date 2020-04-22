package view;

import view.style.ViewStyle;
import controller.Controller;
import file.filter.SMartyFilter;
import funct.FunctView;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * <p>Class of View <b>View</b>.</p>
 * <p>Class responsible for defining a Abstract Model for the <b>Views</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  2019-05-18
 * @see    controller.Controller
 * @see    javax.swing.JFrame
 */
public abstract class View extends JFrame {
    protected Controller controller;
    private HashMap buttons;
    private HashMap fileChoosers;
    private HashMap menus;
    private HashMap menuItems;
    private HashMap scrollPanes;
    
    /**
     * Default constructor method of Class.
     */
    public View() {
        super();
        this.init();
        this.setDefaultProperties();
    }
    
    /**
     * Method responsible for initializing the Component Maps.
     */
    private void init() {
        this.buttons      = new HashMap<>();
        this.fileChoosers = new HashMap<>();
        this.menus        = new HashMap<>();
        this.menuItems    = new HashMap<>();
        this.scrollPanes  = new HashMap<>();
    }
    
    /**
     * Method responsible for setting the Default Properties for the View.
     */
    private void setDefaultProperties() {
        this.setSize(new Dimension(this.getXAxis(), this.getYAxis()));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocation(5, 5);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * Method responsible for returning the X Axix.
     * @return X Axix.
     */
    private int getXAxis() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()- 10;
    }
    
    /**
     * Method responsible for returning the Y Axis.
     * @return Y Axis.
     */
    private int getYAxis() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50;
    }
    
    /**
     * Method responsible for returning a New Label.
     * @param  title Label Title.
     * @return New Label.
     */
    protected JLabel createLabel(String title) {
        JLabel label = new JLabel(title);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
        return label;
    }
    
    /**
     * Method responsible for returning a New JLabel.
     * @param  title Label Title.
     * @param  size Label Size.
     * @return New Label.
     */
    protected JLabel createLabel(String title, int size) {
        JLabel label = this.createLabel(title);
               label.setHorizontalAlignment(SwingConstants.RIGHT);
               label.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
        return label;
    }
    
    /**
     * Method responsible for returning a New Image Label.
     * @param  path Image Path.
     * @return New Image Label.
     */
    protected JLabel createLabelImage(String path) {
        return new JLabel(new FunctView().createImage(path));
    }
    
    /**
     * Method responsible for returning a New Button.
     * @param  id Button Id.
     * @param  title Button Title.
     * @param  path Button Imagem Path.
     * @return New Button.
     */
    protected JButton createButton(String id, String title, String path) {
        JButton button = new JButton(new FunctView().createImage("icons/" + path));
                button.setText(title);
                button.setToolTipText(title);
                button.addActionListener(this.controller);
                button.addKeyListener(this.controller);
                button.setPreferredSize(new Dimension(75, 30));
                button.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                this.buttons.put(id, button);
        return  button;
    }
    
    /**
     * Method responsible for returning the Button by Id.
     * @param  id Button Id.
     * @return Button found.
     */
    protected JButton getButton(String id) {
        return (JButton) this.buttons.get(id);
    }
    
    /**
     * Method responsible for returning a New File Chooser.
     * @param  id File Chooser Id.
     * @return New File Chooser.
     */
    public JFileChooser createFileChooser(String id) {
        UIManager.put("FileChooser.cancelButtonText",  "Cancel");
        UIManager.put("FileChooser.approveButtonText", "Open");
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new SMartyFilter());
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning a New Image Chooser.
     * @param  id Image Chooser Id.
     * @return New Image Chooser.
     */
    public JFileChooser createImageChooser(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png", "png"));
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning the File Chooser by Id.
     * @param  id File Chooser Id.
     * @return File Chooser found.
     */
    protected JFileChooser getFileChooser(String id) {
        return (JFileChooser) this.fileChoosers.get(id);
    }
    
    /**
     * Method responsible for returning a New Menu.
     * @param  id Menu Id.
     * @param  title Menu Title.
     * @return New Menu.
     */
    protected JMenu createMenu(String id, String title) {
        JMenu  menu = new JMenu(title);
               menu.addActionListener(this.controller);
               menu.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               this.menus.put(id, menu);
        return menu;
    }
    
    /**
     * Method responsible for returning the Menu by Id.
     * @param  id Menu Id.
     * @return Menu found.
     */
    protected JMenu getMenu(String id) {
        return (JMenu) this.menus.get(id);
    }
    
    /**
     * Method responsible for returning a New Menu Item.
     * @param  id Menu Item Id.
     * @param  title Menu Item Title.
     * @param  path Menu Item Image Path.
     * @return New Menu Item.
     */
    protected JMenuItem createMenuItem(String id, String title, String path) {
        JMenuItem menuItem = new JMenuItem(title, new FunctView().createImage("icons/" + path));
                  menuItem.addActionListener(this.controller);
                  menuItem.addKeyListener(this.controller);
                  menuItem.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
                  this.menuItems.put(id, menuItem);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning a New Menu Item.
     * @param  id Menu Item Id.
     * @param  title Menu Item Title.
     * @param  path Menu Item Image Path.
     * @param  keychar Menu Item Key Char.
     * @return New Menu Item.
     */
    protected JMenuItem createMenuItem(String id, String title, String path, int keychar) {
        JMenuItem menuItem = this.createMenuItem(id, title, path);
                  menuItem.setAccelerator(KeyStroke.getKeyStroke(keychar, InputEvent.CTRL_MASK));
                  menuItem.setMnemonic(keychar);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning the Menu Item by Id.
     * @param  id Menu Item Id.
     * @return Menu Item found.
     */
    protected JMenuItem getMenuItem(String id) {
        return (JMenuItem) this.menuItems.get(id);
    }
    
    /**
     * Method responsible for returning the Menu Items Map.
     * @return Menu Items Map.
     */
    protected HashMap getMenuItems() {
        return new HashMap(this.menuItems);
    }
    
    /**
     * Method responsible for returning a New Scroll Pane.
     * @param  id Scroll Pane Id.
     * @return New Scroll Pane.
     */
    protected JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = new JScrollPane();
                    scrollPane.addKeyListener(this.controller);
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    this.scrollPanes.put(id, scrollPane);
        return scrollPane;
    }
    
    /**
     * Method responsible for returning the Scroll Pane by Id.
     * @param  id Scroll Pane Id.
     * @return Scroll Pane found.
     */
    protected JScrollPane getScrollPane(String id) {
        return (JScrollPane) this.scrollPanes.get(id);
    }
}
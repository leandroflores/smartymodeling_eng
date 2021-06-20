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
        init();
        setDefaultProperties();
    }
    
    /**
     * Method responsible for initializing the Component Maps.
     */
    private void init() {
        buttons      = new HashMap<>();
        fileChoosers = new HashMap<>();
        menus        = new HashMap<>();
        menuItems    = new HashMap<>();
        scrollPanes  = new HashMap<>();
    }
    
    /**
     * Method responsible for setting the Default Properties for the View.
     */
    private void setDefaultProperties() {
        setSize(new Dimension(getXAxis(), getYAxis()));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocation(5, 5);
        setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
               label.addKeyListener(controller);
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
        JLabel label = createLabel(title);
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
                button.addActionListener(controller);
                button.addKeyListener(controller);
                button.setPreferredSize(new Dimension(75, 30));
                button.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                buttons.put(id, button);
        return  button;
    }
    
    /**
     * Method responsible for returning the Button by Id.
     * @param  id Button Id.
     * @return Button found.
     */
    protected JButton getButton(String id) {
        return (JButton) buttons.get(id);
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
                     fileChoosers.put(id, fileChooser);
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
                     fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning a New Pdf Chooser.
     * @param  id Pdf Chooser Id.
     * @return New Pdf Chooser.
     */
    public JFileChooser createPdfChooser(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new FileNameExtensionFilter("PDF", "pdf", "pdf"));
                     fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning the File Chooser by Id.
     * @param  id File Chooser Id.
     * @return File Chooser found.
     */
    protected JFileChooser getFileChooser(String id) {
        return (JFileChooser) fileChoosers.get(id);
    }
    
    /**
     * Method responsible for returning a New Menu.
     * @param  id Menu Id.
     * @param  title Menu Title.
     * @return New Menu.
     */
    protected JMenu createMenu(String id, String title) {
        JMenu  menu = new JMenu(title);
               menu.addActionListener(controller);
               menu.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               menus.put(id, menu);
        return menu;
    }
    
    /**
     * Method responsible for returning the Menu by Id.
     * @param  id Menu Id.
     * @return Menu found.
     */
    protected JMenu getMenu(String id) {
        return (JMenu) menus.get(id);
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
                  menuItem.addActionListener(controller);
                  menuItem.addKeyListener(controller);
                  menuItem.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
                  menuItems.put(id, menuItem);
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
        JMenuItem menuItem = createMenuItem(id, title, path);
                  menuItem.setAccelerator(KeyStroke.getKeyStroke(keychar, InputEvent.CTRL_MASK));
                  menuItem.setMnemonic(keychar);
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
    protected JMenuItem createMenuItemAlt(String id, String title, String path, int keychar) {
        JMenuItem menuItem = createMenuItem(id, title, path);
                  menuItem.setAccelerator(KeyStroke.getKeyStroke(keychar, InputEvent.ALT_MASK));
                  menuItem.setMnemonic(keychar);
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
    protected JMenuItem createMenuItemShift(String id, String title, String path, int keychar) {
        JMenuItem menuItem = createMenuItem(id, title, path);
                  menuItem.setAccelerator(KeyStroke.getKeyStroke(keychar, InputEvent.SHIFT_MASK));
                  menuItem.setMnemonic(keychar);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning the Menu Item by Id.
     * @param  id Menu Item Id.
     * @return Menu Item found.
     */
    protected JMenuItem getMenuItem(String id) {
        return (JMenuItem) menuItems.get(id);
    }
    
    /**
     * Method responsible for returning the Menu Items Map.
     * @return Menu Items Map.
     */
    protected HashMap getMenuItems() {
        return new HashMap(menuItems);
    }
    
    /**
     * Method responsible for returning a New Scroll Pane.
     * @param  id Scroll Pane Id.
     * @return New Scroll Pane.
     */
    protected JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = new JScrollPane();
                    scrollPane.addKeyListener(controller);
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    scrollPanes.put(id, scrollPane);
        return scrollPane;
    }
    
    /**
     * Method responsible for returning the Scroll Pane by Id.
     * @param  id Scroll Pane Id.
     * @return Scroll Pane found.
     */
    protected JScrollPane getScrollPane(String id) {
        return (JScrollPane) scrollPanes.get(id);
    }
}
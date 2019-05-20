package view;

import controller.Controller;
import funct.FunctView;
import funct.FunctString;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * <p>Class of View <b>View</b>.</p>
 * <p>Class responsible for defining a model for the <b>Views</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/05/2019
 * @see    controller.Controller
 * @see    javax.swing.JFrame
 */
public abstract class View extends JFrame {
    protected Controller controller;
    
    protected HashMap<String, JButton> buttons;
    protected HashMap<String, JComboBox> comboBoxes;
    protected HashMap<String, JTextField> textFields;
    protected HashMap<String, JMenu> menus;
    protected HashMap<String, JPanel> panels;
    protected HashMap<String, JMenuItem> menuItens;
    protected HashMap<String, JScrollPane> scrollPanes;
    protected HashMap<String, JFileChooser> fileChoosers;
    
    /**
     * Default constructor method of Class.
     */
    public View() {
        super();
        this.init();
        this.setSettings();
    }
    
    /**
     * Method responsible for starting the Lists.
     */
    private void init() {
        this.buttons      = new HashMap<>();
        this.comboBoxes   = new HashMap<>();
        this.textFields   = new HashMap<>();
        this.menus        = new HashMap<>();
        this.panels       = new HashMap<>();
        this.menuItens    = new HashMap<>();
        this.scrollPanes  = new HashMap<>();
        this.fileChoosers = new HashMap<>();
    }
    
    /**
     * Method responsible for defining the settings for the View.
     */
    private void setSettings() {
        this.setSize(new Dimension(this.getXAxis(), this.getYAxis()));
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocation(5, 5);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    /**
     * Method responsible for returning the Y Axis.
     * @return Y Axis.
     */
    private int getYAxis() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 50;
    }
    
    /**
     * Method responsible for returning the X Axix.
     * @return X Axix.
     */
    private int getXAxis() {
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()- 10;
    }
    
    /**
     * Method responsible for adding Lines to View.
     * @param number Number of Lines.
     */
    protected void addLinhas(int number) {
        for (int i = 0; i < number; ++i)
            this.add(new JLabel(new FunctString().getSpaces(1000)));
    }
    
    /**
     * Method responsible for returning a new JLabel.
     * @param  title JLabel Title.
     * @return New JLabel.
     */
    protected JLabel createLabel(String title) {
        JLabel label = new JLabel(title);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
        return label;
    }
    
    /**
     * Method responsible for returning a new JLabel.
     * @param  title JLabel Title.
     * @param  size JLabel Size.
     * @return New JLabel.
     */
    protected JLabel createLabel(String title, int size) {
        JLabel label = new JLabel(title, SwingConstants.RIGHT);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               label.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
        return label;
    }
    
    /**
     * Method responsible for retorning a new Image JLabel.
     * @param  url Image URL.
     * @return New Image JLabel.
     */
    protected JLabel createLabelImage(String url) {
        return new JLabel(new FunctView().createImage(url));
    }
    
    /**
     * Method responsible for returning a new JTextField.
     * @param  id JTextField Id.
     * @param  message JTextField Message.
     * @param  size JTextField Size.
     * @return New JTextField.
     */
    protected JTextField createTextField(String id, String message, int size) {
        JTextField textField = new JTextField(size);
                   textField.setText(message);
                   textField.addActionListener(this.controller);
                   textField.addKeyListener(this.controller);
                   textField.setPreferredSize(new Dimension(ViewStyle.WIDTH, ViewStyle.HEIGHT));
                   textField.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                   this.textFields.put(id, textField);
        return     textField;
    }
    
    /**
     * Method responsible for returning a new No Editable JTextField.
     * @param  id JTextField Id.
     * @param  message JTextField Message.
     * @param  size JTextField Size.
     * @return New No Editable JTextField.
     */
    protected JTextField createTextFieldNoEditable(String id, String message, int size) {
        JTextField textField = this.createTextField(id, message, size);
                   textField.setEditable(false);
        return     textField;
    }
    
    /**
     * Method responsible for returning a new JComboBox.
     * @param  id JComboBox Id.
     * @param  values JComboBox Values.
     * @param  size JComboBox Size.
     * @return New JComboBox.
     */
    protected JComboBox createComboBox(String id, Object[] values, int size) {
        JComboBox comboBox = new JComboBox(values);
                  comboBox.addActionListener(this.controller);
                  comboBox.addKeyListener(this.controller);
                  comboBox.setAlignmentX(CENTER_ALIGNMENT);
                  comboBox.setAlignmentY(CENTER_ALIGNMENT);
                  comboBox.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
                  comboBox.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
                  this.comboBoxes.put(id, comboBox);
        return    comboBox;
    }
    
    /**
     * Method responsible for returning a new JButton.
     * @param  id JButton Id.
     * @param  title JButton Title.
     * @param  url JButton Imagem URL.
     * @return New JButton.
     */
    protected JButton createButton(String id, String title, String url) {
        JButton button = new JButton(new FunctView().createImage("icons/" + url));
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
     * Method responsible for returning a new JButton.
     * @param  id JButton Id.
     * @param  title JButton Title.
     * @param  focusTitle JButton Focus Title.
     * @param  url JButton Image URL.
     * @return New JButton.
     */
    protected JButton createButton(String id, String title, String focusTitle, String url) {
        JButton button = this.createButton(id, title, url);
                button.setToolTipText(focusTitle);
        return  button;
    }
    
    /**
     * Method responsible for returning a new JMenu.
     * @param  id JMenu Id.
     * @param  title JMenu Title.
     * @return New JMenu.
     */
    protected JMenu createMenu(String id, String title) {
        JMenu  menu = new JMenu(title);
               menu.addActionListener(this.controller);
               menu.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               this.menus.put(id, menu);
        return menu;
    }
    
    /**
     * Method responsible for returning a new JMenu.
     * @param  id JMenu Id.
     * @param  title JMenu Title.
     * @param  url JMenu Image URL.
     * @return New JMenu.
     */
    protected JMenu createMenu(String id, String title, String url) {
        JMenu  menu = new JMenu(title);
               menu.setIcon(new FunctView().createImage("icons/" + url));
               menu.addActionListener(this.controller);
               menu.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               this.menus.put(id, menu);
        return menu;
    }
    
    /**
     * Method responsible for returning a new JMenuItem.
     * @param  id JMenuItem Id.
     * @param  title JMenuItem Title.
     * @param  url JMenuItem Image URL.
     * @return New JMenuItem.
     */
    protected JMenuItem createMenuItem(String id, String title, String url) {
        JMenuItem menuItem = new JMenuItem(title, new FunctView().createImage("icons/" + url));
                  menuItem.addActionListener(this.controller);
                  menuItem.addKeyListener(this.controller);
                  menuItem.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
                  this.menuItens.put(id, menuItem);
        return    menuItem;
    }
    
    /**
     * Method responsible for returning a new JPanel.
     * @param  id JPanel Id.
     * @return New JPanel.
     */
    protected JPanel createPainel(String id) {
        JPanel painel = new JPanel();
               painel.addKeyListener(this.controller);
               this.panels.put(id, painel);
        return painel;
    }
    
    /**
     * Method responsible for returning a new JScrollPane.
     * @param  id JScrollPane Id.
     * @return New JScrollPane.
     */
    protected JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = new JScrollPane();
                    scrollPane.addKeyListener(this.controller);
                    this.scrollPanes.put(id, scrollPane);
        return scrollPane;
    }
    
    /**
     * Method responsible for returning a new JFileChooser.
     * @param  id JFileChooser Id.
     * @return New JFileChooser.
     */
    public JFileChooser createFileChooserArquivo(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new FileNameExtensionFilter("SMARTY", "smty", "smty"));
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning a new Image JFileChooser.
     * @param  id JFileChooser Id.
     * @return New Image JFileChooser.
     */
    public JFileChooser createImageFileChooser(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png", "png"));
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
}
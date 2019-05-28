package view;

import view.interfaces.InterfaceView;
import controller.Controller;
import funct.FunctDate;
import funct.FunctView;
import funct.FunctString;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Date;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * <p>Class of View <b>ViewModal</b>.</p>
 * <p>Class responsible for defining a model for the <b>Views Modal</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/05/2019
 * @see    controller.Controller
 * @see    javax.swing.JDialog
 * @see    view.InterfaceView
 */
public abstract class ViewModal extends JDialog implements InterfaceView {
    protected String     title;
    protected Controller controller;
    
    protected HashMap<String, JList> lists;
    protected HashMap<String, JLabel> labels;
    protected HashMap<String, JButton> buttons;
    protected HashMap<String, JCheckBox> checkBoxes;
    protected HashMap<String, JComboBox> comboBoxes;
    protected HashMap<String, JTextArea>  textAreas;
    protected HashMap<String, JTextField> textFields;
    protected HashMap<String, JRadioButton> radioButtons;
    protected HashMap<String, JFileChooser> fileChoosers;
    
    protected HashMap<String, JScrollPane> scrollPanes;
    protected HashMap<String, JTable> tables;
    protected HashMap<String, TableModel> tableModels;
    protected HashMap<String, TableColumnModel> tableColumnModels;
    
    /**
     * Default constructor method of Class.
     * @param view View Parent.
     */
    public ViewModal(View view) {
        super(view, "", true);
        this.init();
        this.setSettings();
    }
    
    /**
     * Alternative constructor method of Class.
     * @param view View Parent.
     */
    public ViewModal(ViewModal view) {
        super(view, "", true);
        this.init();
        this.setSettings();
    }
    
    /**
     * Method responsible for starting the Lists.
     */
    private void init() {
        this.buttons        = new HashMap<>();
        this.checkBoxes     = new HashMap<>();
        this.comboBoxes     = new HashMap<>();
        this.radioButtons   = new HashMap<>();
        this.textAreas      = new HashMap<>();
        this.textFields     = new HashMap<>();
        this.fileChoosers   = new HashMap<>();
        this.tables         = new HashMap<>();
        this.lists          = new HashMap<>();
        
        this.scrollPanes       = new HashMap<>();
        this.tables            = new HashMap<>();
        this.tableModels       = new HashMap<>();
        this.tableColumnModels = new HashMap<>();
    }
    
    /**
     * Method responsible for defining the settings for the View Modal.
     */
    private void setSettings() {
        this.setSize(220, 120);
        this.setLocation(350, 400);
        this.setIconImage(new ImageIcon(getClass().getResource("/images/icon.png")).getImage());
        this.setResizable(false);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void addHeader() {
        this.setTitle();
        this.addLines(1);
    }
    
    /**
     * Method responsible for defining the View Title.
     */
    protected void setTitle() {
        this.setTitle(ViewStyle.SYSTEM + this.title);
    }
    
    /**
     * Method responsible for adding Lines to View Modal.
     * @param number Number of Lines.
     */
    protected void addLines(int number) {
        for (int i = 0; i < number; i++)
            this.add(new JLabel(new FunctString().getSpaces(1000)));
    }
    
    /**
     * Method responsible for returning a new Title JLabel.
     * @param  title JLabel Title.
     * @return New Title JLabel.
     */
    protected JLabel createTitle(String title) {
        JLabel label = new JLabel(title);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE + 10));
        return label;
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
     * Method responsible for returning a new Image JLabel.
     * @param  url Image URL.
     * @return New Image JLabel.
     */
    protected JLabel createLabelImage(String url) {
        return new JLabel(new FunctView().createImage(url));
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
     * Method responsible for returning a new JTextField.
     * @param  id JTextField Id.
     * @param  title JTextField Title.
     * @param  size JTextField Size.
     * @return New JTextField.
     */
    protected JTextField createTextField(String id, String title, int size) {
        JTextField textField = new JTextField(size);
                   textField.setText(title);
                   textField.addActionListener(this.controller);
                   textField.addKeyListener(this.controller);
                   textField.setPreferredSize(new Dimension(ViewStyle.WIDTH, ViewStyle.HEIGHT));
                   textField.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                   this.textFields.put(id, textField);
        return     textField;
    }
    
    /**
     * Method responsible for returning a new Date JTextField.
     * @param  id JTextField Id.
     * @param  date JTextField Date.
     * @return New Date JTextField.
     */
    protected JTextField createTextFieldDate(String id, Date date) {
        return this.createTextField(id, new FunctDate().getFormattedDate(date), 8);
    }
    
    /**
     * Method responsible for returning a new No Editable JTextField.
     * @param  id JTextField Id.
     * @param  title JTextField Title.
     * @param  size JTextField Size.
     * @return New No Editable JTextField.
     */
    protected JTextField createTextFieldNoEditable(String id, String title, int size) {
        JTextField textField = this.createTextField(id, title, size);
                   textField.setEditable(false);
        return     textField;
    }
    
    /**
     * Method responsible for returning a new JComboBox.
     * @param  id JComboBox Id.
     * @param  values JComboBox Values.
     * @return New JComboBox.
     */
    public JComboBox createComboBox(String id, Object[] values) {
        JComboBox comboBox = new JComboBox(values);
                  comboBox.addActionListener(this.controller);
                  comboBox.addKeyListener(this.controller);
                  comboBox.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                  comboBox.setPreferredSize(new Dimension(265, 30));
                  this.comboBoxes.put(id, comboBox);
        return    comboBox;
    }
    
    /**
     * Method responsible for returning a new JCheckBox.
     * @param  id JCheckBox Id.
     * @param  title JCheckBox Title.
     * @return New JCheckBox.
     */
    public JCheckBox createCheckBox(String id, String title) {
        JCheckBox checkBox = new JCheckBox(title);
                  checkBox.addActionListener(this.controller);
                  checkBox.addKeyListener(this.controller);
                  checkBox.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                  this.checkBoxes.put(id, checkBox);
        return    checkBox;
    }
    
    /**
     * Method responsible for returning a new JButton.
     * @param  id JButton Id.
     * @param  title JButton Title.
     * @param  url JButton Image URL.
     * @return New JButton.
     */
    protected JButton createButton(String id, String title, String url) {
        JButton button = new JButton(new FunctView().createImage("icons/" + url + ".png"));
                button.setText(title);
                button.setToolTipText(title);
                button.addActionListener(this.controller);
                button.addKeyListener(this.controller);
                button.setFont(new Font(ViewStyle.STYLE, ViewStyle.CENTER, ViewStyle.SIZE));
                this.buttons.put(id, button);
        return  button;
    }
    
    /**
     * Method responsible for returning a new JRadioButton.
     * @param  id JRadioButton Id.
     * @param  title JRadioButton Title.
     * @return New JRadioButton.
     */
    public JRadioButton createRadioButton(String id, String title) {
        JRadioButton radioButton = new JRadioButton(title);
                     radioButton.addActionListener(this.controller);
                     radioButton.addKeyListener(this.controller);
                     radioButton.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                     this.radioButtons.put(id, radioButton);
        return       radioButton;
    }
    
    /**
     * Method responsible for returning a new JFileChooser.
     * @param  id JFileChooser Id.
     * @return New JFileChooser.
     */
    public JFileChooser createFileChooser(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                     fileChooser.setFileFilter(new FileNameExtensionFilter("TEXT FILES", "txt", "text"));
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning a new Directory JFileChooser.
     * @param  id Directory JFileChooser Id.
     * @return New Directory JFileChooser.
     */
    public JFileChooser createFileChooserDiretory(String id) {
        JFileChooser fileChooser = new JFileChooser();
                     fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                     this.fileChoosers.put(id, fileChooser);
        return       fileChooser;
    }
    
    /**
     * Method responsible for returning a new JScrollPane.
     * @param  id JScrollPane Id.
     * @return New JScrollPane of a JPanel.
     */
    public JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = new JScrollPane();
//                    scrollPane.setPreferredSize(new Dimension(390, 120));
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a new JScrollPane of a JPanel.
     * @param  id JScrollPane Id.
     * @param  painel JScrollPane JPanel.
     * @return New JScrollPane of a JPanel.
     */
    public JScrollPane createScrollPane(String id, JPanel painel) {
        JScrollPane scrollPane = new JScrollPane(painel);
                    scrollPane.setPreferredSize(new Dimension(390, 120));
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a new JList.
     * @param  id JList Id.
     * @return New JList.
     */
    public JList createList(String id) {
        JList  list = new JList();
               list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               list.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
               list.setPreferredSize(new Dimension(340, 100));
               list.addKeyListener(this.controller);
               this.createScrollPane(id, list);
               this.lists.put(id, list);
        return list;
    }
    
    /**
     * Method responsible for returning a new JScrollPane of a JList.
     * @param  id JScrollPane Id.
     * @param  list JScrollPane JList.
     * @return New JScrollPane of a JList.
     */
    private JScrollPane createScrollPane(String id, JList list) {
        JScrollPane scrollPane = new JScrollPane(list);
                    scrollPane.setPreferredSize(new Dimension(390, 120));
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a new JTextArea.
     * @param  id JTextArea Id.
     * @return New JTextArea.
     */
    public JTextArea createTextArea(String id) {
        JTextArea textArea = new JTextArea(10, 30);
                  textArea.addKeyListener(this.controller);
                  textArea.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
                  this.textAreas.put(id, textArea);
                  this.createScrollPane(id, textArea);
        return    textArea;
    }
    
    /**
     * Method responsible for returning a new JScrollPane of a JTextArea.
     * @param  id JScrollPane Id.
     * @param  textArea JScrollPane JTextArea.
     * @return New JScrollPane of a JTextArea.
     */
    private JScrollPane createScrollPane(String id, JTextArea textArea) {
        JScrollPane scrollPane = new JScrollPane(textArea);
                    scrollPane.setPreferredSize(new Dimension(380, 150));
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Metodo responsavel por retornar uma Nova JTable.
     * @param  id Identificador da JTable.
     * @return Nova JTable.
     */
    public JTable createTable(String id) {
        JTable table = new JTable(this.createTableModel());
               table.addKeyListener(this.controller);
        this.createScrollPane(id, table);
        this.tableModels.put(id, table.getModel());
        this.tableColumnModels.put(id, table.getColumnModel());
        this.tables.put(id, table);
        return table;
    }
    
    /**
     * Method responsible for returning a new DefaultTableModel.
     * @return New DefaultTableModel.
     */
    private DefaultTableModel createTableModel() {
        return new DefaultTableModel() {
                   @Override
                   public boolean isCellEditable(int rowIndex, int colIndex){   
                        return false;   
                   }};
    }
    
    /**
     * Method responsible for returning a new JScrollPane of a JTable.
     * @param  id JScrollPane Id.
     * @param  table JScrollPane JTable.
     * @return New JScrollPane of a JTable.
     */
    public JScrollPane createScrollPane(String id, JTable table) {
        JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setPreferredSize(new Dimension(380, 150));
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for adding the Columns on Table.
     * @param id Table Id.
     * @param values Columns Values.
     */
    protected void addColumns(String id, String[] values) {
        for (String value : values)
            ((DefaultTableModel) this.tableModels.get(id)).addColumn(value);
    }
    
    /**
     * Method responsible for defining the Columns Size on Table.
     * @param id Table Id. 
     * @param size Array de Tamanhos.
     */
    protected void setColumnsSize(String id, int[] size) {
        for (int i = 0; i < size.length; i++)
            this.tables.get(id).getColumnModel().getColumn(i).setPreferredWidth(size[i]);
    }
    
    /**
     * Method responsible for adding Lines on Table.
     * @param id Table Id. 
     * @param values Values.
     */
    public void addRows(String id, String[][] values) {
        this.clearTable(id);
        for (String[] value : values) {
            ((DefaultTableModel) this.tableModels.get(id)).addRow(value);
            this.tables.get(id).setEditingRow(JTable.AUTO_RESIZE_NEXT_COLUMN);
            this.tables.get(id).setEditingRow(0);
        }
    }
    
    /**
     * Method responsible to clear the Table.
     * @param id Table Id.
     */
    public void clearTable(String id) {
        while (this.tableModels.get(id).getRowCount() > 0)
            ((DefaultTableModel) this.tableModels.get(id)).removeRow(0);
        this.tables.get(id).removeAll();
    }
    
    /**
     * Method responsible for defining a Size to View Modal.
     * @param y Axis Y.
     * @param x Axis X.
     */
    @Override
    public void setSize(int y, int x) {
        super.setSize(y, x);
        this.setLocationRelativeTo(this.getParent());
    }
}
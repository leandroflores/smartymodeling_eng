package view;

import controller.Controller;
import funct.FunctView;
import funct.FunctString;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * <p>Class of View <b>Panel</b>.</p> 
 * <p>Class responsible for defining a Model for the <b>Panels</b> of SMartyModeling.</p>
 * @author Leandro
 * @since  18/05/2019
 * @see    controller.Controller
 * @see    javax.swing.JPanel
 */
public abstract class Panel extends JPanel {
    protected Controller controller;
    
    protected HashMap<String, JList> lists;
    protected HashMap<String, JButton> buttons;
    protected HashMap<String, JComboBox> comboBoxes;
    protected HashMap<String, JCheckBox> checkBoxes;
    protected HashMap<String, JTextField> textFields;
    protected HashMap<String, JFileChooser> fileChoosers;
    
    protected HashMap<String, JTable> tables;
    protected HashMap<String, TableModel> tableModels;
    protected HashMap<String, JScrollPane> scrollPanes;
    protected HashMap<String, TableColumnModel> tableColumnModels;
    
    /**
     * Default constructor method of Classe.
     */
    public Panel() {
        super();
        this.init();
        this.setSettings();
    }
    
    /**
     * Method responsible for starting the Lists.
     */
    private void init() {
        this.lists        = new HashMap<>();
        this.buttons      = new HashMap<>();
        this.textFields   = new HashMap<>();
        this.comboBoxes   = new HashMap<>();
        this.checkBoxes   = new HashMap<>();
        this.fileChoosers = new HashMap<>();
        
        this.tables            = new HashMap<>();
        this.tableModels       = new HashMap<>();
        this.scrollPanes       = new HashMap<>();
        this.tableColumnModels = new HashMap<>();
    }
    
    /**
     * Method responsible for adding the Components.
     */
    protected abstract void addComponents();
    
    /**
     * Method responsible for returning the Default Color.
     * @return Default Color.
     */
    protected Color getDefaultColor() {
        return this.getBackground();
    }
    
    /**
     * Method responsible for returning the Focus Color.
     * @return Focus Color.
     */
    protected Color getFocusColor() {
        return ColorUIResource.LIGHT_GRAY;
    }
    
    /**
     * Method responsible for defining the settings for the Panel.
     */
    private void setSettings() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
    
    /**
     * Method responsible for adding Lines to Panel.
     * @param number Number of Lines.
     */
    protected void addLines(int number) {
        for (int i = 0; i < number; ++i)
            this.add(new JLabel(new FunctString().getSpaces(1000)));
    }
    
    /**
     * Method responsible for adding Lines by Row and Colum Settings.
     * @param rows Rows Number.
     * @param columns Columns Number.
     */
    protected void addLines(int rows, int columns) {
        for (int i = 0; i < rows; i++) {
            for (int x = 0; x < columns; x++)
                this.add(this.createLabel(""));
        }
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
     * Method responsible for returning a new Center JLabel.
     * @param  title JLabel Title.
     * @param  size JLabel Size.
     * @return New Center JLabel.
     */
    protected JLabel createCenterLabel(String title, int size) {
        JLabel label = new JLabel(title, SwingConstants.RIGHT);
               label.setAlignmentX(Component.CENTER_ALIGNMENT);
               label.addKeyListener(this.controller);
               label.setFont(new Font(ViewStyle.STYLE, ViewStyle.BOLD, ViewStyle.SIZE));
               label.setPreferredSize(new Dimension(size, ViewStyle.HEIGHT));
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
     * Method responsible for returning a new JComboBox.
     * @param  id JComboBox Id.
     * @param  values JComboBox Values.
     * @param  size JComboBox Size.
     * @param  object Selected Object.
     * @return New JComboBox.
     */
    protected JComboBox createComboBox(String id, Object[] values, int size, Object object) {
        JComboBox comboBox = new JComboBox(values);
                  comboBox.setSelectedItem(object);
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
     * Method responsible for returning a new JCheckBox.
     * @param  id JCheckBox Id.
     * @param  title JCheckBox Title.
     * @param  selected Selected Flag.
     * @return New JCheckBox.
     */
    public JCheckBox createCheckBox(String id, String title, boolean selected) {
        JCheckBox checkBox = new JCheckBox(title);
                  checkBox.setSelected(selected);
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
     * @return New JButton.
     */
    protected JButton createButton(String id, String title) {
        JButton button = new JButton();
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
     * @param  url JButton Image URL.
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
     * Method responsible for returning a new JList.
     * @param  id JList Id.
     * @return New JList.
     */
    public JList createList(String id) {
        JList  list = new JList();
               list.setBorder(BorderFactory.createLineBorder(Color.BLACK));
               list.setFont(new Font(ViewStyle.STYLE, ViewStyle.STANDARD, ViewStyle.SIZE));
//               list.setPreferredSize(new Dimension(340, 100));;
               list.addKeyListener(this.controller);
               this.createScrollPane(id, list);
               this.lists.put(id, list);
        return list;
    }
    
    /**
     * Method responsible for returning a new JScrollPane of a JList.
     * @param  id Id JScrollPane.
     * @param  list JList.
     * @return New JScrollPane of a JList.
     */
    private JScrollPane createScrollPane(String id, JList list) {
        JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setViewportView(list);
                    scrollPane.setPreferredSize(new Dimension(390, 120));
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
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
    }
    
    /**
     * Method responsible for returning a new JTable.
     * @param  id JTable Id.
     * @return New JTable.
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
                   public boolean isCellEditable(int row, int col){   
                        return true;
                   }};
    }
    
    /**
     * Method responsible for returning a new JScrollPane.
     * @param  id JScrollPane Id.
     * @return New JScrollPane.
     */
    public JScrollPane createScrollPane(String id) {
        JScrollPane scrollPane = new JScrollPane();
                    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
                    this.scrollPanes.put(id, scrollPane);
        return      scrollPane;
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
    public void addRows(String id, Object[][] values) {
        this.clearTable(id);
        for (Object[] value : values) {
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
     * Metodo responsavel por retornar um New JFileChooser.
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
     * Method responsible for returning the Constraints.
     * @param  width Width Constraints.
     * @param  height Height Constraints.
     * @param  x X Position Grid.
     * @param  y Y Position Grid.
     * @return Constraints.
     */
    protected GridBagConstraints getConstraints(int width, int height, int x, int y) {
        GridBagConstraints constraints = new GridBagConstraints();
                           constraints.gridheight = height;
                           constraints.gridwidth  = width;
                           constraints.gridx      = x;
                           constraints.gridy      = y;
                           constraints.fill       = GridBagConstraints.HORIZONTAL;
        return constraints;
    }
}
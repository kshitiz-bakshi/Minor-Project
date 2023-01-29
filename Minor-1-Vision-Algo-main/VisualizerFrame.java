import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

@SuppressWarnings("serial")
public class VisualizerFrame extends JFrame {

    private final int MAX_SPEED = 1000;
    private final int MIN_SPEED = 1;
    private final int MAX_SIZE = 300;
    private final int MIN_SIZE = 1;
    private final int DEFAULT_SPEED = 20;
    private final int DEFAULT_SIZE = 100;
    
    //private final String[] Sorts = {"Bubble", "Selection", "Insertion", "Gnome", "Merge", "Radix LSD", "Radix MSD", "Shell", "Bubble(fast)", "Selection(fast)", "Insertion(fast)", "Gnome(fast)"};
    private final String[] Sorts = {"Bubble", "Selection", "Insertion", "Merge", "Shell"};
    private java.util.HashMap<String,String> map;
    private int sizeModifier;

    private JPanel wrapper;
    private JPanel arrayWrapper;
    private JPanel buttonWrapper;
    private JPanel[] squarePanels;
    private JButton start;
    private JComboBox<String> selection;
    private JSlider speed;
    private JSlider size;
    private JLabel speedVal;
    private JLabel sizeVal;
    private GridBagConstraints c;
    //private JCheckBox stepped;
    //JTextArea var
    JTextArea serial_print_area;
    JScrollPane serial_scroll_pane;
    //j
    ListText lt = new ListText();
    public VisualizerFrame(){
        super("Sorting Visualizer");
        setLayout(new GridLayout(1,2,100,100));
        start = new JButton("Start");
        buttonWrapper = new JPanel();
        arrayWrapper = new JPanel();
        wrapper = new JPanel();
        selection = new JComboBox<String>();
        speed = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
        size = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
        speedVal = new JLabel("Speed: 20 ms");
        sizeVal = new JLabel("Size: 50 values");
        //stepped = new JCheckBox("Stepped Values");
        c = new GridBagConstraints();
        
        for(String s : Sorts) selection.addItem(s);
        map=new java.util.HashMap();
        map.put("Bubble","BubbleSort.java");
        map.put("Selection","SelectionSort.java");
        map.put("Insertion","InsertionSort.java");
        //map.put("Gnome","GnomeSort.java");
        map.put("Merge","MergeSort.java");
        // map.put("Radix MSD","RadixSort.java");
        map.put("Shell","ShellSort.java");
        // map.put("Bubble(fast)", "BubbleSort.java");
        // map.put("Selection(fast)", "SelectionSort.java");
        // map.put("Insertion(fast)", "InsertionSort.java");
        //map.put("Gnome(fast)","GnomeSort.java");
        
        arrayWrapper.setLayout(new GridBagLayout());
        wrapper.setLayout(new BorderLayout());

        c.insets = new Insets(0,1,0,1);
        c.anchor = GridBagConstraints.SOUTH;
        
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SortingVisualizer.startSort((String) selection.getSelectedItem());
            }
        });
        
        //stepped.addActionListener(new ActionListener() {
        //    public void actionPerformed(ActionEvent e) {
        //        SortingVisualizer.stepped = stepped.isSelected();
        //    }
        //});
        
        speed.setMinorTickSpacing(10);
        speed.setMajorTickSpacing(100);
        speed.setPaintTicks(true);
        
        speed.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                speedVal.setText(("Speed: " + Integer.toString(speed.getValue()) + "ms"));
                validate();
                SortingVisualizer.sleep = speed.getValue();
            }
        });
        
        size.setMinorTickSpacing(10);
        size.setMajorTickSpacing(100);
        size.setPaintTicks(true);
        
        size.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent arg0) {
                sizeVal.setText(("Size: " + Integer.toString(size.getValue()) + " values"));
                validate();
                SortingVisualizer.sortDataCount = size.getValue();
            }
        });

        //buttonWrapper.add(stepped);
        buttonWrapper.add(speedVal);
        buttonWrapper.add(speed);
        buttonWrapper.add(sizeVal);
        buttonWrapper.add(size);
        buttonWrapper.add(start);
        buttonWrapper.add(selection);
        
        wrapper.add(buttonWrapper, BorderLayout.WEST);
        wrapper.add(arrayWrapper , BorderLayout.PAGE_END) ;
        
        

        setExtendedState(JFrame.MAXIMIZED_BOTH );
        
        addComponentListener            (new ComponentListener() {

            @Override
            public void componentResized(ComponentEvent e) {
                // Reset the sizeModifier
                // 90% of the windows height, divided by the size of the sorted array.
                sizeModifier = (int) ((getHeight()*0.9)/(squarePanels.length));
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                // Do nothing
            }

            @Override
            public void componentShown(ComponentEvent e) {
                // Do nothing
            }

            @Override
            public void componentHidden(ComponentEvent e) {
                // Do nothing
            }
            
        });
        serial_print_area = new JTextArea();
        serial_print_area.setEditable(false);
        DefaultCaret caret = (DefaultCaret)serial_print_area.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        serial_scroll_pane = new JScrollPane(serial_print_area);
        //serial_scroll_pane.setBounds(40, 100, 700, 500);
        // serial_scroll_pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        // serial_scroll_pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // add(serial_scroll_pane);
        functionForComboBox();
        wrapper.setBounds(800,100,100,700);
        add(wrapper);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    void functionForComboBox()
    {
        String keyy=(String)selection.getSelectedItem();
        String val=map.get(keyy);
        for(String data : lt.fileToList(val))
        {
            serial_print_area.append(data+"\n");
        }
        selection.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent event)
            {
                if(event.getStateChange() == ItemEvent.SELECTED)
                {
                    String keyy=(String)selection.getSelectedItem();
                    String val=map.get(keyy);
                    serial_print_area.setText("");
                    for(String data : lt.fileToList(val))
                    {
                        serial_print_area.append(data+"\n");
                    }
                }
            }
        }
        );
    }
    // preDrawArray reinitializes the array of panels that represent the values. They are set based on the size of the window.
    public void preDrawArray(Integer[] squares){
        squarePanels = new JPanel[SortingVisualizer.sortDataCount];
        arrayWrapper.removeAll();
        // 90% of the windows height, divided by the size of the sorted array.
        sizeModifier =  (int) ((getHeight()*0.9)/(squarePanels.length));
        for(int i = 0; i<SortingVisualizer.sortDataCount; i++){
            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
            squarePanels[i].setBackground(Color.blue);
            arrayWrapper.add(squarePanels[i], c);
        }
        repaint();
        validate();
    }
    
    public void reDrawArray(Integer[] x){
        reDrawArray(x, -1);
    }
    
    public void reDrawArray(Integer[] x, int y){
        reDrawArray(x, y, -1);
    }
    
    public void reDrawArray(Integer[] x, int y, int z){
        reDrawArray(x, y, z, -1);
    }
    
    // reDrawArray does similar to preDrawArray except it does not reinitialize the panel array.
    public void reDrawArray(Integer[] squares, int working, int comparing, int reading){
        arrayWrapper.removeAll();
        for(int i = 0; i<squarePanels.length; i++){
            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i]*sizeModifier));
            if (i == working){
                squarePanels[i].setBackground(Color.green);             
            }else if(i == comparing){
                squarePanels[i].setBackground(Color.red);           
            }else if(i == reading){
                squarePanels[i].setBackground(Color.yellow);            
            }else{
                squarePanels[i].setBackground(Color.blue);
            }
            arrayWrapper.add(squarePanels[i], c);
        }
        repaint();
        validate();
    }
    
}

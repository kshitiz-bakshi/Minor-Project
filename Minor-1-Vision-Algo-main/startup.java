import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class startup extends JFrame {



    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    startup frame = new startup();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public startup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 683, 470);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

         
        
        
        JLabel liveScreen = new JLabel();
        liveScreen.setBackground(Color.CYAN);
        liveScreen.setOpaque(true);
        liveScreen.setBounds(100, 40, 456, 216);
        liveScreen.setIcon(new ImageIcon(
        (new ImageLoader()).loadImage("poster.png",liveScreen.getWidth(),liveScreen.getHeight())
        ));
        contentPane.add(liveScreen);

       
        
        JButton bst = new JButton("BST");
        bst.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BSTVisualization.main(new String[]{});
            }
        });


        bst.setBounds(280, 318, 97, 30);
        contentPane.add(bst);
        
        JButton visualizer = new JButton("Sorting Visualiser");
        visualizer.setBounds(385, 318, 200, 25);
        visualizer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SortingVisualizer.main(new String[]{});
            }
        });
        contentPane.add(visualizer);
	}
}
import java.awt.*;   
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class feed {  
    // main method  
    static JFrame f ; 
    static JLabel feedbackform , l2 , l3; 
   

    public static void main(String args[]) {    

    // creating a frame  
    Frame f = new Frame("FEEDBACK FORM");    
    JLabel feedbackform = new JLabel();
    JLabel l2 = new JLabel();
    JPanel p = new JPanel();
    JButton b = new JButton("Submit");
    JButton b3 = new JButton("close");
    JButton b4 = new JButton("Feedback History");
    JLabel l3 = new JLabel();

        
    // creating objects of textfield  
    TextField t1, t2;    
    // instantiating the textfield objects  
    // setting the location of those objects in the frame 
    feedbackform.setText(" Write your Feedback here");
    l2.setText("how was your experience");
    p.add(feedbackform) ; 


    t1 = new TextField("");    
    t1.setBounds(50, 100, 400, 30);    
    t2 = new TextField("");    
    t2.setBounds(50, 200, 400, 30);   
    b.setBounds(50, 300, 100, 30);
    b3.setBounds(200, 300, 100, 30);
    b4.setBounds(200, 350, 200, 30);
    feedbackform.setBounds(50, 50, 200, 30);    
        l2.setBounds(50, 150, 200, 30);
        l3.setBounds(50, 450, 500, 30);
    // adding the components to frame  
    f.add(p) ; 
    f.add(t1);  
    f.add(t2);   
    f.add(b);
    f.add(feedbackform) ; 
    f.add(l2) ; 
    f.add(l3) ; 
    f.add(b3) ; 
    f.add(b4) ; 
    // setting size, layout and visibility of frame   
    f.setSize(600,600);    
    f.setLayout(null);    
    f.setVisible(true);    
    b.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {   
            String s = t1.getText() ; 
            l3.setText(s);
            JOptionPane.showMessageDialog(null, "THANKS FOR YOUR VALUABLE FEEDBACK", "SORTING VISUALIZER", 1);
            
           
        
        }
    });
    b4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {   
            String s = t1.getText() ; 
            l3.setText(s);
        
        
        }
    });
   
    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);
        }
    });
   
    
}   

}    
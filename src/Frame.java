import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
    private static final long serialVersionUID  = 1L;

    private SortPanel panel = new SortPanel(); 

    JButton btn1; 

    public Frame(){
        this.setTitle("Bubble Sort");
        this.getContentPane().setPreferredSize(new Dimension(1200,600));
        this.getContentPane().add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public SortPanel getPanel(){
        return this.panel;
    }
}

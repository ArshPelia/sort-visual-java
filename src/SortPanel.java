import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.Random;

public class SortPanel extends JPanel{
    private static final long serialUID = 1L;

    private Random random = new Random();
    private int[] arr = new int[50];
    private int arr_idx;
    private int compare_idx;

    JButton start = new JButton("Start");
    JButton reset = new JButton("Reset");

    private boolean isRunning;

    /**
     * Constructor for the SortPanel class.
     * Initializes the arr_idx and compare_idx variables.
     * Sets up the UI components and event listeners for the "Start" and "Reset" buttons.
     */
    public SortPanel(){
        this.arr_idx = 0;
        this.compare_idx = Integer.MAX_VALUE;

        this.setarr();

        // Configure and add the "Start" button
        start.setBackground(Color.white);
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    isRunning = true;
                    BubbleSortAnimate();
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        // Configure and add the "Reset" button
        reset.setBackground(Color.white);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                setarr();
                compare_idx = Integer.MAX_VALUE;
                arr_idx = 0;
                isRunning = false;
                repaint();
            }
        });
        this.add(start);
        this.add(reset);
    }

    /**
     * Retrieves the array arr.
     * @return An array of integers.
     */
    public int[] getarr(){
        return this.arr;
    }

    /**
     * Populates the array arr with random values.
     */
    public void setarr(){
        for (int i = 0; i < this.arr.length; i++){
            this.arr[i] = random.nextInt(510) + 1;
        }
    }

    /**
     * Performs one step of the sorting algorithm.
     * Compares the current element with the next element and swaps them if necessary.
     * Updates the indexes (arr_idx and compare_idx) accordingly.
     */
    public void SortOne(){
        if (arr[compare_idx] > arr[compare_idx + 1]){
            int temp = arr[compare_idx];
            arr[compare_idx] = arr[compare_idx+1];
            arr[compare_idx + 1 ] = temp;
        }
        if ((compare_idx+1) >= (arr.length - arr_idx -1)) {
            arr_idx++;
            compare_idx = 0;
        }
        else compare_idx++;
    }

    /**
     * Checks if the array is sorted in ascending order.
     * @return true if the array is sorted, false otherwise.
     */
    public boolean isSorted(){
        for (int i = 0; i < arr.length; i++){
            if(arr[i] > arr[i + 1]){
                return false;
            }
        }
        return true;
    }

    /**
     * Performs the bubble sort algorithm with animation.
     * Uses a timer to repeatedly call the SortOne() function until the array is sorted.
     * Stops the timer when the array is sorted.
     * Throws an exception if an error occurs during the animation.
     */
    public void BubbleSortAnimate() throws Exception{
        compare_idx = 0;

        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(isSorted()){
                    compare_idx = Integer.MAX_VALUE;
                    ((Timer)e.getSource()).stop();
                } else{
                    if(isRunning){
                        SortOne();
                    }
                }
                repaint();
            }
        });
        timer.start();
    }

    /**
     * Overrides the paintComponent method of the JPanel class to customize the component's appearance.
     * Draws rectangles representing the array elements on the panel.
     * Highlights the elements being compared during the sorting process.
     * @param g The Graphics object used for drawing.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);

        // Draw rectangles representing the array elements
        for (int i = 0; i < arr.length; i++){
            g.setColor(Color.BLACK);

            if(i == compare_idx || i == compare_idx+1){
                g.setColor(Color.CYAN);
            }

            g.drawRect(i*15, 600 - arr[i], 14, arr[i]);
            g.fillRect(i*15, 600 - arr[i], 14, arr[i]);
        }
    }
}

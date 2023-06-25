import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class QuickSortPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Random random = new Random();
    private int[] arr = new int[50];
    private int leftIdx;
    private int rightIdx;

    JButton start = new JButton("Start");
    JButton reset = new JButton("Reset");

    private boolean isRunning;

    public QuickSortPanel() {
        this.leftIdx = 0;
        this.rightIdx = arr.length - 1;

        setArr();

        start.setBackground(Color.WHITE);
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    isRunning = true;
                    quickSortAnimate(arr, leftIdx, rightIdx);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        reset.setBackground(Color.WHITE);
        reset.setFocusPainted(false);
        reset.setBorderPainted(false);
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setArr();
                leftIdx = 0;
                rightIdx = arr.length - 1;
                isRunning = false;
                repaint();
            }
        });
        this.add(start);
        this.add(reset);
    }

    public int[] getArr() {
        return this.arr;
    }

    public void setArr() {
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = random.nextInt(510) + 1;
        }
    }

    public void swapElements(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }

        swapElements(arr, i + 1, right);
        return i + 1;
    }

    public void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotIndex = partition(arr, left, right);
            quickSort(arr, left, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, right);
        }
    }

    public void quickSortAnimate(int[] arr, int left, int right) {
        Timer timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    if (left < right) {
                        int pivotIndex = partition(arr, left, right);
                        quickSortAnimate(arr, left, pivotIndex - 1);
                        quickSortAnimate(arr, pivotIndex + 1, right);
                    }
                } else {
                    ((Timer) e.getSource()).stop();
                }
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.white);

        for (int i = 0; i < arr.length; i++) {
            g.setColor(Color.BLACK);

            if (i == leftIdx || i == rightIdx) {
                g.setColor(Color.CYAN);
            }

            g.drawRect(i * 15, 600 - arr[i], 14, arr[i]);
            g.fillRect(i * 15, 600 - arr[i], 14, arr[i]);
        }
    }
}

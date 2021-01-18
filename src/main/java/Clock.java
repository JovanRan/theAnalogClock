import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Clock extends JFrame implements ActionListener {


    // Creating variables for Buttons.
    private JButton start;
    private JButton stop;
    private JButton reset;
    private JButton randomBackground;
    private JButton randomClockColor;


    private ClockPanel panel2;

    // Here is our main method where the background for the clock is created, also we place the buttons, size.

    public Clock() {

        //Here we add a Title "Clock"

        setTitle("Clock");

        panel2 = new ClockPanel();
        panel2.setRunning(false);
        this.add(panel2, BorderLayout.CENTER);

        start = new JButton("Start");
        start.addActionListener(this);

        stop = new JButton("Stop");
        stop.addActionListener(this);

        reset = new JButton("Reset");
        reset.addActionListener(this);

        randomBackground = new JButton("RandomBackground");
        randomBackground.addActionListener(this);

        randomClockColor = new JButton("ClockBackground");
        randomClockColor.addActionListener(this);


        JPanel buttons = new JPanel(new FlowLayout());

        // Making buttons visible on the clock panel.

        buttons.add(start);
        buttons.add(stop);
        buttons.add(reset);
        buttons.add(randomBackground);
        buttons.add(randomClockColor);
        this.add(buttons, BorderLayout.SOUTH);

        // Size is defined here.

        this.setSize(700, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //check once again
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Clock();
    }

    //Defining what should happen when one of the buttons is being clicked on by a user.

    @Override
    public void actionPerformed(ActionEvent e) {

        Random random = new Random();
        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();

        float r2 = random.nextFloat();
        float g2 = random.nextFloat();
        float b2 = random.nextFloat();


        if (e.getSource() == start) {
            panel2.setRunning(true);
        }

        if (e.getSource() == stop) {
            panel2.setRunning(false);
        }

        if (e.getSource() == reset) {
            panel2.setSeconds(0);
            panel2.setMinutes(0);
            panel2.setHours(0);
            panel2.setRunning(false);
            panel2.repaint();
        }

        if (e.getSource() == randomBackground) {
            panel2.paint(new Color(r, g, b));
            panel2.repaint();
            random = new Random();
        }

        if (e.getSource() == randomClockColor) {
            panel2.paintClock(new Color(r2, g2, b2));
            panel2.repaint();
            random = new Random();
        }



    }
}
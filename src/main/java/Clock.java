import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Random;

public class Clock extends JFrame implements ActionListener {


    // Creating variables for Buttons.
    private JButton start;
    private JButton stop;
    private JButton reset;
    private JButton randomBackground;
    private JButton randomClockColor;
    private JButton currentTime;


    private ClockPanel panel;

    // Here is our main method where the background for the clock is created, also we place the buttons, size.

    public Clock() {

        //Here we add a Title "Clock"

        setTitle("Clock");

        panel = new ClockPanel();
        panel.setRunning(true);
        this.add(panel, BorderLayout.CENTER);

        start = new JButton("Start");
        start.addActionListener(this);

        stop = new JButton("Stop");
        stop.addActionListener(this);

        reset = new JButton("Reset");
        reset.addActionListener(this);

        currentTime = new JButton("CurrentTime");
        currentTime.addActionListener(this);

        randomBackground = new JButton("RandomBackground");
        randomBackground.addActionListener(this);

        randomClockColor = new JButton("ClockBackground");
        randomClockColor.addActionListener(this);


        JPanel buttons = new JPanel(new FlowLayout());

        // Making buttons visible on the clock panel.

        buttons.add(start);
        buttons.add(stop);
        buttons.add(reset);
        buttons.add(currentTime);
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
            panel.setRunning(true);
            start.setBackground(Color.green);
            stop.setBackground(new JButton().getBackground());
        }

        if (e.getSource() == stop) {
            panel.setRunning(false);
            start.setBackground(new JButton().getBackground());
            stop.setBackground(Color.green);
        }

        if (e.getSource() == reset) {
            panel.setSeconds(0);
            panel.setMinutes(0);
            panel.setHours(0);
            panel.setRunning(false);
            panel.repaint();

            start.setBackground(new JButton().getBackground());
            stop.setBackground(new JButton().getBackground());
        }

        if(e.getSource() == currentTime) {

           panel.setSeconds(LocalTime.now().getSecond());
           panel.setMinutes(LocalTime.now().getMinute());
           panel.setHours(LocalTime.now().getHour());
           panel.setRunning(true);
           panel.repaint();

            start.setBackground(new JButton().getBackground());
            stop.setBackground(new JButton().getBackground());
        }

        if (e.getSource() == randomBackground) {
            panel.paint(new Color(r, g, b));
            panel.repaint();

            start.setBackground(new JButton().getBackground());
            stop.setBackground(new JButton().getBackground());
        }

        if (e.getSource() == randomClockColor) {
            panel.paintClock(new Color(r2, g2, b2));
            panel.repaint();

            start.setBackground(new JButton().getBackground());
            stop.setBackground(new JButton().getBackground());
        }



    }
}
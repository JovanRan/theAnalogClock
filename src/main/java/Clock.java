import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Random;
import java.util.ArrayList;

/* As the project name "theAnalogClock" already says, the project is about an analog clock.
 * You can change the background of the window and the background of the clock, how often you want and always change it to different colors.
 * It is also possible to use the Clock as a stopwatch, for this you have the buttons "Start", "Stop", "Reset".
 * To change from the stopwatch to the clock, you can use the button "CurrentTime".
 * Another feature is the actual news. You can check the news with the button "news".
 *
 * To start and see the clock you have to run the class called clock.
 *
 */


public class Clock extends JFrame implements ActionListener {

    // Creating variables for Buttons.
    private final JButton start;
    private final JButton stop;
    private final JButton reset;
    private final JButton randomBackground;
    private final JButton randomClockColor;
    private final JButton currentTime;
    private final JButton next;


    private CardLayout cardLayout = new CardLayout();
    private JPanel cards = new JPanel(cardLayout);
    private ArrayList<AbstractClockPanel> clockPanels = new ArrayList<>();
    private ClockPanel panel = new ClockPanel();
    private ClockPanelNews panelNews = new ClockPanelNews();

    // Here is our main method where the background for the clock is created, also we place the buttons, size.

    public Clock() {

        //Here we add a Title "Clock"

        setTitle("Clock");

        panel.setRunning(true);
        panelNews.setRunning(true);

        panel.repaint();
        panelNews.repaint();

        clockPanels.add(panel);
        clockPanels.add(panelNews);

        for (AbstractClockPanel panel : clockPanels) {
            cards.add(panel);
        }


        this.add(cards, BorderLayout.CENTER);

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

        next = new JButton("News");
        next.addActionListener(this);


        JPanel buttons = new JPanel(new FlowLayout());

        // Making buttons visible on the clock panel.

        buttons.add(start);
        buttons.add(stop);
        buttons.add(reset);
        buttons.add(currentTime);
        buttons.add(randomBackground);
        buttons.add(randomClockColor);
        buttons.add(next);
        this.add(buttons, BorderLayout.SOUTH);

        // Size of the Window is defined here.

        this.setSize(700, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
            for (AbstractClockPanel panel : clockPanels) {
                panel.setRunning(true);

                start.setBackground(Color.green);
                stop.setBackground(new JButton().getBackground());
            }
        }

        if (e.getSource() == stop) {
            for (AbstractClockPanel panel : clockPanels) {
                panel.setRunning(false);

                start.setBackground(new JButton().getBackground());
                stop.setBackground(Color.green);
            }
        }

        if (e.getSource() == reset) {
            for (AbstractClockPanel panel : clockPanels) {
                panel.setSeconds(0);
                panel.setMinutes(0);
                panel.setHours(0);
                panel.setRunning(false);
                panel.repaint();

                start.setBackground(new JButton().getBackground());
                stop.setBackground(new JButton().getBackground());
            }
        }

        if(e.getSource() == currentTime) {
            for (AbstractClockPanel panel : clockPanels) {
                panel.setSeconds(LocalTime.now().getSecond());
                panel.setMinutes(LocalTime.now().getMinute());
                panel.setHours(LocalTime.now().getHour());
                panel.setRunning(true);
                panel.repaint();

                start.setBackground(new JButton().getBackground());
                stop.setBackground(new JButton().getBackground());
            }
        }

        if (e.getSource() == randomBackground) {
            for (AbstractClockPanel panel : clockPanels) {
                panel.paint(new Color(r, g, b));
                panel.repaint();

                start.setBackground(new JButton().getBackground());
                stop.setBackground(new JButton().getBackground());
            }
        }

        if (e.getSource() == randomClockColor) {
            for (AbstractClockPanel panel : clockPanels) {
                panel.paintClock(new Color(r2, g2, b2));
                panel.repaint();

                start.setBackground(new JButton().getBackground());
                stop.setBackground(new JButton().getBackground());
            }
        }

        if (e.getSource() == next) {
            cardLayout.next(cards);

            start.setBackground(new JButton().getBackground());
            stop.setBackground(new JButton().getBackground());

        }
    }
}
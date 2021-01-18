import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.time.LocalDate;
import java.time.LocalTime;

public class ClockPanel extends JPanel implements Runnable {

    public ClockPanel() {
        start();
    }

    private int seconds = LocalTime.now().getSecond();
    private int minutes = LocalTime.now().getMinute();
    private int hours = LocalTime.now().getHour();
    boolean running;
    private Thread thread;
    double angle = Math.PI / -30;


    Color color = Color.blue;
    Color circleColor = Color.white;

    public void paintComponent(Graphics g) {

        Graphics2D gd = (Graphics2D) g;
        gd.setColor(color);
        gd.fillRect(0, 0, this.getWidth(), this.getHeight());
        Font schrift = new Font("Arial", Font.BOLD, 30);
        gd.setFont(schrift);


        Graphics2D circle = (Graphics2D) g;
        circle.setStroke(new BasicStroke(10));
        circle.setColor(circleColor);
        circle.fillOval(185, 45, 325, 325);


        Graphics2D outsideCircle = (Graphics2D) g;
        outsideCircle.setColor(Color.black);
        outsideCircle.setStroke(new BasicStroke(8));
        outsideCircle.drawOval(182, 42, 330, 330);


        Graphics2D digitalClock = (Graphics2D) g;
        digitalClock.drawString((Integer.toString(hours)), 270, 420);
        digitalClock.drawString(":", 310, 420);
        digitalClock.drawString((Integer.toString(minutes)), 330, 420);
        digitalClock.drawString(":", 370, 420);
        digitalClock.drawString((Integer.toString(seconds)), 390, 420);
        digitalClock.drawString((String.valueOf(LocalDate.now())), 270, 30);


        gd.drawString("9", (getWidth() / 2) - 145, (getHeight() / 2));
        gd.drawString("3", (getWidth() / 2) + 135, (getHeight() / 2));
        gd.drawString("12", (getWidth() / 2) - 17, (getHeight() / 2) - 130);
        gd.drawString("6", (getWidth() / 2) - 10, (getHeight() / 2) + 145);


        AffineTransform at = new AffineTransform();
        at.setToScale(1, -1);
        AffineTransform aff = new AffineTransform();
        aff.setToTranslation(this.getWidth() / 2, this.getHeight() / 2);
        at.preConcatenate(aff);
        gd.transform(at);

        // A long, thinner "minute" hand. Creating the hand and adding a color.

        gd.setColor(Color.RED);
        gd.setStroke(new BasicStroke(1));
        Line2D.Double line = new Line2D.Double(0, 0, 0, 120);
        at.setToRotation(angle * seconds, 0, 0);
        Shape ss = at.createTransformedShape(line);
        gd.draw(ss);

        // A long, thinner "minute" hand. Creating the hand and adding a color.

        gd.setColor(Color.BLACK);
        gd.setStroke(new BasicStroke(3));
        Line2D.Double line2 = new Line2D.Double(0, 0, 0, 100);
        at.setToRotation(angle * minutes, 0, 0);
        Shape sm = at.createTransformedShape(line2);
        gd.draw(sm);

        // A short, thick "hour" hand. Creating the hand and adding a color.

        gd.setColor(Color.BLACK);
        gd.setStroke(new BasicStroke(5));
        Line2D.Double line3 = new Line2D.Double(0, 0, 0, 80);
        at.setToRotation((angle*5) * hours, 0, 0);
        Shape sh = at.createTransformedShape(line3);
        gd.draw(sh);

    }


    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    // Creating methods for the function "reset".

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setSeconds(int seconds){
        this.seconds = seconds;
    }

    public void setMinutes(int minutes){
        this.minutes = minutes;
    }

    public void setHours(int hours){
        this.hours = hours;
    }


    public void run() {
        while (true) {
            if (running == true) {
                if (seconds > 59) {
                    seconds = 0;
                } else {
                    seconds++;
                }
                repaint();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (running == true && seconds > 59) {
                if (minutes > 59) {
                    minutes = 0;
                } else {
                    minutes++;
                }
                repaint();
            }

            if (running == true && minutes > 59) {
                if (hours > 23) {
                    hours = 0;
                } else {
                    hours++;
                }
                repaint();
            }

        }

    }


    public void paint(Color b) {
        this.color = b;
    }

    public void paintClock(Color c) {
        this.circleColor = c;
    }


}
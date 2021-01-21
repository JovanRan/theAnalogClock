import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;

public abstract class AbstractClockPanel extends JPanel implements Runnable{
    protected int seconds = LocalTime.now().getSecond();
    protected int minutes = LocalTime.now().getMinute();
    protected int hours = LocalTime.now().getHour();
    boolean running;
    double angle = Math.PI / 30;
    private Thread thread;

    Color color = Color.CYAN;
    Color circleColor = Color.white;

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void run() {
        while (true) {
            if (running == true) {
                if (seconds == 59) {
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

            if (running == true && seconds == 0) {
                if (minutes == 59) {
                    minutes = 0;
                } else {
                    minutes++;
                }
                repaint();
            }

            if (running == true && minutes == 0 && seconds == 0) {
                if (hours == 23) {
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

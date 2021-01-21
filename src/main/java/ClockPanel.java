import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.time.LocalDate;

public class ClockPanel extends AbstractClockPanel {

    public ClockPanel() {
        start();
    }


    public void paintComponent(Graphics g) {

        Graphics2D gd = (Graphics2D) g;
        gd.setColor(color);
        gd.fillRect(0, 0, this.getWidth(), this.getHeight());
        Font font = new Font("Arial", Font.BOLD, 30);
        gd.setFont(font);


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
        at.setToTranslation(this.getWidth() / 2, this.getHeight() / 2);
        gd.transform(at);

        // A long, thinner "seconds" hand. Creating the hand and adding a color.

        gd.setColor(Color.RED);
        gd.setStroke(new BasicStroke(1));
        Line2D.Double line = new Line2D.Double(0, 0, 0, -120);
        at.setToRotation(angle * seconds, 0, 0);
        Shape ss = at.createTransformedShape(line);
        gd.draw(ss);

        // A long, thinner "minute" hand. Creating the hand and adding a color.

        gd.setColor(Color.BLACK);
        gd.setStroke(new BasicStroke(3));
        Line2D.Double line2 = new Line2D.Double(0, 0, 0, -100);
        at.setToRotation(angle * minutes, 0, 0);
        Shape sm = at.createTransformedShape(line2);
        gd.draw(sm);

        // A short, thick "hour" hand. Creating the hand and adding a color.

        gd.setColor(Color.BLACK);
        gd.setStroke(new BasicStroke(5));
        Line2D.Double line3 = new Line2D.Double(0, 0, 0, -80);
        at.setToRotation(angle * hours * 5, 0, 0);
        Shape sh = at.createTransformedShape(line3);
        gd.draw(sh);

    }

}
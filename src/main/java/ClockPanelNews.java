import org.json.JSONArray;
import org.json.JSONObject;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.net.MalformedURLException;
import java.time.LocalDate;

public class ClockPanelNews extends AbstractClockPanel {

    private JTextArea textArea = new JTextArea();

    // Creating a new panel which is going to show last 3 news from BBC website, it is an another design for our clock.
    public ClockPanelNews() {
        start();
        TextURL url = null;
        //create text area adding News
        try {
            url = new TextURL("https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=4dbc17e007ab436fb66416009dfb59a8");
             } catch (MalformedURLException e) {
                 e.printStackTrace();
             }
            String contents = url.read();
            try {
            JSONObject obj = new JSONObject(contents);
            JSONArray arr = obj.getJSONArray("articles");
            textArea.append("BBC News.\n\n");

            // This for-loop is created to get the first three reports from the website
            for (int i = 0; i < 3; i++)
            {
            String title = arr.getJSONObject(i).getString("title");
            String description = arr.getJSONObject(i).getString("description");

            textArea.append(title);
            textArea.append("\n");

            textArea.append(""+description);
            textArea.append("\n\n");
       }

        } catch (NullPointerException e) {
            textArea.append("\n  Internet error!\n");
        }
        setLayout(null);

        this.add(textArea);

        textArea.setForeground(Color.BLACK);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setLocation(350, 50);
        textArea.setSize(310, 320);
        textArea.setOpaque(true);
        textArea.setBackground(Color.lightGray);
        textArea.setMargin(new Insets(20,20,20,20));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gd = (Graphics2D) g;

        AffineTransform oldGTransform = gd.getTransform(); // save current state

        gd.setColor(color);
        gd.fillRect(0, 0, this.getWidth(), this.getHeight());
        Font font = new Font("Arial", Font.BOLD, 30);
        gd.setFont(font);

        Graphics2D circle = (Graphics2D) g;
        circle.setStroke(new BasicStroke(10));
        circle.setColor(circleColor);
        circle.fillOval(50, 50, 250, 250);

        Graphics2D outsideCircle = (Graphics2D) g;
        outsideCircle.setColor(Color.black);
        outsideCircle.setStroke(new BasicStroke(6));
        outsideCircle.drawOval(50, 50, 250, 250);

        Graphics2D digitalClock = (Graphics2D) g;
        digitalClock.drawString((Integer.toString(hours)), 90, 330);
        digitalClock.drawString(":", 130, 328);
        digitalClock.drawString((Integer.toString(minutes)), 150, 330);
        digitalClock.drawString(":", 190, 328);
        digitalClock.drawString((Integer.toString(seconds)), 210, 330);
        digitalClock.drawString((String.valueOf(LocalDate.now())), 100, 30);

        gd.drawString("9", 60, 175);
        gd.drawString("3", 275, 175);
        gd.drawString("12", 155, 80);
        gd.drawString("6", 170, 290);


        AffineTransform at = new AffineTransform();
        at.setToTranslation(175, 175);
        gd.transform(at);

        // A long, thinner "seconds" hand. Creating the hand and adding a color.
        gd.setColor(Color.RED);
        gd.setStroke(new BasicStroke(1));
        Line2D.Double line = new Line2D.Double(0, 0, 0, -90);
        at.setToRotation(angle * seconds, 0, 0);
        Shape ss = at.createTransformedShape(line);
        gd.draw(ss);

        // A long, thinner "minute" hand. Creating the hand and adding a color.
        gd.setColor(Color.BLACK);
        gd.setStroke(new BasicStroke(3));
        Line2D.Double line2 = new Line2D.Double(0, 0, 0, -75);
        at.setToRotation(angle * minutes, 0, 0);
        Shape sm = at.createTransformedShape(line2);
        gd.draw(sm);

        // A short, thick "hour" hand. Creating the hand and adding a color.
        gd.setColor(Color.BLACK);
        gd.setStroke(new BasicStroke(5));
        Line2D.Double line3 = new Line2D.Double(0, 0, 0, -65);
        at.setToRotation(angle * hours * 5, 0, 0);
        Shape sh = at.createTransformedShape(line3);
        gd.draw(sh);

        gd.setTransform(oldGTransform);//reload previous state
    }
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Clock extends JFrame implements ActionListener {


    // Erstellung der Variablen (Buttons)
    private JButton start;
    private JButton stop;


    private ClockPanel panel2;

    //Main Methode in der wir unsere Leindwand erstellen (Größe,Titel, Buttons, Anordnung der Buttons)
    public Clock() {

        setTitle("Clock");

        panel2 = new ClockPanel();
        panel2.setRunning(false);
        this.add(panel2, BorderLayout.CENTER);

        start = new JButton("Start");
        start.addActionListener(this);

        stop = new JButton("Stop");
        stop.addActionListener(this);


        JPanel buttons = new JPanel(new FlowLayout());

        buttons.add(start);
        buttons.add(stop);
        this.add(buttons, BorderLayout.SOUTH);

        this.setSize(700, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE); //nochmal prüfen
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new Clock();
    }

    //Definieren was passieren soll wenn einer der Buttons ausgewählt wird
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == start) {
            panel2.setRunning(true);
        }

        if (e.getSource() == stop) {
            panel2.setRunning(false);
        }

    }
}
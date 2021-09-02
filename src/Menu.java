import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;


/**
 * @author Saam Eymany
 * @version 1.0
 * @since 2021-06-20
 */

public class Menu extends JFrame {
    private JRadioButton rb1, rb2;
    private JLabel groesseLabel, gewichtLabel, alterLabel;
    private JLabel groesseWert, gewichtWert, alterWert;
    private JSlider groesseSlider, gewichtSlider, alterSlider;
    private JPanel centerPanel;
    private JPanel gewichtPanel, alterPanel, groessePanel, geschlechtPanel, buttonPanel;
    private ButtonGroup bg;
    private JButton bmi;
    private JButton kalorien;

    private double groesse;
    private int alter,gewicht, groesseNormal;
    Menu a = this;

    Menu() {
        //Frame erstellen
        super("FitnessApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(20, 20));
        setSize(550, 500);


        //Geschlechts-Eingabe erstellen
        rb1 = new JRadioButton("Männlich");
        rb2 = new JRadioButton("Weiblich");
        bg = new ButtonGroup();
        bg.add(rb1);
        bg.add(rb2);
        geschlechtPanel = new JPanel();
        geschlechtPanel.add(rb1, BorderLayout.WEST);
        geschlechtPanel.add(rb2, BorderLayout.EAST);


        //Alters-Eingabe erstellen
        alterSlider = new JSlider(JSlider.HORIZONTAL, 16, 100, 16);
        alterSlider.setPreferredSize(new Dimension(250, 50));
        alterSlider.setMajorTickSpacing(20);
        alterSlider.setMinorTickSpacing(4);
        alterSlider.setPaintTicks(true);
        alterSlider.setPaintTrack(true);
        alterSlider.setPaintLabels(true);
        alterWert = new JLabel();
        alterWert.setPreferredSize(new Dimension(50, 20));
        alterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                alterWert.setText(alterSlider.getValue() + " Jahre");
                alter = alterSlider.getValue();
            }
        });

        alterLabel = new JLabel("Alter: ");
        alterPanel = new JPanel();
        alterPanel.add(alterLabel, BorderLayout.WEST);
        alterPanel.add(alterSlider, BorderLayout.CENTER);
        alterPanel.add(alterWert, BorderLayout.EAST);


        //Gewichts-Eingabe erstellen
        gewichtLabel = new JLabel("Körpergewicht: ");

        gewichtSlider = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
        gewichtSlider.setPreferredSize(new Dimension(250, 50));
        gewichtSlider.setPaintTicks(true);
        gewichtSlider.setMajorTickSpacing(50);
        gewichtSlider.setMinorTickSpacing(10);
        gewichtSlider.setPaintTrack(true);
        gewichtSlider.setPaintLabels(true);
        gewichtWert = new JLabel();
        gewichtWert.setPreferredSize(new Dimension(50, 20));
        gewichtSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gewichtWert.setText(gewichtSlider.getValue() + "Kg");
                gewicht = gewichtSlider.getValue();
            }
        });


        gewichtPanel = new JPanel();
        gewichtPanel.add(gewichtLabel, BorderLayout.WEST);
        gewichtPanel.add(gewichtSlider, BorderLayout.CENTER);
        gewichtPanel.add(gewichtWert, BorderLayout.EAST);


        //Körpergrösse-Eingabe erstellen
        groesseLabel = new JLabel("Körpergrösse: ");

        groesseSlider = new JSlider(JSlider.HORIZONTAL, 0, 250, 0);
        groesseSlider.setPreferredSize(new Dimension(250, 50));
        groesseSlider.setPaintTicks(true);
        groesseSlider.setMajorTickSpacing(50);
        groesseSlider.setMinorTickSpacing(10);
        groesseSlider.setPaintTrack(true);
        groesseSlider.setPaintLabels(true);


        groesseWert = new JLabel();
        groesseWert.setPreferredSize(new Dimension(50, 20));
        groesseSlider.addChangeListener(e -> {
            groesseWert.setText(groesseSlider.getValue() + "cm");
            double sliderValue = (double) groesseSlider.getValue() / 100;
            groesse = sliderValue * sliderValue;
            groesseNormal =  groesseSlider.getValue();

        });


        groessePanel = new JPanel();
        groessePanel.add(groesseLabel, BorderLayout.WEST);
        groessePanel.add(groesseSlider, BorderLayout.CENTER);
        groessePanel.add(groesseWert, BorderLayout.EAST);

        //Panels zusammenfügen
        centerPanel = new JPanel(new GridLayout(3, 1));
        centerPanel.add(gewichtPanel);
        centerPanel.add(groessePanel);
        centerPanel.add(alterPanel);

        //Button erstellen
        bmi = new JButton("BMI berechen");
        bmi.addActionListener(e -> checkAndShow("bmi"));
        kalorien = new JButton("Kalorienbedarf berechnen");
        kalorien.addActionListener(e -> checkAndShow("kalorien"));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(bmi);
        buttonPanel.add(kalorien);

        //Panel zum Frame anfügen
        this.getContentPane().add(geschlechtPanel, BorderLayout.NORTH);
        this.getContentPane().add(centerPanel, BorderLayout.CENTER);
        this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);


        setVisible(true);
    }

    /**
     * Exception handling: überprüft ob alles ausgewählt ist
     */
    private void checkAndShow(String b) {
        JOptionPane j = new JOptionPane();
        JPanel p = new JPanel();
        if (bg.getSelection() == null || groesseSlider.getValue() == 0 || gewichtSlider.getValue() == 0 || alterSlider.getValue() == 0) {
            j.showMessageDialog(p, "Alles muss aufgefüllt sein!");
        } else if (b == "bmi") {
            new BMI_Ausgabe(a);
        } else if (b == "kalorien") {
            new Kalorienbedarf_Angaben(a);
        }
    }

    /**
     * @return den BMI-Wert
     */
    public double getBmi() {
        double bmi = 0;

        bmi = gewicht / groesse;

        return Math.round(bmi * 100.0) / 100.0;
    }

    /**
     * @return das Alter
     */
    public int getAlter() {
        return alter;
    }

    /**
     * @return das ausgewählte Geschlecht
     */
    public String getRadioButton() {
        String rb = null;
        if (rb1.isSelected()) {
            rb = "m";
        }
        if (rb2.isSelected()) {
            rb = "w";
        }

        return rb;
    }

    /**
     * @return den Grundumsatz einer Frau
     */
    public double getCaloriesFromWoman() {
        double cal = 0;
        if (rb2.isSelected()) {
            cal = 655.1 + (9.6 * gewicht) + (1.8 * groesseNormal) - (4.7 * alter);
        }
        return cal;
    }

    /**
     * @return den Grundumsatz eines Mannes
     */
    public double getCaloriesFromMan() {
        double cal = 0;
        if (rb1.isSelected()) {
            cal = 66.47 + (13.7 * gewicht) + (5 * groesseNormal) - (6.8 * alter);
        }
        return cal;
    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Saam Eymany
 * @version 1.0
 * @since 2021-06-20
 */

public class Kalorienbedarf_Angaben extends JFrame {
    Menu a;
    Kalorienbedarf_Angaben k = this;
    JLabel title;
    JTextField schlaf, sitzend, kaumAktiv, aktiv, sehrAktiv, sportlichAktiv;
    JLabel schlafLabel, sitzendLabel, kaumAktivLabel, aktivLabel, sehrAktivLabel, sportlichAktivLabel;
    JPanel fieldPanel, labelPanel;
    JButton berechnen;
    JPanel north, center, south;

    double palInsegeasamt;
    int stundenInsegesamt;
    double cal;
    double palDurchschnitt;
    int schalfStunden, sitzendStunden, kaumAktivStunden, aktivStunden, sehrAktivStunden, sportlichAktivStunden;

    Kalorienbedarf_Angaben(Menu a) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 300);
        this.a = a;

        title = new JLabel("Aktivitäten in Stunden pro Tag: ");

        schlaf = new JTextField();
        sitzend = new JTextField();
        kaumAktiv = new JTextField();
        aktiv = new JTextField();
        sehrAktiv = new JTextField();
        sportlichAktiv = new JTextField();


        schlafLabel = new JLabel("Schlaf (0.95)");
        sitzendLabel = new JLabel("nur sitzend oder liegend (1.2)");
        kaumAktivLabel = new JLabel("sitzend, kaum Aktiv (1.5)");
        aktivLabel = new JLabel("sitzend, gehend und stehend (1.7)");
        sehrAktivLabel = new JLabel("hauptsächlich stehend / gehend (1.9)");
        sportlichAktivLabel = new JLabel("anstrengende Arbeit / Sport (2.4)");


        labelPanel = new JPanel(new GridLayout(6, 1));
        labelPanel.add(schlafLabel);
        labelPanel.add(sitzendLabel);
        labelPanel.add(kaumAktivLabel);
        labelPanel.add(aktivLabel);
        labelPanel.add(sehrAktivLabel);
        labelPanel.add(sportlichAktivLabel);


        fieldPanel = new JPanel(new GridLayout(6, 1));
        fieldPanel.add(schlaf);
        fieldPanel.add(sitzend);
        fieldPanel.add(kaumAktiv);
        fieldPanel.add(aktiv);
        fieldPanel.add(sehrAktiv);
        fieldPanel.add(sportlichAktiv);


        north = new JPanel();
        north.add(title, BorderLayout.CENTER);

        center = new JPanel(new GridLayout(1, 2));
        center.add(fieldPanel);
        center.add(labelPanel);

        south = new JPanel();
        berechnen = new JButton("Kalorienbedarf berechen");
        berechnen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                checkAndShow();


            }
        }); //gibt die Kalorien aus
        south.add(berechnen, BorderLayout.CENTER);

        this.getContentPane().add(south, BorderLayout.SOUTH);
        this.getContentPane().add(center, BorderLayout.CENTER);
        this.getContentPane().add(north, BorderLayout.NORTH);
        setVisible(true);

    }

    /**
     * Diese Methode ist für den Kalorienbedarf notwendig
     */

    private void update() {
        double schlafPal = Double.parseDouble(schlaf.getText()) * 0.95;
        double sitzendPal = Double.parseDouble(sitzend.getText()) * 1.2;
        double kaumAktivPal = Double.parseDouble(kaumAktiv.getText()) * 1.5;
        double aktivPal = Double.parseDouble(aktiv.getText()) * 1.7;
        double sehrAktivPal = Double.parseDouble(sehrAktiv.getText()) * 1.9;
        double sportlichAktivPal = Double.parseDouble(sportlichAktiv.getText()) * 2.4;


        palInsegeasamt = schlafPal + sitzendPal + kaumAktivPal + aktivPal + sehrAktivPal + sportlichAktivPal;



    }

    /**
     * Exception handling: überprüft ob alles ausgefüllt ist
     * und ob die Stunden insgesamt 24Stunden ergeben
     */
    private void checkAndShow() {
        JOptionPane j = new JOptionPane();
        JPanel p = new JPanel();

        stundenInsegesamt = -1;
        try{
            schalfStunden = Integer.parseInt(schlaf.getText());
            sitzendStunden = Integer.parseInt(sitzend.getText());
            kaumAktivStunden = Integer.parseInt(kaumAktiv.getText());
            aktivStunden = Integer.parseInt(aktiv.getText());
            sehrAktivStunden = Integer.parseInt(sehrAktiv.getText());
            sportlichAktivStunden = Integer.parseInt(sportlichAktiv.getText());
            stundenInsegesamt = schalfStunden + sitzendStunden + kaumAktivStunden + aktivStunden + sehrAktivStunden + sportlichAktivStunden;
        }catch (NumberFormatException nfx){
            j.showMessageDialog(p, "Es müssen Ganzzahlen sein!");
        }
        if (schlaf.getText().equals("") || sitzend.getText().equals("") || kaumAktiv.getText().equals("") || aktiv.getText().equals("") || sehrAktiv.getText().equals("") || sportlichAktiv.getText().equals("")) {
            j.showMessageDialog(p, "Alles muss aufgefüllt sein!");
        }
        if (stundenInsegesamt != 24 ) {
            j.showMessageDialog(p, "Alle Stunden zusammen müssen 24 Stunden (1 Tag) ergeben!");
        } else {
            update();
            new Kalorien_Ausgabe(k, a);
        }
    }

    /**
     * überfprüft ob ein Mann oder eine Frau ausgewählt ist
     *
     * @return den Kalorienbedarf
     */
    public double getKalorienbedarf() {
        palDurchschnitt = palInsegeasamt / stundenInsegesamt;

        if (a.getRadioButton() == "m") {
            cal = a.getCaloriesFromMan() * palDurchschnitt;
        } else if (a.getRadioButton() == "w") {
            cal = a.getCaloriesFromWoman() * palDurchschnitt;
        }
        return cal;
    }


}

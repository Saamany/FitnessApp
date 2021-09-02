import javax.swing.*;
import java.awt.*;

/**
 * @author Saam Eymany
 * @version 1.0
 * @since 2021-06-20
 */

public class Kalorien_Ausgabe extends JFrame {
    JLabel grundumsatzLabel, kalorienbedarfLabel;
    JTextField grundumsatzfield, kalorienbedarffield;
    JPanel ausgabe;
    Kalorienbedarf_Angaben k;
    Menu a;

    Kalorien_Ausgabe(Kalorienbedarf_Angaben k, Menu a) {
        this.k = k;
        this.a = a;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 500);

        grundumsatzfield = new JTextField();
        grundumsatzfield.setEditable(false);
        kalorienbedarffield = new JTextField();
        kalorienbedarffield.setEditable(false);

        grundumsatzLabel = new JLabel("Ihr Grundumsatz beträgt: ");
        kalorienbedarfLabel = new JLabel("Ihr Kalorienbedarf beträgt: ");
        if (a.getRadioButton() == "m") {
            setTitle("Kalorienbedarf eines Mannes");
            grundumsatzfield.setText(String.valueOf(Math.round(a.getCaloriesFromMan()*100.0)/100.0));
            kalorienbedarffield.setText(String.valueOf(Math.round(k.getKalorienbedarf()*100.0)/100.0));

        } else if (a.getRadioButton() == "w") {
            setTitle("Kalorienbedarf einer Frau");
            grundumsatzfield.setText(String.valueOf(Math.round(a.getCaloriesFromWoman()*100.0)/100.0));
            kalorienbedarffield.setText(String.valueOf(Math.round(k.getKalorienbedarf()*100.0)/100.0));
        }

        ausgabe = new JPanel(new GridLayout(2, 2));
        ausgabe.add(grundumsatzLabel);
        ausgabe.add(grundumsatzfield);
        ausgabe.add(kalorienbedarfLabel);
        ausgabe.add(kalorienbedarffield);

        this.getContentPane().add(ausgabe);

        setVisible(true);

    }
}

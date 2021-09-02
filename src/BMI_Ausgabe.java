import javax.swing.*;
import java.awt.*;

/**
 * @author Saam Eymany
 * @version 1.0
 * @since 2021-06-20
 */

public class BMI_Ausgabe extends JFrame {
    Menu a;
    double bmi;
    JLabel bmiAnzeige, kategorieAnzeige, idealBmiAnzeige;
    JPanel anzeigePanel, labelPanel, framePanel;
    JLabel bmiLabel, kategorieLabel, idealBmiLabel;

    BMI_Ausgabe(Menu a) {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 300);
        this.a = a;

        //überprüft das Geschlecht und gibt die Werte aus
        if (a.getRadioButton() == "m") {
            setTitle("BMI für Mann");
            updateBmi();
            bmiAnzeige = new JLabel(String.valueOf(bmi));
            kategorieAnzeige = new JLabel(getCategoryFromMan());
            idealBmiAnzeige = new JLabel(getIdealBmifromMan());

        } else if (a.getRadioButton() == "w") {
            setTitle("BMI für Frau");
            updateBmi();
            bmiAnzeige = new JLabel(String.valueOf(bmi));
            kategorieAnzeige = new JLabel(getCategoryFromWoman());
            idealBmiAnzeige = new JLabel(getIdealBmifromWoman());
        }


        bmiLabel = new JLabel("Ihr BMI beträgt: ");
        idealBmiLabel = new JLabel("Ihr idealer BMI Wert beträgt:");
        kategorieLabel = new JLabel("Kategorie : ");
        Font font = new Font("Courier", Font.BOLD, 12);
        bmiLabel.setFont(font);
        idealBmiLabel.setFont(font);
        kategorieLabel.setFont(font);

        labelPanel = new JPanel(new GridLayout(3, 1));
        labelPanel.add(bmiLabel);
        labelPanel.add(kategorieLabel);
        labelPanel.add(idealBmiLabel);

        anzeigePanel = new JPanel(new GridLayout(3, 1));
        anzeigePanel.add(bmiAnzeige);
        anzeigePanel.add(kategorieAnzeige);
        anzeigePanel.add(idealBmiAnzeige);


        framePanel = new JPanel(new GridLayout(1, 2));
        framePanel.add(labelPanel, BorderLayout.WEST);
        framePanel.add(anzeigePanel, BorderLayout.EAST);


        this.getContentPane().add(framePanel, BorderLayout.CENTER);


        setVisible(true);

    }

    /**
     * Diese Methode übernimmt den BMI-Wert aus der Menü klasse
     * Diese Mehode dient jedeglich für die Vereinfachung
     */
    public void updateBmi() {
        bmi = a.getBmi();


    }

    /**
     * @return die BMI-Katogerie eines Mannes
     */
    public String getCategoryFromMan() {
        String cat = null;
        if (a.getRadioButton() == "m") {
            if (bmi < 20) {
                cat = "Untergewicht";
            } else if (bmi >= 20 && bmi <= 24.9) {
                cat = "Normalgewicht";
            } else if (bmi >= 25 && bmi <= 29.9) {
                cat = "Übergewicht";
            } else if (bmi >= 30 && bmi <= 34.9) {
                cat = "Starkes Übergewicht";
            } else if (bmi >= 35 && bmi <= 39.9) {
                cat = "Adipositas Grad II";
            } else if (bmi >= 40) {
                cat = "Adipositas Grad III";
            }
        }

        return cat;
    }

    /**
     * @return die BMI-Katogerie einer Frau
     */
    public String getCategoryFromWoman() {
        String cat = null;
        if (a.getRadioButton() == "w") {
            if (bmi < 19) {
                cat = "Untergewicht";
            } else if (bmi >= 19 && bmi <= 23.9) {
                cat = "Normalgewicht";
            } else if (bmi >= 24 && bmi <= 29.9) {
                cat = "Übergewicht";
            } else if (bmi >= 30 && bmi <= 34.9) {
                cat = "Starkes Übergewicht";
            } else if (bmi >= 35 && bmi <= 39.9) {
                cat = "Adipositas Grad II";
            } else if (bmi >= 40) {
                cat = "Adipositas Grad III";
            }
        }
        return cat;
    }

    /**
     * @return den idealen BMI-Wert eines Mannes
     */
    public String getIdealBmifromMan() {
        String idealBmi = null;
        if (a.getRadioButton() == "m") {
            if (a.getAlter() == 16) {
                idealBmi = "19-24";
            } else if (a.getAlter() >= 17 && a.getAlter() <= 18) {
                idealBmi = "20-25";
            } else if (a.getAlter() >= 19 && a.getAlter() <= 24) {
                idealBmi = "21-26";
            } else if (a.getAlter() >= 25 && a.getAlter() <= 34) {
                idealBmi = "22-27";
            } else if (a.getAlter() >= 35 && a.getAlter() <= 54) {
                idealBmi = "23-28";
            } else if (a.getAlter() >= 55 && a.getAlter() <= 64) {
                idealBmi = "24-29";
            } else if (a.getAlter() >= 65 && a.getAlter() <= 100) {
                idealBmi = "25-30";
            }
        }
        return idealBmi;
    }

    /**
     * @return den idealen BMI-Wert einer Frau
     */
    public String getIdealBmifromWoman() {
        String idealBmi = null;
        if (a.getRadioButton() == "w") {
            if (a.getAlter() >= 16 && a.getAlter() <= 24) {
                idealBmi = "19-24";
            } else if (a.getAlter() >= 25 && a.getAlter() <= 34) {
                idealBmi = "20-25\t";
            } else if (a.getAlter() >= 35 && a.getAlter() <= 44) {
                idealBmi = "21-26";
            } else if (a.getAlter() >= 45 && a.getAlter() <= 54) {
                idealBmi = "22-27";
            } else if (a.getAlter() >= 55 && a.getAlter() <= 64) {
                idealBmi = "23-28";
            } else if (a.getAlter() >= 64 && a.getAlter() <= 100) {
                idealBmi = "25-30";
            }
        }
        return idealBmi;
    }

}

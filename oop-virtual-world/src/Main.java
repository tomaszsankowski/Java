import main.game.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JSlider firstNumberSlider = new JSlider(10, 25, 10);
        firstNumberSlider.setPaintTicks(true);
        firstNumberSlider.setMajorTickSpacing(5);
        firstNumberSlider.setMinorTickSpacing(1);
        firstNumberSlider.setPaintLabels(true);
        firstNumberSlider.setSnapToTicks(true);
        JLabel firstNumberLabel = new JLabel("Wysokosc planszy:");

        JSlider secondNumberSlider = new JSlider(10, 25, 10);
        secondNumberSlider.setPaintTicks(true);
        secondNumberSlider.setMajorTickSpacing(5);
        secondNumberSlider.setMinorTickSpacing(1);
        secondNumberSlider.setPaintLabels(true);
        secondNumberSlider.setSnapToTicks(true);
        JLabel secondNumberLabel = new JLabel("Szerokosc planszy:");

        JPanel panel = new JPanel();
        panel.add(firstNumberLabel);
        panel.add(firstNumberSlider);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(secondNumberLabel);
        panel.add(secondNumberSlider);

        int result = JOptionPane.showConfirmDialog(null, panel, "Wprowadz wymiary planszy", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int m = firstNumberSlider.getValue();
            int n = secondNumberSlider.getValue();
            GUI gra = new GUI(m,n);
        }
    }
}

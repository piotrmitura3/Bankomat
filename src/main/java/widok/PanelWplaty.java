package widok;

import service.OperacjeKlienta;

import javax.swing.*;
import java.awt.*;

public class PanelWplaty extends JPanel {
    private JLabel kwotaDoWplatyLabel;
    private JTextField kwotaDoWplatyTextField;
    private JButton potwierdzWplateButton;
    private PanelWyboruOperacji panelWyboruOperacji;
    private OperacjeKlienta operacjeKlienta;

    public PanelWplaty(PanelWyboruOperacji panelWyboruOperacji) {
        this.panelWyboruOperacji = panelWyboruOperacji;
        setLayout(new FlowLayout());
        stworzKomponenty();
    }

    private void stworzKomponenty(){
        kwotaDoWplatyLabel = new JLabel("Podaj kwote: ");
        kwotaDoWplatyTextField = new JTextField(12);
        potwierdzWplateButton = new JButton("Potwierdz wplate");

        this.add(kwotaDoWplatyLabel);
        this.add(kwotaDoWplatyTextField);
        this.add(potwierdzWplateButton);
    }


}

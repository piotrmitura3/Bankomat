package widok;

import model.Klient;
import repo.BazaKlientow;
import service.KlientZnajdz;
import service.OperacjeKlienta;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class PanelWplaty extends JPanel {
    private JLabel kwotaDoWplatyLabel;
    private JTextField kwotaDoWplatyTextField;
    private JButton potwierdzWplateButton;
    private OperacjeKlienta operacjeKlienta = new OperacjeKlienta();
    private BazaKlientow bazaKlientow = new BazaKlientow();
    private KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);
    private GlownaRamka glownaRamka;
    private Klient klient;
    private PanelWplaty panelWplaty;
    public PanelWplaty(GlownaRamka glownaRamka) {
        this.glownaRamka = glownaRamka;
        setLayout(new FlowLayout());
        stworzKomponenty();
        stworzActionListner();
    }

    private void stworzKomponenty(){
        kwotaDoWplatyLabel = new JLabel("Podaj kwote: ");
        kwotaDoWplatyTextField = new JTextField(12);
        potwierdzWplateButton = new JButton("Potwierdz wplate");

        this.add(kwotaDoWplatyLabel);
        this.add(kwotaDoWplatyTextField);
        this.add(potwierdzWplateButton);
    }

    private void wplataNaKonto(){
        klient = klientZnajdz.znajdzKlienta(glownaRamka.getNrKlientaTextField());
        BigDecimal stanKontaKlienta = klient.getStanKontaKlienta();
        int rodzajOperacji = 1;
        BigDecimal kwotaDoWplaty = BigDecimal.valueOf(Integer.parseInt(kwotaDoWplatyTextField.getText()));
        operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWplaty, klient);
        BigDecimal stanKontaPoWplacie = stanKontaKlienta.add(kwotaDoWplaty);
        JOptionPane.showMessageDialog(null, "Stan konta wynosi: " + stanKontaPoWplacie, "Stan konta Klienta", JOptionPane.CLOSED_OPTION);    }

    private void stworzActionListner(){
        potwierdzWplateButton.addActionListener(e -> {
            wplataNaKonto();
        });
    }


}

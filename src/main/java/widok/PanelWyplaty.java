package widok;

import model.Klient;
import repo.BazaKlientow;
import service.KlientZnajdz;
import service.OperacjeKlienta;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class PanelWyplaty extends JPanel {
    private JLabel kwotaLabel;
    private JTextField kwotaTextField;
    private JButton potwierdzButton;
    private OperacjeKlienta operacjeKlienta = new OperacjeKlienta();
    private BazaKlientow bazaKlientow = new BazaKlientow();
    private KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);
    private GlownaRamka glownaRamka;
    private Klient klient;

    public PanelWyplaty(GlownaRamka glownaRamka) {
        this.glownaRamka = glownaRamka;
        setLayout(new FlowLayout());
        stworzKomponenty();
        stworzActionListner();
    }

    public void stworzKomponenty(){
        kwotaLabel = new JLabel("Podaj kwote");
        kwotaTextField = new JTextField(12);
        potwierdzButton = new JButton("Potwierdz");

        this.add(kwotaLabel);
        this.add(kwotaTextField);
        this.add(potwierdzButton);
    }

    private void wyplataZKonta(){
        klient = klientZnajdz.znajdzKlienta(glownaRamka.getNrKlientaTextField());
        BigDecimal stanKontaKlienta = klient.getStanKonta();
        int rodzajOperacji = 2;
        BigDecimal kwotaDoWyplaty = new BigDecimal(kwotaTextField.getText());
        operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWyplaty, klient);
        //BigDecimal stanKontaPoWyplacie = stanKontaKlienta.subtract(kwotaDoWyplaty);
        BigDecimal stanKontaPoWyplacie = new BigDecimal(String.valueOf(klient.getStanKonta()));
        System.out.println(klient);
        JOptionPane.showMessageDialog(null, "Stan konta po wyplacie: "
                + stanKontaPoWyplacie, "Stan konta Klienta", JOptionPane.CLOSED_OPTION);
    }

    public void stworzActionListner(){
        potwierdzButton.addActionListener(e -> {
            wyplataZKonta();
        });
    }

}

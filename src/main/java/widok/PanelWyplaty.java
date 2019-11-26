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

        try {
            BigDecimal kwotaDoWyplaty = new BigDecimal(kwotaTextField.getText());
            System.out.println(klient);
            if (kwotaDoWyplaty.compareTo(BigDecimal.ZERO) < 0){
                JOptionPane.showMessageDialog(null, "Podana kwota jest mniejsza od 0. Podaj ponownie");
                kwotaTextField.setText("");
            } else if (kwotaDoWyplaty.compareTo(stanKontaKlienta) <= 0) {
                operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWyplaty, klient);
                //BigDecimal stanKontaPoWyplacie = stanKontaKlienta.subtract(kwotaDoWyplaty);
                BigDecimal stanKontaPoWyplacie = new BigDecimal(String.valueOf(klient.getStanKonta().subtract(kwotaDoWyplaty)));
                JOptionPane.showMessageDialog(null, "Stan konta po wyplacie: "
                        + stanKontaPoWyplacie, "Stan konta Klienta", JOptionPane.CLOSED_OPTION);
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(null, "Brak wystarczajacych srodkow na koncie. Podaj ponownie");
                kwotaTextField.setText("");
            }
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Bledny format. Podaj ponownie");
            kwotaTextField.setText("");
        }
    }

    private void stworzActionListner(){
        potwierdzButton.addActionListener(e -> {
            wyplataZKonta();
        });
    }

}

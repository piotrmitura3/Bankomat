package widok;

import repo.BazaKlientow;
import model.Klient;
import service.KlientZnajdz;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;

public class PanelWyboruOperacji extends JPanel {
    private JButton wyborWplatyButton;
    private JButton wyborWyplatyButton;
    private JButton stanKontaButton;
    private GlownaRamka glownaRamka;
    private Klient klient;
    private BazaKlientow bazaKlientow = new BazaKlientow();
    private KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);
    private JButton przelewyButton;

    public void stanKontaKlienta(){

        klient = klientZnajdz.znajdzKlienta(glownaRamka.getNrKlientaTextField());
        BigDecimal stanKontaKlienta = klient.getStanKontaKlienta();
        JOptionPane.showMessageDialog(null, "Stan konta wynosi: " + stanKontaKlienta, "Stan konta Klienta", JOptionPane.YES_NO_CANCEL_OPTION);
        glownaRamka.setContentPane(new PanelWyboruOperacji(glownaRamka));
    }

    public PanelWyboruOperacji(GlownaRamka glownaRamka) {
        this.glownaRamka = glownaRamka;
        setLayout(new FlowLayout());
        stworzKomponenty();
        setVisible(true);
        stworzActionListner();
    }

    public void stworzKomponenty(){
        wyborWplatyButton = new JButton("Wpłata");
        wyborWyplatyButton = new JButton("Wyplata");
        stanKontaButton = new JButton("Pokaz stan konta");
        przelewyButton = new JButton("Wykonaj przlew");

        this.add(wyborWplatyButton);
        this.add(wyborWyplatyButton);
        this.add(stanKontaButton);
        this.add(przelewyButton);
    }

    public void stworzActionListner(){
        wyborWyplatyButton.addActionListener(e -> {
            glownaRamka.setContentPane(new PanelWyplaty());
        });

        wyborWplatyButton.addActionListener(e -> {
            glownaRamka.setContentPane(new PanelWplaty(this));
            glownaRamka.pack();
        });

        stanKontaButton.addActionListener(e -> {
            stanKontaKlienta();
        });
    }





}
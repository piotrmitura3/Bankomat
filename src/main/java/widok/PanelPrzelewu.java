package widok;

import model.Klient;
import repo.BazaKlientow;
import service.KlientZnajdz;
import service.PrzelewService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class PanelPrzelewu extends JPanel {
    private GlownaRamka glownaRamka;
    private JLabel imieOdbiorcyLabel;
    private JTextField imieOdbiorcyTextField;
    private JLabel nazwiskoOdbiorcyLabel;
    private JTextField nazwiskoOdbiorcyTextField;
    private JLabel nrKontaOdbiorcyLabel;
    private JTextField nrKontaOdbiorcyTextField;
    private JComboBox klientComboBox;
    private BazaKlientow bazaKlientow = new BazaKlientow();
    private KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);
    private JLabel kwotaDoPrzelewuLabel;
    private JTextField kwotaDoPrzelewuTextField;
    private JButton wykonajPrzelewButtono;
    private PrzelewService przelewService = new PrzelewService();

    public PanelPrzelewu(GlownaRamka glownaRamka) {
        this.glownaRamka = glownaRamka;
        stworzKomponenty();
        ustawianiePolPoWyborzeZListy();
        dodajActionListner();
    }

    public void stworzKomponenty() {
        imieOdbiorcyLabel = new JLabel("Imie: ");
        imieOdbiorcyTextField = new JTextField(12);
        nazwiskoOdbiorcyLabel = new JLabel("Nazwisko: ");
        nazwiskoOdbiorcyTextField = new JTextField(12);
        nrKontaOdbiorcyLabel = new JLabel("Podaj numer konta: ");
        nrKontaOdbiorcyTextField = new JTextField(30);
        kwotaDoPrzelewuLabel = new JLabel("Podaj kwote: ");
        kwotaDoPrzelewuTextField = new JTextField(20);
        wykonajPrzelewButtono = new JButton("Wykonaj: ");


        klientComboBox = new JComboBox();
        klientComboBox.setBounds(500, 50, 100, 20);
        for (Klient klient : bazaKlientow.pobierzKlientow()) {
            klientComboBox.addItem(klient);
            klientComboBox.removeItem(znajdzNadawce());
            //Klient selectedItem = (Klient) klientComboBox.getSelectedItem();
            //imieOdbiorcyTextField.setText(selectedItem.getImie());
        }

        this.add(klientComboBox);
        this.add(imieOdbiorcyLabel);
        this.add(imieOdbiorcyTextField);
        this.add(nazwiskoOdbiorcyLabel);
        this.add(nazwiskoOdbiorcyTextField);
        this.add(nrKontaOdbiorcyLabel);
        this.add(nrKontaOdbiorcyTextField);
        this.add(kwotaDoPrzelewuLabel);
        this.add(kwotaDoPrzelewuTextField);
        this.add(wykonajPrzelewButtono);


    }

    private Klient znajdzNadawce() {
        return klientZnajdz.znajdzKlienta(glownaRamka.getNrKlientaTextField());
    }

    private void ustawianiePolPoWyborzeZListy() {

    }

    private void dodajActionListner() {

        klientComboBox.addActionListener(e -> {
                    ustawDaneOdbiorcy();
                }
        );

        wykonajPrzelewButtono.addActionListener(e -> {
            BigDecimal kwota = new BigDecimal(kwotaDoPrzelewuTextField.getText());
            Klient odbiorca = (Klient) klientComboBox.getSelectedItem();
            try {
                przelewService.przelewBankowy(kwota, odbiorca, znajdzNadawce());
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });

    }

    private void ustawDaneOdbiorcy() {
        Klient klient = (Klient) klientComboBox.getSelectedItem();
        imieOdbiorcyTextField.setText(klient.getImie());
        nazwiskoOdbiorcyTextField.setText(klient.getNazwisko());
        nrKontaOdbiorcyTextField.setText(klient.getNrKonta());
        System.out.println("Selected: " + klientComboBox.getSelectedItem());
        System.out.println(", Position: " + klientComboBox.getSelectedIndex());
    }
}


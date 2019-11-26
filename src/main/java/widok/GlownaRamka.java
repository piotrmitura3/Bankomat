package widok;

import repo.BazaKlientow;
import service.KlientWeryfikator;
import service.KlientZnajdz;

import javax.swing.*;
import java.awt.*;

public class GlownaRamka extends JFrame {

    private JLabel nrKlientaLabel;
    private JTextField nrKlientaTextField;
    private JButton potwierdzButton;
    private BazaKlientow bazaKlientow = new BazaKlientow();
    private KlientWeryfikator klientWeryfikator = new KlientWeryfikator(bazaKlientow);
    private KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);

    public GlownaRamka() {
        setLayout(new FlowLayout());
        setBounds(500,500,500,120);
        stworzRamke();
        setVisible(true);
        stworzActionListner();
    }

    private void stworzRamke(){

        nrKlientaLabel = new JLabel("Podaj nr Klienta: ");
        nrKlientaTextField = new JTextField(12);
        potwierdzButton = new JButton("Potwierdz");

        this.add(nrKlientaLabel);
        this.add(nrKlientaTextField);
        this.add(potwierdzButton);
    }

    public Integer getNrKlientaTextField(){
        return Integer.parseInt(nrKlientaTextField.getText());
    }


    public void weryfikacjaNrKlientaNaPodstawiePola() {
        try{
        Integer nrKlienta = Integer.parseInt(nrKlientaTextField.getText());
        if(klientWeryfikator.weryfikujKlienta(nrKlienta)) {
            klientZnajdz.znajdzKlienta(nrKlienta);
            setContentPane(new PanelWyboruOperacji(this));
        } else {
            JOptionPane.showMessageDialog(null, "Brak klienta w bazie", "Brak klienta", JOptionPane.ERROR_MESSAGE);
            nrKlientaTextField.setText("");
        }}catch (NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Bledny format");
            nrKlientaTextField.setText("");
        }
    }


    private void stworzActionListner(){
        potwierdzButton.addActionListener(e -> {
            weryfikacjaNrKlientaNaPodstawiePola();
            pack();
        });
    }
}

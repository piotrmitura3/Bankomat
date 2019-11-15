package service;

import service.KlientWeryfikator;
import service.KlientZnajdz;
import service.OperacjeKlienta;

import java.math.BigDecimal;

public class Bankomat {
    private KlientWeryfikator klientWeryfikator;
    private OperacjeKlienta operacjeKlienta;
    private KlientZnajdz znajdzKlienta;

    public Bankomat(KlientWeryfikator klientWeryfikator, OperacjeKlienta operacjeKlienta, KlientZnajdz znajdzKlienta) {
        this.klientWeryfikator = klientWeryfikator;
        this.operacjeKlienta = operacjeKlienta;
        this.znajdzKlienta = znajdzKlienta;
    }

    public void startBankomat(Integer nrKlienta, int rodzajAkcji, BigDecimal kwotaDoWplatyLubWyplaty) {
        if (klientWeryfikator.weryfikujKlienta(nrKlienta)) {
            operacjeKlienta.operacjeKlienta(rodzajAkcji, kwotaDoWplatyLubWyplaty, znajdzKlienta.znajdzKlienta(nrKlienta));
        } else {
            System.out.println("Brak klienta w bazie");
        }
    }
}

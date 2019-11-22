package service;

import model.Przelew;
import service.KlientWeryfikator;
import service.KlientZnajdz;
import service.OperacjeKlienta;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class Bankomat {
    private KlientWeryfikator klientWeryfikator;
    private OperacjeKlienta operacjeKlienta;
    private KlientZnajdz znajdzKlienta;
    private PrzelewService przelewService;

    public Bankomat(KlientWeryfikator klientWeryfikator, OperacjeKlienta operacjeKlienta, KlientZnajdz znajdzKlienta, PrzelewService przelewService) {
        this.klientWeryfikator = klientWeryfikator;
        this.operacjeKlienta = operacjeKlienta;
        this.znajdzKlienta = znajdzKlienta;
        this.przelewService = przelewService;
    }

    public void startBankomat(Integer nrKlienta, int rodzajAkcji, BigDecimal kwotaDoWplatyLubWyplaty) {
        if (klientWeryfikator.weryfikujKlienta(nrKlienta)) {
            operacjeKlienta.operacjeKlienta(rodzajAkcji, kwotaDoWplatyLubWyplaty, znajdzKlienta.znajdzKlienta(nrKlienta));
        } else {
            System.out.println("Brak klienta w bazie");
        }
    }

    public void przlewWBankomacie(Integer nrKlientaNadawcy, Integer nrKlientaOdbiorcy, BigDecimal kwotaDoPrzelewu) throws FileNotFoundException {
        if (klientWeryfikator.weryfikujKlienta(nrKlientaNadawcy) && klientWeryfikator.weryfikujKlienta(nrKlientaOdbiorcy)){
            przelewService.przelewBankowy(kwotaDoPrzelewu, znajdzKlienta.znajdzKlienta(nrKlientaOdbiorcy), znajdzKlienta.znajdzKlienta(nrKlientaNadawcy));
        } else {
            System.out.println("Brak klienta w bazie");
        }
    }
}

import model.Klient;
import model.Przelew;
import repo.BazaKlientow;
import service.*;
import widok.GlownaRamka;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.BigInteger;

public class Start {
    public static void main(String[] args) throws FileNotFoundException {
        BazaKlientow bazaKlientow = new BazaKlientow();
        KlientWeryfikator klientWeryfikator = new KlientWeryfikator(bazaKlientow);
        KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);
        OperacjeKlienta operacjeKlienta = new OperacjeKlienta();
        PrzelewService przelewService = new PrzelewService();
        Bankomat bankomat = new Bankomat(klientWeryfikator, operacjeKlienta, klientZnajdz, przelewService);

        //bankomat.startBankomat(7564, 1, new BigDecimal(16000));
        bankomat.przlewWBankomacie(4534, 5644, new BigDecimal(12000));

        GlownaRamka gLownaRamka = new GlownaRamka();
    }
}

import model.Klient;
import model.Przelew;
import repo.BazaKlientow;
import service.*;
import widok.GlownaRamka;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Start {
    public static void main(String[] args) {
        BazaKlientow bazaKlientow = new BazaKlientow();
        KlientWeryfikator klientWeryfikator = new KlientWeryfikator(bazaKlientow);
        KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);
        OperacjeKlienta operacjeKlienta = new OperacjeKlienta();
        Bankomat bankomat = new Bankomat(klientWeryfikator, operacjeKlienta, klientZnajdz);

        bankomat.startBankomat(7564, 1, new BigDecimal(16000));
        PrzelewService przelewService = new PrzelewService();
        przelewService.przelewBankowy(new BigDecimal(String.valueOf(5000)), Klient.builder().idKlienta(4534).stanKonta(new BigDecimal(13000)).build(),
                Klient.builder().idKlienta(7564).stanKonta(new BigDecimal(6000)).build());
        //GlownaRamka gLownaRamka = new GlownaRamka();
    }
}

import repo.BazaKlientow;
import service.Bankomat;
import service.KlientWeryfikator;
import service.KlientZnajdz;
import service.OperacjeKlienta;
import widok.GlownaRamka;

public class Start {
    public static void main(String[] args) {
        BazaKlientow bazaKlientow = new BazaKlientow();
        KlientWeryfikator klientWeryfikator = new KlientWeryfikator(bazaKlientow);
        KlientZnajdz klientZnajdz = new KlientZnajdz(bazaKlientow);
        OperacjeKlienta operacjeKlienta = new OperacjeKlienta();
        Bankomat bankomat = new Bankomat(klientWeryfikator, operacjeKlienta, klientZnajdz);
        //repo.startBankomat(7564, 1, new BigDecimal(16000));
        GlownaRamka gLownaRamka = new GlownaRamka();
    }
}

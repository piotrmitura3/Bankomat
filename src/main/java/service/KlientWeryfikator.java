package service;


import repo.BazaKlientow;
import model.Klient;

public class KlientWeryfikator {
    private BazaKlientow bazaKlientow;

    public KlientWeryfikator(BazaKlientow bazaKlientow) {
        this.bazaKlientow = bazaKlientow;
    }

    public boolean weryfikujKlienta(Integer nrKlienta) {

        //bazaKlientow.getListaNumerowKlientow().stream().filter(klient -> nrKlienta.equals(new BigDecimal(4)));
        if (nrKlienta == null){
            return false;
        }

        for (Klient klient : bazaKlientow.getListaNumerowKlientow()) {
            if (nrKlienta.equals(klient.getIdKlienta())) {
                return true;
            }
        }
        return false;
    }
}

package service;

import repo.BazaKlientow;
import model.Klient;

public class KlientZnajdz {
    private BazaKlientow bazaKlientow;

    public KlientZnajdz(BazaKlientow bazaKlientow) {
        this.bazaKlientow = bazaKlientow;
    }

    public Klient znajdzKlienta(Integer nrKlienta){
        if (nrKlienta == null){
            return null;
        }

        for (Klient klient : bazaKlientow.getListaNumerowKlientow()) {
            if (nrKlienta.equals(klient.getIdKlienta())) {
                return klient;
            }
        }
        return null;
    }
}

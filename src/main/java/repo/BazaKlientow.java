package repo;

import model.Klient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BazaKlientow {
    private List<Klient> listaNumerowKlientow = new ArrayList<Klient>();

    public List<Klient> getListaNumerowKlientow() {
        return listaNumerowKlientow;
    }

    public BazaKlientow() {
        Klient klient1 = Klient.builder()
                .idKlienta(4534)
                .stanKontaKlienta(new BigDecimal(13000))
                .build();

        Klient klient2 = Klient.builder()
                .idKlienta(7564)
                .stanKontaKlienta(new BigDecimal(15000))
                .build();

        Klient klient3 = Klient.builder()
                .idKlienta(5644)
                .stanKontaKlienta(new BigDecimal(1200))
                .build();
        listaNumerowKlientow.add(klient1);
        listaNumerowKlientow.add(klient2);
        listaNumerowKlientow.add(klient3);
    }


    public List<Klient> pobierzKlientow(){
        return listaNumerowKlientow;
    }

}

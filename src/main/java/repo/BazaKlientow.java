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
                .stanKonta(new BigDecimal(13000))
                .nrKonta("82 1020 5226 0000 6102 0417 7895")
                .build();

        Klient klient2 = Klient.builder()
                .idKlienta(7564)
                .stanKonta(new BigDecimal(15000))
                .nrKonta("83 1010 1023 0000 2613 9510 0000")
                .build();

        Klient klient3 = Klient.builder()
                .idKlienta(5644)
                .stanKonta(new BigDecimal(1200))
                .nrKonta("42 1564 1535 8468 3153 5343 4615")
                .build();
        listaNumerowKlientow.add(klient1);
        listaNumerowKlientow.add(klient2);
        listaNumerowKlientow.add(klient3);
    }


    public List<Klient> pobierzKlientow(){
        return listaNumerowKlientow;
    }

}

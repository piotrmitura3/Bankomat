package service;

import model.Klient;

import java.math.BigDecimal;

public class PrzelewService {

    public BigDecimal przelewBankowy(BigDecimal kwotaDoPrzelania, Klient odbiorca, Klient nadawca){
        BigDecimal stanKontaNadawcy = nadawca.getStanKonta();
        BigDecimal stanKontaOdbiorcy = odbiorca.getStanKonta();

        if (kwotaDoPrzelania == null) {
            System.out.println("Pusta kwota");
            return stanKontaNadawcy;
        }

        if (kwotaDoPrzelania.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("Podana kwota jest mniejsza od 0");
            return stanKontaNadawcy;
        }

        if (kwotaDoPrzelania.compareTo(stanKontaNadawcy) <= 0) {
            stanKontaNadawcy = stanKontaNadawcy.subtract(kwotaDoPrzelania);
            stanKontaOdbiorcy = stanKontaOdbiorcy.add(kwotaDoPrzelania);
            System.out.println("Stan konta po doknaniu przelewu: " + stanKontaNadawcy);
            System.out.println("Stan konta odbiorcy: " + stanKontaOdbiorcy);
            return stanKontaNadawcy;
        } else {
            System.out.println("Brak wystarczających środków na koncie");
            return stanKontaNadawcy;
        }
    }
}

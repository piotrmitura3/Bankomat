package service;

import model.Klient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;

public class PrzelewService {

    public BigDecimal przelewBankowy(BigDecimal kwotaDoPrzelania, Klient odbiorca, Klient nadawca) throws FileNotFoundException {
        BigDecimal stanKontaNadawcy = nadawca.getStanKonta();
        BigDecimal stanKontaOdbiorcy = odbiorca.getStanKonta();

        File file = new File("Histori_przelewów.txt");


        if (kwotaDoPrzelania == null) {
            System.out.println("Pusta kwota");
            return stanKontaNadawcy;
        } else if (kwotaDoPrzelania.compareTo(BigDecimal.ZERO) < 0){
            System.out.println("Podana kwota jest mniejsza od 0");
            return stanKontaNadawcy;
        } else if(kwotaDoPrzelania.compareTo(BigDecimal.ZERO) == 0){
            System.out.println("Podaj poprawna kwote!");
            return stanKontaNadawcy;
        } else if (kwotaDoPrzelania.compareTo(stanKontaNadawcy) <= 0) {
            stanKontaNadawcy = stanKontaNadawcy.subtract(kwotaDoPrzelania);
            stanKontaOdbiorcy = stanKontaOdbiorcy.add(kwotaDoPrzelania);
            System.out.println("Stan konta po doknaniu przelewu: " + stanKontaNadawcy);

            PrintWriter zapis = new PrintWriter("Historia_przelewów.txt");
            zapis.println(nadawca + " Kwota do przelania: " + kwotaDoPrzelania + " Przelew do: "
                    + odbiorca.getImie() + " " + odbiorca.getNazwisko() + " " + odbiorca.getNrKonta()
                    + " Stan konta po przelewie: " + stanKontaNadawcy);
            zapis.close();

            return stanKontaNadawcy;
        } else {
            System.out.println("Brak wystarczających środków na koncie");
            return stanKontaNadawcy;
        }
    }
}

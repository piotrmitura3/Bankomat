package service;

import model.Klient;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PrzelewServiceTest {
    private PrzelewService przelewService = new PrzelewService();


    @Test
    public void powinienZawieracDanePrzelewu() throws FileNotFoundException {
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();
        Klient nadawca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();

        String spodziewanaZawartosc = "Piotr kjfsd nr konta: 15435153 Kwota do przelania: 1000 Przelew do: Piotr kjfsd 15435153 Stan konta po przelewie: 12000";

        przelewService.przelewBankowy(new BigDecimal(1000), odbiorca, nadawca);

        File file = new File("Historia_przelewów.txt");
        Scanner in = new Scanner(file);
        String aktualnaZawartosc = in.nextLine();

        assertThat(aktualnaZawartosc).isEqualTo(spodziewanaZawartosc);
    }

}
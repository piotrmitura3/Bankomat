package service;

import model.Klient;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PrzelewServiceTest {
    private PrzelewService przelewService = new PrzelewService();

    @Test
    void powinienZawieracDanePrzelewu() throws FileNotFoundException {
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();
        Klient nadawca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();

        String spodziewanaZawartosc = "Piotr kjfsd nr konta: 15435153 Kwota do przelania: 1000 Przelew do: Piotr kjfsd 15435153 Stan konta po przelewie: 12000";

        przelewService.przelewBankowy(new BigDecimal(1000), odbiorca, nadawca);

        File file = new File("Historia_przelewów.txt");
        Scanner in = new Scanner(file);
        String aktualnaZawartosc = in.nextLine();

        assertThat(aktualnaZawartosc).isEqualTo(spodziewanaZawartosc);
    }

    @Test
    void powinnoZwrocicPoprawnyStanKontaNadawcyPoWykonanymPrzelewie() throws FileNotFoundException {
        //given
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(6000)).build();
        Klient nadawca = Klient.builder().idKlienta(5643).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();
        BigDecimal kwotaDoPrzelania = new BigDecimal(4000);

        BigDecimal spodziewanyStanKonta = new BigDecimal(9000);

        //when
        BigDecimal aktualnyStanKonta = przelewService.przelewBankowy(kwotaDoPrzelania, odbiorca, nadawca);

        //then
        assertThat(aktualnyStanKonta).isEqualTo(spodziewanyStanKonta);
    }

    @Test
    void powinnoZwrocicStanKontaNadawcyBezZmianJesliPodanaKwotaJestNull() throws FileNotFoundException {
        //given
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(6000)).build();
        Klient nadawca = Klient.builder().idKlienta(5643).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();
        BigDecimal kwotaDoPrzelania = null;
        BigDecimal spodziewanyStanKonta = new BigDecimal(13000);
        //when
        BigDecimal aktualnyStanKonta = przelewService.przelewBankowy(kwotaDoPrzelania, odbiorca, nadawca);
        //then
        assertThat(aktualnyStanKonta).isEqualTo(spodziewanyStanKonta);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianJesliKwotaDoPrzelaniaJestMniejszaOdZera() throws FileNotFoundException {
        //given
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(6000)).build();
        Klient nadawca = Klient.builder().idKlienta(5643).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();
        BigDecimal kwotaDoPrzelania = new BigDecimal(-2000);

        BigDecimal spodziewanyStanKonta = new BigDecimal(13000);
        //when
        BigDecimal aktualnyStanKonta = przelewService.przelewBankowy(kwotaDoPrzelania,odbiorca, nadawca);
        //then
        assertThat(aktualnyStanKonta).isEqualTo(spodziewanyStanKonta);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianJesliKwotaDoPrzelaniaJestRownaZero() throws FileNotFoundException {
        //given
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(6000)).build();
        Klient nadawca = Klient.builder().idKlienta(5643).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();
        BigDecimal kwotaDoPrzelania = new BigDecimal(0);
        BigDecimal spodziewanyStanKonta = new BigDecimal(13000);
        //when
        BigDecimal aktualnyStanKonta = przelewService.przelewBankowy(kwotaDoPrzelania, odbiorca, nadawca);
        //then
        assertThat(aktualnyStanKonta).isEqualTo(spodziewanyStanKonta);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianJesliKwotaJestWyzszaOdStanuKontaNadawcy() throws FileNotFoundException {
        //given
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(6000)).build();
        Klient nadawca = Klient.builder().idKlienta(5643).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();
        BigDecimal kwotaDoPrzelania = new BigDecimal(14000);

        BigDecimal spodziewanyStanKonta = new BigDecimal(13000);

        //when
        BigDecimal aktualnyStanKonta = przelewService.przelewBankowy(kwotaDoPrzelania, odbiorca, nadawca);
        //then
        assertThat(aktualnyStanKonta).isEqualTo(spodziewanyStanKonta);
    }

    /*@Test
    void powinnoZwrocicStanKontaBezZmianJesliPodaneSaLitery() throws FileNotFoundException {
        //given
        Klient odbiorca = Klient.builder().idKlienta(4534).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(6000)).build();
        Klient nadawca = Klient.builder().idKlienta(5643).nrKonta("15435153").imie("Piotr").nazwisko("kjfsd").stanKonta(new BigDecimal(13000)).build();

        BigDecimal kwotaDoPrzelania = new BigDecimal("dsdds");

        BigDecimal spodziewanyWynik = new BigDecimal(13001);
        //when
        BigDecimal aktualnyStanKonta = przelewService.przelewBankowy(kwotaDoPrzelania, odbiorca, nadawca);
        //then
        assertThrows(NumberFormatException.class,() -> {
            BigDecimal
        });
    }*/
}
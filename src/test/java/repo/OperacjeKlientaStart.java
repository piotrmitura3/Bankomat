package repo;

import model.Klient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.OperacjeKlienta;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class OperacjeKlientaStart {
    private OperacjeKlienta operacjeKlienta;
    @BeforeEach
    void setup(){
        operacjeKlienta = new OperacjeKlienta();
    }
    @Test
    void powinnoZwrocicPoprawnaAkcje(){
        //given
        int rodzajOperacji = 1;
        BigDecimal kwotaDoWplaty = new BigDecimal(2000);
        Klient klient = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(15000)).build();
        BigDecimal spodziewanyWynik = new BigDecimal(17000);

        //when
        BigDecimal aktualna = operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWplaty, klient);

        //then
        assertThat(aktualna).isEqualTo(spodziewanyWynik);
    }

    @Test
    void powinnoZwrocicPoprawnyWynikWyplaty(){
        //given
        int rodzajOperacji = 2;
        BigDecimal kwotaDoWyplaty = new BigDecimal(6000);
        Klient klient = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(15000)).build();
        BigDecimal spodziewanyWynik = new BigDecimal(9000);

        //when
        BigDecimal aktualnyWynik = operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWyplaty, klient);

        //then
        assertThat(aktualnyWynik).isEqualTo(spodziewanyWynik);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianPoPodaniuBlednegoRodzajuOperacji(){
        //given
        int rodzajOperacji = 4;
        BigDecimal kwotaDoWplaty = new BigDecimal(6000);
        Klient klient = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(15000)).build();
        BigDecimal spodziwanyWynik = new BigDecimal(15000);

        //when
        BigDecimal aktualnyWynik = operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWplaty, klient);

        //then
        assertThat(aktualnyWynik).isEqualTo(spodziwanyWynik);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianJesliPodanaKwotaDoWyplatyBylaWiekszaNizStanKonta(){
        //given
        int rodzajOperacji = 2;
        BigDecimal kwotaDoWyplaty = new BigDecimal(16000);
        Klient klient = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(15000)).build();
        BigDecimal spodziewanyWynik = new BigDecimal(15000);

        //when
        BigDecimal aktualnyWynik = operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWyplaty, klient);

        //then
        assertThat(aktualnyWynik).isEqualTo(spodziewanyWynik);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianJesliPodanaKwotaDoWyplatyJestMniejszaOdZera(){
        //given
        int rodzajOperacji = 2;
        BigDecimal kwotaDoWyplaty = new BigDecimal(-1000);
        Klient klient = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(15000)).build();
        BigDecimal spodziewanyWynik = new BigDecimal(15000);

        //when
        BigDecimal aktualnyWynik = operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWyplaty, klient);

        //then
        assertThat(aktualnyWynik).isEqualTo(spodziewanyWynik);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianJesliKwotaPrzyWplacieJestMniejszaOdZera(){
        //given
        int rodzajOperacji = 1;
        BigDecimal kwotaDoWplaty = new BigDecimal(-1000);
        Klient klient = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(15000)).build();
        BigDecimal spodziewanyWynik = new BigDecimal(15000);

        //when
        BigDecimal aktualnyWynik = operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWplaty, klient);

        //then
        assertThat(aktualnyWynik).isEqualTo(spodziewanyWynik);
    }

    @Test
    void powinnoZwrocicStanKontaBezZmianJesliPodanaKwotaDOWplatyJestNullem(){
    //given
    int rodzajOperacji = 1;
    BigDecimal kwotaDoWplaty = null;
    Klient klient = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(15000)).build();
    BigDecimal spodziewanyWynik = new BigDecimal(15000);

    //when
    BigDecimal aktualnyWynik = operacjeKlienta.operacjeKlienta(rodzajOperacji, kwotaDoWplaty, klient);

    //then
    assertThat(aktualnyWynik).isEqualTo(spodziewanyWynik);
    }
}
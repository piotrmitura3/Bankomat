package service;


import model.Klient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import repo.BazaKlientow;
import service.KlientWeryfikator;

import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class KlientWeryfikatorTest {
    private KlientWeryfikator klientWeryfikator;
    @Mock
    private BazaKlientow bazaKlientow;
    @BeforeEach
    void setup(){
        klientWeryfikator = new KlientWeryfikator(bazaKlientow);
    }

    @Test
    void powinnaZwrocicPozytywnyWynikWeryfikacji(){
        //given
        Integer poszukiwanyNumer = 4534;
        boolean spodziewanyWynikWeryfikacji = true;

        Mockito.when(bazaKlientow.getListaNumerowKlientow()).thenReturn(przygotujKlientow());

        //when
        boolean aktualnyWynikWeryfikacji = klientWeryfikator.weryfikujKlienta(poszukiwanyNumer);

        //then
        assertThat(aktualnyWynikWeryfikacji).isEqualTo(spodziewanyWynikWeryfikacji);
    }

    @Test
    void powinnaZwrocicBlednyWynikWeryfikacji(){
        //given
        Integer poszukiwanyNumer =1234;

        Mockito.when(bazaKlientow.getListaNumerowKlientow()).thenReturn(przygotujKlientow());

        //when
        boolean aktualnyWynikWeryfikacji = klientWeryfikator.weryfikujKlienta(poszukiwanyNumer);

        //then
        assertThat(aktualnyWynikWeryfikacji).isFalse();
    }

    @Test
    void powinienZwrocicFalseJesliKlientJestNull(){
        //given

        Integer poszukiwanyNumer = null;

        //when
        boolean aktualnyWynikWeryfikacji = klientWeryfikator.weryfikujKlienta(poszukiwanyNumer);

        //then
        assertThat(aktualnyWynikWeryfikacji).isFalse();
    }


    private List<Klient> przygotujKlientow(){
        List<Klient> listaKlientow = new ArrayList<>();

        Klient klient1 = Klient.builder()
                .idKlienta(4534)
                .stanKonta(new BigDecimal(13000))
                .build();

        Klient klient2 = Klient.builder()
                .idKlienta(7564)
                .stanKonta(new BigDecimal(15000))
                .build();

        Klient klient3 = Klient.builder()
                .idKlienta(5644)
                .stanKonta(new BigDecimal(1200))
                .build();
        listaKlientow.add(klient1);
        listaKlientow.add(klient2);
        listaKlientow.add(klient3);
        return listaKlientow;
    }
    }


package repo;

import model.Klient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import service.KlientZnajdz;

import static org.assertj.core.api.Assertions.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class KlientZnajdzStart {
    private KlientZnajdz klientZnajdz;
    @Mock
    private BazaKlientow bazaKlientow;
    @BeforeEach
    void setup(){
        klientZnajdz = new KlientZnajdz(bazaKlientow);
    }

    @Test
    void powinnaZwrocicPoprawnegoKlienta(){
        //given
        Integer poszukiwanyKlient = 4534;
        Klient spodziewanyWynikZnajdowania = Klient.builder().idKlienta(4534).stanKontaKlienta(new BigDecimal(13000)).build();

        Mockito.when(bazaKlientow.getListaNumerowKlientow()).thenReturn(przygotujKlientow());

        //when
        Klient aktualnyWynikZnajdowania = klientZnajdz.znajdzKlienta(poszukiwanyKlient);

        //then
        assertThat(aktualnyWynikZnajdowania).isEqualTo(spodziewanyWynikZnajdowania);
    }

    @Test
    void powinnaZwrocicBlednyWynikZnajdowaniaKlienta(){
        //given
        Integer poszukiwanyKlient = 1234;
        Klient spodziewanyWynikZnajdowania = null;

        Mockito.when(bazaKlientow.getListaNumerowKlientow()).thenReturn(przygotujKlientow());

        //when
        Klient aktualnyWynikZnajdowania = klientZnajdz.znajdzKlienta(poszukiwanyKlient);

        //then
        assertThat(aktualnyWynikZnajdowania).isEqualTo(spodziewanyWynikZnajdowania);
    }

    @Test
    void powinnaZwrocicNullJesliPoszukiwantJestNullem(){
        //given
        Integer poszukiwanyNumer = null;
        Klient spodziewanyWynikZnajdowania = null;

        //when
        Klient aktualnyWynikZnajdowania = klientZnajdz.znajdzKlienta(poszukiwanyNumer);

        //then
        assertThat(aktualnyWynikZnajdowania).isEqualTo(spodziewanyWynikZnajdowania);
    }



    private List<Klient> przygotujKlientow(){
        List<Klient> listaKlientow = new ArrayList<>();

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
        listaKlientow.add(klient1);
        listaKlientow.add(klient2);
        listaKlientow.add(klient3);
        return listaKlientow;
    }

}
package repo;

import model.Klient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import service.Bankomat;
import service.KlientWeryfikator;
import service.KlientZnajdz;
import service.OperacjeKlienta;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BankomatStart {
    private Bankomat bankomat;
    @Mock
    private KlientWeryfikator klientWeryfikator;
    @Mock
    private KlientZnajdz klientZnajdz;
    @Mock
    private OperacjeKlienta operacjeKlienta;

    @BeforeEach
    void setup(){
        bankomat = new Bankomat(klientWeryfikator, operacjeKlienta, klientZnajdz);
    }

    @Test
    void powinienPrzejscWeryfikacjeIWywolacZadanaOperacje(){

        //given
        Mockito.when(klientWeryfikator.weryfikujKlienta(any())).thenReturn(true);
        Mockito.when(klientZnajdz.znajdzKlienta(any())).thenReturn(Klient.builder().build());
        Mockito.when(operacjeKlienta.operacjeKlienta(any(Integer.class), any(BigDecimal.class), any(Klient.class))).thenReturn(new BigDecimal(2000));
        //when
        bankomat.startBankomat(4534, 1, new BigDecimal(2000));
        //then
        Mockito.verify(klientWeryfikator).weryfikujKlienta(any());
        Mockito.verify(klientZnajdz).znajdzKlienta(any());
        Mockito.verify(operacjeKlienta).operacjeKlienta(any(Integer.class), any(BigDecimal.class), any(Klient.class));
    }

    @Test
    void niePowinienPrzejscWeryfikacjiIWywolacZadanaOperacje(){
        //given
        Mockito.when(klientWeryfikator.weryfikujKlienta(any())).thenReturn(false);
        Mockito.when(klientZnajdz.znajdzKlienta(any())).thenReturn(Klient.builder().build());
        Mockito.when(operacjeKlienta.operacjeKlienta(any(Integer.class), any(BigDecimal.class), any(Klient.class))).thenReturn(new BigDecimal(2000));
        //when
        bankomat.startBankomat(4534, 1, new BigDecimal(2000));
        //then
        Mockito.verify(klientWeryfikator, Mockito.times(1)).weryfikujKlienta(any());
        Mockito.verify(klientZnajdz, Mockito.never()).znajdzKlienta(any());
        Mockito.verify(operacjeKlienta, Mockito.never()).operacjeKlienta(any(Integer.class), any(BigDecimal.class), any(Klient.class));
    //ktore metody powinny sie wywolac a ktore nie i w jaki sposob mozna zmieniac ilosc wywolan metod w mockito verify
    }
}
package model;

import model.Klient;

import java.math.BigDecimal;

public class Przelew {
    private String nrKonta;
    private String tytul;
    private BigDecimal kwota;
    private Klient odbiorca;
    private Klient nadawca;
}

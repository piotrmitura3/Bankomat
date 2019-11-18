package model;

import model.Klient;

import java.math.BigDecimal;

public class Przelew {
    private String nrKonta;
    private String tytul;
    private BigDecimal kwota;
    private Klient odbiorca;
    private Klient nadawca;

    public Przelew() {
    }

    public Przelew(String nrKonta, String tytul, BigDecimal kwota, Klient odbiorca, Klient nadawca) {
        this.nrKonta = nrKonta;
        this.tytul = tytul;
        this.kwota = kwota;
        this.odbiorca = odbiorca;
        this.nadawca = nadawca;
    }

    public String getNrKonta() {
        return nrKonta;
    }

    public void setNrKonta(String nrKonta) {
        this.nrKonta = nrKonta;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public BigDecimal getKwota() {
        return kwota;
    }

    public void setKwota(BigDecimal kwota) {
        this.kwota = kwota;
    }

    public Klient getOdbiorca() {
        return odbiorca;
    }

    public void setOdbiorca(Klient odbiorca) {
        this.odbiorca = odbiorca;
    }

    public Klient getNadawca() {
        return nadawca;
    }

    public void setNadawca(Klient nadawca) {
        this.nadawca = nadawca;
    }
}

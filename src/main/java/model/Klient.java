package model;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
public class Klient {
    private Integer idKlienta;
    private BigDecimal stanKonta;
    private String nrKonta;
    private String imie;
    private String nazwisko;

    public Klient() {
    }

    public Klient(Integer idKlienta, BigDecimal stanKonta, String nrKonta, String imie, String nazwisko) {
        this.idKlienta = idKlienta;
        this.stanKonta = stanKonta;
        this.nrKonta = nrKonta;
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    public BigDecimal getStanKonta() {
        return stanKonta;
    }

    public void setStanKonta(BigDecimal stanKonta) {
        this.stanKonta = stanKonta;
    }

    public String getNrKonta() {
        return nrKonta;
    }

    public void setNrKonta(String nrKonta) {
        this.nrKonta = nrKonta;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klient klient = (Klient) o;
        return idKlienta.equals(klient.idKlienta) &&
                stanKonta.equals(klient.stanKonta) &&
                nrKonta.equals(klient.nrKonta) &&
                imie.equals(klient.imie) &&
                nazwisko.equals(klient.nazwisko);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlienta, stanKonta, nrKonta, imie, nazwisko);
    }

    @Override
    public String toString() {
        return imie + " " + nazwisko + " nr konta: " + nrKonta;
    }
}

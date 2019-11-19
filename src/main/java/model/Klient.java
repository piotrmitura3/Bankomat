package model;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
public class Klient {
    private Integer idKlienta;
    private BigDecimal stanKonta;
    private String nrKonta;



    public Klient() {
    }

    public Klient(Integer idKlienta, BigDecimal stanKonta, String nrKonta) {
        this.idKlienta = idKlienta;
        this.stanKonta = stanKonta;
        this.nrKonta = nrKonta;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klient klient = (Klient) o;
        return idKlienta.equals(klient.idKlienta) &&
                stanKonta.equals(klient.stanKonta) &&
                nrKonta.equals(klient.nrKonta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlienta, stanKonta, nrKonta);
    }

    @Override
    public String toString() {
        return "Klient{" +
                "idKlienta=" + idKlienta +
                ", stanKonta=" + stanKonta +
                ", nrKonta='" + nrKonta + '\'' +
                '}';
    }
}

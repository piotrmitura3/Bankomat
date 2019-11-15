package model;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.Objects;

@Builder
public class Klient {
    private Integer idKlienta;
    private BigDecimal stanKontaKlienta;

    public Klient(Integer idKlienta, BigDecimal stanKontaKlienta) {
        this.idKlienta = idKlienta;
        this.stanKontaKlienta = stanKontaKlienta;
    }

    public Klient() {
    }

    public Integer getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(Integer idKlienta) {
        this.idKlienta = idKlienta;
    }

    public BigDecimal getStanKontaKlienta() {
        return stanKontaKlienta;
    }

    public void setStanKontaKlienta(BigDecimal stanKontaKlienta) {
        this.stanKontaKlienta = stanKontaKlienta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Klient klient = (Klient) o;
        return Objects.equals(idKlienta, klient.idKlienta) &&
                Objects.equals(stanKontaKlienta, klient.stanKontaKlienta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKlienta, stanKontaKlienta);
    }

    @Override
    public String toString() {
        return "Klient{" +
                "idKlienta=" + idKlienta +
                ", stanKontaKlienta=" + stanKontaKlienta +
                '}';
    }
}

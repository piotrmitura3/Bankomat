package service;

import model.Klient;

import java.math.BigDecimal;

public class OperacjeKlienta {

    public BigDecimal operacjeKlienta(int rodzajOperacji, BigDecimal kwotaDoWplatyLubWyplaty, Klient klient) {
        BigDecimal stanKonta = klient.getStanKontaKlienta();
        switch (rodzajOperacji) {
            case 1:
                if (kwotaDoWplatyLubWyplaty == null){
                    System.out.println("Pusta kwota");
                    return stanKonta;
                }

                if (kwotaDoWplatyLubWyplaty.compareTo(BigDecimal.ZERO) > 0) {
                    stanKonta = stanKonta.add(kwotaDoWplatyLubWyplaty);
                    System.out.println("Stan konta po wplacie: " + stanKonta);
                    return stanKonta;
                } else {
                    System.out.println("Blad podczas wpisywania");
                    return stanKonta;
                }

            case 2:
                if (kwotaDoWplatyLubWyplaty == null){
                    System.out.println("Pusta kwota");
                    return stanKonta;
                }

                if (kwotaDoWplatyLubWyplaty.compareTo(BigDecimal.ZERO) < 0){
                    System.out.println("Podana kwota jest mniejsza od 0");
                    return stanKonta;
                }

                if (kwotaDoWplatyLubWyplaty.compareTo(stanKonta) <= 0) {
                    stanKonta = stanKonta.subtract(kwotaDoWplatyLubWyplaty);
                    System.out.println(stanKonta);
                    return stanKonta;
                } else {
                    System.out.println("Blad podczas wpisywania");
                    return stanKonta;
                }


            default:
                System.out.println("Bledna opcja");
                return stanKonta;
        }
    }
}

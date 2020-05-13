package pl.baksza.sklep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static java.lang.StrictMath.random;

@Component
public class ProduktStart {
    private static DecimalFormat df2 = new DecimalFormat("#.##");

    List<Produkt> koszykProduktow;
    Double ceneWszystkichProduktow;
    Double rabatWszystkichProduktow;
    Double sumaPodatkuVat;

    @Value("${info.stawkavat}")
    private int stawkavat;

    @Value("${info.stawkarabat}")
    private int stawkarabat;

    public int getStawkavat() {
        return stawkavat;
    }

    public void setStawkavat(int stawkavat) {
        this.stawkavat = stawkavat;
    }

    public int getStawkarabat() {
        return stawkarabat;
    }

    public void setStawkarabat(int stawkarabat) {
        this.stawkarabat = stawkarabat;
    }

    @Autowired
    public ProduktStart() {
        Produkt nowy_produkt;
        String nazwa_produktu;
        Double cena_produktu;
        Double podatekVat_produktu;
        Double rabat_produktu;

        setCeneWszystkichProduktow(0.0);
        setRabatWszystkichProduktow(0.0);
        setSumaPodatkuVat(0.0);

        this.koszykProduktow = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            nazwa_produktu = "Produkt Nr: " + Integer.toString(i + 1);
            cena_produktu = 50 + 250 * random();
            nowy_produkt = new Produkt(nazwa_produktu, cena_produktu);
            koszykProduktow.add(nowy_produkt);
        }
    }

    public void getKoszykProduktow() {

        System.out.println(koszykProduktow);
    }

    public Double getCeneWszystkichProduktow() {
        return ceneWszystkichProduktow;
    }

    public void setCeneWszystkichProduktow(Double ceneWszystkichProduktow) {
        this.ceneWszystkichProduktow = ceneWszystkichProduktow;
    }

    public Double getRabatWszystkichProduktow() {
        return rabatWszystkichProduktow;
    }

    public void setRabatWszystkichProduktow(Double rabatWszystkichProduktow) {
        this.rabatWszystkichProduktow = rabatWszystkichProduktow;
    }

    public Double getSumaPodatkuVat() {
        return sumaPodatkuVat;
    }

    public void setSumaPodatkuVat(Double sumaPodatkuVat) {
        this.sumaPodatkuVat = sumaPodatkuVat;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void getSklepInfo() {
        System.out.println(stawkavat);
        System.out.println(stawkarabat);
        getKoszykProduktow();

        Produkt produkt;
        for (int i = 0; i < koszykProduktow.size(); i++) {
            produkt = koszykProduktow.get(i);
            produkt.setRabat(produkt.getCena() * stawkarabat/100);
            produkt.setStawka_vat((produkt.getCena() - produkt.getRabat()) * stawkavat/100);

            setCeneWszystkichProduktow(getCeneWszystkichProduktow() + produkt.getCena());
            setSumaPodatkuVat(getSumaPodatkuVat() + produkt.getStawka_vat());
            setRabatWszystkichProduktow(getRabatWszystkichProduktow() + produkt.getRabat());
            System.out.println("Produkt: " + produkt.getNazwa() +" Cena: " + df2.format(produkt.getCena()) + " Rabat: " + df2.format(produkt.getRabat()) + " pod. Vat: " + df2.format(produkt.getStawka_vat()));
            System.out.println("Produkt: " + produkt.getNazwa() +" Cena - Rabat + Vat: " + df2.format((produkt.getCena() - produkt.getRabat() + produkt.getStawka_vat())));
        }

        //getKoszykProduktow();

        System.out.println("Cena Wszystkich Produktów: " + df2.format(getCeneWszystkichProduktow()));
        System.out.println("Rabat Wszystlich Produktów: " + df2.format(getRabatWszystkichProduktow()));
        System.out.println("Naliczony VAT od wszystkich produktów:" + df2.format(getSumaPodatkuVat()));
    }
}


//@ConfigurationProperties(prefix = "info")
/*
Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2020-05-13 10:04:41.289 ERROR 5500 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   :

***************************
APPLICATION FAILED TO START
***************************

Description:

Failed to bind properties under 'info' to pl.baksza.sklep.ProduktStart:

    Reason: Failed to bind properties under 'info' to pl.baksza.sklep.ProduktStart

Action:

Update your application's configuration
 */

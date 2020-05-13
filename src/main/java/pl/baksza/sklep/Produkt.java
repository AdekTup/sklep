package pl.baksza.sklep;

public class Produkt {
    String nazwa;
    double cena;
    double stawka_vat;
    double rabat;


    public Produkt(String nazwa, double cena) {
        this.nazwa = nazwa;
        this.cena = cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getStawka_vat() {
        return stawka_vat;
    }

    public void setStawka_vat(double stawka_vat) {
        this.stawka_vat = stawka_vat;
    }

    public double getRabat() {
        return rabat;
    }

    public void setRabat(double rabat) {
        this.rabat = rabat;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", stawka_vat=" + stawka_vat +
                ", rabat=" + rabat +
                '}';
    }
}

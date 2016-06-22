/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.RezervacijaVoznje;
import domen.StavkaRezervacijeVoznje;
import exception.ServerskiException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOZapamtiRezervaciju extends AbstractSO {

    private RezervacijaVoznje rezervacija;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        StavkaRezervacijeVoznje nova = new StavkaRezervacijeVoznje();
        nova.setRezervacijaVoznje(rezervacija);
        dbb.obrisiObjekat(nova);
        dbb.sacuvajIliAzurirajObjekat(rezervacija);
        for (StavkaRezervacijeVoznje stavkaRezervacijeVoznje : rezervacija.getListaStavki()) {
//            stavkaRezervacijeVoznje.setRedniBrojStavke(0);
            dbb.sacuvajIliAzurirajObjekat(stavkaRezervacijeVoznje);
        }

    }

    public RezervacijaVoznje getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(RezervacijaVoznje rezervacija) {
        this.rezervacija = rezervacija;
    }

}

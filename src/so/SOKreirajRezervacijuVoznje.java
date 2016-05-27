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

/**
 *
 * @author Daniel
 */
public class SOKreirajRezervacijuVoznje extends AbstractSO{

    private AbstractObjekat rezv;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        rezv = dbb.sacuvajIliAzurirajObjekat(rezv);
        sacuvajStavke();
        
    }

    public AbstractObjekat getRezv() {
        return rezv;
    }

    public void setRezv(AbstractObjekat rezv) {
        this.rezv = rezv;
    }

    private void sacuvajStavke() throws ServerskiException {
        RezervacijaVoznje rezervacija = (RezervacijaVoznje) rezv;
        for (StavkaRezervacijeVoznje stavka : rezervacija.getListaStavki()) {
            stavka.setRezervacijaVoznje(rezervacija);
            dbb.sacuvajIliAzurirajObjekat(stavka);
        }
    }
    
}

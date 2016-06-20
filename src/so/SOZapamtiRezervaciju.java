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
public class SOZapamtiRezervaciju extends AbstractSO{
    private RezervacijaVoznje rezervacija;
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObjekat> listaIzBaze = dbb.vratiSveObjekte(new StavkaRezervacijeVoznje());
        LinkedList<StavkaRezervacijeVoznje> listaBrisanje = new LinkedList<>();
        for (AbstractObjekat abstractObjekat : listaIzBaze) {
            StavkaRezervacijeVoznje srv = (StavkaRezervacijeVoznje) abstractObjekat;
            if(srv.getRezervacijaVoznje().getRezevacijaID().equals(rezervacija.getRezevacijaID())){
                listaBrisanje.add(srv);
            }
            for (StavkaRezervacijeVoznje stavkaRezervacijeVoznje : listaBrisanje) {
                dbb.obrisiObjekat(stavkaRezervacijeVoznje);
            }
            dbb.sacuvajIliAzurirajObjekat(rezervacija);
            for (StavkaRezervacijeVoznje stavkaRezervacijeVoznje : rezervacija.getListaStavki()) {
                dbb.sacuvajIliAzurirajObjekat(stavkaRezervacijeVoznje);
            }
            
        }
    }

    public RezervacijaVoznje getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(RezervacijaVoznje rezervacija) {
        this.rezervacija = rezervacija;
    }
    
}

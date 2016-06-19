/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Korisnik;
import exception.ServerskiException;
import kontroler.Kontroler;

/**
 *
 * @author Daniel
 */
public class SOIzlogujKorisnika extends AbstractSO{

    private AbstractObjekat korisnik;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        int indeks = Kontroler.vratiKontrolera().getListaKorisnika().indexOf(korisnik);
        ((Korisnik)Kontroler.vratiKontrolera().getListaKorisnika().get(indeks)).setUlogovan(false);
    }

    public AbstractObjekat getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(AbstractObjekat korisnik) {
        this.korisnik = korisnik;
    }
    
}

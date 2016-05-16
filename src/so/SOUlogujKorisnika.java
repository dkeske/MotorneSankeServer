/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Korisnik;
import exception.ServerskiException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class SOUlogujKorisnika extends AbstractSO{

    private AbstractObjekat unetiParametri;
    private AbstractObjekat ulogovanKorisnik;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObjekat> listaKorisnika = dbb.vratiSveObjekte(new Korisnik());
        Korisnik unetiKorisnik = (Korisnik) unetiParametri;
        for (AbstractObjekat abstractObjekat : listaKorisnika) {
            Korisnik korIzBaz = (Korisnik) abstractObjekat;
            if(korIzBaz.equals(unetiKorisnik)){
                ulogovanKorisnik = korIzBaz;
                return;
            }
        }
    }

    public AbstractObjekat getUnetiParametri() {
        return unetiParametri;
    }

    public void setUnetiParametri(AbstractObjekat unetiParametri) {
        this.unetiParametri = unetiParametri;
    }

    public AbstractObjekat getUlogovanKorisnik() {
        return ulogovanKorisnik;
    }

    public void setUlogovanKorisnik(AbstractObjekat ulogovanKorisnik) {
        this.ulogovanKorisnik = ulogovanKorisnik;
    }
    
}

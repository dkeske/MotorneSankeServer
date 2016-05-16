/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.AbstractObjekat;
import domen.Korisnik;
import exception.ServerskiException;
import java.util.List;
import so.SOObrisiKlijenta;
import so.SOSacuvajKlijenta;
import so.SOUcitajListuKorisnika;
import so.SOUcitajListuMotornihSanki;
import so.SOUlogujKorisnika;

/**
 *
 * @author Daniel
 */
public class Kontroler {
    private static Kontroler instance;
    
    public static Kontroler vratiKontrolera(){
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }

    public List<AbstractObjekat> vratiListuMotornihSanki() throws ServerskiException {
        SOUcitajListuMotornihSanki sou = new SOUcitajListuMotornihSanki();
        sou.izvrsiOperaciju();
        return sou.getListaSanki();
    }

    public AbstractObjekat ulogujKorisnika(Korisnik k) throws ServerskiException {
        SOUlogujKorisnika sok = new SOUlogujKorisnika();
        sok.setUnetiParametri(k);
        sok.izvrsiOperaciju();
        return sok.getUlogovanKorisnik();
    }

    public List<AbstractObjekat> vratiListuKorisnika() throws ServerskiException {
        SOUcitajListuKorisnika soul = new SOUcitajListuKorisnika();
        soul.izvrsiOperaciju();
        return soul.getLista();
    }

    public void obrisiKorisnika(AbstractObjekat korisnik) throws ServerskiException {
        SOObrisiKlijenta soob = new SOObrisiKlijenta(korisnik);
        soob.izvrsiOperaciju();
    }
    public AbstractObjekat sacuvajKorisnika(AbstractObjekat korisnik) throws ServerskiException {
        SOSacuvajKlijenta sosk = new SOSacuvajKlijenta(korisnik);
        sosk.izvrsiOperaciju();
        return sosk.getKlijent();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.AbstractObjekat;
import domen.Korisnik;
import domen.MotorneSanke;
import exception.ServerskiException;
import java.util.List;
import so.SOKreirajMotorneSanke;
import so.SOObrisiKorisnika;
import so.SOSacuvajKorisnika;
import so.SOUcitajListuKorisnika;
import so.SOUcitajListuMotornihSanki;
import so.SOUcitajListuRezervacija;
import so.SOUcitajListuTipovaMS;
import so.SOUlogujKorisnika;

/**
 *
 * @author Daniel
 */
public class Kontroler {

    private static Kontroler instance;

    public static Kontroler vratiKontrolera() {
        if (instance == null) {
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
        SOObrisiKorisnika soob = new SOObrisiKorisnika(korisnik);
        soob.izvrsiOperaciju();
    }

    public AbstractObjekat sacuvajKorisnika(AbstractObjekat korisnik) throws ServerskiException {
        SOSacuvajKorisnika sosk = new SOSacuvajKorisnika(korisnik);
        sosk.izvrsiOperaciju();
        return sosk.getKlijent();
    }

    public List<AbstractObjekat> vratiListuRezervacija() throws ServerskiException {
        SOUcitajListuRezervacija soulr = new SOUcitajListuRezervacija();
        soulr.izvrsiOperaciju();
        return soulr.getListaRezervacija();
    }

    public List<AbstractObjekat> vratiListuTipovaMS() throws ServerskiException {
        SOUcitajListuTipovaMS soult = new SOUcitajListuTipovaMS();
        soult.izvrsiOperaciju();
        return soult.getListaTipova();
    }

    public AbstractObjekat kreirajMotorneSanke(MotorneSanke motorneSanke) throws ServerskiException {
        SOKreirajMotorneSanke sokms = new SOKreirajMotorneSanke(motorneSanke);
        sokms.izvrsiOperaciju();
        return sokms.getMotorneSanke();
    }
}

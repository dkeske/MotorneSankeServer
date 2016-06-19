/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.AbstractObjekat;
import domen.Korisnik;
import domen.MotorneSanke;
import domen.RezervacijaVoznje;
import exception.ServerskiException;
import java.util.List;
import so.SOKreirajMotorneSanke;
import so.SOKreirajRezervacijuVoznje;
import so.SOObrisiKorisnika;
import so.SOSacuvajKorisnika;
import so.SOUcitajListuKorisnika;
import so.SOUcitajListuMotornihSanki;
import so.SOUcitajListuRezervacija;
import so.SOUcitajListuTipovaMS;
import so.SOUcitajListuVozaca;
import so.SOUlogujKorisnika;

/**
 *
 * @author Daniel
 */
public class Kontroler {

    private static Kontroler instance;
    private List<AbstractObjekat> listaKorisnika;

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

    public List<AbstractObjekat> ucitajListuVozaca() throws ServerskiException {
        SOUcitajListuVozaca soulv = new SOUcitajListuVozaca();
        soulv.izvrsiOperaciju();
        return soulv.getListaVozaca();
    }

    public AbstractObjekat kreirajRezervacijuVoznje(RezervacijaVoznje rezervacijaVoznje) throws ServerskiException {
        SOKreirajRezervacijuVoznje sokrv = new SOKreirajRezervacijuVoznje();
        sokrv.setRezv(rezervacijaVoznje);
        sokrv.izvrsiOperaciju();
        return sokrv.getRezv();
    }

    public List<AbstractObjekat> getListaKorisnika() throws ServerskiException {
        if(listaKorisnika == null){
            listaKorisnika = vratiListuKorisnika();
        }
        return listaKorisnika;
    }

    public void setListaKorisnika(List<AbstractObjekat> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }
}

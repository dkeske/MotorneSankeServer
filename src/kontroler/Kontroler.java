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
import so.SOIzlogujKorisnika;
import so.SOZapamtiMotorneSanke;
import so.SOKreirajRezervacijuVoznje;
import so.SOObrisiKorisnika;
import so.SOObrisiMotorneSanke;
import so.SOObrisiRezervaciju;
import so.SOPretraziMotorneSanke;
import so.SOPretraziRezervacijuVoznje;
import so.SOPretraziVozace;
import so.SOSacuvajKorisnika;
import so.SOUcitajListuKorisnika;
import so.SOUcitajListuMotornihSanki;
import so.SOUcitajListuRezervacija;
import so.SOUcitajListuTipovaMS;
import so.SOUcitajListuVozaca;
import so.SOUlogujKorisnika;
import so.SOZapamtiRezervaciju;

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
        SOZapamtiMotorneSanke sokms = new SOZapamtiMotorneSanke(motorneSanke);
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

    public void izlogujKorisnika(AbstractObjekat korisnik) throws ServerskiException {
        SOIzlogujKorisnika soik = new SOIzlogujKorisnika();
        soik.setKorisnik(korisnik);
        soik.izvrsiOperaciju();
    }

    public AbstractObjekat zapamtiRezervacijuVoznje(RezervacijaVoznje rezervacijaVoznje) throws ServerskiException {
        SOZapamtiRezervaciju soza = new SOZapamtiRezervaciju();
        soza.setRezervacija(rezervacijaVoznje);
        soza.izvrsiOperaciju();
        return soza.getRezervacija();
    }

    public List<AbstractObjekat> pretraziRezervacije(String string) throws ServerskiException {
        SOPretraziRezervacijuVoznje soprv = new SOPretraziRezervacijuVoznje();
        soprv.setPretraga(string);
        soprv.izvrsiOperaciju();
        return soprv.getFilterLista();
    }

    public List<AbstractObjekat> pretraziSanke(String string) throws ServerskiException {
        SOPretraziMotorneSanke sopms = new SOPretraziMotorneSanke();
        sopms.setPretraga(string);
        sopms.izvrsiOperaciju();
        return sopms.getFilterLista();
    }

    public List<AbstractObjekat> pretraziVozace(String string) throws ServerskiException {
        SOPretraziVozace sopv = new SOPretraziVozace();
        sopv.setPretraga(string);
        sopv.izvrsiOperaciju();
        return sopv.getFilterLista();
    }

    public List<AbstractObjekat> obrisiSanke(MotorneSanke motorneSanke) throws ServerskiException {
        SOObrisiMotorneSanke soom = new SOObrisiMotorneSanke();
        soom.setSanke(motorneSanke);
        soom.izvrsiOperaciju();
        return soom.getLista();
    }

    public List<AbstractObjekat> obrisiRezervaciju(RezervacijaVoznje rezervacijaVoznje) throws ServerskiException {
        SOObrisiRezervaciju soobr = new SOObrisiRezervaciju(rezervacijaVoznje);
        soobr.izvrsiOperaciju();
        return soobr.getLista();
    }
}

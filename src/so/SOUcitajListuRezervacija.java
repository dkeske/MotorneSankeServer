/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import domen.RezervacijaVoznje;
import domen.StavkaRezervacijeVoznje;
import domen.TipSanki;
import domen.Vozac;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOUcitajListuRezervacija extends AbstractSO {

    private List<AbstractObjekat> listaRezervacija;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaRezervacija = dbb.vratiSveObjekte(new RezervacijaVoznje());
        postaviVozace();
        postaviListuStavki();
    }

    public List<AbstractObjekat> getListaRezervacija() {
        return listaRezervacija;
    }

    public void setListaRezervacija(List<AbstractObjekat> listaRezervacija) {
        this.listaRezervacija = listaRezervacija;
    }

    private void postaviListuStavki() throws ServerskiException {
        List<AbstractObjekat> listaSvihStavki = dbb.vratiSveObjekte(new StavkaRezervacijeVoznje());
        for (AbstractObjekat abstractObjekat : listaRezervacija) {
            RezervacijaVoznje rv = (RezervacijaVoznje) abstractObjekat;
            List<StavkaRezervacijeVoznje> odgovarajuce = new ArrayList<>();
            for (AbstractObjekat abstractObjekat1 : listaSvihStavki) {
                StavkaRezervacijeVoznje stav = (StavkaRezervacijeVoznje) abstractObjekat1;
                if(stav.getRezervacijaVoznje().getRezevacijaID().equals(rv.getRezevacijaID())){
                    odgovarajuce.add(stav);
                }
            }
            for (StavkaRezervacijeVoznje odgStavka : odgovarajuce) {
                odgStavka.setRezervacijaVoznje(rv);
                MotorneSanke mss = (MotorneSanke) dbb.vratiObjekatPoKljucu(new MotorneSanke(), odgStavka.getMotorneSanke().getMotorneSankeID());
                mss.setTipSanki((TipSanki) dbb.vratiObjekatPoKljucu(new TipSanki(), mss.getTipSanki().getTipSankiID()));
                odgStavka.setMotorneSanke(mss);
                rv.getListaStavki().add(odgStavka);
                listaSvihStavki.remove(odgStavka);
            }
        }
    }

    private void postaviVozace() throws ServerskiException {
        for (AbstractObjekat abstractObjekat : listaRezervacija) {
            RezervacijaVoznje rv = (RezervacijaVoznje) abstractObjekat;
            rv.setVozac((Vozac) dbb.vratiObjekatPoKljucu(new Vozac(), rv.getVozac().getVozacID()));
        }
    }
}
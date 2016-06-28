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
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOZapamtiRezervaciju extends AbstractSO {

    private RezervacijaVoznje rezervacija;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
//        proveriDupliUnos();
        StavkaRezervacijeVoznje nova = new StavkaRezervacijeVoznje();
        nova.setRezervacijaVoznje(rezervacija);
        dbb.obrisiObjekat(nova);
        dbb.sacuvajIliAzurirajObjekat(rezervacija);
        for (StavkaRezervacijeVoznje stavkaRezervacijeVoznje : rezervacija.getListaStavki()) {
            dbb.sacuvajObjekat(stavkaRezervacijeVoznje);
        }

    }

    public RezervacijaVoznje getRezervacija() {
        return rezervacija;
    }

    public void setRezervacija(RezervacijaVoznje rezervacija) {
        this.rezervacija = rezervacija;
    }

//    private void proveriDupliUnos() throws ServerskiException {
//        List<AbstractObjekat> listaRezervacije = dbb.vratiSveObjekte(new RezervacijaVoznje());
//        for (AbstractObjekat abstractObjekat : listaRezervacije) {
//            RezervacijaVoznje rez = (RezervacijaVoznje) abstractObjekat;
//            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//            if(!rez.equals(rezervacija) && sdf.format(rez.getDatumRezervacije()).equals(sdf.format(rezervacija.getDatumRezervacije()))){
//                for (AbstractObjekat abstractObjekat1 : listaRezervacije) {
//                    
//                }
//            }
//        }
//    }

}

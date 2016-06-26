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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOPretraziRezervacijuVoznje extends AbstractSO {

    private String pretraga;
    private List<AbstractObjekat> filterLista;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        SOUcitajListuRezervacija sou = new SOUcitajListuRezervacija();
        sou.izvrsiOperaciju();
        List<AbstractObjekat> izBaze = sou.getListaRezervacija();
        filterLista = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObjekat abstractObjekat : izBaze) {
            RezervacijaVoznje rez = (RezervacijaVoznje) abstractObjekat;
            if (rez.getVozac().getIme().toLowerCase().contains(pretraga)
                    || (rez.isUplataUnapred() && pretraga.equals("da"))
                    || (!rez.isUplataUnapred() && pretraga.equals("da"))
                    || (rez.getDatumRezervacije().toString().contains(pretraga))
                    || sadrziMotorneSanke(rez, pretraga)) {
                filterLista.add(rez);
            }
        }
    }

    public List<AbstractObjekat> getFilterLista() {
        return filterLista;
    }

    public void setFilterLista(List<AbstractObjekat> filterLista) {
        this.filterLista = filterLista;
    }

    public String getPretraga() {
        return pretraga;
    }

    public void setPretraga(String pretaga) {
        this.pretraga = pretaga;
    }

    private boolean sadrziMotorneSanke(RezervacijaVoznje rez, String pretraga) {
        for (StavkaRezervacijeVoznje stav : rez.getListaStavki()) {
            if(stav.getMotorneSanke().getBrojSasije().toLowerCase().contains(pretraga)){
                System.out.println("NASAO SAM SANKE U STAVCI");
                return true;
            }
        }
        
        return false;
    }

}

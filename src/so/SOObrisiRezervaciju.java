/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.RezervacijaVoznje;
import exception.ServerskiException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOObrisiRezervaciju extends AbstractSO{
    private RezervacijaVoznje rez;
    private List<AbstractObjekat> lista;

    public SOObrisiRezervaciju(RezervacijaVoznje rez) {
        this.rez = rez;
    }
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        dbb.obrisiObjekat(rez);
        lista = dbb.vratiSveObjekte(new RezervacijaVoznje());
    }

    public RezervacijaVoznje getRez() {
        return rez;
    }

    public void setRez(RezervacijaVoznje rez) {
        this.rez = rez;
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }

    public void setLista(List<AbstractObjekat> lista) {
        this.lista = lista;
    }
    
}

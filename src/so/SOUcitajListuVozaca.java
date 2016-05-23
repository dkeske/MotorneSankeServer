/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Vozac;
import exception.ServerskiException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOUcitajListuVozaca extends AbstractSO{
    
    private List<AbstractObjekat> listaVozaca;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaVozaca = dbb.vratiSveObjekte(new Vozac());
    }

    public List<AbstractObjekat> getListaVozaca() {
        return listaVozaca;
    }
    
}

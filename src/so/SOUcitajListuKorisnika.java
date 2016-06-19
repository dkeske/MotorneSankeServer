/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Korisnik;
import exception.ServerskiException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOUcitajListuKorisnika extends AbstractSO {

    private List<AbstractObjekat> lista;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {

        lista = dbb.vratiSveObjekte(new Korisnik());

    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }

}

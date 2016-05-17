/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import exception.ServerskiException;

/**
 *
 * @author Daniel
 */
public class SOObrisiKorisnika extends AbstractSO{
    private AbstractObjekat klijent;

    public SOObrisiKorisnika(AbstractObjekat klijent) {
        this.klijent = klijent;
    }
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        dbb.obrisiObjekat(klijent);
    }

    public AbstractObjekat getKlijent() {
        return klijent;
    }
    
}

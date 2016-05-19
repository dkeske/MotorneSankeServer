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
public class SOObrisiMotorneSanke extends AbstractSO{
    private AbstractObjekat sanke;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        dbb.obrisiObjekat(sanke);
    }

    public AbstractObjekat getSanke() {
        return sanke;
    }

    public void setSanke(AbstractObjekat sanke) {
        this.sanke = sanke;
    }
    
}

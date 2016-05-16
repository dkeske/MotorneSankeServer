/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;

/**
 *
 * @author Daniel
 */
public class SOSacuvajKlijenta extends AbstractSO{
    private AbstractObjekat klijent;

    public SOSacuvajKlijenta(AbstractObjekat klijent) {
        this.klijent = klijent;
    }
    @Override
    protected void izvrsiKonkretnuOperaciju() {
        klijent = dbb.sacuvajObjekat(klijent);
    }

    public AbstractObjekat getKlijent() {
        return klijent;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import exception.ServerskiException;

/**
 *
 * @author Daniel
 */
public class SOKreirajMotorneSanke extends AbstractSO {

    private AbstractObjekat param;
    private AbstractObjekat motorneSanke;

    public SOKreirajMotorneSanke(AbstractObjekat param) {
        this.param = param;
    }
    
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        motorneSanke = dbb.sacuvajIliAzurirajObjekat(param);
    }

    public AbstractObjekat getMotorneSanke() {
        return motorneSanke;
    }

    public void setMotorneSanke(AbstractObjekat motorneSanke) {
        this.motorneSanke = motorneSanke;
    }

}

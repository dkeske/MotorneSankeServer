/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;
import exception.ServerskiException;

/**
 *
 * @author Daniel
 */
public abstract class AbstractSO {

    public AbstractSO() {
        this.dbb = new DatabaseBroker();
    }
    
    
    protected DatabaseBroker dbb;
    
    synchronized public void izvrsiOperaciju() throws ServerskiException{
        otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        potvrdiTransakciju();
        zatvoriKonekciju();
    }

    private void potvrdiTransakciju() throws ServerskiException {
        dbb.potvrdiTransakciju();
    }

    private void zatvoriKonekciju() throws ServerskiException {
        dbb.raskiniKonekciju();
    }

    private void otvoriKonekciju() throws ServerskiException {
        dbb.uspostaviKonekciju();
    }
    
    protected abstract void izvrsiKonkretnuOperaciju() throws ServerskiException;
}

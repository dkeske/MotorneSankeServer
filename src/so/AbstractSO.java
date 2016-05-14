/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;

/**
 *
 * @author Daniel
 */
public abstract class AbstractSO {

    public AbstractSO() {
        this.dbb = new DatabaseBroker();
    }
    
    
    protected DatabaseBroker dbb;
    
    synchronized public void izvrsiOperaciju(){
        otvoriKonekciju();
        izvrsiKonkretnuOperaciju();
        potvrdiTransakciju();
        zatvoriKonekciju();
    }

    private void potvrdiTransakciju() {
        dbb.potvrdiTransakciju();
    }

    private void zatvoriKonekciju() {
        dbb.raskiniKonekciju();
    }

    private void otvoriKonekciju() {
        dbb.uspostaviKonekciju();
    }
    
    protected abstract void izvrsiKonkretnuOperaciju();
}

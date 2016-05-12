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
    
    public void izvrsiOperaciju(){
        dbb.uspostaviKonekciju();
        izvrsiKonkretnuOperaciju();
        dbb.potvrdiTransakciju();
        
        
        dbb.raskiniKonekciju();
    }
    
    protected abstract void izvrsiKonkretnuOperaciju();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.TipSanki;
import exception.ServerskiException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOUcitajListuTipovaMS extends AbstractSO {

    private List<AbstractObjekat> listaTipova;

    public SOUcitajListuTipovaMS() {
    }

    public List<AbstractObjekat> getListaTipova() {
        return listaTipova;
    }

    public void setListaTipova(List<AbstractObjekat> listaTipova) {
        this.listaTipova = listaTipova;
    }

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaTipova = dbb.vratiSveObjekte(new TipSanki());
    }

}

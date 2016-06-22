/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOPretraziMotorneSanke extends AbstractSO {

    private String pretraga;
    private List<AbstractObjekat> filterLista;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        SOUcitajListuMotornihSanki sou = new SOUcitajListuMotornihSanki();
        sou.izvrsiOperaciju();
        List<AbstractObjekat> izBaze = sou.getListaSanki();
        filterLista = new ArrayList<>();
        for (AbstractObjekat abstractObjekat : izBaze) {
            MotorneSanke ms = (MotorneSanke) abstractObjekat;
            if (ms.getBrojSasije().contains(pretraga)
                    || ms.getMotorneSankeID().contains(pretraga)
                    || ms.getTipSanki().getNazivTipa().contains(pretraga)
                    || ms.getTipSanki().getNamena().contains(pretraga)) {
                filterLista.add(ms);
            }
        }
    }

    public String getPretraga() {
        return pretraga;
    }

    public void setPretraga(String pretraga) {
        this.pretraga = pretraga;
    }

    public List<AbstractObjekat> getFilterLista() {
        return filterLista;
    }

    public void setFilterLista(List<AbstractObjekat> filterLista) {
        this.filterLista = filterLista;
    }

}

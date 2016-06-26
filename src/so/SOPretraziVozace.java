/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Vozac;
import exception.ServerskiException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOPretraziVozace extends AbstractSO{

    private String pretraga;
    private List<AbstractObjekat> filterLista;
    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        List<AbstractObjekat> izBaze = dbb.vratiSveObjekte(new Vozac());
        filterLista = new ArrayList<>();
        pretraga = pretraga.toLowerCase();
        for (AbstractObjekat abstractObjekat : izBaze) {
            Vozac v = (Vozac) abstractObjekat;
            if(v.getIme().toLowerCase().contains(pretraga)
                    || v.getMail().toLowerCase().contains(pretraga)
                    || v.getDatumPrveVoznje().toString().contains(pretraga)){
                filterLista.add(abstractObjekat);
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

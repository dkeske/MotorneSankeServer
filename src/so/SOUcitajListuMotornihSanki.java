/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import domen.TipSanki;
import exception.ServerskiException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOUcitajListuMotornihSanki extends AbstractSO {

    private List<AbstractObjekat> listaSanki;

    @Override
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        listaSanki = dbb.vratiSveObjekte(new MotorneSanke());
        ucitajTipove();
    }

    public List<AbstractObjekat> getListaSanki() {
        return listaSanki;
    }

    private void ucitajTipove() throws ServerskiException {
        for (AbstractObjekat abstractObjekat : listaSanki) {
            MotorneSanke ms = (MotorneSanke) abstractObjekat;
            ms.setTipSanki((TipSanki) dbb.vratiObjekatPoKljucu(new TipSanki(), ms.getTipSanki().getTipSankiID()));
        }
    }

}

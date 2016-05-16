/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import domen.TipSanki;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOUcitajListuMotornihSanki extends AbstractSO{

    private List<AbstractObjekat> listaSanki;
    @Override
    protected void izvrsiKonkretnuOperaciju() {
        try {
            listaSanki = dbb.vratiSveObjekte(new MotorneSanke());
            ucitajTipove();
        } catch (SQLException ex) {
            System.out.println("Vracanje sanki nije uspelo!");
        }
    }

    public List<AbstractObjekat> getListaSanki() {
        return listaSanki;
    }

    private void ucitajTipove() {
        for (AbstractObjekat abstractObjekat : listaSanki) {
            MotorneSanke ms = (MotorneSanke) abstractObjekat;
            ms.setTipSanki((TipSanki) dbb.vratiObjekatPoKljucu(new TipSanki(), ms.getTipSanki().getTipSankiID()));
        }
    }
    
}

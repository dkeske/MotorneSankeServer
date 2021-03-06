/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import exception.ServerskiException;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class SOObrisiMotorneSanke extends AbstractSO{
    private AbstractObjekat sanke;
    private List<AbstractObjekat> lista;
    @Override
    
    protected void izvrsiKonkretnuOperaciju() throws ServerskiException {
        dbb.obrisiObjekat(sanke);
        lista = dbb.vratiSveObjekte(new MotorneSanke());
    }

    public SOObrisiMotorneSanke() {
    }

    public AbstractObjekat getSanke() {
        return sanke;
    }

    public void setSanke(AbstractObjekat sanke) {
        this.sanke = sanke;
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }

    public void setLista(List<AbstractObjekat> lista) {
        this.lista = lista;
    }
    
}

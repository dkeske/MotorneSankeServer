/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.AbstractObjekat;
import domen.Korisnik;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class SOUcitajListuKorisnika extends AbstractSO{

    private List<AbstractObjekat> lista;
    @Override
    protected void izvrsiKonkretnuOperaciju() {
        try {
            lista = dbb.vratiSveObjekte(new Korisnik());
        } catch (SQLException ex) {
            Logger.getLogger(SOUcitajListuKorisnika.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<AbstractObjekat> getLista() {
        return lista;
    }
    
}

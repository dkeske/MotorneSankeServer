/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme.model;

import domen.AbstractObjekat;
import domen.Korisnik;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Daniel
 */
public class ModelKorisnik extends AbstractTableModel {

    private List<AbstractObjekat> listaKorisnika;

    public ModelKorisnik(List<AbstractObjekat> listaKorisnika) {
        this.listaKorisnika = listaKorisnika;
    }

    @Override
    public int getRowCount() {
        return listaKorisnika.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
//        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Korisnik k = (Korisnik) listaKorisnika.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return k.getKorisnikID();
            case 1:
                return k.getIme();
            case 2:
                return k.getKorisnickoIme();
            case 3:
                return k.getPassword();
            case 4:
                return k.getStatusText();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID korisnika";
            case 1:
                return "Ime";
            case 2:
                return "Korisnicko ime";
            case 3:
                return "Password";
            case 4:
                return "Status";
            default:
                return "n/a";
        }
    }

    public List<AbstractObjekat> getListaKorisnika() {
        return listaKorisnika;
    }

}

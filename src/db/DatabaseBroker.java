/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.AbstractObjekat;
import exception.ServerskiException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class DatabaseBroker {

    private Connection connection;

    public DatabaseBroker() {
    }

    public void uspostaviKonekciju() throws ServerskiException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver baze ucitan!");
            String url = "jdbc:mysql://localhost:3306/motorne_sanke";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Uspesno uspostavljanje konekcije!");
        } catch (ClassNotFoundException ex) {
            throw new ServerskiException("Drajver nije pronadjen!");
        } catch (SQLException ex) {
            throw new ServerskiException("Konekcija na bazu nije uspela!");
        }
    }

    public void raskiniKonekciju() throws ServerskiException {
        try {
            connection.close();
            System.out.println("Uspesno raskidanje konekcije!");
        } catch (SQLException ex) {
            throw new ServerskiException("Raskidanje konekcije nije uspelo!");
        }
    }

    public void potvrdiTransakciju() throws ServerskiException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new ServerskiException("Commit nije uspeo!");
        }
    }

    public void ponistiTransakciju() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.out.println("Rollback nije uspeo!");
        }
    }

    public List<AbstractObjekat> vratiSveObjekte(AbstractObjekat o) throws ServerskiException {

        try {
            String upit = "SELECT * FROM " + o.vratiImeTabele();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspesno izvrsen SELECT");
            return listaObjekata;

        } catch (SQLException ex) {
            throw new ServerskiException("Server ne moze da prikaze podatke o " + o.getClass().getName() + ".");
        }
    }

    public AbstractObjekat vratiObjekatPoKljucu(AbstractObjekat o, String ID) throws ServerskiException {
        String upit;
        if (o.vratiPK() != null) {
            upit = "SELECT * FROM " + o.vratiImeTabele() + " WHERE " + o.vratiPK() + "=" + ID;
        } else {
            upit = "SELECT * FROM " + o.vratiImeTabele() + " WHERE " + o.vratiSlozenPK();
        }
        try (Statement s = connection.createStatement();) {
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspesno izvrsen mini SELECT");
            return listaObjekata.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObjekat sacuvajIliAzurirajObjekat(AbstractObjekat o) throws ServerskiException {
        try {
            List<AbstractObjekat> lista = vratiSveObjekte(o);
            String upit;
            if (lista.contains(o)) {
                if (o.vratiPK() != null) {
                    upit = String.format("UPDATE %s SET %s WHERE %s = %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiPK(), o.vratiVrednostPK());
                } else {
                    upit = String.format("UPDATE %s SET %s WHERE %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiSlozenPK());
                }
            } else {
                upit = String.format("INSERT INTO %s VALUES (%s)", o.vratiImeTabele(), o.vratiParametre());
            }
            System.out.println(upit);
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
    }

    public AbstractObjekat obrisiObjekat(AbstractObjekat o) throws ServerskiException {
        try {
            String upit = String.format("DELETE FROM %s WHERE %s = %s", o.vratiImeTabele(), o.vratiPK(), o.vratiVrednostPK());
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            potvrdiTransakciju();
            s.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new ServerskiException(ex.getMessage());
        }
        return o;
    }

    public AbstractObjekat azurirajObjekat(AbstractObjekat o) throws ServerskiException {
        List<AbstractObjekat> lista = vratiSveObjekte(o);

        String upit;
        if (lista.contains(o)) {
            try {
                upit = String.format("UPDATE %s SET %s WHERE %s = %s", o.vratiImeTabele(), o.vratiUpdate(), o.vratiPK(), o.vratiVrednostPK());
                Statement s = connection.createStatement();
                s.executeUpdate(upit);
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
                throw new ServerskiException(ex.getMessage());
            }

        } else {
            sacuvajIliAzurirajObjekat(o);
        }
        return o;
    }
}

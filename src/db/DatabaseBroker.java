/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public void uspostaviKonekciju() {
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
            System.out.println("Driver nije nadjen!");

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void raskiniKonekciju() {
        try {
            connection.close();
            System.out.println("Uspesno raskidanje konekcije!");
        } catch (SQLException ex) {
            System.out.println("Raskidanje konekcije sa bazom nije uspelo!");
        }
    }

    public void potvrdiTransakciju() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("Commit nije uspeo!");
        }
    }

    public void ponistiTransakciju() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.out.println("Rollback nije uspeo!");
        }
    }

    public void sacuvajMotorneSanke(MotorneSanke ms) throws SQLException {
        String upit = "INSERT INTO motorne_sanke (MotorneSankeID, BrojSasije, BrojMestaZaSedenje, TipSankiID) VALUES (?,?,?,?)";
        System.out.println(upit);
        PreparedStatement preparedStatement = connection.prepareStatement(upit);
        preparedStatement.setString(1, ms.getMotorneSankeID());
        preparedStatement.setString(2, ms.getBrojSasije());
        preparedStatement.setString(3, ms.getBrojMestaZaSedenje());
        preparedStatement.setString(4, ms.getTipSanki().getTipSankiID());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<AbstractObjekat> vratiSveObjekte(AbstractObjekat o) throws SQLException {

        try {
            String upit = "SELECT * FROM " + o.vratiImeTabele();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspesno izvrsen SELECT");
            return listaObjekata;

        } catch (SQLException ex) {
            System.out.println("Greska u SELECT upitu");
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public AbstractObjekat vratiObjekatPoKljucu(AbstractObjekat o, String ID) {
        String upit = "SELECT * FROM " + o.vratiImeTabele() + " WHERE " + o.vratiPK() + "=" + ID;
        try (Statement s = connection.createStatement();) {
            ResultSet rs = s.executeQuery(upit);
            List<AbstractObjekat> listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspesno izvrsen mini SELECT");
            return listaObjekata.get(0);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void obrisiObjekat(AbstractObjekat o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public AbstractObjekat sacuvajObjekat(AbstractObjekat o){
        try {
            String upit = String.format("INSERT INTO %s VALUES (%s)", o.vratiImeTabele(), o.vratiParametre());
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
            return o;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

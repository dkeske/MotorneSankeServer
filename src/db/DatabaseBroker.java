/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.AbstractObjekat;
import domen.MotorneSanke;
import domen.TipSanki;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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

    public List<MotorneSanke> UcitajListuMotornihSanki() throws SQLException {
        String upit = "SELECT MotorneSankeID, BrojSasije, BrojMestaZaSedenje FROM motorne_sanke";
        List<MotorneSanke> listaMotorneSanke = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            MotorneSanke motorneSanke = new MotorneSanke();
            motorneSanke.setMotorneSankeID(rs.getString("MotorneSankeID"));
            motorneSanke.setBrojSasije(rs.getString("BrojSasije"));
            motorneSanke.setBrojMestaZaSedenje(rs.getString("BrojMestaZaSedenje"));
            listaMotorneSanke.add(motorneSanke);

        }
        rs.close();
        statement.close();
        return listaMotorneSanke;
    }

    public List<TipSanki> UcitajListuTipovaMotornihSanki() throws SQLException {
        String upit = "SELECT TipSankiID, NazivTipa, Namena, DuzinaKrampona FROM tip_motornih_sanki";
        System.out.println(upit);
        List<TipSanki> listaTipovaMS = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while (rs.next()) {
            TipSanki tipMS = new TipSanki();
            tipMS.setTipSankiID(rs.getString("TipSankiID"));
            tipMS.setNazivTipa(rs.getString("NazivTipa"));
            tipMS.setNamena("Namena");
            tipMS.setDuzinaKrampona(rs.getDouble("DuzinaKrampona"));
            listaTipovaMS.add(tipMS);
        }
        rs.close();
        statement.close();
        return listaTipovaMS;
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
            List<AbstractObjekat> listaObjekata = new ArrayList<>();
            String upit = "SELECT * FROM " + o.vratiImeTabele();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            listaObjekata = o.RSuTabelu(rs);
            s.close();
            System.out.println("Uspesno izvrsen SELECT");
            return listaObjekata;

        } catch (SQLException ex) {
            System.out.println("Greska u SELECT upitu");
            Logger
                    .getLogger(DatabaseBroker.class
                            .getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import domen.MotorneSanke;
import domen.TipSanki;
import domen.Vozac;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel
 */
public class DatabaseBroker {
    private Connection connection;
    
    public DatabaseBroker(){
    }
    
    public void uspostaviKonekciju() throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/motorne_sanke";
        String user="root";
        String password="";
        connection=DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);
        System.out.println("Uspesno uspostavljanje konekcije!");
    }
    
    public void raskiniKonekciju() throws SQLException{
        connection.close();
        System.out.println("Uspesno raskidanje konekcije!");
    }
    public void potvrdiTransakciju() throws SQLException{
        connection.commit();
    }
    
    public void ponistiTransakciju() throws SQLException{
        connection.rollback();
    }
    public List<MotorneSanke> UcitajListuMotornihSanki() throws SQLException{
        String upit = "SELECT MotorneSankeID, BrojSasije, BrojMestaZaSedenje FROM motorne_sanke";
        List<MotorneSanke> listaMotorneSanke = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        while(rs.next()){
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
        while(rs.next()){
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
        PreparedStatement preparedStatement=connection.prepareStatement(upit);
        preparedStatement.setString(1, ms.getMotorneSankeID());
        preparedStatement.setString(2, ms.getBrojSasije());
        preparedStatement.setString(3, ms.getBrojMestaZaSedenje());
        preparedStatement.setString(4, ms.getTipSanki().getTipSankiID());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
}

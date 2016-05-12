/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import domen.AbstractObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Daniel
 */
public class ClientThread extends Thread {

    Socket socket;
    List<ClientThread> klijenti;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientThread(Socket socket, List<ClientThread> klijenti) {
        this.klijenti = klijenti;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Klijent thread!");
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Cekam objekat!");
                KlijentTransfer kt = (KlijentTransfer) in.readObject();
                System.out.println("Stigao objekat!");
                int operacija = kt.getOperacija();
                ServerTransfer st = new ServerTransfer();
                if (operacija == konstante.Konstante.UCITAJ_LISTU_MOTORNIH_SANKI) {
                    List<AbstractObjekat> listaSanki = Kontroler.vratiKontrolera().vratiListuMotornihSanki();
                    st.setUspesnost(1);
                    st.setPodaci(listaSanki);
                }
                out.writeObject(st);
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

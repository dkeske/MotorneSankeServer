/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import domen.AbstractObjekat;
import domen.Korisnik;
import exception.ServerskiException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;
import kontroler.Kontroler;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Daniel
 */
public class ClientThread extends Thread {

    private Socket socket;
    private List<ClientThread> klijenti;
    ObjectInputStream in;
    ObjectOutputStream out;

    public ClientThread(Socket socket, List<ClientThread> klijenti) {
        this.klijenti = klijenti;
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Klijent thread pokrenut!");
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Cekam objekat!");
                KlijentTransfer kt = (KlijentTransfer) in.readObject();
                System.out.println("Stigao objekat!");
                ServerTransfer st = new ServerTransfer();
                try {
                    int operacija = kt.getOperacija();
                    if (operacija == Konstante.UCITAJ_LISTU_MOTORNIH_SANKI) {
                        List<AbstractObjekat> listaSanki = Kontroler.vratiKontrolera().vratiListuMotornihSanki();
                        st.setUspesnost(1);
                        st.setPodaci(listaSanki);
                    } else if (operacija == Konstante.ULOGUJ_KORISNIKA) {
                        AbstractObjekat korisnik = Kontroler.vratiKontrolera().ulogujKorisnika((Korisnik) kt.getParametar());
                        st.setUspesnost(1);
                        st.setPodaci(korisnik);
                    } else if(operacija == Konstante.UCITAJ_LISTU_REZERVACIJA){
                        List<AbstractObjekat> listaRezervacija = Kontroler.vratiKontrolera().vratiListuRezervacija();
                        st.setUspesnost(1);
                        st.setPodaci(listaRezervacija);
                    }
                } catch (ServerskiException ex) {
                    st.setUspesnost(-1);
                    st.setException(ex);
                }
                out.writeObject(st);
            }
        } catch (SocketException se) {
            try {
                System.out.println("Klijent odlazi...");
                in.close();
                out.close();
                socket.close();
                klijenti.remove(this);
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public List<ClientThread> getKlijenti() {
        return klijenti;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import domen.AbstractObjekat;
import domen.Korisnik;
import domen.MotorneSanke;
import domen.RezervacijaVoznje;
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
    AbstractObjekat korisnik;

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
                KlijentTransfer kt = (KlijentTransfer) in.readUnshared();
                System.out.println("Stigao objekat!");
                ServerTransfer st = new ServerTransfer();
                try {
                    int operacija = kt.getOperacija();
                    switch (operacija) {
                        case Konstante.UCITAJ_LISTU_MOTORNIH_SANKI:
                            List<AbstractObjekat> listaSanki = Kontroler.vratiKontrolera().vratiListuMotornihSanki();
                            st.setPodaci(listaSanki);
                            break;
                        case Konstante.ULOGUJ_KORISNIKA:
                            korisnik = Kontroler.vratiKontrolera().ulogujKorisnika((Korisnik) kt.getParametar());
                            st.setPodaci(korisnik);
                            break;
                        case Konstante.UCITAJ_LISTU_REZERVACIJA:
                            List<AbstractObjekat> listaRezervacija = Kontroler.vratiKontrolera().vratiListuRezervacija();
                            st.setPodaci(listaRezervacija);
                            break;
                        case Konstante.UCITAJ_LISTU_TIPOVA_MS:
                            List<AbstractObjekat> listaTipova = Kontroler.vratiKontrolera().vratiListuTipovaMS();
                            st.setPodaci(listaTipova);
                            break;
                        case Konstante.KREIRAJ_MOTORNE_SANKE:
                            AbstractObjekat sanke = Kontroler.vratiKontrolera().kreirajMotorneSanke((MotorneSanke) kt.getParametar());
                            st.setPodaci(sanke);
                            break;
                        case Konstante.PRETRAZI_MOTORNE_SANKE:
                            List<AbstractObjekat> listaFilterSanki = Kontroler.vratiKontrolera().pretraziSanke((String) kt.getParametar());
                            st.setPodaci(listaFilterSanki);
                            break;
                        case Konstante.OBRISI_MOTORNE_SANKE:
                            List<AbstractObjekat> listaPosleBris = Kontroler.vratiKontrolera().obrisiSanke((MotorneSanke) kt.getParametar());
                            st.setPodaci(listaPosleBris);
                            break;
                        case Konstante.KREIRAJ_REZERVACIJU_VOZNJE:
                            AbstractObjekat rezv = Kontroler.vratiKontrolera().kreirajRezervacijuVoznje((RezervacijaVoznje) kt.getParametar());
                            st.setPodaci(rezv);
                            break;
                        case Konstante.ZAPAMTI_REZERVACIJU_VOZNJE:
                            AbstractObjekat rezerv = Kontroler.vratiKontrolera().zapamtiRezervacijuVoznje((RezervacijaVoznje) kt.getParametar());
                            st.setPodaci(rezerv);
                            break;
                        case Konstante.PRETRAZI_REZERVACIJU_VOZNJE:
                            List<AbstractObjekat> listaFilterRezervacija = Kontroler.vratiKontrolera().pretraziRezervacije((String) kt.getParametar());
                            st.setPodaci(listaFilterRezervacija);
                            break;
                        case Konstante.OBRISI_REZERVACIJU_VOZNJE:
                            List<AbstractObjekat> listaBris = Kontroler.vratiKontrolera().obrisiRezervaciju((RezervacijaVoznje) kt.getParametar());
                            st.setPodaci(listaBris);
                            break;
                        case Konstante.UCITAJ_LISTU_VOZACA:
                            List<AbstractObjekat> listaVozaca = Kontroler.vratiKontrolera().ucitajListuVozaca();
                            st.setPodaci(listaVozaca);
                            break;
                        case Konstante.PRETRAZI_VOZACE:
                            List<AbstractObjekat> listaFilter = Kontroler.vratiKontrolera().pretraziVozace((String) kt.getParametar());
                            st.setPodaci(listaFilter);
                            break;
                        default:
                            kt.getParametar();
                            break;
                    }
                    st.setUspesnost(1);

                } catch (ServerskiException ex) {
                    st.setUspesnost(-1);
                    st.setException(ex);
                }
                out.writeUnshared(st);
            }
        } catch (SocketException se) {
            try {
                System.out.println("Klijent odlazi...");
                Kontroler.vratiKontrolera().izlogujKorisnika(korisnik);
                in.close();
                out.close();
                socket.close();
                klijenti.remove(this);
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ServerskiException ex) {
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

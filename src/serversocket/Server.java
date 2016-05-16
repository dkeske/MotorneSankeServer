/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    int brojPorta;
    static List<ClientThread> klijenti = new ArrayList<>();

    public Server(int brojPorta) {
        try {
            this.brojPorta = brojPorta;
            serverSocket = new ServerSocket(brojPorta);
            System.out.println("Kreiran server socket!");
        } catch (Exception e) {
            System.out.println("Server socket nije kreiran!");
        }
    }

    @Override
    public void run() {
        try {
            System.out.println("Cekam klijente...");
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                ClientThread ct = new ClientThread(socket, klijenti);
                ct.start();
                klijenti.add(ct);
                System.out.println("Novi klijent se povezao!");
            }
        } catch (SocketException se) {
            System.out.println("Server se gasi...");
        } catch (IOException ex) {
            System.out.println("Klijent ne moze da se poveze!");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void zaustaviNiti() {
        for (ClientThread clientThread : klijenti) {
            try {
                clientThread.getSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

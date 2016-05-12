/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serversocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daniel
 */
public class Server extends Thread{

    ServerSocket serverSocket;
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
            Socket socket = serverSocket.accept();
            
            ClientThread ct = new ClientThread(socket, klijenti);
            ct.start();
            klijenti.add(ct);
            System.out.println("Novi klijent se povezao!");
        } catch (IOException ex) {
            System.out.println("Klijent ne moze da se poveze!");
        }
    }
    
    

}

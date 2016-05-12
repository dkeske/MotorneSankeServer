/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import serversocket.Server;

/**
 *
 * @author Daniel
 */
public class Test {
    public static void main(String[] args) {
        Server s1 = new Server(9000);
        s1.start();
        
    }
}

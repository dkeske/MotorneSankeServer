/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.AbstractObjekat;
import java.util.List;
import so.SOUcitajListuMotornihSanki;

/**
 *
 * @author Daniel
 */
public class Kontroler {
    private static Kontroler instance;
    
    public static Kontroler vratiKontrolera(){
        if(instance == null){
            instance = new Kontroler();
        }
        return instance;
    }

    public List<AbstractObjekat> vratiListuMotornihSanki() {
        SOUcitajListuMotornihSanki sou = new SOUcitajListuMotornihSanki();
        sou.izvrsiOperaciju();
        return sou.getListaSanki();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derive;

import Client.ClientAuthentication;
import Server.ServerAuthentication;

/**
 *
 * @author Ashu
 */
public class main {

    public static void main(String args[]) {
        //step 1
        Client.ClientAuthentication cA = new ClientAuthentication("Hello world1");
        String token = cA.getToken();
        System.out.println("token generated == " + token);
        
        //Step 2
        Server.ServerAuthentication sa = new ServerAuthentication("Hello world1");
        String License = sa.generateLicense(token);
        System.out.println("Licence key Generated == " + License);
        
        //Step 3
        cA.validate(License);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Derive;

import Client.ClientAuthentication;
import Server.ServerAuthentication;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashu
 */
public class main {

    public static void main(String args[]) {

        String url = "http://localhost:8080/onlineauthenticate.jsp";
        System.out.println("\n\n----------------------------------\n\n");

        //step 1
        System.out.println("Step 1 :");
        Client.ClientAuthentication cA = new ClientAuthentication("Hello world1");
        String token = cA.getToken();
        System.out.println("token generated == " + token);

        //Step 2
        System.out.println("\nStep 2 :");
        ServerAuthentication sa = new ServerAuthentication("Hello world1");
        String License = sa.generateLicense(token);
        System.out.println("Licence key Generated == " + License);

        //Step 3
        System.out.println("\nStep 3 :");
        if(cA.validate(License)){
            System.out.println("*******Validation Successfull******");
        }else{
            System.out.println("xxxxxx*Validation Not Successfull*xxxxxx");
        }

        //online activation
        System.out.println("\n---------Online Activation------\n");
        String onlineLicense;
        try {

            onlineLicense = cA.getLicenseOnline(url);
            System.out.println("Online Response code = "+cA.getResponseCode());
            System.out.println("Online License  = "+onlineLicense);
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}

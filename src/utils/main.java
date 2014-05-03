/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Client.ClientAuthentication;
import Server.ServerAuthentication;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ashu
 */


public class main {

    public static void main(String args[]) {

        main mob = new main();
       // String url = "http://localhost:8080/onlineauthenticate.jsp";
        String url = "http://localhost:8080/authenticate.html";
        System.out.println("\n\n----------------------------------\n\n");

        //step 1
        System.out.println("Step 1 :");
        Client.ClientAuthentication cA = new ClientAuthentication("YourPassPhraseHere");
        String token = cA.getToken(null);
        System.out.println("token generated == " + token);

        //Step 2
        System.out.println("\nStep 2 :");
        ServerAuthentication sa = new ServerAuthentication("YourPassPhraseHere");
        String License = sa.generateLicense(null,token);
        System.out.println("Licence key Generated == " + License);

        //Step 3
        System.out.println("\nStep 3 :");
        if(cA.validate(null,token,License)){
            System.out.println("*******Validation Successfull******");
        }else{
            System.out.println("xxxxxx*Validation Not Successfull*xxxxxx");
        }

        //online activation
        System.out.println("\n---------Online Activation------\n");
        System.out.println("\nOnline Step 1");
        String onlineLicense = null;
        try {
            

            boolean responseRecieved = cA.getLicenseOnline(token,url);
            System.out.println("Online Response code = "+cA.getResponseCode());
            if(responseRecieved){
                System.out.println("RESPONSE RECIEVED");
            }else{
                System.out.println("RESPONSE NOT RECIEVED");
            }
            System.out.println("Please enter the license Key here");
            Scanner scan = new Scanner(System.in);
           onlineLicense = scan.next();
            
            System.out.println("Online License  = "+onlineLicense);
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\nOnline Step 2");
        if(cA.validate(null,token,onlineLicense)){
            System.out.println("*******Online Validation Successfull********");
        }else{
            System.out.println("xxxxxxx**Online Validation failed** xxxxxxx");
        }
    }
}

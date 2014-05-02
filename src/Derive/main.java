/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Derive;

import Client.ClientAuthentication;
import static Derive.md5.md5;
import Server.ServerAuthentication;

/**
 *
 * @author Ashu
 */
public class main {
 
    
        public static void main(String args[] ) {
           Client.ClientAuthentication cA=new ClientAuthentication("Hello world");
            System.out.println(cA.getToken());
            Server.ServerAuthentication sa=new ServerAuthentication("Hello world");
            sa.generateLicense(cA.getToken());
            
            
         }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Server;

import Derive.md5;


/**
 *
 * @author Ashu
 */
public class ServerAuthentication {
    String passPhrase;
    public ServerAuthentication(String passPhrase) {
        this.passPhrase=passPhrase;
    }
    
    public String generateLicense(String token){
        String License="";
        License=md5.md5(token+passPhrase);
        return License;
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Derive.md5;
import javax.xml.ws.Response;

/**
 *
 * @author Ashu
 */
public class ServerAuthentication {

    String passPhrase = "";

    public ServerAuthentication(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    public String generateLicense(String token) {
        String License = "";
        String temp = md5.hash(token + passPhrase).toUpperCase();
        
        final String[] segments = temp.split(String.format("(?s)(?<=\\G.{%d})", 4));
        for (int i = 0; i < segments.length - 1; i++) {
            License += segments[i] + "-";
        }
        License+= segments[segments.length - 1];

        return License;

    }
    


}

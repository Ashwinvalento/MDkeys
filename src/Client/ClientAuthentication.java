/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Derive.Keys;
import Derive.md5;

/**
 *
 * @author Ashu
 */
public class ClientAuthentication {

    String token = "";
    String passPhrase = "";

    public ClientAuthentication(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    public String getToken() {
        Keys keys = new Keys();
        String MBID = keys.getMotherBoardSerialNumber();
        String MACID = keys.getMacId();
        String HDDID = keys.getSerialNumber();

        String hashedValue = md5.hash(MBID + MACID + HDDID + passPhrase).toUpperCase();
        System.out.println("token generated 1 "+hashedValue);
        final String[] segments = hashedValue.split(String.format("(?s)(?<=\\G.{%d})", 4));
        for (int i = 0; i < segments.length - 1; i++) {
            token += segments[i] + "-";
        }
        token += segments[segments.length - 1];

        return token;
    }

    public boolean validate(String License) {
        String validateLicense="";
        
        String temp = md5.hash(token + passPhrase).toUpperCase();
        
        final String[] segments = temp.split(String.format("(?s)(?<=\\G.{%d})", 4));
        for (int i = 0; i < segments.length - 1; i++) {
            validateLicense += segments[i] + "-";
        }
        validateLicense+= segments[segments.length - 1];
        System.out.println("validation license == "+validateLicense);
        
        if(License.equals(validateLicense)){
            System.out.println("Validation Successfull");
            return true;
        }
        return false;
        
    }
}

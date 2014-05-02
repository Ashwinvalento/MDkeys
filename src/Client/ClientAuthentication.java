/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import Derive.Keys;
import static Derive.md5.md5;

/**
 *
 * @author Ashu
 */
public class ClientAuthentication {
    String token="";
    String passPhrase = "";
    
     public ClientAuthentication(String passPhrase ) {
            this.passPhrase=passPhrase;
     }
     
     public String getToken(){
          Keys keys=new Keys();
             String MBID=keys.getMotherBoardSerialNumber();
             String MACID=keys.getMacId();
             String HDDID=keys.getSerialNumber();
             
             String hashedValue=md5(MBID+MACID+HDDID+passPhrase).toUpperCase();
             final String[] segments = hashedValue.split(String.format("(?s)(?<=\\G.{%d})", 4));
             for(int i=0;i<segments.length-1;i++){
                 token+=segments[i]+"-";
             }
              token+=segments[segments.length-1];

              return token;
     }
     
     public void validate(String token, String License) {
         
     }
}

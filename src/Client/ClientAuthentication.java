/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import Derive.Keys;
import Derive.md5;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Ashu
 */
public class ClientAuthentication {

    String passPhrase = "";
    int responseCode = 0;

    public ClientAuthentication(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    public String getToken() {
        String token = "";
        Keys keys = new Keys();
        String MBID = keys.getMotherBoardSerialNumber();
        String MACID = keys.getMacId();
        String HDDID = keys.getSerialNumber();

        String hashedValue = md5.hash(MBID + MACID + HDDID + passPhrase).toUpperCase();
        final String[] segments = hashedValue.split(String.format("(?s)(?<=\\G.{%d})", 4));
        for (int i = 0; i < segments.length - 1; i++) {
            token += segments[i] + "-";
        }
        token += segments[segments.length - 1];

        return token;
    }

    public boolean validate(String License) {
        String token = getToken();
        String validateLicense = "";

        String temp = md5.hash(token + passPhrase).toUpperCase();

        final String[] segments = temp.split(String.format("(?s)(?<=\\G.{%d})", 4));
        for (int i = 0; i < segments.length - 1; i++) {
            validateLicense += segments[i] + "-";
        }
        validateLicense += segments[segments.length - 1];
        System.out.println("validation license == " + validateLicense);

        if (License.equals(validateLicense)) {
            return true;
        }
        return false;
    }


    public String getLicenseOnline(String URL) throws Exception {

        String onlineToken = getToken();

        System.out.println("online token :" + onlineToken);
        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "token="+onlineToken;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();
        
        responseCode = con.getResponseCode();
        
        String License = con.getHeaderField("License");

        return License;

    }
    
    public int getResponseCode(){
        return responseCode;
    }

}

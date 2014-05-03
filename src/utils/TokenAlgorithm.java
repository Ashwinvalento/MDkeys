/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Ashu
 */
public class TokenAlgorithm {

    /**
     * The algorithm function takes in an input and generates a unique token
     * for that input.
     * <p>
     * Note This function can be overridden to provide a custom implementation
     * of the algorithm.
     *
     * @param input A input of type String for which a token has to be
     * generated.
     * @return returns the Unique token generated using this algorithm.
     */
    public String algorithm(String input) {

        String md5 = null;

        if (null == input) {
            return null;
        }

        try {

            //Create MessageDigest object for MD5
            MessageDigest digest = MessageDigest.getInstance("MD5");

            //Update input string in message digest
            digest.update(input.getBytes(), 0, input.length());

            //Converts message digest value in base 16 (hex) 
            md5 = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }
        String token = "";
        final String[] segments = md5.split(String.format("(?s)(?<=\\G.{%d})", 4));
        for (int i = 0; i < segments.length - 1; i++) {
            token += segments[i] + "-";
        }
        token += segments[segments.length - 1];
        return token;
    }

}

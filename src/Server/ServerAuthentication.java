/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import utils.LicenseAlgorithm;

/**
 *
 * @author Ashu
 */
public class ServerAuthentication {

    String passPhrase = "";

    /**
     * This Class provides various methods to be used in the Server side.
     * Argument passPhrase is a symmetric key which is passed once to the class
     * and uses this passPhrase as a password to generate unique token to the
     * Server.
     * <p>
     * Note that the same passPhrase has to be used in the Client Side also
     *
     * @param passPhrase a password required to generate unique application
     * dependent keys
     */
    public ServerAuthentication(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    /**
     * the generateLicense function is a server side function which returns a
     * unique license key which is generated by using the algorithm and The passPhrase.
     * <p>
     * NOTE: the parameter licenseAlgorithm is a object of LicenseAlgorithm
     * class which contains the function algorithm that can be overridden to
     * implement custom license validation algorithm. Also if a custom algorithm
     * is used, it should match the algorithm used in the server . If null is
     * sent as the parameter then the default algorithm will be used.
     *
     * @param licenseAlgorithm Object of a class which extends the
     * LicenseAlgorithm class ,null for default Algorithm
     * @param token The token generated by the getToken() method.

     * @return the Unique license key getnerated using the algorithm function and the passPhrase
     */
    public String generateLicense(LicenseAlgorithm licenseAlgorithm, String token) {
        String License = "";

        LicenseAlgorithm defaultAlgorithm = new LicenseAlgorithm();
        if (licenseAlgorithm == null) {
            licenseAlgorithm = defaultAlgorithm;
        }

        License = licenseAlgorithm.algorithm(token + passPhrase).toUpperCase();

        return License;

    }

}

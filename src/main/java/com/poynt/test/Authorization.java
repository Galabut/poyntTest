package com.poynt.test;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Authorization {

    public static String generateToken() throws NoSuchAlgorithmException {

        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(1024);

        KeyPair keyPair = keyGenerator.genKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        JWSSigner signer = new RSASSASigner(privateKey);

        // Audience for the cleaim set
        final List<String> aud = new ArrayList<String>();
        aud.add("https://services.poynt.net");

        // Prepare JWT with claims set
        JWTClaimsSet claimsSet = new JWTClaimsSet();
        claimsSet.setAudience(aud);
        claimsSet.setSubject("urn:tid:acb2a349-4688-32d9-9607-b0532212525b");
        claimsSet.setIssuer("urn:tid:acb2a349-4688-32d9-9607-b0532212525b");
        claimsSet.setIssueTime(Calendar.getInstance().getTime());
        claimsSet.setExpirationTime(new Date(new Date().getTime() + 60 * 1000));
        claimsSet.setJWTID(UUID.randomUUID().toString());
//        if (appSettings != null) {
//            if (appSettings.getBusinessId() != null) {
//                claimsSet.setCustomClaim(Claims.POYNT_BIZ, appSettings.getBusinessId().toString());
//            }
//            if (appSettings.getStoreId() != null) {
//                claimsSet.setCustomClaim(Claims.POYNT_STORE, appSettings.getStoreId().toString());
//            }
//            if (appSettings.getStoreDeviceId() != null) {
//                claimsSet.setCustomClaim(Claims.POYNT_DEVICE_ID, deviceMetaData.getStoreDeviceId());
//            }
//        }
//        if (Strings.notEmpty(issuedTo)) {
//            claimsSet.setCustomClaim(Claims.POYNT_ISSUED_TO, issuedTo);
//        }

        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

// Compute the RSA Signature
        try {
            signedJWT.sign(signer);
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        String serialize = signedJWT.serialize();
        System.out.println(serialize);
        return serialize;

    }
}

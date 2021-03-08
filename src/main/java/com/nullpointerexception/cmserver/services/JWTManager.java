package com.nullpointerexception.cmserver.services;

import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTManager {
	private final static Algorithm algorithmHS = Algorithm.HMAC512("H!CU/:0;gnTrnElp(fa5DwVELYY20Xgredr434Cdfg_435+");
	
	public String createToken(String email){
        try {
            String token = JWT.create()
                    .withClaim("email", email)
                    .withIssuer("CollabMode")
                    .sign(algorithmHS);
            return token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            return null;
        }
    }
	
	public boolean verifyAToken(String token){
        try {
            JWTVerifier verifier = JWT.require(algorithmHS)
                    .withIssuer("CollabMode")
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e){
            e.getMessage();
            return false;
        }
    }
	
	public String decodeAToken(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            Map<String, Claim> claims = jwt.getClaims();
            Claim claim = claims.get("email");
            return claim.asString();
        } catch (JWTDecodeException e){
           e.getMessage();
           return null;
        }
    }
}

package it.objectmethod.ecommerce.spring.services;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import it.objectmethod.ecommerce.spring.models.UserObject;
import it.objectmethod.ecommerce.spring.utils.Constants;

@Service
public class JwtService {

	public String createJWTToken(UserObject user) {

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DATE, 1);

		Algorithm alg = Algorithm.HMAC256(Constants.MY_SECRET_JWT_KEY);
		String token = JWT.create().withClaim(Constants.AUTH_TOKEN_ID, user.getId())
				.withClaim(Constants.AUTH_TOKEN_USERNAME, user.getUsername()).withExpiresAt(cal.getTime()).sign(alg);

		return token;
	}

	public boolean checkJWTToken(String jwtToken) {
		boolean valid = false;
		Algorithm alg = Algorithm.HMAC256(Constants.MY_SECRET_JWT_KEY);
		try {
			JWTVerifier ver = JWT.require(alg).build();
			DecodedJWT decoded = ver.verify(jwtToken);

			Long userId = decoded.getClaim(Constants.G5_AUTH_TOKEN_ID).asLong();
			String username = decoded.getClaim(Constants.G5_AUTH_TOKEN_USERNAME).asString();

			valid = true;
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("Token scaduto!");
		}

		return valid;
	}
}

package server.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class SecurityService {

	private static String secret = "secretCode";

//	public SecurityService(String secret) {
//		this.secret = secret;
//	}
	
	public Long user(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT jwt = verifier.verify(token);
			return jwt.getClaim("user").asLong();
		} catch (JWTVerificationException e) {
			throw new InvalidTokenException(e);
		}
	}
	
	public String generateTokenFor(Long idEstudiante) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withClaim("user", idEstudiante).sign(algorithm);
	}

}

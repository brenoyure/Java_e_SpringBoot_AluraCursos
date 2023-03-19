package med.voll.api.infra.security;

import static com.auth0.jwt.JWT.create;
import static com.auth0.jwt.algorithms.Algorithm.HMAC256;
import static java.time.LocalDateTime.now;

import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

	@Value("${api.voll.med.infra.security.secret}")
	private String secret;
	
	public String gerarToken(UserDetails usuario) {
		return create()
				.withSubject(usuario.getUsername())
				.withExpiresAt(getDataEHoraDeExpiracao())
				.withIssuer("API Voll Med")
				.sign(HMAC256(secret));
			
	}

	private Instant getDataEHoraDeExpiracao() {
		return now()
				.plusHours(2)
					.toInstant(ZoneOffset.of("-03:00"));
	}
	
}

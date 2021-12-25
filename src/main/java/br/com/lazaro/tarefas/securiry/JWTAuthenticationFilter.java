package br.com.lazaro.tarefas.securiry;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.lazaro.tarefas.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	public static final long EXPIRATION_TIME = 600000;

	public static final String SECRET = "8fc10bbf-b69a-4907-a1a5-52ff25ffd0a1";

	public static final String TOKEN_PREFIX = "Bearer";

	public static final String HEADER_STRING = "Authorization";
	
	private final AuthenticationManager authenticationManager;
	
	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request,
			HttpServletResponse response)
			throws AuthenticationException {
		
		try {
			
			Usuario usuario = new ObjectMapper()
					.readValue(request.getInputStream(), Usuario.class);
			
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(),
							usuario.getAuthorities()));
			
		} catch (Exception e) {
			throw new RuntimeException("Falha ao autenticar usuário", e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User user = (User) authResult.getPrincipal();
		
		String token = Jwts.builder()
				.setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		response.getWriter().write(token);
		response.getWriter().flush();
		
	}

}

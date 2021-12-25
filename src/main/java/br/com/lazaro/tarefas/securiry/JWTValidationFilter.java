package br.com.lazaro.tarefas.securiry;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import br.com.lazaro.tarefas.ApplicationContextLoad;
import br.com.lazaro.tarefas.model.Usuario;
import br.com.lazaro.tarefas.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;

public class JWTValidationFilter extends BasicAuthenticationFilter {
	
	public JWTValidationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		
		String attribute = request.getHeader(JWTAuthenticationFilter.HEADER_STRING);
		
		if (attribute == null || !attribute.startsWith(JWTAuthenticationFilter.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		String token = attribute.replace(JWTAuthenticationFilter.TOKEN_PREFIX, "").trim();
		
		UsernamePasswordAuthenticationToken authenticationToken 
			= getAuthenticationToken(token);
		
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {
		
		if (token != null) {
			
			String username = Jwts.parser().setSigningKey(JWTAuthenticationFilter.SECRET)
					.parseClaimsJws(token.replace(JWTAuthenticationFilter.TOKEN_PREFIX, ""))
					.getBody().getSubject();
			
			if (username != null) {
				
				Usuario usuario = ApplicationContextLoad
						.getApplicationContext()
						.getBean(UsuarioRepository.class)
						.findUserByLogin(username);
				
				if (usuario != null) {
					return new UsernamePasswordAuthenticationToken(
							usuario.getLogin(),
							usuario.getSenha(),
							usuario.getAuthorities()
					);
				}
				
			}
			
		}
		
		return null;
		
	}

}

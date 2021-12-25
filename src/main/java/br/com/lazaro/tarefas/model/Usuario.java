package br.com.lazaro.tarefas.model;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	private String nome;
	
	@Column(unique = true)
	private String login;
	
	private String senha;
	
	@Column(name = "data_criacao")
	private Calendar dataCriacao;
	
	private boolean ativo;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "usuarios_role",
			uniqueConstraints = @UniqueConstraint(name="unique_role_user", columnNames = {"usuario_id", "role_id"}),
			joinColumns = @JoinColumn(
					name="usuario_id",
					referencedColumnName = "id",
					table = "usuario",
					unique = false,
					foreignKey = @ForeignKey(name="fk_usuario_id", value = ConstraintMode.CONSTRAINT)
			),
			inverseJoinColumns = @JoinColumn(
					name="role_id",
					referencedColumnName = "id",
					table = "role",
					unique = false,
					updatable = false,
					foreignKey = @ForeignKey(name="fk_role_id", value = ConstraintMode.CONSTRAINT)
			)
	)
	private List<Role> roles;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

}

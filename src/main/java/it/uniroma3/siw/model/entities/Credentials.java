package it.uniroma3.siw.model.entities;

import it.uniroma3.siw.model.enums.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Credentials {

	//public static final String DEFAULT_ROLE = UserRole.REGISTERED.toString();
	//public static final String ADMIN_ROLE = UserRole.ADMIN.toString();
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String username;
	private String password;
	private String role;

	@OneToOne
	(cascade = CascadeType.ALL)
	private User utente;
	
	public String getUsername() {
		return username;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUtente() {
		return utente;
	}

	public void setUtente(User utente) {
		this.utente = utente;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

}
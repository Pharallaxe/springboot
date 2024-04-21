package com.example.sorcier.config;

// Utilisé pour créer des collections.
import java.util.ArrayList;
import java.util.List;

// Annotations pour la configuration Spring.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// Utilisé pour configurer les requêtes HTTP dans Spring Security.
import org.springframework.http.HttpMethod;
// Importe les classes pour la configuration de la sécurité web.
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// Importe les classes pour gérer les détails des utilisateurs.
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

// Importe les classes pour le cryptage des mots de passe.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Importe les classes pour la gestion des utilisateurs en mémoire.
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

// Importe les classes pour configurer les filtres de sécurité.
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// Importe une classe de la couche d'accès aux données pour la gestion en mémoire.
import com.example.sorcier.dal.mock.SingletonRepositoryMock;

/**
 * Configuration de sécurité utilisant un mock de base de données pour les environnements de développement.
 */
@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecuriteConfigurationMock {
	public static final String ADMIN = "ADMIN";
	public static final String INSCRIT = "INSCRIT";
	
	// Accès singleton pour simuler la base de données.
    SingletonRepositoryMock singletonRepositoryMock = SingletonRepositoryMock.getInstance();
	
	/**
	 * Configure et fournit un gestionnaire d'utilisateurs en mémoire.
	 * 
	 * @return le gestionnaire d'utilisateurs avec des utilisateurs chargés.
	 */
    @Bean
    public UserDetailsManager userDetailsManager() {
        List<UserDetails> userDetailsList = new ArrayList<>();

        // Convertit chaque utilisateur de la base de données simulée en UserDetails.
        singletonRepositoryMock.getUtilisateurs().forEach(u -> {
            String[] rolesCourant = singletonRepositoryMock.getRoles().stream()
                    .filter(r -> r.getIdUtilisateur() == u.getId())
                    .map(r -> r.getRole())
                    .toArray(String[]::new);

            // Construit un nouvel utilisateur pour Spring Security.
            User.UserBuilder userBuilder = User.withUsername(u.getNom())
                                               .password(u.getMdpHash())
                                               .roles(rolesCourant);
            
            userDetailsList.add(userBuilder.build());
        });

        // Retourne un gestionnaire qui maintient les utilisateurs en mémoire.
        return new InMemoryUserDetailsManager(userDetailsList);
    }
    
	/**
	 * Bean pour encoder les mots de passe.
	 * 
	 * @return Encodeur de mot de passe.
	 */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/**
	 * Configure la chaîne de filtres de sécurité pour les requêtes HTTP.
	 * 
	 * @param http L'objet HttpSecurity à configurer.
	 * @return La chaîne de filtres configurée.
	 * @throws Exception Si une erreur de configuration se produit.
	 */
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// Configuration des autorisations de requête.
				.authorizeHttpRequests(auth -> {
					auth
						// Autoriser seulement les administrateurs à accéder aux chemins sous '/admin/**'.
						.requestMatchers(HttpMethod.GET, "/admin/**").hasRole(ADMIN)
						.requestMatchers(HttpMethod.POST, "/admin/**").hasRole(ADMIN)
						
						// Autoriser les utilisateurs inscrits à accéder au profil.
						.requestMatchers(HttpMethod.GET, "/profil").hasRole(INSCRIT)
						.requestMatchers(HttpMethod.POST, "/profil").hasRole(INSCRIT)
						
						// Toutes les autres requêtes sont autorisées pour tous.
						.anyRequest().permitAll();
				});

		// Configuration du formulaire de login.
		http.formLogin(form -> {
			form.loginPage("/connexion").permitAll()
				.defaultSuccessUrl("/profil", true);
		});

		// Configuration de la déconnexion.
		http.logout(logout -> {
		    logout.invalidateHttpSession(true)
		           .clearAuthentication(true)
		           .deleteCookies("JSESSIONID")
		           .logoutRequestMatcher(new AntPathRequestMatcher("/deconnexion", "GET"))
		           .logoutSuccessUrl("/")
		           .permitAll();
		});

		return http.build();
	}
}

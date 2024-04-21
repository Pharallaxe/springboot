package com.example.sorcier.config;

// Import pour l'interface DataSource.
import javax.sql.DataSource;

// Annotations pour la configuration de Spring.
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// Import pour configurer les autorisations HTTP dans Spring Security.
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

// Import pour les encodeurs de mots de passe.
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Import pour la gestion des utilisateurs via JDBC.
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

// Import pour configurer la chaîne de filtres de sécurité.
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Configuration de sécurité pour une base de données SQL Server.
 */
@Configuration
@EnableWebSecurity
@Profile("sqlserver")
public class SecuriteConfigurationSqlserver {
    public static final String ADMIN = "ADMIN";
    public static final String INSCRIT = "INSCRIT";

    /**
     * Crée et configure un gestionnaire d'utilisateurs JDBC.
     * 
     * @param dataSource La source de données pour la connexion JDBC.
     * @return Un gestionnaire d'utilisateurs JDBC configuré.
     */
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
        // Crée le gestionnaire d'utilisateurs JDBC.
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        
        // Définit la requête pour retrouver un utilisateur par son nom d'utilisateur.
        jdbcUserDetailsManager.setUsersByUsernameQuery("SELECT nom, mdpHash, 1 FROM Utilisateur WHERE nom = ?");
        
        // Définit de la requête pour retrouver les rôles d'un utilisateur.
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
            "SELECT u.nom, r.role FROM Utilisateur AS u " +
            "INNER JOIN Roles AS r ON u.id = r.id_utilisateur " +
            "WHERE u.nom = ?"
        );
        return jdbcUserDetailsManager;
    }
    
    /**
     * Bean pour l'encodage des mots de passe.
     * 
     * @return Un encodeur de mots de passe.
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configure et crée la chaîne de filtres de sécurité pour les requêtes HTTP.
     * 
     * @param http L'objet HttpSecurity à configurer.
     * @return La chaîne de filtres de sécurité configurée.
     * @throws Exception Si une erreur de configuration se produit.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Configure des autorisations de requête.
            .authorizeHttpRequests(auth -> {
                auth
                    // Autorise seulement les administrateurs à accéder aux chemins sous '/admin/**'.
                    .requestMatchers(HttpMethod.GET, "/admin/**").hasRole(ADMIN)
                    .requestMatchers(HttpMethod.POST, "/admin/**").hasRole(ADMIN)
                    
                    // Autorise l'accès au chemin '/profil' seulement pour les utilisateurs authentifiés.
                    .requestMatchers(HttpMethod.GET, "/profil").hasRole(INSCRIT)
                    .requestMatchers(HttpMethod.POST, "/profil").hasRole(INSCRIT)
                    
                    // Autorise toutes les autres requêtes pour tout le monde.
                    .anyRequest().permitAll();
            });

     // Configuration du formulaire de login.
        http.formLogin(form -> {
            form
                // Spécifie l'URL vers laquelle les utilisateurs sont redirigés pour se connecter.
                .loginPage("/connexion")
                
                // Permet à tous les utilisateurs d'accéder à la page de connexion.
                .permitAll()
                
                // Spécifie l'URL de redirection après une authentification réussie.
                .defaultSuccessUrl("/profil", true);
                // Le second paramètre 'true' garantit que l'utilisateur est toujours redirigé vers cette URL,
                // ignoré si la demande précédente était dirigée vers une autre page sécurisée.
        });

        // Configuration de la déconnexion.
        http.logout(logout -> {
            logout
                // Assure que la session est complètement détruite.
                .invalidateHttpSession(true)
                
                // Supprime les détails de l'authentification de la session.
                .clearAuthentication(true)
                
                // Supprime le cookie de session 'JSESSIONID' pour éviter la réutilisation de la session.
                .deleteCookies("JSESSIONID")
                
                // Définit l'URL de déconnexion et la méthode HTTP requise pour initier la déconnexion.
                .logoutRequestMatcher(new AntPathRequestMatcher("/deconnexion", "GET"))
                
                // Redirige l'utilisateur vers la page d'accueil après la déconnexion.
                .logoutSuccessUrl("/")
                
                // Assure que la déconnexion est accessible sans restriction.
                .permitAll();
        });


        return http.build();
    }
}

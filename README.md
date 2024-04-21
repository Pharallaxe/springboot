

Ce document détaille les différentes annotations, fonctions, directives, attributs en Spring Boot et en ThymeLeaf vus au cours d'un module réalisé.

Il ne remplace pas une lecture approfondie de la doc ;) Il essaye juste de faire un tri modeste de ce que j'ai pu observer dans la réalisation de la petite application présente dans ce dépôt. A suivre...

Belle découverte à vous !


## Sommaire


- [Sommaire](#sommaire)
- [A. Spring Boot - annotations de stéréotype](#a-spring-boot---annotations-de-stéréotype)
    - [`@Component`](#component)
    - [`@Repository`](#repository)
    - [`@Service`](#service)
    - [`@Controller`](#controller)
    - [`@RestController`](#restcontroller)
- [B. Spring Boot : annotations de configuration](#b-spring-boot--annotations-de-configuration)
    - [`@Configuration`](#configuration)
    - [`@Bean`](#bean)
    - [`@Profile`](#profile)
    - [`@PropertySource`](#propertysource)
    - [`@Import`](#import)
- [C. Spring Boot : annotations de persistance](#c-spring-boot--annotations-de-persistance)
    - [`@Entity`](#entity)
    - [`@Table`](#table)
    - [`@Id`](#id)
    - [`@GeneratedValue`](#generatedvalue)
    - [`@Column`](#column)
- [D. Spring Boot : annotations de validation](#d-spring-boot--annotations-de-validation)
    - [`@Pattern`](#pattern)
    - [`@Min`](#min)
    - [`@Max`](#max)
    - [`@Valid`](#valid)
    - [`@Size`](#size)
    - [`@NotNull`](#notnull)
    - [`@NotEmpty`](#notempty)
    - [`@Email`](#email)
    - [`@Positive`](#positive)
    - [`@PositiveOrZero`](#positiveorzero)
    - [`@OneToOne`](#onetoone)
    - [`@ManyToMany`](#manytomany)
    - [`@ManyToOne`](#manytoone)
    - [`@OneToMany`](#onetomany)
- [E. Spring Boot : annotations supplémentaires](#e-spring-boot--annotations-supplémentaires)
    - [`@Autowired`](#autowired)
    - [`@Qualifier`](#qualifier)
    - [`@GetMapping`](#getmapping)
    - [`@PostMapping`](#postmapping)
    - [`@ModelAttribute`](#modelattribute)
    - [`@RequestParam`](#requestparam)
    - [`@Test`](#test)
    - [`@TestMethodOrder`](#testmethodorder)
    - [`@Order`](#order)
    - [`@SpringBootTest`](#springboottest)
    - [`@SpringBootApplication`](#springbootapplication)
- [F. Thymeleaf](#f-thymeleaf)
    - [`data-th-href`](#data-th-href)
      - [Sans paramètre.](#sans-paramètre)
      - [Avec paramètre.](#avec-paramètre)
    - [`data-th-each`](#data-th-each)
    - [`th:if`](#thif)
    - [`th:unless`](#thunless)
    - [`th:switch`](#thswitch)
    - [`th:block`](#thblock)
    - [`th-attr`](#th-attr)
    - [`th-object`](#th-object)
    - [`th:src`](#thsrc)
    - [`th:style`](#thstyle)
    - [`th:text`](#thtext)
    - [`th:selected`](#thselected)
    - [`th:with`](#thwith)
    - [`th:action`](#thaction)
    - [`th:replace`](#threplace)
    - [`th:fragment`](#thfragment)
- [G. Spring Boot : build.gradle](#g-spring-boot--buildgradle)
  - [1) Plugins](#1-plugins)
    - [`plugin:java`](#pluginjava)
    - [`org-springframework-boot`](#org-springframework-boot)
    - [`io-spring-dependency-management`](#io-spring-dependency-management)
  - [2) Informations sur le projet](#2-informations-sur-le-projet)
    - [`group`](#group)
    - [`version`](#version)
  - [3) Configurations Java](#3-configurations-java)
    - [`sourceCompatibility`](#sourcecompatibility)
  - [4) Dépôts](#4-dépôts)
    - [`mavenCentral`](#mavencentral)
  - [5) Dépendances](#5-dépendances)
    - [`spring-boot-starter-thymeleaf`](#spring-boot-starter-thymeleaf)
    - [`spring-boot-starter-web`](#spring-boot-starter-web)
    - [`spring-boot-starter-validation`](#spring-boot-starter-validation)
    - [`spring-boot-devtools`](#spring-boot-devtools)
    - [`sqlite-jdbc`](#sqlite-jdbc)
    - [`spring-boot-starter-test`](#spring-boot-starter-test)
    - [`spring-boot-starter-data-jpa`](#spring-boot-starter-data-jpa)
    - [`hibernate-community-dialects`](#hibernate-community-dialects)
  - [6) Configuration des tâches](#6-configuration-des-tâches)
    - [`useJUnitPlatform`](#usejunitplatform)
- [H. Méthodes](#h-méthodes)
  - [1) Méthodes de JdbcTemplate](#1-méthodes-de-jdbctemplate)
    - [`execute`](#execute)
    - [`queryForObject`](#queryforobject)
    - [`queryForList`](#queryforlist)
    - [`update`](#update)
  - [2) Méthodes d'Assertions de JUnit](#2-méthodes-dassertions-de-junit)
    - [`assertEquals`](#assertequals)
    - [`assertFalse`](#assertfalse)
  - [3) java.sql.DriverManager](#3-javasqldrivermanager)
    - [`getConnection`](#getconnection)
  - [4) java.sql.Connection](#4-javasqlconnection)
    - [`prepareStatement`](#preparestatement)
    - [`createStatement`](#createstatement)
  - [5) java.sql.PreparedStatement](#5-javasqlpreparedstatement)
    - [`executeQuery`](#executequery)
    - [`setInt`](#setint)
    - [`setString`](#setstring)
    - [`executeUpdate`](#executeupdate)
  - [6) java.sql.ResultSet](#6-javasqlresultset)
    - [`next`](#next)
    - [`getInt`](#getint)
    - [`getString`](#getstring)
  - [7) java.sql.SQLException](#7-javasqlsqlexception)

---



## A. Spring Boot - annotations de stéréotype

#### `@Component`

**Description** : L'annotation de base qui indique qu'une classe est un composant Spring. Toutes les classes annotées avec `@Component` sont automatiquement détectées et enregistrées comme beans dans le conteneur Spring lors du balayage des classes.

**Exemple d'utilisation** :

```java
import org.springframework.stereotype.Component;

@Component
public class MonComposant {
    // implémentations et méthodes.
}
```

#### `@Repository`

**Description** : Cette annotation permet de marquer une classe comme un repository et est utilisé dans la dal. Spring configure également une gestion des exceptions de BDD adaptée autour de ces classes annotées ainsi.

**Exemple d'utilisation** :

```java
import org.springframework.stereotype.Repository;

@Repository
public class SorcierRepository {
    // méthodes d'accès aux données dans la
    // base sorcier.
}
```

#### `@Service`
**Description** : Utilisé pour indiquer qu'une classe définit un service dans la couche bll, autrement dit la couche de logique métier.

**Exemple d'utilisation** :

```java
import org.springframework.stereotype.Service;

@Service
public class SorcierService {
    // logique métier pour gérer les
    //utilisateurs
}
```

#### `@Controller`

**Description** : Marque une classe comme un contrôleur dans le modèle MVC de Spring, utilisée pour définir un contrôleur qui gère les requêtes HTTP.

**Exemple d'utilisation** :

```java
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation
    .GetMapping;

@Controller
public class SorcierController {
    @GetMapping("/sorciers")
    public List<Sorcier> getSorciers() {
        // code pour afficher la liste des
        // utilisateurs
    }
}
```

#### `@RestController`

**Description** : Combine @Controller et @ResponseBody. Cette annotation est utilisée pour les contrôleurs où chaque méthode renvoie automatiquement un corps de réponse (généralement pour les services web RESTful).

**Exemple d'utilisation** :

```java
import org.springframework.web.bind.annotation.
    RestController;
import org.springframework.web.bind.annotation.
    GetMapping;

@RestController
public class SorciersRestController {
    @GetMapping("/api/sorciers")
    public List<User> getAllSorciers() {
        // renvoie la liste des utilisateurs
        // comme JSON
    }
}
```

---

## B. Spring Boot : annotations de configuration

#### `@Configuration`

**Description** : Indique qu'une classe peut être utilisée comme source de définitions de beans.

**Exemple d'utilisation** : 

```java
import org.springframework.context.annotation.
    Configuration;
import org.springframework.context.annotation.
    Bean;

@Configuration
public class AppConfig {
    // Configuration du bean pour le service
    // des sorciers
    @Bean
    public SorcierService sorcierService(
        SorcierRepository sorcierRep) {

        return new SorcServiceImpl(sorcRep);
    }
}
```

#### `@Bean`

**Description** : Utilisée au sein des classes @Configuration pour déclarer un bean Spring. Cette annotation est employée pour configurer et initialiser les objets que le conteneur Spring gère.

**Exemple d'utilisation** :

```Java
import org.springframework.context.annotation.
    Configuration;
import org.springframework.context.annotation.
    Bean;

@Configuration
public class AppConfig {
    // Configuration du bean pour le service
    // des sorciers
    @Bean
    public SorcService sorcService(
        SorcRepository sorcRep) {

        return new SorcServiceImpl(sorcRep);
    }
}
```

#### `@Profile`

**Description** : Spécifie le profil pour lequel une classe est éligible. Cela permet de préciser quelle version de classe est utilisée, par exemple mock ou sqlite.

**Exemple d'utilisation** :
```java
import org.springframework.context.annotation.
    Profile;

@Profile("dev")
public class SorcierServiceMock {
    public SorcServiceMock(SorcRepositoryMock 
        sorcierRepositoryMock) {
        // ...
    }
}
```

#### `@PropertySource`

**Description** : Utilisée pour déclarer un ensemble de propriétés dans des fichiers externes qui seront disponibles dans l'environnement Spring. Cette annotation est souvent utilisée pour externaliser la configuration.

**Exemple d'utilisation** :

```java
import org.springframework.context.annotation.
    PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class ProprietesConfig {
    // Configuration utilisant les propriétés
    // de config.prorperties.
}
```

#### `@Import`

**Description** : Permet d'importer des configurations de bean supplémentaires dans une classe de configuration existante. Cela est particulièrement utile pour la modularité et la réutilisation des configurations.

**Exemple d'utilisation** :

```java
import org.springframework.context.annotation.Import;

@Configuration
@Import(AnotherConfig.class)
public class MainConfig {
    // logique de la principale config
}
```

---

## C. Spring Boot : annotations de persistance
Utilisées dans le contexte de la persistance, notamment avec JPA (Java Persistence API), elles indique qu'une classe est une entité JPA, ce qui signifie qu'elle est liée à une table de base de données et qu'elle est gérée par le contexte de persistance.

#### `@Entity`

**Description** : Marque une classe comme une entité JPA, ce qui signifie qu'elle est une table dans une base de données. Cette annotation est nécessaire pour que la classe puisse être gérée par l'EntityManager de JPA.

**Exemple d'utilisation** :

```java
import jakarta.persistence.Entity;

@Entity
public class Sorcier {
    // La classe est maintenant une entité
    // liée à une table de base de données.
}
```

#### `@Table`

**Description** : Spécifie la table à laquelle l'entité est mappée. Cette annotation est utile lorsque le nom de la table diffère du nom de la classe.

**Exemple d'utilisation** :

```java

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sorcier")
public class Sorcier {
    // La classe est mappée à la table 'users'
    // dans la base de données.
}
```

#### `@Id`

**Description** : Marque un champ comme la clé primaire de l'entité.

**Exemple d'utilisation** :

```java
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sorcier")
public class User {
    @Id
    private Long id;
    // Ce champ est la clé primaire de la
    // table.
}
```

#### `@GeneratedValue`

**Description** : : Configure la stratégie de génération de la valeur des clés primaires. Cela peut être automatique, basé sur une séquence, ou fourni par une autre source.

**Exemple d'utilisation** :

```java
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sorcier")
public class Sorcier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // La clé primaire 'id' est générée
    // automatiquement.
}
```

#### `@Column`

**Description** : : Spécifie le mappage entre un champ et une colonne de la base de données. Cette annotation peut aussi définir le nom, la longueur, et d'autres propriétés de la colonne.

**Exemple d'utilisation** :

```java
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "sorcier")
public class User {
    //...
    @Column(name = "utilisateur", length = 50, nullable = false)
    private String utilisateur;
    // Ce champ est mappé à la colonne
    // 'utilisateur' dans la table avec
    // les contraintes nécessaires.
}
```

---

## D. Spring Boot : annotations de validation

Utilisées pour garantir la conformité des données avec les règles métier spécifiques et maintenir l'intégrité de l'application. Ces annotations permettent de définir clairement les exigences pour chaque champ de données, améliorant ainsi la robustesse et la sécurité du système.

#### `@Pattern`

**Description** : Valide que le contenu d'une chaîne correspond à un motif (regex) spécifié.

**Exemple d'utilisation** :

```java
import jakarta.validation.constraints.Pattern;

@Pattern(regexp = "^[a-zA-Z]{3,25}$", message = "Le nom doit contenir entre 3 et 25 lettres.")
private String nom;
```

#### `@Min`
#### `@Max`

**Description** : Définissent les contraintes numériques minimum et maximum pour une valeur.

**Exemple d'utilisation** :

```java
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Min(18)
@Max(65)
private int age;
```

#### `@Valid`

**Description** : Indique que les propriétés d'un objet imbriqué doivent également être validées.

**Exemple d'utilisation** :

```java
import jakarta.validation.Valid;

@Valid
private Adresse adresse;
```

#### `@Size`

**Description** : S'assure que le caractère ou la taille de la collection se trouve dans une plage spécifiée.

**Exemple d'utilisation** :

```Java
import jakarta.validation.constraints.Size;

@Size(max = 200, message = "La description ne doit pas
    dépasser 200 caractères.")
private String description;
```

#### `@NotNull`

**Description** : S'assure qu'un champ n'est pas nul.

**Exemple d'utilisation** :


```java
import jakarta.validation.constraints.NotNull;

@NotNull(message = "Le numéro de téléphone est
    obligatoire.")
private String telephone;
```

#### `@NotEmpty`

**Description** : S'assure que les collections, les tableaux ou les chaînes ne sont pas vides.

**Exemple d'utilisation** :

```java
import jakarta.validation.constraints.NotEmpty;

@NotEmpty(message = "La liste des compétences ne
    peut pas être vide.")
private List<String> competences;
```

#### `@Email`

**Description** : Valide que la chaîne correspond à un format d'e-mail valide.

**Exemple d'utilisation** :

```java
import jakarta.validation.constraints.Email;

@Email(message = "Adresse email invalide.")
private String email;
```

#### `@Positive`

**Description** : @Positive vérifie qu'un nombre est strictement positif.

**Exemple d'utilisation** : 

```java
import jakarta.validation.constraints.Positive;

@Positive(message = "Le dépôt doit être
    supérieur à zéro.")
private long depotInitial;
```

#### `@PositiveOrZero`

**Description** : @PositiveOrZero vérifie qu'un nombre est positif ou zéro.

**Exemple d'utilisation** :

```java
import jakarta.validation.constraints.PositiveOrZero;

@PositiveOrZero(message = "Le solde du compte
    ne peut pas être négatif.")
private long solde;
```

#### `@OneToOne`

**Description** : Utilisée pour indiquer une relation un-à-un entre deux classes d'entités. Chaque instance dans une table est associée à une instance unique dans une autre table.

**Exemple d'utilisation** :
```java
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.CascadeType;

@Entity
public class UtilisateurProfil {
    @OneToOne(mappedBy = "utilisateurProfil",
        cascade = CascadeType.ALL)
    // ATTENTION AUX OPERATIONS EN CASCADE.
    private Utilisateur utilisateur;
}
```

#### `@ManyToMany`

**Description** : Utilisée pour définir une relation beaucoup-à-beaucoup entre deux classes d'entités. Aucune des deux classes n'est propriétaire de la relation.

**Exemple d'utilisation** :
```java
import javax.persistence.ManyToMany;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

@Entity
public class Etudiant {
    @ManyToMany
    @JoinTable(
      name = "cour_etudiant",
      joinColumns = @JoinColumn(name =
        "id_etudiant"),
      inverseJoinColumns = @JoinColumn(name =
        "id_cour")
    )
    private Set<Course> courses;
}
```

#### `@ManyToOne`

**Description** : Annotation utilisée dans JPA pour définir une association de clé étrangère à sens unique où plusieurs entités d'une classe peuvent être associées à une seule entité d'une autre classe. Elle est généralement utilisée dans le contexte des relations de base de données entre tables.

**Exemple d'utilisation** :
```java

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;

@Entity
public class Employe {
    @ManyToOne
    @JoinColumn(name="id_service") 
    // id_service = nom de la colonne de clé
    // étrangère dans la table 'Employe'
    private Service service;
}
```

#### `@OneToMany`
**Description** : Annotation utilisée dans JPA pour indiquer qu'une seule entité est liée à plusieurs entités d'une autre classe. Cette annotation est souvent utilisée pour définir la partie "un" d'une relation "un à plusieurs" entre deux types d'entités.

**Exemple d'utilisation** :
```java
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Entity;

@Entity
public class Departement {
    @OneToMany(mappedBy="departement", cascade=
        CascadeType.ALL)
    // ATTENTION AUX OPERATIONS EN CASCADE.
    private List<Employe> employe;
}
```

---

## E. Spring Boot : annotations supplémentaires

#### `@Autowired`

**Description** : Permet l'injection automatique de dépendances dans Spring. `@Autowired` peut être placée sur des champs, des méthodes setter, ou des constructeurs, indiquant au conteneur Spring d'injecter automatiquement la dépendance appropriée.

**Exemple d'utilisation** :

```java
import org.springframework.beans.factory.annotation
    .Autowired;
import org.springframework.stereotype.Service;

@Service
public class SorcierService {
    private SorcierRepository sorcierRepository;

    @Autowired
    public SorcierService(SorcierRepository
        sorcierRepository) {
        this.sorcierRepository = sorcierRepository;
    }
}
```

#### `@Qualifier`

**Description** : Utilisée en conjonction avec @Autowired pour préciser quel bean doit être injecté lorsque plusieurs beans de même type sont disponibles.

**Exemple d'utilisation** :
```java
import org.springframework.beans.factory.
    annotation.Autowired;
import org.springframework.beans.factory.
    annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SorcService {
    private Sort sort;

    @Autowired
    public SorcService(@Qualifier("sortDefense")
        Sort sort) {

        this.sort = sort;
    }
}
```

#### `@GetMapping`

**Description** : utilisée pour mapper les requêtes HTTP GET sur une méthode spécifique du contrôleur.

**Exemple d'utilisation** :

```java
import org.springframework.web.bind.annotation.
    GetMapping;

//...
@GetMapping("/accueil")
public String accueil() {
    return "accueil";
}
```

#### `@PostMapping`
**Description** : utilisée pour mapper les requêtes HTTP POST sur une méthode spécifique du contrôleur.

**Exemple d'utilisation** :

```java
import org.springframework.web.bind.annotation.
    PostMapping;
//...

@PostMapping("/Enregistrer")
public String joueurEnregistrer() {
    // logique d'enregistrement
    return "redirect:/home";
}

```

#### `@ModelAttribute`

**Description** : lie une méthode de paramètre ou une méthode de retour à un nom de modèle spécifié, et l'expose à une vue web. Souvent utilisée pour préparer les données du modèle que la vue utilisera.

**Exemple d'utilisation** :

```java
import org.springframework.web.bind.
    annotation.ModelAttribute;
//...

@GetMapping("/sorciers/detailler")
public String detaillerSorcier(@ModelAttribute("id") int id) {
    sorcierService.detaillerSorcier(id);
    return "sorcier_detaille";
}
```

#### `@RequestParam`

**Description** : utilisée pour extraire les valeurs des paramètres de la requête. Elle est utilisée pour accéder aux valeurs des paramètres des requêtes GET ou POST.

**Exemple d'utilisation** :

```java
import org.springframework.web.bind.
    annotation.GetMapping;
import org.springframework.web.bind.
    annotation.RequestParam;
//...

@GetMapping("/sorciers")
public String chercherSorcier(
    @RequestParam("terme") String terme) {
    // logique pour aller chercher
    return "results";
}
```

#### `@Test`

**Description** : utilisée avec JUnit pour identifier une méthode de test. Les méthodes annotées avec @Test seront exécutées par Spring Boot lors du lancement des tests.

**Exemple d'utilisation** :

```java
import org.junit.jupiter.api.Test;

public class SimpleTest {
    @Test
    public void monTest() {
        // logique de test.
        assert(true);
    }
}
```

#### `@TestMethodOrder`

**Description** : utilisée avec JUnit pour spécifier l'ordre d'exécution des méthodes de test à l'échelle de la classe. Elle nécessite un argument indiquant la stratégie d'ordonnancement à utiliser.

**Exemple d'utilisation** :

```java
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.
    OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class TestOrdonnees {
    // méthodes avec logique de test.
}
```

#### `@Order`

**Description** : Utilisée avec JUnit pour spécifier l'ordre d'exécution des méthodes de test dans une classe de test. Elle est souvent utilisée quand l'ordre dans lequel les tests sont exécutés est important pour le contexte du test.

**Exemple d'utilisation** :

```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.
    OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class TestsOrdonnes {
    @Test
    @Order(1)
    public void premierTest() {
        // logique pour le premier test.
        assert(true);
    }

    @Test
    @Order(2)
    public void deuxiemeTest() {
        // logique poru le second test.
        assert(true);
    }
}
```

#### `@SpringBootTest`

**Description** : utilisée pour fournir une configuration de test complet basée sur Spring Boot. Elle charge l'ensemble du contexte de l'application Spring Boot pour être utilisé pendant les tests.

**Exemple d'utilisation** :

```java
import org.springframework.boot.test.context.
    SpringBootTest;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class SpringContexteTest {
    @Test
    public void chargementEffectue() {
        // logique de test.
    }
}
```

#### `@SpringBootApplication`

**Description** : utilisée pour marquer la classe principale d'une application Spring Boot. Elle englobe @Configuration, @EnableAutoConfiguration, et @ComponentScan, fournissant tout le nécessaire pour configurer l'application.

**Exemple d'utilisation** :

```java
import org.springframework.boot.
    SpringApplication;
import org.springframework.boot.autoconfigure.
    SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}
```

---

## F. Thymeleaf

#### `data-th-href`

##### Sans paramètre.

**Description** : Cet attribut `data-th-href`/`th:href` est utilisé pour dynamiquement générer des liens  dans les éléments HTML tels que \<a>, en intégrant des valeurs issues du modèle de données. Cet attribut permet de créer des URLs conditionnées par des données, rendant les liens plus interactifs et adaptés au contexte de l'application.

**Exemple d'utilisation** :

```java
// Le code Java
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MaisonController {

    @GetMapping("/maisons")
    public String versMaison(Model model) {
        return "liste_maisons"; 
    }
}
```
```html
<!-- Le code HTML -->
<a th:href="@{/maisons}" >Maison</a>
```

##### Avec paramètre.

**Description** : Ainsi utilisé, l'attribut data-th-href/th:href permet de générer dynamiquement des liens qui incluent des paramètres de requête (@RequestParam). Ces paramètres permettent de passer des valeurs spécifiques via l'URL, ce qui est utile pour des actions comme filtrer ou afficher des détails sur une entité sans modifier l'URL de base.

**Exemple d'utilisation** :

```java
// Le code Java
@GetMapping("/maisons/detailler")
public String afficherMaison(
    @RequestParam("id") int id_maison, Model model) {
    // Récupération de la maison par son identifiant.
    Maison m = maisonService.recupererMaison(id_maison);
    // Ajout de la maison au modèle pour l'affichage.
    model.addAttribute("maisonsADetailler", m);
    return "maisons_detaillee";
}
```
```html
<!-- Le code HTML -->
<a th:href="@{/maisons/detailler(id=${maison.id})}">Détails</a>
```


#### `data-th-each`

**Description** : L'attribut `data-th-each`/`th:each` crée dynamiquement les lignes d'un tableau en itérant sur une collection. Pour chaque maison, seront affichées toutes les caractétistiques voulues.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/maisons")
public String listerMaisons(Model model) {
    List<Maison> maisons = maisonService.lesMaisons();
    model.addAttribute("maisons", maisons);
    return "liste_maisons";
}
```

```html
<!-- Le code HTML avec Thymeleaf -->
<table>
    <thead><tr>
            <th>Nom</th>
            <th>Attaque</th>
            <th>Sante</th>
    </tr></thead>
    <tbody>
        <tr th:each="maison : ${maisons}">
            <td th:text="${maison.nom}"></td>
            <td th:text="${maison.attaque}"></td>
            <td th:text="${maison.sante}"></td>
        </tr>
    </tbody>
</table>
```

#### `th:if`

**Description** : L'attribut `th:if` est utilisé pour conditionnellement afficher un élément HTML basé sur une expression. Il permet de contrôler la visibilité des éléments dans la vue en fonction de conditions spécifiées, rendant la page dynamique et interactive.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/afficherMessage")
public String afficherMessage(Model model) {
    model.addAttribute("afficher", true); // Condition pour afficher le message
    return "afficherMessage";
}
```

```html
<!-- Le code HTML -->
<div th:if="${afficher}">
    <p>Message conditionnel affiché si 'afficher' est vrai.</p>
</div>
```

#### `th:unless`

**Description** : `th:unless` affiche un élément si une condition `th:if` n'a pas été vérifiée.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/afficherCondition")
public String afficherCondition(Model model) {
    model.addAttribute("estAdmin", true);
    return "afficherCondition";
}
```

```html
<!-- Le code HTML -->
<div th:if="${estAdmin}">
    Vous êtes administrateur.
</div>
<div th:unless="${estAdmin}">
    Vous n'êtes pas administrateur.
</div>
```

#### `th:switch`

**Description** : `th:switch` est un attribut de Thymeleaf utilisé pour effectuer une sélection parmi plusieurs possibilités, similaire à la structure de contrôle switch en Java. Il évalue l'expression une fois, puis compare le résultat à différentes valeurs th:case dans des éléments enfants, permettant ainsi de gérer plusieurs branches conditionnelles dans un template.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/afficherRole")
public String afficherRoleUtilisateur(Model model) {
    model.addAttribute("user", new User("admin"));
    // Simuler un utilisateur avec le rôle 'admin'
    return "afficherRole";
}
```

```html
<!-- Le code HTML -->
<div th:switch="${user.role}">
    <p th:case="'admin'">User est un administrateur</p>
    <p th:case="#{roles.inscrit}">User est un inscrit</p>
    <p th:case="*">User est un visiteur</p>
</div>
```

#### `th:block`

**Description** : `th:block` est un élément non-affiché utilisé pour grouper plusieurs éléments sans introduire de balises HTML supplémentaires dans le rendu final. Il est utile pour appliquer des attributs à un groupe d'éléments sans affecter la structure du document.

**Exemple d'utilisation** :
```java
@GetMapping("/afficherBloc")
public String afficherBloc(Model model) {
    model.addAttribute("message", "Ceci est un message groupé.");
    return "afficherBloc";
}
```

```html
<!-- Le code HTML -->
<th:block th:if="${message}">
    <p th:text="${message}"></p>
    <p>Un autre paragraphe sous le même bloc conditionnel.</p>
</th:block>
```

#### `th-attr`

**Description** : L'attribut th-attr permet de définir dynamiquement les attributs des éléments HTML basés sur des valeurs de modèle. Il est utilisé pour personnaliser des attributs comme class, style, href, entre autres, en fonction des données du serveur.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/sorciers/profil")
public String profilSorcier(Model model) {
    model.addAttribute("estActif", true);
    return "profilSorcier";
}
```

```html
<!-- Le code HTML -->
<a href="/user/settings" th-attr="class=${estActif ? 'active' : 'inactive'}">Paramètres</a>
```

#### `th-object`

**Description** : th-object est utilisé pour spécifier un objet de modèle auquel les expressions de l'attribut th:* peuvent faire référence. Cela simplifie l'accès aux propriétés de l'objet dans le template, améliorant la clarté et réduisant la répétition du code.

**Exemple d'utilisation** :

```java
// Le code Java
@GetMapping("/sorciers/creer")
public String creerSorcier(Model model) {
    Sorcier sorcier = new Sorcier();
    // suppose une classe Utilisateur appropriée
    model.addAttribute("sorcier", sorcier);
    return "sorcier";
}
```

```html
<!-- Le code HTML -->
<form th-object="${sorcier}" action="#" th:action="@{/sorciers/creer}">
    <input type="text" th:field="*{nom}" />
    <input type="submit" value="Soumettre" />
</form>
```


#### `th:src`

**Description** : `th:src` est utilisé pour insérer dynamiquement des URL de ressources dans les attributs src des éléments HTML tels que \<img>, ce qui permet de référencer des images de manière dynamique.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/afficherImage")
public String afficherImage(Model model) {
    model.addAttribute("urlImage", "/images/logo.png");
    return "afficherImage";
}
```

```html
<!-- Le code HTML -->
<img th:src="@{${urlImage}}" alt="Logo"/>
```

#### `th:style`

**Description** : `th:style` permet de définir dynamiquement des attributs de style CSS sur des éléments HTML, en utilisant des valeurs extraites du modèle.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/afficherStyle")
public String afficherStyle(Model model) {
    model.addAttribute("tailleFont", "16px");
    return "afficherStyle";
}
}=
```

```html
<!-- Le code HTML -->
<div th:style="'font-size: ' + ${tailleFont}">
    Texte avec une taille de police dynamique.
</div>
```

#### `th:text`

**Description** : `th:text` est utilisé pour remplacer le texte d'un élément HTML par une valeur dynamique provenant du modèle, ce qui permet de localiser le texte ou de changer le contenu textuel en fonction des données du serveur.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/afficherTexte")
public String afficherTexte(Model model) {
    model.addAttribute("message", "Bonjour, monde !");
    return "afficherTexte";
}
```

```html
<!-- Le code HTML -->
<p th:text="${message}"></p>
```

#### `th:selected`

**Description** : `th:selected` est utilisé pour définir dynamiquement l'attribut selected sur des options de \<select>, généralement basé sur une comparaison avec une valeur du modèle.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/formulaire")
public String montrerFormulaire(Model model) {
    model.addAttribute("sel", "opt2");
    return "formulaire";
}
```

```html
<!-- Le code HTML -->
<select>
    <option value="opt1" th:selected="${sel == 'opt1'}">Option 1</option>
    <option value="opt2" th:selected="${sel == 'opt2'}">Option 2</option>
    <option value="opt3" th:selected="${sel == 'opt3'}">Option 3</option>
</select>
```


#### `th:with`

**Description** : `th:with` est utilisé pour déclarer des variables locales dans le template, utiles pour réutiliser des valeurs calculées dans plusieurs expressions sans recalculer.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/calcul")
public String faireCalcul(Model model) {
    model.addAttribute("a", 5);
    model.addAttribute("b", 10);
    return "afficherCalcul";
}
```

```html
<!-- Le code HTML -->
<div th:with="somme=${a + b}">
    La somme de a et b est : <span th:text="${somme}"></span>
</div>
```

#### `th:action`

**Description** : `th:action` est utilisé pour définir dynamiquement l'attribut action d'un formulaire HTML, qui spécifie l'URL de destination pour la soumission du formulaire. Cela permet de configurer l'URL de soumission du formulaire en fonction des données ou des conditions spécifiques du modèle.

**Exemple d'utilisation** :
```java
// Le code Java
@GetMapping("/afficherFormulaire")
public String afficherFormulaire(Model model) {
    return "formulaire";
}
```

```html
<!-- Le code HTML -->
<form th:action="@{/soumettreFormulaire}" method="post">
    <input type="text" name="nom" placeholder="Entrez votre nom">
    <button type="submit">Soumettre</button>
</form>
```


#### `th:replace`
#### `th:fragment`

**Description** : `th:replace` est utilisé pour inclure et remplacer un élément dans un template avec un fragment de template spécifié. Cela permet de réutiliser des parties de code HTML communes à plusieurs pages, comme les en-têtes, pieds de page, ou composants de navigation. `th:fragment` définit le fragment de code HTML à inclure.

**Exemple d'utilisation** :

Définition du fragment (dans un fichier appelé fragments.html par exemple) :

```html
<!-- Code HTML pour le fragment -->
<div th:fragment="header">
    <header>
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/about">About</a></li>
            </ul>
        </nav>
    </header>
</div>
```

Utilisation du fragment avec th:replace (dans n'importe quel autre fichier template) :

```html
<!-- Code HTML utilisant le fragment -->
<html>
<head>
    <title>Page Title</title>
</head>
<body>
    <div th:replace="fragments :: header"></div>
    <p>Content of the page goes here.</p>
</body>
</html>
```

---

## G. Spring Boot : build.gradle

### 1) Plugins

#### `plugin:java`
**Description** : Ce plugin est essentiel pour tout projet Java. Il permet à Gradle de compiler des projets Java, de gérer les dépendances et d'exécuter des tests, parmi d'autres tâches essentielles.

**Exemple d'utilisation** :

```groovy
plugins {
    id 'java'
}
```

#### `org-springframework-boot`

**Description** : Ce plugin facilite la création d'applications Spring Boot. Il configure automatiquement le projet pour l'empaqueter correctement en tant qu'application exécutable, gère les dépendances Spring Boot et configure le projet pour utiliser spring-boot:run pour démarrer l'application.

**Exemple d'utilisation** :

```groovy
plugins {
    id 'org.springframework.boot' version '3.2.4'
}
```

#### `io-spring-dependency-management`

**Description** : Ce plugin permet une gestion fine des dépendances dans un projet Spring Boot, en ligne avec la manière dont Spring Boot gère ses dépendances, permettant d'assurer la compatibilité des versions des différentes bibliothèques.

**Exemple d'utilisation** :

```groovy
plugins {
    id 'io.spring.dependency-management' version '1.1.4'
}
```

### 2) Informations sur le projet

#### `group`

**Description** : Identifie le groupe ou l'organisation qui développe le projet. Cela aide à structurer les packages et à publier des artefacts dans des répertoires de dépôt de manière organisée.

**Exemple d'utilisation** :

```groovy
group = 'com.example.sorcier'
```

#### `version`

**Description** : Spécifie la version du projet en cours de développement. 0.0.1-SNAPSHOT indique une version préliminaire en développement.

**Exemple d'utilisation** :

```groovy
version = '0.0.1-SNAPSHOT'
```

### 3) Configurations Java

#### `sourceCompatibility`

**Description** : Définit la version de la source Java que Gradle doit utiliser pour compiler le projet. Ici, 17 indique que le code source est compatible avec Java 17.

**Exemple d'utilisation** :

```groovy
java {
    sourceCompatibility = '17'
}
```

### 4) Dépôts

#### `mavenCentral`

**Description** : Déclare que Gradle devrait résoudre les dépendances à partir du dépôt central Maven, un vaste dépôt public de bibliothèques Java et d'autres composants.

**Exemple d'utilisation** :

```groovy
repositories {
    mavenCentral()
}
```

### 5) Dépendances

#### `spring-boot-starter-thymeleaf`

**Description** : Cette dépendance ajoute le support de Thymeleaf à une application Spring Boot. Thymeleaf est un moteur de template pour le développement web en Java, utilisé pour rendre les vues HTML côté serveur.

**Exemple d'utilisation** :

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
}
```

#### `spring-boot-starter-web`

**Description** : Starter pour la création d'applications web, y compris RESTful, utilisant Spring MVC. Il utilise Tomcat comme serveur web par défaut, mais peut être configuré pour utiliser d'autres serveurs.

**Exemple d'utilisation** :

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

#### `spring-boot-starter-validation`

**Description** : Starter pour la validation avec Hibernate Validator. Intègre automatiquement la validation JSR-380 (Bean Validation 2.0) dans Spring.

**Exemple d'utilisation** :

```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-validation'
}
```

#### `spring-boot-devtools`

**Description** : Fournit des outils de développement rapide pour les applications Spring Boot, comme le rechargement automatique des classes et des ressources sans redémarrer l'application.

**Exemple d'utilisation** :

```groovy
dependencies {
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
}
```

#### `sqlite-jdbc`

**Description** : Pilote JDBC pour SQLite. Permet la connexion à des bases de données SQLite à partir d'applications Java.

**Exemple d'utilisation** :

```groovy
dependencies {
    implementation 'org.xerial:sqlite-jdbc:3.34.0'
}
```

#### `spring-boot-starter-test`

**Description** : Starter pour tester les applications Spring Boot avec des bibliothèques comme JUnit, Hamcrest et Mockito.

**Exemple d'utilisation** :

```groovy
dependencies {
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
}
```

#### `spring-boot-starter-data-jpa`

**Description** : Starter pour utiliser Spring Data JPA avec Hibernate. Simplifie la configuration et l'utilisation de bases de données dans les applications Spring.

**Exemple d'utilisation** :


```groovy
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
}
```

#### `hibernate-community-dialects`

**Description** : Fournit des dialectes Hibernate pour diverses bases de données. Ces dialectes sont nécessaires pour que Hibernate communique efficacement avec la base de données en utilisant des commandes SQL adaptées à chaque base.

**Exemple d'utilisation** :

```groovy
dependencies {
    implementation group: 'org.hibernate.orm', name: 'hibernate-community-dialects', version: '6.5.0.CR1'
}
```

### 6) Configuration des tâches

#### `useJUnitPlatform`

**Description** : Configure la tâche de test pour utiliser la plateforme JUnit. Cela permet à Gradle de reconnaître et d'exécuter les tests écrits avec JUnit 5, le framework de test moderne pour les applications Java.

**Exemple d'utilisation** :

```groovy
tasks.named('test') {
    useJUnitPlatform()
}
```

---

## H. Méthodes

### 1) Méthodes de JdbcTemplate


#### `execute`

**Description** : Exécute une instruction SQL donnée, généralement pour des commandes DDL comme CREATE ou DROP.

**Exemple d'utilisation** :

```java
jdbcTemplate.execute("DROP TABLE IF EXISTS Sorcier;");
```

#### `queryForObject`

**Description** : Exécute une requête SQL pour retourner un objet de type spécifié, souvent utilisé pour des requêtes qui retournent une seule ligne de résultat.

**Exemple d'utilisation** :

```java
Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM Maison", Integer.class);
```

#### `queryForList`

**Description** : Exécute une requête SQL pour retourner une liste d'objets de type spécifié, utile pour récupérer des colonnes spécifiques de plusieurs lignes.

**Exemple d'utilisation** :

```java
List<String> names = jdbcTemplate.queryForList("SELECT nom FROM Sorcier", String.class);
```

#### `update`

**Description** : Exécute une instruction SQL pour la mise à jour des données (INSERT, UPDATE, DELETE) et retourne le nombre de lignes affectées.

**Exemple d'utilisation** :

```java
int rowsAffected = jdbcTemplate.update("INSERT INTO Maison (nom, bonus_attaque, bonus_sante, modifiable) VALUES ('Gryffondor', 5, 10, 0)");
```

### 2) Méthodes d'Assertions de JUnit

#### `assertEquals`
**Description** : Assure que deux valeurs sont égales. Si elles ne le sont pas, le test échoue.

**Exemple d'utilisation** :

```java
assertEquals(1, rowsAffected);
```

#### `assertFalse`

**Description** : Vérifie que la condition donnée est fausse. Si la condition est vraie, le test échoue.

**Exemple d'utilisation** :

```java
assertFalse(count > 0);
```

### 3) java.sql.DriverManager

#### `getConnection`
**Description** : Établit une connexion à la base de données en utilisant l'URL fournie. Cette fonction est essentielle pour interagir avec la base de données.

**Exemple d'utilisation** :

```java
Connection connexion = DriverManager.getConnection("jdbc:sqlite:chemin/vers/la/base_de_donnees.sqlite");
```

### 4) java.sql.Connection

#### `prepareStatement`

**Description** : Prépare une requête SQL pour exécution, ce qui peut inclure des paramètres IN qui doivent être spécifiés avant l'exécution.

Exemple d'utilisation :

```java
PreparedStatement preparedStatement = connexion.prepareStatement("SELECT * FROM maison WHERE id = ?");
preparedStatement.setInt(1, 10);  // Définir le paramètre 'id' à 10
ResultSet rs = preparedStatement.executeQuery();
```

#### `createStatement`
**Description** : Crée un objet Statement pour envoyer des commandes SQL à la base de données sans paramètres.

**Exemple d'utilisation** :

```java
Statement stmt = connexion.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM maison");
```

### 5) java.sql.PreparedStatement

#### `executeQuery`

**Description** : Exécute la requête SQL préparée et retourne un ResultSet qui contient les données obtenues de la base de données, utilisé principalement pour les requêtes de lecture.

**Exemple d'utilisation** :

```java
ResultSet rs = preparedStatement.executeQuery(); 
// Exécuter une requête SELECT préparée
```

#### `setInt`

**Description** : Définit la valeur d'un paramètre de type entier dans la requête SQL précompilée à l'index spécifié.

**Exemple d'utilisation** :

```java
preparedStatement.setInt(1, 123);  // Définir le premier paramètre à 123
```

#### `setString`

**Description** : Définit la valeur d'un paramètre de type chaîne de caractères dans la requête SQL précompilée à l'index spécifié.

**Exemple d'utilisation** :

```java
preparedStatement.setString(1, "Gryffondor"); 
// Définir le premier paramètre à 'Gryffondor'
```

#### `executeUpdate`

**Description** : Exécute la requête SQL préparée (INSERT, UPDATE, DELETE, etc.) et retourne le nombre de lignes affectées.

**Exemple d'utilisation** :

```java
int affectedRows = preparedStatement.executeUpdate();  // Exécuter une requête INSERT, UPDATE ou DELETE
```

### 6) java.sql.ResultSet

#### `next`

**Description** : Déplace le curseur de ResultSet à la ligne suivante et retourne true si une nouvelle ligne est disponible.

**Exemple d'utilisation** :

```java
while (rs.next()) {
    System.out.println("Nom: " + rs.getString("nom"));
}
```

#### `getInt`

**Description** : Récupère la valeur de la colonne spécifiée comme un entier.

**Exemple d'utilisation** :

```java
int id = rs.getInt("id");
```

#### `getString`

**Description** : Récupère la valeur de la colonne spécifiée comme une chaîne de caractères.

**Exemple d'utilisation** :

```java
String nom = rs.getString("nom");
```

### 7) java.sql.SQLException

**Description** : Capture et traite les erreurs qui se produisent lors de l'interaction avec la base de données.

**Exemple d'utilisation** :

```java
try {
    // Opérations JDBC
} catch (SQLException e) {
    System.out.println("Erreur SQL: " + e.getMessage());
}
```
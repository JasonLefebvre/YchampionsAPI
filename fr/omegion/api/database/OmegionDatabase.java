// Package dans lequel se trouve la classe
package fr.omegion.api.database;

// Importation de classes nécessaires pour la gestion de la base de données
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Déclaration de la classe OmegionDatabase
public class OmegionDatabase {
   // Connexion à la base de données
   private Connection connection;

   // Informations de connexion
   private final String url;
   private final String host;
   private final String database;
   private final String user;
   private final String pass;

   // Constructeur de la classe, initialise les informations de connexion
   public OmegionDatabase(String url, String host, String database, String user, String pass) {
      this.url = url;
      this.host = host;
      this.database = database;
      this.user = user;
      this.pass = pass;
   }

   // Méthode pour établir la connexion à la base de données
   public void connect() {
      // Vérifie si la connexion n'est pas déjà établie
      if (!this.isConnected()) {
         try {
            // Établissement de la connexion en utilisant les informations fournies
            this.connection = DriverManager.getConnection(this.url + this.host + "/" + this.database, this.user, this.pass);
            System.out.println("OMEGION DATABASE CONNECTED");
         } catch (SQLException var2) {
            // Gestion des erreurs en cas d'échec de connexion
            var2.printStackTrace();
         }
      }
   }

   // Méthode pour fermer la connexion à la base de données
   public void disconnect() {
      // Vérifie si la connexion est établie
      if (this.isConnected()) {
         try {
            // Fermeture de la connexion
            this.connection.close();
            System.out.println("OMEGION DATABASE DISCONNECTED");
         } catch (SQLException var2) {
            // Gestion des erreurs en cas d'échec de fermeture de connexion
            var2.printStackTrace();
         }
      }
   }

   // Méthode pour vérifier si la connexion est établie
   public boolean isConnected() {
      return this.connection != null;
   }

   // Méthode pour obtenir l'objet Connection actuel
   public Connection connection() {
      return this.connection;
   }
}

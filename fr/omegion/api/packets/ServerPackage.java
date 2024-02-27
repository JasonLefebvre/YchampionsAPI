// Package dans lequel se trouve l'énumération
package fr.omegion.api.packets;

// Importation de la classe Bukkit pour accéder à des fonctionnalités de Bukkit
import org.bukkit.Bukkit;

// Déclaration de l'énumération ServerPackage
public enum ServerPackage {
   // Énumérations représentant les packages "net.minecraft.server" et "org.bukkit.craftbukkit" avec la version du serveur
   MINECRAFT("net.minecraft.server." + getServerVersion()),
   CRAFTBUKKIT("org.bukkit.craftbukkit." + getServerVersion());

   // Chemin du package
   private final String path;

   // Constructeur privé pour initialiser le chemin du package
   private ServerPackage(String path) {
      this.path = path;
   }

   // Méthode statique pour obtenir la version du serveur Bukkit
   public static String getServerVersion() {
      return Bukkit.getServer().getClass().getPackage().getName().substring(23);
   }

   // Méthode toString pour obtenir le chemin complet du package en tant que chaîne de caractères
   public String toString() {
      return this.path;
   }

   // Méthode pour obtenir une classe à partir du nom de classe dans le package spécifié
   public Class<?> getClass(String className) throws ClassNotFoundException {
      return Class.forName(this.toString() + "." + className);
   }
}

// Package dans lequel se trouve la classe
package fr.omegion.api.managers;

// Importation de classes nécessaires pour gérer les événements Bukkit
import fr.omegion.api.OmegionAPI;
import org.bukkit.event.Listener;

// Déclaration de la classe EventsManager
public class EventsManager {
   // Tableau de Listeners représentant les événements à gérer
   private final Listener[] events;

   // Constructeur de la classe qui prend en paramètre un tableau de Listeners
   public EventsManager(Listener[] events) {
      this.events = events;
   }

   // Méthode pour charger les événements enregistrés dans le tableau
   public void load() {
      // Pour chaque Listener dans le tableau d'événements
      for(Listener event : this.events) {
         // Enregistrement de l'événement auprès du gestionnaire d'événements de Bukkit
         OmegionAPI.getInstance().getServer().getPluginManager().registerEvents(event, OmegionAPI.getInstance());
      }
   }
}

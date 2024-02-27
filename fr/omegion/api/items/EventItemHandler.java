// Package dans lequel se trouve la classe
package fr.omegion.api.items;

// Importation de la classe Player de Bukkit pour gérer le joueur associé à cet EventItemHandler
import org.bukkit.entity.Player;

// Déclaration de la classe EventItemHandler
public class EventItemHandler {
   // Joueur associé à cet EventItemHandler
   private Player player;

   // Constructeur de la classe prenant un joueur en paramètre
   public EventItemHandler(Player player) {
      this.player = player;
   }

   // Méthode pour obtenir le joueur associé à cet EventItemHandler
   public Player getPlayer() {
      return this.player;
   }
}

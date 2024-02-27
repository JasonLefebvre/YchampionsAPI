// Package dans lequel se trouve la classe
package fr.omegion.api.events;

// Importations de classes nécessaires pour gérer les événements liés à la déconnexion des joueurs
import fr.omegion.api.OmegionAPI;
import java.sql.SQLException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

// Déclaration de la classe PlayerLeaveAPI, qui implémente l'interface Listener de Bukkit
public class PlayerLeaveAPI implements Listener {

   // Méthode annotée avec @EventHandler pour traiter les événements de déconnexion des joueurs
   @EventHandler
   public void leave(PlayerQuitEvent event) throws SQLException {
      // Récupération du joueur lié à l'événement
      Player player = event.getPlayer();

      // Déconnexion du joueur en utilisant la méthode disconnect de la classe PlayersManager
      OmegionAPI.getInstance().getPlayersManager().disconnect(player);
   }
}

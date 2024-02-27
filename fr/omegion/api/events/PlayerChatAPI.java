// Package dans lequel se trouve la classe
package fr.omegion.api.events;

// Importations de classes nécessaires pour gérer les événements liés au chat des joueurs
import fr.omegion.api.OmegionAPI;
import fr.omegion.api.accounts.OmegionPlayer;
import fr.omegion.api.utils.TextFormat;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

// Déclaration de la classe PlayerChatAPI, qui implémente l'interface Listener de Bukkit
public class PlayerChatAPI implements Listener {

   // Méthode annotée avec @EventHandler pour traiter les événements de chat asynchrones des joueurs
   @EventHandler
   public void onChat(AsyncPlayerChatEvent event) {
      // Récupération du joueur lié à l'événement
      Player player = event.getPlayer();

      // Récupération de l'objet OmegionPlayer associé au joueur
      OmegionPlayer omegionPlayer = OmegionAPI.getInstance().getPlayersManager().findPlayer(player);

      // Traduction des codes de couleur (&) dans le message du joueur
      event.setMessage(ChatColor.translateAlternateColorCodes('&', event.getMessage()));

      // Définition du format du message du joueur en utilisant une méthode de TextFormat avec l'objet OmegionPlayer
      event.setFormat(TextFormat.getChatFormat(omegionPlayer));
   }
}

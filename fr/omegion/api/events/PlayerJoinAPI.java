// Package dans lequel se trouve la classe
package fr.omegion.api.events;

// Importations de classes nécessaires pour gérer les événements liés à la connexion des joueurs
import fr.omegion.api.OmegionAPI;
import fr.omegion.api.accounts.OmegionPlayer;
import fr.omegion.api.packets.Title;
import fr.omegion.api.utils.TextFormat;
import java.sql.SQLException;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

// Déclaration de la classe PlayerJoinAPI, qui implémente l'interface Listener de Bukkit
public class PlayerJoinAPI implements Listener {

   // Méthode annotée avec @EventHandler pour traiter les événements de connexion des joueurs
   @EventHandler
   public void onPlayerJoinServer(PlayerJoinEvent event) throws SQLException {
      // Récupération de l'objet OmegionPlayer associé au joueur et connexion du joueur
      OmegionPlayer omegionPlayer = OmegionAPI.getInstance().getPlayersManager().connect(event.getPlayer());

      // Configuration du nom du joueur dans la liste des joueurs en utilisant un format spécifique
      omegionPlayer.getPlayer().setPlayerListName(TextFormat.getTabFormat(omegionPlayer));

      // Création d'un objet Title pour afficher un titre personnalisé lors de la connexion
      Title connectTitle = new Title("§l§aOmeg§l§eion", "§o§6 Bon retour parmi nous !", 20, 60, 20);

      // Envoi du titre personnalisé au joueur
      connectTitle.send(omegionPlayer.getPlayer());
   }
}

// Package dans lequel se trouve la classe
package fr.omegion.api.managers;

// Importations de classes nécessaires pour gérer les joueurs, les groupes, les comptes bancaires et la base de données
import fr.omegion.api.OmegionAPI;
import fr.omegion.api.accounts.BankAccount;
import fr.omegion.api.accounts.OmegionPlayer;
import fr.omegion.api.permissions.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.entity.Player;

// Déclaration de la classe OmegionPlayersManager
public class OmegionPlayersManager {
   // HashMap contenant les joueurs connectés, associés à leur UUID
   private final HashMap<UUID, OmegionPlayer> connectedPlayers = new HashMap();

   // Méthode pour connecter un joueur
   public OmegionPlayer connect(Player handledPlayer) throws SQLException {
      UUID playerUUID = handledPlayer.getUniqueId();

      // Vérifie si le joueur est déjà connecté
      if (!this.playerAlreadyConnected(playerUUID)) {
         // Vérifie si le joueur est déjà enregistré dans la base de données, sinon l'enregistre
         if (!this.playerAlreadyRegistered(playerUUID)) {
            this.storePlayer(handledPlayer);
         }

         // Ajoute le joueur à la liste des joueurs connectés
         this.addConnectedPlayer(handledPlayer);
      }

      // Renvoie l'objet OmegionPlayer associé au joueur
      return this.connectedPlayers.get(playerUUID);
   }

   // Méthode pour déconnecter un joueur
   public void disconnect(Player handledPlayer) throws SQLException {
      // Met à jour les informations du joueur dans la base de données
      this.updatePlayer(handledPlayer);

      // Retire le joueur de la liste des joueurs connectés
      this.connectedPlayers.remove(handledPlayer.getUniqueId());
   }

   // Méthode pour obtenir la liste des joueurs connectés
   public HashMap<UUID, OmegionPlayer> getConnectedPlayers() {
      return this.connectedPlayers;
   }
}
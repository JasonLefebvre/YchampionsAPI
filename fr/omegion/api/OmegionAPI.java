// Déclaration de la classe principale du plugin OmegionAPI
package fr.omegion.api;

import fr.omegion.api.database.OmegionDatabase;
import fr.omegion.api.events.PlayerChatAPI;
import fr.omegion.api.events.PlayerJoinAPI;
import fr.omegion.api.events.PlayerLeaveAPI;
import fr.omegion.api.managers.EventsManager;
import fr.omegion.api.managers.OmegionGroupsManager;
import fr.omegion.api.managers.OmegionPlayersManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public class OmegionAPI extends JavaPlugin {
   // Instance statique du plugin pour permettre un accès facile depuis d'autres parties du code
   private static OmegionAPI instance;

   // Instance de la base de données utilisée par le plugin
   private final OmegionDatabase omegionDatabase = new OmegionDatabase("jdbc:mysql://", "omegion.fr:15678", "server", "server", "BCoNwPTOCogCkcMM");

   // Gestionnaire des joueurs connectés
   private final OmegionPlayersManager omegionPlayersManager = new OmegionPlayersManager();

   // Gestionnaire des groupes de permissions
   private final OmegionGroupsManager omegionGroupsManager = new OmegionGroupsManager();

   // Méthode appelée lors de l'activation du plugin
   public void onEnable() {
      // Initialisation de l'instance du plugin
      instance = this;

      // Configuration par défaut
      this.getConfig().options().copyDefaults(true);
      this.saveConfig();

      // Message indiquant que le plugin Omegion API est chargé
      this.getLogger().info("Omegion API loaded");

      // Connexion à la base de données
      this.omegionDatabase.connect();

      // Initialisation du gestionnaire d'événements avec les événements spécifiques du plugin
      EventsManager eventsManager = new EventsManager(new Listener[]{new PlayerJoinAPI(), new PlayerChatAPI(), new PlayerLeaveAPI()});
      eventsManager.load();

      // Chargement des groupes de permissions depuis la base de données
      try {
         this.omegionGroupsManager.loadGroups();
      } catch (SQLException var3) {
         var3.printStackTrace();
      }
   }

   // Méthode statique pour obtenir l'instance du plugin
   public static OmegionAPI getInstance() {
      return instance;
   }

   // Méthode pour obtenir le gestionnaire des joueurs
   public OmegionPlayersManager getPlayersManager() {
      return this.omegionPlayersManager;
   }

   // Méthode pour obtenir la base de données du plugin
   public OmegionDatabase getOmegionDatabase() {
      return this.omegionDatabase;
   }

   // Méthode pour obtenir le gestionnaire des groupes de permissions
   public OmegionGroupsManager getGroupsManager() {
      return this.omegionGroupsManager;
   }
}

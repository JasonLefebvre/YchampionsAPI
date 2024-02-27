// Package dans lequel se trouve la classe
package fr.omegion.api.permissions;

// Importations de classes nécessaires pour gérer les permissions et la base de données
import fr.omegion.api.OmegionAPI;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Déclaration de la classe Group
public class Group {
   // Variables d'instance pour stocker les informations du groupe
   private Integer id;
   private Integer parentId;
   private String slug;
   private String chatPrefix;
   private String chatFormat;
   private String tabPrefix;
   private ArrayList<Permission> permissions = new ArrayList();

   // Constructeur pour initialiser les propriétés du groupe en récupérant les données de la base de données
   public Group(Integer id, Integer parentId, String slug, String chatPrefix, String chatFormat, String tabPrefix) throws SQLException {
      this.id = id;
      this.parentId = parentId;
      this.slug = slug;
      this.chatPrefix = chatPrefix;
      this.chatFormat = chatFormat;
      this.tabPrefix = tabPrefix;
      this.loadPermissions(); // Chargement des permissions associées au groupe depuis la base de données
   }

   // Méthode privée pour charger les permissions associées au groupe depuis la base de données
   private void loadPermissions() throws SQLException {
      // Requête SQL pour récupérer les permissions liées à ce groupe
      PreparedStatement query = OmegionAPI.getInstance().getOmegionDatabase().connection().prepareStatement("SELECT permissions.* FROM permissions INNER JOIN groups_permissions ON permission_id = permissions.id INNER JOIN groups ON group_id = groups.id WHERE groups.id = ?");
      query.setInt(1, this.id);
      ResultSet resultSet = query.executeQuery();

      // Parcours des résultats pour créer des objets Permission et les ajouter à la liste des permissions du groupe
      while(resultSet.next()) {
         Permission permission = new Permission(resultSet.getInt("id"), resultSet.getString("slug"), resultSet.getString("name"), resultSet.getString("description"));
         this.permissions.add(permission);
      }
   }

   // Méthodes getter et setter pour accéder et modifier les propriétés du groupe
   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Integer getParentId() {
      return this.parentId;
   }

   public void setParentId(Integer parentId) {
      this.parentId = parentId;
   }

   public String getSlug() {
      return this.slug;
   }

   public void setSlug(String slug) {
      this.slug = slug;
   }

   public String getChatPrefix() {
      return this.chatPrefix;
   }

   public void setChatPrefix(String chatPrefix) {
      this.chatPrefix = chatPrefix;
   }

   public String getTabPrefix() {
      return this.tabPrefix;
   }

   public void setTabPrefix(String tabPrefix) {
      this.tabPrefix = tabPrefix;
   }

   public String getChatFormat() {
      return this.chatFormat;
   }

   public void setChatFormat(String chatFormat) {
      this.chatFormat = chatFormat;
   }

   public ArrayList<Permission> getPermissions() {
      return this.permissions;
   }
}

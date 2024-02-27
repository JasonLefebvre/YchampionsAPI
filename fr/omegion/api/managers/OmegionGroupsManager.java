// Package dans lequel se trouve la classe
package fr.omegion.api.managers;

// Importations de classes nécessaires pour gérer les groupes et la base de données
import fr.omegion.api.OmegionAPI;
import fr.omegion.api.permissions.Group;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

// Déclaration de la classe OmegionGroupsManager
public class OmegionGroupsManager {
   // Liste des groupes chargés depuis la base de données
   private ArrayList<Group> groups = new ArrayList();

   // Méthode pour charger les groupes depuis la base de données
   public void loadGroups() throws SQLException {
      // Préparation de la requête SQL pour récupérer les groupes
      PreparedStatement query = OmegionAPI.getInstance().getOmegionDatabase().connection().prepareStatement("SELECT * FROM groups");

      // Exécution de la requête et récupération des résultats dans un ResultSet
      ResultSet resultSet = query.executeQuery();

      // Parcours des résultats pour créer et ajouter des objets Group à la liste
      while(resultSet.next()) {
         this.groups.add(new Group(
                 resultSet.getInt("id"),
                 resultSet.getInt("parent_id"),
                 resultSet.getString("slug"),
                 resultSet.getString("chat_prefix"),
                 resultSet.getString("chat_format"),
                 resultSet.getString("tab_prefix")
         ));
      }
   }

   // Méthode pour obtenir la liste des groupes
   public ArrayList<Group> getGroups() {
      return this.groups;
   }

   // Méthode pour définir la liste des groupes
   public void setGroups(ArrayList<Group> groups) {
      this.groups = groups;
   }
}

// Déclaration de la classe TextFormat
package fr.omegion.api.utils;

import fr.omegion.api.accounts.OmegionPlayer;

public class TextFormat {
   // Méthode statique pour obtenir le format du chat en fonction du joueur OmegionPlayer
   public static String getChatFormat(OmegionPlayer omegionPlayer) {
      // Récupération du pseudo en utilisant le pseudo réel ou le pseudo factice si disponible
      String pseudo = omegionPlayer.getFakePseudo() != null ? omegionPlayer.getFakePseudo() : omegionPlayer.getPseudo();

      // Construction du format du chat en utilisant les préfixes, le pseudo et le format du groupe du joueur
      return omegionPlayer.getGroup().getChatPrefix() + "§r " + pseudo + " " + omegionPlayer.getGroup().getChatFormat() + " %2$s";
   }

   // Méthode statique pour obtenir le format du tableau des joueurs en fonction du joueur OmegionPlayer
   public static String getTabFormat(OmegionPlayer omegionPlayer) {
      // Récupération du pseudo en utilisant le pseudo réel ou le pseudo factice si disponible
      String pseudo = omegionPlayer.getFakePseudo() != null ? omegionPlayer.getFakePseudo() : omegionPlayer.getPseudo();

      // Construction du format du tableau des joueurs en utilisant le préfixe du tableau et le pseudo
      return omegionPlayer.getGroup().getTabPrefix() + "§r " + pseudo;
   }
}
